//12/18/2023 Austen Radigk

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


	//Constructor
	public Translator() throws Exception {
		List<String> fileAddresses = reader.scanFilePath("data");
		for (String address : fileAddresses) {
			String orignalAddress = address.substring(0, address.length()-8);
			String tag = address.substring(address.length()-8, address.length());
			if (tag.equals("_encrypt")) {
				List<String> rawData = reader.readFile(address);
				List<String> formattedData = reader.formatData(rawData);
				List<String> encryptedData = encryptData(formattedData);
				if (encryptedData != null) {
					reader.writeFile(encryptedData, orignalAddress, "_encrypted");
				} else if (encryptedData == null) {
					throw new Exception("Failed to Encrypt Data");
				}
			}
			else if (tag.equals("_decrypt")) {
				List<String> rawData = reader.readFile(address);
				List<String> decryptedData = decryptData(rawData);
				if (decryptedData != null) {
					reader.writeFile(decryptedData, orignalAddress, "_decrypted");
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