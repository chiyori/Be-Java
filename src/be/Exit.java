package be;

import java.io.Serializable;

/**
 * Write a description of class Exit here.
 *
 * @author Arthur PERRAD
 * @version 14/11/14
 */
public class Exit implements Serializable{
    
    private String currentRoom;
    private Room room;

    /**
     * @param room Room à la sortie de la salle
     * @param currentR Nom de la salle à laquel on ajoute une sortie
     */
    public Exit(String currentR, Room room) {
        currentRoom = currentR;
        this.room = room;
    }

    /**
     *
     * @return Room à l'extérieur de la salle
     */
    public Room getRoom() {
        return room;
    }
    
    

    /**
     * Fonction qui ouvre permet d'ouvrir la salle
     *
     * @param p Le joueur
     * @return La salle dans laquelle le joeur va
     */
    public Room open(Player p) {
       return room; // Rien d'extraordinaire car la porte n'a pas de clé
    }
    
    @Override
    public String toString(){
       return getRoom().getName();
    }

}
