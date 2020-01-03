package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.AccountBean;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.RegisterForm;

public interface CustomersRepository extends BaseRepository<TCustomer, Integer> {

	List<TCustomer> getListCustomer();

	List<AccountBean> gettListAccount(String username, String email, String phoneNumber);

	void registerCustomer(RegisterForm registerForm);

	List<TCustomer> getIdSocial(String id);

	void insertCustomer(String name, String id_social);

	List<TCustomer> findUserAccount(String userName);

	void blockAccount(String idAccount, String check);

	void deleteAccount(String idAccount);

	List<TCustomer> getAccountWithEmail(String email);

	List<TCustomer> getAccountWithIdLogin(String idLogin);

	void updatePasswordNew(String password, String idLogin);

	void editInforAccount(CustomerForm customerForm, String idLogin);

	List<TCustomer> getCustomerWithBarcode(String barcode);

	void updatePointCustomerLoyal(String idLogin, double totalMoney);
}
