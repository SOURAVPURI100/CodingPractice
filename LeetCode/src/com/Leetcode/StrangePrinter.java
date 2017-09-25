package com.Leetcode;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// https://leetcode.com/problems/strange-printer/description/
// 664. Strange Printer
public class StrangePrinter {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String s = in.nextLine();

		System.out.println(strangePrinter(s));
	}

	// abbac => aaaaa => abbac
	// ababbaa => aaaaaaa => abaaaaa=> ababbaa
	// abcbdabcabbaa => aaaaaaaaaaaaa =>
	// tbgtgb => tttttt => tbbtbb => tbgtbb => tbgtgb
	// tbgtgb => tttttt => tbbbbb=> tbgggb => tbgtgb
	public static int strangePrinter(String s) {
		char[] array = s.toCharArray();
		int sol = 0;
		Set<Character> set = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (set.contains(ch)) {
				continue;
			}

			boolean flag = true;
			for (int j = i; j < s.length(); j++) {
				char charr = s.charAt(j);
				if (!set.contains(charr) && ch == charr) {
					array[i] = ch;
					if (flag == true) {
						sol++;
						flag = false;
					}
				} else if (set.contains(charr)) {
					flag = true;
				}

			}
			set.add(ch);

		}

		return sol;
	}
}
