package uk.gov.hmcts.reform.fprl.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Service
@Slf4j
public class ApplicationConsiderationTimetableValidationService {

    public static final String APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS = "ApplicationConsideredInDaysAndHours";
    public static final String DAYS = "days";
    public static final String HOURS = "hours";

    public List<String> getErrorForApplicationNoticeEfforts(Map<String, Object> caseData) {
        boolean noticeEffortsIsBlank = Objects.toString(caseData.get("ApplicationNoticeEfforts"), "").isBlank();

        return applicationToBeConsideredInLessThan48Hours(caseData) && noticeEffortsIsBlank
            ? singletonList("Proposed timetable has to include either days or hours")
            : emptyList();
    }

    private boolean applicationToBeConsideredInLessThan48Hours(Map<String, Object> caseData) {
        Map<String, Object> applicationConsideredInDaysAndHours = (Map<String, Object>) caseData.get(APPLICATION_CONSIDERED_IN_DAYS_AND_HOURS);
        if (applicationConsideredInDaysAndHours != null) {
            int days = Integer.valueOf(Objects.toString(applicationConsideredInDaysAndHours.get(DAYS), "0"));
            int hours = Integer.valueOf(Objects.toString(applicationConsideredInDaysAndHours.get(HOURS), "0"));

            return days * 24 + hours < 48;
        }
        return false;
    }
}
