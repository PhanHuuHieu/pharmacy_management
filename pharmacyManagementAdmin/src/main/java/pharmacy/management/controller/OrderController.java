package pharmacy.management.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.LogService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;

@Controller
public class OrderController {
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LogService logService;
	@Autowired
	private ProductGroupService productGroupService;

	@GetMapping("/getListOrder")
	@ResponseBody
	public List<OrderBean> getListOrderProduct(@RequestParam String customerId, @RequestParam String employeeId,
			@RequestParam String dateOrder) {
		OrderProductForm orderProductForm = new OrderProductForm(Integer.parseInt(customerId),
				Integer.parseInt(employeeId), dateOrder);
		return orderProductService.getListSearchOrderProduct(orderProductForm);
	}

	@GetMapping("/detailOrderAdmin")
	@ResponseBody
	public List<OrderCustomerBean> detailOrderAdmin(@RequestParam String idOrder) {
		// Truy xuat thong tin tai day
		List<OrderCustomerBean> listO = orderProductService.getOrderDetail(idOrder);
		String name = "";
		for (String value : orderProductService.getListOrderProduct(String.valueOf(listO.get(0).getId()))) {
			name += value + ",";
		}
		listO.get(0).setNameProducts(name.substring(0, name.length() - 1));
		return listO;
	}

	@RequestMapping(value = { "/updateStatusOrder" }, method = RequestMethod.POST)
	public String personLisst(@ModelAttribute("orderCustomerBean") OrderCustomerBean orderCustomerBean,
			ModelMap modelMap, HttpServletRequest request) {

		orderProductService.updateStatusOrderAdmin(orderCustomerBean);
		return "redirect:/order";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String order(@ModelAttribute("orderReportForm") OrderReportForm orderProductForm,
			@ModelAttribute("orderCustomerBean") OrderCustomerBean orderCustomerBean, Principal principal,
			ModelMap modelMap, HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		modelMap.addAttribute("listEmployee", employeeService.getListEmployee());
		modelMap.addAttribute("listCustomer", customerService.getListCustomer());
		modelMap.addAttribute("listProductGroup", productGroupService.getListProductGroup());
		CommonContanst.displayUsername(modelMap, principal);

		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Đơn Hàng.");
		return "order";
	}
}
