import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        File dir = new File("resources");
        for (File file : dir.listFiles()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String selectedFilePath = file.getAbsolutePath();
                if (!FilenameUtils.getExtension(selectedFilePath).equals("txt")) {
                    System.out.println("Sorry, please select a txt file!");
                    return;
                } else {
                    FileToArrayConverter converter = new FileToArrayConverter();
                    Sudoku sudoku = new Sudoku(converter.convertFile(bufferedReader));
                    if (sudoku.solve()) {
                        System.out.println(sudoku.toString());
                    } else {
                        System.out.println("This puzzle is unsolvable! The file name is: "+file.getName());
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
}
