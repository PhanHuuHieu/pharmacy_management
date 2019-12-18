package pharmacy.management.service;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;

public interface CustomerService {
	List<TCustomer> getListCustomer();
	
	void registerCustomer(CustomerForm customerForm);
}
