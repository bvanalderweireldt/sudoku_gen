package org.sudoku.generator.board;

public class Cell {
	final static int MAX_CELL_VALUE = 9;
	final static int MIN_CELL_VALUE = 1;
	
	private int value;
	
	public Cell(int value) {
		if(value <= MAX_CELL_VALUE && value >= MIN_CELL_VALUE){
			this.value = value;
		}
		else{
			throw new IllegalArgumentException("Error Cell values must be between "+MIN_CELL_VALUE+" and "+MAX_CELL_VALUE);
		}
	}
	
	public String toString() {
		return Integer.toString(this.value);
	}
	
	public int getValue(){
		return this.value;
	}
	
	public boolean equals(Cell obj) {
		if(this.value == obj.getValue()){
			return true;
		}
		return false;
	}
}
