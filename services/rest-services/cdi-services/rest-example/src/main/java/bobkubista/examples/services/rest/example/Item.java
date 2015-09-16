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
    public Item(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }
}
