package uk.gov.hmcts.reform.fprl.framework.task;

import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.framework.exceptions.TaskException;

@FunctionalInterface
public interface Task<T> {
    T execute(TaskContext context, T payload) throws TaskException;
}
