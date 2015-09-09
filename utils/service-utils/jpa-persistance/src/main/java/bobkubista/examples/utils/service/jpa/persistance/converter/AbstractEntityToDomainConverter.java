/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.converter;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.IdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.IdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * @author bkubista @param <DMO> {@link IdentifiableDomainObject} @param <DMOL>
 * {@link DomainObjectCollection} @param <EO> {@link IdentifiableEntity} @param
 * <ID> Identifier
 */
public abstract class AbstractEntityToDomainConverter<DMO extends IdentifiableDomainObject<ID>, DMOL extends DomainObjectCollection<DMO>, EO extends IdentifiableEntity<ID>, ID extends Serializable>
		implements EntityToDomainConverter<DMO, DMOL, EO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityToDomainConverter.class);

	@Override
	public DMOL convertToDomainObject(final Collection<EO> entities) {
		final DMOL result = this.getNewDomainObjectCollection();
		LOGGER.debug("Converting entities to domain");
		if (entities != null) {
			entities.stream().forEach(v -> result.getDomainCollection().add(this.convertToDomainObject(v)));
		}
		return result;
	}

	@Override
	public DMO convertToDomainObject(final EO entity) {
		LOGGER.debug("Converting entity to domain with id {}", entity.getId());
		return this.doConvertToDomainObject(entity);
	}

	@Override
	public EO convertToEntity(final DMO domainModelObject) {
		final EO entity;
		if (domainModelObject == null) {
			entity = null;
		} else {
			LOGGER.debug("Converting domain to entity with id {}", domainModelObject.getId());
			final EO oldEntity;
			if (domainModelObject.getId() != null && (oldEntity = this.getService().getById(domainModelObject.getId())) != null) {
				entity = oldEntity;
				this.doConvertToEntity(domainModelObject, entity);
			} else {
				entity = this.doConvertToEntity(domainModelObject);
			}
		}
		return entity;
	}

	@Override
	public Collection<EO> convertToEntity(final DomainObjectCollection<DMO> domainObjects) {
		final Collection<EO> result = new LinkedList<EO>();
		LOGGER.debug("Converting domain to entities");
		if (domainObjects != null) {
			domainObjects.getDomainCollection().stream().forEach(v -> result.add(this.convertToEntity(v)));
		}
		return result;
	}

	/**
	 * Convert an {@link EntityObject} to a {@link DomainObject}
	 *
	 * @param entity the {@link EntityObject} to convert @return the converted
	 * {@link DomainObject}
	 */
	protected abstract DMO doConvertToDomainObject(final EO entity);

	/**
	 * Convert a {@link DomainObject} to and {@link EntityObject}
	 *
	 * @param domainModelObject the {@link DomainObject} @return an {@link
	 * IdentifiableEntity}
	 */
	protected abstract EO doConvertToEntity(final DMO domainModelObject);

	/**
	 * Convert a {@link DomainObject} to an {@link EntityObject}
	 *
	 * @param domainModelObject the {@link DomainObject} to convert @param
	 * entityObject the {@link EntityObject} to convert to
	 */
	protected abstract void doConvertToEntity(final DMO domainModelObject, final EO entityObject);

	/**
	 *
	 * @return {@link DomainObjectCollection} <code>DMO</code>
	 */
	protected abstract DMOL getNewDomainObjectCollection();

	protected abstract IdentifiableEntityService<EO, ID> getService();

}
