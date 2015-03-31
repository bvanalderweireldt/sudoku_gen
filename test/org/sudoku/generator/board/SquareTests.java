package org.sudoku.generator.board;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SquareTests {
	
	Square validSquare = new Square();
	Square validSquare2 = new Square();
	Square inValidSquare = new Square();

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
		
		validSquare2.addCell(0, 0, new Cell(9));
		validSquare2.addCell(0, 1, new Cell(6));
		validSquare2.addCell(0, 2, new Cell(8));
		validSquare2.addCell(1, 0, new Cell(7));
		validSquare2.addCell(1, 1, new Cell(3));
		validSquare2.addCell(1, 2, new Cell(1));
		validSquare2.addCell(2, 0, new Cell(5));
		validSquare2.addCell(2, 1, new Cell(2));
		validSquare2.addCell(2, 2, new Cell(4));

		inValidSquare.addCell(0, 0, new Cell(5));
		inValidSquare.addCell(0, 1, new Cell(4));
		inValidSquare.addCell(0, 2, new Cell(8));
		inValidSquare.addCell(1, 0, new Cell(2));
		inValidSquare.addCell(1, 1, new Cell(4));
		inValidSquare.addCell(1, 2, new Cell(9));
		inValidSquare.addCell(2, 0, new Cell(1));
		inValidSquare.addCell(2, 1, new Cell(7));
		inValidSquare.addCell(2, 2, new Cell(6));
	}

	@Test
	public void squareNotValid(){
		Square square = new Square();
		square.addCell(0, 0, new Cell(4));
		square.addCell(0, 1, new Cell(4));
		
		assertFalse(square.squareValid());
		assertFalse(inValidSquare.squareValid());
	}
	
	@Test
	public void squareValid(){
		assertTrue(validSquare.squareValid());
		assertTrue(validSquare2.squareValid());
	}
	
	@Test
	public void generateValidSquare(){
		assertTrue(Square.generateValidSquare().squareValid());
		assertTrue(Square.generateValidSquare().squareValid());
		assertTrue(Square.generateValidSquare().squareValid());
	}
	
	@Test
	public void squareFull(){
		assertTrue(validSquare.squareFull());
		assertTrue(validSquare2.squareFull());
	}
}
