//12/06/2023 Austen Radigk

package util;
import util.Translator;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Reader {

	//Constructor
	public Reader() {
	}


	//Reads File (WIP)
	public static List<String> readFile(String filePath) {
		try {
	        Path path = Paths.get(filePath);
	        List<String> rawData = Files.readAllLines(path);
	        return rawData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //Creates/Updates and Writes to File (WIP)
    public void writeFile(List<String> fileData, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath + "_decrypted")) {
            for (String line : fileData) {
                fileWriter.write(line + "\n");
            }
            System.out.println("File created and written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing the file: " + e.getMessage());
        }
    } 


    //Formats File (Removes Unnecessary Spacing)
    public static List<String> formatData(List<String> rawData) {
    	List<String> formattedData = new ArrayList<>();
    	StringBuilder formattedLine;
    	boolean foundText = false;

    	//Builds formattedData
    	for (String line : rawData) {
    		formattedLine = new StringBuilder();
    		for (int i = 0; i < line.length(); i++) {
    			boolean isWhitespace = Character.isWhitespace(line.charAt(i));
    			if (!isWhitespace && !foundText) {
    				formattedLine.append(line.charAt(i));
    				foundText = true;
    			}
    			else if (foundText) {formattedLine.append(line.charAt(i));}
    		}
    		formattedData.add(formattedLine.toString());
    		foundText = false;
    	}

    	return formattedData;
    }


    //Finds Group
    public static List<String> findGroup(List<String> fileData, String header) {
    	List<String> group = new ArrayList<>();
    	int separatorCount = 0;
    	boolean foundGroup = false;

    	//Finds & Builds Group
    	for (String line : fileData) {
    		if (line.equals(header)) {
    			group.add(line);
    			foundGroup = true;
    		}
    		else if (foundGroup) {
    			group.add(line);
    			if (Translator.isSeparator(line)) {
    				separatorCount ++;
    				if (separatorCount > 1) {break;}
    			}
    		}
    	}

    	return group;
    }


    //Finds Section
    public static List<String> findSection(List<String> fileData, String header) {
    	List<String> section = new ArrayList<>();
    	boolean foundSection = false;

    	//Finds & Builds Section
    	for (String line : fileData) {
    		if (line.equals(header)) {
    			section.add(line);
    			foundSection = true;
    		}
    		else if (foundSection) {
    			if (line == "") {break;}
    			section.add(line);
    		}
    	}

    	return section;
    }
}