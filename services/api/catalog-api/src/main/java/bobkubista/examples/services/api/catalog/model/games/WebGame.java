/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import bobkubista.examples.services.api.catalog.model.Developer;

/**
 * @author Bob
 *
 */
@XmlRootElement(name = "webgame")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class WebGame extends AbstractGame {

    private static final long serialVersionUID = -1896666729789454367L;

    @NotBlank
    @XmlElement(required = true)
    private boolean active;

    private Long id;

    public WebGame() {
        super();
    }

    public WebGame(final Long id, final boolean active, final String url, final Developer gameDeveloper) {
        super(url, gameDeveloper);
        this.id = id;
        this.active = active;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    @XmlElement()
    public Long getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
