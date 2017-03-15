/**
 * This file asks the user to identifi an input file (in the format of
 * one intiger on each line with the first intiger being your query) and 
 * an out put file to print the sorted array to and give the answer as to
 * how many of the number were greater then out equal to your query.
 * 
 * @author Aaron J. Ratcliffe
 * @version 1
 */
import java.util.Scanner;
import java.io.*;
public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		int [] quickSortList;
		int [] insertSortList;
		int arraySize = 0;
		int answer;
		int query;
		int temp;
		String readFile = "null";
		String printFile = "null";
		Scanner keybordIn = new Scanner(System.in);
		Scanner fileIn = new Scanner(System.in);
		PrintWriter fileOut;
		boolean errorFound = false;
		//validating input
		do{
			try{
				errorFound = false;
				//asking for input
				System.out.println("Please enter the input files addres: ");
				readFile = keybordIn.nextLine();
				fileIn = new Scanner(new File(readFile));
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
		//asking for output
		System.out.println("Please enter the file you wish to print to:");
		printFile = keybordIn.nextLine();
		fileOut = new PrintWriter(printFile);
		keybordIn.close();
		//iterating thru the file to see how large the array will be
		while(fileIn.hasNextInt()){
			arraySize++;
			fileIn.nextInt();
		}
		//reseting to start of file and declaring sizes for the arrays
		fileIn.close();
		fileIn = new Scanner(new File(readFile));
		query = fileIn.nextInt();
		arraySize--;
		quickSortList = new int[arraySize];
		insertSortList = new int[arraySize];
		//filling out the arrays to be sorted
		for(int i = 0; i < arraySize; i++){
			temp = fileIn.nextInt();
			quickSortList[i] = temp;
			insertSortList[i] = temp;
		}
		fileIn.close();
		//calling quickSort to sort the array
		SortInt.useQuickSort(quickSortList, 0, quickSortList.length-1);
		SortInt.useInsertionSort(insertSortList);
		//calls binary serch to find the index of the first element grater then the query
		answer = SortInt.useBinarySerch(insertSortList, 0, (insertSortList.length - 1), query);
		//Writing the answer to the question and the now sorted array
		//to the specified file
		fileOut.println(answer);
		for(int i = 0; i < insertSortList.length; i++){
			fileOut.println(insertSortList[i]);
		}
		fileOut.close();
	}

}
