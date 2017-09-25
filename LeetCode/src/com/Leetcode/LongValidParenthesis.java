package com.Leetcode;

import java.util.Scanner;
import java.util.Stack;

// https://leetcode.com/problems/longest-valid-parentheses/description/
//32. Longest Valid Parentheses
public class LongValidParenthesis {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String str = in.nextLine();
		in.close();
		System.out.println(longestValidParentheses(str));

	}

	public static int longestValidParentheses(String s) {
		// ")()())"
		// (()
		// () =2 => () => 4
		// (())() ) = 2+2+2 = 6
		// (()())(
		// (()())
		// "(()(((()"

		int globalAns = 0;
		Stack<Integer> stack = new Stack<>();
		int tempCount = 0;
		int stackCount = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				stack.push(i);
			} else if (ch == ')') {
				if (stack.isEmpty()) {
					tempCount = 0;
					stackCount = 0;
				} else {
					stack.pop();
					if (!stack.isEmpty()) {
						stackCount = Math.max(i - stack.peek(), stackCount);
					} else {
						tempCount += stackCount + 2;
						stackCount = 0;
						if (globalAns < tempCount) {
							globalAns = tempCount;
						}
					}

				}

			}

		}

		return Math.max(globalAns, stackCount);

	}

}
