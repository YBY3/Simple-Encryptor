11/28/2023 Austen Radigk

Encryptor & Decryptor indexArray Format:
--------------------------------------------------------------------------------

Test123
LowerCase - est123
UpperCase - T0 (T=Character 0=IndexPosition)

--------------------------------------------------------------------------------


Console JUnit 
--------------------------------------------------------------------------------

Compile Tests & Classes:
	javac -d bin -cp ".:/Library/Java/Extensions/junit.jar" src/test/* src/util/*

Run Tests:
	java -jar /Library/Java/Extensions/junit.jar -cp bin --scan-classpath --reports-dir bin/test/results

--------------------------------------------------------------------------------