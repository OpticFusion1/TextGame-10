package model;

import java.io.Serializable;
/**
 * @author Amber Mitchell
 */
public class Location implements Serializable {
    private String name;
    private String description;
    private String mapSymbol;
    private String gameTip;
    
    public Location(){
        //Empty constructor for bean
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
            this.description = description;
    }
    public String getMapSymbol(){
        return mapSymbol;
    }
    public void setMapSymbol(String mapSymbol){
        this.mapSymbol = mapSymbol;
    }
    public String getGameTip(){
        return gameTip;
    }
    public void setGameTip(String gameTip){
        this.gameTip = gameTip;  
    }
     
    // toString method
    @Override
    public String toString(){
        return "Location:  " 
                + name + ","
                + description + ","
                + mapSymbol + ","
                + gameTip;                         
    } 
       
}
