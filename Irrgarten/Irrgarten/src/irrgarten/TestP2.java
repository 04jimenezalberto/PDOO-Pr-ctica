/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author alberto y santiago
 */
public class TestP2 {
    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth(10,10,9,9);
        
        for (int i =0; i< 5; i++){
            Monster monster = new Monster("monster "+i, Dice.randomIntelligence(), Dice.randomStrength());
            int[] pos = labyrinth.randomEmptyPos();
            int row = pos[0];
            int col = pos[1];
            labyrinth.addMonster(row, col, monster);
            System.out.println(monster);
        }
                
        System.out.println(labyrinth);
        
        
    }
}
