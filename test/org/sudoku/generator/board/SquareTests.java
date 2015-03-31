package org.sudoku.generator.board;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SquareTests {
	
	@Before
	public void init(){
	}

	@Test
	public void squareNotValid(){
		Square square = new Square();
		square.addCell(0, 0, new Cell(4));
		square.addCell(0, 1, new Cell(4));
		
		assertFalse(square.squareValid());
	}
	
	@Test
	public void generateValidSquare(){
		assertTrue(Square.generateValidSquare().squareValid());
		assertTrue(Square.generateValidSquare().squareValid());
		assertTrue(Square.generateValidSquare().squareValid());
	}
	
}
