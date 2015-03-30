package org.sudoku.generator.board;

public class Square {
	final static int MAX_SQUARE_WIDTH = 3;
	final static int MAX_SQUARE_HEIGHT = 3;

	private Cell[][] square;

	public Square() {
		square = new Cell[MAX_SQUARE_HEIGHT][MAX_SQUARE_WIDTH];
	}
	
	public Cell getCell(int x, int y){
		return this.square[x][y];
	}
	
	public void addCell(int x, int y, Cell c){
		this.square[x][y] = c;
	}
	
	public void removeCell(int x, int y){
		this.square[x][y] = null;
	}
	
	public boolean cellExistsInSquare(Cell c){
		for (int i = 0; i <  this.square.length; i++) {
			for (int j = 0; j < square[this.square.length].length; j++) {
				if(square[i][j] == null){
					continue;
				}
				else if (square[i][j].getValue() == c.getValue()) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	public boolean squareValid(){
		boolean[] existInSquare = new boolean[ ( MAX_SQUARE_WIDTH * Square.MAX_SQUARE_HEIGHT + 1 ) ];
		for (int i = 0; i < square.length; i++) {
			for (int j = 0; j < square[i].length; j++) {
				if(square[i][j] == null){
					continue;
				}
				if(existInSquare[square[i][j].getValue()] == true){
					return false;
				}
				existInSquare[square[i][j].getValue()] = true;
			}
		}
		return true;
	}
	
	public boolean squareFull(){
		for (int i = 0; i <  this.square.length; i++) {
			for (int j = 0; j < square[i].length; j++) {
				if(square[i][j] == null){
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <  this.square.length; i++) {
			for (int j = 0; j < square[this.square.length].length; j++) {
				if(square[i][j] == null){
					sb.append("[0]");
				}
				else {
					sb.append("[" + square[i][j] + "]");
				}				
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	
	public Cell[][] getSquare(){
		return this.square;
	}
}
