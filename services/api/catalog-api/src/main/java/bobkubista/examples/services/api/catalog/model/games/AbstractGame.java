/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotBlank;

import bobkubista.examples.services.api.catalog.model.Developer;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author Bob
 *
 */
public abstract class AbstractGame extends AbstractGenericActiveDomainObject<Long> {
    private static final long serialVersionUID = -2102816507775475156L;

    @NotBlank
    @XmlElement(required = true)
    private Developer gameDeveloper;

    /**
     * Default constructor
     */
    public AbstractGame() {
    }

    /**
     * Constructor
     *
     * @param fileName
     *            filename of url
     * @param gameDeveloper
     *            {@link Developer}
     * @param active
     *            active flag
     */
    public AbstractGame(final String fileName, final Developer gameDeveloper, final boolean active) {
        super(active, fileName);
        this.gameDeveloper = gameDeveloper;
    }


    /**
     *
     * @return
     */
    public Developer getGameDeveloper() {
        return this.gameDeveloper;
    }


    /**
     *
     * @param gameDeveloper
     */
    public void setGameDeveloper(final Developer gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }
}
