//12/02/2023 Austen Radigk

package main;
import util.Reader;
import util.Translator;
import java.util.Scanner;
import java.util.List;

public class Main {

    //Fields
    private static String status = "none";
    private static List<String> encryptedData;
    private static List<String> decryptedData;
    private static Scanner Input = new Scanner(System.in);
    private static Reader reader = new Reader();
    private static Translator translator = new Translator();   


    //Main Method
    public static void main(String[] args) {
        while (status != "exit") {
            printMenu(0);
            updateStatus(getInput());
            directData();
        }
    }


    //Directs Data to Decryptor / Encryptor (WIP)
    public static void directData() {
        if (status == "decrypt") {
            System.out.println("\nEnter File Address:");
            encryptedData = reader.readFile(getInput());
            decryptedData = translator.decryptData(encryptedData);
            printMenu(1);
            String position = getInput();
            if (!"1".equals(position)) {
                System.out.println("\nEnter Header:");
                String header = getInput();
                printData(decryptedData, position, header);
            }
            else if ("1".equals(position)) {
                printData(decryptedData, position, "");
            }
        }
        else if (status == "encrypt") {
            System.out.println("\nEnter File Address:");
            decryptedData = reader.readFile(getInput());
            encryptedData = translator.encryptData(decryptedData);
            //Add File Output
        }
    }


    //Menu Print Method
    private static void printMenu(int k) {
        if (k == 0) {
            System.out.println("\n-Encryption-Menu-");
            System.out.println("1 - Decrypt File\n2 - Encrypt File\n3 - Exit");
            System.out.println("-----------------\n");
        }
        else if (k == 1) {
            System.out.println("\n-Search-Menu-----");
            System.out.println("1 - All\n2 - Group\n3 - Section");
            System.out.println("-----------------\n");
        }
    }


    //Returns Keyboard Input
    private static String getInput() {
        System.out.print("-> ");
        return Input.nextLine();
    }


    //Updates Status
    private static void updateStatus(String str) {
        if ("1".equals(str)) {status = "decrypt";}
        else if ("2".equals(str)) {status = "encrypt";}
        else if ("3".equals(str)) {status = "exit";}
    }


    //Print Data Based on section
    private static void printData(List<String> data, String position, String header) {
        System.out.println("\n-Start------------------------------------------\n");
        if ("1".equals(position)) {
            for (String line:data) {
                System.out.println(line);
            }
        }
        else if ("2".equals(position)) {
            for (String line:reader.findGroup(data, header)) {
                System.out.println(line);
            }
        }
        else if ("3".equals(position)) {
            for (String line:reader.findSection(data, header)) {
                System.out.println(line);
            }
        }
        System.out.println("\n-End--------------------------------------------\n");
    }
}