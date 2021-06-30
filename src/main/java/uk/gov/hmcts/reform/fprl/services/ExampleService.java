package uk.gov.hmcts.reform.fprl.services;

import org.springframework.stereotype.Component;

/**
 * This class is added only as a java service example. It can be deleted when more services is added.
 */
@Component
public class ExampleService {

    public static final String HELLO_WORLD = "Hello World";

    public String getMessage() {
        return HELLO_WORLD;
    }
}
