package pharmacy.management.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.entity.TCustomer;
import pharmacy.management.form.RegisterForm;
import pharmacy.management.service.CustomerService;
 
@Controller
public class SimpleEmailExampleController {
 
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
	private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @RequestMapping(value = { "/sendMail" }, method = RequestMethod.POST)
    public String sendEmail(@ModelAttribute("registerForm") RegisterForm registerForm,HttpServletRequest request) {
 
    	request.getSession().setAttribute("email", registerForm.getEmail());
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(registerForm.getEmail());
        message.setSubject("Đổi password từ HT-Pharmacy");
        message.setText("Nhấp vào link để đổi password: http://localhost:8080/changePassword");
 
        // Send Message!
        this.emailSender.send(message);
 
        return "redirect:/login";
    }
    @RequestMapping(value = { "/forgetPassword" }, method = RequestMethod.GET)
    public String forgetPassword(ModelMap modelMap,@ModelAttribute("registerForm") RegisterForm registerForm,HttpServletRequest request) {
		return "fg-pass";
	}

    @RequestMapping(value = { "/changePassword" }, method = RequestMethod.GET)
    public String changePassword(ModelMap modelMap,@ModelAttribute("registerForm") RegisterForm registerForm,HttpServletRequest request) {
		
    	return "change-pass";
	}
    @RequestMapping(value = { "/getNewPassword" }, method = RequestMethod.POST)
    public String getNewPassword(ModelMap modelMap,@ModelAttribute("registerForm") RegisterForm registerForm,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<TCustomer> list = customerService.getAccountWithEmail(email);
		registerForm.setPassword(passwordEncoder.encode(registerForm.getPassword()));
		customerService.updatePasswordNew(registerForm.getPassword(),String.valueOf(list.get(0).getId()));
    	return "redirect:/login";
	}
}