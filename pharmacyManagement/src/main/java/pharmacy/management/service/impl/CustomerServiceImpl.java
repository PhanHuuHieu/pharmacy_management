package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import pharmacy.management.respository.CustomersRepository;
import pharmacy.management.respository.EmployeeRepository;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.service.CustomerService;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomersRepository customersRepository;

	@Override
	public List<TCustomer> getListCustomer() {
		return customersRepository.getListCustomer();
	}

	@Override
	public void registerCustomer(CustomerForm customerForm) {
		customersRepository.registerCustomer(customerForm);
	}
}
