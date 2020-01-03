package pharmacy.management.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.WebUtils;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TProduct;
import pharmacy.management.form.ProductForm;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.LogService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.SupplierService;
import pharmacy.management.service.UnitService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private LogService logService;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/api")
	@ResponseBody
	public String JSONTypewo(@RequestParam String idPr, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		ReponseBean[] pp1 = mapper.readValue(idPr, ReponseBean[].class);
//		List<ReponseBean> ppl2 = Arrays.asList(mapper.readValue(idPr, ReponseBean[].class));
		for (int i = 0; i <= 7; i++) {
			// ppl2.get(i).getLabel().replace("\\", "/");
			// productService.THUXINHDEP(String.valueOf(i+114),ppl2.get(i).getLabel());
		}
		return "";

	}

	@GetMapping("/getListProduct")
	@ResponseBody
	public List<ProductBean> JSONTypeword(@RequestParam String nameSearch, @RequestParam String productGroup,
			@RequestParam String price_orginal, @RequestParam String price_sell) {
		if (price_orginal.equals("")) {
			price_orginal = "0";
		}
		if (price_sell.equals("")) {
			price_sell = "0";
		}
		ProductForm productForm = new ProductForm();
		productForm.setName(nameSearch);
		productForm.setFk_product_group_id(Integer.parseInt(productGroup));
		productForm.setPrice_orginal(Double.parseDouble(price_orginal));
		productForm.setPrice_sell(price_sell);
		return productService.getProductSearch(productForm);
	}

	@GetMapping("/displayProduct")
	@ResponseBody
	public List<TProduct> JSONTypewords(@RequestParam String idProduct, HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IOException {
		return productService.getProduct(Integer.parseInt(idProduct));

	}

	@GetMapping("/deleteProduct")
	@ResponseBody
	public void JSONType(@RequestParam String idProduct, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, IOException {
		productService.deleteProduct(idProduct);
	}

	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String personList(@ModelAttribute("productForm") TProduct productForm, ModelMap modelMap,
			Principal principal, HttpServletRequest request) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		if (userInfo == null) {
			return "redirect:/login";
		}
		// Gan Username
		// Kiem tra quyen de hien thi
		CommonContanst.displayUsername(modelMap, principal);
		modelMap.addAttribute("listProductGroup", productGroupService.getListProductGroup());
		modelMap.addAttribute("listSupplier", supplierService.getListSupplier());
		modelMap.addAttribute("listUnit", unitService.getListUnit());

		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Sản Phẩm.");

		request.getSession().setAttribute("idLogin",
				String.valueOf(employeeService.findUserAccount(userInfo).get(0).getId()));
		return "product";
	}

	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String redirectProduct(@ModelAttribute("productForm") TProduct productForm, ModelMap modelMap,
			Principal principal, HttpServletRequest request) {
		return "redirect:/product";
	}

	@RequestMapping(value = { "/addProduct" }, method = RequestMethod.POST)
	public String personLisst(@ModelAttribute("productForm") ProductForm productForm, ModelMap modelMap,
			HttpServletRequest request) {

		TProduct tproduct = new TProduct();
		BeanUtils.copyProperties(productForm, tproduct);
		tproduct.setPicture(saveFile(request, productForm.getPicture()));
		tproduct.setPicture1(saveFile(request, productForm.getPicture1()));
		tproduct.setPicture2(saveFile(request, productForm.getPicture2()));
		if (productService.countIdProduct(productForm.getId()) > 0) {
			productService.updateProduct(tproduct);
		} else {
			productService.insertProduct(tproduct);
		}

		return "redirect:/product";
	}

	// file word
	public void hello() throws IOException {
		XWPFDocument document = new XWPFDocument();
		// Create new Paragraph
		XWPFParagraph paragraph1 = document.createParagraph();
		XWPFRun run = paragraph1.createRun();
		run.setText("Paragraph 1: stackjava.com");

		XWPFParagraph paragraph2 = document.createParagraph();
		run = paragraph2.createRun();
		run.setText("Paragraph 2: demo read/write file MS-Word");

		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File("minhlathuday.docx"));
		document.write(out);
		out.close();
		document.close();
	}

	public String saveFile(HttpServletRequest request, MultipartFile[] picture) {
		String nameFile = "";
		String uploadRootPath = request.getServletContext().getRealPath("").replace("webapp",
				"resources\\static\\images");
		File uploadRootDir = new File(uploadRootPath);

		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = picture;
		List<File> uploadedFiles = new ArrayList<File>();

		for (MultipartFile fileData : fileDatas) {
			String name = fileData.getOriginalFilename();
			nameFile = "/images/" + name;
			if (name != null && name.length() > 0) {
				try {
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					uploadedFiles.add(serverFile);
				} catch (Exception e) {
				}
			}
		}
		return nameFile;
	}
}
