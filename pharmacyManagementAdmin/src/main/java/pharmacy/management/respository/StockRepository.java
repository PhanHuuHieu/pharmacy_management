package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.StockExportForm;

public interface StockRepository extends BaseRepository<TStock, Integer> {

	void getNameProductFromStock(String stock_name, List<ReponseBean> listData);

	void getValueProductFromStock(String stock_name, List<ReponseBean> listData);

	List<TStock> getListStock(TStock stockForm);

	List<StockBean> getStock(String id);

	void deleteStock(String id);

	void insertStock(TStock stockBean);

	void updateStock(TStock stockBean);

	long countIdStock(int id);

	List<StockBean> getListExport(StockExportForm stockExportForm);
}
