package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.service.ProductGroupService;

@Service
public class ProductGroupServiceImpl implements ProductGroupService {

	@Autowired
	private ProductGroupRepository productGroupRepository;

	@Override
	public List<TProductGroup> getListProductGroup() {
		return productGroupRepository.getListProductGroup();
	}

	@Override
	public String getNameProductGroup(int id) {
		return productGroupRepository.getNameProductGroup(id);
	}

}
