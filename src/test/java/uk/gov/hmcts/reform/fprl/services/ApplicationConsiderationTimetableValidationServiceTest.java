package uk.gov.hmcts.reform.fprl.services;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class ApplicationConsiderationTimetableValidationServiceTest {

    private ApplicationConsiderationTimetableValidationService applicationConsiderationTimetableValidationService;

    @Before
    public void setup() {
        applicationConsiderationTimetableValidationService = new ApplicationConsiderationTimetableValidationService();
    }

    @Test
    public void givenNeitherDaysOrHoursProvided_whenCheckingNoticeEfforts_thenNoError() {
        Map<String, Object> caseData = emptyMap();
        List<String> errors = applicationConsiderationTimetableValidationService.getErrorForApplicationNoticeEfforts(caseData);

        assertThat(errors, is(empty()));
    }
}
