package pharmacy.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.bean.PurchaseBean;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.service.AddressService;
import pharmacy.management.service.CustomerService;
@Controller
public class InforAccountController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AddressService addressService;
	
	
	@RequestMapping(value = { "/inforAccount" }, method = RequestMethod.GET)
    public String inforAccount(ModelMap modelMap,@ModelAttribute("customerForm") CustomerForm customerForm,HttpServletRequest request) {
		List<TCustomer> listCus = customerService.getAccountWithIdLogin((String) request.getSession().getAttribute("idLogin"));
		List<PurchaseBean> listAddress = addressService.getAddress((String) request.getSession().getAttribute("idLogin"));
		CustomerForm cForm = new CustomerForm(listCus.get(0).getName(),
				listCus.get(0).getEmail(),
				listCus.get(0).getPhone_number(),
				listCus.get(0).getNumber_account(),
				listAddress.get(0).getAddress_detail());
		modelMap.addAttribute("customerForm", cForm);
		return "inforAccount";
	}
	@RequestMapping(value = "editAccount", method = RequestMethod.POST)  
	public String editAccount(ModelMap modelMap,@ModelAttribute("customerForm") CustomerForm customerForm,HttpServletRequest request) {  
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setAddress_detail(customerForm.getAddress());
		addressService.addAddress(purchaseBean,(String) request.getSession().getAttribute("idLogin"),1);
		customerService.editInforAccount(customerForm, (String) request.getSession().getAttribute("idLogin"));
		request.getSession().setAttribute("user_name", customerForm.getName());
		return "redirect:/index";  
	}  
}
