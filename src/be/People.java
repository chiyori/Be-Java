package be;

import java.util.ArrayList;
import java.io.Serializable;



/**
 * Write a description of class People here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class People implements Serializable
{
    // instance variables - replace the example below with your own
    private String name;
    private Boolean dead;
    private Dialog currentDialog;

    /**
     * Constructor for objects of class People
     * @param name 
     */
    public People(String name, Dialog d)
    {
        this.name=name;
        dead=false;
        currentDialog=d;
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
        return concat;
    }
}
