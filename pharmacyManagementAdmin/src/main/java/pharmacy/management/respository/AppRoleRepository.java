package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TAppRole;

public interface AppRoleRepository extends BaseRepository<TAppRole, Integer> {
	List<TAppRole> getNameRole(String userId);
}
