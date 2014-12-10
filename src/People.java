import java.util.ArrayList;


/**
 * Class for the People we can find in the game
 * 
 * @author Sarah Santal
 * @version 1
 */
public class People
{
    // instance variables - replace the example below with your own
    private String name;
    private Boolean dead;
    private ArrayList<Item> itemsNeeded;
    private ArrayList<Item> itemsTheyHave;

    /**
     * Constructor without any items
     * @param name 
     */
    public People(String name)
    {
        this.name=name;
        dead=false;
        itemsNeeded = new ArrayList<>();
        itemsTheyHave = new ArrayList<>();
    }
    
    /**
     * Contructor with items needed or items had
     * @param name
     * @param ain arrayList of items needed
     * @param aith arrayList of items they have
     */
    
    public People(String name, ArrayList<Item> ain, ArrayList<Item> aith){
        this.name=name;
        dead=false;
        itemsNeeded = ain;
        itemsTheyHave = aith;
    }
    
    /**
     * 
     * @return name of the person
     */
    public String getName (){
        return name;
    }
    
    /**
     * 
     * @return boolean to know if the character is dead
     */
    public Boolean isDead (){
        return dead;
    }
    
    /**
     * ToString
     * @return string of the description of a character
     */
    @Override
    public String toString () {
        String concat=name+" (";
        if (isDead())
            concat+="dead)";
        else concat+="alive)";
        for(Item i : itemsNeeded){
            concat+="\n";
            concat+=i;
        }
        return concat;
    }
    
    /**
     * Add an item in the arrayList ItemNeeded
     * @param i item to put in the list
     */
    public void addItemNeeded(Item i) {
        itemsNeeded.add(i);
    }
    
    /**
     * Add an item in the arrayList ItemTheyHave
     * @param i item to put in the List
     */
    public void addItemTheyHave(Item i){
        itemsTheyHave.add(i);
    }
    
    /**
     * display items needed by the character
     */
    public void printItemsNeeded(){
        System.out.println("Item needed : ");
        for(Item i : itemsNeeded){
            System.out.println(i);
        }
    }
    
    /**
     * Items needed by the character ?
     * @param i item we want give to the character
     * @return Boolean to know if he want it
     */
    public boolean NeedGivenItem(Item i){
        return itemsNeeded.contains(i);
    }
}
