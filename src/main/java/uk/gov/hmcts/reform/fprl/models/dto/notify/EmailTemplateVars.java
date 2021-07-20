package uk.gov.hmcts.reform.fprl.models.dto.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplateVars {

    @JsonProperty("caseReference")
    private String caseReference;
}
