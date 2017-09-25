import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Data {
	String date, time, sale, rating, amount, issue, pop1, pop2, assessVal1, assessVal2, market_perc, netDir1,
			overallDebt, overallPC, overallAV, overallMV, comment;

	public Data() {
		this.date = "";
		this.time = "";
		this.sale = "";
		this.rating = "";
		this.amount = "";
		this.issue = "";
		this.pop1 = "";
		this.pop2 = "";
		this.assessVal1 = "";
		this.assessVal2 = "";
		this.market_perc = "";
		this.netDir1 = "";
		this.overallDebt = "";
		this.overallPC = "";
		this.overallAV = "";
		this.overallMV = "";
		this.comment = "";
	}
}

public class File_Reading {

	public static void main(String[] args) {
		File file = new File("/home/sourav/Documents/Finance/library/1982JulDec.txt");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = "", first_word = "";
			String[] columns;
			Pattern p = Pattern.compile("[0-9]+/[0-9]+");
			//
			// String date= "", time="", sale ="", rating="", amount="",
			// issue="", pop="",
			// net="", comment="";
			int count = 1;

			// Create Data object
			Data data = new Data();
			BuildExcel buildExcel = new BuildExcel("/home/sourav/Documents/Finance/library/1982JulDec.xlsx");
			File_Reading obj = null;
			while ((line = in.readLine()) != null) {

				columns = line.split("[@][@]");
				columns = trimValues(columns);

				// Check if line contains only "@" symbol, so ignore this line
				if (!verifyColumnData(columns)) {
					continue;
				}

				// Check if this a first line for next column
				if (columns.length > 0) {
					first_word = columns[0];
				} else {
					continue;
				}
				Matcher m = p.matcher(first_word);

				if (m.find() && count > 1) {
					count = 1;
					buildExcel.addRow(data);
					data = new Data();

				}

				if (count == 1) {
					data.date = columns[0];
					if (columns.length > 1)
						data.rating = columns[1];
				} else if (count == 2) {
					data.time = columns[0];

					if (columns.length > 1)
						data.amount = data.amount + columns[1];
				} else if (count == 3) {
					data.sale = columns[0];
					if (columns.length > 1)
						data.amount = data.amount + columns[1];
				}
				if (columns.length > 2) {
					data.issue = data.issue + columns[2] + "\n";
				}

				if (columns.length > 3) {
					fillPopulationData(data, count, columns[3]);
					// data.pop = data.pop + columns[3]+"\n";
				}
				if (columns.length > 4) {
					fillDebtData(data, count, columns[4]);
					// data.net = data.net + columns[4]+"\n";
				}

				if (columns.length > 5) {
					data.comment = eliminateSymbol(data.comment) + columns[5] + "\n";
				}

				count++;

			}
			in.close();

			buildExcel.addRow(data);
			buildExcel.generateOutput();

			System.out.println("File Generated");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String formed_line(String line, int beg, int end) {

		if (line.length() >= end)
			return line.substring(beg, end);
		else if (line.length() > beg)
			return line.substring(beg);
		else
			return "";

	}

	public static String[] trimValues(String[] columns) {
		String[] retColumns = new String[columns.length];

		for (int i = 0; i < columns.length; i++) {
			retColumns[i] = columns[i].trim();
		}

		return retColumns;
	}

	// Fill Population Assess value into corresponding columns
	public static void fillPopulationData(Data data, int line, String value) {
		if (line == 1) {
			data.pop1 = eliminateSymbol(value);
		} else if (line == 2) {
			data.pop2 = eliminateSymbol(value);

		} else if (line == 3) {
			data.assessVal1 = eliminateSymbol(value);

		} else if (line == 4) {
			data.assessVal2 = eliminateSymbol(value);

		} else if (line == 5) {
			data.market_perc = eliminateSymbol(value);

		}
	}

	// Fill Net and Overall Debt data into corresponding columns
	public static void fillDebtData(Data data, int line, String value) {
		if (line == 1) {
			data.netDir1 = eliminateSymbol(value);
		} else if (line == 2) {
			data.overallDebt = eliminateSymbol(value);

		} else if (line == 3) {
			data.overallPC = eliminateSymbol(value);

		} else if (line == 4) {
			data.overallAV = eliminateSymbol(value);

		} else if (line == 5) {
			data.overallMV = eliminateSymbol(value);

		}
	}

	public static boolean verifyColumnData(String[] columns) {
		for (String s : columns) {
			if (!s.equals("") && !s.matches("[@]+"))
				return true;
		}
		return false;
	}

	public static String eliminateSymbol(String str) {
		return str.replaceAll("[@][@]", "");
	}

}
