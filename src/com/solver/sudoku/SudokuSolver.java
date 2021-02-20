package com.solver.sudoku;

import java.util.Arrays;

public class SudokuSolver {
	public int [][] board = {{0, 5, 8, 2, 4, 0, 9, 1, 0},
	  		 				 {0, 0, 0, 0, 9, 0, 6, 8, 7},
	  		 				 {0, 0, 0, 0, 6, 0, 2, 0, 0},
	  		 				 {8, 0, 5, 0, 0, 0, 4, 0, 0},
	  		 				 {0, 7, 0, 0, 5, 0, 1, 6, 2},
	  		 				 {1, 2, 0, 0, 0, 4, 0, 3, 0},
	  		 				 {0, 9, 6, 0, 8, 1, 3, 0, 5},
	  		 				 {0, 8, 1, 0, 0, 0, 0, 2, 0},
	  		 				 {7, 4, 3, 5, 0, 6, 0, 0, 0}};
	
	public void solveBoard() {
		solveBoardRec(board);
	}
	
	public boolean solveBoardRec(int[][] board) {
		if(isFull(board)) {
			return true;
		}
		
		if(findEmpty(board) == null) {
			return true;
		}
		
		int[] empty = findEmpty(board);
		int row = empty[0];
		int col = empty[1];
		
		for(int i = 1; i < 10; i++) {
			if(isValid(board, i, empty)) {
				board[row][col] = i;
				
				if(solveBoardRec(board)) {
					return true;
				}
				
				board[row][col] = 0;
			}
		}
		return false;
	}
	
	public int[] findEmpty(int[][] board) {
		int[] loc = new int[2];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0) {
					loc[0] = i;
					loc[1] = j;
				}
			}
		}
		if(loc.length == 0) {
			return null;
		}
		return loc;
	}
	
	
	public void printBoard() {
		for(int i = 0; i < board.length; i++) {
			if(i % 3 == 0 && i != 0) {
				System.out.println("--------------------");
			}
			for(int j = 0; j < board.length; j++) {
				if(j % 3 == 0 && j != 0) {
					System.out.print("|");
				}
				
				System.out.print(board[i][j] + " ");
				
			}
			System.out.println();
			
		}
		
		
	}
	public boolean isFull(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValid(int[][] board, int num, int[] pos) {
		
		// Check Row.
		
		for(int i = 0; i < board[0].length; i++) {
			if(board[pos[0]][i] == num && pos[1] != i) {
				return false;
			}
		}
		
		for(int j = 0; j < board.length; j++) {
			if(board[j][pos[1]] == num && pos[0] != j) {
				return false;
			}
		}
		
		int x_box = pos[1] / 3;
		int y_box = pos[0] / 3;
		

		
		for(int k = y_box*3; k < y_box*3 + 3; k++) {
			for(int l = x_box*3; l < x_box*3 + 3; l++) {
				int[] check = {k, l};
				if(board[k][l] == num && !Arrays.equals(check, pos)) {
					return false;
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		
		SudokuSolver ss = new SudokuSolver();
		ss.printBoard();
		ss.solveBoard();
		System.out.println();
		System.out.println();
		System.out.println();
		ss.printBoard();
		
		

	}

}
