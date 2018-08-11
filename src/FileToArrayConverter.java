import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

import static constants.Constants.SIZE;

public class FileToArrayConverter {

    public int[][] convertFile(BufferedReader bufferedReader) throws IOException, Exception {
        String line;
        int [][] array = new int[SIZE][SIZE];
        int row = 0;
        while ((line = bufferedReader.readLine()) != null) {
            //if there are too many rows in the file, throw error
            if(row>=SIZE) {
                throw new Exception("There are too many rows in the file for this array");
            }
            String [] tokens = line.split("");
            //if there are too many characters in a line (columns) throw error
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
