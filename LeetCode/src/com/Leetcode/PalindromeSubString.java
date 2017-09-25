package com.Leetcode;

// https://leetcode.com/problems/palindromic-substrings/description/
// 647. Palindromic Substrings
import java.util.Scanner;

public class PalindromeSubString {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String str = in.nextLine();

		System.out.println(countSubstrings(str));

	}

	// naman
	public static int countSubstrings(String s) {
		int count = 0;
		int len = s.length();
		boolean[][] sol = new boolean[len + 1][len + 1];

		for (int i = 0; i < s.length(); i++) {
			sol[i][i] = true;
			count++;
			char charI = s.charAt(i);
			for (int j = i - 1; j >= 0; j--) {
				char charJ = s.charAt(j);
				if (i - j == 1) {
					if (charI == charJ) {
						count++;
						sol[j][i] = true;
					} else {
						sol[j][i] = false;
					}
				} else {
					if (charI == charJ && sol[j + 1][i - 1] == true) {
						sol[j][i] = true;
						count++;
					} else {
						sol[j][i] = false;
					}
				}

			}
		}

		return count;
	}

}
