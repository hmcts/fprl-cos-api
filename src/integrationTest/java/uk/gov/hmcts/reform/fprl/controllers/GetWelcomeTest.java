package uk.gov.hmcts.reform.fprl.controllers;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.reform.fprl.IntegrationTest;
import uk.gov.hmcts.reform.fprl.util.CosApiClient;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetWelcomeTest extends IntegrationTest {

    @Autowired
    private CosApiClient cosApiClient;

    @DisplayName("Should welcome upon root request with 200 response code")
    @Test
    public void welcomeRootEndpoint() {
        String response = cosApiClient.welcome();

        assertThat(response, startsWith("Welcome"));
    }
}
