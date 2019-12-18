package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TProductType;
import pharmacy.management.entity.TSupplier;

public interface SupplierRepository extends BaseRepository<TSupplier, Integer> {
	List<TSupplier> getListSupplier();
}
