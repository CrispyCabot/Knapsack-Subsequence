//Chris Bridewell
//Solves the knapsack problem
//Given the number of weeks a project takes and the profit it gains,
//This program determines which projects to select in order to be the most profitable
//based on the inputted amount of weeks available
//Gets input by text file, output to text file, names of files both being inputted by the user

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack {
    public static void main(String args[]) throws FileNotFoundException {
        //Get user inputs
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of weeks available: ");
        int weeksAvailable = scan.nextInt();
        scan.nextLine(); //dummy input
        System.out.print("Input file: ");
        String inputFile = scan.nextLine();
        System.out.print("Output file: ");
        String outputFile = scan.nextLine();

        //Get data from file
        ArrayList<String> projectNames = new ArrayList();
        ArrayList<Integer> laborAmt = new ArrayList();
        ArrayList<Integer> netProfit = new ArrayList();
        try (Scanner input = new Scanner(new File(inputFile))) {
            while (input.hasNext()) {
                projectNames.add(input.next());
                laborAmt.add(Integer.parseInt(input.next()));
                netProfit.add(Integer.parseInt(input.next()));
            }
        }

        //Create 2d array
        int[][] scores = new int[weeksAvailable+1][projectNames.size()+1]; //Add 1 to each to fit 0
        int[] highestIndex = new int[2]; //Variable to keep track of the index of highest value
        int totalProfit = 0;
        for (int i=1; i<=weeksAvailable; i++) {
            for (int x=1; x<=projectNames.size(); x++) {
                int highestWithItem = 0, highestOtherwise;
                //Get profit if using this item
                if (laborAmt.get(x - 1) <= i) {
                    highestWithItem = netProfit.get(x - 1);
                    int col = x;
                    int row = i;
                    int nextScore = -1;
                    row -= laborAmt.get(col - 1);
                    col--;
                    nextScore = scores[row][col]; //Move left a column, up rows based on weeks
                    highestWithItem += nextScore;
                }
                highestOtherwise = scores[i][x - 1];
                if (highestWithItem > highestOtherwise)
                    scores[i][x] = highestWithItem;
                else
                    scores[i][x] = highestOtherwise;
                if (scores[i][x] > scores[highestIndex[0]][highestIndex[1]]) {
                    highestIndex = new int[]{i, x};
                    totalProfit = scores[i][x];
                }
            }
        }

        //Trace the answer out
        int row = highestIndex[0];
        int column = highestIndex[1];
        ArrayList<Integer> indexes = new ArrayList();
        while (scores[row][column] != 0) {
            int temp = scores[row][column];
            while (temp == scores[row][column]) { //Check if the column to left is the same
                if (column > 0)
                    column--;
                else {
                    column--;
                    break;
                }
            }
            indexes.add(column); //Add one to column bc the above overshoots it
            //The while loop overshoots it by one so don't need to lower column for next check
            row -= laborAmt.get(column); //Go up in rows the labor amt in weeks
        }

        //Write to text file
        PrintWriter output = new PrintWriter(outputFile);
        output.println("Number of projects available: "+projectNames.size());
        output.println("Available employee work weeks: "+weeksAvailable);
        output.println("Number of projects chosen: "+indexes.size());
        output.println("Total Profit: "+totalProfit);
        for (int i=indexes.size()-1; i>-1; i--) {
            output.println(projectNames.get(indexes.get(i))+" "
                    +laborAmt.get(indexes.get(i))+" "+netProfit.get(indexes.get(i)));
        }
        output.close();

        //Prints the table
      /*  System.out.print("    ");
        for (int i=0; i<projectNames.size(); i++)
            System.out.print(laborAmt.get(i)+","+netProfit.get(i)+" ");
        System.out.println();
        for (int i=0; i <= weeksAvailable; i++) {
            for (int x=0; x <= projectNames.size(); x++) {
                System.out.print(scores[i][x]+"    ");
            }
            System.out.println();
        } */

    }
}