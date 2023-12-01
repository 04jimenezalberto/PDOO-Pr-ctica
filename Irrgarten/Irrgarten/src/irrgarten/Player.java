
package irrgarten;
import java.util.ArrayList;
/**
 *
 * @author alberto y santiago
 */
public class Player {
    private static final int  MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player (char number, float intelligence, float strength){
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        
        this.name = "Player #" + number;
        this.health = INITIAL_HEALTH;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>(); 
               
    }
    
    public void resurrect() {
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        
        health = INITIAL_HEALTH;
        
        consecutiveHits = 0;
        
    }
    
    public char getNumber(){
        return number;
        
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
        
    }
    
    public boolean dead(){
        return health <= 0;
        
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
       boolean contained = validMoves.contains(direction);
       
       if ( (size > 0) && (!contained) ){
           Directions firstElement = validMoves.get(0);
           return firstElement;
       }else 
           return direction;
       
    }
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
        
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i=1; i <= wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        
        for(int i=1; i <= sReward; i++){
            Shield snew = newShield();
            receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        
        health += extraHealth;
        
    }
    
    public String toString(){
        return "Player [name=" + name + ", row = "+ row + ", col = " + col + ", health "+ health + ", strength = " + strength + "]";
        
    }
    
    private void receiveWeapon(Weapon w){
        for( int i=weapons.size()-1; i >=0; i-- ){
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();
            
            if(discard){
                weapons.remove(wi);
            }
        }
        
        int size = weapons.size();
        
        if(size < MAX_WEAPONS) 
            weapons.add(w);
        
    }
    
    private void receiveShield(Shield s){
        for( int i=shields.size()-1; i >=0; i-- ){
            Shield si = shields.get(i);
            boolean discard = si.discard();
            
            if(discard){
                shields.remove(si);
            }
        }
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS) 
            shields.add(s);
        
    }
    
    private Weapon newWeapon(){
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();
        
        return new Weapon(power, uses);
    }
    
    private Shield newShield(){
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        
        return new Shield (protection, uses);
    }
    
    private float sumWeapons(){
        float totalAttack = 0;
        for (Weapon weapon : weapons) {
            totalAttack += weapon.attack();
        }
        return totalAttack;
        
        
    }
    
    private float sumShields(){
        float totalProtection = 0;
        for (Shield shield : shields) {
            totalProtection += shield.protect();
        }
        return totalProtection;        
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();        
    }
    
    private boolean manageHit(float receivedAttack){
        float defense = defensiveEnergy();
        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }else resetHits();
        
        boolean lose;
        if( consecutiveHits == HITS2LOSE || dead()){
            resetHits();
            lose = true;
        }else lose = false;
        return lose;
        
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        if (health > 0) {
            health--;
        }
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
        
    }
}
