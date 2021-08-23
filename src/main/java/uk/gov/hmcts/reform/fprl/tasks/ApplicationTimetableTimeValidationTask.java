package uk.gov.hmcts.reform.fprl.tasks;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;

import java.util.Map;

@Component
public class ApplicationTimetableTimeValidationTask implements Task<WorkflowResult> {

    public static final String APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS = "ApplicationConsideredInDaysAndHours";
    public static final String DAYS = "days";
    public static final String HOURS = "hours";
    public static final String ERROR_MSG_NOTICE_DATE_OR_TIME_REQUIRED = "Please provide either days or hours in proposed timetable";

    @Override
    public WorkflowResult execute(TaskContext context, WorkflowResult workflowResult) throws TaskException {
        if (!timetambleContainsEitherDaysOrHours(workflowResult.getCaseData())) {
            workflowResult.getErrors().add(ERROR_MSG_NOTICE_DATE_OR_TIME_REQUIRED);
            context.setTaskFailed(true);    // stop further validation tasks from running when no days or hours provided
        }

        return workflowResult;
    }

    private boolean timetambleContainsEitherDaysOrHours(Map<String, Object> caseData) {
        Map<String, Object> applicationConsideredInDaysAndHours = (Map<String, Object>) caseData.get(APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS);

        return applicationConsideredInDaysAndHours != null
            && (applicationConsideredInDaysAndHours.get(DAYS) != null
            || applicationConsideredInDaysAndHours.get(HOURS) != null);
    }
}
