package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TUnit;

public interface UnitRepository extends BaseRepository<TUnit, Integer> {
	List<TUnit> getListUnit();
}
