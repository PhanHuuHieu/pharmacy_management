package pharmacy.management.respository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.entity.TUnit;
import pharmacy.management.respository.UnitRepository;

@Repository
public class UnitRepositoryImpl extends BaseRepositoryImpl<TUnit, Integer> implements UnitRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TUnit> getListUnit() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT unit.* ");
		sql.append("FROM         UNIT unit ");

		Query query = entityManager.createNativeQuery(sql.toString(), TUnit.class);
		List<TUnit> listUnit = query.getResultList();
		return listUnit;
	}

}
