package bobkubista.examples.utils.service.jpa.persistance.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An {@link Object} with primary key the id of type <code>ID</code>
 *
 * @author bkubista
 *
 * @param <ID>
 *            {@link Serializable}
 */
@MappedSuperclass
public abstract class AbstractIdentifiableEntity<ID extends Serializable> implements EntityObject {

    private static final long serialVersionUID = 4957722166359705216L;

    @Column(name = "insertedDate", nullable = false)
    private Timestamp insertedDate;

    @Version
    @Column(name = "updatedDate", nullable = false)
    private Timestamp updatedDate;

    public AbstractIdentifiableEntity() {
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass() && !obj.getClass().isAssignableFrom(this.getClass()) && !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked")
        final AbstractIdentifiableEntity<ID> other = (AbstractIdentifiableEntity<ID>) obj;
        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
    }

    public abstract ID getId();

    public Timestamp getInsertedDate() {
        return this.insertedDate;
    }

    public Timestamp getUpdatedDate() {
        return this.updatedDate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getId()).toHashCode();
    }

    public abstract void setId(ID id);

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }

    @PrePersist
    protected void setInsertedDate() {
        this.insertedDate = new Timestamp(System.currentTimeMillis());
    }
}
