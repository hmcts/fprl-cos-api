package uk.gov.hmcts.reform.fprl.tasks;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;

import java.util.Map;

import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.APPLICANT_ATTENDED_MIAM;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.CLAIMING_EXEMPTION_MIAM;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.NO;


@Component
public class validateMiamApplicationOrExemptionTask implements Task<WorkflowResult> {

    public static final String ERROR_MSG_MIAM =
        "You cannot make this application unless the applicant has either attended, or is exempt from attending a MIAM";

    @Override
    public WorkflowResult execute(TaskContext context, WorkflowResult payload) throws TaskException {

        Map<String, Object> caseData = payload.getCaseData();

        if (applicantHasNotAttendedMiam(caseData) && applicantIsNotClaimingMiamExemption(caseData)) {
            payload.getErrors().add(ERROR_MSG_MIAM);
        }

        return payload;
    }

    private boolean applicantHasNotAttendedMiam(Map<String, Object> caseData) {
        return NO.equals(caseData.get(APPLICANT_ATTENDED_MIAM));
    }

    private boolean applicantIsNotClaimingMiamExemption(Map<String, Object> caseData) {
        return NO.equals(caseData.get(CLAIMING_EXEMPTION_MIAM));
    }

}

