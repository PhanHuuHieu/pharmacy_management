package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TProduct;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<TProduct> getProductDisplay(int idProduct) {
		return productRepository.getProductDisplay(idProduct);
	}

}
