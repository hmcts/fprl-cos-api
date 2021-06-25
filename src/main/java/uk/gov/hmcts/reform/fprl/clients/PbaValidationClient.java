package uk.gov.hmcts.reform.fprl.clients;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import uk.gov.hmcts.reform.fprl.config.consts.CustomHttpHeaders;
import uk.gov.hmcts.reform.fprl.models.payment.PbaOrganisationResponse;

@FeignClient(name = "pba-validation-client", url = "${pba.validation.service.api.baseurl}")
public interface PbaValidationClient {

    @ApiOperation("Validates Solicitor Pay By Account (PBA) number for payment")
    @GetMapping(value = "/refdata/external/v1/organisations/pbas")
    ResponseEntity<PbaOrganisationResponse> retrievePbaNumbers(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorisation,
        @RequestHeader(CustomHttpHeaders.SERVICE_AUTHORIZATION) String serviceAuthorisation,
        @RequestParam(name = "email") String email);
}
