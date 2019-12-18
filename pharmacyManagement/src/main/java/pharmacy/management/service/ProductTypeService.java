package pharmacy.management.service;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TProductType;

public interface ProductTypeService {
	List<TProductType> getListProductType();
}