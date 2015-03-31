package org.sudoku.generator.board;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class Board {
	final static int MAX_BOARD_WIDTH = 3;
	final static int MAX_BOARD_HEIGHT = 3;

	private Square[][] board;
	
	public Board() {
		board = new Square[MAX_BOARD_HEIGHT][MAX_BOARD_WIDTH];
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < MAX_BOARD_WIDTH; j++) {
				board[i][j] = new Square();
			}
		}
	}
	
	public Board(int[][][][] board){
		this.board = new Square[MAX_BOARD_HEIGHT][MAX_BOARD_WIDTH];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				addSquare(i, j, new Square(board[i][j]));
			}
		}
	}
	
	
	
	public void addSquare(int x, int y, Square s){
		this.board[x][y] = s;
	}
	
	public Square getSquare(int x, int y){
		return this.board[x][y];
	}
	
	public void removeSquare(int x, int y){
		this.board[x][y] = null;
	}
	
	public boolean cellExistInLine(int line,Cell c){
		return false;
	}
	
	public boolean cellExistInColumn(int column, Cell c){
		return false;
	}
	
	public boolean lineValid(int line){
		int square = 0;
		switch (line) {
		case 0:
		case 1:
		case 2:
			square = 0;
			break;
		case 3:
		case 4:
		case 5:
			square = 1;
			break;
		case 6:
		case 7:
		case 8:
			square = 2;
			break;
		}
		boolean[] existInLine = new boolean[ ( MAX_BOARD_HEIGHT * Square.MAX_SQUARE_HEIGHT + 1 ) ];
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				if(this.board[square][i].getSquare()[square][j] == null){
					continue;
				}
				else if(existInLine[ this.board[square][i].getSquare()[square][j].getValue() ] == true){
					return false;
				}
				existInLine[ this.board[square][i].getSquare()[square][j].getValue() ] = true;
			}
		}
		return true;
	}

	public boolean columnValid(int column){
		int square = 0;
		switch (column) {
		case 0:
		case 1:
		case 2:
			square = 0;
			break;
		case 3:
		case 4:
		case 5:
			square = 1;
			break;
		case 6:
		case 7:
		case 8:
			square = 2;
			break;
		}
		
		boolean[] existInColumn = new boolean[ ( MAX_BOARD_WIDTH * Square.MAX_SQUARE_WIDTH + 1 )];
		for (int i = 0; i < MAX_BOARD_WIDTH; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_WIDTH; j++) {
				if(this.board[i][square].getSquare()[j][square] == null){
					continue;
				}
				else if(existInColumn[ this.board[i][square].getSquare()[j][square].getValue() ] == true){
					return false;
				}
				existInColumn[ this.board[i][square].getSquare()[j][square].getValue() ] = true;
			}
		}
		return true;
	}
	
	
	public boolean boardFull(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if( ! board[i][j].squareFull() ){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean allBoardsValid(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if( ! board[i][j].squareValid() ){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean allLinesValid(){
		for (int i = 0; i < MAX_BOARD_HEIGHT * Square.MAX_SQUARE_HEIGHT ; i++) {
			if( ! lineValid(i) ){
				return false;
			}
		}
		return true;
	}

	public boolean allColumnsValid(){
		for (int i = 0; i < MAX_BOARD_WIDTH * Square.MAX_SQUARE_WIDTH ; i++) {
			if( ! columnValid(i) ){
				return false;
			}
		}
		return true;
	}
	
	public boolean boardValid(){
		return (allBoardsValid() && allColumnsValid() && allLinesValid());
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				for (int k = 0; k < MAX_BOARD_WIDTH; k++) {
					for (int l = 0; l < Square.MAX_SQUARE_WIDTH; l++) {
						if( board[i][k].getSquare()[j][l] == null ){
							sb.append("[ ]");
						}
						else{
							sb.append("[" + board[i][k].getSquare()[j][l].toString() + "]" );							
						}
					}
				}
				sb.append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();
	}
	
	public Stack<int[]> getFreeCells(){
		Stack<int[]> freeCells = new Stack<int[]>();
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				for (int k = 0; k < MAX_BOARD_WIDTH; k++) {
					for (int l = 0; l < Square.MAX_SQUARE_WIDTH; l++) {
						if( board[i][j].getSquare()[k][l] == null ){
							freeCells.add( new int[]{i,j,k,l} );
						}
					}
				}
			}
		}
		return freeCells;
	}
	
	public void generateBoard(){
		do{
			if(  ! resolveBoard() ) {
				System.out.println(this);
				clearBoard();
			}
		}while ( ! boardFull() );
/*		int X,Y,x,y;
		int print = 5000;
		int tuck = 0;
		while( ! boardFull() ){
			if(print-- == 0){
				System.out.println(this.toString());
				print = 1000;
			}
			X = randPosition();
			Y = randPosition();
			x = randPosition();
			y = randPosition();
			
			if( board[X][Y].getSquare()[x][y] != null ){
				continue;
			}
			
			board[X][Y].getSquare()[x][y] = new Cell(randInt());
			if(boardValid()){
				continue;
			}
			board[X][Y].getSquare()[x][y] = null;
		}
*/
	}
	
	private void clearBoard() {
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				for (int k = 0; k < MAX_BOARD_WIDTH; k++) {
					for (int l = 0; l < Square.MAX_SQUARE_WIDTH; l++) {
						board[i][k].getSquare()[j][l] = null;
					}
				}
			}
		}
	}

	public boolean resolveBoard(){
		
		Stack<int[]> freeCells;
		int[] pos;
		final int MAX_TRIES = 10;
		int tries = 0;
		Stack<Integer> cellValues = new Stack<Integer>();
		
		for (int i = 1; i <= Cell.MAX_CELL_VALUE; i++) {
			cellValues.add(i);
		}
		
		do {
			freeCells : {
				freeCells = getFreeCells();
			
				Collections.shuffle(freeCells);
				
				@SuppressWarnings("unchecked")
				Stack<Integer> shuffledCellValues = (Stack<Integer>) cellValues.clone();
				Collections.shuffle(shuffledCellValues);
				
				pos = freeCells.pop();
				while ( ! shuffledCellValues.empty() ) {
					int i = shuffledCellValues.pop().intValue();
					board[pos[0]][pos[1]].getSquare()[pos[2]][pos[3]] = new Cell(i);
					if(boardValid()){
						break freeCells;
					}

					board[pos[0]][pos[1]].getSquare()[pos[2]][pos[3]] = null;

					if(++tries > MAX_TRIES){
						break freeCells;
					}
					System.out.println(toString());
				}
			}
		} while ( ! freeCells.empty() ) ;
		return true;
	}
	
	public static int randInt() {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((Cell.MAX_CELL_VALUE - Cell.MIN_CELL_VALUE) + 1) + Cell.MIN_CELL_VALUE;

	    return randomNum;
	}
	
	public static int randPosition(){
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((2 - 0) + 1) + 0;

	    return randomNum;
		
	}
	
}
