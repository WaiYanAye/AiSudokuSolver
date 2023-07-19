package Ai_project_4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class SudokuSolver
{
  public static void main(String[] args) throws Exception
  {
    System.out.println("Welcome to the Game of Sudoku");
    System.out.println("*****************************");
    try (Scanner scan = new Scanner(System.in)) {
      System.out.println("Please enter a valid filename: ");
      String filename = scan.nextLine();
      
      File file = new File(filename);
      System.out.println("The initial problem: ");    
      BufferedReader in = new BufferedReader(new FileReader(file));    
      String fileOutput;
      while((fileOutput = in.readLine()) != null)
      {
          System.out.println(fileOutput);    //Display the initial problem 
      }
      in.close();
      try (Scanner fileScanner = new Scanner(file)) {
        System.out.println("*****************************");
        System.out.println("*****************************");
        System.out.println("The final Solution:");

            // Create a matrix chart to store the puzzle
            int[][] chart = new int[9][9];         
        
String line = fileScanner.nextLine();
 line = line.replace(" ", "");
            //Prase all digits to chart array looping through rows and cols
for (int x = 0; x < 9; x++)
{
            for (int y = 0; y < 9; y++)
            {
              chart[x][y] = Character.getNumericValue(line.charAt(y));
              if (y == 8 && fileScanner.hasNextLine())
            {
              line = fileScanner.nextLine();
              line = line.replace(" ", "");
            }
            } 
            } 

        
            solve(chart, 0, 0);
      }
    }

  } 

  
  // Recartion to replaces 0 as a new number to solve the puzzle
  private static void solve(int[][] chart, int cellX, int cellY)
  {
    //The game finished when y is 9
    if(cellY >= 9)
    {
      printSolution(chart);
      System.out.println();
    }
    else
    {
      //Identify the next digit to solve the puzzle
      int nextX = cellX;
      int nextY = cellY;
      if(cellX == 8)
      {
        //Go thourgh the next row and reset the col to 0
        nextX = 0;
        nextY++;
      }
      else
      {
        nextX++;
      }

      //if the number is already existed, move to the next one
      if(chart[cellY][cellX] != 0)
      {
        solve(chart, nextX, nextY);
      }
      else
      {
        //check if the number is legal
        //replace that number and move to the next one
        for(int checkNum = 1; checkNum <= 9; checkNum++)
        {
          if(isLegalSquare(chart, cellX, cellY, checkNum)
             && isLegalRow(chart, cellY, checkNum)
             && isLegalCol(chart, cellX, checkNum))
          {
            chart[cellY][cellX] = checkNum;
            solve(chart, nextX, nextY);
          }
        }
        //there is an incorrect number in the puzzle
        chart[cellY][cellX] = 0;
      }
    }
  }

  //checking if the number is legal in a square
  private static boolean isLegalSquare(int[][] chart, int x, int y, int check)
  {
    int rowY;
    int colX;

    //Identify which column of the square
    if(x < 3)
    {
      colX = 0;
    }
    else if (x < 6)
    {
      colX = 3;
    }
    else
    {
      colX = 6;
    }

    //Identify which row of the square. 
    if(y < 3)
    {
      rowY = 0;
    }
    else if (y < 6)
    {
      rowY = 3;
    }
    else
    {
      rowY = 6;
    }

    //Check every digit in the square 
    //Return fasle if it has already existed
    for(int i = rowY; i < rowY + 3; i++)
    {
      for(int j = colX; j < colX + 3; j++)
      {
        if(chart[i][j] == check)
          {
            return false;
          }
      }
    }

    return true; // if a legal digit

  }
  //Checks if a given digit is in a given row and returns false if it is.
  private static boolean isLegalRow(int[][] chart, int rowY, int check)
  {
    //loops every number in the row
    for(int x= 0; x < 9; x++)
    {
      //Checks if the given number is the same as the current digit
      if (check == chart[rowY][x])
      {
        return false;
      }
    }
    return true; //if a legal digit
  }

   //Checks if a given number is in a given column and returns false if it is.
   private static boolean isLegalCol(int[][] chart, int colX, int check)
   {
     //loops every number in the column
     for(int y = 0; y< 9; y++)
     {
       //Checks if the current digit is the given digit.
       if (check == chart[y][colX])
       {
         return false;
       }
     }
     return true; //if a legal digit
   }  
 

 
  //Prints the final solution
  private static void printSolution(int chart[][])
  {
    //Loops round each digit and prints it.
    for(int y = 0; y < 9; y++)
	{
	  for(int x = 0; x < 9; x++)
	  {
	    System.out.print(chart[y][x] + " ");
	    //Starts a new line when at the end of a row.
        if(x== 8)
		{
		  System.out.println();
		}
	  } 
	} 
  } 
} 