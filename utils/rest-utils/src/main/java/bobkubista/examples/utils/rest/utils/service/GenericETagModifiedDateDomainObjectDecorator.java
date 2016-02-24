package bobkubista.examples.utils.rest.utils.service;

import java.time.Instant;
import java.util.Locale;

import javax.ws.rs.core.EntityTag;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * Generic decorator for {@link DomainObject}s, to add an {@link EntityTag} and
 * Last modified date
 *
 * @author Bob
 *
 * @param <TYPE>
 *            the type of {@link DomainObject}
 */
public class GenericETagModifiedDateDomainObjectDecorator<TYPE extends DomainObject> {

    private final EntityTag eTag;

    private final Locale locale;

    private final Instant modifiedDate;

    private final TYPE object;

    /**
     *
     * Constructor
     *
     * @param eTag
     *            {@link EntityTag}
     * @param modifiedDate
     *            {@link Instant}
     * @param object
     *            The {@link DomainObject}
     */
    public GenericETagModifiedDateDomainObjectDecorator(final EntityTag eTag, final Instant modifiedDate, final TYPE object, final Locale locale) {
        this.eTag = eTag;
        this.modifiedDate = modifiedDate;
        this.object = object;
        this.locale = locale;
    }

    /**
     * @return the eTag
     */
    public final EntityTag getETag() {
        return this.eTag;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * @return the modifiedDate
     */
    public final Instant getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * @return the object
     */
    public final TYPE getObject() {
        return this.object;
    }
}
