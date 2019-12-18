package pharmacy.management.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pharmacy.management.bean.ChartBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TProduct;
import pharmacy.management.form.ProductForm;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.ProductTypeService;
import pharmacy.management.service.StockService;
import pharmacy.management.service.SupplierService;
import pharmacy.management.service.UnitService;
@Controller
public class StatisticController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StockService stockService;
	
	@GetMapping("/getDataChartColumn")
	@ResponseBody
	public List<ReponseBean> getDataChartColumn(@RequestParam String param,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		
		List<ReponseBean> listChartColumn = new ArrayList<ReponseBean>();
		if(param.length()==1||param.length()==2)
		{
			orderProductService.getListNameGroupProduct(param, listChartColumn);
			orderProductService.getSumAmountListNameGroupProduct(param, listChartColumn);
		}
		else if(param.length()==3)
		{
			orderProductService.getQuy(param, listChartColumn);
			orderProductService.getValueQuy(param, listChartColumn);
		} 
		else
		{
			orderProductService.getYear(param, listChartColumn);
			orderProductService.getValueYear(param, listChartColumn);
		}
		
		return listChartColumn;
		
	}
	@RequestMapping(value = { "/chartColumn" }, method = RequestMethod.GET)
    public String chartColumn(ModelMap modelMap) {
		
		return "chartColumn";
    }
	
	@GetMapping("/getDataChartLine")
	@ResponseBody
	public List<ReponseBean> getDataChartLine(@RequestParam String year,HttpServletRequest request,
            HttpServletResponse response,ModelMap modelMap) throws NumberFormatException, IOException {
		
		List<ReponseBean> listChartLine = new ArrayList<ReponseBean>();
		orderProductService.getListMonthDate(year, listChartLine);
		orderProductService.getValueFinance(year, listChartLine);
		return listChartLine;
		
	}
	
	@RequestMapping(value = { "/chartLine" }, method = RequestMethod.GET)
    public String chartLine(ModelMap modelMap) {
		List<ReponseBean> listYear = new ArrayList<ReponseBean>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=Integer.parseInt(CommonContanst.YEAR_START);i--)
		{
			listYear.add( new ReponseBean(String.valueOf(i)));
		}
		modelMap.addAttribute("listYear", listYear);
		return "chartLine";
    }
	
	@GetMapping("/getDataChartCircle")
	@ResponseBody
	public List<ReponseBean> getDataChartCircle(@RequestParam String nameStock,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		
		List<ReponseBean> listChartCircle = new ArrayList<ReponseBean>();
 		stockService.getNameProductFromStock(nameStock,listChartCircle);
 		stockService.getValueProductFromStock(nameStock,listChartCircle);
		return listChartCircle;
		
	}
	
	@RequestMapping(value = { "/chartCircle" }, method = RequestMethod.GET)
    public String chartCircle(ModelMap modelMap) {
		
		return "chartCircle";
    }
	
}
