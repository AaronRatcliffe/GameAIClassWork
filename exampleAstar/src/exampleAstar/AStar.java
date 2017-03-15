/**
 * 
 */
package exampleAstar;

/**
 * @author aaron ratcliffe
 *
 **/
import java.util.*;

public class AStar {

   static int[][] world = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                           {1, 0, 0, 1, 1, 0, 1, 0, 1, 0},
                           {1, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                           {1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                           {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                           {0, 0, 1, 1, 0, 1, 1, 1, 0, 0},
                           {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                           {0, 1, 1, 0, 0, 0, 1, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}};


   public static void main (String[] args){

                             
      Scanner k = new Scanner(System.in);
      int startRow, startCol, endRow, endCol;
      
      System.out.println("Enter Starting Row: ");
      startRow = k.nextInt();
      
      System.out.println("Enter Starting Col: ");
      startCol = k.nextInt();
      
      System.out.println("Enter Ending Row: ");
      endRow = k.nextInt();
      
      System.out.println("Enter Ending Col: ");
      endCol = k.nextInt();
      
      Node start = new Node(startRow, startCol, world[startRow][startCol]);
      Node end = new Node(endRow, endCol, world[endRow][endCol]);
      
      findPath(start, end);
      
       for(int i = 0; i < world.length; i++){
         for(int j = 0; j < world[0].length; j++){
            System.out.print(world[i][j]);  
            if(j < world[0].length - 1){
               System.out.print(",");
            }
         }
         System.out.println();
      }
   }
   
   public static void findPath(Node start, Node end){
   
      ArrayList<Node> openList = new ArrayList<Node> ();
      ArrayList<Node> closedList = new ArrayList<Node> ();
      
      //add starting node to open list
      openList.add(start);
      
      boolean keepSearching = true;
      
      //main loop body of A*
      while(keepSearching){
         
         Node current;
         
         //pop best move off of open list
         if(openList.size() == 0){
            keepSearching = false;
            System.out.println("No Path Exists");
            current = null;
            break;
         }else{
            current = openList.remove(0);
         }
         
         
         //check for goal
         if(current.equals(end)){               //found path!
            System.out.println("COOKIES!");
            
            Node n = current;
            while(n.getParent() != null){
               System.out.println(n.toString());
               world[n.getRow()][n.getCol()] = 7;
               n = n.getParent();
            }
            keepSearching = false;
         }else{                                 //explore neighbors
            closedList.add(current);            //add current node to closed list
            
            int curRow = current.getRow ();
            int curCol = current.getCol ();
            
            for(int r = curRow - 1; r <= curRow + 1; r++){
               for(int c = curCol - 1; c <= curCol + 1; c++){
                 
                  if(!(r < 0 || c < 0 || r > world.length - 1 || c > world[0].length - 1)){ //in bounds
                     if(world[r][c] == 0){                                          //pathable
                        Node n = new Node(r, c, 0);
                        if(!closedList.contains (n)){                               //not in closed list
                           
                           int g = 14;
                           if(r == curRow || c == curCol){
                              g = 10;
                           }
                           g += current.getG ();
   
                           if(!openList.contains (n)){                              //not in open list
                              n.setG (g);
                              n.setH (getH(n, end));
                              n.setF ();
                              n.setParent (current);
                              openList.add ( n );
                           }else{                                                   //already in open list
                              Node m = openList.get(openList.indexOf(n));
                              if(g < m.getG()){
                                 m.setG(g);
                                 m.setF();
                                 m.setParent(current);
                              }
                           }
                        }
                     }
                  }  
               }
            }
         }
      }  
   }
   
   public static int getH(Node s, Node e){
      int r = Math.abs(s.getRow() - e.getRow());
      int c = Math.abs(s.getCol() - e.getCol());
      
      return (r + c) * 10;
   }
}