import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileToArrayConverterTest {

    private FileToArrayConverter converter;

    private static final int[][] TEST_BOARD = new int[][] {
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

    @BeforeEach
    void init() {
        converter = new FileToArrayConverter();
    }

    @Test
    void convertFile() throws Exception {
        //Code is to write 2d array into a stringBuilder for testing.
        //This way, we can compare the 2 array sets
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < TEST_BOARD.length; i++) {
            for(int j = 0; j < TEST_BOARD.length; j++) {
                builder.append(TEST_BOARD[i][j]);
            }
            if (i != TEST_BOARD.length-1) {
                builder.append("\n");
            }
        }

        BufferedReader reader = new BufferedReader(new StringReader(builder.toString()));
        int[][] matrix = converter.convertFile(reader);
        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(TEST_BOARD[i], matrix[i]);
        }
    }

    //test to make sure Exception is thrown for too many rows
    @Test
    void tooManyRowsTest() {
        BufferedReader reader = new BufferedReader(new StringReader("1\n2\n3\n4\n5\n6\n7\n8\n9\n10"));
        Throwable exception = assertThrows(Exception.class, ()->{
            converter.convertFile(reader);
        });
        assertEquals("There are too many rows in the file for this array", exception.getMessage());
    }

    //test to make sure Exception is thrown for too many columns
    @Test
    void tooManyColumnsTest() {
        BufferedReader reader = new BufferedReader(new StringReader("1234567890"));
        Throwable exception = assertThrows(Exception.class, ()->{
            converter.convertFile(reader);
        });
        assertEquals("The length of the line is too long for the array", exception.getMessage());
    }
}