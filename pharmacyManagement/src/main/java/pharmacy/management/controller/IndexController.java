package pharmacy.management.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.SingleReponseBean;
import pharmacy.management.bean.WebUtils;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;

@Controller
public class IndexController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderProductService orderProductService;

	private SingleReponseBean singleReponse;

	// Get index
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String register(ModelMap modelMap, HttpServletRequest request, Principal principal) {
		modelMap.addAttribute("productGroups", productGroupService.getListProductGroup());
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		String role = WebUtils.getRole(loginedUser);
		if (role.equals("ROLE_ADMIN") || role.equals("ROLE_CUSTOMER")) {
			modelMap.addAttribute("QL", 1);
		} else {
			modelMap.addAttribute("QL", 0);
		}
		if (request.getSession().getAttribute("user_name") == null) {
			modelMap.addAttribute("user_name", userInfo);
			request.getSession().setAttribute("user_name", userInfo);
		} else {
			modelMap.addAttribute("user_name", request.getSession().getAttribute("user_name"));
		}
		if (request.getSession().getAttribute("idLogin") == null) {
			request.getSession().setAttribute("idLogin",
					String.valueOf(customerService.findUserAccount(userInfo).get(0).getId()));
		}
		return "index";
	}

	@GetMapping("/getProductGroup")
	@ResponseBody
	public SingleReponseBean getProductGroup(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProductGroup> listProduct = productGroupService.getListProductGroup();
		singleReponseBean.settProductGroup(listProduct);
		return singleReponseBean;

	}

	@GetMapping("/getIndex")
	@ResponseBody
	public SingleReponseBean getIndex(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProduct> listProduct = productGroupService.getListProductGroupById("", 1);
		List<TProduct> listProductOffer = new ArrayList<TProduct>();
		for (int i = 0; i <= 3; i++) {
			DecimalFormat format = new DecimalFormat("0.000");
			String price = format.format(Double.parseDouble(listProduct.get(i).getPrice_sell()));
//			double x= Double.parseDouble(price);
			listProduct.get(i).setPrice_sell(price);
			listProductOffer.add(listProduct.get(i));

		}
		singleReponseBean.settProduct(listProductOffer);
		return singleReponseBean;

	}

	@GetMapping("/getProductIndex")
	@ResponseBody
	public SingleReponseBean getProductIndex(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProduct> listProduct = productGroupService.getListProductInIndex();
		List<TProduct> listProductOffer = new ArrayList<TProduct>();
		for (int i = 0; i <= 7; i++) {
			DecimalFormat format = new DecimalFormat("0.000");
			String price_sell = format.format(Double.parseDouble(listProduct.get(i).getPrice_sell()));
			listProduct.get(i).setPrice_sell(price_sell);
			listProductOffer.add(listProduct.get(i));

		}
		singleReponseBean.settProduct(listProductOffer);
		return singleReponseBean;

	}

	// Detail Product
	@RequestMapping(value = { "/detail/{id}" }, method = RequestMethod.GET)
	public String detailProduct(@PathVariable("id") String id, ModelMap modelMap, HttpServletRequest request) {
		if (!id.equals("null")) {
			List<TProduct> listProduct = productService.getProductDisplay(Integer.parseInt(id));
			modelMap.addAttribute("name", listProduct.get(0).getName());
			modelMap.addAttribute("note", listProduct.get(0).getNote());
			modelMap.addAttribute("id", listProduct.get(0).getId());
			modelMap.addAttribute("description", listProduct.get(0).getDescription());
			modelMap.addAttribute("weight", listProduct.get(0).getWeight());
			modelMap.addAttribute("color", listProduct.get(0).getColor());
			modelMap.addAttribute("picture", listProduct.get(0).getPicture());
			modelMap.addAttribute("picture1", listProduct.get(0).getPicture1());
			modelMap.addAttribute("picture2", listProduct.get(0).getPicture2());
			DecimalFormat format = new DecimalFormat("0.000");
			modelMap.addAttribute("price_sell", format.format(Double.parseDouble(listProduct.get(0).getPrice_sell())));
			modelMap.addAttribute("productGroups", productGroupService.getListProductGroup());
			request.getSession().setAttribute("listProductRelated", productGroupService.getListProductRelated(id));
			List<TProduct> list = productGroupService.getListProductRelated(id);
			List<TProduct> listItem1 = new ArrayList<TProduct>();
			List<TProduct> listItem2 = new ArrayList<TProduct>();
			List<TProduct> listItem3 = new ArrayList<TProduct>();
			for (int i = 0; i < list.size(); i++) {
				if (i <= 3) {
					TProduct tProduct = new TProduct();
					list.get(i).setPrice_sell(format.format(Double.parseDouble(list.get(i).getPrice_sell())));
					BeanUtils.copyProperties(list.get(i), tProduct);
					listItem1.add(tProduct);
				}
				if (i <= 7) {
					TProduct tProduct = new TProduct();
					list.get(i).setPrice_sell(format.format(Double.parseDouble(list.get(i).getPrice_sell())));
					BeanUtils.copyProperties(list.get(i), tProduct);
					listItem2.add(tProduct);
				}
				if (i <= 11) {
					TProduct tProduct = new TProduct();
					list.get(i).setPrice_sell(format.format(Double.parseDouble(list.get(i).getPrice_sell())));
					BeanUtils.copyProperties(list.get(i), tProduct);
					listItem3.add(tProduct);
				}
			}
			modelMap.addAttribute("listItem1", listItem1);
			modelMap.addAttribute("listItem2", listItem2);
			modelMap.addAttribute("listItem3", listItem3);
		}
		return "detailProduct";
	}

	@GetMapping("/getListRelated")
	@ResponseBody
	public SingleReponseBean getListRelated(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		@SuppressWarnings("unchecked")
		List<TProduct> list = (List<TProduct>) request.getSession().getAttribute("listProductRelated");
		singleReponseBean.settProduct(list);
		singleReponse = new SingleReponseBean();
		singleReponse.settProduct(list);
		return singleReponseBean;
	}

	// ::::::
	@GetMapping("/getDataListProductr")
	@ResponseBody
	public SingleReponseBean detailProductGroup(@RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProduct> list = productGroupService.getListProductGroupById(id, 0);
		for (int i = 0; i < list.size(); i++) {
			DecimalFormat format = new DecimalFormat("0.000");
			String price_sell = format.format(Double.parseDouble(list.get(i).getPrice_sell()));
			list.get(i).setPrice_sell(price_sell);
		}
		singleReponseBean.settProduct(list);
		singleReponse = new SingleReponseBean();
		singleReponse.settProduct(list);
		return singleReponseBean;
	}

	@GetMapping("/getDataListProductx")
	@ResponseBody
	public SingleReponseBean detailProductGroupx(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		return singleReponse;
	}

	@RequestMapping(value = { "/listProduct" }, method = RequestMethod.GET)
	public String listProduct(HttpServletRequest request) {

		return "listProduct";
	}

	void loadWeb(ModelMap modelMap) {

		// list 3 product
		List<TProductGroup> listPopular = new ArrayList<TProductGroup>();
		if (productGroupService.getListProductGroup().size() > 3) {
			listPopular.add(productGroupService.getListProductGroup().get(0));
			listPopular.add(productGroupService.getListProductGroup().get(1));
			listPopular.add(productGroupService.getListProductGroup().get(2));
		}
		modelMap.addAttribute("listPopular", listPopular);

		// list top product
		modelMap.addAttribute("listTop", productGroupService.getListProductGroup());
	}

	// this is function empty cart
	@RequestMapping(value = { "/emptyCart" }, method = RequestMethod.GET)
	public String emptyCart(HttpServletRequest request) {
		if (request.getSession().getAttribute("idLogin") != null) {
			List<CartBean> listProduct = orderProductService
					.getListCart((String) request.getSession().getAttribute("idLogin"), 0);
			for (CartBean cartBean : listProduct) {
				orderProductService.deleteCart(String.valueOf(cartBean.getId()));
			}
		}
		return "redirect:/index";
	}

	@GetMapping("/loadName")
	@ResponseBody
	public String loadName(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		if (request.getSession().getAttribute("user_name") == null) {
			return "";
		}
		return (String) request.getSession().getAttribute("user_name");

	}

	@GetMapping("/searchWithName")
	@ResponseBody
	public String searchWithName(@RequestParam String searchNameIndex, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		request.getSession().setAttribute("nameSearchFromIndex", searchNameIndex);
		request.getSession().setAttribute("idProductGroup", null);
		return null;
	}
}
