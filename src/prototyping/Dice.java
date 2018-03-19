/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package prototyping;

import java.util.Random;

/**
 *
 * @author matth
 */
public class Dice
{
    public static void main(String[] args) 
    {
        Dice d = new Dice(2, 6);
        System.out.println(d.roll());
    }

    // Number of dice
    private int n;
    
    // Number of sides
    private int sides;
    
    // Random number generator
    private Random r;
    
    public Dice(int n, int sides)
    {
        if(n < 1) {
            throw new IllegalArgumentException(
                "There must be at least one die.");
        }
        
        if(sides < 3) {
            throw new IllegalArgumentException(
                "There must be at least three sides.");
        }
        
        this.n = n;
        this.sides = sides;
        
        r = new Random();
    }
    
    public int roll()
    {
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += r.nextInt(sides) + 1;
        }
        
        return sum;
    }
}
