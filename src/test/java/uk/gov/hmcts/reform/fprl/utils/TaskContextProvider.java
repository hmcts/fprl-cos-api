package uk.gov.hmcts.reform.fprl.utils;

import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.fprl.framework.context.DefaultTaskContext;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;

@NoArgsConstructor
public class TaskContextProvider {

    public static TaskContext empty() {
        return new DefaultTaskContext();
    }
}
