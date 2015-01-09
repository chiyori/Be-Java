package be;

import java.util.TreeMap;

/**
 *
 * @author Arsène G.
 */
public class Dialog {

    /*Treemap du dialogue.
     Les dialogues sont représentés par un arbre de dialogues menant les uns
     aux autres.
     le string est la clef vers un dialogue définit.
     */
    private TreeMap<String, Dialog> nextDialogs;

    /*String Blabla paroles du personnage.
     Choses dites par le personnage.
     */
    private String blabla;

    /*
     Item voulu et/ou donné par le personnage lors du dialogue.
     */
    private Item itemNeed;
    private Item itemGive;

    //Boolean indiquant si le dialogue actuel Permet de tuer le personnage avec
    //acceptation
    private boolean wantToDie;

    public Dialog(String blabla) {
        this.nextDialogs = new TreeMap<>();
        this.blabla = blabla;
        this.itemGive = null;
        this.itemNeed = null;
        this.wantToDie = false;
    }

    //--------------------------------------------------------------------------
    //Methodes "get" :
    public String getBlabla() {
        return blabla;
    }

    public Item getItemGive() {
        return itemGive;
    }

    public Item getItemNeed() {
        return itemNeed;
    }

    public TreeMap<String, Dialog> getNextDialogs() {
        return nextDialogs;
    }
    
    public Boolean getWantToDie(){
        return wantToDie;
    }

    /**
     * Donne un tableau des keys des dialogues suivants
     *
     * @return String[] un tableau des prochaines keys.
     */
    public String[] getNextDialogsKeys() {
        if (nextDialogs.size() == 0) {
            return null;
        }
        int i = 0;
        String[] TabString = new String[nextDialogs.size()];
        for (String key : nextDialogs.keySet()) {
            TabString[i] = key;
            i++;
        }
        return TabString;
    }

    public boolean isWantToDie() {
        return wantToDie;
    }

    //--------------------------------------------------------------------------
    //Méthodes 'set' :
    public void setWantToDie(boolean wantToDie) {
        this.wantToDie = wantToDie;
    }

    public void setBlabla(String blabla) {
        this.blabla = blabla;
    }

    public void setItemGive(Item itemGive) {
        this.itemGive = itemGive;
    }

    public void setItemNeed(Item itemNeed) {
        this.itemNeed = itemNeed;
    }

    //--------------------------------------------------------------------------
    //Méthodes autres :
    /**
     * Ajout d'une feuille à l'arbre des dialogues
     *
     * @param key String 'clef' permettant l'accès au dialogue suivant.
     * @param paroles paroles du dialogue suivant
     */
    public void addDialog(String key, String paroles) {
        nextDialogs.put(key, new Dialog(paroles));
    }
    
        /**
     * Ajout d'une feuille à l'arbre des dialogues
     *
     * @param key String 'clef' permettant l'accès au dialogue suivant.
     * @param dialog dialogue suivant
     */
    public void addDialog(String key, Dialog dialog) {
        nextDialogs.put(key, dialog);
    }

    /**
     * @param key clef du dialogue demandé
     * @return Le dialogue demandé
     */
    public Dialog nextFromKey(String key) {
        return nextDialogs.get(key);
    }

}
