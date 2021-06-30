package uk.gov.hmcts.reform.fprl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {
    "uk.gov.hmcts.reform.fprl"
})
/*
 I don't know why this was not working, but I did what was suggested here:
 https://stackoverflow.com/questions/26889970/
 intellij-incorrectly-saying-no-beans-of-type-found-for-autowired-repository/41766552
    @SpringBootApplication(
    scanBasePackages = {
        "uk.gov.hmcts.reform.fprl"
      }
    )
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    basePackages = "uk.gov.hmcts.reform.fprl"
)
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
