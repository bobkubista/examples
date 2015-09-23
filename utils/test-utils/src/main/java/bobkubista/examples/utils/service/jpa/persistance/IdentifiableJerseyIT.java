/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;

/**
 *
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public interface IdentifiableJerseyIT<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>> {

    static final int COLLECTION_TYPE_ARGUMENT_NUMBER = 2;

    /**
     * Test if create works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public default void shouldCreate() {
        final TYPE domainObject = this.create();
        final Response response = this.getTarget("/").request().post(Entity.xml(domainObject));
        Assert.assertNotNull(response);
        Assert.assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
        Assert.assertTrue(StringUtils.isNotBlank(response.getLocation().getPath()));
    }

    /**
     * Test if delete works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public default void shouldDelete() {
        this.getTarget("/-1").request().delete();
    }

    /**
     * Test if getAll works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public default void shouldGetAll() {
        final COL response = this.getTarget("/").request().get(this.getCollectionClass());
        this.checkResponseGetAll(response, 1);
    }

    /**
     * Test if getById works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public default void shouldGetById() {
        final TYPE response = this.getTarget("/" + this.getId()).request().get(this.getSingleClass());
        this.checkSingle(response);
    }

    /**
     * Test if update works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public default void shouldUpdate() {
        TYPE response = this.getTarget("/1").request().get(this.getSingleClass());
        response = this.update(response);
        this.getTarget("/").request().put(Entity.xml(response));
    }

    /**
     *
     * @param response
     *            response collection to check
     * @param size
     *            the amount in the collection
     */
    void checkResponseGetAll(COL response, int size);

    /**
     * check a single instance
     *
     * @param response
     *            the type to check
     */
    void checkSingle(TYPE response);

    /**
     * check updated
     *
     * @param response
     *            the type to check
     */
    void checkUpdated(TYPE response);

    /**
     *
     * @return create update object
     */
    TYPE create();

    /**
     *
     * @return COL
     */
    @SuppressWarnings("unchecked")
    default Class<COL> getCollectionClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<COL>) genericSuperclass.getActualTypeArguments()[COLLECTION_TYPE_ARGUMENT_NUMBER];
    }

    /**
     *
     * @return identifier
     */
    ID getId();

    /**
     *
     * @return the single glass
     */
    @SuppressWarnings("unchecked")
    default Class<TYPE> getSingleClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Get the jerseyTest {@link WebTarget}
     *
     * @param string
     *            path
     * @return {@link WebTarget}
     */
    WebTarget getTarget(String string);

    /**
     * Update
     *
     * @param response
     *            object to update
     * @return updated object
     */
    TYPE update(TYPE response);
}
