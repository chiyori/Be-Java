package be;


/**
 * Write a description of class MagicalExit here.
 *
 * @author Arthur PERRAD
 * @version 19/11/14
 */
public class MagicalExit extends Exit {

    final private Key key;
    final private Lock lock;

    /**
     * Constructeur des portes avec serrure
     *
     * @param currentR Nom de la salle dans laquelle on ajoute une sortie
     * @param r Room
     * @param l Lock : Serrure de la porte
     * @param k Clé qui permet d'ouvrir cette porte-ci
     */
    public MagicalExit(String currentR, Room r, Lock l, Key k) {
        super(currentR, r);
        key = k;
        lock = l;
    }

    /**
     *
     * @return Clé pour ouvrir la porte
     */
    public Key getKey() {
        return key;
    }

    /**
     *
     * @param p Joueur etant dans une salle
     * @param k Cle pour passer a la salle suivante
     * @param nextR Salle dans laquelle on veut aller
     * @return Salle dans laquelle le joueur veut aller
     */
    public Room open(Player p, Key k) {
   
            if (lock.getCorrespondingKey() == getKey()) {
                return getRoom();
            } 
            else {
               System.out.println("This is not the right key");
               // P-e a faire la gestion des exceptions ...
               return null;
            }
    }
}
