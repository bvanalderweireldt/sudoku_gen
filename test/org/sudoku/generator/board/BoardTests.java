package org.sudoku.generator.board;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class BoardTests {
	
	Board validBoard, validBoard2;
	Board invalidBoard, invalidBoard2;
	Board freeCellsBoard,freeCellsBoard2;
	Board emptyBoard;
	Board generated;
	
	final static int[][][][] validBoardArray = new int[][][][] { 
		{{{9,5,3}, {2,7,6}, {8,1,4} }, { {2,1,4}, {8,5,3}, {6,7,9} }, { {7,6,8}, {4,1,9}, {2,3,5} }},
		{{{7,4,8}, {6,9,1}, {5,3,2} }, { {5,3,1}, {7,4,2}, {9,6,8} }, { {6,9,2}, {5,8,3}, {1,7,4} }},
		{{{1,6,9}, {3,2,5}, {4,8,7} }, { {4,8,5}, {1,9,7}, {3,2,6} }, { {3,2,7}, {8,4,6}, {9,5,1} }}
	};
	final static int[][][][] validBoardArray2 = new int[][][][] { 
		{{{6,3,2}, {4,8,7}, {5,1,9} }, { {7,8,1}, {5,9,6}, {2,4,3} }, { {9,4,5}, {2,1,3}, {8,7,6} }},
		{{{8,6,4}, {7,5,1}, {2,9,3} }, { {3,5,2}, {9,6,8}, {1,7,4} }, { {7,9,1}, {3,2,4}, {6,5,8} }},
		{{{9,4,5}, {1,7,6}, {3,2,8} }, { {6,3,7}, {8,2,5}, {4,1,9} }, { {1,8,2}, {4,3,9}, {5,6,7} }}
	};
	final static int[][][][] invalidBoardArray = new int[][][][] { 
		{{{9,5,3}, {2,7,6}, {8,1,4} }, { {2,1,4}, {8,5,3}, {6,7,9} }, { {7,3,8}, {4,1,9}, {2,3,5} }},
		{{{7,4,8}, {6,9,1}, {5,3,2} }, { {5,3,1}, {7,4,2}, {9,6,8} }, { {6,9,2}, {5,8,3}, {1,7,4} }},
		{{{1,6,9}, {3,2,5}, {4,8,7} }, { {4,8,1}, {1,9,7}, {3,2,6} }, { {3,2,7}, {8,4,6}, {9,5,1} }}
	};
	final static int[][][][] invalidBoardArray2 = new int[][][][] { 
		{{{4,3,2}, {4,8,7}, {5,1,9} }, { {7,8,1}, {5,9,6}, {2,4,3} }, { {9,4,5}, {2,1,3}, {8,7,6} }},
		{{{8,6,4}, {7,5,1}, {2,7,3} }, { {3,5,2}, {9,6,8}, {1,7,4} }, { {7,9,1}, {3,2,4}, {6,5,8} }},
		{{{9,4,5}, {1,7,6}, {3,2,8} }, { {6,3,9}, {8,2,5}, {4,1,9} }, { {1,8,2}, {4,3,9}, {5,6,7} }}
	};
	final static int[][][][] freeCellsArray = new int[][][][] { 
		{{{6,3,2}, {4,8,7}, {5,1,9} }, { {7,8,1}, {5,9,6}, {2,4,3} }, { {9,4,5}, {2,1,3}, {8,7,6} }},
		{{{8,6,4}, {7,5,1}, {2,9,3} }, { {3,0,2}, {9,6,8}, {1,7,4} }, { {7,9,1}, {3,2,4}, {6,5,8} }},
		{{{9,4,5}, {1,7,6}, {3,2,8} }, { {6,3,7}, {8,2,5}, {4,1,9} }, { {1,8,2}, {4,3,9}, {5,6,7} }}
	};
	final static int[][][][] freeCellsArray2 = new int[][][][] { 
		{{{9,0,3}, {2,7,6}, {8,1,4} }, { {2,0,4}, {8,5,3}, {6,7,9} }, { {7,6,8}, {4,1,9}, {2,3,5} }},
		{{{7,4,8}, {6,9,1}, {5,0,2} }, { {5,3,1}, {7,4,2}, {9,6,8} }, { {6,0,2}, {5,8,3}, {1,7,4} }},
		{{{1,6,9}, {3,0,5}, {4,8,7} }, { {4,8,5}, {1,0,7}, {3,2,6} }, { {3,2,7}, {8,4,6}, {9,5,1} }}
	};
	
	@Before
	public void init(){
		validBoard = new Board(validBoardArray);
		validBoard2 = new Board(validBoardArray2);
		
		invalidBoard = new Board(invalidBoardArray);
		invalidBoard2 = new Board(invalidBoardArray2);
		
		freeCellsBoard = new Board(freeCellsArray);
		freeCellsBoard2 = new Board(freeCellsArray2);
		
		emptyBoard = new Board();
		
		generated = new Board();
		generated.generateValidBoard();
	}
	
	@Test
	public void allColumnsValid(){
		assertTrue(validBoard.allColumnsValid());
		assertTrue(validBoard2.allColumnsValid());
		assertTrue(generated.allColumnsValid());
		
		assertFalse(invalidBoard.allColumnsValid());
		assertFalse(invalidBoard2.allColumnsValid());
	}
	
	@Test 
	public void allLinesValid(){
		assertTrue(validBoard.allLinesValid());
		assertTrue(validBoard2.allLinesValid());		
		assertTrue(generated.allLinesValid());		

		assertFalse(invalidBoard.allLinesValid());
		assertFalse(invalidBoard2.allLinesValid());
	}
	
	@Test
	public void columnValid(){
		assertTrue(validBoard.columnValid(0, 0));
		assertTrue(validBoard.columnValid(2, 1));
		assertTrue(validBoard.columnValid(1, 2));

		assertTrue(validBoard2.columnValid(0, 1));
		assertTrue(validBoard2.columnValid(1, 1));
		assertTrue(validBoard2.columnValid(2, 2));

		assertFalse(invalidBoard.columnValid(1, 2));
		assertFalse(invalidBoard2.columnValid(0, 0));
	}
	
	@Test
	public void getCell(){
		assertTrue(validBoard.getCell(1, 0, 0, 2).getValue() == 8);
		assertTrue(validBoard2.getCell(2, 1, 2, 1).getValue() == 1);
	}
	
	@Test
	public void boardFull(){
		assertTrue(validBoard.boardFull());
		assertTrue(validBoard2.boardFull());
		
		assertFalse(emptyBoard.boardFull());
	}
	
	@Test
	public void boardValid(){
		assertTrue(validBoard.boardValid());
		assertTrue(validBoard2.boardValid());

		assertFalse(invalidBoard.boardValid());
		assertFalse(invalidBoard2.boardValid());
		
		assertTrue(generated.boardValid());
	}
	
	@Test
	public void boardCompleted(){
		assertTrue(validBoard.boardCompleted());
		assertTrue(validBoard2.boardCompleted());
		assertTrue(generated.boardCompleted());
		
		assertFalse(invalidBoard.boardCompleted());
		assertFalse(invalidBoard2.boardCompleted());
		assertFalse(emptyBoard.boardCompleted());
	}
	
	@Test
	public void getFreeCells(){
		Stack<int[]> freeCells = freeCellsBoard.getFreeCellsStack();
		assertTrue( Arrays.equals(freeCells.pop(), new int[]{1,1,0,1}) );
		assertTrue(freeCells.empty());
		
		Stack<int[]> freeCells2 = freeCellsBoard2.getFreeCellsStack();
		assertTrue( Arrays.equals(freeCells2.pop(), new int[]{2,1,1,1}) );
		assertTrue( Arrays.equals(freeCells2.pop(), new int[]{2,0,1,1}) );
		assertTrue( Arrays.equals(freeCells2.pop(), new int[]{1,2,0,1}) );
		assertTrue( Arrays.equals(freeCells2.pop(), new int[]{1,0,2,1}) );
		assertTrue( Arrays.equals(freeCells2.pop(), new int[]{0,1,0,1}) );
		assertTrue( Arrays.equals(freeCells2.pop(), new int[]{0,0,0,1}) );
		assertTrue(freeCells2.empty());	
	}
	
	@Test
	public void getRandomValue(){
		int i = validBoard.getRandomValue();
		int j = validBoard.getRandomValue();
		int k = validBoard.getRandomValue();
		int l = validBoard.getRandomValue();
		int m = validBoard.getRandomValue();
		
		assertFalse( i == j && i == k && i == l && i == m);
		
		assertTrue(i >= Cell.MIN_CELL_VALUE && i <= Cell.MAX_CELL_VALUE);
		assertTrue(j >= Cell.MIN_CELL_VALUE && j <= Cell.MAX_CELL_VALUE);
		assertTrue(k >= Cell.MIN_CELL_VALUE && k <= Cell.MAX_CELL_VALUE);
		assertTrue(l >= Cell.MIN_CELL_VALUE && l <= Cell.MAX_CELL_VALUE);
		assertTrue(m >= Cell.MIN_CELL_VALUE && m <= Cell.MAX_CELL_VALUE);
	}
	@Test
	public void getRandomPosition(){
		int[] p = validBoard.getRandomPosition();
		assertTrue(p.length == 4);
		assertTrue(p[0] >= 0 && p[0] <=3);
		assertTrue(p[1] >= 0 && p[1] <=3);
		assertTrue(p[2] >= 0 && p[2] <=3);
		assertTrue(p[3] >= 0 && p[3] <=3);
		int[] p1 = validBoard.getRandomPosition();
		int[] p2 = validBoard.getRandomPosition();

		assertFalse(Arrays.equals(p, p1) && Arrays.equals(p, p2));
	}
	
}
