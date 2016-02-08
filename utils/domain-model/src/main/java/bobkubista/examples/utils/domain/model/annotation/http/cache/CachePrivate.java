/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

import javax.ws.rs.core.CacheControl;

/**
 * Sets the cache header to the value "Private"
 *
 * @author Bob
 *
 * @see <a href=
 *      'http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.9.1'>W3c
 *      Header Field Definitions</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CachePrivate {

    public static final Function<CachePrivate, CacheControl> HEADER = t -> {
        final CacheControl cc = new CacheControl();
        cc.setPrivate(true);
        return cc;
    };

}
