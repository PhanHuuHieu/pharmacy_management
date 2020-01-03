package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.AccountBean;
import pharmacy.management.entity.TCustomer;
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
}
