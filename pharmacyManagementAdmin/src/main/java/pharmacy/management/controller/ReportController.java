package pharmacy.management.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pharmacy.management.bean.ChartBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.StockExportForm;
import pharmacy.management.service.EmployeeService;
import pharmacy.management.service.LogService;
import pharmacy.management.service.OrderProductService;
import pharmacy.management.service.ProductGroupService;
import pharmacy.management.service.StockService;

@Controller
public class ReportController {
	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StockService stockService;
	@Autowired
	private LogService logService;

	@RequestMapping(value = { "/linkStatistic" }, method = RequestMethod.GET)
	public String linkStatistic(ModelMap modelMap, HttpServletRequest request, Principal principal) {
		if (request.getSession().getAttribute("idLogin") == null) {
			return "redirect:/login";
		}
		ChartBean objChart = new ChartBean();
		// List<ReponseBean> list = new ArrayList<ReponseBean>();

		// ChartColumn: get ra tên nhóm sp và số lượng bán đc
		objChart.setListChartColumn(new ArrayList<ReponseBean>());
		orderProductService.getListNameGroupProduct("", objChart.getListChartColumn());
		orderProductService.getSumAmountListNameGroupProduct("", objChart.getListChartColumn());

		// ChartCircle: get ra tên nhóm sp và số lượng hàng tồn
		objChart.setListChartCircle(new ArrayList<ReponseBean>());
		stockService.getNameProductFromStock("", objChart.getListChartCircle());
		stockService.getValueProductFromStock("", objChart.getListChartCircle());

		// ChartLine: get ra tháng và số lượng bán được
		objChart.setListChartLine(new ArrayList<ReponseBean>());
		orderProductService.getListMonthDate("", objChart.getListChartLine());
		orderProductService.getValueFinance("", objChart.getListChartLine());
		CommonContanst.displayUsername(modelMap, principal);

		// Save log login into admin page
		logService.insertLog((String) request.getSession().getAttribute("idLogin"),
				"Truy cập vào trang Quản Lý Thống kê.");
		return "statistic";
	}

	@RequestMapping(value = { "/exportReport" }, method = RequestMethod.POST)
	public String exportReport(@ModelAttribute("orderProductForm") OrderReportForm orderProductForm, ModelMap modelMap,
			HttpServletRequest request) throws IOException {
		List<OrderBean> listReportOrderProduct = orderProductService.findListReportOrderProduct(orderProductForm);
		List<ReportOrderProduct> listValueOrderProduct = orderProductService.getValueOrderProduct(orderProductForm);
		for (int i = 0; i < listValueOrderProduct.size(); i++) {
			ReportOrderProduct rp = listValueOrderProduct.get(i);
			OrderBean or = listReportOrderProduct.get(i);
			rp.setStt(i + 1);
			rp.setNameProduct(or.getNameProduct());
			rp.setTax(or.getTax());
		}
		if (!orderProductForm.getNameEmployee().equals("-1")) {
			orderProductForm.setNameEmployee(
					employeeService.getNameEmployee(Integer.parseInt(orderProductForm.getNameEmployee())));
		}
		if (!orderProductForm.getGroupProduct().equals("-1")) {
			orderProductForm.setGroupProduct(
					productGroupService.getNameProductGroup(Integer.parseInt(orderProductForm.getGroupProduct())));
		}
		if (orderProductForm.getNameFile().equals("")) {
			orderProductForm.setNameFile("Undefine");
		}
		exportSalesReport(listValueOrderProduct, orderProductForm);
		return "redirect:/order";
	}

	@RequestMapping(value = { "/exportReportStock" }, method = RequestMethod.POST)
	public String exportReportStock(@ModelAttribute("stockExportForm") StockExportForm stockExportForm,
			ModelMap modelMap, HttpServletRequest request) throws IOException {
		exportStockReport(stockService.getListExport(stockExportForm), stockExportForm);
		return "redirect:/stock";
	}

	public HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.RED.getIndex());
		font.setFontHeightInPoints((short) 16);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}

	public HSSFCellStyle createStyleForTitleSub(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 13);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}

	public HSSFCellStyle createStyleForTitleTable(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 10);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}

	public HSSFCellStyle createStyleForTitleSubLeft(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 13);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.LEFT);
		return style;
	}

	public HSSFCellStyle createStyleForTitleSubRight(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 13);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.RIGHT);
		return style;
	}

	public void exportSalesReport(List<ReportOrderProduct> list, OrderReportForm orderProductForm) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Report sale");

		int rownum = 10;
		int range = 7;
		int rowTitle = 3;
		int colTitle = 8;
		Cell cell;

		// Tittle
		setMerge(sheet, rowTitle, rowTitle, colTitle, colTitle + 4, false);
		cell = sheet.createRow(rowTitle).createCell(colTitle, CellType.STRING);
		cell.setCellValue("BÁO CÁO BÁN HÀNG PHARMACY");
		cell.setCellStyle(createStyleForTitle(workbook));

		// Date
		setMerge(sheet, rowTitle + 1, rowTitle + 1, colTitle + 1, colTitle + 3, false);
		cell = sheet.createRow(rowTitle + 1).createCell(colTitle + 1, CellType.STRING);
		cell.setCellValue(orderProductForm.getDate());
		cell.setCellStyle(createStyleForTitleSub(workbook));

		if (!orderProductForm.getNameEmployee().equals("-1")) {
			// NhanVien
			setMerge(sheet, rowTitle + 3, rowTitle + 3, colTitle, colTitle + 4, false);
			cell = sheet.createRow(rowTitle + 3).createCell(colTitle, CellType.STRING);
			cell.setCellValue("Nhân viên: " + orderProductForm.getNameEmployee());
			cell.setCellStyle(createStyleForTitleSubLeft(workbook));
		}

		if (!orderProductForm.getGroupProduct().equals("-1")) {
			// LoaiHang
			setMerge(sheet, rowTitle + 4, rowTitle + 4, colTitle, colTitle + 4, false);
			cell = sheet.createRow(rowTitle + 4).createCell(colTitle, CellType.STRING);
			cell.setCellValue("Loại hàng: " + orderProductForm.getGroupProduct());
			cell.setCellStyle(createStyleForTitleSubLeft(workbook));
		}

		// DonVi
		setMerge(sheet, rowTitle + 5, rowTitle + 5, colTitle + 4, colTitle + 5, false);
		cell = sheet.createRow(rowTitle + 5).createCell(colTitle + 4, CellType.STRING);
		cell.setCellValue("Đơn vị: VNĐ");
		cell.setCellStyle(createStyleForTitleSubRight(workbook));

		Row row = sheet.createRow(rownum);
		// Title table
		/*--------------------------------------------*/
		// STT
		cell = row.createCell(range + 0, CellType.STRING);
		cell.setCellValue("STT");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TenLoai
		cell = row.createCell(range + 1, CellType.STRING);
		cell.setCellValue("Tên loại");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// SoLuong
		cell = row.createCell(range + 2, CellType.STRING);
		cell.setCellValue("Số lượng");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TienBan
		cell = row.createCell(range + 3, CellType.STRING);
		cell.setCellValue("Tiền bán");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TienHang
		cell = row.createCell(range + 4, CellType.STRING);
		cell.setCellValue("Tiền hàng");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// ThueVAT
		cell = row.createCell(range + 5, CellType.STRING);
		cell.setCellValue("Thuế VAT");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// ThanhTien
		cell = row.createCell(range + 6, CellType.STRING);
		cell.setCellValue("Thành tiền");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		/*--------------------------------------------*/

		// Data table
		for (ReportOrderProduct order : list) {
			rownum++;
			row = sheet.createRow(rownum);

			// STT
			cell = row.createCell(range + 0, CellType.STRING);
			cell.setCellValue(order.getStt());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// NameEmployee
			cell = row.createCell(range + 1, CellType.NUMERIC);
			cell.setCellValue(order.getNameProduct());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// Amount
			cell = row.createCell(range + 2, CellType.NUMERIC);
			cell.setCellValue(order.getSumAmountProduct());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// PriceSell
			cell = row.createCell(range + 3, CellType.NUMERIC);
			cell.setCellValue(order.getSumPriceSellProduct());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// PriceOrginal
			cell = row.createCell(range + 4, CellType.NUMERIC);
			cell.setCellValue(order.getSumPriceOrginal());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// Tax
			cell = row.createCell(range + 5, CellType.NUMERIC);
			cell.setCellValue(order.getTax());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// TotalMoney
			cell = row.createCell(range + 6, CellType.NUMERIC);
			cell.setCellValue(order.getSumTotalMoney());
			sheet.autoSizeColumn(cell.getColumnIndex());
		}
		rownum = 10;
		PropertyTemplate pt = new PropertyTemplate();
		pt.drawBorders(new CellRangeAddress(rownum, rownum + list.size(), range, range + 6), BorderStyle.THIN,
				BorderExtent.ALL);
		pt.applyBorders(sheet);

		File file = new File("C:/demo/" + orderProductForm.getNameFile() + ".xls");
		file.getParentFile().mkdirs();

		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
	}

	public void exportStockReport(List<StockBean> list, StockExportForm stockExportForm) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Report stock");

		int rownum = 10;
		int range = 7;
		int rowTitle = 3;
		int colTitle = 8;
		Cell cell;

		// Tittle
		setMerge(sheet, rowTitle, rowTitle, colTitle, colTitle + 4, false);
		cell = sheet.createRow(rowTitle).createCell(colTitle, CellType.STRING);
		cell.setCellValue("BÁO CÁO KHO HÀNG PHARMACY");
		cell.setCellStyle(createStyleForTitle(workbook));

		// Date
		setMerge(sheet, rowTitle + 1, rowTitle + 1, colTitle + 1, colTitle + 3, false);
		cell = sheet.createRow(rowTitle + 1).createCell(colTitle + 1, CellType.STRING);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		cell.setCellValue(formatter.format(date));
		cell.setCellStyle(createStyleForTitleSub(workbook));

		if (!stockExportForm.getName_stock_export().equals("")) {
			// NhanVien
			setMerge(sheet, rowTitle + 3, rowTitle + 3, colTitle, colTitle + 4, false);
			cell = sheet.createRow(rowTitle + 3).createCell(colTitle, CellType.STRING);
			cell.setCellValue("Nhà kho: " + stockExportForm.getName_stock_export());
			cell.setCellStyle(createStyleForTitleSubLeft(workbook));
		}

		if (!stockExportForm.getType_form_export().equals("-1")) {
			// TypeForm
			setMerge(sheet, rowTitle + 5, rowTitle + 5, colTitle + 4, colTitle + 5, false);
			cell = sheet.createRow(rowTitle + 5).createCell(colTitle + 4, CellType.STRING);
			if (stockExportForm.getType_form_export().equals("1")) {
				cell.setCellValue("Loại phiếu: Nhập");
			} else {
				cell.setCellValue("Loại phiếu: Xuất");
			}

			cell.setCellStyle(createStyleForTitleSubRight(workbook));
		}

		Row row = sheet.createRow(rownum);
		// Title table
		/*--------------------------------------------*/
		// ID
		cell = row.createCell(range + 0, CellType.STRING);
		cell.setCellValue("ID");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TypeForm
		cell = row.createCell(range + 1, CellType.STRING);
		cell.setCellValue("Loại phiếu");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// NameProduct
		cell = row.createCell(range + 2, CellType.STRING);
		cell.setCellValue("Ngày lập phiếu");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// T
		cell = row.createCell(range + 3, CellType.STRING);
		cell.setCellValue("Tên mặt hàng");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TienHang
		cell = row.createCell(range + 4, CellType.STRING);
		cell.setCellValue("Trạng thái");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TienHang
		cell = row.createCell(range + 5, CellType.STRING);
		cell.setCellValue("Loại");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		// TienHang
		cell = row.createCell(range + 6, CellType.STRING);
		cell.setCellValue("Tên NV");
		cell.setCellStyle(createStyleForTitleTable(workbook));
		/*--------------------------------------------*/

		// Data table
		for (StockBean order : list) {
			rownum++;
			row = sheet.createRow(rownum);

			// STT
			cell = row.createCell(range + 0, CellType.STRING);
			cell.setCellValue(order.getId());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// NameEmployee
			cell = row.createCell(range + 1, CellType.NUMERIC);
			if (order.getType_form().equals("1")) {
				cell.setCellValue("Nhập");
			} else {
				cell.setCellValue("Xuất");
			}

			sheet.autoSizeColumn(cell.getColumnIndex());

			// Amount
			cell = row.createCell(range + 2, CellType.NUMERIC);
			cell.setCellValue(order.getFk_product_id());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// PriceSell
			cell = row.createCell(range + 3, CellType.NUMERIC);
			cell.setCellValue(order.getDate());
			sheet.autoSizeColumn(cell.getColumnIndex());

			// PriceOrginal
			cell = row.createCell(range + 4, CellType.NUMERIC);
			if (order.getStatus().equals("1")) {
				cell.setCellValue("Mới");
			} else if (order.getStatus().equals("2")) {
				cell.setCellValue("Đang xử lý");
			} else if (order.getStatus().equals("3")) {
				cell.setCellValue("Hoàn thành");
			} else {
				cell.setCellValue("Hủy bỏ");
			}
			sheet.autoSizeColumn(cell.getColumnIndex());
			// PriceSell
			cell = row.createCell(range + 5, CellType.NUMERIC);
			cell.setCellValue(order.getType());
			sheet.autoSizeColumn(cell.getColumnIndex());
			// PriceSell
			cell = row.createCell(range + 6, CellType.NUMERIC);
			cell.setCellValue(order.getEmployeeName());
			sheet.autoSizeColumn(cell.getColumnIndex());
		}
		rownum = 10;
		PropertyTemplate pt = new PropertyTemplate();
		pt.drawBorders(new CellRangeAddress(rownum, rownum + list.size(), range, range + 6), BorderStyle.THIN,
				BorderExtent.ALL);
		pt.applyBorders(sheet);

		File file = new File("C:/demo/" + stockExportForm.getName_file_export() + ".xls");
		file.getParentFile().mkdirs();

		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
	}

	protected void setMerge(Sheet sheet, int numRow, int untilRow, int numCol, int untilCol, boolean border) {
		CellRangeAddress cellMerge = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
		sheet.addMergedRegion(cellMerge);
		if (border) {
			setBordersToMergedCells(sheet, cellMerge);
		}

	}

	protected void setBordersToMergedCells(Sheet sheet, CellRangeAddress rangeAddress) {
		RegionUtil.setBorderTop(BorderStyle.MEDIUM, rangeAddress, sheet);
		RegionUtil.setBorderLeft(BorderStyle.MEDIUM, rangeAddress, sheet);
		RegionUtil.setBorderRight(BorderStyle.MEDIUM, rangeAddress, sheet);
		RegionUtil.setBorderBottom(BorderStyle.MEDIUM, rangeAddress, sheet);
	}
}
