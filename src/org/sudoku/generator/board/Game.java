package org.sudoku.generator.board;

public class Game {
	public static void main(String[] args) {
		Board board = new Board();
		board.generateValidBoard();;
		System.out.println(board);
	}
}
