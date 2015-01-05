package be;

import java.io.Serializable;

/**
 * Class designed to represent an item
 * @author
 * @version
 */
public class Item implements Serializable
{

    private String itemDescription;
    private int itemNbSlot;    

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int nbSlot)
    {
        itemDescription=description;
        itemNbSlot=nbSlot;
    }

    public String getDescription()
    {
        return itemDescription;
    }

    public int getNbSlot()
    {
        return itemNbSlot;
    }

        public boolean equals(Item item){
        return this.getDescription().equals(item.getDescription());
    }
}
