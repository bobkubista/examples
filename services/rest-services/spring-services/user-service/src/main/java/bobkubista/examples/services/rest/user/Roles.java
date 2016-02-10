/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    @ManyToMany(fetch = FetchType.LAZY)
    private final List<Rights> rights = new ArrayList<>();

    /**
     * Check that the {@link Roles} has an active {@link Rights} assigned to it
     * and is active
     *
     * @param name
     *            {@link Rights} to check
     * @return true if authorized
     */
    public static Predicate<Roles> isAuthorized(final String name) {
        final Predicate<Roles> active = Roles::isActive;
        final Predicate<Roles> rights = t -> t.rights.stream()
                .anyMatch(Rights.isAuthorized(name));
        final Predicate<Roles> names = t -> t.name.equals(name);
        return active.and(names)
                .or(rights);
    }

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
