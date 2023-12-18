//12/18/2023 Austen Radigk

package main;
import util.Translator;

public class Main {

    //Main Method
    public static void main(String[] args) {
        try {new Translator();}
        catch (Exception errorMessage) {System.out.println(errorMessage);}
    }
}