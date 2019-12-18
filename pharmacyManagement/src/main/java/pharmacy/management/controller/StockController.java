package pharmacy.management.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockExportForm;
import pharmacy.management.form.StockForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.StockService;
@Controller
public class StockController {
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
	
	@GetMapping("/getStock")
	@ResponseBody
	public List<StockBean> getStock( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		return stockService.getStock(id);
		
	}
	
	@PostMapping("/getListStock")
	@ResponseBody
	public List<TStock> getListStock(@Valid @RequestBody TStock stockForm) {
		return stockService.getListStock(stockForm);
	}
	
	@GetMapping("/deleteStock")
	@ResponseBody
	public void deleteStock( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		stockService.deleteStock(id);
	}
	
	@RequestMapping(value = { "/addStock" }, method = RequestMethod.POST)
    public String personLisst(@ModelAttribute("stockForm") TStock stockBean, ModelMap modelMap,HttpServletRequest request) {
		if(stockService.countIdStock(stockBean.getId())>0)
	    {
			stockService.updateStock(stockBean);
	    }
	    else
	    {
	    	stockService.insertStock(stockBean);
	    }
		
		return "redirect:/stock";
    }
	
	@RequestMapping(value = { "/stock" }, method = RequestMethod.GET)
    public String linkExportReport(@ModelAttribute("stockForm") TStock stockForm,@ModelAttribute("stockExportForm") StockExportForm stockExportForm, ModelMap modelMap,HttpServletRequest request) {
		OrderProductForm orderProductForm = new OrderProductForm();
		orderProductForm.setDateOrder("");
		orderProductForm.setIdEmployee(-1);
		orderProductForm.setIdCustomer(-1);
		List<OrderBean> listOrder = orderProductService.getListSearchOrderProduct(orderProductForm);
		//modelMap.addAttribute("listOrder", orderProductS);
		modelMap.addAttribute("listEmployee", employeeService.getListEmployee());
		return "stock";
	}
}
