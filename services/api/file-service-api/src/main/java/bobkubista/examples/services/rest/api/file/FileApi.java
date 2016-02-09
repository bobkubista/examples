/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.api.file;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Api for files
 *
 * @author Bob
 *
 */
@Path("file")
public interface FileApi {

    /**
     * Download a file at this path
     *
     * @param filepath
     *            relative filepath
     * @return the file
     */
    @GET
    @Path("{filepath: .*}")
    @Produces(MediaType.TEXT_PLAIN)
    public File getFile(@PathParam("filepath") final String filepath);

    @POST
    @Path("{filepath: .*}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response uploadFile(@PathParam("fileoath") final String filepath);
}
