package pharmacy.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.entity.TProduct;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.service.CustomerService;
@Controller
public class LoginController {
	@Autowired
    private CustomerService customerService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String register(@ModelAttribute("customerForm") CustomerForm customerForm,ModelMap modelMap,HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(@ModelAttribute("customerForm") CustomerForm customerForm,ModelMap modelMap,HttpServletRequest request) {
		return "login";
	}
	@RequestMapping(value = { "/registerCustomer" }, method = RequestMethod.POST)
    public String personLisst(@ModelAttribute("customerForm") CustomerForm customerForm, ModelMap modelMap,HttpServletRequest request) {
	   if(!customerForm.getPassword().equals(customerForm.getRe_password()))
	   {
		   return "login";
	   }
	   customerForm.setPassword(passwordEncoder.encode(customerForm.getPassword()));
	   customerService.registerCustomer(customerForm);
	   return "index";
    }
}
