package com.Leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LongestPairChain {

	public static void main(String[] args) {
		int[][] arr = { { -10, -8 }, { 8, 9 }, { -5, 0 }, { 6, 10 }, { -6, -4 }, { 1, 7 }, { 9, 10 }, { -4, 7 } };

		System.out.println(findLongestChain(arr));
	}

	public static int findLongestChain(int[][] pairs) {
		int count = 0;
		sortArray(pairs);
		int len = pairs.length;
		int[] sol = new int[len];
		// for(int i =0; i< pairs.length; i++){

		int start = pairs[len - 1][0];
		for (int j = len - 2; j >= 0; j--) {

			int startJ = pairs[j][0];
			int endJ = pairs[j][1];

			if (start > endJ) {
				count++;
				start = startJ;
			}

		}

		// }

		return count + 1;

	}

	public static void sortArray(int[][] pairs) {
		Arrays.sort(pairs, new Comparator<int[]>() {

			public int compare(int[] arr1, int[] arr2) {

				if (arr1[0] > arr2[0])
					return 1;
				else if (arr1[0] < arr2[0])
					return -1;
				else {
					if (arr1[1] > arr2[1])
						return 1;

					return -1;
				}

			}

		});
	}

}
