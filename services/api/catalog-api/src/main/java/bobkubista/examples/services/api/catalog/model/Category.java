/**
 *
 */
package bobkubista.examples.services.api.catalog.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 *
 * @author Bob
 *
 */
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.NONE)
public class Category extends AbstractGenericActiveDomainObject<Long> {

    private static final long serialVersionUID = 3272535936657151315L;

    @NotBlank
    @XmlElement(required = true)
    private String name;

    /**
     * Default contructor
     */
    public Category() {
        super();
    }

    /**
     * Constructor
     *
     * @param id
     *            identifier
     * @param functionalId
     *            unique seo name
     * @param active
     *            isActive
     * @param name
     *            name
     */
    public Category(final Long id, final String functionalId, final boolean active, final String name) {
        super(active, functionalId, id);
        this.name = name;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

}
