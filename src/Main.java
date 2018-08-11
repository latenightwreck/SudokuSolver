import org.apache.commons.io.FilenameUtils;
import java.io.*;

public class Main {

    public static void main(String[] args) {


        File dir = new File("resources");
        for (File file : dir.listFiles()) {
            Sudoku sudoku = new Sudoku();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String selectedFilePath = file.getAbsolutePath();
                if (!FilenameUtils.getExtension(selectedFilePath).equals("txt")) {
                    System.out.println("Sorry, please select a txt file!");
                    return;
                } else {
                    sudoku.printFile(bufferedReader);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
}
