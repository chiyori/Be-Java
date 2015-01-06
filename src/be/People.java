package be;

import java.io.Serializable;

/**
 * @author Arsène and Sarah
 */
public class People implements Serializable {

    // instance variables
    private String name;
    private Boolean dead;
    private Dialog currentDialog;

    /**
     * Constructor for objects of class People
     *
     * @param name nom du personnage
     */
    public People(String name) {
        this.name = name;
        this.dead = false;
        this.currentDialog = null;
    }

    /**
     * Constructor for objects of class People
     *
     * @param name nom du personnage
     * @param blabla premier dialogue du personnage
     */
    public People(String name, String blabla) {
        this.name = name;
        this.dead = false;
        this.currentDialog = new Dialog(blabla);
    }

    public String getName() {
        return name;
    }

    public Dialog getCurrentDialog() {
        return currentDialog;
    }

    public Boolean isDead() {
        return dead;
    }

    public void setCurrentDialog(Dialog currentDialog) {
        this.currentDialog = currentDialog;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public void addCurrentDialog(String blabla) {
        this.currentDialog = new Dialog(blabla);
    }

    @Override
    public String toString() {
        String concat = name;
        if (isDead()) {
            concat += " is dead.";
        } else if (getCurrentDialog() != null) {
            concat += " says : " + this.currentDialog.getBlabla();
        } else {
            concat += " isn't dead, but doesn't say anything (what a jerk).";
        }
        return concat;
    }

}
