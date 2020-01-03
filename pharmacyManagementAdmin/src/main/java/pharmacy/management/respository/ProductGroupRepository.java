package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TProductGroup;

public interface ProductGroupRepository extends BaseRepository<TProductGroup, Integer> {
	List<TProductGroup> getListProductGroup();

	String getNameProductGroup(int id);
}
