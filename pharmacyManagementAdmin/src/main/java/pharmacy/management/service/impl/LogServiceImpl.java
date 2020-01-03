package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.LogBean;
import pharmacy.management.respository.LogRepository;
import pharmacy.management.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;

	@Override
	public List<LogBean> getLog(String idEmployee, String dateTime) {
		return logRepository.getLog(idEmployee, dateTime);
	}

	@Override
	public void insertLog(String idEmp, String content) {
		logRepository.insertLog(idEmp, content);
	}
}
