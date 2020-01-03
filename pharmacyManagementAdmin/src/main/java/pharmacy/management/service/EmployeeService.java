package pharmacy.management.service;

import java.util.List;

import pharmacy.management.entity.TEmployee;
import pharmacy.management.form.EmployeeForm;

public interface EmployeeService {

	List<TEmployee> getListEmployee();

	String getNameEmployee(int id);

	List<TEmployee> findUserAccount(String userName);

	List<TEmployee> getAccountWithIdLogin(String idLogin);

	void editInforAccount(EmployeeForm customerForm, String idLogin);
}
