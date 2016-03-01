package bobkubista.examples.utils.domain.model.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass() && !obj.getClass()
                .isAssignableFrom(this.getClass())
                && !this.getClass()
                        .isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SearchBean other = (SearchBean) obj;
        // TODO maybe refactor this and hashcode to "new
        // EqualsBuilder().reflectionEquals(lhs, rhs, excludeFields)" where the
        // excludeFields is an abstract method
        return new EqualsBuilder().append(this.getMaxResults(), other.getMaxResults())
                .append(this.getPage(), other.getPage())
                .append(this.getSort(), other.getSort())
                .isEquals();
    }

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getSort())
                .append(this.getPage())
                .append(this.getMaxResults())
                .toHashCode();
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
