package bobkubista.examples.utils.service.jpa.persistance.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

    /**
     * Default constructor
     */
    public AbstractIdentifiableEntity() {
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass() && !obj.getClass()
                .isAssignableFrom(this.getClass())
                && !this.getClass()
                        .isAssignableFrom(obj.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked")
        final AbstractIdentifiableEntity<ID> other = (AbstractIdentifiableEntity<ID>) obj;
        // TODO maybe refactor this and hashcode to "new
        // EqualsBuilder().reflectionEquals(lhs, rhs, excludeFields)" where the
        // excludeFields is an abstract method
        return new EqualsBuilder().append(this.getId(), other.getId())
                .append(this.getUpdatedDate(), other.getUpdatedDate())
                .isEquals();
    }

    /**
     *
     * @return ID
     */
    public abstract ID getId();

    /**
     *
     * @return the inserted {@link Timestamp}
     */
    public Timestamp getInsertedDate() {
        return this.insertedDate;
    }

    /**
     *
     * @return the updated {@link Timestamp}
     */
    public Timestamp getUpdatedDate() {
        return this.updatedDate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getId())
                .append(this.getUpdatedDate())
                .toHashCode();
    }

    /**
     *
     * @param id
     *            to set
     */
    public abstract void setId(ID id);

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * set the insertedDate. Saved before persist of the first time
     */
    @PrePersist
    protected void setInsertedDate() {
        this.insertedDate = Timestamp.from(Instant.now());
    }

    /**
     * set the insertedDate. Saved before persist of the first time
     */
    @PreUpdate
    protected void setUpdatedDate() {
        this.updatedDate = Timestamp.from(Instant.now());
    }
}
