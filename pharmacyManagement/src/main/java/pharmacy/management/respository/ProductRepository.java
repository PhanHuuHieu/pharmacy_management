package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TProduct;

public interface ProductRepository extends BaseRepository<TProduct, Integer> {

	List<TProduct> getProductDisplay(int idProduct);
}
