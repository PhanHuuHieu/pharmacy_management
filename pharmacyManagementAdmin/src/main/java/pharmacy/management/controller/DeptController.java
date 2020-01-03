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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.DeptBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TDept;
import pharmacy.management.form.DeptForm;
import pharmacy.management.service.DeptService;
import pharmacy.management.service.LogService;
import pharmacy.management.service.SupplierService;

@Controller
public class DeptController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private LogService logService;

	@GetMapping("/getDept")
	@ResponseBody
	public List<DeptBean> getStock(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		return deptService.getDept(id);

	}

	@GetMapping("/getListDept")
	@ResponseBody
	public List<DeptBean> getListStock(@RequestParam String nameSupplier, @RequestParam String dateStart,
			@RequestParam String dateEnd) {
		DeptForm deptForm = new DeptForm(nameSupplier, dateStart, dateEnd);
		return deptService.getListDept(deptForm);
	}

	@GetMapping("/deleteDept")
	@ResponseBody
	public void deleteStock(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		deptService.deleteDept(id);
	}

	@RequestMapping(value = { "/addDept" }, method = RequestMethod.POST)
	public String personLisst(@ModelAttribute("deptForm") TDept tDept, ModelMap modelMap, HttpServletRequest request) {
		if (deptService.countIdDept(tDept.getId()) > 0) {
			deptService.updateDept(tDept);
		} else {
			deptService.insertDept(tDept);
		}

		return "redirect:/dept";
	}

	@RequestMapping(value = { "/dept" }, method = RequestMethod.GET)
	public String linkExportReport(@ModelAttribute("deptForm") TDept tDept, ModelMap modelMap, Principal principal,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		modelMap.addAttribute("listSupplier", supplierService.getListSupplier());
		CommonContanst.displayUsername(modelMap, principal);

		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Công nợ.");
		return "dept";
	}
}
