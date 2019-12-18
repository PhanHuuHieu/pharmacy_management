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

import pharmacy.management.bean.DeptBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TDept;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.DeptForm;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.DeptService;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.StockService;
import pharmacy.management.service.SupplierService;
@Controller
public class DeptController {
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductGroupService productGroupService;
	
	@GetMapping("/getDept")
	@ResponseBody
	public List<DeptBean> getStock( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		return deptService.getDept(id);
		
	}
	
	@PostMapping("/getListDept")
	@ResponseBody
	public List<DeptBean> getListStock(@Valid @RequestBody DeptForm deptForm) {
		return deptService.getListDept(deptForm);
	}
	
	@GetMapping("/deleteDept")
	@ResponseBody
	public void deleteStock( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		deptService.deleteDept(id);
	}
	
	@RequestMapping(value = { "/addDept" }, method = RequestMethod.POST)
    public String personLisst(@ModelAttribute("deptForm") TDept tDept, ModelMap modelMap,HttpServletRequest request) {
		if(deptService.countIdDept(tDept.getId())>0)
	    {
			deptService.updateDept(tDept);
	    }
	    else
	    {
	    	deptService.insertDept(tDept);
	    }
		
		return "redirect:/dept";
    }
	
	@RequestMapping(value = { "/dept" }, method = RequestMethod.GET)
    public String linkExportReport(@ModelAttribute("deptForm") TDept tDept, ModelMap modelMap,HttpServletRequest request) {
		
		modelMap.addAttribute("listSupplier", supplierService.getListSupplier());
		return "dept";
	}
}
