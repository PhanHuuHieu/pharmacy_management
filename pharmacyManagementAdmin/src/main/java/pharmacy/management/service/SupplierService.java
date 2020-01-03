package pharmacy.management.service;

import java.util.List;

import pharmacy.management.entity.TSupplier;
import pharmacy.management.form.SupplierForm;

public interface SupplierService {
	List<TSupplier> getListSupplier();

	List<SupplierForm> getListSupplierSearch(SupplierForm supplierForm);

	List<TSupplier> getSupplier(String idSupplier);

	void deleteSupplier(String idSupplier);

	long countIdSupplier(String idSupplier);

	void updateSupplier(TSupplier tSupplier);

	void insertSupplier(TSupplier tSupplier);
}
