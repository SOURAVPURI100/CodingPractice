package com.TCO;

import java.util.Scanner;

public class UnclearNotes {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// int test = in.nextInt();
		//
		// for (int i = 1; i <= test; i++) {
		//
		// }
		System.out.println(isMatch("topc0der", "codertop"));
	}

	public static String isMatch(String S, String T) {

		if (S.length() != T.length()) {
			return "Impossible";
		}

		int i = 0;
		while (i < S.length()) {
			char chS = S.charAt(i);
			char chT = T.charAt(i);
			if (chS == '0' || chS == 'o') {
				if (!(chT == '0' || chT == 'o'))
					return "Impossible";
			} else if (chS == '1' || chS == 'l') {
				if (!(chT == '1' || chT == 'l'))
					return "Impossible";
			} else if (chS == 'm' || chS == 'n') {
				if (!(chT == 'm' || chT == 'n'))
					return "Impossible";
			} else if (chS != chT) {
				return "Impossible";
			}

			i++;
		}

		return "Possible";

	}

}
