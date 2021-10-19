package uk.gov.hmcts.reform.fprl.workflows;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.ccd.client.model.CallbackRequest;
import uk.gov.hmcts.reform.ccd.client.model.CaseDetails;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.tasks.ConfirmMiamApplicationOrExemptionTask;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmMiamApplicationOrExemptionWorkFlowTest {

    @Mock private ConfirmMiamApplicationOrExemptionTask confirmMiamApplicationOrExemptionTask;

    @InjectMocks
    private ConfirmMiamApplicationOrExemptionWorkflow confirmMiamApplicationOrExemptionWorkflow;

    @Test
    public void whenWorkflowRun_thenExpectedTasksInvoked() throws WorkflowException {
        confirmMiamApplicationOrExemptionWorkflow.run(
            CallbackRequest.builder()
                .caseDetails(CaseDetails.builder()
                                 .data(ImmutableMap.of())
                                 .build())
                .build());

        verify(confirmMiamApplicationOrExemptionTask).execute(any(), any());
    }
}
