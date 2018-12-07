/*
 * This model holds player details
 */
package model;
import java.io.Serializable;

/**
 *
 * @author Amber Mitchell
 */
public class Player implements Serializable {
    private String name;
    private int round;
    
    public Player(){
        // Empty constructor for bean
    }
    
    public Player(String aName, int aRound) {
        setName(aName);
        setRound(aRound);
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getRound(){
        return round;
    }
    
    public void setRound(int round){
        this.round = round;
    }
    // toString method
    @Override
    public String toString() {
        return "Player{" 
                + "name=" + name 
                + ", round=" + round
                + '}';
    }
    
}
