package uk.gov.hmcts.reform.fprl.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.fprl.config.Features;
import uk.gov.hmcts.reform.fprl.framework.exceptions.WorkflowException;
import uk.gov.hmcts.reform.fprl.models.dto.ccd.CaseDetails;
import uk.gov.hmcts.reform.fprl.utils.CaseDataProvider;
import uk.gov.hmcts.reform.fprl.utils.CaseDetailsProvider;
import uk.gov.hmcts.reform.fprl.workflows.ExampleWorkflow;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceTest {

    @Mock
    private ExampleWorkflow exampleWorkflow;

    @Mock
    private FeatureToggleService featureToggleService;

    @InjectMocks
    private ExampleService exampleService;

    @Test
    public void testToGetCoverageExampleFeatureOn() throws WorkflowException {
        runTestWithExampleFeatureToggleSet(true);

        verify(exampleWorkflow).run(any(CaseDetails.class));
        verifyNoMoreInteractions(exampleWorkflow);
    }

    @Test
    public void testToGetCoverageExampleFeatureOff() throws WorkflowException {
        runTestWithExampleFeatureToggleSet(false);

        verifyNoInteractions(exampleWorkflow);
    }

    private void runTestWithExampleFeatureToggleSet(boolean featureToggleFlag) throws WorkflowException {
        when(featureToggleService.isFeatureEnabled(Features.EXAMPLE)).thenReturn(featureToggleFlag);
        CaseDetails caseDetails = CaseDetailsProvider.of(CaseDataProvider.empty());
        when(exampleWorkflow.run(caseDetails)).thenReturn(caseDetails);

        exampleService.executeExampleWorkflow(caseDetails);
    }
}
