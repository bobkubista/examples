package bobkubista.examples.utils.domain.model.domainmodel.identification;

/**
 * Abstract domain object for testing
 *
 * @author bkubista
 *
 */
public class GenericTestActiveDomainObject extends AbstractGenericActiveDomainObject<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * constructor
     */
    public GenericTestActiveDomainObject() {
        super(true, "testObject", 1);
    }

}
