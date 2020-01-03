package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.DeptBean;
import pharmacy.management.entity.TDept;
import pharmacy.management.form.DeptForm;
import pharmacy.management.respository.DeptRepository;
import pharmacy.management.service.DeptService;

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
