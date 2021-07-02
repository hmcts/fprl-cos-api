package uk.gov.hmcts.reform.fprl.utils;

import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.fprl.models.dto.notify.EmailTemplateVars;

@NoArgsConstructor
public class EmailTemplateVarsProvider {

    public static EmailTemplateVars empty() {
        return EmailTemplateVars.builder().build();
    }

    public static EmailTemplateVars of(String caseId) {
        return EmailTemplateVars.builder().caseReference(caseId).build();
    }
}
