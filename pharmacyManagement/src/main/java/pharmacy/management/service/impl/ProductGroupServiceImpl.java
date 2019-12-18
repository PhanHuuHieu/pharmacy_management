package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
@Service
public class ProductGroupServiceImpl implements ProductGroupService {

	@Autowired
	private ProductGroupRepository productGroupRepository;

	@Override
	public List<TProductGroup> getListProductGroup() {
		return productGroupRepository.getListProductGroup();
	}

	@Override
	public String getNameProductGroup(int id) {
		return productGroupRepository.getNameProductGroup(id);
	}

	@Override
	public List<TProduct> getListProductGroupById(String id,int newProduct) {
		return productGroupRepository.getListProductGroupById(id,newProduct);
	}

	@Override
	public List<TProductGroup> getListTopProductGroup() {
		return productGroupRepository.getListTopProductGroup();
	}

	@Override
	public List<TProduct> getListProductInIndex() {
		return productGroupRepository.getListProductInIndex();
	}

	@Override
	public List<TProduct> getListProductRelated(String id) {
		return productGroupRepository.getListProductRelated(id);
	}
	
}
