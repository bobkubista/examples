package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class LinkedDomainObject implements DomainObject {

    private static final long serialVersionUID = -3687644119948874776L;

    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    @NotNull
    private List<Link> links = new ArrayList<>();

    public LinkedDomainObject() {
        super();
    }

    /**
     * @return the links
     */
    public final List<Link> getLinks() {
        return this.links;
    }

    /**
     * @param links
     *            the links to set
     */
    public final void setLinks(final List<Link> links) {
        this.links = links;
    }

}
