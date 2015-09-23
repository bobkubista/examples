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
@XmlRootElement(name = "serie")
@XmlAccessorType(XmlAccessType.NONE)
public class Serie extends AbstractGenericActiveDomainObject<Long> {

    private static final long serialVersionUID = -5566287246537687934L;

    private Long id;

    @NotBlank
    @XmlElement(required = true)
    private String serieName;

    /**
     * Default constructor
     */
    public Serie() {
        super();
    }

    /**
     * Constructor
     *
     * @param id
     *            identifier
     * @param functionalId
     *            unique seo friendly name
     * @param active
     *            active flag
     * @param name
     *            name
     */
    public Serie(final Long id, final String functionalId, final boolean active, final String name) {
        super(active, functionalId);
        this.id = id;
        this.serieName = name;
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
        return this.serieName;
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
        this.serieName = name;
    }
}
