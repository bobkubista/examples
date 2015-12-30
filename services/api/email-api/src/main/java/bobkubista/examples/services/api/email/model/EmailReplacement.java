/**
 *
 */
package bobkubista.examples.services.api.email.model;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Bob
 *
 */
public class EmailReplacement extends Pair<String, String> {

	private static final String EMAIL = "email";

	private static final long serialVersionUID = -374936343510336318L;
	private String value;

	/**
	 * Constructor
	 *
	 * @param recipient {@link EmailAddress}
	 */
	public EmailReplacement(final String recipient) {
		this.value = recipient;
	}

	@Override
	public String getLeft() {
		return EMAIL;
	}

	@Override
	public String getRight() {
		return this.value;
	}

	@Override
	public String setValue(final String value) {
		this.value = value;
		return value;
	}

}
