package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.PurchaseBean;
import pharmacy.management.entity.TAddress;

public interface AddressRepository extends BaseRepository<TAddress, Integer> {
	List<PurchaseBean> getAddress(String idLogin);

	void addAddress(PurchaseBean address, String idLogin, int status);

	void insertAddress(String idLogin);
}
