package uk.gov.hmcts.reform.fprl.models.dto.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplateVars {

    @JsonProperty("caseReference")
    private String caseReference;
}
