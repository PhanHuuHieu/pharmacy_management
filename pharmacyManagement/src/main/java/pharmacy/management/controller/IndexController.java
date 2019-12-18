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
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.SingleReponseBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
@Controller
public class IndexController {
	@Autowired
    private CustomerService customerService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private ProductService productService;
	private SingleReponseBean singleReponse;
	
	// Get index
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
    public String register(ModelMap modelMap,HttpServletRequest request) {
		//loadWeb(modelMap);
		modelMap.addAttribute("productGroups", productGroupService.getListProductGroup());
		return "index";
	}
	@GetMapping("/getProductGroup")
	@ResponseBody
	public SingleReponseBean getProductGroup(HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProductGroup> listProduct = productGroupService.getListProductGroup();
		singleReponseBean.settProductGroup(listProduct);
		return singleReponseBean;
		
	}
	@GetMapping("/getIndex")
	@ResponseBody
	public SingleReponseBean getIndex(HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProduct> listProduct = productGroupService.getListProductGroupById("",1);
		List<TProduct> listProductOffer = new ArrayList<TProduct>();
		for(int i=0;i<=3;i++)
		{
//			DecimalFormat format = new DecimalFormat("0.000");
//			String price=format.format(listProduct.get(i).getPrice_sell());
//			double x= Double.parseDouble(price);
			//listProduct.get(i).setPrice_sell(price);
			listProductOffer.add(listProduct.get(i));
			 
		}
		singleReponseBean.settProduct(listProductOffer);
		return singleReponseBean;
		
	}
	@GetMapping("/getProductIndex")
	@ResponseBody
	public SingleReponseBean getProductIndex(HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		List<TProduct> listProduct = productGroupService.getListProductInIndex();
		List<TProduct> listProductOffer = new ArrayList<TProduct>();
		for(int i=0;i<=7;i++)
		{
			DecimalFormat format = new DecimalFormat("0.000");
			String price_sell=format.format(Double.parseDouble(listProduct.get(i).getPrice_sell()));
			listProduct.get(i).setPrice_sell(price_sell);
			listProductOffer.add(listProduct.get(i));
			 
		}
		singleReponseBean.settProduct(listProductOffer);
		return singleReponseBean;
		
	}
	
	// Detail Product
	@RequestMapping(value = { "/detail/{id}" }, method = RequestMethod.GET)
    public String detailProduct(@PathVariable("id") String id,ModelMap modelMap,HttpServletRequest request) {
		
		List<TProduct> listProduct = productService.getProductDisplay(Integer.parseInt(id));
		modelMap.addAttribute("name", listProduct.get(0).getName());
		modelMap.addAttribute("note", listProduct.get(0).getNote());
		
		modelMap.addAttribute("description", listProduct.get(0).getDescription());
		modelMap.addAttribute("weight", listProduct.get(0).getWeight());
		modelMap.addAttribute("color", listProduct.get(0).getColor());
		modelMap.addAttribute("picture", listProduct.get(0).getPicture());
	      //DecimalFormat format = new DecimalFormat("0.000");
	      modelMap.addAttribute("price_sell", listProduct.get(0).getPrice_sell());
	      modelMap.addAttribute("productGroups", productGroupService.getListProductGroup());
	      modelMap.addAttribute("listProductRelated", productGroupService.getListProductRelated(id));
		return "detailProduct";
	}
	// ::::::
	@GetMapping("/getDataListProduct")
	@ResponseBody
	public SingleReponseBean detailProductGroup( @RequestParam String id,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		SingleReponseBean singleReponseBean = new SingleReponseBean();
		singleReponseBean.settProduct(productGroupService.getListProductGroupById(id,0));
		singleReponse = new SingleReponseBean();
		singleReponse.settProduct(productGroupService.getListProductGroupById(id,0));
		return singleReponseBean;
	}
	@GetMapping("/getDataListProductx")
	@ResponseBody
	public SingleReponseBean detailProductGroupx( HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		return singleReponse;
	}
	@RequestMapping(value = { "/listProduct" }, method = RequestMethod.GET)
	public String listProduct() {
		return "listProduct";
	}
	void loadWeb(ModelMap modelMap)
	{
		
		// list 3 product
		List<TProductGroup> listPopular = new ArrayList<TProductGroup>();
		if(productGroupService.getListProductGroup().size()>3)
		{
			listPopular.add(productGroupService.getListProductGroup().get(0));
			listPopular.add(productGroupService.getListProductGroup().get(1));
			listPopular.add(productGroupService.getListProductGroup().get(2));
		}
		modelMap.addAttribute("listPopular", listPopular);
		
		// list top product
		modelMap.addAttribute("listTop", productGroupService.getListProductGroup());
		
		// list product offer
//		List<TProduct> listProduct = productGroupService.getListProductGroupById("1");
//		List<TProduct> listProductOffer = new ArrayList<TProduct>();
//		for(int i=0;i<=7;i++)
//		{
//			listProductOffer.add(listProduct.get(i));
//		}
//		modelMap.addAttribute("listOffer", listProductOffer);
	}
}
