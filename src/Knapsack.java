//Chris Bridewell
//Solves the knapsack problem

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack {
    public static void main(String args[]) throws FileNotFoundException {
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
        for (int i=1; i <= weeksAvailable; i++) { //Start with 1 bc 0 weeks will always be 0
            for (int x=1; x <= projectNames.size(); x++) { //Start with 1 item bc 0 items will always be 0

            }
        }

        //Prints the table
        System.out.print("    ");
        for (int i=0; i<projectNames.size(); i++)
            System.out.print(laborAmt.get(i)+","+netProfit.get(i)+" ");
        System.out.println();
        for (int i=0; i <= weeksAvailable; i++) {
            for (int x=0; x <= projectNames.size(); x++) {
                System.out.print(scores[i][x]+"    ");
            }
            System.out.println();
        }

    }
}