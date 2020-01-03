package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TSupplier;
import pharmacy.management.form.SupplierForm;
import pharmacy.management.respository.SupplierRepository;
import pharmacy.management.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<TSupplier> getListSupplier() {
		return supplierRepository.getListSupplier();
	}

	@Override
	public List<SupplierForm> getListSupplierSearch(SupplierForm supplierForm) {
		return supplierRepository.getListSupplierSearch(supplierForm);
	}

	@Override
	public List<TSupplier> getSupplier(String idSupplier) {
		return supplierRepository.getSupplier(idSupplier);
	}

	@Override
	public void deleteSupplier(String idSupplier) {
		supplierRepository.deleteSupplier(idSupplier);
	}

	@Override
	public long countIdSupplier(String idSupplier) {
		return supplierRepository.countIdSupplier(idSupplier);
	}

	@Override
	public void updateSupplier(TSupplier tSupplier) {
		supplierRepository.updateSupplier(tSupplier);
	}

	@Override
	public void insertSupplier(TSupplier tSupplier) {
		supplierRepository.insertSupplier(tSupplier);
	}

}
