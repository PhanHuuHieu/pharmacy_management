package pharmacy.management.service;

import java.util.List;

import pharmacy.management.entity.TProduct;

public interface ProductService {

	List<TProduct> getProductDisplay(int idProduct);
}
