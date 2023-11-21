//11/16/2023 Austen Radigk

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
}