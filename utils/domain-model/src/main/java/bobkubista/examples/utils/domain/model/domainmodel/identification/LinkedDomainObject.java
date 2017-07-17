package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Link;

/**
 * A {@link DomainObject} that contains HATEOAS links
 *
 * @author Bob
 *
 */
public interface LinkedDomainObject extends Serializable {

	/**
	 * @return the links
	 */
	public List<Link> getLinks();

}
