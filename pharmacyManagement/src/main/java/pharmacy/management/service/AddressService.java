package pharmacy.management.service;

import java.util.List;

import pharmacy.management.bean.PurchaseBean;

public interface AddressService {
	List<PurchaseBean> getAddress(String idLogin);

	void addAddress(PurchaseBean address, String idLogin, int status);

	void insertAddress(String idLogin);
}
