import constants.Constants;
import org.apache.commons.io.FilenameUtils;

import java.io.*;

public class Main {

    public static void main(String[] args) {


        File dir = new File("resources/puzzles");
        for (File file : dir.listFiles()) {
            //try-with-resources will auto close the file, even on Exception
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                //Get filepath, as it is used in multiple locations
                String selectedFilePath = file.getAbsolutePath();
                //Make sure the input file is a txt file, else, skip it
                if (!FilenameUtils.getExtension(selectedFilePath).equals("txt")) {
                    System.out.println("Sorry, please use a txt file!");
                    return;
                } else {
                    FileToArrayConverter converter = new FileToArrayConverter();
                    Sudoku sudoku = new Sudoku(converter.convertFile(bufferedReader));
                    if (sudoku.solve()) {
                        //The file for the solution is created in resources/solutions
                        //with the original fileName+.sln.txt
                        String solutionFilePath = "resources/solutions/"+FilenameUtils.getBaseName(selectedFilePath)+ Constants.SOLUTION_FILE;
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(solutionFilePath))) {
                            bufferedWriter.write(sudoku.printBoard());
                        }
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
        System.out.println("All files have been run! Please check resources/solutions for the answers!");
    }
}
