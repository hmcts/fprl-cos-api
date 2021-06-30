package uk.gov.hmcts.reform.fprl.tasks.emails.generics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.services.EmailService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public abstract class SendEmailTask implements Task<Map<String, Object>> {

    private final EmailService emailService;

    protected abstract EmailTemplateVars getPersonalisation(TaskContext context, Map<String, Object> caseData);

    protected abstract EmailTemplateNames getTemplate();

    protected abstract String getRecipientEmail(Map<String, Object> caseData);

    protected LanguagePreference getLanguage(Map<String, Object> caseData) {
        return LanguagePreference.ENGLISH; // fix
    }

    protected boolean canEmailBeSent(Map<String, Object> caseData) {
        return true;
    }

    @Override
    public Map<String, Object> execute(TaskContext context, Map<String, Object> caseData) {
        final String caseId = "1"; // getCaseId(context); fix it
        final String templateName = getTemplate().name();

        if (canEmailBeSent(caseData)) {
            log.info("CaseID: {} email {} is going to be sent.", caseId, templateName);

            emailService.send(
                getRecipientEmail(caseData),
                getTemplate(),
                getPersonalisation(context, caseData),
                getLanguage(caseData)
            );

            log.info("CaseID: {} email {} was sent.", caseId, templateName);
        } else {
            log.warn("CaseID: {} email {} will not be sent.", caseId, templateName);
        }

        return caseData;
    }
}

