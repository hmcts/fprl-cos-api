package uk.gov.hmcts.reform.fprl.tasks.emails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.notify.CitizenEmail;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.services.EmailService;
import uk.gov.hmcts.reform.fprl.utils.CaseDetailsProvider;
import uk.gov.hmcts.reform.fprl.utils.TaskContextProvider;
import uk.gov.hmcts.reform.fprl.utils.TestConstants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ExampleEmailTaskTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ExampleEmailTask task;

    @Test
    public void getTemplateShouldReturnValidValue() {
        assertThat(task.getTemplate(), is(EmailTemplateNames.EXAMPLE));
    }

    @Test
    public void getPersonalisationShouldReturnModel() {
        assertThat(
            task.getPersonalisation(TaskContextProvider.empty(), CaseDetailsProvider.empty()),
            is(expectedPersonalisation())
        );
    }

    @Test
    public void executeCallsEmailService() {
        task.execute(TaskContextProvider.empty(), CaseDetailsProvider.empty());

        verify(emailService).send(
            TestConstants.TEST_SOLICITOR_EMAIL,
            EmailTemplateNames.EXAMPLE,
            expectedPersonalisation(),
            LanguagePreference.ENGLISH
        );
    }

    public static EmailTemplateVars expectedPersonalisation() {
        return CitizenEmail.builder()
            .caseReference(TestConstants.TEST_CASE_ID)
            .petitionerName(TestConstants.TEST_PETITIONER_NAME)
            .respondentName(TestConstants.TEST_RESPONDENT_NAME)
            .build();
    }
}
