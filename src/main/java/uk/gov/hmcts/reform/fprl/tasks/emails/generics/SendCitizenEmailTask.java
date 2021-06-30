package uk.gov.hmcts.reform.fprl.tasks.emails.generics;

import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.models.dto.notify.CitizenEmail;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.services.EmailService;

import java.util.Map;

public abstract class SendCitizenEmailTask extends SendEmailTask {

    public SendCitizenEmailTask(EmailService emailService) {
        super(emailService);
    }

    @Override
    protected EmailTemplateVars getPersonalisation(TaskContext context, Map<String, Object> caseData) {
        return CitizenEmail.CitizenEmailBuilder().build();
    }

    @Override
    protected String getRecipientEmail(Map<String, Object> caseData) {
        return (String) caseData.get("email");
    }
}

