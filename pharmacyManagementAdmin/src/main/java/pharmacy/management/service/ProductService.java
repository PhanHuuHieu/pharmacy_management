package pharmacy.management.service;

import java.util.List;

import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.form.ProductForm;

public interface ProductService {

	void insertProduct(TProduct tproduct);

	List<TProduct> getProduct(int idProduct);

	List<TProduct> getProductDisplay(int idProduct);

	List<ProductBean> getProductSearch(ProductForm productForm);

	long countIdProduct(int idProduct);

	void updateProduct(TProduct tproduct);

	void deleteProduct(String id);

	void THUXINHDEP(String id, String label);
}
