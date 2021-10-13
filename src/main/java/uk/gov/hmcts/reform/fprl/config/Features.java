package uk.gov.hmcts.reform.fprl.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This enum stores all actively used feature toggle flags.
 * Please see `feature-toggle` in application.yaml to see all feature flags. Env vars can be set in cnp_flux_config repo.
 */
@RequiredArgsConstructor
@Getter
public enum Features {

    /**
     * This is just an example. It should be removed when you have a real flag added.
     */
    EXAMPLE("example");

    private final String name;

}
