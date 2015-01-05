package be;

import java.util.ArrayList;
import java.io.Serializable;

//@TODO Description des Rooms, ajout des personnages dans les Rooms et leurs objets
// @TODO Faire marcher les magicExits

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game implements Serializable {
    
    final private transient Parser parser;
    private Player player;
    private Room[] map;

    public Room[] getMap() {
        return map;
    }

    /**
     * Créer un jeu et initialisation de la map.
     */
    public Game() {
        Room currentRoom;
        currentRoom = initialize();
        parser = new Parser();
        player = new Player("Player", currentRoom);
    }
    
    public Game(GameSave g){
        parser = new Parser();
        player = g.getGame().getPlayer();
        this.map = g.getGame().getMap();
    }
    public Game(Game g){
        parser = new Parser();
        player = g.player;
        this.map = g.map;
    }
    public Player getPlayer() {
        return player;
    }

    /**
     * Creation de toutes les salles, et des portes entre elles
     */
    private Room initialize() {
        Room elevator, ground, basement, first, room1, room2, room3, room4, room5, room6, kitchen, cafetaria, psychologistOffice, waitingRoom, staffRoom, heatingRoom, surgery, morgue;

        // Creation des salles
        ground = new Room("Ground floor", "");
        first = new Room("First floor", "");
        basement = new Room("Basement floor", "");
        elevator = new Room("Elevator", "");
        room1 = new Room("Room 1", "");
        room2 = new Room("Room 2", "");
        room3 = new Room("Room 3", "");
        room4 = new Room("Room 4", "");
        room5 = new Room("Room 5", "");
        room6 = new Room("Room 6", "");
        kitchen = new Room("Kitchen", "");
        cafetaria = new Room("Cafetaria", "");
        psychologistOffice = new Room("Psychologist Office", "");
        waitingRoom = new Room("Waiting room", "");
        staffRoom = new Room("Staff room", "");
        heatingRoom = new Room("Heating room", "");
        surgery = new Room("Surgery", "It's not a very clean room, there is blood EVERYWHERE !");
        morgue = new Room("Morgue", "");

        //Ajout des liens entre les salles
        elevator.addExit(first);
        elevator.addExit(ground);
        elevator.addExit(basement);
        ground.addExit(elevator);
        ground.addExit(psychologistOffice);
        ground.addExit(waitingRoom);
        ground.addExit(cafetaria);
        basement.addExit(elevator);
        basement.addExit(heatingRoom);
        basement.addExit(morgue);
        basement.addExit(surgery);
        first.addExit(room1);
        first.addExit(room2);
        first.addExit(room3);
        first.addExit(room4);
        first.addExit(room5);
        first.addExit(room6);
        first.addExit(elevator);
        room1.addExit(first);
        room2.addExit(first);
        room3.addExit(first);
        room4.addExit(first);
        room5.addExit(first);
        room6.addExit(first);
        kitchen.addExit(cafetaria);
        cafetaria.addExit(kitchen);
        cafetaria.addExit(ground);
        psychologistOffice.addExit(ground);
        waitingRoom.addExit(ground);
        waitingRoom.addExit(staffRoom);
        staffRoom.addExit(waitingRoom);
        heatingRoom.addExit(basement);
        surgery.addExit(basement);
        morgue.addExit(basement);

        //Création des Item
        Item fleur = new Item("Fleur",0);
        
        //Ajout des items dans les Room
        room1.addItem(fleur);
        
        //Création des listes d'Item des People
        ArrayList<Item> itemsNeeded = new ArrayList<>();
        itemsNeeded.add(fleur);
        ArrayList<Item> itemsTheyHave = new ArrayList<>();
        
        //Creation Dialogue
        Dialog d = new Dialog("blabla");
        d.setItemGive(fleur);
        
        //Création des People dans les Room
        room2.addPeople(new People("mamieItem",d));
        
        //Création du tableau de Room utilisé pour la sauvegarde
        this.map = new Room [] {elevator, ground, basement, first, room1, room2, room3, 
        room4, room5, room6, kitchen, cafetaria, psychologistOffice, 
        waitingRoom, staffRoom, heatingRoom, surgery, morgue};
        
        return room1;
    }
        
    /**
     * Jeu en lui-meme, boucle tant qu'on ne lui dit pas d'arreter
     */
    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
      
        System.out.println("Thank you for playing.  Good bye.");
      
    }

    /**
     * Affichage du message de bienvenue
     */
    private void printWelcome() {
        System.out.println("Welcome to the hospital!");
        System.out.println("Have fun and kill peoples !");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.getRoom().printPossibleExits();
    }

    /**
     * Donner une commande qui va être executer
     *
     * @param command Commande à effectuer
     * @return true Si le jeu est fini, sinon false.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")) {
            System.out.println(player.getRoom().getDescription());
            player.getRoom().printPossibleExits();
            player.getRoom().printItemsList();
        } else if (commandWord.equals("inventory")) {
            player.printItemsList();
        } else if (commandWord.equals("pick")) {
            pick(command);
        } else if (commandWord.equals("leave")) {
            leave(command);
        }

        return wantToQuit;
    }

    /**
     * Fonction pour aller dans une salle
     *
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getOtherWord();

        if (player.getRoom().ExitsIsEquals(direction)) {
            // Try to leave current room.
            Exit exit = player.getRoom().returnExits(direction);
            if (exit == null) {
                System.out.println("There is no door!");
            } else {
                Room nextRoom = exit.getRoom();
                player.setRoom(nextRoom);
                player.getRoom().printPossibleExits();
                System.out.println();
            }
        } else {
            System.out.println("It's not a Room");
        }
    }

    /**
     * Affichage du message d'aide
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the hospital.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help look pick use leave inventory");
    }

    /**
     * Si quit entr�, check s'il y a un deuxième mot, si ce n'est pas le cas on
     * quitte le jeu
     * @return true Pour quitter le jeu, sinon false.
     */
   private boolean quit(Command command) 
    {
        if(command.hasSecondWord() || command.hasThirdWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Commande pour recupérer un objet
     * @param command Commande à effectuer
     */
    private void pick(Command command) {
        if (!command.hasSecondWord()) {
            // S'il n'y a pas de deuxi�me mot, il ne sait pas quoi prendre
            System.out.println("Pick what?");
            return;
        }
        String stringItem = command.getSecondWord();

        if (!player.getRoom().checkItem(stringItem)) {
            System.out.println("This object is not here!");
        } else {
            player.pickup(stringItem);
        }
    }

    /**
     * Commande pour laisser un objet
     *
     * @param command Commande � effectuer
     */
    private void leave(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Leave wich item?");
            return;
        }
        String stringItem = command.getSecondWord();

        if (!player.checkItem(stringItem)) {
            System.out.println("You don't have this item!");
        } else {
            player.putdown(stringItem);
        }
    }

}
