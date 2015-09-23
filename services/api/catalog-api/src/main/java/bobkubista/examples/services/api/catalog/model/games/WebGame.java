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

    private Long id;

    /**
     * Default constructor
     */
    public WebGame() {
        super();
    }

    /**
     * Constructor
     * 
     * @param id
     *            identifier
     * @param active
     *            active flag
     * @param url
     *            url
     * @param gameDeveloper
     *            {@link Developer}
     */
    public WebGame(final Long id, final boolean active, final String url, final Developer gameDeveloper) {
        super(url, gameDeveloper, active);
        this.id = id;
    }

    @Override
    @XmlElement()
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
