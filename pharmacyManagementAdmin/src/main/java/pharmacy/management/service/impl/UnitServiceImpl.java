package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TUnit;
import pharmacy.management.respository.UnitRepository;
import pharmacy.management.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {

	@Autowired
	private UnitRepository unitRepository;

	@Override
	public List<TUnit> getListUnit() {
		return unitRepository.getListUnit();
	}

}
