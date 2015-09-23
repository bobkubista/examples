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
    private String fileName;
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
     */
    public AbstractGame(final String fileName, final Developer gameDeveloper, final boolean active) {
        super(active);
        this.fileName = fileName;
        this.gameDeveloper = gameDeveloper;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public String getFunctionalId() {
        return this.fileName;
    }

    /**
     *
     * @return
     */
    public Developer getGameDeveloper() {
        return this.gameDeveloper;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void setFunctionalId(final String functionalId) {
        this.fileName = functionalId;
    }

    /**
     *
     * @param gameDeveloper
     */
    public void setGameDeveloper(final Developer gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }
}
