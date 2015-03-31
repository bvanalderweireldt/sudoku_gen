package org.sudoku.generator.board;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTests {
	
	Board validBoard = new Board();
	
	final static int[][][][] validBoardArray = new int[][][][] { 
		{
			{ {9,5,3}, {2,7,6}, {8,1,4} }, { {2,1,4}, {8,5,3}, {6,7,9} }, { {7,6,8}, {4,1,9}, {2,3,5} }
		},
		{ 
			{ {7,4,8}, {6,9,1}, {5,3,2} }, { {5,3,1}, {7,4,2}, {9,6,8} }, { {6,9,2}, {5,8,3}, {1,7,4} }
		},
		{
			{ {1,6,9}, {3,2,5}, {4,8,7} }, { {4,8,5}, {1,9,7}, {3,2,6} }, { {3,2,7}, {8,4,6}, {9,5,1} }
		}
	};
	
	@Before
	public void init(){
		validBoard.addSquare(0, 0, new Square( new int[][] { { 9 , 5 , 3 }, { 2, 7, 6 }, { 8, 1, 4 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 2 , 1 , 4 }, { 8, 5, 3 }, { 6, 7, 9 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 7 , 6 , 8 }, { 4, 1, 9 }, { 2, 3, 5 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 7 , 4 , 8 }, { 6, 9, 1 }, { 5, 3, 2 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 5 , 3 , 1 }, { 7, 4, 2 }, { 9, 6, 8 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 6 , 9 , 2 }, { 5, 8, 3 }, { 1, 7, 4 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 1 , 6 , 9 }, { 3, 2, 5 }, { 4, 8, 7 } } ) );
		validBoard.addSquare(0, 0, new Square( new int[][] { { 4 , 8 , 5 }, { 2, 7, 6 }, { 8, 1, 4 } } ) );

	}
}
