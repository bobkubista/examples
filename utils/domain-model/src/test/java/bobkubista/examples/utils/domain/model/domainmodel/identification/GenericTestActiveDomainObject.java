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
    private final String functionalId = "testObject";

    public GenericTestActiveDomainObject() {
        super(true);
    }

    @Override
    public String getFunctionalId() {
        return this.functionalId;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setFunctionalId(final String functionalId) {

    }

    @Override
    public void setId(final Integer id) {

    }
}
