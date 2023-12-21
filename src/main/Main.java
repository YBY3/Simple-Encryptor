//12/21/2023 Austen Radigk

package main;
import java.util.List;
import java.util.ArrayList;
import util.Reader;
import util.Translator;

public class Main {

    //Main Method
    public static void main(String[] args) {
        try {
            //Reads Data
            Reader reader = new Reader("data"); //Set File Path
            List<List<String>> filePathData = reader.getFilePathData();
            List<String> orignalPaths = reader.getOriginalPaths();
            List<String> fileTags = reader.getFileTags();

            //Translates Data
            Translator translator = new Translator(filePathData, fileTags);
            List<List<String>> newFileData = translator.getNewFileData();
            List<String> newFileTags = translator.getNewFileTags();

            //Saves New File Data
            for (int i = 0; i < newFileData.size(); i++) {
                reader.writeFile(newFileData.get(i), orignalPaths.get(i), newFileTags.get(i));
            }
        }
        catch (Exception errorMessage) {System.out.println(errorMessage);}
    }
}