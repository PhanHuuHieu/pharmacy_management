package pharmacy.management.service;

import java.util.List;

import pharmacy.management.entity.TProductGroup;

public interface ProductGroupService {
	List<TProductGroup> getListProductGroup();

	String getNameProductGroup(int id);
}
