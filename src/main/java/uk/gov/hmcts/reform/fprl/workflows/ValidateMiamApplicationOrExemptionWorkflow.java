package uk.gov.hmcts.reform.fprl.workflows;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.ccd.client.model.CallbackRequest;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.framework.workflow.DefaultWorkflow;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;
import uk.gov.hmcts.reform.fprl.tasks.ValidateMiamApplicationOrExemptionTask;

@Component
@RequiredArgsConstructor
public class ValidateMiamApplicationOrExemptionWorkflow extends DefaultWorkflow<WorkflowResult> {

    private final ValidateMiamApplicationOrExemptionTask validateMiamApplicationOrExemptionTask;


    public WorkflowResult run(CallbackRequest callbackRequest) throws WorkflowException {

        return this.execute(
            new Task[] {
                validateMiamApplicationOrExemptionTask
            },
            new WorkflowResult(callbackRequest.getCaseDetails().getData())
        );
    }
}
