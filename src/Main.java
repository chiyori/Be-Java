
import java.io.*;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arthur
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //TEST du oui/non , améliorer avec
        //la future interface
        GameSave lastGame = new GameSave();
        Game g;
        String str = new String();

        System.out.println("Do you want to load your last game?");
        System.out.println("      Yes: Y       No: Any key");
        str = sc.nextLine();

        if (str.equals("Y") || str.equals("y")) {
            boolean b = lastGame.loadFromSerial("default.txt");
            if (!b) { // Chargement du jeu de base, Initialisation 
                System.out.println("\nLoading failed ...");
                System.out.println("Initialization of a new game");
                g = new Game();
            } else {
                System.out.println("\nLoading succeeded");
                g = new Game(lastGame.getGame());
            }
        } else {
            g = new Game();
            System.out.println("\nInitialization of a new game");
        }

        g.play();
        GameSave save = new GameSave(g);
        save.serialize("default.txt");
    }
}
