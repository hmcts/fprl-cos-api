package uk.gov.hmcts.reform.fprl.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailObfuscatorTest {

    @Test
    public void obfuscateShortEmails() {
        assertThat(EmailObfuscator.obfuscate("     aa@example.com"), is("a*@example.com"));
        assertThat(EmailObfuscator.obfuscate("aa@example.com"), is("a*@example.com"));
        assertThat(EmailObfuscator.obfuscate("a@example.com"), is("a@example.com"));
    }

    @Test
    public void obfuscateNormalEmails() {
        assertThat(EmailObfuscator.obfuscate("     abcdef@example.com"), is("a***f@example.com"));
        assertThat(EmailObfuscator.obfuscate("abcdef@example.com"), is("a***f@example.com"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void obfuscateTooShortEmailShouldThrowException() {
        EmailObfuscator.obfuscate("@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void obfuscateInvalidEmailShouldThrowException() {
        EmailObfuscator.obfuscate("example.com");
    }
}
