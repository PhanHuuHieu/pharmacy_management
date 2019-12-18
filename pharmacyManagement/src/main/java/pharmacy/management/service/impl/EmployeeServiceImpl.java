package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.respository.EmployeeRepository;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductService;
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
}
