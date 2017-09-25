import java.io.*;
import java.util.regex.*;

public class GeneralObligations {
	
	public static void main(String[] args) {
		 File file = new File("/home/sourav/Documents/Finance/library/1988JulOct.txt");
		 try{
		 BufferedReader in = new BufferedReader(new FileReader(file));
		 String line = "", first_word = "";
		 Pattern p = Pattern.compile("[0-9]+/[0-9]+");
		 Pattern pYear = Pattern.compile("[1][9][0-9][0-9][: ]");
		 Pattern pPercent = Pattern.compile("[(][0-9][0-9.]");
		 boolean isUnderReview = false;
		 boolean noYearValue = false;
		 boolean negFlag = false;
		 boolean isWeek = false;
		 boolean isWeekTemp = false;
//		 
//		 String date= "", time="", sale ="", rating="", amount="", issue="", pop="", 
//				 net="", comment="";
		 int count =1;
		 
		 // Create Data object
		 Data data = new Data();
		 BuildExcel buildExcel = new BuildExcel("/home/sourav/Documents/Finance/library/1988JulOct.xlsx"); 
		 while((line = in.readLine()) != null){
			 //System.out.println(line);
			 isWeekTemp = false;
			 line = line.trim();
			 
			 // Check if this a first line for next column
			 if(line.length() > 0){	 
				 first_word = firstWord(line);
			 }
			 else{
				 continue;
			 }
			 Matcher m = p.matcher(first_word);
			 
			 if((m.find() || first_word.equals("Wk."))&& count > 1 && first_word.length() <= 5){
				 
				 if(isWeek == true){
					isWeek = false; 
					isWeekTemp =true;
				 }
				 else{
//					 isWeek = false;	 
					 if(first_word.equals("Wk.")){
						 isWeek = true;
					 }
					 count = 1;	 			 
					 buildExcel.addRow(data);
					 data = new Data();		 
					 isUnderReview = false;
					 noYearValue = false;
					 negFlag = false;
				 }
			 }
			 // Line number 1
			 if(count == 1){	 
				 // in the first line of new row check for "Under Review"
				 isUnderReview = checkUnderReview(line);
				// Check for yearly value
				 if(isUnderReview == false){
					 noYearValue = checkYearValue(line, pYear);
				 }
				 
				 fillFirstLineData(data, line, isUnderReview, noYearValue, pYear, isWeek); 
			 }
			 // Line number 2
			 else if (count == 2){
				 // Check if first word == "Neg." in second line
				 if(first_word.equals("Neg.") || first_word.equals("Neg")){
					 negFlag = true;
					 data.time = first_word;
					 fillSecondLineDataNeg(data, line.substring(first_word.length()), isUnderReview, noYearValue, pYear);
					 
				 }
				 else{
//					 System.out.println(line);
					 if(line.length() >= 10){
						 if(isWeekTemp == false){
							 data.time = line.substring(0, 10);
						 }
						 else
						 {
							 data.date = "Wk. of "+line.substring(0, 10);
							 isWeekTemp = false;
						 }
						 line = line.substring(10).trim();
					 }
					// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
					 fillCommonDatafromIssuer(data, line, 2, 0, isUnderReview, noYearValue, pYear);
					 
				 }

			 }
			 // For line number 3
			 else if (count == 3){
				if(negFlag == true){
					line = line.trim();
					// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
					 fillCommonDatafromIssuer(data, line, 3, 0, isUnderReview, noYearValue, pYear);
				}
				else{
					data.sale = first_word;
					fillThirdLineData(data, line.substring(first_word.length()), isUnderReview, noYearValue, pYear);
				}
			 }
			// For line number 4
			 else if(count == 4){
				 line = line.trim();
				// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
				 fillCommonDatafromIssuer(data, line, 4, 0, isUnderReview, noYearValue, pYear);
			 }
			// For line number 5
			 else if(count == 5){
				 line = line.trim();
				// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
				 fillCommonDatafromIssuer(data, line, 5, 0, isUnderReview, noYearValue, pPercent);
			 } 
			 
			 else if(count > 5){
				 data.comment = data.comment + line.trim()+"\n";
			 }
			 
			 count++;
			 
		 }
		 in.close();
		 
		 buildExcel.addRow(data);
		 buildExcel.generateOutput();

		 System.out.println("File Generated");	 
		 }
		 
		 catch(Exception e){
			 e.printStackTrace();
		 }

	}
	
	public static String formed_line(String line, int beg, int end){
		
		if(line.length() >= end)
			return line.substring(beg, end);
		else if(line.length() > beg)
			return line.substring(beg);
		else
			return "";
		
	}
	
	public static String[] trimValues(String[] columns){
		String [] retColumns = new String[columns.length];
		
		for(int i=0; i<columns.length; i++){
			retColumns[i] = columns[i].trim();
		}
		
		return retColumns;
	}
	// Fill Population Assess value into corresponding columns
	public static void fillPopulationData(Data data, int line, String value){
		if(line == 1){
			data.pop1 = eliminateSymbol(value);		
		}
		else if(line == 2){
			data.pop2 = eliminateSymbol(value);	
			
		}
		else if(line == 3){
			data.assessVal1 = eliminateSymbol(value);	
			
		}
		else if(line == 4){
			data.assessVal2= eliminateSymbol(value);	
			
		}
		else if(line == 5){
			data.market_perc= eliminateSymbol(value);	
			
		}
	}
	
	// Fill Net and Overall Debt data into corresponding columns
	public static void fillDebtData(Data data, int line, String value){
		if(line == 1){
			data.netDir1 = eliminateSymbol(value);	
		}
		else if(line == 2){
			data.overallDebt = eliminateSymbol(value);	
			
		}
		else if(line == 3){
			data.overallPC = eliminateSymbol(value);	
			
		}
		else if(line == 4){
			data.overallAV= eliminateSymbol(value);	
			
		}
		else if(line == 5){
			data.overallMV= eliminateSymbol(value);	
			
		}
	}
	
	public static boolean verifyColumnData(String[] columns){
		for(String s: columns){
			if(!s.equals("") && !s.matches("[@]+"))
				return true;
		}
		return false;
	}
	
	public static String eliminateSymbol(String str){
		return str.replaceAll("[@][@]", "");
	}
	
	public static String firstWord(String line){
		String firstWord = "";
		
		for(char c: line.toCharArray()){
			if(c == ' ')
				break;
			firstWord += c;
		}
		return firstWord;
	}
	
	public static boolean checkUnderReview(String line){
		if(line.contains("Under Review") || line.contains("under review"))
			return true;
		return false;
		
	}
	
	public static boolean checkYearValue(String line, Pattern pattern){
		Matcher match = pattern.matcher(line);
		if(match.find())
			return false;
		return true;
		
	}
	
	public static void fillFirstLineData(Data data, String line, boolean isUnderReview, boolean noYearValue, 
										Pattern pattern, boolean isWeek){
		
		int i =0, count = 0;
		String rating = "";
		
		if(isWeek == false){
			// Fill date value 
			while(i < line.length()){
				if(line.charAt(i) == ' ')
					break;
				data.date += line.charAt(i);
				i++;
			}
			
		}
		else{
			line = line.substring(6);
		}
		
		// Look for rating value
		while(i < line.length()){
			if(line.charAt(i) != ' ')
				break;	
			i++;
		}
		// Fill could be rating value upto 7 characters
		while(i < line.length() && count < 7){
			rating += line.charAt(i);
			count++;
			i++;
		}
		rating = rating.trim();
		// Check if valid rating 
		if(rating.equals("A 1") || rating.equals("Baa 1") || rating.equals("Aa") || rating.equals("Ba 1")
			|| rating.equals("A") || rating.equals("#") || rating.equals("*") || rating.equals("Baal")
			|| rating.equals("Baa") || rating.equals("Con.(A)")|| rating.equals("Aa 1") || rating.equals("Con.")
			|| rating.equals("Aaa") || rating.equals("As") || rating.equals("Al")){
			data.rating = rating;
			
		}
		else{
			// Backtrack index "i" if rating not found
			i = i -7;
			
		}
		
		// Look for issuer value
		System.out.println(line);
		while(i < line.length() && i >= 0){
			if(line.charAt(i) != ' ')
				break;	
			i++;
		}
		// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
		fillCommonDatafromIssuer(data, line, 1, i, isUnderReview, noYearValue, pattern);
			
		
	}
	// Fill second lien data
	public static void fillSecondLineDataNeg(Data data, String line, boolean isUnderReview, boolean noYearValue, Pattern pattern){
		int i =0;
		line = line.trim();
		while(i < line.length()){
			if(line.charAt(i) == ' ')
				break;
			data.amount = data.amount + line.charAt(i);
			i++;
		}
		
		line = line.substring(i).trim();
		// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
		fillCommonDatafromIssuer(data, line, 2, 0, isUnderReview, noYearValue, pattern);
		
	}
	
	// Fill third line data
	public static void fillThirdLineData(Data data, String line, boolean isUnderReview, boolean noYearValue, Pattern pattern){
		int i =0;
		line = line.trim();
		while(i < line.length()){
			if(line.charAt(i) == ' ')
				break;
			data.amount = data.amount + line.charAt(i);
			i++;
		}
		
		line = line.substring(i).trim();
		// Data , Line, Line Number, IssuerIndex, isUnderReview, noYearValue
		fillCommonDatafromIssuer(data, line, 3, 0, isUnderReview, noYearValue, pattern);
		
	}
	// Filling Issuer and other data 
	//	Index i represents starting point of issuer
	public static void fillIssuerOtherData(String line, int i, Data data, int lineNumber, Pattern pattern){
		int j = 0;
		String value = "";
		Matcher match = pattern.matcher(line);
		if(match.find()){
		   j = match.start();
		}
//		System.out.println(line);
		data.issue = data.issue + line.substring(i, j).trim()+"\n";
		i =j;
		
//		two consecutive spaces are found
		while(i < line.length() -1){
			if(line.charAt(i) == ' ' && line.charAt(i+1) == ' ')
				break;
			value = value + line.charAt(i);
			i++;
		}
		
		fillPopulationData(data, lineNumber, value);
		
		
		// Look for Net Direct Overall fields value
		while(i < line.length()){
			if(line.charAt(i) != ' ')
				break;	
			i++;
		}
		
//		two consecutive spaces are found
		value = "";
		while(i < line.length() -1){
			if(line.charAt(i) == ' ' && line.charAt(i+1) == ' ')
				break;
			value = value + line.charAt(i);
			i++;
		}
		
		fillDebtData(data, lineNumber, value);

		data.comment = data.comment + line.substring(i).trim()+"\n";
	}
	
	public static String fillIssuerData(String line, int i){
		
		String issuer = line.substring(i).trim();
		return issuer.replace("Under Review", "").trim();
	}
	
	public static void fillCommonDatafromIssuer(Data data, String line, int lineNumber, int issuerIndex,
										boolean isUnderReview, boolean noYearValue, Pattern pattern){
		// For normal section
		if(!isUnderReview && !noYearValue){
			fillIssuerOtherData(line, issuerIndex, data, lineNumber, pattern);
		}
		// for under Review Section
		else if(isUnderReview){
			data.issue = data.issue + fillIssuerData(line, issuerIndex)+"\n";
			fillPopulationData(data, lineNumber, "Under Review");
			fillDebtData(data, lineNumber, "Under Review");
			data.comment = "Under Review";
		}
		// No year value but comments are there
		else if(noYearValue){
			int maxLength = Math.min(issuerIndex + 45, line.length());
//			System.out.println(line);
			data.issue = data.issue + line.substring(issuerIndex, maxLength).trim()+"\n";
			if(maxLength < line.length()){
				data.comment = data.comment + line.substring(maxLength).trim()+"\n";
			}
			
		}
	}
	// Fill amount value
	public static String fillamount(String line, int index){
		String lineTemp = line.trim();
		String amount = "";
		for(char c: lineTemp.toCharArray()){
			if(c == ' ')
				break;
			amount = amount + c;
		}
		return amount;
	}

}

