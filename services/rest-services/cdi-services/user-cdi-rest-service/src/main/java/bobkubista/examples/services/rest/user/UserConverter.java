/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractEntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.converter.EntityToDomainConverter;

/**
 * @author Bob Kubista
 *
 */
public class UserConverter extends AbstractEntityToDomainConverter<User, UserCollection, UserEntity, Long>implements EntityToDomainConverter<User, UserCollection, UserEntity> {

    @Inject
    private UserService service;

    @Override
    protected User doConvertToDomainObject(final UserEntity entity) {
        final User user = new User();

        if (entity != null) {
            user.setActive(entity.isActive());
            user.setEncryptedPassword(entity.getEncryptedPassword());
            user.setFunctionalId(entity.getFunctionalId());
            user.setId(entity.getId());
            user.setName(entity.getName());
        }

        return user;
    }

    @Override
    protected UserEntity doConvertToEntity(final User domainModelObject) {
        final UserEntity entity = new UserEntity();
        this.doConvertToEntity(domainModelObject, entity);
        return entity;
    }

    @Override
    protected void doConvertToEntity(final User domainModelObject, final UserEntity entityObject) {
        Validate.notNull(entityObject);
        Validate.notNull(domainModelObject);

        entityObject.setActive(domainModelObject.isActive());
        entityObject.setEncryptedPassword(domainModelObject.getEncryptedPassword());
        entityObject.setFunctionalId(domainModelObject.getFunctionalId());
        entityObject.setId(domainModelObject.getId());
        entityObject.setName(domainModelObject.getName());
    }

    @Override
    protected UserCollection getNewDomainObjectCollection() {
        return new UserCollection();
    }

    @Override
    protected UserService getService() {
        return this.service;
    }

}
