package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.PurchaseBean;
import pharmacy.management.respository.AddressRepository;
import pharmacy.management.service.AddressService;

@Service
public class DeliveryServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<PurchaseBean> getAddress(String idLogin) {
		return addressRepository.getAddress(idLogin);
	}

	@Override
	public void addAddress(PurchaseBean address, String idLogin, int status) {
		addressRepository.addAddress(address, idLogin, status);
	}
}
