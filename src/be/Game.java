package be;

import java.util.ArrayList;
import java.io.Serializable;

//@TODO Description des Rooms, ajout des personnages dans les Rooms et leurs objets
//@TODO Faire marcher les magicExits
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

    public Game(GameSave g) {
        parser = new Parser();
        player = g.getGame().getPlayer();
        this.map = g.getGame().getMap();
    }

    public Game(Game g) {
        parser = new Parser();
        player = g.player;
        this.map = g.map;
    }

    public Game(Player player, Room[] map) {
        parser = new Parser();
        this.player = player;
        this.map = map;
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
        ground = new Room("Ground floor", "Beautiful corridor with many posters "
                + "and plants");
        first = new Room("First floor", "Beautiful corridor with many posters "
                + "and plants");
        basement = new Room("Basement floor", "It's not very friendly here");
        elevator = new Room("Elevator", "It's just an elevator with many buttons");
        room1 = new Room("Room 1", "A small room, with a bed, toilet and TV. "
                + "It's very .... ORANGE !\nOh ! There is a football game on "
                + "TV ! GO REDS !");
        room2 = new Room("Room 2", "A small room, with bed, toilet and TV. "
                + "There is also an empty vase.");
        room3 = new Room("Room 3", "A small room, with a bed, toilet and TV.");
        room4 = new Room("Room 4", "A small room, with a bed, toilet and TV. "
                + "Huuuuh,it smells male !");
        room5 = new Room("Room 5", "A small room, with a bed, toilet and TV. "
                + "Huuuuh what an horrible smell ! What happened here ?!");
        room6 = new Room("Room 6", "A small room, with a bed, toilet and TV. "
                + "It smells of death here");
        kitchen = new Room("Kitchen", "A big kitchen whith many furnaces.\n"
                + "Here you can see the process from food to eatable things they"
                + " serve in the hospital.");
        cafetaria = new Room("Cafetaria", "A big place with tables and chairs"
                + " but no happiness.");
        psychologistOffice = new Room("Psychologist Office", "A little office"
                + " with a desk, some chairs and some posters on the wall ..."
                + " Maybe a bird ... A monkey ?");
        waitingRoom = new Room("Waiting room", "The famous waiting room of the hospital where you can wait few hours before a doctor call you.\n"
                + "Ho ... There is a TV !");
        staffRoom = new Room("Staff room", "A panel says 'Staff only'."
                + " The heart of the hospital.");
        heatingRoom = new Room("Heating room", "It's very HOT there.");
        surgery = new Room("Surgery", "It's not a very clean room, there is"
                +" blood EVERYWHERE !");
        morgue = new Room("Morgue", "You can see dead people ...");

        //Creation des portes avec clés et serrures 
        //
        //
        //
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
        Item fleur = new Item("Fleur", 0);

        //Ajout des items dans les Room
        room1.addItem(fleur);

        //Création des People dans les Room
        room1.addPeople(new People("Dr Connor","Hello world."));
        room2.addPeople(new People("GrandMa","Ssssssh ... My husband is sleeping"));
        room2.addPeople(new People("GrandPa","RRRRRRRRRRRRrrrrrrrrrrrrzZZZZZZZZZzzzzzzzzZZZzzzzz"));
        room3.addPeople(new People("Kevin","Are you new here ?"));
        room4.addPeople(new People("Brenda","Did you see my breast ?"));
        room4.addPeople(new People("Many guys","Hey/Hello/Hi ! What do you want ? What are you doing here ? Are you a doctor ?"));
        room5.addPeople(new People("Nurse Helene","Oh ! My ! God !"));
        room5.addPeople(new People("Michael","Worst ! Day ! Ever !")); //Diarrhea man 
        room6.addPeople(new People("Proctologist","Hi there !"));
        room6.addPeople(new People("Karl")); //Dead guy of an urinary tract infection
        psychologistOffice.addPeople(new People("Psychologist","What do you see ?"));
        psychologistOffice.addPeople(new People("Tom","Please ! Please ! Please ! Don't let them touch me !!! PLEASE !")); //Crazy patient that wants to kill you
        cafetaria.addPeople(new People("Waitress Lynda","Can't you wait ?"));
        cafetaria.addPeople(new People("Ryan","So much food.")); //Hate food
        cafetaria.addPeople(new People("Abigail","So much food !")); //Like food
        kitchen.addPeople(new People("Chef Joshua","This chicken is so uncooked, I still can see it cross the road !!!"));
        kitchen.addPeople(new People("Cook Nancy","It ... Was ... Just ... A ... Chicken ..."));
        kitchen.addPeople(new People("Cook Edward","I like espelette pepper !"));
        waitingRoom.addPeople(new People("Jean-Louis","OMAGADOMAGADOMAGAD !!! I'M DYING !!!")); //The woodcutter
        waitingRoom.addPeople(new People("Jack","How many electrician do you need to change a bulb ?")); //The electrician
        waitingRoom.addPeople(new People("Victoria","'We will take care of you' they said ...")); //Bored
        staffRoom.addPeople(new People("Intern George","Sooooooo Boring ...")); //Bored intern
        staffRoom.addPeople(new People("Dr McCoy","I must study ! I must study, I must study ...")); //Studying doctor
        heatingRoom.addPeople(new People("Mecanician Henry","Hic ! Hey You ! You look like a walking corpse. Bwahahahahaaaa")); //alcoholic
        surgery.addPeople(new People("Chirurgian Albert","No time to talk."));
        surgery.addPeople(new People("Nurse Mary","What are you doing here ?"));
        surgery.addPeople(new People("John","I'm Jhonn."));
        morgue.addPeople(new People("Medical Examiner Igor","Что ты здесь делаешь")); // Medical Examiner ?
        morgue.addPeople(new People("Olivia","Staying alive, staying alive ! Ha Haa Haaa ! STAYING ALIIIIIIIIIIiiIIIIIIIIiiiiiiVE !!!")); // Patient

        //Création du tableau de Room utilisé pour la sauvegarde
        this.map = new Room[]{elevator, ground, basement, first, room1, room2, room3,
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
        GameSave save = new GameSave(new Game(getPlayer(), getMap()));
        save.serialize("default.txt");
        System.exit(0);
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
            player.getRoom().printTabPeople();
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
     *
     * @return true Pour quitter le jeu, sinon false.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord() || command.hasThirdWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Commande pour recupérer un objet
     *
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
