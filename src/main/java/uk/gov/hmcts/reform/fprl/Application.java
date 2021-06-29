package uk.gov.hmcts.reform.fprl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
    "uk.gov.hmcts.reform.fprl"
})
@SpringBootApplication(
    scanBasePackages = {
        "uk.gov.hmcts.reform.fprl"
    }
)
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
