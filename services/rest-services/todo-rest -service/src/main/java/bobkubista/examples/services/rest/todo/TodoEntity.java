/**
 *
 */
package bobkubista.examples.services.rest.todo;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import bobkubista.examples.utils.service.jpa.persistance.entity.EntityObject;

/**
 * @author Bob Kubista
 *
 */
@Table(name = "todoitem")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class TodoEntity implements EntityObject {

	private static final long serialVersionUID = 8974577038350152806L;

	@Basic
	@Column(nullable = false)
	private boolean active;
	@Column(nullable = false)
	private String value;

	public String getValue() {
		return this.value;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
