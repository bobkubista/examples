package bobkubista.examples.utils.service.jpa.persistance.metrics.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

public class DatabaseHealthCheck extends HealthCheck {

	@SuppressWarnings("rawtypes")
	private IdentifiableEntityService service;

	public DatabaseHealthCheck(@SuppressWarnings("rawtypes") final IdentifiableEntityService identifiableEntityService) {
		this.service = identifiableEntityService;
	}

	@Override
	protected Result check() throws Exception {
		if (service.isDatabaseConnected()) {
			return HealthCheck.Result.healthy();
		} else {
			return HealthCheck.Result.unhealthy("Cannot connect to database");
		}
	}

}
