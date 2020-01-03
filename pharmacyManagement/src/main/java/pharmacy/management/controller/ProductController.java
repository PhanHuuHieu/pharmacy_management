package pharmacy.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class ProductController {
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String getIndex(ModelMap modelMap,HttpServletRequest request) {
		return "index";
	}
}