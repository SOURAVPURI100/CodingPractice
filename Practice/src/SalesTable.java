import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Pattern;

public class SalesTable {

	public static void main(String[] args) {
		File file = new File("/home/sourav/Documents/Finance/library/1981Sales.txt");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = "";
			char firstChar = ' ';
			String[] columns;
			Pattern p = Pattern.compile("[0-9]+/[0-9]+");
			Pattern rating = Pattern.compile("[A-Za-z]+");
			int count = 1;

			// Create SalesData object
			SalesData SalesData = new SalesData();
			BuildSalesExcel buildExcel = new BuildSalesExcel("/home/sourav/Documents/Finance/library/1981Sales.xlsx");
			File_Reading obj = null;
			while ((line = in.readLine()) != null) {

				line = line.trim();
				// Check if this a first line for next column
				if (line.length() > 0) {
					firstChar = line.charAt(0);
				} else {
					continue;
				}

				if (checkFirstChar(firstChar) && count > 1) {
					count = 1;

					System.out.print(SalesData.rating + " ");
					System.out.print(SalesData.sale + " ");
					System.out.print(SalesData.date + " ");
					System.out.print(SalesData.issuer + " ");
					System.out.print(SalesData.amount + " ");
					System.out.print(SalesData.avg + " ");
					System.out.print(SalesData.years + " ");
					System.out.print(SalesData.year1 + " ");
					System.out.print(SalesData.year3 + " ");
					System.out.print(SalesData.year6 + " ");
					System.out.print(SalesData.year10 + " ");
					System.out.print(SalesData.year15 + " ");
					System.out.print(SalesData.year20 + " ");
					System.out.println();

					buildExcel.addRow(SalesData);
					SalesData = new SalesData();
				}

				if (count == 1) {
					int j, k;
					for (j = 0; j < line.length(); j++) {
						if (line.charAt(j) != ' ') {
							SalesData.rating += line.charAt(j);
						} else
							break;
					}

					for (k = j + 1; k < line.length(); k++) {
						if (line.charAt(k) != ' ' && line.charAt(k) > 47 && line.charAt(k) <= 57) {
							SalesData.rating += " " + line.charAt(k);
							k++;
						}
						break;
					}
					SalesData.issuer = line.substring(k).trim();
				} else if (count == 2) {
					fillSalesData(SalesData, line);
				} else if (count == 3) {
					SalesData.years = line.trim();
				}
				count++;

			}
			in.close();

			buildExcel.addRow(SalesData);
			buildExcel.generateOutput();

			System.out.println("File Generated");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String[] trimValues(String[] columns) {
		String[] retColumns = new String[columns.length];

		for (int i = 0; i < columns.length; i++) {
			retColumns[i] = columns[i].trim();
		}

		return retColumns;
	}

	public static boolean verifyColumnSalesData(String[] columns) {
		for (String s : columns) {
			if (!s.equals("") && !s.matches("[@]+"))
				return true;
		}
		return false;
	}

	public static String getIssuer(String[] columns) {
		// Fetch columns data from 2 to last

		String issuer = "";
		for (int i = 1; i < columns.length; i++) {
			issuer += columns[i];
		}
		issuer = issuer.replaceAll("[@][@]", "");
		return issuer;

	}

	public static void fillSalesData(SalesData salesData, String line) {
		String val = "";
		line = line.trim();
		int count = 1;
		for (int i = 0; i < line.length(); i++) {

			if (line.charAt(i) == ' ') {
				val = val.trim();
				// Check if current field value is filled or not
				if (!val.isEmpty()) {
					// Fill the value to appropriate column
					fillColumnData(salesData, val.trim(), count);
					val = "";
					count++;
				}
			}

			else if (line.charAt(i) == '-' || line.charAt(i) == '~') {
				int hyphenCount = 1;
				// Check number of hyphen to determine number of empty values
				for (int j = i + 1; j < line.length(); j++) {
					if (line.charAt(j) == '-' || line.charAt(j) == '~')
						hyphenCount++;
					else if (line.charAt(j) != ' ') {
						i = j - 1;
						count = count + (hyphenCount - 1) / 3 + 1;
						break;
					}
				}
			} else {
				val += line.charAt(i);
			}
		}

		fillColumnData(salesData, val.trim(), count);

	}

	public static void fillColumnData(SalesData salesData, String val, int count) {
		if (count == 1) {
			salesData.date = val;
		}
		if (count == 2) {
			salesData.amount = val;
		}
		if (count == 3) {
			salesData.avg = val;
		}
		if (count == 4) {
			salesData.year1 = val;
		}
		if (count == 5) {
			salesData.year3 = val;
		}
		if (count == 6) {
			salesData.year6 = val;
		}
		if (count == 7) {
			salesData.year10 = val;
		}
		if (count == 8) {
			salesData.year15 = val;
		}
		if (count == 9) {
			salesData.year20 = val;
		}
	}

	public static String fillSalesYears(String[] columns) {
		String years = "";
		for (String s : columns) {
			years += s;
		}

		years = years.replaceAll("[@][@]", "");
		years = years.trim();
		return years;
	}

	public static boolean checkFirstChar(char c) {
		if (c == 'A' || c == 'a' || c == 'B' || c == 'b' || c == 'C' || c == 'c')
			return true;
		return false;
	}

}
