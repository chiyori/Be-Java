package be;


/**
 * Write a description of class Lock here.
 *
 * @author Arthur PERRAD
 * @version 19/11/14
 */
public class Lock
{
    private Key correspondingKey;
    private boolean isLocked;

    /**
     * Constructeur de la serrure
     * @param rightKey Cl  qui va avec la serrure
     */
    public Lock(Key rightKey)
    {
        correspondingKey = rightKey;
        isLocked = true;
    }

/**
 * Fonction qui permet de s'avoir quelle est la bonne cl 
 * @return La cl  qui ouvre la serrure
 */
    public Key getCorrespondingKey(){
        return correspondingKey;
    }

    /**
     * Fonction qui d verrouille la porte s'il s'agit de la bonne cl 
     * @param key Cl  test  dans la serrure
     *
     */
    public void rightKey(Key key){
        if(key.equals( correspondingKey) ) isLocked = false;

    }

}
