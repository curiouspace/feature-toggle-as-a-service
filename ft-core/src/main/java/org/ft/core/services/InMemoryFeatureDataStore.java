package org.ft.core.services;

import org.ft.core.api.model.FeatureInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
public class InMemoryFeatureDataStore implements FeatureDataStore
{

    @Override
    public void enable (String featureId, String tenant)
    {
        throw new NotImplementedException();
    }

    @Override
    public void disable (String featureId, String tenant)
    {
        throw new NotImplementedException();
    }

    @Override
    public Optional<FeatureInfo> getFeature (String featureId, String tenant)
    {
        throw new NotImplementedException();
    }

    @Override
    public List<FeatureInfo> getFeatures (String tenant)
    {
        throw new NotImplementedException();
    }

    @Override
    public Optional<FeatureInfo> createOrUpdate (FeatureInfo feature)
    {
         throw new NotImplementedException();
    }


    @Override
    public List<FeatureInfo> createOrUpdate (List<FeatureInfo> features)
    {
        throw new NotImplementedException();
    }

    @Override
    public Optional<FeatureInfo> update (FeatureInfo feature)
    {
        throw new NotImplementedException();
    }

    @Override
    public void delete (String featureId)
    {
        throw new NotImplementedException();
    }

    @Override
    public List<String> getAllTenantsIdentifiers ()
    {
        throw new NotImplementedException();
    }
}
