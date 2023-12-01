
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) { 
          // Mostrar la información en la consola
        System.out.println("Estado actual del juego: \n");
        System.out.println("Laberinto\n" + gameState.getLabyrinth() + "\n");
        System.out.println("Jugador actual: " + ( gameState.getCurrentPlayer() + 1) );
        System.out.println(gameState.getPlayers());
        System.out.println(gameState.getLog());
        System.out.println(gameState.getMonsters());
        if(gameState.isWinner()){
            System.out.println("Ha ganado el jugador: " + ( gameState.getCurrentPlayer() + 1) );
        }
    }
    
}