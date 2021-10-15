package uk.gov.hmcts.reform.fprl.tasks;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.context.DefaultTaskContext;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;

import java.util.Map;

import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.YES;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.APPLICANT_ATTENDED_MIAM;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.CLAIMING_EXEMPTION_MIAM;

@Component
public class ConfirmMiamApplicationOrExemptionTask implements Task<WorkflowResult> {

    public static final String ERROR_MSG_MIAM =
        "You cannot make this application unless the applicant has either attended, or is exempt from attending a MIAM";

    @Override
    public WorkflowResult execute(TaskContext context, WorkflowResult payload) throws TaskException {

        Map<String, Object> caseData = payload.getCaseData();

        if (!applicantHasAttendedMIAM(caseData) && !applicantIsClaimingMIAMExemption(caseData)) {
            payload.getErrors().add(ERROR_MSG_MIAM);
        }

        return payload;
    }

    private boolean applicantHasAttendedMIAM(Map<String, Object> caseData) {
        return YES.equals(caseData.get(APPLICANT_ATTENDED_MIAM));
    }

    private boolean applicantIsClaimingMIAMExemption(Map<String, Object> caseData) {
        return YES.equals(caseData.get(CLAIMING_EXEMPTION_MIAM));
    }

}

