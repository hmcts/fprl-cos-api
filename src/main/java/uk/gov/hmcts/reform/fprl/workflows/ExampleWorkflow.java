package uk.gov.hmcts.reform.fprl.workflows;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.framework.task.Task;
import uk.gov.hmcts.reform.fprl.framework.workflow.DefaultWorkflow;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.tasks.emails.ExampleEmailTask;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleWorkflow extends DefaultWorkflow<CaseDetails> {

    private final ExampleEmailTask exampleEmailTask;

    public CaseDetails run(CaseDetails caseDetails) throws WorkflowException {
        return this.execute(
            new Task[]{
                exampleEmailTask
            },
            caseDetails
        );
    }
}
