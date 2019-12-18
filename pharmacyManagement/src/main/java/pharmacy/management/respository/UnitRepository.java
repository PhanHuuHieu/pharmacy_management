package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TProductType;
import pharmacy.management.entity.TUnit;

public interface UnitRepository extends BaseRepository<TUnit, Integer> {
	List<TUnit> getListUnit();
}
