package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TProductType;
import pharmacy.management.entity.TSupplier;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.ProductTypeRepository;
import pharmacy.management.respository.SupplierRepository;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.ProductTypeService;
import pharmacy.management.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<TSupplier> getListSupplier() {
		return supplierRepository.getListSupplier();
	}
	
}
