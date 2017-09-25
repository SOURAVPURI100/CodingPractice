package com.codecon;

//https://codecon.bloomberg.com/contest/377/4023
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Program {
	int time;
	String day;

	public Program(int time, String day) {
		this.time = time;
		this.day = day;
	}
}

class Comp implements Comparator<Program> {
	@Override
	public int compare(Program obj1, Program obj2) {

		if (obj1.time > obj2.time)
			return 1;
		else if (obj1.time < obj2.time)
			return -1;
		else if (obj1.day.equals("start"))
			return 1;
		return -1;
	}

}

public class SportsBar {

	public static void main(String[] args) {

		int out = 0;
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());

		List<Program> events = new ArrayList<>();
		for (int i = 0; i < n; i++) {

			String str = in.nextLine();

			String[] str1 = str.split("\\|");

			Program obj = new Program(Integer.parseInt(str1[1]), "start");
			events.add(obj);
			obj = new Program(Integer.parseInt(str1[2]), "end");
			events.add(obj);

		}

		Collections.sort(events, new Comp());
		int count = 0;
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).day.equals("start")) {
				count++;
				out = Math.max(out, count);
			} else {
				count--;
			}

		}

		System.out.println(out);

	}

}
