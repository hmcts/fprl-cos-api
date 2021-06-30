package uk.gov.hmcts.reform.fprl.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.fprl.clients.EmailClient;
import uk.gov.hmcts.reform.fprl.config.EmailTemplatesConfig;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;
    private final EmailTemplatesConfig emailTemplatesConfig;

    public Map<String, Object> send(String email,
                                    EmailTemplateNames templateName,
                                    EmailTemplateVars templateVars,
                                    LanguagePreference languagePreference) {
        return null;
    }

    //    private void sendEmailUsingClient(EmailToSend emailToSend, String emailDescription)
    //        log.debug("Attempting to send {} email. Reference ID: {}", emailDescription, emailToSend.getReferenceId()
    //        emailClient.sendEmail(
    //            emailToSend.getTemplateId(),
    //            emailToSend.getDestinationEmailAddress(),
    //            emailToSend.getTemplateFields(),
    //            emailToSend.getReferenceId()
    //        );
    //        log.info("Sending email success. Reference ID: {}", emailToSend.getReferenceId());
    //    }
}
