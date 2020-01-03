package pharmacy.management.service;

import java.util.List;

import pharmacy.management.bean.LogBean;

public interface LogService {
	List<LogBean> getLog(String idEmployee, String dateTime);

	void insertLog(String idEmp, String content);
}
