package uk.gov.hmcts.reform.fprl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.fprl.config.Features;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseData;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.workflows.ExampleWorkflow;

/**
 * This class is added only as a java service example. It can be deleted when more services is added.
 */
@Component
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleWorkflow exampleWorkflow;
    private final FeatureToggleService featureToggleService;

    public CaseData executeExampleWorkflow(CaseDetails caseDetails) throws WorkflowException {
        if (featureToggleService.isFeatureEnabled(Features.EXAMPLE)) {
            return exampleWorkflow.run(caseDetails).getCaseData();
        }

        return caseDetails.getCaseData();
    }
}
