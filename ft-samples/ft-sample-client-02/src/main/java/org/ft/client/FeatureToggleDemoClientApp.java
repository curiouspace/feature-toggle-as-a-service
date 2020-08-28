package org.ft.client;

import org.ft.client.annotations.EnableFeatureToggleClient;
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
public class FeatureToggleDemoClientApp
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleDemoClientApp.class, args);
	}
}

@Service
class MultiTenantService implements TenantIdentifierService
{

	@Override
	public List<String> getTenantIdentifiers ()
	{
		return Arrays.asList("Cu01", "Cu02", "Cu03");
	}
}