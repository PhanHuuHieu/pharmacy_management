package pharmacy.management.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.bean.GooglePojo;
import pharmacy.management.common.RestFB;
import pharmacy.management.config.GoogleUtils;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.form.LoginForm;
import pharmacy.management.form.RegisterForm;
import pharmacy.management.service.AddressService;
import pharmacy.management.service.CustomerService;
@Controller
public class LoginController {
	@Autowired
    private CustomerService customerService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private RestFB restFb;
	@Autowired
	private AddressService addressService;
	@Autowired
	private GoogleUtils googleUtils;
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,ModelMap modelMap,HttpServletRequest request) {
		return "register";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm,ModelMap modelMap,HttpServletRequest request) {
		return "singin";
	}
	@RequestMapping(value = { "/register-account" }, method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute("registerForm") RegisterForm registerForm,ModelMap modelMap,HttpServletRequest request) {
		// Kiem tra trung email
		List<TCustomer> listEmail = customerService.getAccountWithEmail(registerForm.getEmail());
		if(listEmail.size()>0)
		{
			modelMap.addAttribute("error", "Email trùng!");
			return "register";
		}
		// Kiem tra trung ten dang nhap
		List<TCustomer> list = customerService.findUserAccount(registerForm.getUser_name());
		if(list.size()>0)
		{
			modelMap.addAttribute("error", "Tên đăng nhập trùng!");
			return "register";
		}
		else
		{
			registerForm.setPassword(passwordEncoder.encode(registerForm.getPassword()));
			customerService.registerCustomer(registerForm);
			List<TCustomer> list1 = customerService.findUserAccount(registerForm.getUser_name());
			addressService.insertAddress(String.valueOf(list1.get(0).getId()));
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/login-facebook")
	public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		
		if (code == null || code.isEmpty()) {
			return "redirect:/login?facebook=error";
		}

		String accessToken = restFb.getToken(code);
		System.out.println(accessToken);
		
		com.restfb.types.User user = restFb.getUserInfo(accessToken);
		UserDetails userDetail = restFb.buildUser(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String name = userDetail.getUsername().substring(17,userDetail.getUsername().length());
		String id_social = userDetail.getUsername().substring(0, 15);
		request.getSession().setAttribute("user_name", name);
		if(customerService.getIdSocial(id_social).size()==0)
		{
			// insert account with fb
			customerService.insertCustomer(name, id_social);
			
		}
		String idLogin =String.valueOf(customerService.getIdSocial(id_social).get(0).getId());
		request.getSession().setAttribute("idLogin", idLogin);
		// insert address customer
		addressService.insertAddress(idLogin);
		return "redirect:/index";
		//return "login";
	}
	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		
		if (code == null || code.isEmpty()) {
			return "redirect:/login?google=error";
		}

		String accessToken = googleUtils.getToken(code);
		
		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String nameLogin = userDetail.getUsername().substring(0, userDetail.getUsername().length()-10);
		request.getSession().setAttribute("user_name", nameLogin);
		
		String id_social = googlePojo.getId();
		if(customerService.getIdSocial(id_social).size()==0)
		{
			// insert account with fb
			customerService.insertCustomer(nameLogin, id_social);
			
		}
		String idLogin =String.valueOf(customerService.getIdSocial(id_social).get(0).getId());
		request.getSession().setAttribute("idLogin", idLogin);
		// insert address customer
		addressService.insertAddress(idLogin);
		return "redirect:/index";
	}
	
	@RequestMapping(value = { "/registerCustomer" }, method = RequestMethod.POST)
    public String personLisst(/*@ModelAttribute("customerForm") CustomerForm customerForm, */ModelMap modelMap,HttpServletRequest request) {
//	   if(!customerForm.getPassword().equals(customerForm.getRe_password()))
//	   {
//		   return "login";
//	   }
//	   customerForm.setPassword(passwordEncoder.encode(customerForm.getPassword()));
//	   customerService.registerCustomer(customerForm);
	   return "index";
    }
}
