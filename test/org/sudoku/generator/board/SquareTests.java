package org.sudoku.generator.board;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SquareTests {
	
	Square validSquare = new Square();
	@Before
	public void init(){
		validSquare.addCell(0, 0, new Cell(5));
		validSquare.addCell(0, 1, new Cell(4));
		validSquare.addCell(0, 2, new Cell(8));
		validSquare.addCell(1, 0, new Cell(2));
		validSquare.addCell(1, 1, new Cell(3));
		validSquare.addCell(1, 2, new Cell(9));
		validSquare.addCell(2, 0, new Cell(1));
		validSquare.addCell(2, 1, new Cell(7));
		validSquare.addCell(2, 2, new Cell(6));
	}

	@Test
	public void squareNotValid(){
		Square square = new Square();
		square.addCell(0, 0, new Cell(4));
		square.addCell(0, 1, new Cell(4));
		
		assertFalse(square.squareValid());
	}
	
	@Test
	public void squareValid(){
		assertTrue(validSquare.squareValid());
	}
	
	@Test
	public void generateValidSquare(){
		assertTrue(Square.generateValidSquare().squareValid());
		assertTrue(Square.generateValidSquare().squareValid());
		assertTrue(Square.generateValidSquare().squareValid());
	}
	
}
