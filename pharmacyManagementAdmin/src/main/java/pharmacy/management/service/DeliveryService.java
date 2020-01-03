package pharmacy.management.service;

import java.util.List;

import pharmacy.management.entity.TDelivery;

public interface DeliveryService {
	void insertDelivery(String idOrder);

	List<TDelivery> getListDelivery(String idOrder);
}
