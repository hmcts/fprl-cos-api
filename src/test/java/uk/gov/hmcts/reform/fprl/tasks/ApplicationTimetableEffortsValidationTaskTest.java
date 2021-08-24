package uk.gov.hmcts.reform.fprl.tasks;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import uk.gov.hmcts.reform.fprl.framework.context.DefaultTaskContext;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.DAYS;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.HOURS;
import static uk.gov.hmcts.reform.fprl.tasks.ApplicationTimetableEffortsValidationTask.ERROR_MSG_NOTICE_EFFORTS_REQUIRED;

public class ApplicationTimetableEffortsValidationTaskTest {

    private ApplicationTimetableEffortsValidationTask applicationTimetableEffortsValidationTask
        = new ApplicationTimetableEffortsValidationTask();

    @Test
    public void whenApplicationToBeConsideredInLessThan48Hours_thenErrorReturnedInResult() {
        WorkflowResult workflowResult = new WorkflowResult(ImmutableMap.of(
            APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS, ImmutableMap.of(
                DAYS, "1")));

        workflowResult = applicationTimetableEffortsValidationTask.execute(new DefaultTaskContext(), workflowResult);
        assertThat(workflowResult.getErrors(), hasSize(1));
        assertThat(workflowResult.getErrors().get(0), is(ERROR_MSG_NOTICE_EFFORTS_REQUIRED));
    }

    @Test
    public void whenApplicationToBeConsideredIn48HoursOrMore_thenNoErrorReturnedInResult() {
        WorkflowResult workflowResult = new WorkflowResult(ImmutableMap.of(
            APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS, ImmutableMap.of(
                HOURS, "48")));

        workflowResult = applicationTimetableEffortsValidationTask.execute(new DefaultTaskContext(), workflowResult);
        assertThat(workflowResult.getErrors(), hasSize(0));
    }
}
