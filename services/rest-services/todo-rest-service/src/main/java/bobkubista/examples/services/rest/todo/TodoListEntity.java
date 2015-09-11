/**
 *
 */
package bobkubista.examples.services.rest.todo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import bobkubista.examples.utils.service.jpa.persistance.entity.ActiveEntity;

/**
 * @author Bob Kubista
 *
 */
@Table(name = "todolist")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@SequenceGenerator(name = "sq_todolist", allocationSize = 1, sequenceName = "sq_todolist", initialValue = 1)
public class TodoListEntity extends ActiveEntity<Long> {

	private static final long serialVersionUID = 9086574784838581996L;

	@Basic
	@Column(nullable = false)
	private boolean active;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_todolist")
	@Column(name = "todolistid")
	private Long id;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
	// orphanRemoval = true)
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "todoitem")
	private List<TodoEntity> todoItems = new ArrayList<>();

	@Basic
	@Column(unique = true, nullable = false)
	private String todoListName;

	@Override
	public String getFunctionalId() {
		return this.todoListName;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public List<TodoEntity> getTodoList() {
		return this.todoItems;
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
		this.todoListName = functionalId;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

}
