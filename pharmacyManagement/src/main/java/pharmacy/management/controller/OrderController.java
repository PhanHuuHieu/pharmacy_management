package pharmacy.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.StockService;
@Controller
public class OrderController {
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StockService stockService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductGroupService productGroupService;
	
	@PostMapping("/getListOrder")
	@ResponseBody
	public List<OrderBean> getListOrderProduct(@Valid @RequestBody OrderProductForm orderProductForm) {
		return orderProductService.getListSearchOrderProduct(orderProductForm);
	}
	@RequestMapping(value = { "/linkExportReport" }, method = RequestMethod.GET)
    public String linkExportReport(@ModelAttribute("orderReportForm") OrderReportForm orderProductForm, ModelMap modelMap,HttpServletRequest request) {
		modelMap.addAttribute("listEmployee", employeeService.getListEmployee());
		modelMap.addAttribute("listCustomer", customerService.getListCustomer());
		modelMap.addAttribute("listProductGroup", productGroupService.getListProductGroup());
		return "order";
	}
}
