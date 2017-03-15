/**
 * This program asks the user for two input files that each contain a single string,
 * compairs them to each other, finds and outputs the normalized edit distence and 
 * the largest common subsequince.
 * @author aaron ratcliffe
 * @vertion 2
 */
import java.util.Scanner;
import java.io.*;
public class Comparison {
	public static void main(String args[]) throws FileNotFoundException {
		char [][] DNA = new char[2][];
		Scanner keybordIn = new Scanner(System.in);
		Scanner [] fileInSA = new Scanner[2];
		boolean errorFound = false;
		//validating input
		do{
			try{
				errorFound = false;
				if(args.length == 0 || errorFound){
					//asking for input
					args = new String[2];
					System.out.println("Please enter the first input files addres: ");
					args[0] = keybordIn.nextLine();
					System.out.println("Please enter the second input files addres: ");
					args[1] = keybordIn.nextLine();
				}
				fileInSA[0] = new Scanner(new File(args[0]));
				fileInSA[1] = new Scanner(new File(args[1]));
			}
			catch(IOException e){
				errorFound = true;
				System.out.println("Can not find file try aggen.");
			}
			catch(Throwable e){
				errorFound = true;
				System.out.println("Something stranges happend try aggen.");
			}
		}while(errorFound);
		keybordIn.close();
		//reeding the strings in the files into an array of chars
		for(int j = 0; j < 2; j++){
			DNA[j] = fileInSA[j].nextLine().trim().toCharArray();
			fileInSA[j].close();
		}
		//creating a compair object with the ragged array as the input
		Compare comparison = new Compare(DNA);
		//outputing the Normalized edit distence and the Longest common subsequince of the two files
		System.out.println(comparison);
	}
}
