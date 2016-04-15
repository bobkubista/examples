package bobkubista.example.webapps.tools.admin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@RequestMapping("/load")
	public String loadMenu(final Model model) {
		LOGGER.info("Loading menu main app");
		final RestTemplate rest = new RestTemplate();
		final StringBuilder sb = new StringBuilder();
		for (final MenuEnum next : MenuEnum.values()) {
			LOGGER.info("Menu : " + next.name() + " : " + next.toString());
			try {
				sb.append(rest.getForObject(next.toString(), String.class));
			} catch (final RestClientException e) {
				LOGGER.info("Cannot load menu from : {}.\n{}", next.toString(), e);
			}
		}
		model.addAttribute("menu", sb.toString());
		return "menu/leftMenu";
	}

}
