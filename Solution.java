import java.util.*;
import java.io.*;
import java.math.*;



class Solution {

    //create a class to store coord. of each cells
static class Point {
    int x;
    int y;
 
    public Point(int x, int y)  {
        this.x = x;
        this.y = y;
    }
};

//class to store coords of a cell and distance from M
static class queueNode {
    Point point; 
    int dist;
 
    public queueNode(Point point, int dist) {
        this.point = point;
        this.dist = dist;
    }
};

// check whether given cell (row, col) 
// is a wall or oob cell or not.
static boolean isValid(int row, int col) {
   
    return (row >= 0) && (row < 10) &&
           (col >= 0) && (col < 10);
}
 
// These arrays are used to get row and column
// numbers of 4 neighbours of a given cell
static int rowNum[] = {-1, 0, 0, 1};
static int colNum[] = {0, -1, 1, 0};

static int BFS(char mat[][], Point src,
                            Point dest)
{
    // check source and destination cell
    // of the matrix have value 'C' and 'M'
    if (mat[src.x][src.y] != 'C' && 
        mat[dest.x][dest.y] != 'M') {
        System.out.println("ffs");
        return -1;

        }
 
    boolean [][]visited = new boolean[10][10];
     
    // Mark the source cell as visited
    visited[src.x][src.y] = true;
 
    // Create a queue for BFS
    Queue<queueNode> q = new LinkedList<>();
     
    // Distance of source cell is 0
    queueNode s = new queueNode(src, 0);
    q.add(s); // Enqueue source cell
 
    // Do a BFS starting from source cell
    while (!q.isEmpty())
    {
        queueNode curr = q.peek();
        Point point = curr.point;
          
        // If we have reached the destination cell,
        // we are done
        if (point.x == dest.x && point.y == dest.y) {
          
            return curr.dist;
        }
        // Otherwise dequeue the front cell 
        // in the queue and enqueue
        // its adjacent cells
        q.remove();
 
        for (int i = 0; i < 4; i++)
        {
            int row = point.x + rowNum[i];
            int col = point.y + colNum[i];

            
             
            // if adjacent cell is valid, has path 
            // and not visited yet, enqueue it.
             if (isValid(row, col) && (
                    mat[row][col] == '.' || mat[row][col] == 'C' || mat[row][col] == 'M' )&& 
                    !visited[row][col])
            {
                // mark cell as visited and enqueue it
                visited[row][col] = true;
                queueNode Adjcell = new queueNode
                             (new Point(row, col),
                                   curr.dist + 1 );
                q.add(Adjcell);
            }
        }
    }

    // Return -1 if destination cannot be reached
    return -1;
}



    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char [][] grid = new char [10][10];
        String [] rows = new String [grid.length];
        char[] ch = new char[(rows.length * 10)*10];
      
      

         //Populate a String array with each input
        for (int i = 0; i < rows.length; i++) {
            String row = in.nextLine();
            rows[i] = row;
             
        }

        //Convert each String in row array into a char array
        //Populate the 2D array with each value of the char array.
            for (int i = 0; i < rows.length; i++) {  
                for(int j = 0; j < 10; j++) {
                    ch = rows[i].toCharArray();  
                    grid[i][j] = ch[j];    
                  
                }
                
            }

        // Get position of the Mother and the Child
            int MPosI = 0;
            int MPosJ = 0;
            int CPosI = 0;
            int CPosJ = 0;
           
            for( int i = 0; i < grid.length ; i++) {
                for( int j = 0; j < grid.length ; j++) {
                    if(grid[i][j] == 'M') {
                        MPosI = i;
                        MPosJ = j;
                       
                    }
                    if(grid[i][j] == 'C') {
                        CPosI = i;
                        CPosJ = j;
                      
                    }
     
                 }
            }

          Point source = new Point(CPosI, CPosJ);
          Point dest = new Point(MPosI, MPosJ);
 
    int dist = BFS(grid, source, dest);

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

      System.out.println((dist*10)+"km");
    }
}