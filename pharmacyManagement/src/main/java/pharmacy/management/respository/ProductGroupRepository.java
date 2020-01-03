package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;

public interface ProductGroupRepository extends BaseRepository<TProductGroup, Integer> {
	List<TProductGroup> getListProductGroup();

	List<TProductGroup> getListTopProductGroup();

	List<TProduct> getListProductGroupById(String id, int newProduct);

	List<TProduct> getListSearch(String id, String[] searchUnit, String[] searchSup, String nameSearch);

	List<TProduct> getListProductInIndex();

	String getNameProductGroup(int id);

	List<TProduct> getListProductRelated(String id);
}
