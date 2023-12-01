/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten.juego;

/**
 *
 * @author alberto y santiago
 */
import irrgarten.controller.Controller;
import irrgarten.UI.TextUI;
import irrgarten.Game;

public class programa { 
    public static void main(String[] args) {
        TextUI vista = new TextUI();
        Game juego = new Game(2, false);
        Controller controlador = new Controller(juego, vista);
        
        controlador.play();
    }
}
