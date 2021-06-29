package uk.gov.hmcts.reform.fprl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.fprl.services.ExampleService;

@Configuration
public class ExampleConfigurator {

    @Bean
    public ExampleService setExampleService() {
        return new ExampleService();
    }
}
