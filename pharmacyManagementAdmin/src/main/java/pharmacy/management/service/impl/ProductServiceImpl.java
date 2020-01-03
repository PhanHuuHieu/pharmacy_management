package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.form.ProductForm;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void insertProduct(TProduct tproduct) {
		productRepository.insertProduct(tproduct);
	}

	@Override
	public List<TProduct> getProduct(int idProduct) {
		return productRepository.getProduct(idProduct);
	}

	@Override
	public long countIdProduct(int idProduct) {
		return productRepository.countIdProduct(idProduct);
	}

	@Override
	public void updateProduct(TProduct tproduct) {
		productRepository.updateProduct(tproduct);

	}

	@Override
	public List<ProductBean> getProductSearch(ProductForm productForm) {
		return productRepository.getProductSearch(productForm);
	}

	@Override
	public void THUXINHDEP(String id, String label) {
		productRepository.THUXINHDEP(id, label);
	}

	@Override
	public void deleteProduct(String id) {
		productRepository.deleteProduct(id);
	}

	@Override
	public List<TProduct> getProductDisplay(int idProduct) {
		return productRepository.getProductDisplay(idProduct);
	}

}
