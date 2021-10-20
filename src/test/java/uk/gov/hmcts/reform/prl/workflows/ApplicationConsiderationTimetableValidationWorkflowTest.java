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
import uk.gov.hmcts.reform.fprl.tasks.ApplicationTimetableEffortsValidationTask;
import uk.gov.hmcts.reform.fprl.tasks.ApplicationTimetableTimeValidationTask;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationConsiderationTimetableValidationWorkflowTest {

    @Mock private ApplicationTimetableTimeValidationTask applicationTimetableTimeValidationTask;
    @Mock private ApplicationTimetableEffortsValidationTask applicationTimetableEffortsValidationTask;

    @InjectMocks
    private ApplicationConsiderationTimetableValidationWorkflow applicationConsiderationTimetableValidationWorkflow;

    @Test
    public void whenWorkflowRun_thenExpectedTasksInvoked() throws WorkflowException {
        applicationConsiderationTimetableValidationWorkflow.run(
            CallbackRequest.builder()
                .caseDetails(CaseDetails.builder()
                                 .data(ImmutableMap.of())
                                 .build())
                .build());

        verify(applicationTimetableTimeValidationTask).execute(any(), any());
        verify(applicationTimetableEffortsValidationTask).execute(any(), any());
    }
}
