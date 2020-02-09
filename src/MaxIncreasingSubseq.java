//Chris Bridewell
//Gets the longest increasing subsequence of an inputted string based on ascii values of letter
// e.g. zebras = ers

import java.util.ArrayList;
import java.util.Scanner;

public class MaxIncreasingSubseq {
    public static void main(String[] args) {
        //Get user input
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scan.nextLine();
        scan.close();

        //Initialize arrays of things
        int strLength = input.length();
        int[] scores = new int[strLength];
        int[] prevIndex = new int[strLength];
        //Make string into an array so it's easier to go through
        char[] strArray = input.toCharArray();

        scores[0] = 1;
        prevIndex[0] = -1;

        //Do the search thingy
        for (int i=0; i<strLength; i++) { //Loop through every letter of the string (current letter)
            for (int x=0; x<i; x++) { //Loop through all the letters starting at front up to current letter (loop letter)
                if (strArray[x] < strArray[i]) { //If loop letter < current letter
                    if (scores[x] >= scores[i]) { //and has a higher score than current letter's score
                        scores[i] = scores[x] + 1; //then set current letter's score to loop letter's score + 1
                        prevIndex[i] = x; //And set the index to trace back where the score came from
                    }
                }
            }
            if (scores[i] == 0) { //If no better score is found sets score and prevIndex to 0 and -1 respectively
                scores[i] = 1;
                prevIndex[i] = -1;
            }
        }
        int bestScore = getHighestIndex(scores);
        ArrayList<Character> letters = new ArrayList();
        while (bestScore != -1) { //Trace the indexes backwards to create string
            letters.add(strArray[bestScore]);
            bestScore = prevIndex[bestScore];
        }
        for (int i=letters.size()-1; i>=0; i--) { //Print the array backwards to make it the proper way
            System.out.print(letters.get(i));
        }
    }

    public static int getHighestIndex(int[] array) { //Gets the index of the largest int in an array
        int highestVal = 0;
        int index = 0;
        for (int i=0; i<array.length; i++) {
            if (array[i] > highestVal) {
                highestVal = array[i];
                index = i;
            }
        }
        return index;
    }
}