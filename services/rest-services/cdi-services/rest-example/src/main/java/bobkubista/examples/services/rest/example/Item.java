package bobkubista.examples.services.rest.example;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bob
 *
 *         Application config
 */

@Entity
@NamedQueries({ @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"), @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i where i.id = :id") })
@XmlRootElement
public class Item implements Serializable {

    private static final long serialVersionUID = -8081979073600140467L;

    @Column(length = 100)
    private String description;

    @Id
    private int id;

    @Column(length = 60)
    private String name;

    @Column
    private int type;

    /**
     * Constructor
     */
    public Item() {
        super();
    }

    /**
     * Constructor
     *
     * @param name
     *            name
     * @param type
     *            type
     */
    public Item(final String name, final int type) {
        this.name = name;
        this.type = type;
    }

    /**
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public int getType() {
        return this.type;
    }

    /**
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param type
     */
    public void setType(final int type) {
        this.type = type;
    }
}
