/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.mocks;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * @author Bob
 *
 */
public class MockFunctionalEntity extends AbstractGenericFunctionalIdentifiableEntity<Integer> {

    private static final long serialVersionUID = -5916180822928979766L;

    private final String functionalId = "foo";
    private final Integer id = 1;

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
