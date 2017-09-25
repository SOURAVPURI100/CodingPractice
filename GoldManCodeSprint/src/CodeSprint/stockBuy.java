package CodeSprint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class comp implements Comparator<stock> {
	@Override
	public int compare(stock val1, stock val2) {

		if (val1.price > val2.price)
			return 1;
		else if (val1.price < val2.price)
			return -1;
		else if (val1.day < val2.day)
			return 1;
		return -1;
	}
}

class stock {
	int day;
	int price;

	public stock(int day, int price) {
		this.day = day;
		this.price = price;
	}

}

public class stockBuy {
	static long buyMaximumProducts(int n, long k, int[] a) {

		List<stock> stockList = new ArrayList<>();
		long buyStocks = 0;
		for (int i = 0; i < a.length; i++) {
			stock obj = new stock(i + 1, a[i]);
			stockList.add(obj);
		}
		Collections.sort(stockList, new comp());

		for (int i = 0; i < stockList.size(); i++) {
			stock stockObj = stockList.get(i);
			int numStocks = buyStock(stockObj, k);

			if (numStocks <= 0)
				break;
			buyStocks += numStocks;
			k = k - numStocks * stockObj.price;
		}

		return buyStocks;
	}

	public static int buyStock(stock obj, long k) {

		int price = obj.price;
		int day = obj.day;

		// Maximize the number of stocks to buy

		while (k < (long) (price * day)) {
			day = day - 1;
		}
		return day;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int arr_i = 0; arr_i < n; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		long k = in.nextLong();
		long result = buyMaximumProducts(n, k, arr);
		System.out.println(result);
		in.close();
	}
}
