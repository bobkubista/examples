/**
 *
 */
package bobkubista.examples.services.api.email;

/**
 * @author Bob
 *
 */
public interface Replacement {

    /**
     *
     * @return the value that needs to be replaced
     */
    public String getConstant();

    /**
     *
     * @return the value that has to be inserted
     */
    public String getValue();

}
