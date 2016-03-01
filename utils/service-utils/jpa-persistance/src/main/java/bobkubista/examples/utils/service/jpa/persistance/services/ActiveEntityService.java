package bobkubista.examples.utils.service.jpa.persistance.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;
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
    public abstract ActiveDAO<TYPE, ID> getDAO();

    /**
     *
     * @param maxResults
     *            amount of results to return
     * @param page
     *            the page to skip too. page * maxResults will be skipped
     * @param sortFields
     *            the field to sort by
     * @return all active entities of <code>TYPE</code>
     */
    default Collection<TYPE> getAllActive(final List<String> sortFields, final Integer page, final Integer maxResults) {
        return this.getDAO()
                .findAllActive(sortFields, page, maxResults);
    }

}
