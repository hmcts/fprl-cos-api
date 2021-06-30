package uk.gov.hmcts.reform.fprl.models.dto.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CitizenEmail extends EmailTemplateVars {

    @JsonProperty("petitionerName")
    private final String petitionerName;

    @JsonProperty("respondentName")
    private final String respondentName;

    @Builder(builderMethodName = "CitizenEmailBuilder")
    public CitizenEmail(String caseReference, String petitionerName, String respondentName) {
        super(caseReference);
        this.petitionerName = petitionerName;
        this.respondentName = respondentName;
    }
}
