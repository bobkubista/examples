package bobkubista.examples.webapps.jsf;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Test bean
 *
 * @author Bob
 *
 */
@ManagedBean
@RequestScoped
public class TestBean {

    private String message;

    /**
     *
     * @return
     */
    public String getMessage() {
        return this.message;
    }

    @PostConstruct
    private void init() {
        this.message = "JSF is running";
    }

}
