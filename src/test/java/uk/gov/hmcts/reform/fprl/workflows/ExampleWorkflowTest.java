package uk.gov.hmcts.reform.fprl.workflows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.tasks.emails.ExampleEmailTask;
import uk.gov.hmcts.reform.fprl.utils.CaseDetailsProvider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.gov.hmcts.reform.fprl.utils.Verificators.mockTasksExecution;
import static uk.gov.hmcts.reform.fprl.utils.Verificators.verifyTaskWasCalled;

@RunWith(MockitoJUnitRunner.class)
public class ExampleWorkflowTest {

    @Mock
    private ExampleEmailTask exampleEmailTask;

    @InjectMocks
    private ExampleWorkflow exampleWorkflow;

    @Test
    public void shouldExecuteTask() throws Exception {
        CaseDetails caseDetails = CaseDetailsProvider.empty();
        mockTasksExecution(CaseDetailsProvider.empty(), exampleEmailTask);

        CaseDetails returned = exampleWorkflow.run(caseDetails);

        assertThat(returned, is(caseDetails));
        verifyTaskWasCalled(caseDetails, exampleEmailTask);
    }
}
