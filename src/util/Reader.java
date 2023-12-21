//12/21/2023 Austen Radigk

package util;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.nio.file.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

public class Reader {

    //Fields
    private List<List<String>> filePathData = new ArrayList<>(); //2D List
    private List<String> originalPaths = new ArrayList<>();
    private List<String> fileTags = new ArrayList<>();


    //Getters
    public List<List<String>> getFilePathData() {return this.filePathData;}
    public List<String> getOriginalPaths() {return this.originalPaths;}
    public List<String> getFileTags() {return this.fileTags;}


	//Constructor
	public Reader(String filePath) throws Exception {
        List<String> filePaths = scanFilePath(filePath);
        String address;
        String orignalAddress;
        String tag;

        //Reads and Saves Each Tasked File
        for (int i = 0; i < filePaths.size(); i++) {
            //Gets Tag
            address = filePaths.get(i);
            if (address.length() < 14) {throw new Exception("Invalid File Name: " + address);}
            orignalAddress = address.substring(0, address.length()-8);
            tag = address.substring(address.length()-8, address.length());

            if (tag.equals("_encrypt") || tag.equals("_decrypt")) {
                //Saves Oringal File Path
                this.originalPaths.add(orignalAddress);

                //Saves File Tag
                this.fileTags.add(tag);

                //Saves File Data
                List<String> rawData = (readFile(filePaths.get(i)));
                this.filePathData.add(formatData(rawData));
            }
        }
	}


    //Scans filePath and Returns File Addresses
    public static List<String> scanFilePath(String filePath) {
        List<String> fileAddresses = new ArrayList();
        Path directory = Paths.get(filePath);
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    fileAddresses.add(file.toString());
                    return FileVisitResult.CONTINUE;
                }
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.println("Failed to Visit File: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println(e);
        }
        return fileAddresses;
    }


	//Reads File
	public static List<String> readFile(String filePath) {
		try {
	        Path path = Paths.get(filePath);
	        List<String> rawData = Files.readAllLines(path);
            System.out.println("\nFile Read: " + filePath);
	        return rawData;
        } 
        catch (NoSuchFileException e) {
            System.out.println("\nInvalid File Path: " + e.getMessage());
            return null;
        }
        catch (Exception e) {
            System.out.println("\nError Reading File: " + e);
            return null;
        }
    }


    //Creates New File
    public void writeFile(List<String> fileData, String filePath, String fileTag) {
        try (FileWriter fileWriter = new FileWriter(filePath + fileTag)) {
            for (String line : fileData) {
                fileWriter.write(line + "\n");
            }
            System.out.println("\nFile Saved at: " + filePath + fileTag);
        } 
        catch (IOException e) {
            System.out.println("\nFailed to Create File: " + e.getMessage());
        }
    } 


    //Formats File (Removes Unnecessary Spacing)
    public static List<String> formatData(List<String> rawData) {
    	List<String> formattedData = new ArrayList<>();
    	StringBuilder formattedLine;
    	boolean foundText = false;

        //Checks for and Returns Null
        if (rawData == null) {return null;}

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
}