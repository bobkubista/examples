package bobkubista.examples.utils.domain.model.domainmodel.identification;

/**
 * Abstract domain object for testing
 *
 * @author bkubista
 *
 */
public class GenericTestActiveDomainObject extends AbstractGenericActiveDomainObject {

	private static final long serialVersionUID = 1L;

	private final Long id = 1L;

	/**
	 * constructor
	 */
	public GenericTestActiveDomainObject() {
		super(true, "testObject");
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(final Long id) {

	}
}
