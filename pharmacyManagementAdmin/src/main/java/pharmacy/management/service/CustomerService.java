package pharmacy.management.service;

import java.util.List;

import pharmacy.management.bean.AccountBean;
import pharmacy.management.entity.TCustomer;

public interface CustomerService {
	List<TCustomer> getListCustomer();

	List<AccountBean> gettListAccount(String username, String email, String phoneNumber);

	void blockAccount(String idAccount, String check);

	void deleteAccount(String idAccount);
}
