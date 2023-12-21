 //12/21/2023 Austen Radigk

package util;
import java.util.List;
import java.util.ArrayList;
import util.Encryptor;
import util.Decryptor;

public class Translator {

	//Field
	List<List<String>> newFileData = new ArrayList();
	List<String> newFileTags = new ArrayList();


	//Getters
	public List<List<String>> getNewFileData() {return this.newFileData;}
	public List<String> getNewFileTags() {return this.newFileTags;}


	//Constructor
	public Translator(List<List<String>> filePathData, List<String> fileTags) throws Exception {
		//Runs Through Each File Address
		for (int i = 0; i < filePathData.size(); i++) {
			//Encrypts Data
			if ((fileTags.get(i)).equals("_encrypt")) {
				List<String> encryptedData = encryptData(filePathData.get(i));
				if (encryptedData != null) {
					newFileData.add(encryptedData);
					newFileTags.add("_encrypted");
				} else if (encryptedData == null) {
					throw new Exception("Failed to Encrypt Data");
				}
			}

			//Decrypts Data
			else if ((fileTags.get(i)).equals("_decrypt")) {
				List<String> decryptedData = decryptData(filePathData.get(i));
				if (decryptedData != null) {
					newFileData.add(decryptedData);
					newFileTags.add("_decrypted");
				} else if (decryptedData == null) {
					throw new Exception("Failed to Decrypt Data");
				}
			}
		}
	}


	//Ecrypts formattedData 
	public static List<String> encryptData(List<String> formattedData) {
		Encryptor encryptor = new Encryptor();
		List<String> encryptedData = new ArrayList<>();
		String encryptedLine;
		boolean separatorFound;

		//Checks for and Returns Null
		if (formattedData == null) {return null;}

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

		//Checks for and Returns Null
		if (encryptedData == null) {return null;}

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
}