package pharmacy.management.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.entity.TDelivery;
import pharmacy.management.service.DeliveryService;
import pharmacy.management.service.OrderProductService;

@Controller
public class DeliveryController {
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private DeliveryService deliveryService;

	@RequestMapping(value = { "/listOrder" }, method = RequestMethod.GET)
	public String listOrder(ModelMap modelMap, HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		List<OrderCustomerBean> list = new ArrayList<OrderCustomerBean>();
		List<OrderCustomerBean> listO = orderProductService
				.getListOrderCustomer((String) request.getSession().getAttribute("idLogin"));
		for (int i = 0; i < listO.size(); i++) {
			String name = "";
			List<String> listD = orderProductService.getListOrderProduct(String.valueOf(listO.get(i).getId()));
			for (String value : listD) {
				name += value + ",";
			}
			if (!name.equals("")) {
				name = name.substring(0, name.length() - 1);
			}
			OrderCustomerBean orderCustomerBean = new OrderCustomerBean(listO.get(i).getId(),
					listO.get(i).getDate_start(), name);
			list.add(orderCustomerBean);
		}
		modelMap.addAttribute("listOrder", list);
		return "listOrder";
	}

	@RequestMapping(value = { "/detailOrder/{id}" }, method = RequestMethod.GET)
	public String detailOrder(@PathVariable("id") int id, ModelMap modelMap, HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		List<CartBean> list = orderProductService.getListCart((String) request.getSession().getAttribute("idLogin"),
				id);
		if (list.get(0).getIs_pay_pal().equals("1")) {
			modelMap.addAttribute("payment", "Thanh toán với payPal");
		} else {
			modelMap.addAttribute("payment", "Thanh toán khi nhận hàng");
		}
		double total_money = 0;
		DecimalFormat format = new DecimalFormat("0.000");
		for (int i = 0; i < list.size(); i++) {
			CartBean cartBean = list.get(i);
			String price = format.format(Double.parseDouble(cartBean.getPrice_sell()));
			String product_price = format.format(Double.parseDouble(cartBean.getProduct_price()));
			total_money += Double.parseDouble(cartBean.getPrice_sell()) * cartBean.getAmount();
			cartBean.setPrice_sell(price);
			cartBean.setProduct_price(product_price);
		}
		total_money = (double) Math.round(total_money * 100) / 100;
		String total_price_sell = format.format(total_money);
		modelMap.addAttribute("total_money", total_price_sell);
		modelMap.addAttribute("listOrder", list);
		// Display status about order
		List<TDelivery> listDel = deliveryService.getListDelivery(String.valueOf(id));
		modelMap.addAttribute("date_start", listDel.get(0).getDate_start());
		modelMap.addAttribute("date_end", listDel.get(0).getDate_end());
		modelMap.addAttribute("date_pay", listDel.get(0).getDate_pay());
		modelMap.addAttribute("date_delivery", listDel.get(0).getDate_delivery());
		modelMap.addAttribute("status", listDel.get(0).getStatus());
		return "detailOrder";
	}
}
