//11/16/2023 Austen Radigk

package utils;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.StringBuilder;


public class Encryptor {
		
	//Fields
	private String encrpytedText;
	public static final List<Character> KEYBOARD_LOWERCASE = 
			Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
			              'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
			              'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', 
			              '5', '6', '7', '8', '9', '0');
	public static final List<Character> KEYBOARD_UPPERCASE =
			Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
					      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
					      'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#', '$', 
					      '%', '^', '&', '*', '(', ')');
			

	//Getters
	public String getEncrpytedText() {return encrpytedText;}


	//Constructor (String)
	public Encryptor (String plainText) {
	}

	
	//Separates LowerCase and UpperCase characters into Index Array
	public static List<Integer> getIndexArray(String str) {
		List<Integer> lowerIndexArray = new ArrayList<>();
		List<Integer> upperIndexArray = new ArrayList<>();
		List<Integer> upperIndexPositionArray = new ArrayList<>();
		List<Integer> indexArray = new ArrayList<>();

		//Builds Lower & Upper Case Index Arrays
		for (int i = 0; i < str.length(); i++) { 
			for (int n = 0; n < 36; n++) { 
				boolean lowerMatchFound = (str.charAt(i) == KEYBOARD_LOWERCASE.get(n));
				boolean upperMatchFound = (str.charAt(i) == KEYBOARD_UPPERCASE.get(n));
				if (lowerMatchFound) {lowerIndexArray.add(n);}
				else if (upperMatchFound) {
					upperIndexArray.add(n);
					upperIndexPositionArray.add(i);
				}
			}
		}

		//Adds Lowercase Index's to indexArray
		for (int i = 0; i < lowerIndexArray.size(); i++) { 
			indexArray.add(lowerIndexArray.get(i));
		}

		indexArray.add(-1); //Separator

		//Adds Uppercase Index's & Uppercase Index's Position to indexArray
		for (int i = 0; i < upperIndexArray.size(); i++) { 
			indexArray.add(upperIndexArray.get(i));
			indexArray.add(upperIndexPositionArray.get(i));
		}

		return indexArray;
	}


	//Gets Matrix Character Through Row Index and Key Index
	public static char getMatrixEntry(int rowIndex, int keyIndex, String caseType) {
		char matrixCharacter = '0';
		int matrixEntryIndex = (rowIndex + keyIndex) % 36;
		if (caseType == "upper") {matrixCharacter = KEYBOARD_UPPERCASE.get(matrixEntryIndex);}
		else if (caseType == "lower") {matrixCharacter = KEYBOARD_LOWERCASE.get(matrixEntryIndex);}
		return matrixCharacter;
	}

	
	//Encrypts Lower and Upper Case Text and Returns it as a String
	public static String encryptIndexArray(List<Integer> intArray, String key) {
		String caseType = "upper"; //Translating Lowercase to Uppercase
		int keyIndex = 0;
		List<Integer> keyIndexArray = new ArrayList<>();
		StringBuilder encryptedText = new StringBuilder();

		//Builds keyIndexArray
		for (int i = 0; i < key.length(); i++) { 
			for (int n = 0; n < 36; n++) { 
				boolean lowerMatchFound = (key.charAt(i) == KEYBOARD_LOWERCASE.get(n));
				if (lowerMatchFound) {keyIndexArray.add(n);}
			}
		}

		//Builds encryptedText
		for (int i = 0; i < intArray.size(); i++) {
			boolean foundSeparator = (intArray.get(i) == -1);
			if (keyIndex == key.length()) {keyIndex = 0;}
			if (foundSeparator) {caseType = "lower";}
			else if (caseType == "upper") {
				char matrixEntry = getMatrixEntry(intArray.get(i), keyIndexArray.get(keyIndex), caseType);
				encryptedText.append(matrixEntry);
				keyIndex ++;
			}
			else if (caseType == "lower") {
				char matrixEntry = getMatrixEntry(intArray.get(i), keyIndexArray.get(keyIndex), caseType);
				encryptedText.append(matrixEntry);
				keyIndex ++;
			}
		}

		return encryptedText.toString();
	}
}