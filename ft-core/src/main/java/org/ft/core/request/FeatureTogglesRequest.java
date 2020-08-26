package org.ft.core.request;

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
public class FeatureTogglesRequest implements Serializable
{
    private List<FeatureToggle> featureToggles;

    public FeatureTogglesRequest () {

    }

    public FeatureTogglesRequest (List<FeatureToggle> featureToggles) {
        this.featureToggles = featureToggles;
    }

}
