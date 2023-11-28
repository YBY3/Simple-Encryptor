11/27/2023 Austen Radigk

Encryptor & Decryptor indexArray Format:
--------------------------------------------------------------------------------

Test123
LowerCase - est123
UpperCase - T0 (T=Character 0=IndexPosition)

--------------------------------------------------------------------------------


Simple-Encryptor (main) (TEMP)
--------------------------------------------------------------------------------

COMPILE: javac ProgramDriver.java
RUN: java ProgramDriver

--------------------------------------------------------------------------------


Console JUnit (TEMP)
--------------------------------------------------------------------------------

Encryptor_Test:
	COMPILE: javac -cp .:/Library/Java/Extensions/junit.jar test/Encryptor_Test.java

Decryptor_Test:
	COMPILE: javac -cp .:/Library/Java/Extensions/junit.jar test/Decryptor_Test.java

Reader_Test:
	COMPILE: javac -cp .:/Library/Java/Extensions/junit.jar test/Reader_Test.java

Translator_Test:
	COMPILE: javac -cp .:/Library/Java/Extensions/junit.jar test/Translator_Test.java

Run All Test:
	RUN: java -jar /Library/Java/Extensions/junit.jar --classpath /Library/Java/Extensions/junit.jar:. --class-path test --scan-classpath --reports-dir test

--------------------------------------------------------------------------------