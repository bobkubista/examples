package bobkubista.examples.services.rest.cdi.email.strategy;

import bobkubista.examples.services.api.email.model.EmailContext;

/**
 * {@link EmailStrategy} for general emails
 *
 * @author bkubista
 *
 */
public class GeneralEmailStrategy extends AbstractEmailStrategy {

	private final EmailContext email;

	/**
	 * Constructor
	 *
	 * @param email {@link Email}
	 */
	public GeneralEmailStrategy(final EmailContext email) {
		this.email = email;
		final String templateFile = "template/generalTemplate.tmpl";

		this.composeEmail(this.email, templateFile);
	}

	@Override
	public String toString() {
		return "GeneralEmailStrategy";
	}

	@Override
	EmailContext getEmail() {
		return this.email;
	}

}
