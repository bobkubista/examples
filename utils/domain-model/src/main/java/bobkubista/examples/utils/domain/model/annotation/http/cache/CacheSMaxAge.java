/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Bob
 *
 *         Sets the cache header to the value "s max age"
 *
 * @see <a href=
 *      'http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.9.1'>W3c
 *      Header Field Definitions</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheSMaxAge {
    public static final Function<CacheSMaxAge, String> HEADER = maxAge -> "s-maxage=" + Long.toString(maxAge.unit()
            .toSeconds(maxAge.time()));

    /**
     * @return The amount of time to cache this resource.
     */
    long time();

    /**
     * @return The {@link TimeUnit} for the given {@link #time()}.
     */
    TimeUnit unit();
}
