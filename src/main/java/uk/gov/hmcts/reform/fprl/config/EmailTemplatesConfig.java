package uk.gov.hmcts.reform.fprl.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import uk.gov.hmcts.reform.fprl.models.LanguagePreference;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "uk.gov.notify.email")
@Validated
@Getter
public class EmailTemplatesConfig {

    private final Map<LanguagePreference, Map<String, String>> templates = new HashMap<>();
}
