package uk.gov.hmcts.reform.fprl.tasks.emails;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.services.EmailService;
import uk.gov.hmcts.reform.fprl.tasks.emails.generics.SendCitizenEmailTask;

import java.util.Map;

@Component
public class ExampleEmailTask extends SendCitizenEmailTask {

    protected ExampleEmailTask(EmailService emailService) {
        super(emailService);
    }

    @Override
    protected String getRecipientEmail(Map<String, Object> caseData) {
        return "example@mailinator.com";
    }

    @Override
    protected EmailTemplateNames getTemplate() {
        return EmailTemplateNames.EXAMPLE;
    }
}
