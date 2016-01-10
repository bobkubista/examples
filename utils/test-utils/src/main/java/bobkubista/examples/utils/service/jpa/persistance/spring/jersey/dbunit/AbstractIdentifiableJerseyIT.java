package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;

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
public abstract class AbstractIdentifiableJerseyIT<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractBaseSpringJerseyDbUnitTest {

	private static final int COLLECTION_TYPE_ARGUMENT_NUMBER = 2;

	/**
	 * Test if create works
	 */
	@Test
	@DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
	@ExpectedDatabase(value = "/dataset/expected/FacadeIT_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void shouldCreate() {
		final TYPE domainObject = this.create();
		final Response response = this.target("/").request().post(Entity.xml(domainObject));
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
	public void shouldDelete() {
		this.target("/-1").request().delete();
	}

	/**
	 * Test if getAll works
	 */
	@Test
	@DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
	public void shouldGetAll() {
		final COL response = this.target("/").request().get(this.getCollectionClass());
		this.checkResponseGetAll(response, 1);
	}

	/**
	 * Test if getAll with query params works
	 */
	@Test
	@DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
	public void shouldGetAllWithSortAndLimit() {
		final COL response = this.target("/").queryParam(IdentifiableApi.SORT, this.getIdField()).queryParam(IdentifiableApi.PAGE, 0).queryParam(IdentifiableApi.MAX, 2).request()
		        .get(this.getCollectionClass());
		this.checkResponseGetAll(response, 1);
		this.checkSorting(response);
	}

	/**
	 * Test if getById works
	 */
	@Test
	@DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
	public void shouldGetById() {
		final TYPE response = this.target("/" + this.getId()).request().get(this.getSingleClass());
		this.checkSingle(response);
	}

	/**
	 * Test if update works
	 */
	@Test
	@DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
	@ExpectedDatabase(value = "/dataset/expected/FacadeIT_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void shouldUpdate() {
		TYPE response = this.target("/1").request().get(this.getSingleClass());
		response = this.update(response);
		this.target("/").request().put(Entity.xml(response));
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

	/**
	 * Check the sorting of a collection
	 *
	 * @param response
	 *            the collection
	 */
	protected abstract void checkSorting(COL response);

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

	/**
	 *
	 * @return COL
	 */
	@SuppressWarnings("unchecked")
	protected Class<COL> getCollectionClass() {
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<COL>) genericSuperclass.getActualTypeArguments()[COLLECTION_TYPE_ARGUMENT_NUMBER];
	}

	/**
	 *
	 * @return identifier
	 */
	protected abstract ID getId();

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
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
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
