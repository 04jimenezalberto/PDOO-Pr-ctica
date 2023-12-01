
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author alberto y santiago
 */
public class Game {
    
    private static final int MAX_ROUNDS = 10;
    private static final int NROWS = 10;
    private static final int NCOLS = 10;
    private static final int EXIT_ROW = 4;
    private static final int EXIT_COL = 4;
    
    private int currentPlayerIndex;
    private String log;
    
    private ArrayList<Player> players;
    private Player currentPlayer;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    public Game (int nplayers, boolean debug){
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        labyrinth = new Labyrinth(NROWS, NCOLS, EXIT_ROW,EXIT_COL);
        log = "";
        if(debug){
            
            configureLabyrinthDebug();
            
        }else 
            configureLabyrinth();
        
        for (int i = 0; i < nplayers; i++){
            Player p = new Player((char)('1'+i), Dice.randomIntelligence(), Dice.randomStrength());
            players.add(p);
        }
        
        
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);        
        labyrinth.spreadPlayers(players);
        
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
       log = "";
        boolean dead = currentPlayer.dead();
        
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            
            if(direction != preferredDirection) logPlayerNoOrders();
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if (monster == null){
                logNoMonster();
            }else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }else manageResurrection();
        
        boolean endGame = finished();
        
        if(!endGame) nextPlayer();
        
        return endGame;
    }
    
    public GameState getGameState(){
        String labyrinthStr = labyrinth.toString();
        
        String playersStr = "";
        for (Player player : players) {
            playersStr += player.toString();
        }
        
        String monstersStr = "";
        for (Monster monster : monsters) {
            playersStr += monster.toString();
        }

        boolean winner = finished();

        GameState gameState = new GameState(labyrinthStr, playersStr, monstersStr, currentPlayerIndex, winner, log);

        return gameState;
        
    }
    
    private void configureLabyrinth(){
        
        for (int i = 0; i < ((NROWS + NCOLS)/4); i++) {
           labyrinth.addBlock(Orientation.HORIZONTAL, Dice.randomPos(NROWS), Dice.randomPos(NCOLS), Dice.usesLeft());
           labyrinth.addBlock(Orientation.VERTICAL, Dice.randomPos(NROWS), Dice.randomPos(NCOLS), Dice.usesLeft());
        }
        for (int i = 0; i < ((NROWS + NCOLS)/2); i++) {
            int[] randomPos = labyrinth.randomEmptyPos();
            int row = randomPos[0];
            int col = randomPos[1];


            Monster monster = new Monster("Monster " + i, Dice.randomIntelligence(), Dice.randomStrength());

            labyrinth.addMonster(row, col, monster);
            monsters.add(monster);
        }
        
    }
    
    private void nextPlayer(){
        
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        for(int i=0; (!lose) && (rounds < MAX_ROUNDS); i++){
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            winner = GameCharacter.MONSTER;
            rounds++;
            if (!lose){
                playerAttack = currentPlayer.attack();
                lose = monster.defend(playerAttack);
                winner = GameCharacter.PLAYER;
            }
        }
        
        logRounds(rounds, MAX_ROUNDS);
        
        return winner;        
    }
    
    private void manageReward(GameCharacter winner){
        if( winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }else{
            logMonsterWon();
        }
        
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if (resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }else{
            logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate.\n";
    }
    
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate.\n";
    }
    
    private void logResurrected(){
        log += "El jugador ha resucitado.\n";        
    }
    
    private void logPlayerSkipTurn(){
        log += "El jugador ha perdido el turno por estar muerto.\n";     
    }
    
    private void logPlayerNoOrders(){
        log += "El jugador no ha seguido las instrucciones del jugador humano (no fue posible).\n";        
    }
    
    private void logNoMonster(){
        log += "El jugador se ha movido a una celda vacía o no le ha sido posible moverse.\n";        
    }
    
    private void logRounds(int rounds, int max){
        log += "Se han producido"+rounds+"de"+ MAX_ROUNDS+" de max rondas de combate.\n";
        
    }
    private void configureLabyrinthDebug(){
        // Agregar bloques de obstáculos al laberinto
        labyrinth.addBlock(Orientation.HORIZONTAL, Dice.randomPos(NROWS), Dice.randomPos(NCOLS), 5);
        labyrinth.addBlock(Orientation.VERTICAL, Dice.randomPos(NROWS), Dice.randomPos(NCOLS), 5);

        // Agregar monstruos al laberinto y guardarlos en el contenedor propio
        for (int i = 0; i < 5; i++) {
            int[] randomPos = labyrinth.randomEmptyPos();
            int row = randomPos[0];
            int col = randomPos[1];

            Monster monster = new Monster("Monster " + i, 1, 1);

            labyrinth.addMonster(row, col, monster);
            monsters.add(monster);
        }
    }
}
    
