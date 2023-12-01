
package irrgarten;

/**
 *
 * @author alberto y santiago
 */
public class TestP1 {
     public static void main(String[] args) {
        // Crea instancias de las clases
        Weapon weapon = new Weapon(2.0f, 5);
        Shield shield = new Shield(3.0f, 4);
        GameState gameState = new GameState("Laberinto", "Jugadores", "Monstruos", 0, false, "Log");

        // Prueba los métodos de las clases
        System.out.println("Pruebas con Weapon:");
        System.out.println("Weapon power: " + weapon.attack());
        System.out.println("Weapon Info: " + weapon);

        System.out.println("\nPruebas con Shield:");
        System.out.println("Shield protection: " + shield.protect());
        System.out.println("Shield Info: " + shield);

        System.out.println("\nPruebas con GameState:");
        System.out.println("Current Player: " + gameState.getCurrentPlayer());
        System.out.println("Is Winner? " + gameState.isWinner());

        for (int contador = 0; contador < 2; contador++) {
            System.out.println("Random Intelligence: " + Dice.randomIntelligence());
            System.out.println("Random Strength: " + Dice.randomStrength());
            System.out.println("Resurrect Player? " + Dice.resurrectPlayer());
            System.out.println("Weapons Reward: " + Dice.weaponsReward());
            System.out.println("Shields Reward: " + Dice.shieldsReward());
            System.out.println("Health Reward: " + Dice.healthReward());
            System.out.println("Weapon Power: " + Dice.weaponPower());
            System.out.println("Shield Power: " + Dice.shieldPower());
            System.out.println("Uses Left: " + Dice.usesLeft());
            System.out.println("Intensity: " + Dice.intensity(10.0f));
            System.out.println("Discard Element: " + Dice.discardElement(3));
        }

    }
    
    
    
}
