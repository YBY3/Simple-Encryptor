//11/30/2023 Austen Radigk

package test;
import util.Translator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Arrays;

public class Translator_Test {

	//Fields
	public static final List<String> formattedData = Arrays.asList(
		"Group 1",
		"---------------------------------------", 
		"", 
		"Test 1:", 
		"Unnecessary Spacing 1", 
		"", 
		"Test 2:", 
		"Unnecessary Spacing 2", 
		"", 
		"---------------------------------------", 
		"", 
		"Group 2", 
		"---------------------------------------", 
		"", 
		"Test 3:", 
		"Unnecessary Spacing 1", 
		"", 
		"Test 4:", 
		"Unnecessary Spacing 2", 
		"", 
		"---------------------------------------"
	);

	public static final List<String> encryptedData = Arrays.asList( //Change Key
		"@SGZCMqe", 
		"---------------------------------------",
		"", 
		"OWFI%X4e", 
		"XR#MIE#ED(CBKG&XKW-yy3q", 
		"", 
		"OWFI^X4e", 
		"XR#MIE#ED(CBKG&XKW;yy3q",
		"", 
		"---------------------------------------",
		"", 
		"@SGZCNqe",
		"---------------------------------------", 
		"", 
		"OWFI&X4e", 
		"XR#MIE#ED(CBKG&XKW-yy3q",
		"", 
		"OWFI*X4e", 
		"XR#MIE#ED(CBKG&XKW;yy3q",
		"",
		"---------------------------------------"
	);


	@Test() //1 Method
	@Timeout(1)
	public void encryptData(){
		//Test 1
		List<String> expectedRV = encryptedData; //Expected Return Value
		List<String> actualRV = Translator.encryptData(formattedData); //Actual Return Value
		String failureString = "encryptData 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}


	@Test() //2 Method
	@Timeout(1)
	public void decryptData(){
		//Test 1
		List<String> expectedRV = formattedData; //Expected Return Value
		List<String> actualRV = Translator.decryptData(encryptedData); //Actual Return Value
		String failureString = "decryptData 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}
}