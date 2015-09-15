package bobkubista.examples.services.rest.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Bob
 *
 */
@Path("")
public class ItemRest {
	@PersistenceContext
	private EntityManager em;

	@Context
	private UriInfo uriInfo;

	/**
	 * Create
	 *
	 * @param entity
	 *            {@link Item} to create
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	public void create(final Item entity) {
		this.em.persist(entity);
	}

	/**
	 * edit
	 *
	 * @param id
	 *            identifier
	 * @param entity
	 *            {@link Item}
	 */
	@PUT
	@Path("{id}")
	@Consumes({ "application/xml", "application/json" })
	public void edit(@PathParam("id") final Integer id, final Item entity) {
		this.em.merge(entity);
	}

	/**
	 * find
	 *
	 * @param id
	 *            identifier
	 * @return {@link Item}
	 */
	@GET
	@Path("{id}")
	@Produces({ "application/xml; qs=0.50", "application/json" })
	public Item find(@PathParam("id") final Integer id) {
		return this.em.createNamedQuery("Item.findById", Item.class).setParameter("id", id).getSingleResult();
	}

	/**
	 * get all
	 *
	 * @return {@link List} of {@link Item}s
	 */
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Item> findAll() {
		return this.em.createNamedQuery("Item.findAll", Item.class).getResultList();
	}

	/**
	 * delete
	 * 
	 * @param id
	 *            identifier
	 */
	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") final Integer id) {
		this.em.remove(this.find(id));
	}
}
