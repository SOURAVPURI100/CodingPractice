import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class BuildSalesExcel {
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private String excelFilePath = "";
	int rowCount = 0;

	// Constructor
	BuildSalesExcel(String excelFilePath) {
		// Create excel workbook and sheet
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Sales1981");
		this.excelFilePath = excelFilePath;
		addColumnNames();
	}

	// Add Row salesData
	public void addRow(SalesData salesData) {

		Row row = sheet.createRow(rowCount++);
		row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
		addCells(salesData, row);

	}
	// Add cells for each column

	public void addCells(SalesData salesData, Row row) {

		CellStyle cs = workbook.createCellStyle();
		cs.setWrapText(true);
		// Add Rating
		Cell cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.rating);
		// Add date
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.date);
		// Add issuer
		cell = row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.issuer);
		// Add amount
		cell = row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.amount);
		// Add Average
		cell = row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.avg);
		// Add Years
		cell = row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.years);
		// Add year1
		cell = row.createCell(7);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.year1);
		// Add year3
		cell = row.createCell(8);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.year3);
		// Add year6
		cell = row.createCell(9);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.year6);
		// Add year10
		cell = row.createCell(10);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.year10);
		// Add year15
		cell = row.createCell(11);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.year15);
		// Add year20
		cell = row.createCell(12);
		cell.setCellStyle(cs);
		cell.setCellValue(salesData.year20);

	}

	public void addColumnNames() {
		SalesData colsalesData = new SalesData();
		colsalesData.rating = "Rating";
		colsalesData.date = "Date";
		colsalesData.issuer = "Issuer";
		colsalesData.amount = "Amount";
		colsalesData.avg = "Average";
		colsalesData.years = "Years";
		colsalesData.year1 = "Year 1";
		colsalesData.year3 = "Year 3";
		colsalesData.year6 = "Year 6";
		colsalesData.year10 = "Year 10";
		colsalesData.year15 = "Year 15";
		colsalesData.year20 = "Year 20";

		addRow(colsalesData);
	}

	public void generateOutput() {
		// sheet.autoSizeColumn(1);
		// sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		// sheet.autoSizeColumn(4);
		// sheet.autoSizeColumn(5);
		// sheet.autoSizeColumn(6);
		// sheet.autoSizeColumn(7);
		// sheet.autoSizeColumn(8);
		// sheet.autoSizeColumn(9);
		// sheet.autoSizeColumn(10);
		// sheet.autoSizeColumn(11);
		// sheet.autoSizeColumn(12);
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
