package pharmacy.management.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.SingleReponseBean;
import pharmacy.management.service.OrderProductService;

@Controller
public class CartController {
	@Autowired
	private OrderProductService orderProductService;

	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public String register(ModelMap modelMap, HttpServletRequest request) {
		modelMap.addAttribute("user_name", request.getSession().getAttribute("user_name"));
		return "checkout";
	}

	@GetMapping("/getDataCart")
	@ResponseBody
	public SingleReponseBean getDataCart(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		if (request.getSession().getAttribute("idLogin") == null) {
			/// singleReponseBean.settOrder("");
			return singleReponseBean;
		}

		List<CartBean> listProduct = orderProductService
				.getListCart((String) request.getSession().getAttribute("idLogin"), 0);
		for (int i = 0; i < listProduct.size(); i++) {
			DecimalFormat format = new DecimalFormat("0.000");
			String price = format.format(Double.parseDouble(listProduct.get(i).getPrice_sell()));
			listProduct.get(i).setPrice_sell(price);
		}
		singleReponseBean.settOrder(listProduct);
		return singleReponseBean;

	}

	@GetMapping("/deleteCart")
	@ResponseBody
	public String deleteCart(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		orderProductService.deleteCart(id);
		return "";
	}

	@GetMapping("/updateCart")
	@ResponseBody
	public String updateCart(@RequestParam String id, @RequestParam String amount, @RequestParam String idPro,
			HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException {
		// update SL product
		orderProductService.updateStatusOrder(id, amount);
		// List<CartBean> list = orderProductService.getListCart("2");
		// orderProductService.updateMoneyCart(list.get(0).getIdOrder(),idPro,Integer.parseInt(amount));

		return "";
	}

	@GetMapping("/addCart")
	@ResponseBody
	public List<CartBean> addCart(@RequestParam String id, @RequestParam String status, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		if (request.getSession().getAttribute("idLogin") == null) {
			return null;
		} else {
			if (!id.equals("-1") && status.equals("1")) {
				List<CartBean> list = orderProductService
						.getListCart((String) request.getSession().getAttribute("idLogin"), 0);
				if (list.size() > 0) {
					orderProductService.addCart(list.get(0).getIdOrder(), id, 0);
					orderProductService.updateMoneyCart(list.get(0).getIdOrder(), id, 1, 0);
				} else {
					int idO = orderProductService
							.insertOrderCart((String) request.getSession().getAttribute("idLogin"));
					orderProductService.addCart(String.valueOf(idO), id, 0);
					orderProductService.updateMoneyCart(String.valueOf(idO), id, 1, 0);
				}
			}
			List<CartBean> list = new ArrayList<CartBean>();
			list = orderProductService.getListCart((String) request.getSession().getAttribute("idLogin"), 0);
			if (list.size() != 0) {
				double price = (double) Math.round(Double.parseDouble(list.get(0).getTotal_money()) * 100) / 100;
				DecimalFormat format = new DecimalFormat("0.000");
				String total_price_sell = format.format(price);
				list.get(0).setTotal_money(total_price_sell);
				orderProductService.updateMoneyCart(list.get(0).getIdOrder(), "", 0, price);
			}
			return list;
		}

	}
}
