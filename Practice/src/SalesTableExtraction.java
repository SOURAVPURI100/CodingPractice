import java.io.*;
import java.util.regex.*;


class SalesData{
	String rating, sale, date, issuer, amount, avg, years,
		year1, year3, year6, year10, year15, year20; 
	
	public SalesData(){
		this.rating = ""; this.sale = ""; this.date = ""; this.issuer =""; this.amount =""; 
		this.avg =""; this.years = ""; this.year1 = ""; this.year3 = ""; 
		this.year6 =""; this.year10 =""; this.year15 =""; this.year20 = ""; 
	}
}
public class SalesTableExtraction {
	
	public static void main(String[] args) {
		 File file = new File("/home/sourav/Documents/Finance/library/1982SalesJanDec.txt");
		 try{
		 BufferedReader in = new BufferedReader(new FileReader(file));
		 String line = "", first_word = "";
		 String[] columns;
		 Pattern p = Pattern.compile("[0-9]+/[0-9]+");
		 Pattern rating = Pattern.compile("[A-Za-z]+");
		 int count =1;
		 
		 // Create SalesData object
		 SalesData SalesData = new SalesData();
		 BuildSalesExcel buildExcel = new BuildSalesExcel("/home/sourav/Documents/Finance/library/1982SalesJanDec.xlsx"); 
		 File_Reading obj = null;
		 while((line = in.readLine()) != null){
			 
			 columns = line.split("[@][@]");	 
			 columns = trimValues(columns);
			 
			 // Check if line contains only "@" symbol, so ignore this line
			 if(!verifyColumnSalesData(columns)){
				 continue;
			 }
			 
			 // Check if this a first line for next column
			 if(columns.length > 0){	 
				 first_word = columns[0];
			 }
			 else{
				 continue;
			 }
			 //Matcher m = p.matcher(first_word);
			 Matcher mRating = rating.matcher(first_word);
			 
			 if(mRating.find() && count > 1){
				 count = 1;	 			 	
				 
				 System.out.print(SalesData.rating+ " ");
				 System.out.print(SalesData.sale+ " ");
				 System.out.print(SalesData.date+ " ");
				 System.out.print(SalesData.issuer+ " ");
				 System.out.print(SalesData.amount+ " ");
				 System.out.print(SalesData.avg+ " ");
				 System.out.print(SalesData.years+ " ");
				 System.out.print(SalesData.year1+ " ");
				 System.out.print(SalesData.year3+ " ");
				 System.out.print(SalesData.year6+ " ");
				 System.out.print(SalesData.year10+ " ");
				 System.out.print(SalesData.year15+ " ");
				 System.out.print(SalesData.year20+ " ");
				 System.out.println();
				
				 buildExcel.addRow(SalesData);
				 SalesData = new SalesData();
			 }
			 
			 
			 if(count == 1){
				 SalesData.rating = columns[0];
				 if(columns.length > 1)
					 SalesData.issuer = getIssuer(columns);
			 }
			 else if (count == 2){	 
				 fillSalesData(SalesData, columns);
			 }
			 else if (count == 3){
				 SalesData.years = fillSalesYears(columns);
			 }	 
			 count++;
			 
		 }
		 in.close();
		 
		 buildExcel.addRow(SalesData);
		 buildExcel.generateOutput();

		 System.out.println("File Generated");	 
		 }
		 
		 catch(Exception e){
			 e.printStackTrace();
		 }

	}
	
	
	public static String[] trimValues(String[] columns){
		String [] retColumns = new String[columns.length];
		
		for(int i=0; i<columns.length; i++){
			retColumns[i] = columns[i].trim();
		}
		
		return retColumns;
	}

	
	public static boolean verifyColumnSalesData(String[] columns){
		for(String s: columns){
			if(!s.equals("") && !s.matches("[@]+"))
				return true;
		}
		return false;
	}
	
	public static String getIssuer(String[] columns){		
		// Fetch columns data from 2 to last		
		
		String issuer = "";
		for(int i = 1; i<columns.length; i++){
			issuer += columns[i];
		}	
		issuer = issuer.replaceAll("[@][@]", "");
		return issuer;
		
	}
	
	public static void fillSalesData(SalesData salesData, String[] columns){
		if(columns.length > 0){
			salesData.date = columns[0];
		}
		if(columns.length > 1){
			salesData.amount = columns[1];
		}
		if(columns.length > 2){
			salesData.avg = columns[2];
		}
		if(columns.length > 3){
			salesData.year1 = columns[3];
		}
		if(columns.length > 4){
			salesData.year3 = columns[4];
		}
		if(columns.length > 5){
			salesData.year6 = columns[5];
		}
		if(columns.length > 6){
			salesData.year10 = columns[6];
		}
		if(columns.length > 7){
			salesData.year15= columns[7];
		}
		if(columns.length > 8){
			salesData.year20= columns[8];
		}
	
	}
	
	public static String fillSalesYears(String [] columns){
		String years  = "";
		for(String s : columns){
			years += s;
		}
		
		years = years.replaceAll("[@][@]", "");
		years = years.trim();
		return years;	
	}

}

