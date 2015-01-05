package be;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player implements Serializable
{
    private String name;
    private Room currentRoom;
    private ArrayList<Item> items;
    private static final int MAX_SLOT=2;

    public Player(String name, Room initialRoom)
    {
        this.name=name;
        currentRoom=initialRoom;
        items=new ArrayList<Item>();
    }

    public void setRoom(Room room)
    {
        currentRoom=room;
    }

    public Room getRoom()
    {
        return currentRoom;
    }

    public boolean checkItem (String s) {
        for (Item item : items){
            if (item.getDescription().equals(s)){
                return true;
            }
        }
        return false;
    }
    
    public Item removeItem (String s) {
        Item i;
        for (Item item : items){
            if (item.getDescription().equals(s)){
                i=item;
                items.remove(item);
                return i;
            }
        }
        return null;
    }

    public Item getItem(int index)
    {
        return items.get(index);
    }
    
    public void printItemsList (){
        System.out.println("Objects : ");
        for (Item i : items){
            System.out.println(i.getDescription());
        }
    }

    public void pickup(String stringItem)
    {
        Item item = currentRoom.removeItem(stringItem);
        if(nbSlotUse()+item.getNbSlot()<=MAX_SLOT){
            items.add(item);
        } else {
            System.out.println("You can't take it, too many item");
            currentRoom.addItem(item);
        }
    }
    
    public void putdown (String stringItem){
        currentRoom.addItem(removeItem(stringItem));
    }

    public int nbSlotUse()
    {
        int n=0;
        for(Item i : items) n+=i.getNbSlot();
        return n;
    }
}
