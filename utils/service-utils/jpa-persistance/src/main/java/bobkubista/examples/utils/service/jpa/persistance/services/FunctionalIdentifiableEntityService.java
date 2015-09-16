package bobkubista.examples.utils.service.jpa.persistance.services;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericFunctionalIdentifiableEntityDao;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            Identifier of {@link AbstractGenericFunctionalIdentifiableEntity}
 */
public interface FunctionalIdentifiableEntityService<TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable> extends IdentifiableEntityService<TYPE, ID> {

    /**
     * get the {@link IdentifiableEntity}
     *
     * @param identifier
     *            the identfier @return the {@link IdentifiableEntity}
     */
    public default TYPE getByFunctionalId(final Object identifier) {
        return this.getDAO().getByFunctionalId(identifier);
    }

    @Override
    public abstract AbstractGenericFunctionalIdentifiableEntityDao<TYPE, ID> getDAO();

    /**
     *
     * @param fId
     *            functional id @return the identifier <code>ID</code> for the
     *            given functional id
     */
    public default ID getIdByFunctionalId(final String fId) {
        return this.getDAO().getIdByFunctionalId(fId);
    }

    /**
     *
     * @param identifier
     *            the functioanl identifier to use @return search for
     *            <code>TYPE</code> which has the identifier as part of the
     *            functional id
     */
    public default Collection<TYPE> searchByFunctionalID(final String identifier) {
        return this.getDAO().searchByFunctionalId(identifier);
    }
}
