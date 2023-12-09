//12/06/2023 Austen Radigk

package util;
import util.Encryptor;
import util.Decryptor;
import util.Reader;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Translator {

	//Fields
	private Reader reader = new Reader();
	private List<String> fileData;
	private String filePath;


	//Constructor
	public Translator(String filePath, String instruction) {
		if (instruction == "encrypt") {
			List<String> rawData = reader.readFile(filePath);
			List<String> formattedData = reader.formatData(rawData);
			this.fileData = encryptData(formattedData);
		}
		else if (instruction == "decrypt") {
			List<String> encryptedData = reader.readFile(filePath);
			this.fileData = decryptData(encryptedData);
		}
		this.filePath = filePath;
	}


	//Ecrypts formattedData 
	public static List<String> encryptData(List<String> formattedData) {
		Encryptor encryptor = new Encryptor();
		List<String> encryptedData = new ArrayList<>();
		String encryptedLine;
		boolean separatorFound;

		//Builds encryptedData
		for (String line : formattedData) {
			separatorFound = isSeparator(line);
			if (!separatorFound) {
				encryptedLine = encryptor.encryptString(line, "key"); //Using Set Key For Now
				encryptedData.add(encryptedLine);
			}
			else if (separatorFound) {
				encryptedData.add(line);
			}
		}

		return encryptedData;
	}


	//Decrypts encryptedData
	public static List<String> decryptData(List<String> encryptedData) {
		Decryptor decryptor = new Decryptor();
		List<String> decryptedData = new ArrayList<>();
		String decryptedLine;
		boolean separatorFound;

		//Builds decryptedData
		for (String line : encryptedData) {
			separatorFound = isSeparator(line);
			if (!separatorFound) {
				decryptedLine = decryptor.decryptString(line, "key"); //Using Set Key For Now
				decryptedData.add(decryptedLine);
			}
			else if (separatorFound) {
				decryptedData.add(line);
			}
		}

		return decryptedData;
	}


	//Determines if Line is a Separator
	public static boolean isSeparator(String str) {
		boolean isEmpty = str.isEmpty();

		if (isEmpty) {
			return false;
		}
		else if (!isEmpty) {
			boolean isWhitespace;
			char firstChar = str.charAt(0);
			
			//Checks for Separator
			for (int i = 0; i < str.length(); i++) { 
				isWhitespace = Character.isWhitespace(str.charAt(i));
				if (str.charAt(i) != firstChar && !isWhitespace) {
					return false;
				}

			}
		}

		return true;
	}


	//Returns Selected Output Data
	public List<String> getOutput(String type, String header) {
		List<String> fileData = this.fileData;
		List<String> outputData = new ArrayList<String>();

		if (type == "save") { //Save File
            reader.writeFile(fileData, filePath);
        }
        if (type == "group") {
            for (String line:reader.findGroup(fileData, header)) {
                outputData.add(line);
            }
        }
        else if (type == "section") {
            for (String line:reader.findSection(fileData, header)) {
                outputData.add(line);
            }
        }

        return outputData;
	}
}