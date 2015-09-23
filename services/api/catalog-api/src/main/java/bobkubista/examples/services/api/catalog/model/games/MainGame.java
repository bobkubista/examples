/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotBlank;

import bobkubista.examples.services.api.catalog.model.Category;
import bobkubista.examples.services.api.catalog.model.Serie;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author Bob
 *
 */
public class MainGame extends AbstractGenericActiveDomainObject<Long> {

    private static final long serialVersionUID = 5328563526284156386L;

    @XmlElement
    private boolean active;

    @NotBlank
    @XmlElement(required = true)
    private Category category;

    @NotBlank
    @XmlElement(required = true)
    private String description;

    @XmlElement
    private String functionalId;

    @NotBlank
    @XmlElement(required = false)
    private Html5Game html5Game;

    private Long id;

    @NotBlank
    @XmlElement(required = true)
    private Date launchDate;

    @NotBlank
    @XmlElement(required = false)
    private PcGame pcGame;
    
    @NotBlank
    @XmlElement(required = false)
    private IosGame iosGame;

    @NotBlank
    @XmlElement(required = false)
    private AndriodGame andriodGame;
    
    @NotBlank
    @XmlElement(required = true)
    private Serie serie;

    @NotBlank
    @XmlElement(required = true)
    private String title;

    @Override
    @XmlElement()
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public String getFunctionalId() {
        return this.functionalId;
    }

    @Override
    public void setFunctionalId(final String functionalId) {
        this.functionalId = functionalId;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(final Date launchDate) {
        this.launchDate = launchDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(final Serie serie) {
        this.serie = serie;
    }

    public PcGame getPcGame() {
        return pcGame;
    }

    public void setPcGame(final PcGame pcGame) {
        this.pcGame = pcGame;
    }

    public Html5Game getHtml5Game() {
        return html5Game;
    }

    public void setHtml5Game(final Html5Game html5Game) {
        this.html5Game = html5Game;
    }

    /**
     * @return the iosGame
     */
    public final IosGame getIosGame() {
        return iosGame;
    }

    /**
     * @param iosGame the iosGame to set
     */
    public final void setIosGame(IosGame iosGame) {
        this.iosGame = iosGame;
    }

    /**
     * @return the andriodGame
     */
    public final AndriodGame getAndriodGame() {
        return andriodGame;
    }

    /**
     * @param andriodGame the andriodGame to set
     */
    public final void setAndriodGame(AndriodGame andriodGame) {
        this.andriodGame = andriodGame;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

}
