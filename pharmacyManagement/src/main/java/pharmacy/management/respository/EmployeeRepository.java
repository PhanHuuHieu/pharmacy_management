package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.ProductForm;

public interface EmployeeRepository extends BaseRepository<TEmployee, Integer> {
	
	List<TEmployee> getListEmployee();
	
	String getNameEmployee(int id);
}
