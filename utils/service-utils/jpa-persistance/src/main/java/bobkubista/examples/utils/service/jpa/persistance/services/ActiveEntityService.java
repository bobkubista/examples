package bobkubista.examples.utils.service.jpa.persistance.services;

import java.io.Serializable;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            identifier
 */
@Transactional
public interface ActiveEntityService<TYPE extends AbstractGenericActiveEntity<ID>, ID extends Serializable> extends FunctionalIdentifiableEntityService<TYPE, ID> {

    /**
     *
     * @return the amount of all active entities
     */
    public default Long countActive() {
        return this.getDAO()
                .countActive();
    }

    @Override
    public abstract GenericActiveDAO<TYPE, ID> getDAO();

    /**
     *
     * @param search
     *            {@link SearchBean}
     * @return all active entities of <code>TYPE</code>
     */
    default Stream<TYPE> getAllActive(final SearchBean search) {
        return this.getDAO()
                .findAllActive(search);
    }

}
