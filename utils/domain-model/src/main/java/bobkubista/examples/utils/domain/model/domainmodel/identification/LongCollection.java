package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IdCollection")
@XmlAccessorType(XmlAccessType.NONE)
public class LongCollection extends AbstractGenericDomainObjectCollection<Long> {

	private static final long serialVersionUID = -833926326168736129L;

	@XmlElementWrapper(name = "userlist")
	@XmlElement(name = "user")
	private final Collection<Long> ids = new LinkedList<>();

	@Override
	public Collection<Long> getDomainCollection() {
		return ids;
	}

}
