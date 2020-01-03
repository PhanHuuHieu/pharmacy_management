package pharmacy.management.service;

import java.util.List;

import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;

public interface ProductGroupService {
	List<TProductGroup> getListProductGroup();

	List<TProductGroup> getListTopProductGroup();

	List<TProduct> getListProductGroupById(String id, int newProduct);

	List<TProduct> getListSearch(String id, String[] searchUnit, String[] searchSup, String nameSearch);

	String getNameProductGroup(int id);

	List<TProduct> getListProductInIndex();

	List<TProduct> getListProductRelated(String id);
}
