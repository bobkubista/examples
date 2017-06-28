/**
 *
 */
package bobkubista.examples.utils.domain.model.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * @author bob
 *
 */
public class ActiveSearchBean extends SearchBean {

	private static final long serialVersionUID = -1495857211032932439L;

	@QueryParam(ApiConstants.ACTIVE)
	@DefaultValue("true")
	private final boolean active = true;

	public boolean isActive() {
		return active;
	}
}
