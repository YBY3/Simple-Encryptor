11/22/2023 Austen Radigk

Tests
--------------------------------------------------------------------------------

Encryptor_Tests:
	COMPILE: javac -cp .:/Library/Java/Extensions/junit.jar tests/Encryptor_Test.java

Decryptor_Tests:
	COMPILE: javac -cp .:/Library/Java/Extensions/junit.jar tests/Decryptor_Test.java

Run Tests:
	RUN: java -jar /Library/Java/Extensions/junit.jar --classpath /Library/Java/Extensions/junit.jar:. --class-path tests --scan-classpath --reports-dir tests

--------------------------------------------------------------------------------


OutputTools (WIP)

PasswordGaurd (WIP & Driver)

//SAMPLE COMMANDS
// COMPILE: javac asg6sp23Test/GameDriver.java
// RUN: java asg6sp23Test/GameDriver

Format:
Test123
LowerCase - est123
UpperCase - T0 (T=Character 0=IndexPosition)