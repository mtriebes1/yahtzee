/**
* This Class calculates the user's hand
* CPSC 224 - Sring 2020
* Programming Assignment #3 (hand.java)
* Sources: Bruce Worobek yahtzee.cpp
* 
* @author Matthew Triebes 
* @version v1.0 2/10/20
*/

public class hand
{

    public int num_dice;
    public int num_sides;
    public int total_turns;

    public hand () // -------------------------------------------------------------------------------------------------- Constructor
    {
        num_dice = 0;
        num_sides = 0;
        total_turns = 0;
    }
    
    public void sort(int array[], int size) // ------------------------------------------------------------------ Sort Function (Bubble Sort)
    {
        boolean swap = false;
        int temp = 0;
        do {
            swap = false;
            for (int i = 0; i < (size - 1); i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swap = true;
                }
            }
        } while (swap);
    }

    public int maxOfAKindFound(int hand[], int num_sides, int num_dice) // ------------------------------------------------------------------ Max of a Kind
    {
        int max = 0;
        int cur = 0;
        for (int dieValue = 1; dieValue <= num_sides; dieValue++) {
            cur = 0;
            for (int diePosition = 0; diePosition < num_dice; diePosition++) {
                if (hand[diePosition] == dieValue)
                    cur++;
            }
            if (cur > max)
                max = cur;
        }
        return max;
    }

    public int totalAllDice(int hand[], int num_dice) // ------------------------------------------------------------------ Dice Total                                          
    {
        int total = 0;
        for (int i = 0; i < num_dice; i++) {
            total += hand[i];
        }
        return total;
    }

    public int maxStraightFound(int hand[], int num_dice) // ------------------------------------------------------------------ Max Straight
    {                                              // Length of longest straight found in hand 
        int max = 1;
        int cur = 1;
        for (int i = 0; i < num_dice - 1; i++) {
            if (hand[i] + 1 == hand[i + 1]) // jump of 1
                cur++;
            else if (hand[i] + 1 < hand[i + 1]) // jump of >= 2
                cur = 1;
            if (cur > max)
                max = cur;
        }
        return max;
    }

    public boolean fullHouseFound(int hand[], int num_sides, int num_dice) // ------------------------------------------------------------------ Full House
    {   
        boolean foundFH = false;
        boolean found3K = false;
        boolean found2K = false;
        int currentCount ;
        for (int dieValue = 1; dieValue <= num_sides; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < num_dice; diePosition++)
            {
                if (hand[diePosition] == dieValue)
                    currentCount++;
            }
            if (currentCount >= 2)
                found2K = true;
            if (currentCount >= 3)
                found3K = true;
        }
        if (found2K && found3K)
            foundFH = true;
        return foundFH;
    }
} //end of class