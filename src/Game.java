import java.util.ArrayList;


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
public class Game {

    final private Parser parser;
    final private Player player;

    /**
     * Cr�er un jeu et initialisation de la map.
     */
    public Game() {
        parser = new Parser();
        player = new Player("Player", createRooms());
    }

    /**
     * Creation de toutes les salles, et des portes entre elles
     */
    private Room createRooms() {
        Room elevator, ground, basement, first, room1, room2, room3, room4, room5, room6, kitchen, cafetaria, psychologistOffice, waitingRoom, staffRoom, heatingRoom, surgery, morgue;

        // Creation des salles
        ground = new Room("The hall of the hospital", "");
        first = new Room("Firstfloor", "");
        basement = new Room("Hall of the basement", "");
        elevator = new Room("Elevator", "");
        room1 = new Room("Room1", "");
        room2 = new Room("Room2", "");
        room3 = new Room("Room3", "");
        room4 = new Room("Room4", "");
        room5 = new Room("Room 5", "");
        room6 = new Room("Room 6", "");
        kitchen = new Room("Kitchen", "");
        cafetaria = new Room("Cafetaria", "");
        psychologistOffice = new Room("Psychologist Office", "");
        waitingRoom = new Room("Waiting room", "");
        staffRoom = new Room("Staff room", "");
        heatingRoom = new Room("Heating room", "");
        surgery = new Room("Surgery", "");
        morgue = new Room("Morgue", "");

        // Faire les portes dans l'autre sens aussi , je crois que c'est déjà
        //fait
        elevator.addSimpleExits(first);
        elevator.addSimpleExits(ground);
        elevator.addSimpleExits(basement);
        ground.addSimpleExits(elevator);
        ground.addSimpleExits(psychologistOffice);
        ground.addSimpleExits(waitingRoom);
        ground.addSimpleExits(cafetaria);
        basement.addSimpleExits(elevator);
        basement.addSimpleExits(heatingRoom);
        basement.addSimpleExits(morgue);
        basement.addSimpleExits(surgery);
        first.addSimpleExits(room1);
        first.addSimpleExits(room2);
        first.addSimpleExits(room3);
        first.addSimpleExits(room4);
        first.addSimpleExits(room5);
        first.addSimpleExits(room6);
        first.addSimpleExits(elevator);
        room1.addSimpleExits(first);
        room2.addSimpleExits(first);
        room3.addSimpleExits(first);
        room4.addSimpleExits(first);
        room5.addSimpleExits(first);
        room6.addSimpleExits(first);
        kitchen.addSimpleExits(cafetaria);
        cafetaria.addSimpleExits(kitchen);
        cafetaria.addSimpleExits(ground);
        psychologistOffice.addSimpleExits(ground);
        waitingRoom.addSimpleExits(ground);
        waitingRoom.addSimpleExits(staffRoom);
        staffRoom.addSimpleExits(waitingRoom);
        heatingRoom.addSimpleExits(basement);
        surgery.addSimpleExits(basement);
        morgue.addSimpleExits(basement);

        //Ajout des items
        Item fleur = new Item("Fleur",0);
        
        //placement des items dans les rooms
        room1.addItem(fleur);
        
        //placement des items dans des liste et création de personnage dans les salles
        ArrayList<Item> itemsNeeded = new ArrayList<>();
        itemsNeeded.add(fleur);
        ArrayList<Item> itemsTheyHave = new ArrayList<>();
        room2.addPeople(new People("mamieItem",itemsNeeded,itemsTheyHave));
        itemsNeeded.clear();
        itemsTheyHave.clear();

        return room1;  //D�marrage du jeu dans la salle room1
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
        System.out.println();
        System.out.println("Welcome to the hospital!");
        System.out.println("Have fun and kill peoples !");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.getRoom().printPossibleExits();
    }

    /**
     * Donner une commande qui va �tre executer
     *
     * @param command Commande � effectuer
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

        String direction = command.getSecondWord();

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
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
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
