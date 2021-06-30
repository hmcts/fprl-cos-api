package uk.gov.hmcts.reform.fprl.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.fprl.workflows.ExampleWorkflow;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceTest {

    @Mock
    ExampleWorkflow exampleWorkflow;

    @InjectMocks
    ExampleService exampleService;

    @Test
    public void testToGetCoverage() {
        assertEquals(ExampleService.HELLO_WORLD, exampleService.getMessage());
    }
}
