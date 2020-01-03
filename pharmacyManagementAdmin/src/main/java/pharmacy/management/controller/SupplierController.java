package pharmacy.management.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TSupplier;
import pharmacy.management.form.SupplierForm;
import pharmacy.management.service.LogService;
import pharmacy.management.service.SupplierService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private LogService logService;

	@GetMapping("/getListSupplier")
	@ResponseBody
	public List<SupplierForm> getListSupplier(@RequestParam String idSearch, @RequestParam String nameSearch,
			@RequestParam String phoneNumberSearch) {
		SupplierForm supplierForm = new SupplierForm(idSearch, phoneNumberSearch, nameSearch);
		return supplierService.getListSupplierSearch(supplierForm);
	}

	@GetMapping("/displaySupplier")
	@ResponseBody
	public List<TSupplier> displaySupplier(@RequestParam String idSupplier, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		return supplierService.getSupplier(idSupplier);

	}

	@GetMapping("/deleteSupplier")
	@ResponseBody
	public void JSONType(@RequestParam String idSupplier, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		supplierService.deleteSupplier(idSupplier);
	}

	@RequestMapping(value = { "/supplier" }, method = RequestMethod.GET)
	public String personList(@ModelAttribute("supplierForm") SupplierForm supplierForm, ModelMap modelMap,
			Principal principal, HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		// Gan Username
		// Kiem tra quyen de hien thi
		CommonContanst.displayUsername(modelMap, principal);
		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Nhà Cung Cấp.");
		return "supplier";
	}

	@RequestMapping(value = { "/addSupplier" }, method = RequestMethod.POST)
	public String personLisst(@ModelAttribute("supplierForm") SupplierForm supplierForm, ModelMap modelMap,
			HttpServletRequest request) {

		TSupplier tsupplier = new TSupplier();
		BeanUtils.copyProperties(supplierForm, tsupplier);
		if (supplierForm.getId().equals("")) {
			supplierForm.setId("0");
		}
		tsupplier.setId(Integer.parseInt(supplierForm.getId()));
		if (supplierService.countIdSupplier(supplierForm.getId()) > 0) {
			supplierService.updateSupplier(tsupplier);
		} else {
			supplierService.insertSupplier(tsupplier);
		}

		return "redirect:/supplier";
	}
}
