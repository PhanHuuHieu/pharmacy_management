package pharmacy.management.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.form.EmployeeForm;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.LogService;

@Controller
public class InforAccountController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LogService logService;

	@RequestMapping(value = { "/inforAccountAdmin" }, method = RequestMethod.GET)
	public String inforAccountAdmin(ModelMap modelMap, HttpServletRequest request, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		CommonContanst.displayUsername(modelMap, principal);
		List<TEmployee> listCus = employeeService
				.getAccountWithIdLogin((String) request.getSession().getAttribute("idLogin"));
		EmployeeForm cForm = new EmployeeForm(listCus.get(0).getName(), listCus.get(0).getEmail(),
				listCus.get(0).getPhone_number());
		modelMap.addAttribute("employeeForm", cForm);
		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Thông Tin.");
		return "inforAccountAdmin";
	}

	@RequestMapping(value = "editAccountAdmin", method = RequestMethod.POST)
	public String editAccountAdmin(@ModelAttribute("employeeForm") EmployeeForm employeeForm, ModelMap modelMap,
			HttpServletRequest request) {
		employeeService.editInforAccount(employeeForm, (String) request.getSession().getAttribute("idLogin"));
		return "redirect:/product";
	}
}
