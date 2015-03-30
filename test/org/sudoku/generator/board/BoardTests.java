package org.sudoku.generator.board;

import org.junit.Test;

public class BoardTests {
	@Test
	public void testBoard(){
		Board board = new Board();
		board.generateBoard();
		System.out.println(board);
	}
}
