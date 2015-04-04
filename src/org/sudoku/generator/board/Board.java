package org.sudoku.generator.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


public class Board {
	final static int MAX_BOARD_WIDTH = 3;
	final static int MAX_BOARD_HEIGHT = 3;
	
	final static int MAX_POSITION_BOARD = (int)(Math.sqrt(MAX_BOARD_WIDTH) * Math.sqrt(MAX_BOARD_HEIGHT));
	final static int POSITION_AXES = 4;
	
	/**
	 * How many times we try to resolve the board before we randomly delete RELEASE_NUMBERS
	 */
	final static int RESOLVE_MAX_TRIES = 100;
	
	/**
	 * How many numbers we randomly delete when we are stuck
	 */
	final static int RELEASE_NUMBERS = 2;

	
	Random randomValue = new Random();

	Random randomPosition = new Random();

	Random randomFreeCell = new Random();

	Stack<int[]> freeCellsStack = new Stack<int[]>();

	Stack<int[]> lastInsertCellStack = new Stack<int[]>();
	
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
	
	public boolean boardCompleted(){
		return boardFull() && boardValid();
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
						}
					}
				}
			}
		}
		return freeCells;		
	}
		
	/**
	 * Try to generate a valid solution
	 * We start with an empty board
	 * 
	 * while(board is not full and valid)
	 * 	insert random value into random free position
	 * 	continue if the board is valid
	 * 	delete the inserted number if not valid
	 * 	if we have deleted the last max_tries numbers without any success
	 * 		we release randomly valid release_numbers  from occupied positions
	 * 
	 */
	public void generateValidBoard(){
		int tries = 0;
		while( ! boardCompleted() ){

			freeCellsStack = getFreeCellsStack();

			insertRandomValueIntoRandomFreePosition();
			
			if(boardValid()) continue;
			
			deleteCell(lastInsertCellStack.pop());
			
			if(++tries > RESOLVE_MAX_TRIES){
				releaseRandomCells(getFreeCellsStack());
				tries = 0;
			}
		}
	}
	
	private void releaseRandomCells(Stack<int[]> stack) {
		for (int i = 0; i <= RELEASE_NUMBERS; i++) {
			int random  = randomFreeCell.nextInt(lastInsertCellStack.size());
			deleteCell(lastInsertCellStack.get(random));
			lastInsertCellStack.remove(random);
		}
	}

	/**
	 * 
	 * @return a random integer between MIN_CELL_VALUE and MAX_CELL_VALUE
	 */
	public int getRandomValue(){
		return ( randomValue.nextInt(Cell.MAX_CELL_VALUE) + 1);
	}
	
	public void insertRandomValueIntoRandomPosition(){
		int[] randomPosition = getRandomPosition();
		int randomValue = getRandomValue();
		
		insertValue(randomPosition[0],
					randomPosition[1],
					randomPosition[2],
					randomPosition[3],
					randomValue);
		lastInsertCellStack.push(randomPosition);
	}

	public void insertRandomValueIntoRandomFreePosition(){
		int[] randomPosition = freeCellsStack.get(randomFreeCell.nextInt(freeCellsStack.size()));
		int randomValue = getRandomValue();
		
		insertValue(randomPosition[0],
					randomPosition[1],
					randomPosition[2],
					randomPosition[3],
					randomValue);
		
		lastInsertCellStack.push(randomPosition);		
	}
	
	public void insertValue(int X, int Y, int x, int y, int value){
		this.board[X][Y].addCell(x, y, new Cell(value));
	}
	/**
	 * 
	 * @return an array of 4 integer materializing a position on the board
	 */
	public int[] getRandomPosition(){
		return new int[]{
				randomPosition.nextInt(Board.POSITION_AXES), 
				randomPosition.nextInt(Board.POSITION_AXES),
				randomPosition.nextInt(Board.POSITION_AXES),
				randomPosition.nextInt(Board.POSITION_AXES)
				};
	}
	
	public Cell getCell(int X, int Y, int x, int y){
		return this.board[X][Y].getSquare()[x][y];
	}
	
	public boolean isCellNull(int X, int Y, int x, int y){
		return this.getCell(X, Y, x, y) == null;
	}
	
	private void deleteCell(int X, int Y, int x, int y){
		this.board[X][Y].getSquare()[x][y] = null;
	}

	private void deleteCell(int[] pos){
		deleteCell(pos[0],pos[1],pos[2],pos[3]);
	}
	
}
