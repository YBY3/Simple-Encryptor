//11/26/2023 Austen Radigk

package util;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Reader {

	//Fields
	List<String> fileData;


	//Getters
	public List<String> getFileData() {return fileData;}


	//Constructor
	public Reader() {
	}


	//Reads File
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


    //Finds Group (WIP)
    public static List<String> findGroup(List<String> formattedData, String groupHeader) {
    	List<String> group = new ArrayList<>();
    	int separatorCount = 0;
    	boolean foundGroup = false;

    	//Finds & Builds Group
    	for (String line : formattedData) {
    		if (line.equals(groupHeader)) {
    			group.add(line);
    			foundGroup = true;
    		}
    		else if (foundGroup) {
    			group.add(line);
    			if (line.equals("---------------------------------------")) {
    				separatorCount ++;
    				if (separatorCount > 1) {break;}
    			}
    		}
    	}

    	return group;
    }


    //Finds Section
    public static List<String> findSection(List<String> formattedData, String sectionHeader) {
    	List<String> section = new ArrayList<>();
    	boolean foundSection = false;

    	//Finds & Builds Section
    	for (String line : formattedData) {
    		if (line.equals(sectionHeader)) {
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