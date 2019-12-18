package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.ProductForm;

public interface ProductRepository extends BaseRepository<TProduct, Integer> {
	
	List<ProductBean> getListProduct();
	
    void insertProduct(TProduct tproduct);
    
    List<TProduct> getProduct(int idProduct);
    
    List<TProduct> getProductDisplay(int idProduct);
    
    List<ProductBean> getProductSearch(ProductForm productForm);
    
    long countIdProduct(int idProduct);
    
    void updateProduct(TProduct tproduct);
    
    void deleteProduct(String id);
    
    void THUXINHDEP(String id,String label);
}
