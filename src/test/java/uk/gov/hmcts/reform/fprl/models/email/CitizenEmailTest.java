package uk.gov.hmcts.reform.fprl.models.email;

import org.junit.Test;
import uk.gov.hmcts.reform.fprl.models.dto.notify.CitizenEmail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CitizenEmailTest {

    @Test
    public void customBuilderShouldWork() {
        assertThat(CitizenEmail.CitizenEmailBuilder().petitionerName("a").build().getPetitionerName(), is("a"));
        assertThat(CitizenEmail.CitizenEmailBuilder().respondentName("b").build().getRespondentName(), is("b"));
        assertThat(CitizenEmail.CitizenEmailBuilder().caseReference("c").build().getCaseReference(), is("c"));
    }
}
