//11/22/2023 Austen Radigk

package tests;
import utils.Encryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Arrays;

public class Encryptor_Test {
	
	@Test() //1 Method
	@Timeout(1)
	public void getIndexArray(){
		//Test 1
		List<Integer> expectedRV = Arrays.asList(4, 18, 19, 26, 27, 28, -1, 19, 0); //Expected Return Value
		List<Integer> actualRV = Encryptor.getIndexArray("Test123"); //Actual Return Value
		String failureString = "getIndexArray 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //2 Method
	@Timeout(1)
	public void encryptIndexArray(){
		//Test 1
		List<Integer> intArray = Arrays.asList(4, 18, 19, 26, 27, 28, -1, 19, 0);
		String expectedRV = "OWHA^Q4e"; //Expected Return Value
		String actualRV = Encryptor.encryptIndexArray(intArray, "key"); //Actual Return Value
		String failureString = "encryptIndexArray 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //3 Constructor
	@Timeout(1)
	public void Encryptor(){
		//Fields
		Encryptor Encryptor_Object; //Encryptor_Object
		String expectedRV; //Expected Return Value
		String actualRV; //Actual Return Value
		String failureString; //Failure String

		//Test 1
		Encryptor_Object = new Encryptor("Test123", "key");
		expectedRV = "OWHA^Q4e";
		actualRV = Encryptor_Object.getEncrpytedText();
		failureString = "Encryptor_Object 1 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 2
		Encryptor_Object = new Encryptor("Zippy#@(*&22", "magic");
		expectedRV = "UPV&$Dzgahdgep0u7p";
		actualRV = Encryptor_Object.getEncrpytedText();
		failureString = "Encryptor_Object 2 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 3
		Encryptor_Object = new Encryptor("Yap#@)939393", "important");
		expectedRV = "I@NGPL(Fhiesfvsf";
		actualRV = Encryptor_Object.getEncrpytedText();
		failureString = "Encryptor_Object 3 ->";
		assertEquals(failureString, expectedRV, actualRV);
	}
}