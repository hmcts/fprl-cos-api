package uk.gov.hmcts.reform.fprl.tasks;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.WorkflowResult;

import java.util.Map;
import java.util.Objects;

@Component
public class ApplicationTimetableEffortsValidationTask implements Task<WorkflowResult> {

    public static final String APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS = "ApplicationConsideredInDaysAndHours";
    public static final String DAYS = "days";
    public static final String HOURS = "hours";
    public static final String ERROR_MSG_NOTICE_EFFORTS_REQUIRED =
        "Please specify what efforts have been made to put each respondent on notice of the application";

    @Override
    public WorkflowResult execute(TaskContext context, WorkflowResult workflowResult) throws TaskException {
        Map<String, Object> caseData = workflowResult.getCaseData();
        boolean noticeEffortsIsBlank = Objects.toString(caseData.get("ApplicationNoticeEfforts"), "").isBlank();

        if (applicationToBeConsideredInLessThan48Hours(caseData) && noticeEffortsIsBlank) {
            workflowResult.getErrors().add(ERROR_MSG_NOTICE_EFFORTS_REQUIRED);
        }

        return workflowResult;
    }

    private boolean applicationToBeConsideredInLessThan48Hours(Map<String, Object> caseData) {
        Map<String, Object> applicationConsideredInDaysAndHours = (Map<String, Object>) caseData.get(APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS);
        if (applicationConsideredInDaysAndHours != null) {
            int days = Integer.parseInt(Objects.toString(applicationConsideredInDaysAndHours.get(DAYS), "0"));
            int hours = Integer.parseInt(Objects.toString(applicationConsideredInDaysAndHours.get(HOURS), "0"));

            return days * 24 + hours < 48;
        }
        return false;
    }
}
