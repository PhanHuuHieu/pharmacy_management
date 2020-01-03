package pharmacy.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpServletRequest request) {
		return "singin";
	}
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		return "redirect:/login";
	}
}
