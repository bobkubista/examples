/**
 *
 */
package bobkubista.example.webapps.tools.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

@Component("ParameterInterceptor")
public class ParameterInterceptor extends HandlerInterceptorAdapter {
	/**
	 * {@inheritDoc}
	 *
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
		if (modelAndView == null) {
			return;
		}
		if (modelAndView.getView() == null && modelAndView.getViewName().startsWith("redirect:")
		        || modelAndView.getView() != null && modelAndView.getView() instanceof RedirectView) {
			return;
		}
		String contextPath = request.getContextPath();
		if (contextPath == null) {
			contextPath = "";
		}
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.lastIndexOf('/'));
		}
		modelAndView.addObject("contextPath", contextPath);

		super.postHandle(request, response, handler, modelAndView);
	}
}
