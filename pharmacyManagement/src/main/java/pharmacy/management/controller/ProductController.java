package pharmacy.management.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pharmacy.management.bean.ChartBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.nameClass;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.ProductTypeService;
import pharmacy.management.service.StockService;
import pharmacy.management.service.SupplierService;
import pharmacy.management.service.UnitService;
@Controller
public class ProductController {
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
	@Autowired
	private CustomerService customerService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String getIndex(ModelMap modelMap,HttpServletRequest request) {
		return "index";
	}
	@GetMapping("/api")
	@ResponseBody
	public String JSONTypewo( @RequestParam String idPr,HttpServletRequest request,
            HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ReponseBean[] pp1 = mapper.readValue(idPr, ReponseBean[].class);
		List<ReponseBean> ppl2 = Arrays.asList(mapper.readValue(idPr, ReponseBean[].class));
		for(int i=0;i<=7;i++)
		{
			//ppl2.get(i).getLabel().replace("\\", "/");
			//productService.THUXINHDEP(String.valueOf(i+114),ppl2.get(i).getLabel());
		}
		return "";
		
	}
	@PostMapping("/ha")
	@ResponseBody
	public List<ProductBean> JSONTypeword(@Valid @RequestBody ProductForm productForm) {
		return productService.getProductSearch(productForm);
	}
	@GetMapping("/hi")
	@ResponseBody
	public List<TProduct> JSONTypewords( @RequestParam String idProduct,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		return productService.getProduct(Integer.parseInt(idProduct));
		
	}
	@GetMapping("/deleteProduct")
	@ResponseBody
	public void JSONType( @RequestParam String idProduct,HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException, IOException {
		productService.deleteProduct(idProduct);
	}
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public String personList(@ModelAttribute("productForm") TProduct productForm, ModelMap modelMap) {
		modelMap.addAttribute("listProductGroup", productGroupService.getListProductGroup());
		modelMap.addAttribute("listProductType", productTypeService.getListProductType());
		modelMap.addAttribute("listSupplier", supplierService.getListSupplier());
		modelMap.addAttribute("listUnit", unitService.getListUnit());
		//FIXME
		String x=passwordEncoder.encode("password");
		if(passwordEncoder.matches("password", x))
		{
			System.out.println("Giong nha may ma");
		}
		return "product";
    }
	@RequestMapping(value = { "/addProduct" }, method = RequestMethod.POST)
    public String personLisst(@ModelAttribute("productForm") ProductForm productForm, ModelMap modelMap,HttpServletRequest request) {
	    String nameFile=saveFile(request, productForm);
	    TProduct tproduct = new TProduct();
	    BeanUtils.copyProperties(productForm, tproduct);
	    tproduct.setPicture("/images/"+nameFile);
	    
		if(productService.countIdProduct(productForm.getId())>0)
	    {
	    	productService.updateProduct(tproduct);
	    }
	    else
	    {
	    	productService.insertProduct(tproduct);
	    }
		
		return "redirect:/product";
    }
	
	// file word
	public void hello() throws IOException
	{
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
	public String saveFile(HttpServletRequest request, ProductForm tproduct)
	{
		String nameFile="";
        String uploadRootPath = request.getServletContext().getRealPath("").replace("webapp", "resources\\static\\images");
        File uploadRootDir = new File(uploadRootPath);
        
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = tproduct.getPicture();
        List<File> uploadedFiles = new ArrayList<File>();
 
        for (MultipartFile fileData : fileDatas) {
            String name = fileData.getOriginalFilename();
            nameFile=name;
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
