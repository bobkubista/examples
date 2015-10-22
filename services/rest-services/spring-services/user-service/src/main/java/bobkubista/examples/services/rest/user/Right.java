/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author Bob
 *
 */
@Entity
public class Right extends AbstractGenericActiveEntity<Long> {
    private static final long serialVersionUID = 2359290515125351928L;

    @Column(nullable = false)
    private boolean active;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public String getFunctionalId() {
        return this.name;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public void setFunctionalId(final String functionalId) {
        this.name = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
