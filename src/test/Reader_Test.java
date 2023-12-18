//12/18/2023 Austen Radigk

package test;
import util.Reader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Arrays;

public class Reader_Test {

	//Fields
	public static final List<String> fileAddresses = Arrays.asList(
		"data/.DS_Store", "data/decrypted_test", "data/encrypted_test"
	);

	public static final List<String> rawData = Arrays.asList(
		"Group 1",
		"---------------------------------------", 
		"", 
		"Test 1:", 
		"	Unnecessary Spacing 1", 
		"", 
		"Test 2:", 
		"	Unnecessary Spacing 2", 
		"", 
		"---------------------------------------", 
		"", 
		"Group 2", 
		"---------------------------------------", 
		"", 
		"Test 3:", 
		"	Unnecessary Spacing 1", 
		"", 
		"Test 4:", 
		"	Unnecessary Spacing 2", 
		"", 
		"---------------------------------------"
	);

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

	public static final List<String> group = Arrays.asList(
		"Group 1",
		"---------------------------------------", 
		"", 
		"Test 1:", 
		"Unnecessary Spacing 1", 
		"", 
		"Test 2:", 
		"Unnecessary Spacing 2", 
		"", 
		"---------------------------------------" 
	);

	public static final List<String> section = Arrays.asList(
		"Test 2:", 
		"Unnecessary Spacing 2" 
	);

	@Test() //1 Method
	@Timeout(1)
	public void scanFilePath(){
		//Test 1
		List<String> expectedRV = fileAddresses; //Expected Return Value
		List<String> actualRV = Reader.scanFilePath("data"); //Actual Return Value
		String failureString = "getFileAddress 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //2 Method
	@Timeout(1)
	public void formatData(){
		//Test 1
		List<String> expectedRV = formattedData; //Expected Return Value
		List<String> actualRV = Reader.formatData(rawData); //Actual Return Value
		String failureString = "formatData 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //3 Method
	@Timeout(1)
	public void findGroup(){
		//Test 1
		List<String> expectedRV = group; //Expected Return Value
		List<String> actualRV = Reader.findGroup(formattedData, "Group 1"); //Actual Return Value
		String failureString = "findGroup 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}

	@Test() //4 Method
	@Timeout(1)
	public void findSection(){
		//Test 1
		List<String> expectedRV = section; //Expected Return Value
		List<String> actualRV = Reader.findSection(formattedData, "Test 2:"); //Actual Return Value
		String failureString = "findSection 1 ->"; //Failure String
		assertEquals(failureString, expectedRV, actualRV);
	}
}