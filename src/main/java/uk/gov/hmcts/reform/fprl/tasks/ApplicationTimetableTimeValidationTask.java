package uk.gov.hmcts.reform.fprl.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.YesOrNo;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;

import java.util.Map;

import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.DAYS;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.HOURS;
import static uk.gov.hmcts.reform.fprl.models.OrchestrationConstants.IS_APPLICATION_URGENT;

@Component
@Slf4j
public class ApplicationTimetableTimeValidationTask implements Task<WorkflowResult> {

    public static final String ERROR_MSG_NOTICE_DATE_OR_TIME_REQUIRED = "Please provide either days or hours in proposed timetable";

    @Override
    public WorkflowResult execute(TaskContext context, WorkflowResult workflowResult) throws TaskException {
        Map<String, Object> caseData = workflowResult.getCaseData();

        log.info("applicationIsUrgent = {}, timetableContainsEitherDaysOrHours = {}", applicationIsUrgent(caseData), timetableContainsEitherDaysOrHours(caseData));

        if (applicationIsUrgent(caseData) && !timetableContainsEitherDaysOrHours(caseData)) {
            log.info("Inside the flow ApplicationTimetableTimeValidationTask adding error");
            workflowResult.getErrors().add(ERROR_MSG_NOTICE_DATE_OR_TIME_REQUIRED);
            context.setTaskFailed(true);    // stop further validation tasks from running when no days or hours provided
        }

        return workflowResult;
    }

    private boolean applicationIsUrgent(Map<String, Object> caseData) {
        return YesOrNo.YES.equals(caseData.get(IS_APPLICATION_URGENT));
    }

    private boolean timetableContainsEitherDaysOrHours(Map<String, Object> caseData) {
        Map<String, Object> applicationConsideredInDaysAndHours = (Map<String, Object>) caseData.get(APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS);

        return applicationConsideredInDaysAndHours != null
            && (applicationConsideredInDaysAndHours.get(DAYS) != null
            || applicationConsideredInDaysAndHours.get(HOURS) != null);
    }
}
