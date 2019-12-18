package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.ProductForm;

public interface CustomersRepository extends BaseRepository<TCustomer, Integer> {
	
	List<TCustomer> getListCustomer();
	
	void registerCustomer(CustomerForm customerForm);
}
