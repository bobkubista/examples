/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.converter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Link;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

/**
 * Interface that describes a converter used to convert to and from
 * {@link DomainObject} and {@link EntityObject}
 *
 * @param <DMO>
 *            The type of {@link DomainObject}
 * @param <EO>
 *            The type of {@link EntityObject}
 * @param <DMOL>
 *            {@link AbstractGenericDomainObjectCollection}
 * @author bkubista
 *
 */
public interface EntityToDomainConverter<DMO extends Serializable, DMOL extends AbstractGenericDomainObjectCollection<DMO>, EO extends Serializable> {

	AbstractGenericDomainObjectCollection<Long> convertIdToDomainObject(Collection<Long> collection, Long amount,
			List<Link> links);

	/**
	 * Convert a {@link Collection} of {@link EntityObject} to a
	 * {@link AbstractGenericDomainObjectCollection}
	 *
	 * @param entities
	 *            the {@link Collection} of {@link EntityObject}s to convert
	 * @param amount
	 *            amount of possible results
	 * @param links
	 * @return a {@link Collection} of {@link DomainObject}s. It will never
	 *         return a <code>null</code>
	 */
	DMOL convertToDomainObject(Collection<EO> entities, Long amount, List<Link> links);

	/**
	 * Convert an {@link EntityObject} to a {@link DomainObject}
	 *
	 * @param entity
	 *            the {@link EntityObject} to convert
	 * @return the converted {@link DomainObject}
	 */
	DMO convertToDomainObject(EO entity);

	/**
	 * Convert a {@link AbstractGenericDomainObjectCollection} to
	 * {@link EntityObject}s
	 *
	 * @param domainObjects
	 *            a {@link Collection} of {@link DomainObject}s
	 * @return a {@link Collection} of {@link EntityObject}s. It will never
	 *         return a <code>null</code>
	 */
	Collection<EO> convertToEntity(AbstractGenericDomainObjectCollection<DMO> domainObjects);

	/**
	 * Convert a {@link DomainObject} to an {@link EntityObject}. It will create
	 * a new {@link EntityObject}
	 *
	 * @param domainModelObject
	 *            the {@link DomainObject}
	 * @return the newly created {@link EntityObject}
	 */
	EO convertToEntity(DMO domainModelObject);
}
