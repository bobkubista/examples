package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.Instant;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import bobkubista.examples.utils.domain.model.RestConstants;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheFilterFactory;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.ConstraintViolationEntity;

/**
 *
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <ID>
 *            identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
// TODO add test for contains, order by for id's
public abstract class AbstractIdentifiableJerseyIT<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractBaseSpringJerseyDbUnitTest {

    private static final int COLLECTION_TYPE_ARGUMENT_NUMBER = 2;

    private static final String NEXT_LINK = "next";

    private static final String PREVIOUS_LINK = "previous";

    /**
     * Test if create works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void shouldCreate() {
        final TYPE domainObject = this.create();
        final Response response = this.target("/")
                .request()
                .post(Entity.xml(domainObject));
        try {
            Assert.assertNotNull(response);
            Assert.assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
            Assert.assertTrue(StringUtils.isNotBlank(response.getLocation()
                    .getPath()));
        } finally {
            response.close();
        }
    }

    /**
     * Test if delete works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void shouldDelete() {
        final Response response = this.target("/1")
                .request()
                .delete();
        try {
            Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
        } finally {
            response.close();
        }
    }

    /**
     * Test if getAll works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAll() {
        final Response response = this.target("/")
                .request()
                .get();

        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("private, max-age=10", response.getHeaderString(HttpHeaders.CACHE_CONTROL));

        final COL collection = response.readEntity(this.getCollectionClass());
        this.checkResponseGetAll(collection, this.expectedSize());
    }

    /**
     * Test if getAll with query params works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAllWithMaxLimit() {
        final Response response = this.target("/")
                .queryParam(ApiConstants.PAGE, 0)
                .queryParam(ApiConstants.MAX, 200)
                .request()
                .get();
        Assert.assertEquals(RestConstants.UNPROCESSABLE_ENTITY, response.getStatus());
        final ConstraintViolationEntity violation = response.readEntity(ConstraintViolationEntity.class);
        Assert.assertNotNull(violation);
        Assert.assertEquals("must be less than or equal to 100", violation.getErrorMsg());
    }

    /**
     * Test if getAll with query params works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAllWithReverseSortAndLimit() {
        final COL response = this.target("/")
                .queryParam(ApiConstants.SORT, "-" + this.getIdField())
                .queryParam(ApiConstants.PAGE, 0)
                .queryParam(ApiConstants.MAX, 2)
                .request()
                .get(this.getCollectionClass());
        this.checkResponseGetAll(response, this.expectedSize());
        this.checkSorting(response, true);
    }

    /**
     * Test if getAll with query params works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAllWithSortAndLimit() {
        final COL response = this.target("/")
                .queryParam(ApiConstants.SORT, this.getIdField())
                .queryParam(ApiConstants.PAGE, 0)
                .queryParam(ApiConstants.MAX, 2)
                .request()
                .get(this.getCollectionClass());
        Assert.assertNotNull(response.getLinks());
        Assert.assertTrue(!(response.getDomainCollection()
                .size() == 2)
                || response.getLinks()
                        .stream()
                        .anyMatch(link -> NEXT_LINK.equals(link.getRel())));
        Assert.assertFalse(response.getLinks()
                .stream()
                .anyMatch(link -> PREVIOUS_LINK.equals(link.getRel())));

        this.checkResponseGetAll(response, this.expectedSize());
        this.checkSorting(response, false);
    }

    /**
     * Test if getAll with query params works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAllWithSortAndLimitPreviousLink() {
        final COL response = this.target("/")
                .queryParam(ApiConstants.SORT, this.getIdField())
                .queryParam(ApiConstants.PAGE, 1)
                .queryParam(ApiConstants.MAX, 100)
                .request()
                .get(this.getCollectionClass());
        Assert.assertNotNull(response.getLinks());
        Assert.assertFalse(response.getLinks()
                .stream()
                .anyMatch(link -> NEXT_LINK.equals(link.getRel())));
        Assert.assertTrue(response.getLinks()
                .stream()
                .anyMatch(link -> PREVIOUS_LINK.equals(link.getRel())));
    }

    /**
     * Test if getById works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetById() {
        final Response response = this.target("/" + this.getId())
                .request()
                .get(Response.class);

        this.checkSingleResponse(response);
    }

    /**
     * Test if getById works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByIdModified() {
        final Response response = this.target("/" + this.getId())
                .request()
                .header(HttpHeaders.IF_MODIFIED_SINCE, Date.from(Instant.EPOCH))
                .get(Response.class);

        this.checkSingleResponse(response);
    }

    /**
     * Test if getById works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByIdNotModified() {
        final Response response = this.target("/" + this.getId())
                .request()
                .header(HttpHeaders.IF_MODIFIED_SINCE, Date.from(Instant.now()))
                .get(Response.class);
        Assert.assertEquals(Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test if delete works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldNotDelete() {
        final Response response = this.target("/100")
                .request()
                .delete();
        try {
            Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
        } finally {
            response.close();
        }
    }

    /**
     * Test if update works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void shouldUpdate() {
        final Response response = this.target("/1")
                .request()
                .get();
        final String lastModified = response.getHeaderString(HttpHeaders.LAST_MODIFIED);
        TYPE toUpdate = response.readEntity(this.getSingleClass());

        toUpdate = this.update(toUpdate);

        final Response updatedResponse = this.target("/")
                .request()
                .put(Entity.xml(toUpdate));
        try {
            Assert.assertEquals(Status.OK.getStatusCode(), updatedResponse.getStatus());
            Assert.assertNotEquals(lastModified, updatedResponse.getHeaderString(HttpHeaders.LAST_MODIFIED));
            this.checkUpdated(updatedResponse.readEntity(this.getSingleClass()));
        } finally {
            updatedResponse.close();
        }
    }

    /**
     * Test if update works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void shouldUpdateModified() {
        final Response response = this.target("/1")
                .request()
                .get();
        final String lastModified = response.getHeaderString(HttpHeaders.LAST_MODIFIED);
        TYPE toUpdate = response.readEntity(this.getSingleClass());

        toUpdate = this.update(toUpdate);

        final Response updatedResponse = this.target("/")
                .request()
                .header(HttpHeaders.LAST_MODIFIED, Date.from(Instant.EPOCH))
                .put(Entity.xml(toUpdate));
        try {
            Assert.assertEquals(Status.OK.getStatusCode(), updatedResponse.getStatus());
            Assert.assertNotEquals(lastModified, updatedResponse.getHeaderString(HttpHeaders.LAST_MODIFIED));
            this.checkUpdated(updatedResponse.readEntity(this.getSingleClass()));
        } finally {
            updatedResponse.close();
        }
    }

    /**
     * Test if update works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    @ExpectedDatabase(value = "/dataset/expected/FacadeIT_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void shouldUpdateNotModified() {
        final Response response = this.target("/1")
                .request()
                .get();
        final String lastModified = response.getHeaderString(HttpHeaders.LAST_MODIFIED);
        TYPE toUpdate = response.readEntity(this.getSingleClass());

        toUpdate = this.update(toUpdate);

        final Response updatedResponse = this.target("/")
                .request()
                .header(HttpHeaders.LAST_MODIFIED, lastModified)
                .put(Entity.xml(toUpdate));
        try {
            Assert.assertEquals(Status.OK.getStatusCode(), updatedResponse.getStatus());
            Assert.assertNotEquals(lastModified, updatedResponse.getHeaderString(HttpHeaders.LAST_MODIFIED));
            this.checkUpdated(updatedResponse.readEntity(this.getSingleClass()));
        } finally {
            updatedResponse.close();
        }
    }

    /**
     *
     * @param response
     *            response collection to check
     * @param size
     *            the amount in the collection
     */
    protected abstract void checkResponseGetAll(COL response, int size);

    /**
     * check a single instance
     *
     * @param response
     *            the type to check
     */
    protected abstract void checkSingle(TYPE response);

    protected void checkSingleResponse(final Response response) {
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertNotNull(response.getHeaderString(HttpHeaders.LAST_MODIFIED));
        Assert.assertNotNull(response.getHeaderString(HttpHeaders.LOCATION));
        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("max-age=300", response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        // TODO fix me
        // Assert.assertEquals("Wed, 31 Dec 2014 23:00:00 GMT",
        // response.getHeaderString(HttpHeaders.LAST_MODIFIED));
        Assert.assertEquals(this.getBaseUri()
                .toString() + "1", response.getHeaderString(HttpHeaders.LOCATION));

        this.checkSingle(response.readEntity(this.getSingleClass()));
    }

    /**
     * Check the sorting of a collection
     *
     * @param response
     *            the collection
     */
    protected abstract void checkSorting(COL response, boolean reverse);

    /**
     * check updated
     *
     * @param response
     *            the type to check
     */
    protected abstract void checkUpdated(TYPE response);

    /**
     *
     * @return create update object
     */
    protected abstract TYPE create();

    protected abstract int expectedSize();

    /**
     *
     * @return COL
     */
    @SuppressWarnings("unchecked")
    protected Class<COL> getCollectionClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        return (Class<COL>) genericSuperclass.getActualTypeArguments()[COLLECTION_TYPE_ARGUMENT_NUMBER];
    }

    /**
     *
     * @return identifier
     */
    protected abstract ID getId();

    /**
     *
     * @return the ID
     */
    @SuppressWarnings("unchecked")
    protected Class<ID> getIdentifierClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        return (Class<ID>) genericSuperclass.getActualTypeArguments()[1];
    }

    /**
     * @return the identifier field name
     */
    protected abstract String getIdField();

    /**
     *
     * @return the single glass
     */
    @SuppressWarnings("unchecked")
    protected Class<TYPE> getSingleClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        return (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    protected ResourceConfig registerFilters(final ResourceConfig rc) {
        return rc.register(CacheFilterFactory.class);
    }

    /**
     * Update
     *
     * @param response
     *            object to update
     * @return updated object
     */
    protected abstract TYPE update(TYPE response);
}
