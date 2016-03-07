/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author Bob
 *
 */
@Entity
@SequenceGenerator(name = "sq_rights", allocationSize = 1, sequenceName = "sq_rights", initialValue = 1)
public class Rights extends AbstractGenericActiveEntity<Long> {

    private static final long serialVersionUID = 2359290515125351928L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_rights")
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Check that the right is active and equal to the asked right
     *
     * @param right
     *            {@link Rights} to check against
     * @return true if equal and active
     */
    public static Predicate<Rights> isAuthorized(final String right) {
        final Predicate<Rights> name = t -> t.getFunctionalId()
                .equals(right);
        final Predicate<Rights> active = t -> t.isActive();
        return name.and(active);
    }

    @Override
    public String getFunctionalId() {
        return this.name;
    }

    @Override
    public Long getId() {
        return this.id;
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
