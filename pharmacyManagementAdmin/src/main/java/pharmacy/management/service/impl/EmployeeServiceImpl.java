package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TEmployee;
import pharmacy.management.form.EmployeeForm;
import pharmacy.management.respository.EmployeeRepository;
import pharmacy.management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<TEmployee> getListEmployee() {
		return employeeRepository.getListEmployee();
	}

	@Override
	public String getNameEmployee(int id) {
		return employeeRepository.getNameEmployee(id);
	}

	@Override
	public List<TEmployee> getAccountWithIdLogin(String idLogin) {
		return employeeRepository.getAccountWithIdLogin(idLogin);
	}

	@Override
	public void editInforAccount(EmployeeForm customerForm, String idLogin) {
		employeeRepository.editInforAccount(customerForm, idLogin);
	}

	@Override
	public List<TEmployee> findUserAccount(String userName) {
		return employeeRepository.findUserAccount(userName);
	}
}
