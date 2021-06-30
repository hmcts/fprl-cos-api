package uk.gov.hmcts.reform.fprl.workflows;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.framework.workflow.DefaultWorkflow;
import uk.gov.hmcts.reform.fprl.tasks.emails.ExampleEmailTask;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleWorkflow extends DefaultWorkflow<Map<String, Object>> {

    private final ExampleEmailTask exampleEmailTask;

    public Map<String, Object> run() throws WorkflowException {
        return this.execute(
            new Task[] {
                exampleEmailTask
            },
            new HashMap<>()
        );
    }
}
