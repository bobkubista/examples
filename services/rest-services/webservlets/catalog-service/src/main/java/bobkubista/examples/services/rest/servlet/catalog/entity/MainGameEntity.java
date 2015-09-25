/**
 *
 */
package bobkubista.examples.services.rest.servlet.catalog.entity;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author Bob
 *
 */
@Table(name = "maingame")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@SequenceGenerator(name = "sq_maingame", allocationSize = 1, sequenceName = "sq_maingame", initialValue = 1)
public class MainGameEntity extends AbstractGenericActiveEntity<Long> {

    private static final long serialVersionUID = 2926939627884915942L;
    @Basic
    @Column(nullable = false)
    private boolean active;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_maingame")
    @Column(name = "todolistid")
    private Long id;
    @Basic
    @Column(nullable = false)
    private String seoName;

    @Override
    public String getFunctionalId() {
        return this.seoName;
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
        this.seoName = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

}
