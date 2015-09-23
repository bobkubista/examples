package bobkubista.examples.utils.domain.model.domainmodel.identification;

/**
 * Abstract domain object for testing
 *
 * @author bkubista
 *
 */
public class GenericTestActiveDomainObject extends AbstractGenericActiveDomainObject<Integer> {

    private static final long serialVersionUID = 1L;

    private final Integer id = 1;

    /**
     * constructor
     */
    public GenericTestActiveDomainObject() {
        super(true, "testObject");
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(final Integer id) {

    }
}
