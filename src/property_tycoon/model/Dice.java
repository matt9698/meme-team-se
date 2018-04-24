/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Random;

/**
 *
 * @author adam
 */
public class Dice
{

    /**
     * 
     * @return an int array where each int is the value rolled on each dice
     */
    public static int[] rollDice()
    {

        Random random = new Random();
        int roll1 = random.nextInt(6) + 1;
        int roll2 = random.nextInt(6) + 1;

        int[] roll = new int[2];
        roll[0] = roll1;
        roll[1] = roll2;
        return roll;
    }
}
