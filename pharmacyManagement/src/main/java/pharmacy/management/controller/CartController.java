package pharmacy.management.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javassist.expr.NewArray;
import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.SingleReponseBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
@Controller
public class CartController {
	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private OrderProductService orderProductService;
	
	
	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
    public String register(ModelMap modelMap,HttpServletRequest request) {
		return "checkout";
	}
	@GetMapping("/getDataCart")
	@ResponseBody
	public SingleReponseBean getDataCart(HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<CartBean> listProduct = orderProductService.getListCart("1");
		singleReponseBean.settOrder(listProduct);
		return singleReponseBean;
		
	}
	@GetMapping("/deleteCart")
	@ResponseBody
	public String deleteCart( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		orderProductService.deleteCart(id);
		return "";
	}
	@GetMapping("/addCart")
	@ResponseBody
	public List<CartBean> addCart( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		if(!id.equals("-1"))
		{
			List<CartBean> list = orderProductService.getListCart("2");
			if(list.size()>0)
			{
				orderProductService.addCart(list.get(0).getIdOrder(), id, 0);
				orderProductService.updateMoneyCart(list.get(0).getIdOrder(),id);
			}
			else
			{
				int idO = orderProductService.insertOrderCart("2");
				orderProductService.addCart(String.valueOf(idO), id, 0);
				orderProductService.updateMoneyCart(String.valueOf(idO),id);
			}
		}
		
		return orderProductService.getListCart("2");
	}
}
