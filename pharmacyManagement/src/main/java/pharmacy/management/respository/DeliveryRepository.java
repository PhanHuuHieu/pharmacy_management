package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TDelivery;

public interface DeliveryRepository extends BaseRepository<TDelivery, Integer> {
	void insertDelivery(String idOrder);

	List<TDelivery> getListDelivery(String idOrder);
}
