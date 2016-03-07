/**
 *
 */
package bobkubista.examples.services.rest.user;

import org.junit.Assert;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractTestEntityToDomainConverter;

/**
 * @author Bob
 *
 */
public class UserConverterTest extends AbstractTestEntityToDomainConverter<User, UserCollection, UserEntity, Long> {

    private final UserConverter converter = new UserConverter();

    @Override
    protected void assertDomainObject(User domainModelObject, UserEntity entity) {
        Assert.assertNotNull(domainModelObject);
        Assert.assertEquals(entity.getId(), domainModelObject.getId());
        Assert.assertEquals(entity.isActive(), domainModelObject.isActive());
        Assert.assertEquals(entity.getEncryptedPassword(), domainModelObject.getEncryptedPassword());
        Assert.assertEquals(entity.getFunctionalId(), domainModelObject.getFunctionalId());
        Assert.assertEquals(entity.getName(), domainModelObject.getName());
    }

    @Override
    protected void assertEntity(User domainModelObject, UserEntity entity) {
        Assert.assertNotNull(entity);
        Assert.assertEquals(domainModelObject.getId(), entity.getId());
        Assert.assertEquals(domainModelObject.isActive(), entity.isActive());
        Assert.assertEquals(domainModelObject.getEncryptedPassword(), entity.getEncryptedPassword());
        Assert.assertEquals(domainModelObject.getFunctionalId(), entity.getFunctionalId());
        Assert.assertEquals(domainModelObject.getName(), entity.getName());
    }

    @Override
    protected UserConverter getConverter() {
        return this.converter;
    }

    @Override
    protected User getEmptyDomainObject() {
        return new User();
    }

    @Override
    protected User getValidDomainObject() {
        final User user = new User();

        user.setActive(true);
        user.setEncryptedPassword("1234");
        user.setFunctionalId("bla@foo.bar");
        user.setId(1L);
        user.setName("Foo Bar");

        return user;
    }

    @Override
    protected UserEntity getValidEntity() {
        final UserEntity user = new UserEntity();

        user.setActive(true);
        user.setEncryptedPassword("1234");
        user.setFunctionalId("bla@foo.bar");
        user.setId(1L);
        user.setName("Foo Bar");

        return user;
    }

}
