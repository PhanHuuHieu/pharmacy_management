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

import pharmacy.management.bean.LogBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.LogService;

@Controller
public class LogController {
	@Autowired
	LogService logService;
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = { "/log" }, method = RequestMethod.GET)
	public String listProduct(HttpServletRequest request, ModelMap modelMap, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		CommonContanst.displayUsername(modelMap, principal);
		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Nhật Ký.");
		return "log";
	}

	@GetMapping("/getDataLog")
	@ResponseBody
	public List<LogBean> getLog(@RequestParam String idEmployee, @RequestParam String dateTime,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws NumberFormatException, IOException {
		modelMap.addAttribute("listEmployee", employeeService.getListEmployee());
		return logService.getLog(idEmployee, dateTime);

	}
}