/**
 * Bob Kubista's examples
 */
package bobkubista.example.utils.property.filters;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import bobkubista.example.utils.property.ServerProperties;

/**
 * @author Bob
 *
 */
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        final String httpMethod = requestContext.getMethod();
        if ("OPTIONS".equalsIgnoreCase(httpMethod)) {
            return;
        }
        final String authenticationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authenticationHeader == null) {
            throw new NotAuthorizedException("Bearer");
        }

        final String token = this.parseToken(authenticationHeader);
        final Principal user = this.getUserByToken(token);

        if (!this.verifyToken(user)) {
            throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
        }
        // requestContext.setSecurityContext(new TimSecurityContext(user,
        // servletRequest))

    }

    private Principal getUserByToken(final String token) {
        final String casUrl = ServerProperties.getString("cas.url");
        ClientBuilder.newClient()
                .target(casUrl)
                .queryParam("access_token", token)
                .request(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .get();

        // Principal principal = response.readEntity(CasUser.class)
        return null;
    }

    private String parseToken(final String authenticationHeader) {
        return StringUtils.stripStart(authenticationHeader, "Bearer ");
    }

    private boolean verifyToken(final Principal principal) {
        return principal != null;
    }

}
