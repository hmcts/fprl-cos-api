package uk.gov.hmcts.reform.fprl.models.dto.ccd;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class WorkflowResult {

    Map<String, Object> caseData;
    List<String> warnings;
    List<String> errors;

    public WorkflowResult(Map<String, Object> caseData) {
        this.caseData = caseData;
        warnings = new ArrayList<>();
        errors = new ArrayList<>();
    }
}
