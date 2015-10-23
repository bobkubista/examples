/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author Bob
 *
 */
@Entity
@SequenceGenerator(name = "sq_role", allocationSize = 1, sequenceName = "sq_role", initialValue = 1)
public class Roles extends AbstractGenericActiveEntity<Long> {

    private static final long serialVersionUID = -7212732541628102691L;

    @Column(nullable = false)
    private boolean active;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_role")
    @Column(nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Rights> rights = new ArrayList<>();

    @Override
    public String getFunctionalId() {
        return this.name;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * @return the rights
     */
    public List<Rights> getRights() {
        return this.rights;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Check that the {@link Roles} has an active {@link Rights} assigned to it
     * and is active
     *
     * @param right
     *            {@link Rights} to check
     * @return true if authorized
     */
    public boolean isAuthorized(final Rights right) {
        return this.isActive() && this.rights.stream().anyMatch(t -> t.isAuthorized(right));
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
