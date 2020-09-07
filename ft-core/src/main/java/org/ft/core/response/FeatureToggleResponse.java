package org.ft.core.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.FeatureInfo;

import java.util.List;

/**
 * @author Prajwal Das
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureToggleResponse extends Response
{
    private List<FeatureInfo> features;


    @Builder
    public FeatureToggleResponse (String errorMsg,
                                  int statusCode,
                                  List<FeatureInfo> features)
    {
        super(errorMsg, statusCode);
        this.features = features;
    }
}
