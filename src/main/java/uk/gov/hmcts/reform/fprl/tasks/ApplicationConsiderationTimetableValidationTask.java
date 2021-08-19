package uk.gov.hmcts.reform.fprl.tasks;

import uk.gov.hmcts.reform.ccd.client.model.CaseDetails;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;

public class ApplicationConsiderationTimetableValidationTask implements Task<CaseDetails> {

    @Override
    public CaseDetails execute(TaskContext context, CaseDetails payload) throws TaskException {
        return null;
    }
}
