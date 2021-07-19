package uk.gov.hmcts.reform.fprl.tasks.emails;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.models.dto.notify.CitizenEmail;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.services.EmailService;
import uk.gov.hmcts.reform.fprl.tasks.emails.generics.SendEmailTask;

@Component
public class ExampleEmailTask extends SendEmailTask {

    protected ExampleEmailTask(EmailService emailService) {
        super(emailService);
    }

    @Override
    protected String getRecipientEmail(CaseDetails caseDetails) {
        return "fprl_caseworker_solicitor@mailinator.com";
    }

    @Override
    protected EmailTemplateNames getTemplate() {
        return EmailTemplateNames.EXAMPLE;
    }

    @Override
    protected EmailTemplateVars getPersonalisation(TaskContext context, CaseDetails caseDetails) {
        return CitizenEmail.CitizenEmailBuilder()
            .caseReference("1234567890")
            .petitionerName("Jack Kirman")
            .respondentName("Jill Kirman")
            .build();
    }

    @Override
    protected LanguagePreference getLanguage(CaseDetails caseDetails) {
        return LanguagePreference.ENGLISH;
    }
}
