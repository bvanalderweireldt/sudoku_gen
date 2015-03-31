package org.sudoku.generator.board;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTests {
	
	Board validBoard, validBoard2;
	Board invalidBoard, invalidBoard2;
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
	
	@Before
	public void init(){
		validBoard = new Board(validBoardArray);
		validBoard2 = new Board(validBoardArray2);
		
		invalidBoard = new Board(invalidBoardArray);
		invalidBoard2 = new Board(invalidBoardArray2);
	}
	
	@Test
	public void columnValid(){
		assertTrue( validBoard.allColumnsValid() );
		assertTrue( validBoard2.allColumnsValid() );
		
		System.out.println(invalidBoard);
		assertFalse(invalidBoard.allColumnsValid());
		assertFalse(invalidBoard2.allColumnsValid());
	}
	
	@Test 
	public void lineValid(){
		assertTrue( validBoard.allLinesValid() );
		assertTrue( validBoard2.allLinesValid() );		

		assertFalse(invalidBoard.allLinesValid());
		assertFalse(invalidBoard2.allLinesValid());
	}
	
}
