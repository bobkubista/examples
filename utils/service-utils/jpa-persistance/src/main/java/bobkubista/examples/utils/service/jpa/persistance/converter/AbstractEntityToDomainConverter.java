/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.core.Link;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.entity.EntityObject;
import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * @author bkubista
 *
 * @param <DTO>
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 * @param <EO>
 *            {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            Identifier
 */
public abstract class AbstractEntityToDomainConverter<DTO extends AbstractGenericIdentifiableDomainObject, COL extends AbstractGenericDomainObjectCollection<DTO>, EO extends AbstractIdentifiableEntity>
		implements EntityToDomainConverter<DTO, COL, EO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityToDomainConverter.class);

	@Override
	public COL convertToDomainObject(final Collection<EO> entities, final Long amount, final List<Link> links) {
		final COL result = this.getNewDomainObjectCollection();
		result.setAmount(amount);
		result.getLinks()
				.addAll(links);
		LOGGER.debug("Converting entities to domain");
		if (entities != null) {
			result.getDomainCollection()
					.addAll(entities.stream()
							.map(v -> this.convertToDomainObject(v))
							.collect(Collectors.toList()));
		}
		return result;
	}

	@Override
	public DTO convertToDomainObject(final EO entity) {
		LOGGER.debug("Converting entity to domain with id {}", entity.getId());
		return this.doConvertToDomainObject(entity);
	}

	@Override
	public Collection<EO> convertToEntity(final AbstractGenericDomainObjectCollection<DTO> domainObjects) {
		LOGGER.debug("Converting domain to entities");
		if (domainObjects == null) {
			return new LinkedList<>();
		}
		return domainObjects.getDomainCollection()
				.stream()
				.map(v -> this.convertToEntity(v))
				.collect(Collectors.toCollection(LinkedList<EO>::new));
	}

	@Override
	public EO convertToEntity(final DTO domainModelObject) {
		final EO entity;
		if (domainModelObject == null) {
			entity = null;
		} else {
			LOGGER.debug("Converting domain to entity with id {}", domainModelObject.getId());
			final Optional<EO> oldEntity;
			if (domainModelObject.getId() != null && this.getService().contains(domainModelObject.getId())) {
				oldEntity = this.getService()
						.getById(domainModelObject.getId());
				entity = oldEntity.get();
				this.doConvertToEntity(domainModelObject, entity);
			} else {
				entity = this.doConvertToEntity(domainModelObject);
			}
		}
		return entity;
	}

	/**
	 * Convert an {@link EntityObject} to a {@link DomainObject}
	 *
	 * @param entity
	 *            the {@link EntityObject} to convert
	 * @return the converted {@link DomainObject}
	 */
	protected abstract DTO doConvertToDomainObject(final EO entity);

	/**
	 * Convert a {@link DomainObject} to and {@link EntityObject}
	 *
	 * @param domainModelObject
	 *            the {@link DomainObject}
	 * @return an {@link AbstractIdentifiableEntity}
	 */
	protected abstract EO doConvertToEntity(final DTO domainModelObject);

	/**
	 * Convert a {@link DomainObject} to an {@link EntityObject}
	 *
	 * @param domainModelObject
	 *            the {@link DomainObject} to convert
	 * @param entityObject
	 *            the {@link EntityObject} to convert to
	 */
	protected abstract void doConvertToEntity(final DTO domainModelObject, final EO entityObject);

	/**
	 *
	 * @return {@link AbstractGenericDomainObjectCollection} <code>DMO</code>
	 */
	protected abstract COL getNewDomainObjectCollection();

	/**
	 *
	 * @return {@link IdentifiableEntityService}
	 */
	protected abstract IdentifiableEntityService<EO> getService();

}
