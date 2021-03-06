package bobkubista.examples.services.rest.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 *
 * @author Bob
 *
 */
@Entity
@SequenceGenerator(name = "sq_user", allocationSize = 1, sequenceName = "sq_user", initialValue = 1)
public class UserEntity extends AbstractGenericActiveEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, unique = true)
    private String email;
    private String encryptedPassword;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    @Column(nullable = false)
    private Long id;
    @Column
    private String name;

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

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
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
