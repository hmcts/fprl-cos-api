package uk.gov.hmcts.reform.fprl.utils;

import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.fprl.models.dto.notify.CitizenEmail;

@NoArgsConstructor
public class CitizenEmailProvider {

    public static CitizenEmail empty() {
        return builder().build();
    }

    public static CitizenEmail of(String caseId) {
        return builder().caseReference(caseId).build();
    }

    public static CitizenEmail of(String caseId, String petitionerName, String respondentName) {
        return builder()
            .caseReference(caseId)
            .petitionerName(petitionerName)
            .respondentName(respondentName)
            .build();
    }

    private static CitizenEmail.CitizenEmailBuilder builder() {
        return CitizenEmail.CitizenEmailBuilder();
    }
}
