package bobkubista.examples.utils.domain.model.api;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class SearchBean {

    @QueryParam(ApiConstants.MAX)
    @DefaultValue("20")
    private Integer maxResults;

    @QueryParam(ApiConstants.PAGE)
    @DefaultValue("0")
    private Integer page;

    @QueryParam(ApiConstants.SORT)
    private List<String> sort;

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
     */
    public final SearchBean setMaxResults(final Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * @param page
     *            the page to set
     */
    public final SearchBean setPage(final Integer page) {
        this.page = page;
        return this;
    }

    /**
     * @param sort
     *            the sort to set
     */
    public final SearchBean setSort(final List<String> sort) {
        this.sort = sort;
        return this;
    }
}
