//12/06/2023 Austen Radigk

package main;
import util.Translator;
import java.util.Scanner;
import java.util.List;

public class Main {

    //Fields
    private static String status = "none";
    private static List<String> encryptedData;
    private static List<String> decryptedData;
    private static List<String> outputData;
    private static Scanner Input = new Scanner(System.in);  


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
            String filePath = getInput();
            Translator translator = new Translator(filePath, "decrypt");
            printMenu(1);
            String type = getInput();
            if ("1".equals(type)) {
                outputData = translator.getOutput("save", ""); //Saves New File (WIP)
            }
            else if ("2".equals(type)) {
                System.out.println("\nEnter Header:");
                String header = getInput();
                outputData = translator.getOutput("group", header);
                printOutput(outputData);
            }
            else if ("3".equals(type)) {
                System.out.println("\nEnter Header:");
                String header = getInput();
                outputData = translator.getOutput("section", header);
                printOutput(outputData);
            }
        }
        else if (status == "encrypt") {
            System.out.println("\nEnter File Address:");
            
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
            System.out.println("\n-Search-Menu-----"); //WIP
            System.out.println("1 - Save New File\n2 - Group\n3 - Section");
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


    //Prints Output Data
    private static void printOutput(List<String> outputData) {
        System.out.println("\n-Output-Start---------------------------------------");
        for (String line:outputData) {
            System.out.println(line);
        }
        System.out.println("-Output-End-----------------------------------------");
    }
}