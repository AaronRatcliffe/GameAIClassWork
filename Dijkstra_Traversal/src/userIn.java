/**
 * This aceptes user input of the file that contains the graph information,
 * passes it into the graph class, then wates for the user to input their quiry,
 * it will then exicut the quiry and ask for another one untill the user types
 * exit.
 *  
 * @author aaron ratcliffe
 * @version 2
 */
import java.util.Scanner;
import java.io.*;
public class userIn {
	public static void main(String [] args) throws FileNotFoundException{
		Graph map;
		Scanner keybordIn = new Scanner(System.in);
		Scanner fileIn = new Scanner(System.in);
		String [] comand = new String[3];
		String newFileIn = "";
		boolean inputErrer;
		boolean askForInput = false;
		boolean end = false;
		do{
			inputErrer = false;
			//cheking to see if ther was something enterd in the comand line
			if(args.length > 0){
				if(args[0] == null || inputErrer){
					askForInput = true;
				}
				else{
					askForInput = false;
				}
			}
			else{
				askForInput = true;
			}
			//cheking to see if the file name enterd exists
			try{
				if(askForInput){
					System.out.println("Please enter the input file: ");
					newFileIn = keybordIn.nextLine();
					fileIn = new Scanner(new File(newFileIn));
				}
				else{
					newFileIn = args[0];
					fileIn = new Scanner(new File(newFileIn));
				}
			}
			catch(IOException e){
			 System.out.println("You have enterd an invalid file name!");
			 newFileIn = "";
			 inputErrer = true;
			}
		}while(inputErrer);
		fileIn.close();
		//creating a graph out of the input file
		map = new Graph(newFileIn);
		//looping until user inputs end
		do{
			System.out.println("Input your comand: ");
			comand[0] = keybordIn.next();
			comand[0].toLowerCase();
			//cheking to see which comand
			if(comand[0].equals("quit")){
				end = true;
			}
			else if(comand[0].equals("print")){
				System.out.println(map);
			}
			else if(comand[0].equals("path")){
				comand[1] = keybordIn.next();
				comand[2] = keybordIn.next();
				System.out.println(map.dijkstra(comand[1],comand[2]));
			}
			else if(comand[0].equals("reachable")){
				System.out.println(map.reachable());
			}
			else if(comand[0].equals("deleteedge")){
				comand[1] = keybordIn.next();
				comand[2] = keybordIn.next();
				map.deleteEdge(comand[1], comand[2]);
			}
			else if(comand[0].equals("addedge")){
				comand[1] = keybordIn.next();
				comand[2] = keybordIn.next();
				if(keybordIn.hasNextFloat()){
					float edgeLength = keybordIn.nextFloat();
					map.addEdge(comand[1], comand[2], edgeLength);
				}
				else{
					System.out.println("You did not enter a distence for the edge.");
				}
			}
			else{
				System.out.println("You have enterd an invalid comand.");
			}
			keybordIn.nextLine();
				
		}while(!end);
		keybordIn.close();
		System.out.println("Have a nice day :)");
	}
}
