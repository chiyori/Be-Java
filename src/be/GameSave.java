package be;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * A GameSave object allows to capture the state of a game. We can save and load
 * the game
 *
 * @author Arthur PERRAD
 *
 */
public class GameSave implements Serializable {

    private Game game; // contains Player and array of Room

    public GameSave() {
        this(null);
    }

    public GameSave(Game g) {

        game = g;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean serialize(String path) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(new File(path)));
            out.writeObject(getGame());
            out.close();
  //          System.out.println("OK");

            return true;
        } catch (FileNotFoundException e) {
  //          System.out.println("NOT OK");
            return false;
        } catch (IOException e) {
  //          System.out.println("NOT OK");

            return false;
        }
    }

    public boolean loadFromSerial(String path) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    new File(path)));
            Object object = in.readObject();
            in.close();
            if (object instanceof Game) {
                Game previousGame = (Game) object;
                setGame(previousGame);
                return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }

    }

}
