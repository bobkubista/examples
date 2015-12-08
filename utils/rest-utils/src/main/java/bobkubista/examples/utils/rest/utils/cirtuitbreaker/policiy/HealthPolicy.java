/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

/**
 * An interface which defines a heath policy, on basis of which to determain of
 * an operation should be called or closed down
 *
 * @author Bob
 *
 */
@FunctionalInterface
public interface HealthPolicy {

    /**
     *
     * @param scope
     *            the host
     * @return is the transaction healthy
     */
    boolean isHealthy(String scope);

}
