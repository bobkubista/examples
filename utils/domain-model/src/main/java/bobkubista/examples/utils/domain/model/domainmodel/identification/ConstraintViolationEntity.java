package bobkubista.examples.utils.domain.model.domainmodel.identification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <a href
 * ="https://github.com/abhirockzz/JAX-RS-and-Bean-Validation/blob/master/src/main/java/abhirockzz/wordpress/com/payara611/ConstraintViolationEntity.java">
 * source</a>
 * 
 * @author Bob
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConstraintViolationEntity {

    private String errorMsg;

    public ConstraintViolationEntity() {
    }

    public ConstraintViolationEntity(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
