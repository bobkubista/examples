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

import bobkubista.examples.utils.service.jpa.persistance.annotation.SearchField;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 *
 * @author Bob
 *
 */
@Entity
@SequenceGenerator(name = "sq_user", allocationSize = 1, sequenceName = "sq_user", initialValue = 1)
public class UserEntity extends AbstractGenericActiveEntity<Long> {

    private static final long serialVersionUID = 3230156455762101429L;

    @Column(nullable = false, unique = true)
    @SearchField(fieldName = "functionalId")
    private String email;

    private String encryptedPassword;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    @Column(nullable = false)
    @SearchField(fieldName = "id")
    private Long id;

    @Column
    @SearchField(fieldName = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private final List<Roles> roles = new ArrayList<>();

    /**
     * Is the current user authorized The user, role and right should be active
     *
     * @param right
     *            functional name of right or role
     * @return true is autorized
     */
    public static Predicate<UserEntity> isAuthorized(final String right) {
        final Predicate<UserEntity> active = UserEntity::isActive;
        final Predicate<UserEntity> roles = t -> t.roles.stream()
                .anyMatch(Roles.isAuthorized(right));
        return active.and(roles);
    }

    /**
     * @return
     */
    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    @Override
    public String getFunctionalId() {
        return this.email;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the roles
     */
    public List<Roles> getRoles() {
        return this.roles;
    }

    /**
     * @param encryptedPassword
     */
    public void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public void setFunctionalId(final String functionalId) {
        this.email = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }
}
