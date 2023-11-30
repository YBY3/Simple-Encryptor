//11/29/2023 Austen Radigk

package test;
import util.Encryptor;
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
		String expectedRV = "OWF-^O4e"; //Expected Return Value
		String actualRV = Encryptor.encryptIndexArray(intArray, "key"); //Actual Return Value
		String failureString = "encryptIndexArray 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //3 Method
	@Timeout(1)
	public void encryptString(){
		//Fields
		Encryptor encryptor = new Encryptor(); //Encryptor Object
		String expectedRV; //Expected Return Value
		String actualRV; //Actual Return Value
		String failureString; //Failure String

		//Test 1
		expectedRV = "OWF-^O4e";
		actualRV = encryptor.encryptString("Test123", "key");
		failureString = "Encryptor_Object 1 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 2
		expectedRV = "UPV&$Bzg hbgcp0u7p";
		actualRV = encryptor.encryptString("Zippy#@(*&22", "magic");
		failureString = "Encryptor_Object 2 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 3
		expectedRV = "I@LENJ(Dficsdvqf";
		actualRV = encryptor.encryptString("Yap#@)939393", "important");
		failureString = "Encryptor_Object 3 ->";
		assertEquals(failureString, expectedRV, actualRV);

		//Test 4
		expectedRV = "M%(NP!I^RQ%NOPAE^A2m9u";
		actualRV = encryptor.encryptString("Test: This is a test", "important");
		failureString = "Encryptor_Object 4 ->";
		assertEquals(failureString, expectedRV, actualRV);
	}
}