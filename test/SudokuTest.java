import constants.Constants;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static constants.Constants.SUBSECTION_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    private static final int[][] POSITIVE_TEST_BOARD = new int[][] {
            {0,0,0,1,5,0,0,7,0},
            {1,0,6,0,0,0,8,2,0},
            {3,0,0,8,6,0,0,4,0},
            {9,0,0,4,0,0,5,6,7},
            {0,0,4,7,0,8,3,0,0},
            {7,3,2,0,0,6,0,0,4},
            {0,4,0,0,8,1,0,0,9},
            {0,1,7,0,0,0,2,0,8},
            {0,5,0,0,3,7,0,0,0},
    };

    private static final int[][] NEGATIVE_TEST_BOARD = new int[][] {
            {2,0,0,1,5,0,0,7,0},
            {1,0,6,0,0,0,8,2,0},
            {3,0,0,8,6,0,0,4,0},
            {9,0,0,4,0,0,5,6,7},
            {0,0,4,7,0,8,3,0,0},
            {7,3,2,0,0,6,0,0,4},
            {0,4,0,0,8,1,0,0,9},
            {0,1,7,0,0,0,2,0,8},
            {0,5,0,0,3,7,0,0,0},
    };

    private int[] sortedLine = {1,2,3,4,5,6,7,8,9};

    @Test
    void printBoard() {
        Sudoku sudokuTest = new Sudoku(new int[3][3]);
        //a new board will just be zeros. make sure new board prints that way
        assertEquals(sudokuTest.printBoard(),"000\n000\n000");
    }

    @Test
    void solve() {
        Sudoku sudoku = new Sudoku(POSITIVE_TEST_BOARD);
        //Make sure method comes back true
        assertTrue(sudoku.solve());

        for (int row = 0; row < Constants.SIZE; row++) {
            //Need to make an int[] for the column to test
            //that the column also has 1 through 9
            int[] columnArray = new int[Constants.SIZE];
            int[] subSectionArray = new int[Constants.SIZE];
            for (int column=0; column < Constants.SIZE; column++) {
                columnArray[column] = sudoku.getBoard()[row][column];

                //Start of verifying subsection. need to write values into array
                //in order to verify that it contains all 9 single digit numbers.

                //Using the same style of code used to verify if the number was valid
                //in the Sudoku class
                int subRow = row - row % SUBSECTION_SIZE;
                int subCol = column - column % SUBSECTION_SIZE;

                for (int i = subRow; i < subRow + SUBSECTION_SIZE; i++) {
                    for (int j = subCol; j < subCol + SUBSECTION_SIZE; j++) {
                        subSectionArray[j] = sudoku.getBoard()[i][j];
                    }
                }
            }
            //verify both row and column
            assertTrue(isArrayComplete(subSectionArray));
            assertTrue(isArrayComplete(sudoku.getBoard()[row]));
            assertTrue(isArrayComplete(columnArray));
        }
    }

    @Test
    void solveFail() {
        //The NEGATIVE_TEST_BOARD is unsolvable, due to the [0][0]
        //position holding the value of 2. That is the only difference between the boards
        Sudoku sudoku = new Sudoku(NEGATIVE_TEST_BOARD);
        assertFalse(sudoku.solve(), "This board is unsolvable, so test should come back false");
    }

    //method returns true if given method has 1 through 9 after sorting it
    //Returned based off of static variable at beginning of test class
    private boolean isArrayComplete(int[] array) {
        int[] copy = Arrays.copyOf(array, Constants.SIZE);
        Arrays.sort(copy);
        return Arrays.equals(sortedLine, copy);
    }
}

