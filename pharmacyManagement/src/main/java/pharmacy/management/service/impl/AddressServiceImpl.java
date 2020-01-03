package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TDelivery;
import pharmacy.management.respository.DeliveryRepository;
import pharmacy.management.service.DeliveryService;

@Service
public class AddressServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	public void insertDelivery(String idOrder) {
		deliveryRepository.insertDelivery(idOrder);
	}

	@Override
	public List<TDelivery> getListDelivery(String idOrder) {
		return deliveryRepository.getListDelivery(idOrder);
	}
}
