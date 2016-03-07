/**
 *
 */
package bobkubista.examples.services.rest.todo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import bobkubista.examples.utils.service.jpa.persistance.annotation.SearchField;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author Bob Kubista
 *
 */
@Table(name = "todolist")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@SequenceGenerator(name = "sq_todolist", allocationSize = 1, sequenceName = "sq_todolist", initialValue = 1)
public class TodoListEntity extends AbstractGenericActiveEntity<Long> {

    private static final long serialVersionUID = 9086574784838581996L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_todolist")
    @Column(name = "todolistid")
    @SearchField(fieldName = "id")
    private Long id;

    @OneToMany(mappedBy = "listEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private final List<TodoEntity> items = new ArrayList<>();

    @Basic
    @Column(unique = true, nullable = false)
    @SearchField(fieldName = "functionalId")
    private String todoListName;

    @Override
    public String getFunctionalId() {
        return this.todoListName;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * @return
     */
    public List<TodoEntity> getTodoList() {
        return this.items;
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
