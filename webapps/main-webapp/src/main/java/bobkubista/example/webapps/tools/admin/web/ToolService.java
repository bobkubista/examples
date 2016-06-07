package bobkubista.example.webapps.tools.admin.web;

import bobkubista.example.utils.property.ServerProperties;

/**
 *
 * @author Bob Kubista
 *
 */
public final class ToolService {

    /**
     * Private Constructor
     */
    private ToolService() {
        super();
    }

    /**
     *
     * @param appName
     * @return
     */
    public static String getUrl(final String appName) {
        return ServerProperties.get()
                .getString(appName);
    }
}
