import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class FileToArrayConverter {

    private static final int SIZE = 9;

    public int[][] convertFile(BufferedReader bufferedReader) throws IOException, Exception {
        String line;
        int [][] array = new int[SIZE][SIZE];
        int row = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String [] tokens = line.split("");
            if (tokens.length>SIZE) {
                throw new Exception("The length of the line is too long for the array");
            }
            for (int i=0;i<tokens.length; i++) {
                //if the value in the location is not a number (in the instance of this test, it can be an 'X')
                //replace with zero (0), as this can not be on the board when finished, and still holds an int value
                if (!StringUtils.isNumeric(tokens[i])) {
                    array[row][i] = 0;
                } else {
                    array[row][i] = Integer.parseInt(tokens[i]);
                }
            }
            row ++;
        }
        return array;
    }
}
