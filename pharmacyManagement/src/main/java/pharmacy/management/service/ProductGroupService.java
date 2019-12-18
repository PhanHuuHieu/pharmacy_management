package pharmacy.management.service;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;

public interface ProductGroupService {
	List<TProductGroup> getListProductGroup();
	
	List<TProductGroup> getListTopProductGroup();
	
	List<TProduct> getListProductGroupById(String id,int newProduct);
	
	String getNameProductGroup(int id);
	
	List<TProduct> getListProductInIndex();
	
	List<TProduct> getListProductRelated(String id);
}
