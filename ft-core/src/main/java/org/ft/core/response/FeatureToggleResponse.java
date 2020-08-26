package org.ft.core.response;

import org.ft.core.models.FeatureToggle;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajwal Das
 */
@Data
@Builder
public class FeatureToggleResponse implements Serializable
{
    private List<FeatureToggle> featureToggles;

    public FeatureToggleResponse () {

    }

    public FeatureToggleResponse (List<FeatureToggle> featureToggles) {
        this.featureToggles = featureToggles;
    }

}
