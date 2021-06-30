package uk.gov.hmcts.reform.fprl.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CcdCallbackResponse;
import uk.gov.hmcts.reform.fprl.services.ExampleService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class CallbackController {

    private final ExampleService exampleService;

    /**
     * It just an example.
     * */
    @PostMapping(path = "/send-email", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    @ApiOperation(value = "Callback to send email")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Callback processed.", response = CcdCallbackResponse.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<CcdCallbackResponse> email() throws WorkflowException {
        return ok(CcdCallbackResponse.builder().data(exampleService.executeExampleWorkflow()).build());
    }
}
