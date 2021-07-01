package uk.gov.hmcts.reform.fprl.tasks.emails.generics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.fprl.framework.context.TaskContext;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseData;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;
import uk.gov.hmcts.reform.fprl.models.email.EmailTemplateNames;
import uk.gov.hmcts.reform.fprl.services.EmailService;
import uk.gov.hmcts.reform.fprl.utils.CaseDataProvider;
import uk.gov.hmcts.reform.fprl.utils.CaseDetailsProvider;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.gov.hmcts.reform.fprl.utils.TestConstants.TEST_EMAIL;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailTaskTest {

    public static final EmailTemplateNames EMAIL_TEMPLATE_ID = EmailTemplateNames.EXAMPLE;

    @Mock
    private EmailService emailService;

    private final SendEmailTask task = getSendEmailTaskInstance();

    private SendEmailTask getSendEmailTaskInstance() {
        return new SendEmailTask(emailService) {
            @Override
            protected EmailTemplateVars getPersonalisation(TaskContext context, CaseDetails caseDetails) {
                return EmailTemplateVars.builder().build();
            }

            @Override
            protected EmailTemplateNames getTemplate() {
                return EMAIL_TEMPLATE_ID;
            }

            @Override
            protected String getRecipientEmail(CaseDetails caseDetails) {
                return TEST_EMAIL;
            }
        };
    }

    @Test
    public void getLanguageShouldReturnEnglishWhenNotSpecified() {
        assertThat(task.getLanguage(CaseDetailsProvider.empty()), is(LanguagePreference.ENGLISH));
    }

    @Test
    public void getLanguageShouldReturnEnglishWhenSetEnglishOrEmpty() {
        asList(CaseDataProvider.english(), CaseDataProvider.empty()).forEach(caseData -> {
            assertThat(
                task.getLanguage(CaseDetailsProvider.of(caseData)),
                is(LanguagePreference.ENGLISH)
            );
        });
    }

    @Test
    public void getLanguageShouldReturnWelshWhenSetWelsh() {
        CaseData caseData = CaseDataProvider.welsh();

        assertThat(
            task.getLanguage(CaseDetailsProvider.of(caseData)),
            is(LanguagePreference.WELSH)
        );
    }

    @Test
    public void canEmailBeSentShouldReturnTrue() {
        assertThat(task.canEmailBeSent(CaseDetailsProvider.empty()), is(true));
    }
}
