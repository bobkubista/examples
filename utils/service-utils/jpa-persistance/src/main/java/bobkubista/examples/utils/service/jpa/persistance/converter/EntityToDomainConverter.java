/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.converter;

import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.service.jpa.persistance.entity.EntityObject;

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
public interface EntityToDomainConverter<DMO extends DomainObject, DMOL extends AbstractGenericDomainObjectCollection<DMO>, EO extends EntityObject> {

    /**
     * Convert a {@link Collection} of {@link EntityObject} to a
     * {@link AbstractGenericDomainObjectCollection}
     *
     * @param entities
     *            the {@link Collection} of {@link EntityObject}s to convert
     * @return a {@link Collection} of {@link DomainObject}s. It will never
     *         return a <code>null</code>
     */
    DMOL convertToDomainObject(Collection<EO> entities);

    /**
     * Convert an {@link EntityObject} to a {@link DomainObject}
     * 
     * @param entity
     *            the {@link EntityObject} to convert
     * @return the converted {@link DomainObject}
     */
    DMO convertToDomainObject(EO entity);

    /**
     * Convert a {@link DomainObject} to an {@link EntityObject}. It will create
     * a new {@link EntityObject}
     *
     * @param domainModelObject
     *            the {@link DomainObject}
     * @return the newly created {@link EntityObject}
     */
    EO convertToEntity(DMO domainModelObject);

    /**
     * Convert a {@link AbstractGenericDomainObjectCollection} to {@link EntityObject}s
     *
     * @param domainObjects
     *            a {@link Collection} of {@link DomainObject}s
     * @return a {@link Collection} of {@link EntityObject}s. It will never
     *         return a <code>null</code>
     */
    Collection<EO> convertToEntity(AbstractGenericDomainObjectCollection<DMO> domainObjects);
}
