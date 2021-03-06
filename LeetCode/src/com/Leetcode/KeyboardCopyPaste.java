package com.Leetcode;

import java.util.Scanner;

//
//Add to List
//650. 2 Keys Keyboard
public class KeyboardCopyPaste {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(minSteps(n));

	}

	// Given a number n. You have to get exactly n 'A' on the notepad by
	// performing
	// the minimum number of steps permitted. Output the minimum number of steps
	// to get n 'A'.
	public static int minSteps(int n) {

		int[][] sol = new int[n + 1][n + 1];
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {

			int min = Integer.MAX_VALUE;
			for (int j = (i / 2 + i % 2); j < i; j++) {

				if (sol[j][i - j] != 0) {
					sol[i][i - j] = sol[j][i - j] + 1;
					min = Math.min(min, sol[i][i - j]);
				}

			}

			if (i == 1) {
				sol[i][i] = 1;
			} else {
				sol[i][i] = min + 1;
			}

		}

		for (int i = 1; i < n; i++) {
			if (sol[n][i] != 0) {
				ans = Math.min(sol[n][i], ans);
			}
		}
		return Math.min(ans, sol[n][n] - 1);

	}

}
