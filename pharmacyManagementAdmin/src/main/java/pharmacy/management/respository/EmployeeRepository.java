package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.form.EmployeeForm;

public interface EmployeeRepository extends BaseRepository<TEmployee, Integer> {

	List<TEmployee> getListEmployee();

	String getNameEmployee(int id);

	List<TEmployee> findUserAccount(String userName);

	List<TEmployee> getAccountWithIdLogin(String idLogin);

	void editInforAccount(EmployeeForm customerForm, String idLogin);
}
