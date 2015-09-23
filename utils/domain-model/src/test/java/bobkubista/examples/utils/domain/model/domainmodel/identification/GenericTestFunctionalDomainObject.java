/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

/**
 * @author Bob
 *
 */
public class GenericTestFunctionalDomainObject extends AbstractGenericFunctionalIdentifiableDomainObject<Integer> {

    private static final long serialVersionUID = -5984860269385611821L;

    private final Integer id = 2;

    /**
     * Constructor
     */
    public GenericTestFunctionalDomainObject() {
        super("testObject2");
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(final Integer id) {
    }

}
