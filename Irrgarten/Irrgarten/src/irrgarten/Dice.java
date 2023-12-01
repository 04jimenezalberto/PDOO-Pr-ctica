/**
 *
 * @author alberto y santiago
 */
package irrgarten;
import java.util.Random;

public class Dice {
    
    private static  int MAX_USES = 5;
    private static  double MAX_INTELLIGENCE = 10.0;
    private static  double MAX_STRENGTH = 10.0; 
    private static  double RESURRECT_PROB = 0.3;        
    private static  int WEAPONS_REWARD = 2;
    private static  int SHIELDS_REWARDS = 3;
    private static  int HEALTH_REWARD = 5;
    private static  int MAX_ATTACK = 3;        
    private static  int MAX_SHIELD = 2;    
    
    private static  Random generator = new Random();
    
    
    public static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
        
    }
    
    public static float randomIntelligence(){
        return generator.nextFloat() * (float)MAX_INTELLIGENCE;
    }
    
    public static float randomStrength(){
        return generator.nextFloat() * (float)MAX_STRENGTH;
    }
    
    public static boolean resurrectPlayer(){
        double dato = generator.nextDouble();
        return dato >= RESURRECT_PROB;
    }
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD );
    }
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARDS);
    }
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
        
    }
    
    public static float weaponPower(){
        return generator.nextFloat() *(float)MAX_ATTACK;
    }
    
    public static float shieldPower(){
        return generator.nextFloat() * (float)MAX_SHIELD;
    }
    
    public static int usesLeft(){
        return generator.nextInt(MAX_USES);
    }
    
    public static float intensity(float competence){
        return generator.nextFloat() * competence;
    }
    
    public static boolean discardElement(int usesLeft){
        return (generator.nextDouble() < (1 - ((double)usesLeft / (double)MAX_USES)));
    }
}

