package com.TCO;

import java.util.Scanner;

public class Avoid9 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// int test = in.nextInt();
		//
		// for (int i = 1; i <= test; i++) {
		//
		// }

		int[] A = { 1, 10, 7, 19, 16, 28, 9, 46, 0, 37 };

		System.out.println(maxSizeOf9Free(A));

	}

	public static int maxSizeOf9Free(int[] A) {
		int max = -1;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {

				int count = 0;
				for (int k = j + 1; k < A.length; k++) {

					if ((A[i] + A[j] + A[k]) % 9 != 0) {
						count++;

					}

				}

				if (count > 0) {
					max = Math.max(max, count + 2);
				}

			}

		}
		return max;

	}

}