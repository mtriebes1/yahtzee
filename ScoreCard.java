/**
 * This Computes the users scorecard at the end of the turn CPSC 224 - Sring
 * 2020 Programming Assignment #1 (ScoreCard.java) Sources: Bruce Worobek
 * yahtzee.cpp
 * 
 * @author Matthew Triebes
 * @version v1.0 
 * @date 2/12/20
 */

import java.util.Arrays;

public class ScoreCard
{

    public int turn_num;
    public int score_selection;
    private int round_score;
    private int[] row_total = new int[30];
    Display_Scorecard points = new Display_Scorecard();

    // -------------------------------------------------------------------------------------------- Cunstructor
    public ScoreCard()
    {
        turn_num = 1;
        score_selection = 1;
        round_score = 0;
        for (int i = 0; i < 30; i++){
            row_total[i] = 0;
        }
    }

    // -------------------------------------------------------------------------------------------- Round Score
    public void roundScore(int hand[], int num_sides, int num_dice, Boolean selected[])
    {
        int i, filler;
        int upper_score[] = new int[num_sides];
        hand h1 = new hand();
        String[][] score = new String[num_sides + 8][3];

        // ---- Sets up Score Array user sees

        score[0][0] = "  ";
        score[0][1] = "RESULT:        ";
        score[0][2] = "SCORE";

        for (i = 1; i <= num_sides + 7; ++i) {
            if (i < 10)
                score[i][0] = Integer.toString(i) + " ";
            else 
                score[i][0] = Integer.toString(i);
        }

        for (i = 1; i <= num_sides; i++) {
            if (i < 10) 
                score[i][1] = "side " + Integer.toString(i) + "         ";
            else 
                score[i][1] = "side " + Integer.toString(i) + "        ";
        }

        score[num_sides + 1][1] = "3 of a Kind    ";
        score[num_sides + 2][1] = "4 of a Kind    ";
        score[num_sides + 3][1] = "Full House     ";
        score[num_sides + 4][1] = "Small Straight ";
        score[num_sides + 5][1] = "Large Straight ";
        score[num_sides + 6][1] = "Yahtzee        ";
        score[num_sides + 7][1] = "Chance         ";

        // ----- Fills Upper Score Card    
        for (i = 0; i< num_sides; i++) { //initializes lower score array to 0
                upper_score[i] = 0;
        }
        for (int j = 0; j < num_dice; j++) { // Fills lower score array with values
                upper_score[hand[j] - 1] = upper_score[hand[j] - 1] + hand[j];
        }
        for (i = 1; i <= num_sides; i++) { // Prints out the lower score card
                score[i][2] = (Integer.toString(upper_score[i - 1]));
        }

        // ----- Fills Lower Score Card
        if (h1.maxOfAKindFound(hand, num_sides, num_dice) >= 3) {
            score[num_sides + 1][2] = Integer.toString(h1.totalAllDice(hand, num_dice));
        } else {
            filler = 0;
            score[num_sides + 1][2] = Integer.toString(filler);
        }

        if (h1.maxOfAKindFound(hand, num_sides, num_dice) >= 4) {
            score[num_sides + 2][2] = Integer.toString(h1.totalAllDice(hand, num_dice));
        } else {
            score[num_sides + 2][2] = Integer.toString(0);
        }

        if (h1.fullHouseFound(hand, num_sides, num_dice))
            score[num_sides + 3][2] = Integer.toString(25);
        else
            score[num_sides + 3][2] = Integer.toString(0);

        if (h1.maxStraightFound(hand, num_dice) >= 4)
            score[num_sides + 4][2] = Integer.toString(30);
        else
            score[num_sides + 4][2] = Integer.toString(0);

        if (h1.maxStraightFound(hand, num_dice) >= 5)
            score[num_sides + 5][2] = Integer.toString(40);
        else
            score[num_sides + 5][2] = Integer.toString(0);

        if (h1.maxOfAKindFound(hand, num_sides, num_dice) >= num_dice)
            score[num_sides + 6][2] = Integer.toString(50);
        else
            score[num_sides + 6][2] = Integer.toString(0);

        score[num_sides + 7][2] = Integer.toString(h1.totalAllDice(hand, num_dice));

        for (i = 1; i <= num_sides + 7; ++i) {
            row_total[i - 1] = Integer.parseInt(score[i][2]);
            if (Integer.parseInt(score[i][2]) == 0 | selected[i - 1] == true) {
                score[i][0] = "--";
                score[i][1] = "---------------";
                score[i][2] = "--";
            }
        }

        System.out.println();
        System.out.println(Arrays.deepToString(score).replace("], ", "\n").replace("[[", "[").replace("]]", "").replace(",", "|"));
    }

    // -------------------------------------------------------------------------------------------- Game Score
    public void gameScore (int total_turns, int num_sides) 
    {
        round_score = row_total[score_selection - 1];
        points.score_chart(total_turns, num_sides, score_selection, turn_num, round_score);
            if (score_selection <= num_sides) {
                points.upper_score = points.upper_score + round_score;
            }
            else {
                points.lower_score = points.lower_score + round_score;
            }
            
            if (turn_num == total_turns & points.upper_score > 63) { // bonus adding Part
                points.upper_score = points.upper_score + 35;
            }
            
        System.out.println("\n" + "Upper Card Total: " + points.upper_score);
        System.out.println("Lower Card Total: " + points.lower_score);
        System.out.println("Score Total: " + (points.lower_score + points.upper_score) + "\n");
    }
} // end of score card class 