package bobkubista.examples.utils.domain.model.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * {@link DomainObject} that defines search parameters like amounts to skip,
 * amount of results to return and sorts. It is written with fluent api.
 *
 * @author Bob
 *
 */
public class SearchBean implements DomainObject {

    private static final long serialVersionUID = 292585102377195854L;

    @QueryParam(ApiConstants.MAX)
    @DefaultValue("20")
    private Integer maxResults = 20;

    @QueryParam(ApiConstants.PAGE)
    @DefaultValue("0")
    private Integer page = 0;

    @QueryParam(ApiConstants.SORT)
    private List<String> sort = new ArrayList<>();

    /**
     * @return the maxResults
     */
    public final Integer getMaxResults() {
        return this.maxResults;
    }

    /**
     * @return the page
     */
    public final Integer getPage() {
        return this.page;
    }

    /**
     * @return the sort
     */
    public final List<String> getSort() {
        return this.sort;
    }

    /**
     * @param maxResults
     *            the maxResults to set
     * @return the {@link SearchBean}
     */
    public final SearchBean setMaxResults(final Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * @param page
     *            the page to set
     * @return the {@link SearchBean}
     */
    public final SearchBean setPage(final Integer page) {
        this.page = page;
        return this;
    }

    /**
     * @param sort
     *            the sort to set
     * @return the {@link SearchBean}
     */
    public final SearchBean setSort(final List<String> sort) {
        this.sort = sort;
        return this;
    }
}
