import java.util.Arrays;

import static constants.Constants.*;

public class Sudoku {

    public Sudoku(int[][] board) {
        this.board = board;
    }

    private int[][] board;

    public int[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Sudoku{" +
                "board=" + Arrays.deepToString(board) +
                '}';
    }

    public String printBoard() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                builder.append(board[i][j]);
            }
            //if its not the last row, add a new line
            //no need for extra new lines
            if (i != board.length-1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    //if the number is in the row, return true, else, return false
    private boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    //if the number is in the column, return true, else, return false
    private boolean isInColumn(int column, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    //checks to see if number is valid in the subsection, based off of SUBSECTION_SIZE var
    private boolean isInSubSection(int row, int column, int number) {
        //getting starting row and column based off of subsection size
        int subRow = row - row % SUBSECTION_SIZE;
        int subCol = column - column % SUBSECTION_SIZE;

        //double for loop to go through the sub section, hence why it is subRow+SUBSECTION_SIZE
        for (int i = subRow; i < subRow + SUBSECTION_SIZE; i++) {
            for (int j = subCol; j < subCol + SUBSECTION_SIZE; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    //If the number is NOT in the row, column and subsection, return true
    private boolean isValid(int row, int column, int number) {
        return !isInRow(row, number) && !isInColumn(column, number) && !isInSubSection(row, column, number);
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                //if the value of the location is an empty value (0)
                //run the logic
                if (board[row][column] == EMPTY) {
                    //run through all single digit numbers
                    for (int number = 1; number <= SIZE; number++) {
                        //if the number fits into the Sudoku constraints
                        if (isValid(row, column, number)) {
                            board[row][column] = number;
                            //recursively backtracking through the board
                            if (solve()) {
                                return true;
                            }
                            board[row][column] = EMPTY;
                        }
                    }
                    //No solution for any number on that location
                    return false;
                }
            }
        }
        //End of method if all values are set
        return true;
    }
}
