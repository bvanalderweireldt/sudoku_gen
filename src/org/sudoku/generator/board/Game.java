package org.sudoku.generator.board;

public class Game {
	public static void main(String[] args) {
		Board board = new Board();
		board.generateBoard();
		System.out.println(board);
	}
}
