package bobkubista.example.webapps.tools.admin.web;

import bobkubista.example.utils.property.ApacheCommonsConfig;

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
        return ApacheCommonsConfig.INSTANCE.get()
                .getString(appName);
    }
}
