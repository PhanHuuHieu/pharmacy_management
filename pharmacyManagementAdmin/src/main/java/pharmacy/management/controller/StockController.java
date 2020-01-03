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

import pharmacy.management.bean.StockBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.StockExportForm;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.LogService;
import pharmacy.management.service.StockService;

@Controller
public class StockController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StockService stockService;
	@Autowired
	private LogService logService;

	@GetMapping("/getStock")
	@ResponseBody
	public List<StockBean> getStock(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		return stockService.getStock(id);

	}

	@GetMapping("/getListStock")
	@ResponseBody
	public List<TStock> getListStock(@RequestParam String id, @RequestParam String date, @RequestParam String status) {
		if (id.equals("")) {
			id = "0";
		}
		TStock stockForm = new TStock(Integer.parseInt(id), date, status);
		return stockService.getListStock(stockForm);
	}

	@GetMapping("/deleteStock")
	@ResponseBody
	public void deleteStock(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		stockService.deleteStock(id);
	}

	@RequestMapping(value = { "/addStock" }, method = RequestMethod.POST)
	public String personLisst(@ModelAttribute("stockForm") TStock stockBean, ModelMap modelMap,
			HttpServletRequest request) {
		if (stockService.countIdStock(stockBean.getId()) > 0) {
			stockService.updateStock(stockBean);
		} else {
			stockService.insertStock(stockBean);
		}

		return "redirect:/stock";
	}

	@RequestMapping(value = { "/stock" }, method = RequestMethod.GET)
	public String stock(@ModelAttribute("stockForm") TStock stockForm,
			@ModelAttribute("stockExportForm") StockExportForm stockExportForm, ModelMap modelMap, Principal principal,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		modelMap.addAttribute("listEmployee", employeeService.getListEmployee());
		CommonContanst.displayUsername(modelMap, principal);

		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Kho Hàng.");
		return "stock";
	}
}
