package uk.gov.hmcts.reform.fprl.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceTest {

    @Test
    public void testToGetCoverage() {
        assertEquals(ExampleService.HELLO_WORLD, new ExampleService().getMessage());
    }
}
