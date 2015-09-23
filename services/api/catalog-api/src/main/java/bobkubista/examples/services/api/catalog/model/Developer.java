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
 * @author Bob
 *
 */
@XmlRootElement(name = "gamedeveloper")
@XmlAccessorType(XmlAccessType.NONE)
public class Developer extends AbstractGenericActiveDomainObject<Long> {

    private static final long serialVersionUID = 2251532459223464241L;

    @XmlElement
    private String functionalId;

    private Long id;

    @NotBlank
    @XmlElement(required = true)
    private String name;

    /**
     * Default constructor
     */
    public Developer() {
        super();
    }

    /**
     * Constructor
     *
     * @param id
     *            identifier
     * @param functionalId
     *            seo-unique name
     * @param active
     *            active
     * @param name
     *            name
     */
    public Developer(final Long id, final String functionalId, final boolean active, final String name) {
        super(active);
        this.id = id;
        this.functionalId = functionalId;
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
    @XmlElement()
    public Long getId() {
        return this.id;
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

    @Override
    public void setFunctionalId(final String functionalId) {
        this.functionalId = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }
}
