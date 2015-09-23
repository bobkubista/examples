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

    @NotBlank
    @XmlElement(required = false)
    private AndriodGame andriodGame;

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
    @XmlElement(required = false)
    private IosGame iosGame;

    @NotBlank
    @XmlElement(required = true)
    private Date launchDate;

    @NotBlank
    @XmlElement(required = false)
    private PcGame pcGame;

    @NotBlank
    @XmlElement(required = true)
    private Serie serie;

    @NotBlank
    @XmlElement(required = true)
    private String title;

    public MainGame() {
        super();
    }

    public MainGame(final boolean active, final String seoName) {
        super(active, seoName);
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     * @return the andriodGame
     */
    public final AndriodGame getAndriodGame() {
        return this.andriodGame;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public Html5Game getHtml5Game() {
        return this.html5Game;
    }

    @Override
    @XmlElement()
    public Long getId() {
        return this.id;
    }

    /**
     * @return the iosGame
     */
    public final IosGame getIosGame() {
        return this.iosGame;
    }

    public Date getLaunchDate() {
        return this.launchDate;
    }

    public PcGame getPcGame() {
        return this.pcGame;
    }

    public Serie getSerie() {
        return this.serie;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @param andriodGame
     *            the andriodGame to set
     */
    public final void setAndriodGame(final AndriodGame andriodGame) {
        this.andriodGame = andriodGame;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setHtml5Game(final Html5Game html5Game) {
        this.html5Game = html5Game;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param iosGame
     *            the iosGame to set
     */
    public final void setIosGame(final IosGame iosGame) {
        this.iosGame = iosGame;
    }

    public void setLaunchDate(final Date launchDate) {
        this.launchDate = launchDate;
    }

    public void setPcGame(final PcGame pcGame) {
        this.pcGame = pcGame;
    }

    public void setSerie(final Serie serie) {
        this.serie = serie;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
