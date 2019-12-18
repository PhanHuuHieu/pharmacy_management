package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.DeptBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TDept;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.DeptForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockForm;
import pharmacy.management.respository.DeptRepository;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.StockRepository;
import pharmacy.management.service.DeptService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.StockService;
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptRepository deptRepository;
	
	@Override
	public List<DeptBean> getListDept(DeptForm deptForm) {
		return deptRepository.getListDept(deptForm);
	}

	@Override
	public List<DeptBean> getDept(String id) {
		return deptRepository.getDept(id);
	}

	@Override
	public void deleteDept(String id) {
		deptRepository.deleteDept(id);
	}

	@Override
	public void insertDept(TDept tDept) {
		deptRepository.insertDept(tDept);
	}

	@Override
	public void updateDept(TDept tDept) {
		deptRepository.updateDept(tDept);
	}

	@Override
	public long countIdDept(int id) {
		return deptRepository.countIdDept(id);
	}

	@Override
	public List<DeptBean> getListPayDept(String dateNow) {
		return deptRepository.getListPayDept(dateNow);
	}
}
