package uk.gov.hmcts.reform.fprl.utils;

import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseData;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;

@NoArgsConstructor
public class CaseDetailsProvider {

    public static CaseDetails empty() {
        return CaseDetails.builder().caseData(CaseDataProvider.empty()).build();
    }

    public static CaseDetails of(CaseData caseData) {
        return CaseDetails.builder().caseData(caseData).build();
    }
}
