//11/30/2023 Austen Radigk

package util;
import util.Encryptor;
import util.Decryptor;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Translator {

	//Constructor
	public Translator() {
	}


	//Ecrypts formattedData 
	public static List<String> encryptData(List<String> formattedData) {
		Encryptor encryptor = new Encryptor();
		List<String> encryptedData = new ArrayList<>();
		String encryptedLine;

		//Builds encryptedData
		for (String line : formattedData) {
			boolean separatorFound = (line == "---------------------------------------"); //TEMP
			if (!separatorFound) {
				encryptedLine = encryptor.encryptString(line, "key"); //Using Set Key For Now
				encryptedData.add(encryptedLine);
			}
			else if (separatorFound) {
				encryptedData.add("---------------------------------------"); //TEMP
			}
		}

		return encryptedData;
	}


	//Decrypts (formatted/group/section)Data
	public static List<String> decryptData(List<String> encryptedData) {
		Decryptor decryptor = new Decryptor();
		List<String> decryptedData = new ArrayList<>();
		String decryptedLine;

		//Builds decryptedData
		for (String line : encryptedData) {
			boolean separatorFound = (line == "---------------------------------------"); //TEMP
			if (!separatorFound) {
				decryptedLine = decryptor.decryptString(line, "key"); //Using Set Key For Now
				decryptedData.add(decryptedLine);
			}
			else if (separatorFound) {
				decryptedData.add("---------------------------------------"); //TEMP
			}
		}

		return decryptedData;
	}
}