import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    @Test
    void printBoard() {
        Sudoku sudokuTest = new Sudoku(new int[3][3]);
        //a new board will just be zeros. make sure new board prints that way
        assertEquals(sudokuTest.printBoard(),"000\n000\n000");
    }

    @Test
    void solve() {
    }
}