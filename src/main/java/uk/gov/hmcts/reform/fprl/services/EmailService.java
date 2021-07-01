package uk.gov.hmcts.reform.fprl.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.fprl.clients.EmailClient;
import uk.gov.hmcts.reform.fprl.config.EmailTemplatesConfig;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.utils.EmailObfuscator;
import uk.gov.service.notify.NotificationClientException;
import uk.gov.service.notify.SendEmailResponse;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;
    private final EmailTemplatesConfig emailTemplatesConfig;
    private final ObjectMapper objectMapper;

    public void send(String email,
                     EmailTemplateNames templateName,
                     EmailTemplateVars templateVars,
                     LanguagePreference languagePreference) {
        final String reference = generateReference();
        onBeforeLog(email, templateName, templateVars.getCaseReference(), reference);
        final String templateId = getTemplateId(templateName, languagePreference);

        try {
            SendEmailResponse response = emailClient.sendEmail(templateId, email, toMap(templateVars), reference);
            onAfterLog(templateName, templateVars.getCaseReference(), reference, response.getNotificationId());
        } catch (NotificationClientException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    private void onAfterLog(EmailTemplateNames name, String caseId, String reference, UUID notificationId) {
        log.info(
            "CaseId: {}: email {} sent. NotificationId = {}, Reference = {}",
            caseId, name, notificationId, reference
        );
    }

    private Map<String, String> toMap(EmailTemplateVars templateVars) {
        return objectMapper.convertValue(templateVars, Map.class);
    }

    private void onBeforeLog(String email, EmailTemplateNames name, String caseId, String reference) {
        log.info(
            "CaseId: {}: attempting to send email {} to {}. Reference = {}",
            caseId, name, EmailObfuscator.obfuscate(email), reference
        );
    }

    private String generateReference() {
        return UUID.randomUUID().toString();
    }

    private String getTemplateId(EmailTemplateNames templateName, LanguagePreference languagePreference) {
        return emailTemplatesConfig.getTemplates().get(languagePreference).get(templateName);
    }
}
