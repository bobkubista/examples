package bobkubista.examples.utils.domain.model.domainmodel.validators;

/**
 *
 * @author Bob
 *
 */
@XorFields.List({ @XorFields(first = "first", second = "second", message = "bla") })
public class MockXorFieldsObject {
    private String first;
    private String second;

    public MockXorFieldsObject() {
        super();
    }

    public MockXorFieldsObject(String first, String seccond) {
        this.first = first;
        this.second = seccond;
    }

    /**
     * @return the first
     */
    public final String getFirst() {
        return this.first;
    }

    /**
     * @return the second
     */
    public final String getSecond() {
        return this.second;
    }

    /**
     * @param first
     *            the first to set
     */
    public final void setFirst(String first) {
        this.first = first;
    }

    /**
     * @param second
     *            the second to set
     */
    public final void setSecond(String second) {
        this.second = second;
    }
}
