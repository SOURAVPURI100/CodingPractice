package CodeSprint;

import java.util.Scanner;

public class TimeSeries {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		int[] t = new int[n];
		for (int t_i = 0; t_i < n; t_i++) {
			t[t_i] = in.nextInt();
		}
		int[] p = new int[n];
		for (int p_i = 0; p_i < n; p_i++) {
			p[p_i] = in.nextInt();
		}

		// find sqrt array length

		int sqrtCommon = (int) Math.sqrt(n);
		int diff = n - (int) Math.pow(sqrtCommon, 2);
		int extraBlocks = 0;

		if (diff % sqrtCommon == 0) {
			extraBlocks = diff / sqrtCommon;
		} else if (diff != 0) {
			extraBlocks = diff / sqrtCommon + 1;
		}

		int[] sqrt_t = new int[sqrtCommon + extraBlocks];
		int[] sqrt_p = new int[sqrtCommon + extraBlocks];

		int count_sqrt = 0;
		int max_value = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (p[i] > max_value) {
				max_value = p[i];
			}
			if ((i + 1) % sqrtCommon == 0) {
				sqrt_t[count_sqrt] = t[i];
				sqrt_p[count_sqrt] = max_value;
				count_sqrt++;
				max_value = Integer.MIN_VALUE;
			}
		}

		if (extraBlocks != 0) {
			sqrt_t[count_sqrt] = t[n - 1];
			sqrt_p[count_sqrt] = max_value;
		}

		for (int a0 = 0; a0 < q; a0++) {
			int type = in.nextInt();
			int v = in.nextInt();

			int sol = findSol(type, v, t, p, sqrt_t, sqrt_p, sqrtCommon);

			System.out.println(sol);
		}
		in.close();
	}

	public static int findSol(int type, int v, int[] t, int[] p, int[] sqrt_t, int[] sqrt_p, int sqrtCommon) {
		// For type 1, given a value , when was the first time that the price of
		// the stock was at least ?
		// For type 2, given a value , what's the maximum price of the stock at
		// a time greater or equal to ?
		if (type == 1) {

			for (int i = 0; i < sqrt_p.length; i++) {
				if (v <= sqrt_p[i]) {
					int startIndex = i * sqrtCommon;
					for (int j = startIndex; j < startIndex + sqrtCommon && j < p.length; j++) {

						if (v <= p[j]) {
							return t[j];
						}

					}

				}
			}
		}

		else if (type == 2) {
			int i = 0;
			for (i = 0; i < sqrt_t.length; i++) {
				if (v <= sqrt_t[i]) {
					break;
				}

			}

			if (i >= sqrt_t.length) {
				return -1;
			}
			int max_value = Integer.MIN_VALUE;
			int startIndex = i * sqrtCommon;
			for (int j = startIndex; j < startIndex + sqrtCommon && j < p.length; j++) {
				if (v <= t[j]) {
					max_value = Math.max(max_value, p[j]);
				}
			}

			for (int j = i + 1; j < sqrt_p.length; j++) {

				max_value = Math.max(max_value, sqrt_p[j]);

			}

			return max_value;
		}

		return -1;
	}

}
