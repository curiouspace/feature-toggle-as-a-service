package org.ft.core.request;

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
public class FeatureTogglesRequest implements Serializable
{
    private List<FeatureInfo> features;
}
