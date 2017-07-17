/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author bkubista
 * @param <ID>
 *            the {@link Serializable} identifier
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractGenericIdentifiableDomainObject implements Serializable {

	private static final long serialVersionUID = 6041983912533900961L;

	/**
	 * Constructor
	 */
	public AbstractGenericIdentifiableDomainObject() {
		super();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (!obj.getClass()
				.isAssignableFrom(this.getClass()) && this.getClass() != obj.getClass()
				&& !this.getClass()
						.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final AbstractGenericIdentifiableDomainObject other = (AbstractGenericIdentifiableDomainObject) obj;
		return new EqualsBuilder().append(this.getId(), other.getId())
				.isEquals();
	}

	/**
	 * @return ID
	 */
	public abstract Long getId();

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
				.toHashCode();
	}

	/**
	 * @param id
	 *            ID
	 */
	public abstract void setId(Long id);

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
