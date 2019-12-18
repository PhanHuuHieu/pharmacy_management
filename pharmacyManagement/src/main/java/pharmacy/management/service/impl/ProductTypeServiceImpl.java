package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TProductType;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.ProductTypeRepository;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.ProductTypeService;
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public List<TProductType> getListProductType() {
		return productTypeRepository.getListProductType();
	}
	
}