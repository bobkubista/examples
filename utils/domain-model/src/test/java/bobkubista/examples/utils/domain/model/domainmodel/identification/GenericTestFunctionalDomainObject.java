/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

/**
 * @author Bob
 *
 */
public class GenericTestFunctionalDomainObject extends AbstractGenericFunctionalIdentifiableDomainObject {

	private static final long serialVersionUID = -5984860269385611821L;

	private final Long id = 2L;

	/**
	 * Constructor
	 */
	public GenericTestFunctionalDomainObject() {
		super("testObject2");
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(final Long id) {
	}

}
