package uk.gov.hmcts.reform.fprl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.workflows.ExampleWorkflow;

import java.util.Map;

/**
 * This class is added only as a java service example. It can be deleted when more services is added.
 */
@Component
@RequiredArgsConstructor
public class ExampleService {

    public static final String HELLO_WORLD = "Hello World";

    private final ExampleWorkflow exampleWorkflow;

    public String getMessage() {
        return HELLO_WORLD;
    }

    public Map<String, Object> executeExampleWorkflow() throws WorkflowException {
        return exampleWorkflow.run();
    }
}
