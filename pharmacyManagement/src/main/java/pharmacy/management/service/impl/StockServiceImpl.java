package pharmacy.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockExportForm;
import pharmacy.management.form.StockForm;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.StockRepository;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductService;
import pharmacy.management.service.StockService;
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	@Override
	public void getNameProductFromStock(String stock_name, List<ReponseBean> listData) {
		stockRepository.getNameProductFromStock(stock_name, listData);
	}

	@Override
	public void getValueProductFromStock(String stock_name, List<ReponseBean> listData) {
		stockRepository.getValueProductFromStock(stock_name, listData);
	}

	@Override
	public List<TStock> getListStock(TStock stockForm) {
		return stockRepository.getListStock(stockForm);
	}

	@Override
	public List<StockBean> getStock(String id) {
		return stockRepository.getStock(id);
	}

	@Override
	public void deleteStock(String id) {
		stockRepository.deleteStock(id);
	}

	@Override
	public void insertStock(TStock stockBean) {
		stockRepository.insertStock(stockBean);
	}

	@Override
	public void updateStock(TStock stockBean) {
		stockRepository.updateStock(stockBean);
	}

	@Override
	public long countIdStock(int id) {
		return stockRepository.countIdStock(id);
	}

	@Override
	public List<StockBean> getListExport(StockExportForm stockExportForm) {
		return stockRepository.getListExport(stockExportForm);
	}
}
