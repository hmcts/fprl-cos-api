package uk.gov.hmcts.reform.fprl.models.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CitizenEmail {

    @JsonProperty("caseReference")
    private String caseReference;

    @JsonProperty("petitionerName")
    private String petitionerName;

    @JsonProperty("respondentName")
    private String respondentName;
}
