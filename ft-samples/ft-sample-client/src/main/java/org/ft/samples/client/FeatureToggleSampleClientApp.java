package org.ft.samples.client;

import lombok.AllArgsConstructor;
import org.ft.client.annotations.EnableFeatureToggleClient;
import org.ft.client.config.FeatureProperties;
import org.ft.core.services.TenantIdentifierService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Prajwal Das
 */
@SpringBootApplication
@EnableFeatureToggleClient
public class FeatureToggleSampleClientApp
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleSampleClientApp.class, args);
	}
}

@Service
@AllArgsConstructor
class MultiTenantService implements TenantIdentifierService
{
	FeatureProperties featureProperties;

	@Override
	public List<String> getTenantIdentifiers ()
	{
		return Arrays.asList("Cu01", "Cu02");
	}
}