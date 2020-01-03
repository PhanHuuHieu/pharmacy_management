package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.AccountBean;
import pharmacy.management.entity.TCustomer;

public interface CustomersRepository extends BaseRepository<TCustomer, Integer> {

	List<TCustomer> getListCustomer();

	List<AccountBean> gettListAccount(String username, String email, String phoneNumber);

	void blockAccount(String idAccount, String check);

	void deleteAccount(String idAccount);
}
