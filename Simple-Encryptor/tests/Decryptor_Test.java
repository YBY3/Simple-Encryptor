//11/22/2023 Austen Radigk

package tests;
import utils.Decryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Arrays;

public class Decryptor_Test {
	
	@Test() //1
	@Timeout(1)
	public void getIndexArray(){
		//Test 1
		List<Integer> expectedRV = Arrays.asList(14, 22, 7, 0, 31, 16, -1, 29, 4); //Expected Return Value
		List<Integer> actualRV = Decryptor.getIndexArray("OWHA^Q4e"); //Actual Return Value
		String failureString = "getIndexArray 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //2
	@Timeout(1)
	public void decryptIndexArray(){
		//Test 1
		List<Integer> intArray = Arrays.asList(14, 22, 7, 0, 31, 16, -1, 29, 4);
		String expectedRV = "Test123"; //Expected Return Value
		String actualRV = Decryptor.decryptIndexArray(intArray, "key"); //Actual Return Value
		String failureString = "decryptIndexArray 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //3 Constructor
	@Timeout(1)
	public void Decryptor(){
		//Fields
		Decryptor Decryptor_Object; //Encryptor_Object
		String expectedRV; //Expected Return Value
		String actualRV; //Actual Return Value
		String failureString; //Failure String

		//Test 1
		Decryptor_Object = new Decryptor("OWHA^Q4e", "key");
		expectedRV = "Test123";
		actualRV = Decryptor_Object.getDecrpytedText();
		failureString = "Decryptor_Object 1 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 2
		Decryptor_Object = new Decryptor("UPV&$Dzgahdgep0u7p", "magic");
		expectedRV = "Zippy#@(*&22";
		actualRV = Decryptor_Object.getDecrpytedText();
		failureString = "Decryptor_Object 2 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 3
		Decryptor_Object = new Decryptor("I@NGPL(Fhiesfvsf", "important");
		expectedRV = "Yap#@)939393";
		actualRV = Decryptor_Object.getDecrpytedText();
		failureString = "Decryptor_Object 3 ->";
		assertEquals(failureString, expectedRV, actualRV);
	}
}