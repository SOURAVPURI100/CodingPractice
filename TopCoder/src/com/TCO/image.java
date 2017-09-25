package com.TCO;
// Imagine we have an image. We’ll represent this image as a simple 2D array where every pixel is a 1 or a 0. 

// The image you get is known to have N rectangles of 0s on a background of 1s. Write a function that takes in the image and outputs the coordinates of all the 0 rectangles -- top-left and bottom-right; or top-left, width and height.

import java.util.HashSet;
import java.util.Set;

class Image {
	public static void main(String[] args) {
		// indiec are 2 and 3 "2+3" 2+3
		// Java
		int[][] image = { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 1 }, { 1, 0, 1, 0, 0, 1, 1 }, { 1, 1, 1, 0, 0, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1 }, };

		findAllRect(image);

		// for(int i =0; i < sol.length; i++){
		// System.out.println(sol[i][0] + "," + sol[i][1]);

		// }

	}

	public static void findAllRect(int[][] matrix) {
		Set<String> set = new HashSet<>();

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0 && !set.contains(i + "-" + j)) {
					int[][] temp = findRect(i, j, matrix, set);

					for (int k = 0; k < temp.length; k++) {
						System.out.println(temp[k][0] + "," + temp[k][1]);
					}

				}

			}

		}

	}

	public static int[][] findRect(int row, int col, int[][] image, Set<String> set) {
		int[][] result = new int[2][2];
		int rightCol = col;
		int bottomRow = row;
		for (int i = col + 1; i < image[0].length; i++) {
			if (image[row][i] == 0) {
				rightCol = i;
				set.add(row + "-" + i);
			} else {
				break;
			}
		}

		for (int i = row + 1; i < image.length; i++) {
			if (image[i][col] == 0) {
				bottomRow = i;
				set.add(i + "-" + col);
			} else {
				break;
			}
		}

		result[0][0] = row;
		result[0][1] = col;
		result[1][0] = bottomRow;
		result[0][1] = rightCol;
		return result;
	}

}
