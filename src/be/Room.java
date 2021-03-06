package be;

import java.util.ArrayList;
import java.util.TreeMap;
import java.io.Serializable;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. The exits are labelled north, east, south, west.
 * For each direction, the room stores a reference to the neighboring room, or
 * null if there is no exit in that direction.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room implements Serializable {

    private String name;
    private String description;
    private TreeMap<String, Exit> roomExits;
    private ArrayList<Item> items;
    private ArrayList<People> tabPeople;

    /**
     * @param name Nom de la salle
     * @param description Description de la salle
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.roomExits = new TreeMap<>();
        this.items = new ArrayList<>();
        this.tabPeople = new ArrayList<>();
    }

    /**
     * @return Nom de la salle
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return La description de la salle.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ajout de sortie à une salle (sortie sans clé)
     * @param room
     */
    public void addExit(Room room) {
        roomExits.put(room.getName(), new Exit(this.getName(), room));
            // Key du Hashmap est le nom de la salle 
        // Mettre la salle reliée en deuxième à chaque fois
    }

    /**
     * Ajout de sortie à une salle (ayant besoin d'une clé)
     * @param room 
     * @param l
     * @param k 
     */
    public void addExit(Room room, Lock l, Key k) {
        roomExits.put(room.getName(), new MagicalExit(this.getName(),room,l,k));
            // Key du Hashmap est le nom de la salle 
        // Mettre la salle reliée en deuxième à chaque fois
    }
    
    /**
     *
     * @param exitRoom Nom de la ou des salles ayant une sortie commune avec la
     * salle demandée
     * @return La salle demand�e
     */
    public Exit returnExits(String exitRoom) {
        return roomExits.get(exitRoom);
    }

    /**
     * Affichage des sorties possibles pour la salle dans laquelle on est
     */
    public void printPossibleExits() {
        System.out.println("You are in " + getName());
        System.out.println("Available exits : ");

        for (String key : roomExits.keySet()) {
            System.out.println(roomExits.get(key));
        }

    }

    /**
     * Test si la sortie existe
     * @param dir sortie vers laquel on veux aller
     * @return boolean
     */
    public boolean ExitsIsEquals(String dir) {
        for (String key : roomExits.keySet()) {
            if (key.equals(dir)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ajout d'un item dans la salle
     *
     * @param item Item a ajout� dans la salle
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     *
     * @return La liste des items de la salle
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Supprime un objet en donnant en param�tre un ITEM
     * @param item Objet � supprimer de la liste
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Supprime un objet en donnant en param�tre le nom de cet objet
     * @param s Nom de l'objet
     * @return Un objet
     */
    public Item removeItem(String s) {
        Item i;
        for (Item item : items) {
            if (item.getDescription().equals(s)) {
                i = item;
                removeItem(item);
                return i;
            }
        }
        return null;
    }

    /**
     * Recherche d'un objet dans la liste
     * @param s Nom de l'objet
     * @return True si objet trouv�, False, si objet non trouv�
     */
    public boolean checkItem(String s) {
        for (Item item : items) {
            if (item.getDescription().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Affichage de la liste des objets
     */
    public void printItemsList() {
        System.out.println("Objects : ");
        for (Item i : items) {
            System.out.println(i.getDescription());
        }
    }
    
    /*
    * Ajout une personne dans la liste de personnes
    * @param p : personne à ajouter 
    */
    public void addPeople(People p){
        tabPeople.add(p);
    }

    public ArrayList<People> getTabPeople() {
        return tabPeople;
    }
    
    /*
    *Affichage de la liste de personnage
    */
    public void printTabPeople() {
        System.out.println("Characters : ");
        for (People p : tabPeople){
            if (p.isDead()!=true){
            System.out.println(p.getName());
            }
        }
    }
}
