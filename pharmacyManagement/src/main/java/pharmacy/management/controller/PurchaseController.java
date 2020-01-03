package pharmacy.management.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.PurchaseBean;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.service.AddressService;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.DeliveryService;
import pharmacy.management.service.OrderProductService;
@Controller
public class PurchaseController {
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private DeliveryService deliveryService;
	
	
	@RequestMapping(value = { "/purchase" }, method = RequestMethod.GET)
    public String purchase(ModelMap modelMap,HttpServletRequest request) {
		if(request.getSession().getAttribute("idLogin")==null)
		{
			return null;
		}
		modelMap.addAttribute("objAddress", addressService.getAddress((String) request.getSession().getAttribute("idLogin")).get(0));
		List<CartBean> listOrder = orderProductService.getListCart((String) request.getSession().getAttribute("idLogin"),0);
		for(int i=0;i<listOrder.size();i++) {
			DecimalFormat format = new DecimalFormat("0.000");
			String price=format.format(Double.parseDouble(listOrder.get(i).getPrice_sell()));
			listOrder.get(i).setPrice_sell(price);
			String product_price = format.format(Double.parseDouble(listOrder.get(i).getProduct_price()));
			listOrder.get(i).setProduct_price(product_price);
		}
		modelMap.addAttribute("listOrder", listOrder);
		//modelMap.addAttribute("total_money", orderProductService.getListCart("2").get(0).getTotal_money());
		// Tính ra xong update vào luôn
		
		double total_money = 0;
		for(int i=0;i<listOrder.size();i++)
		{
			CartBean cartBean = listOrder.get(i);
			total_money += Double.parseDouble(cartBean.getPrice_sell())*cartBean.getAmount();
		}
		total_money = (double) Math.round(total_money * 100) / 100;
		
		
		orderProductService.updateMoneyCart(listOrder.get(0).getIdOrder(), "", 0,total_money);
		DecimalFormat format = new DecimalFormat("0.000");
		String total_price_sell=format.format(total_money);
		modelMap.addAttribute("total_money", total_price_sell);
		modelMap.addAttribute("user_name",request.getSession().getAttribute("user_name"));
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 4);
		Date date = cal.getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
		modelMap.addAttribute("dateHasProduct", dateFormat.format(date));
		
	    return "order-pharmacy";
	}
	@RequestMapping(value = "gotoPurchase", method = RequestMethod.POST)  
	  public String save(PurchaseBean tAddress,ModelMap modelMap,HttpServletRequest request) {  
		if(tAddress.getName().equals("")||tAddress.getPhone_number().equals("")||tAddress.getAddress_detail().equals(""))
		{
			return "redirect:/purchase";
		}
		else
		{
			// Add in table delivery
			List<CartBean> list = orderProductService.getListCart((String) request.getSession().getAttribute("idLogin"),0);
			deliveryService.insertDelivery(list.get(0).getIdOrder());
			
			// Update total_money
			List<TOrderProduct> listOrderProduct = orderProductService.getTotalMoney((String)request.getSession().getAttribute("idLogin"));
	    	request.getSession().setAttribute("total_money", listOrderProduct.get(0).getTotal_money());
			
	    	// Add in table address
	    	addressService.addAddress(tAddress, (String)request.getSession().getAttribute("idLogin"),0);
		    orderProductService.updateStatusOrder((String) request.getSession().getAttribute("idLogin"),"0");
		    
		    // Update point with customer loyal
		    if(request.getSession().getAttribute("flagBarcode") != null) {
		    	 if((int)request.getSession().getAttribute("flagBarcode") == 1) {
				    	customerService.updatePointCustomerLoyal( (String)request.getSession().getAttribute("idLogin"),Double.parseDouble(listOrderProduct.get(0).getTotal_money()));
				    }
		    }
		    
		    if(tAddress.getStatus_pay().equals("2"))
		    {
		    	orderProductService.updateStatusPayment(list.get(0).getIdOrder());
		    	return "redirect:/payment"; 
		    }
		}
	    return "redirect:/successPurchase";  
	  }  
	@RequestMapping(value = "successPurchase", method = RequestMethod.GET)  
	  public String successPurchase(ModelMap modelMap,HttpServletRequest request) { 
		modelMap.addAttribute("user_name",request.getSession().getAttribute("user_name"));
	    return "success-purcharse";  
	  }  
}
