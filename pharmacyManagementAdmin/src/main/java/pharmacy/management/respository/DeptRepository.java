package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.DeptBean;
import pharmacy.management.entity.TDept;
import pharmacy.management.form.DeptForm;

public interface DeptRepository extends BaseRepository<TDept, Integer> {

	List<DeptBean> getListDept(DeptForm deptForm);

	List<DeptBean> getDept(String id);

	void deleteDept(String id);

	void insertDept(TDept tDept);

	void updateDept(TDept tDept);

	long countIdDept(int id);

	List<DeptBean> getListPayDept(String dateNow);
}
