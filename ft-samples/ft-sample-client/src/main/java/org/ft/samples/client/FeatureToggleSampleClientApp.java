package org.ft.samples.client;

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
public class FeatureToggleSampleClientApp
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleSampleClientApp.class, args);
	}
}

@Service
class MultiTenantService implements TenantIdentifierService
{

	@Override
	public List<String> getTenantIdentifiers ()
	{
		return Arrays.asList("Customer 01", "Customer 02");
	}
}