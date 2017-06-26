/**
 *
 */
package bobkubista.examples.services.rest.todo;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * @author Bob Kubista
 *
 */
@Entity
@Table(name = "todoitem")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@SequenceGenerator(name = "sq_todoitem", allocationSize = 1, sequenceName = "sq_todoitem", initialValue = 1)
public class TodoEntity extends AbstractIdentifiableEntity {

	private static final long serialVersionUID = 8974577038350152806L;

	@Basic
	@Column(nullable = false)
	private boolean active;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_todoitem")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "todolistid", nullable = false)
	private TodoListEntity listEntity;

	@Column(nullable = false)
	private String value;

	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return
	 */
	public TodoListEntity getListEntity() {
		return this.listEntity;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @return
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * @param active
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param listEntity
	 */
	public void setListEntity(final TodoListEntity listEntity) {
		this.listEntity = listEntity;
	}

	/**
	 * @param value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

}
