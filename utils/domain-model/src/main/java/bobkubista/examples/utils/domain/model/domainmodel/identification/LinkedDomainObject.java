package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.List;

import javax.ws.rs.core.Link;

public interface LinkedDomainObject extends DomainObject {

    /**
     * @return the links
     */
    public List<Link> getLinks();

}
