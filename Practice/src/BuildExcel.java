import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

class BuildExcel {
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private String excelFilePath = "";
	int rowCount = 0;
	// Constructor
	BuildExcel(String excelFilePath){
		// Create excel workbook and sheet
		 workbook = new XSSFWorkbook();
		 sheet = workbook.createSheet("General_Obligations_1988JulOct");
		 this.excelFilePath = excelFilePath;
		 addColumnNames();
	}
	// Add Row data
	public void addRow(Data data){
		 
		 Row row = sheet.createRow(++rowCount);
		 row.setHeightInPoints((5*sheet.getDefaultRowHeightInPoints()));
	     addCells(data, row);
	        
	}
	// Add cells for each column
	
	public void addCells(Data data, Row row){
		// Add Date
		Cell cell = row.createCell(1);
	    cell.setCellValue(data.date);
	    // Add Time
	    cell = row.createCell(2);
	    cell.setCellValue(data.time);
	    // Add Sale
	    cell = row.createCell(3);
	    cell.setCellValue(data.sale);
	    // Add Rating
	    cell = row.createCell(4);
	    cell.setCellValue(data.rating);
	    // Add Amount
	    cell = row.createCell(5);
	    cell.setCellValue(data.amount);
	    // Add issue
	    cell = row.createCell(6);
	    CellStyle cs = workbook.createCellStyle();
	    cs.setWrapText(true);
	    cell.setCellStyle(cs);
	    cell.setCellValue(data.issue);
	    // Add population1
	    cell = row.createCell(7);
	    cell.setCellStyle(cs);
	    cell.setCellValue(data.pop1);
	    // Add population2
	    cell = row.createCell(8);
	    cell.setCellStyle(cs);
	    cell.setCellValue(data.pop2);
	    // Add AssessedValue11
	    cell = row.createCell(9);
	    cell.setCellStyle(cs);
	    cell.setCellValue(data.assessVal1);
	    // Add AssessedValue12
	    cell = row.createCell(10);
	    cell.setCellStyle(cs);
	    cell.setCellValue(data.assessVal2);
	    // Add Market Percent
	    cell = row.createCell(11);
	    cell.setCellStyle(cs);
	    cell.setCellValue(data.market_perc);
	    // Add NetDirect1
	    cell = row.createCell(12);
	    cell.setCellValue(data.netDir1);
	    // Add OverallDebt
	    cell = row.createCell(13);
	    cell.setCellValue(data.overallDebt);
	    // Add OverallPC
	    cell = row.createCell(14);
	    cell.setCellValue(data.overallPC);
	    // Add OverallAV
	    cell = row.createCell(15);
	    cell.setCellValue(data.overallAV);
	    // Add OverallMV
	    cell = row.createCell(16);
	    cell.setCellValue(data.overallMV);
	    // Add comment
	    cell = row.createCell(17);
	    cell.setCellValue(data.comment);
	    	
	}
	
	public void addColumnNames(){
		Data colData = new Data();
		colData.date = "Date";
		colData.time = "Time";
		colData.sale = "Sale";
		colData.rating = "Rating";
		colData.amount = "Amount";
		colData.issue = "Issue: (Country) State";
		colData.pop1 = "Population1";
		colData.pop2 = "Population2";
		colData.assessVal1 = "AssessedValue1";
		colData.assessVal2 = "AssessedValue2";
		colData.market_perc = "MarketPercent";
		colData.netDir1 = "NetDirect1";
		colData.overallDebt = "OverallDebt";
		colData.overallPC = "OverallPC";
		colData.overallAV = "OverallAV";
		colData.overallMV = "OverallMV";
		colData.comment= "Comment";
		
		addRow(colData);
	}
	
	public void generateOutput(){
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
		sheet.autoSizeColumn(9);
		sheet.autoSizeColumn(10);
		sheet.autoSizeColumn(11);
		sheet.autoSizeColumn(12);
		sheet.autoSizeColumn(13);
		sheet.autoSizeColumn(14);
		sheet.autoSizeColumn(15);
		sheet.autoSizeColumn(16);
		sheet.autoSizeColumn(17);
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
            workbook.close();
        }
     catch(IOException e){
    	 e.printStackTrace();
     }
	}
	
	
	
}
