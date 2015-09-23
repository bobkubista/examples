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
    private boolean active;

    @NotBlank
    @XmlElement(required = true)
    private String functionalId;

    @XmlElement
    private Long id;

    @NotBlank
    @XmlElement(required = true)
    private String name;

    public Category() {
        super();
    }

    public Category(final Long id, final String functionalId, final boolean active, final String name) {
        this.id = id;
        this.functionalId = functionalId;
        this.active = active;
        this.name = name;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public String getFunctionalId() {
        return this.functionalId;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public void setFunctionalId(final String functionalId) {
        this.functionalId = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

}