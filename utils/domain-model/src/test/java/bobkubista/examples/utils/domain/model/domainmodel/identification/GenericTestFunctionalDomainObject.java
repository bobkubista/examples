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

    private final String functionalId = "testObject2";
    private final Integer id = 2;

    @Override
    public String getFunctionalId() {
        return this.functionalId;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setFunctionalId(String functionalId) {
    }

    @Override
    public void setId(Integer id) {
    }

}
