import java.util.ArrayList;


/**
 * Write a description of class People here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class People
{
    // instance variables - replace the example below with your own
    private String name;
    private Boolean dead;
    private ArrayList<Item> itemsNeeded;
    private ArrayList<Item> itemsTheyHave;

    /**
     * Constructor for objects of class People
     * @param name 
     */
    public People(String name)
    {
        this.name=name;
        dead=false;
        itemsNeeded = new ArrayList<>();
        itemsTheyHave = new ArrayList<>();
    }
    
    public People(String name, ArrayList<Item> ain, ArrayList<Item> aith){
        this.name=name;
        dead=false;
        itemsNeeded = ain;
        itemsTheyHave = aith;
    }

    public String getName (){
        return name;
    }
    
    public Boolean isDead (){
        return dead;
    }
    
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
    
    public void addItemNeeded(Item i) {
        itemsNeeded.add(i);
    }
    
    public void addItemTheyHave(Item i){
        itemsTheyHave.add(i);
    }
    
    public void printItemsNeeded(){
        System.out.println("Objet demandé : ");
        for(Item i : itemsNeeded){
            System.out.println(i);
        }
    }
    
    public boolean NeedGivenItem(Item i){
        if (itemsNeeded.contains(i)){
            return true;
        }
        return false;
    }
}
