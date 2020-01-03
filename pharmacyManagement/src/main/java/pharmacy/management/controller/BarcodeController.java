package pharmacy.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.entity.TCustomer;
import pharmacy.management.service.CustomerService;

@Controller
public class BarcodeController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/getCustomerWithBarcode")
	@ResponseBody
	public List<TCustomer> getListOrderProduct(@RequestParam String barcode, HttpServletRequest request) {
		List<TCustomer> list = customerService.getCustomerWithBarcode(barcode);
		if (list.size() > 0) {
			request.getSession().setAttribute("flagBarcode", 1);
		} else {
			request.getSession().setAttribute("flagBarcode", 0);
		}
		return list;
	}

	@RequestMapping(value = { "/barcode" }, method = RequestMethod.GET)
	public String barcode(ModelMap modelMap, HttpServletRequest request) {
		modelMap.addAttribute("user_name", request.getSession().getAttribute("user_name"));
		return "barcode";
	}
}
