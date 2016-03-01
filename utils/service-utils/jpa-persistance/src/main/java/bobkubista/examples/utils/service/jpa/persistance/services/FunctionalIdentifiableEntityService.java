package bobkubista.examples.utils.service.jpa.persistance.services;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.service.jpa.persistance.dao.GenericFunctionalIdentifiableDao;
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
@Transactional
public interface FunctionalIdentifiableEntityService<TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable>
        extends IdentifiableEntityService<TYPE, ID> {

    /**
     * get the {@link AbstractGenericFunctionalIdentifiableEntity}
     *
     * @param identifier
     *            the identfier @return the
     *            {@link AbstractGenericFunctionalIdentifiableEntity}
     * @return {@link AbstractGenericFunctionalIdentifiableEntity}
     */
    public default TYPE getByFunctionalId(final Object identifier) {
        return this.getDAO()
                .getByFunctionalId(identifier);
    }

    @Override
    public abstract GenericFunctionalIdentifiableDao<TYPE, ID> getDAO();

    /**
     *
     * @param fId
     *            functional id
     * @return the identifier <code>ID</code> for the given functional id
     */
    public default ID getIdByFunctionalId(final String fId) {
        return this.getDAO()
                .getIdByFunctionalId(fId);
    }

}
