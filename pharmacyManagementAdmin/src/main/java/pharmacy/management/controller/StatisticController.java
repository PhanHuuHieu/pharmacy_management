package pharmacy.management.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
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

import pharmacy.management.bean.ReponseBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.StockService;

@Controller
public class StatisticController {
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private StockService stockService;

	@GetMapping("/getDataChartColumn")
	@ResponseBody
	public List<ReponseBean> getDataChartColumn(@RequestParam String monthSearch, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {

		List<ReponseBean> listChartColumn = new ArrayList<ReponseBean>();
		if (monthSearch.length() == 1 || monthSearch.length() == 2) {
			orderProductService.getListNameGroupProduct(monthSearch, listChartColumn);
			orderProductService.getSumAmountListNameGroupProduct(monthSearch, listChartColumn);
		} else if (monthSearch.length() == 3) {
			orderProductService.getQuy(monthSearch, listChartColumn);
			orderProductService.getValueQuy(monthSearch, listChartColumn);
		} else {
			orderProductService.getYear(monthSearch, listChartColumn);
			orderProductService.getValueYear(monthSearch, listChartColumn);
		}

		return listChartColumn;

	}

	@RequestMapping(value = { "/chartColumn" }, method = RequestMethod.GET)
	public String chartColumn(HttpServletRequest request, ModelMap modelMap, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		CommonContanst.displayUsername(modelMap, principal);
		return "chartColumn";
	}

	@GetMapping("/getDataChartLine")
	@ResponseBody
	public List<ReponseBean> getDataChartLine(@RequestParam String year, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws NumberFormatException, IOException {

		List<ReponseBean> listChartLine = new ArrayList<ReponseBean>();
		orderProductService.getListMonthDate(year, listChartLine);
		orderProductService.getValueFinance(year, listChartLine);
		return listChartLine;

	}

	@RequestMapping(value = { "/chartLine" }, method = RequestMethod.GET)
	public String chartLine(HttpServletRequest request, ModelMap modelMap, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		List<ReponseBean> listYear = new ArrayList<ReponseBean>();
		for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= Integer.parseInt(CommonContanst.YEAR_START); i--) {
			listYear.add(new ReponseBean(String.valueOf(i)));
		}
		modelMap.addAttribute("listYear", listYear);
		CommonContanst.displayUsername(modelMap, principal);
		return "chartLine";
	}

	@GetMapping("/getDataChartCircle")
	@ResponseBody
	public List<ReponseBean> getDataChartCircle(@RequestParam String nameStock, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {

		List<ReponseBean> listChartCircle = new ArrayList<ReponseBean>();
		stockService.getNameProductFromStock(nameStock, listChartCircle);
		stockService.getValueProductFromStock(nameStock, listChartCircle);
		return listChartCircle;

	}

	@RequestMapping(value = { "/chartCircle" }, method = RequestMethod.GET)
	public String chartCircle(HttpServletRequest request, ModelMap modelMap, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		CommonContanst.displayUsername(modelMap, principal);
		return "chartCircle";
	}

}
