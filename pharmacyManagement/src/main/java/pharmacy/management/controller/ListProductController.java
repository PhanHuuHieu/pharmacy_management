package pharmacy.management.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.management.bean.ResponsePage;
import pharmacy.management.entity.TProduct;
import pharmacy.management.service.ProductGroupService;
@Controller
public class ListProductController {
	@Autowired
	private ProductGroupService productGroupService;
	
	// Get index
	@GetMapping("/getDataListProduct")
	@ResponseBody
	public String detailProductGroup(@RequestParam String id,HttpServletRequest request,
            HttpServletResponse response,ModelMap modelMap) {
		request.getSession().setAttribute("idProductGroup", id);
		request.getSession().setAttribute("listProductGroup", null);
		return "";
	}
	@GetMapping("/paginationListProduct")
	@ResponseBody
	public ResponsePage detailProductGroupx(@RequestParam String page,
			@RequestParam String resultUnit,@RequestParam String resultSup,
			@RequestParam String nameSearch,@RequestParam String status,
			HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
		if(status.equals("1"))
		{
			request.getSession().setAttribute("listProductGroup", null);
		}
		String[] searchUnit=resultUnit.split(",");
		String[] searchSup=resultSup.split(",");
		String id=(String) request.getSession().getAttribute("idProductGroup");
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("listProductGroup");
		int pagesize = 9;
		//List<TProduct> list = productGroupService.getListProductGroupById(id,0);
		if(request.getSession().getAttribute("nameSearchFromIndex")!=null) {
			nameSearch = (String) request.getSession().getAttribute("nameSearchFromIndex");
		}
		List<TProduct> list = productGroupService.getListSearch(id, searchUnit, searchSup,nameSearch);
		for(int i=0;i<list.size();i++) {
			DecimalFormat format = new DecimalFormat("0.000");
			String price=format.format(Double.parseDouble(list.get(i).getPrice_sell()));
			list.get(i).setPrice_sell(price);
		}
		request.getSession().setAttribute("nameSearchFromIndex", null);
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = Integer.parseInt(page) - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("listProductGroup", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 9, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		
//		modelMap.addAttribute("beginIndex", begin);
//		modelMap.addAttribute("endIndex", end);
		ResponsePage responsePage = new ResponsePage();
		responsePage.setPages(pages);
		responsePage.setCurrentIndex(current);
		responsePage.setTotalPageCount(totalPageCount);
		responsePage.setBeginIndex(begin);
		responsePage.setEndIndex(end);
		return responsePage;
	}
}
