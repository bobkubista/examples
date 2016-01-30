/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.api.file;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Bob
 *
 */
@Path("file")
public interface FileApi {

    @GET
    @Path("{filepath: .*}")
    @Produces(MediaType.TEXT_PLAIN)
    public File getFile(@PathParam("filepath") final String filepath);
}
