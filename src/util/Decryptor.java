//11/29/2023 Austen Radigk

package util;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Decryptor {

	//Fields
	private static final int KEYBOARD_SIZE = 38;
	private static final List<Character> KEYBOARD_LOWERCASE = Arrays.asList(
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', 
		'3', '4', '5', '6', '7', '8', '9', '0', ' ', ':'
	);
	private static final List<Character> KEYBOARD_UPPERCASE = Arrays.asList(
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', 
		'#', '$', '%', '^', '&', '*', '(', ')', '-', ';'
	);


	//Constructor
	public Decryptor () {
	}


	//Decrypts String and Returns Decrypted String using getIndexArray and decryptIndexArray
	public static String decryptString(String encryptedText, String key) {
		List<Integer> indexArray = getIndexArray(encryptedText);
		String decryptedString = decryptIndexArray(indexArray, key);
		return decryptedString;
	}


	//Separates LowerCase and UpperCase characters into Index Array
	public static List<Integer> getIndexArray(String str) {
		List<Integer> lowerIndexArray = new ArrayList<>();
		List<Integer> upperIndexArray = new ArrayList<>();
		List<Integer> indexArray = new ArrayList<>();

		//Builds Lower & Upper Case Index Arrays
		for (int i = 0; i < str.length(); i++) { 
			for (int n = 0; n < KEYBOARD_SIZE; n++) { 
				boolean lowerMatchFound = (str.charAt(i) == KEYBOARD_LOWERCASE.get(n));
				boolean upperMatchFound = (str.charAt(i) == KEYBOARD_UPPERCASE.get(n));
				if (upperMatchFound) {upperIndexArray.add(n);}
				else if (lowerMatchFound) {lowerIndexArray.add(n);}
			}
		}

		//Adds Uppercase Index's to indexArray (Order is Reversed)
		for (int i = 0; i < upperIndexArray.size(); i++) { 
			indexArray.add(upperIndexArray.get(i));
		}

		indexArray.add(-1); //Separator

		//Adds Lowercase Index's to indexArray & isUpperPositionIndex's to upperPositionIndexArray
		for (int i = 0; i < lowerIndexArray.size(); i++) { 
			indexArray.add(lowerIndexArray.get(i));
		}

		return indexArray;
	}


	//Gets Column Character Through Key Index and Matrix Index
	public static char getColumn(int keyIndex, int matrixEntryIndex, String caseType) {
		char columnCharacter = '0';
		int columnIndex = (matrixEntryIndex - keyIndex + KEYBOARD_SIZE) % KEYBOARD_SIZE;
		if (caseType == "upper") {columnCharacter = KEYBOARD_UPPERCASE.get(columnIndex);}
		else if (caseType == "lower") {columnCharacter = KEYBOARD_LOWERCASE.get(columnIndex);}
		return columnCharacter;
	}


	//Gets Upper Position Index
	public static int getUpperPositionIndex(char positionChar) {
		int upperPositionIndex = 0;

		for (int n = 0; n < KEYBOARD_SIZE; n++) { 
			boolean upperMatchFound = (positionChar == KEYBOARD_UPPERCASE.get(n));
			if (upperMatchFound) {
				upperPositionIndex = n;
				break;
			}
		}

		return upperPositionIndex;
	}


	//Builds Decrypted String
	public static String getDecryptedString(List<Character> charArray) {
		StringBuilder decryptedString = new StringBuilder();

		for (int i = 0; i < charArray.size(); i++) { 
			decryptedString.append(charArray.get(i));
		}

		return decryptedString.toString();
	}


	//Decrypts Index Array to Character Array (WIP)
	public static String decryptIndexArray(List<Integer> intArray, String key) {
		String caseType = "lower"; //Converting to lowercase first
		int keyIndex = 0;
		int upperCount = 1;
		int lastIndex = 0;
		List<Integer> keyIndexArray = new ArrayList<>();
		List<Character> decryptedArray = new ArrayList<>();

		//Builds keyIndexArray
		for (int i = 0; i < key.length(); i++) { 
			for (int n = 0; n < KEYBOARD_SIZE; n++) { 
				boolean lowerMatchFound = (key.charAt(i) == KEYBOARD_LOWERCASE.get(n));
				if (lowerMatchFound) {keyIndexArray.add(n);}
			}
		}

		//Builds decryptedArray
		for (int i = 0; i < intArray.size(); i++) {
			boolean foundSeparator = (intArray.get(i) == -1);
			if (keyIndex == key.length()) {keyIndex = 0;}
			if (foundSeparator) {caseType = "upper";}
			else if (caseType == "lower") {
				char matrixEntry = getColumn(keyIndexArray.get(keyIndex), intArray.get(i), caseType);
				decryptedArray.add(matrixEntry);
				keyIndex ++;
			}
			else if (caseType == "upper") {
				char matrixEntry = getColumn(keyIndexArray.get(keyIndex), intArray.get(i), caseType);
				if (upperCount % 2 == 0) {
					lastIndex = (decryptedArray.size()-1);
					decryptedArray.add(getUpperPositionIndex(matrixEntry), decryptedArray.get(lastIndex));
					lastIndex = (decryptedArray.size()-1);
					decryptedArray.remove(lastIndex);
				}
				else {decryptedArray.add(matrixEntry);}
				keyIndex ++;
				upperCount ++;
			}
		}

		return getDecryptedString(decryptedArray);
	}
}