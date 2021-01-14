/**
* This program play yahtzee
* CPSC 224 - Sring 2020
* Programming Assignment #3 (yahtzee.java)
* Sources: Bruce Worobek yahtzee.cpp
* 
* @author Matthew Triebes 
* @version v2.0 2/26/20
*/

import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class yahtzee
{
    public static void main(String args[]) 
    {
        // ------------------------------------------------------------------ Declarations
        //object declarations
        dice yahtzee_dice = new dice();
        hand h1 = new hand();
        ScoreCard sc1 = new ScoreCard();

        //Scanner Declararions
        Scanner input = new Scanner(System.in); // For User input
        Scanner inFile = null; // for File input
        Scanner input_string = new Scanner(System.in);
        Scanner score_selection = new Scanner(System.in);
        
        //Varaible Declaration
        int num_rolls = 0;
        String playAgain = "y";
        String edit_settings = "";
        String yes = "";
        int turn = 0;
        String keep = "";
        int i;
        int NUM_ROUNDS = 13;
        
        //Array Declaration
        int file[] = new int[3]; //input from file
        sc1.turn_num = 1;
        
        // ------------------------------------------------------------------ Reads from File
        i = 0;
        try {
            inFile = new Scanner(new File("yahtzeeConfig.txt"));
            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                int int_line = Integer.parseInt(line);
                file[i] = int_line;
                ++i;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // ------------------------------------------------------------------ Initialization of Starting Variables        
        h1.num_sides = file[0];
        h1.num_dice = file[1];
        num_rolls = file[2];
        
        System.out.println("<---- WELCOME TO YAHTZEE ---->\n");
        System.out.println("Initial Settings:");
        System.out.println("Number of dice in play: " + h1.num_dice);
        System.out.println("Number of sides on each dice: " + h1.num_sides);
        System.out.println("Number of rolls: " + num_rolls);
        System.out.println("Number of rounds: " + NUM_ROUNDS);
        System.out.println();
        
        System.out.print("Enter 'y' if you would like to edit settings, Enter 'n' to skip: ");
        edit_settings = input.nextLine();

        if (edit_settings.equals("y") || edit_settings.equals("Y")) // edit settings option
        {
            do { // to make sure user doesn't enter less than 5 dice  
                System.out.print("Enter Number of Dice (>= 5): ");
                        h1.num_dice = input.nextInt();
            } while (h1.num_dice < 5);
            
            System.out.print("Enter Number of Sides: ");
                h1.num_sides = input.nextInt();
            System.out.print("Enter Number of Rolls: ");
                num_rolls = input.nextInt();
            
            do { // to make sure user doesn't enter more than 13 rounds  
                System.out.print("Enter Number of Rounds (<= 13): ");
                    NUM_ROUNDS = input.nextInt();
            } while (NUM_ROUNDS > 13);
            
            System.out.println("\n<---- UPDATED SETTINGS ---->");
            System.out.println("Number of dice in play: " + h1.num_dice);
            System.out.println("Number of sides on each dice: " + h1.num_sides);
            System.out.println("Number of rolls: " + num_rolls);
            System.out.println("Number of rounds: " + NUM_ROUNDS);
        }

        int hand[] = new int[h1.num_dice];
        Boolean selected[] = new Boolean[h1.num_sides + 7]; //keeps track of what user has selcted in previous rounds

        for (i = 0; i < h1.num_dice; i++){
            yes = yes + 'y';
        }

        for (i = 0; i < h1.num_sides + 7; i++){
            selected[i] = false;
        }
        
        playAgain = "y";

        // ------------------------------------------------------------------ Game Begins
        while (playAgain.equals("y")) 
        {   
            sc1.turn_num = 1;
            while (sc1.turn_num <= NUM_ROUNDS)
            {
                turn = 1;
                System.out.println();
                System.out.println("Round: " + sc1.turn_num);
                System.out.println();
                keep = "";
                for (i = 0; i < h1.num_dice; i++){
                    keep = keep + 'n';
                }
                System.out.println("turn: " + turn);
                System.out.println("keep: " + keep);
                while ((turn < (num_rolls + 1)) & (!keep.equals(yes))) 
                {  
                    for (i = 0; i < h1.num_dice; ++i) {
                        if (keep.charAt(i) != 'y') {
                            hand[i] = yahtzee_dice.roll(h1.num_sides);
                        }
                    }
                    System.out.print("Your Roll Was: ");
                    for (i = 0; i < h1.num_dice; i++){
                        System.out.print(hand[i] + " ");
                    }
                    if (turn < num_rolls) {
                        System.out.println();
                        System.out.print("Enter the Dice you want to keep (y or n): ");
                        keep = input_string.nextLine();
                    }
                    turn++;
                }

                h1.sort(hand, h1.num_dice);
                System.out.print("Here is your sorted hand: ");
                for (i = 0; i < h1.num_dice; i++)
                {
                    System.out.print(hand[i] + " ");
                }
                
                System.out.println();

                sc1.roundScore(hand, h1.num_sides, h1.num_dice, selected);
                
                System.out.println("Select Scorecard Line (type number): ");
                sc1.score_selection = score_selection.nextInt();
                selected[sc1.score_selection - 1] = true;
                
                score_selection.nextLine();

                System.out.println("Type 'S' to see scorecard: ");
                edit_settings = input_string.nextLine();
                if (edit_settings.equals("S") | edit_settings.equals("s")) {
                    sc1.gameScore(NUM_ROUNDS, h1.num_sides);
                }

                sc1.turn_num++;
            } // end of while
            System.out.print("Enter 'y' to play another game: ");
            playAgain = input_string.nextLine();
        } // end of while
    } // end of main

} // end of class

