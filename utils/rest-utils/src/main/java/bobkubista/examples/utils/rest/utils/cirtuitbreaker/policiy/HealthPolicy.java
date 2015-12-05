/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

/**
 * @author Bob
 *
 */
public interface HealthPolicy {

    boolean isHealthy(String scope);

}
