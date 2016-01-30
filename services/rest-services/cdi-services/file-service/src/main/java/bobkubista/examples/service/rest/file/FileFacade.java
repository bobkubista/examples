/**
 * Bob Kubista's examples
 */
package bobkubista.examples.service.rest.file;

import java.io.File;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.rest.api.file.FileApi;

/**
 * @author Bob
 *
 */
public class FileFacade implements FileApi {

    static final String BASEPATH = ServerProperties.getString("file.base.path");

    @Override
    public File getFile(final String filepath) {
        return new File(BASEPATH + filepath);
    }

}
