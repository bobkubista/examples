/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotBlank;

import bobkubista.examples.services.api.catalog.model.Developer;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;

/**
 * @author Bob
 *
 */
public abstract class AbstractGame extends AbstractGenericIdentifiableDomainObject<Long> {
    private static final long serialVersionUID = -2102816507775475156L;

    @NotBlank
    @XmlElement(required = true)
    private String fileName;
    @NotBlank
    @XmlElement(required = true)
    private Developer gameDeveloper;

    public AbstractGame() {
    }

    public AbstractGame(final String fileName, final Developer gameDeveloper) {
        this.fileName = fileName;
        this.gameDeveloper = gameDeveloper;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    public String getFileName() {
        return this.fileName;
    }

    public Developer getGameDeveloper() {
        return this.gameDeveloper;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setGameDeveloper(final Developer gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }
}