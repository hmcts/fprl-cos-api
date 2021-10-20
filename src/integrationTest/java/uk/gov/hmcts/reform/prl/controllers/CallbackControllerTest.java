package uk.gov.hmcts.reform.fprl.controllers;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.reform.fprl.IntegrationTest;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CallbackRequest;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.util.CosApiClient;

public class CallbackControllerTest extends IntegrationTest {

    @Autowired
    private CosApiClient cosApiClient;

    @DisplayName("temporary test to verify gov UK notifications integration")
    @Test
    public void sendEmail() {
        cosApiClient.sendEmail(CallbackRequest.builder()
                                   .caseDetails(CaseDetails.builder().build())
                                   .build());
    }
}
