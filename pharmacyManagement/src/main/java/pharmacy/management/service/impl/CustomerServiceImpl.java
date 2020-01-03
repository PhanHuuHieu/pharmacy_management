package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.AccountBean;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.RegisterForm;
import pharmacy.management.respository.CustomersRepository;
import pharmacy.management.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomersRepository customersRepository;

	@Override
	public List<TCustomer> getListCustomer() {
		return customersRepository.getListCustomer();
	}

	@Override
	public void registerCustomer(RegisterForm registerForm) {
		customersRepository.registerCustomer(registerForm);
	}

	@Override
	public List<TCustomer> getIdSocial(String id) {
		return customersRepository.getIdSocial(id);
	}

	@Override
	public void insertCustomer(String name, String id_social) {
		customersRepository.insertCustomer(name, id_social);
	}

	@Override
	public List<TCustomer> findUserAccount(String userName) {
		return customersRepository.findUserAccount(userName);
	}

	@Override
	public List<AccountBean> gettListAccount(String username, String email, String phoneNumber) {
		return customersRepository.gettListAccount(username, email, phoneNumber);
	}

	@Override
	public void blockAccount(String idAccount, String check) {
		customersRepository.blockAccount(idAccount, check);
	}

	@Override
	public void deleteAccount(String idAccount) {
		customersRepository.deleteAccount(idAccount);
	}

	@Override
	public List<TCustomer> getAccountWithEmail(String email) {
		return customersRepository.getAccountWithEmail(email);
	}

	@Override
	public void updatePasswordNew(String password, String idLogin) {
		customersRepository.updatePasswordNew(password, idLogin);
	}

	@Override
	public List<TCustomer> getAccountWithIdLogin(String idLogin) {
		return customersRepository.getAccountWithIdLogin(idLogin);
	}

	@Override
	public void editInforAccount(CustomerForm customerForm, String idLogin) {
		customersRepository.editInforAccount(customerForm, idLogin);
	}

	@Override
	public List<TCustomer> getCustomerWithBarcode(String barcode) {
		return customersRepository.getCustomerWithBarcode(barcode);
	}

	@Override
	public void updatePointCustomerLoyal(String idLogin, double totalMoney) {
		customersRepository.updatePointCustomerLoyal(idLogin, totalMoney);
	}
}
