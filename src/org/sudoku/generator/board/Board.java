package org.sudoku.generator.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class Board {
	final static int MAX_BOARD_WIDTH = 3;
	final static int MAX_BOARD_HEIGHT = 3;
	
	final static int MAX_POSITION_BOARD = (int)(Math.sqrt(MAX_BOARD_WIDTH) * Math.sqrt(MAX_BOARD_HEIGHT));
	final static int POSITION_AXES = 4;
	public enum SubsetType {
		LINE,
		COLUMN
	}
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
	
	public boolean subsetValid(int squarePos, int cellPos, SubsetType type ){
		boolean[] valids = new boolean[ ( MAX_BOARD_HEIGHT*Square.MAX_SQUARE_HEIGHT ) + 1 ];
		Cell c = null;
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT ; j++) {
				
				switch (type) {
				case COLUMN:					
					c = getCell(i, squarePos, j, cellPos);
					break;
				case LINE:
					c = getCell(squarePos ,i ,cellPos ,j);
					break;
				}
				
				
				if(c == null) continue;
				
				if(valids[c.getValue()] == true) return false;
				
				valids[c.getValue()] = true;
			}
		}
		return true;		
	}
	
	public boolean lineValid(int squareHeightPos, int cellHeightPos){
		return subsetValid(squareHeightPos, cellHeightPos, SubsetType.LINE);
	}

	/**
	 * 
	 * @param column, column number from 0
	 */
	public boolean columnValid(int squareWidthPos, int cellWidthPos){
		return subsetValid(squareWidthPos, cellWidthPos, SubsetType.COLUMN);
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
	
	
	private boolean allValid(SubsetType subset){
		for (int i = 0; i < MAX_BOARD_WIDTH ; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_WIDTH ; j++) {
				switch (subset) {
				case COLUMN:
					if( ! columnValid(i,j) ) return false;
					break;
				case LINE:
					if( ! lineValid(i,j) ) return false;
					break;
				}
			}
		}
		return true;
	}
	public boolean allLinesValid(){
		return allValid(SubsetType.LINE);
	}

	public boolean allColumnsValid(){
		return allValid(SubsetType.COLUMN);
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
	
	public Stack<int[]> getFreeCellsStack(){
		Stack<int[]> freeCells = new Stack<>();
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				for (int k = 0; k < MAX_BOARD_WIDTH; k++) {
					for (int l = 0; l < Square.MAX_SQUARE_WIDTH; l++) {
						if( board[i][j].getSquare()[k][l] == null ){
							freeCells.add(new int[]{i,j,k,l});
						}
					}
				}
			}
		}
		return freeCells;		
	}
	
	public ArrayList<int[]> getFreeCellsList(){
		return new ArrayList<int[]>(Arrays.asList(getFreeCellsArray())); 
	}
	
	public int[][] getFreeCellsArray(){
		int[][] freeCells = new int[MAX_POSITION_BOARD][POSITION_AXES];
		int freeCellsIndex = 0;
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				for (int k = 0; k < MAX_BOARD_WIDTH; k++) {
					for (int l = 0; l < Square.MAX_SQUARE_WIDTH; l++) {
						if( board[i][j].getSquare()[k][l] == null ){
							freeCells[freeCellsIndex] = new int[]{i,j,k,l};
							i++;
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
	}
	
	private void clearBoard() {
		for (int i = 0; i < MAX_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Square.MAX_SQUARE_HEIGHT; j++) {
				for (int k = 0; k < MAX_BOARD_WIDTH; k++) {
					for (int l = 0; l < Square.MAX_SQUARE_WIDTH; l++) {
						board[i][j].getSquare()[k][l] = null;
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
				tries = 0;
				freeCells = getFreeCellsStack();
			
				Collections.shuffle(freeCells);
				
				@SuppressWarnings("unchecked")
				Stack<Integer> shuffledCellValues = (Stack<Integer>) cellValues.clone();
				Collections.shuffle(shuffledCellValues);
				
				pos = freeCells.pop();
				while ( ! shuffledCellValues.empty() ) {
					int i = shuffledCellValues.pop().intValue();
					System.out.println(""+pos[0]+""+pos[1]+pos[2]+pos[3]);
					board[pos[0]][pos[1]].getSquare()[pos[2]][pos[3]] = new Cell(i);
					if(boardValid()){
						break freeCells;
					}
					
					board[pos[0]][pos[1]].getSquare()[pos[2]][pos[3]] = null;
					tries++;
					if(tries > MAX_TRIES){
						System.out.println(toString());
						int[] delCell = freeCells.pop();
						board[delCell[0]][delCell[1]].getSquare()[delCell[2]][delCell[3]] = null;
						break freeCells;
					}
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
	
	public Cell getCell(int X, int Y, int x, int y){
		return this.board[X][Y].getSquare()[x][y];
	}
	
	public boolean isCellNull(int X, int Y, int x, int y){
		return this.getCell(X, Y, x, y) == null;
	}
	
}
