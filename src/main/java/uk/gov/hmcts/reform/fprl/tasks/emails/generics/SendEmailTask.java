package uk.gov.hmcts.reform.fprl.tasks.emails.generics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.services.EmailService;

@Slf4j
@RequiredArgsConstructor
public abstract class SendEmailTask implements Task<CaseDetails> {

    private final EmailService emailService;

    protected abstract EmailTemplateVars getPersonalisation(TaskContext context, CaseDetails caseDetails);

    protected abstract EmailTemplateNames getTemplate();

    protected abstract String getRecipientEmail(CaseDetails caseDetails);

    protected LanguagePreference getLanguage(CaseDetails caseDetails) {
        return LanguagePreference.getLanguagePreference(caseDetails.getCaseData());
    }

    /**
     * By default - always send email. This can be used for scenarios e.g. when email is not mandatory.
     */
    protected boolean canEmailBeSent(CaseDetails caseDetails) {
        return true;
    }

    @Override
    public CaseDetails execute(TaskContext context, CaseDetails caseDetails) {
        final String caseId = caseDetails.getCaseId();
        final String templateName = getTemplate().name();

        if (canEmailBeSent(caseDetails)) {
            log.info("CaseID: {} email {} is going to be sent.", caseId, templateName);

            emailService.send(
                getRecipientEmail(caseDetails),
                getTemplate(),
                getPersonalisation(context, caseDetails),
                getLanguage(caseDetails)
            );

            log.info("CaseID: {} email {} was sent.", caseId, templateName);
        } else {
            log.warn("CaseID: {} email {} will not be sent.", caseId, templateName);
        }

        return caseDetails;
    }
}

