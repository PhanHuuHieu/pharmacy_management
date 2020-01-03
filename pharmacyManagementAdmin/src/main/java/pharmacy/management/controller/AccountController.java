package pharmacy.management.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.AccountBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.LogService;

@Controller
public class AccountController {
	@Autowired
	private LogService logService;
	@Autowired
	private CustomerService customerService;

	@GetMapping("/getListAccount")
	@ResponseBody
	public List<AccountBean> getListOrderProduct(@RequestParam String username, @RequestParam String email,
			@RequestParam String phone_number) {
		return customerService.gettListAccount(username, email, phone_number);
	}

	@GetMapping("/blockAccount")
	@ResponseBody
	public String blockAccount(@RequestParam String idAccount, @RequestParam String check, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		customerService.blockAccount(idAccount, check);
		return null;

	}

	@GetMapping("/deleteAccount")
	@ResponseBody
	public String deleteAccount(@RequestParam String idAccount, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		customerService.deleteAccount(idAccount);
		return null;

	}

	@RequestMapping(value = { "/account" }, method = RequestMethod.GET)
	public String account(ModelMap modelMap, HttpServletRequest request, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		CommonContanst.displayUsername(modelMap, principal);

		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Tài Khoản.");
		return "account";
	}
}
