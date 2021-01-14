
/**
 * This class takes the valeues from the score card class and uses them to make
 * a complete game CPSC 224 - Sring 2020 Programming Assignment #3
 * (ScoreCard.java) Sources: Bruce Worobek yahtzee.cpp
 * 
 * @author Matthew Triebes
 * @version v1.0 2/26/20
 */

import java.util.Arrays;

public class Display_Scorecard {
    
    public int hand[];
    public int dice;
    public int upper_score;
    public int lower_score;

    public Display_Scorecard() {
        dice = 0;
        upper_score = 0;
        lower_score = 0;
    }

    // ---------------------------------------------------------------------------------------- Total Score
    public void print_total_score() {
        System.out.println("Total Score: " + upper_score + lower_score);
    }

    // ---------------------------------------------------------------------------------------- Score Card
    public void score_chart(int turns, int sides, int choice, int current_turn, int round_score) 
    {
        int i, j;
        String[][] score_array = new String [sides + 8][turns + 2];
            
            for (i = 1; i <= sides + 7; i++) {
                if (i < 10) {
                    score_array[i][0] = Integer.toString(i) + ".   " ;
                    score_array[0][0] = "    ";
                }
                else {
                    score_array[i][0] = Integer.toString(i) + ".  " ;
                    score_array[0][0] = "     ";
                }
            }

            for (i = 1; i <= sides; i++) {
                if (i < 10) 
                    score_array[i][1] = "side " + Integer.toString(i) + "         ";
                else 
                    score_array[i][1] = "side " + Integer.toString(i) + "        ";
            }
            score_array[0][1] = "ROUND #:       ";
            score_array[sides + 1][1] = "3 of a Kind    ";
            score_array[sides + 2][1] = "4 of a Kind    ";
            score_array[sides + 3][1] = "Full House     ";
            score_array[sides + 4][1] = "Small Straight ";
            score_array[sides + 5][1] = "Large Straight ";
            score_array[sides + 6][1] = "Yahtzee        ";
            score_array[sides + 7][1] = "Chance         ";

            for (i = 2; i <= turns + 1; i++) {
                score_array[0][i] = "RND " + Integer.toString(i - 1);
            }

            for (i = 1; i <= sides + 7; i++) {
                for (j = 2; j <= turns + 1; j++) {
                    score_array[i][j] = "-----";
                }
            }

            if (round_score < 10) {
                score_array[choice][current_turn + 1] = "  " + Integer.toString(round_score) + "  ";
            } else { 
                score_array[choice][current_turn + 1] = "  " + Integer.toString(round_score) + " ";
            }

            System.out.println(Arrays.deepToString(score_array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]").replace(",", "|"));
        }
    }
