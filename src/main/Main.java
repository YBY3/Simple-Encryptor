//12/22/2023 Austen Radigk @YBY3

package main;
import java.util.List;
import java.util.ArrayList;
import util.Reader;
import util.Translator;

public class Main {

    //Feilds
    private static List<List<String>> filePathData;
    private static List<String> filePaths;
    private static List<String> fileTags;


    //Main Method
    public static void main(String[] args) {
        try {
            //Reads Data
            System.out.println(""); //Spacer
            Reader reader = new Reader("data"); //Set File Path
            filePathData = reader.getFilePathData();
            filePaths = reader.getOriginalPaths();
            fileTags = reader.getFileTags();

            //Translates Data
            Translator translator = new Translator(filePathData, fileTags);
            filePathData = translator.getNewFileData();
            fileTags = translator.getNewFileTags();

            //Saves New File Data
            System.out.println(""); //Spacer
            for (int i = 0; i < filePathData.size(); i++) {
                reader.writeFile(filePathData.get(i), filePaths.get(i), fileTags.get(i));
            }
            System.out.println(""); //Spacer
        }
        catch (Exception errorMessage) {System.out.println(errorMessage);}
    }
}