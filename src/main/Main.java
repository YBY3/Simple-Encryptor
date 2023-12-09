//12/09/2023 Austen Radigk

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
            if (status != "exit") {getTranslator();}
        }
    }


    //Directs Data to Translator Based on Status
    private static void getTranslator() {
        System.out.println("\nEnter File Address:");
        try {
            Translator translator = new Translator(getInput(), status);
            while (status == "decrypt") { //WIP
                printMenu(1);
                String type = getInput();
                if ("1".equals(type)) {
                    translator.getOutput("save", "", "decrypt"); //Saves New File (WIP)
                }
                else if ("2".equals(type)) {
                    System.out.println("\nEnter Header:");
                    String header = getInput();
                    outputData = translator.getOutput("group", header, "");
                    printOutput(outputData);
                }
                else if ("3".equals(type)) {
                    System.out.println("\nEnter Header:");
                    String header = getInput();
                    outputData = translator.getOutput("section", header, "");
                    printOutput(outputData);
                }
                else if ("4".equals(type)) {status = "none";}
            }
            if (status == "encrypt") {
                translator.getOutput("save", "", "encrypt"); //Saves New File (WIP)
            }
        } 
        catch (Exception error) {System.out.println(error.getMessage());}
    }


    //Menu Print Method
    private static void printMenu(int k) {
        if (k == 0) {
            System.out.println("\n-Encryption-Menu-");
            System.out.println("1 - Decrypt File\n2 - Encrypt File\n3 - Exit");
            System.out.println("-----------------\n");
        }
        else if (k == 1) {
            System.out.println("\n-File-Menu-------");
            System.out.println("1 - Save New File\n2 - Show Group\n3 - Show Section\n4 - Go Back");
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
        System.out.println("\n\n-Output-Start---------------------------------------\n");
        for (String line:outputData) {
            System.out.println(line);
        }
        System.out.println("\n-Output-End-----------------------------------------\n");
    }
}