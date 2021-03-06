import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int p = in.nextInt();
		long m = in.nextLong();
		int ivalue = -1, jvalue = -1;
		int k1 = 0, k2 = 0, k11 = 0, k22 = 0;
		String str = findTransactions(n, k, p, m);
		String[] ans = str.split("#");
		if (ans.length > 1) {
			String kvalues = ans[0];
			String[] kval = kvalues.split("-");
			String indices = ans[1];

			String[] ind = indices.split("-");
			if (ind.length > 1) {
				ivalue = Integer.parseInt(ind[0]);
				jvalue = Integer.parseInt(ind[1]);
			}

			if (kval.length >= 4) {
				k1 = Integer.parseInt(kval[0]);
				k2 = Integer.parseInt(kval[1]);
				k11 = Integer.parseInt(kval[2]);
				k22 = Integer.parseInt(kval[3]);
			}
		}

		for (int i = 0; i < n; i++) {
			if (i == ivalue) {
				System.out.print(k1 + " ");
			} else if (i == jvalue) {
				System.out.print(k2 + " ");
			} else {
				System.out.print(1 + " ");
			}
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			if (i == ivalue) {
				System.out.print(k11 + " ");
			} else if (i == jvalue) {
				System.out.print(k22 + " ");
			} else {
				System.out.print(1 + " ");
			}
		}
		in.close();
	}

	public static String findTransactions(int n, int Kvalue, int p, long m) {

		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < n; i++) {

			for (int j = i + 1; j < n; j++) {

				for (int k1 = 1; k1 <= Kvalue; k1++) {

					for (int k2 = 1; k2 <= Kvalue; k2++) {

						// calculate the value for 2 variables
						double val1 = k1 * (double) Math.pow(p, n - 1 - i);
						double val2 = k2 * (double) Math.pow(p, n - 1 - j);
						double val = val1 + val2;

						if (map.containsKey(val + "-" + i + "-" + j)) {

							String str = map.get(val + "-" + i + "-" + j);
							int k11 = 0, k22 = 0;
							String[] str1 = str.split("#");

							if (str1.length > 0) {
								String str2 = str1[0];
								String[] k1k2 = str2.split("-");

								if (k1k2.length > 1) {
									k11 = Integer.parseInt(k1k2[0]);
									k22 = Integer.parseInt(k1k2[1]);
								}
							}

							if (k1 != k11 && k2 != k22) {
								return (k1 + "-" + k2 + "-" + map.get(val + "-" + i + "-" + j));
							}

						}
						map.put(val + "-" + i + "-" + j, k1 + "-" + k2 + "#" + i + "-" + j);
					}

				}

			}

		}
		return "";

	}
}