//Sarah Hoekema
//CS 145
//GadsbyPrinter.java

import java.io.*;
import java.util.*;

//GadsbyPrinter.java is a program that takes a text file and removes all the letter E's
//from it, then outputs the new text to a new file for the reader to enjoy.
//GadsbyPrinter.java was inspired by the book Gadsby by Ernest Vincent Wright,
//who wrote his book without a single letter E in it.
public class GadsbyPrinter {
    public static void main(String[] args) throws FileNotFoundException {
        //construct Scanner for user input
        Scanner userInput = new Scanner(System.in);

        intro();

        //prompt user for file
        File inputFile = promptFile(userInput);
        Scanner fileInput = new Scanner(inputFile);
        //create output file
        String fileOutputName = "Gadsby" + inputFile.getName();
        PrintStream output = new PrintStream(fileOutputName);

        //processes file and calculate number of letter E's in file
        int numOfE = processFile(fileInput, output);

        outro(numOfE, fileOutputName);

    }

    //prints out an introduction to the Gadsby Printer
    public static void intro(){
        System.out.println("Welcome to the Gadsby printer!");
        System.out.println("The Gadsby printer accepts a text file and creates a new file");
        System.out.println("that contains the original text, but with all the letter Es removed.");
        System.out.println("The Gadsby printer is created in honor of Ernest Vincent Wright, who");
        System.out.println("wrote the book \"Gadsby\" without using a single letter E.");
        System.out.println();
    }

    //accepts a Scanner for input
    //throws FileNotFoundException
    //prompts the user for a file to use until an existing file is entered
    //returns the File specified
    public static File promptFile(Scanner input) {
        System.out.print("Please specify which file to use: ");
        File f = new File(input.next());
        //prompts for file until file that exists is entered
        while(!f.exists()){
            System.out.print("Sorry, that file doesn't exist! Please try again: ");
            f = new File(input.next());
        }
        System.out.println("Thank you for the file!");
        System.out.println();
        return f;
    }

    //accepts a Scanner for input and a PrintStream for output
    //processes the file line by line and removes all occurrences of the letter E
    //returns the number of E's removed
    public static int processFile(Scanner input, PrintStream output){
        int numOfE = 0;
        //iterates through each line of the file
        while(input.hasNextLine()){
            String line = input.nextLine();
            //iterates through each character of the line
            for(int i = 0; i < line.length(); i++){
                //checks the current character
                if(line.charAt(i) == 'e' || line.charAt(i) == 'E') {
                    //removes an E at the end of the String
                    if(i == line.length()-1) {
                        line = line.substring(0, i);
                    } else { //removes an E in front or middle of the String
                        line = line.substring(0, i) + line.substring(i + 1, line.length());
                        i--;
                    }
                    numOfE++;
                }
            }
            //prints the line to the output file
            output.println(line);
        }
        return numOfE;
    }

    //accepts the integer number of E's and the String name of the output file
    //prints out the outro of the Gadsby Printer
    public static void outro(int numOfE, String fileOutputName){
        System.out.println(numOfE + " occurrences of the letter E have been removed.");
        System.out.println("Your new text has been printed to " + fileOutputName + ". Enjoy!");
    }
}