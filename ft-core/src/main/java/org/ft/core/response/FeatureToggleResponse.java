package org.ft.core.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.FeatureInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureToggleResponse implements Serializable
{
    private List<FeatureInfo> features;
}
