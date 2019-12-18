package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.DeptBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TDept;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.DeptForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockForm;

public interface DeptRepository extends BaseRepository<TDept, Integer> {
	
	List<DeptBean> getListDept(DeptForm deptForm);
	
	List<DeptBean> getDept(String id);
	
	void deleteDept(String id);
	
	void insertDept(TDept tDept);
	
	void updateDept(TDept tDept);
	
	long countIdDept(int id);
	
	List<DeptBean> getListPayDept(String dateNow);
}
