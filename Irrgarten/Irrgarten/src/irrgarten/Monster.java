
package irrgarten;

/**
 *
 * @author alberto y santiago
 */
public class Monster {
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster(String name, float intelligence, float strength) {
        
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        
        this.health = INITIAL_HEALTH;
        
    }
    
    public boolean dead() {
        return health <= 0;        
    }
    
    public float attack (){
        return Dice.intensity(strength);
    }
    
    public boolean defend (float receivedAttack){
        boolean isDead = dead();
        if ( !isDead ){
            float defensiveEnergy = Dice.intensity(intelligence);
            if(defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
    
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
        
    }
    
    public String toString(){
        return "Monster [name = " + name + ", row = " + row + ", col = " + col + ",health = " + health + ", strength = " + strength +"]";        
    }
    
    private void gotWounded(){
        if (health > 0){
            health--;
        }
    }
}
