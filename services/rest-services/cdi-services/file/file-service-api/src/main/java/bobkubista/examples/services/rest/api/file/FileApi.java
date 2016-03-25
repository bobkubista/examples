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
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public File getFile(@PathParam("filepath") final String filepath);

    /**
     * Upload a file
     *
     * @param filepath
     *            the relative filepath where to store the file
     * @return {@link Response} that indicated if the upload was succesfull.
     */
    @POST
    @Path("{filepath: .*}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadFile(@PathParam("filepath") final String filepath, final File file);
}
