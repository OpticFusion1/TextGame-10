/*
 * Awesome game model here
 */
package model;

import java.io.Serializable;

/**
 * @author Amber Mitchell
 */
public class Game implements Serializable {

    private Player thePlayer;
    private Map theMap;
    private Storehouse theStorehouse;
    private int currentPopulation;
    private int acresOwned;
    private int wheatInStorage;
    private int yearPlaying;

    public Game() {
        // Empty constructor for bean
    }

    // this one is for loading a saved game
    public Game(Game game) {
        setThePlayer(game.getThePlayer());
        setTheMap(game.getTheMap());
        setTheStorehouse(game.getTheStorehouse());
        setCurrentPopulation(game.getCurrentPopulation());
        setAcresOwned(game.getAcresOwned());
        setWheatInStorage(game.getWheatInStorage());
        setYear(game.getYear());
    }

    // this one is for initializing a new game
    public Game(Player aPlayer, Map aMap, Storehouse aStorehouse, int aCurrentPopulation, int aAcresOwned, int aWheatInStorage, int aYearPlaying) {
        setThePlayer(aPlayer);
        setTheMap(aMap);
        setTheStorehouse(aStorehouse);
        setCurrentPopulation(aCurrentPopulation);
        setAcresOwned(aAcresOwned);
        setWheatInStorage(aWheatInStorage);
        setYear(aYearPlaying);
    }

    public Player getThePlayer() {
        return thePlayer;
    }

    public void setThePlayer(Player thePlayer) {
        this.thePlayer = thePlayer;
    }

    public Map getTheMap() {
        return theMap;
    }

    public void setTheMap(Map theMap) {
        this.theMap = theMap;
    }

    public Storehouse getTheStorehouse() {
        return theStorehouse;
    }

    public void setTheStorehouse(Storehouse theStorehouse) {
        this.theStorehouse = theStorehouse;
    }

    public int getCurrentPopulation() {
        return currentPopulation;
    }

    public void setCurrentPopulation(int currentPopulation) {
        this.currentPopulation = currentPopulation;
    }

    public int getAcresOwned() {
        return acresOwned;
    }

    public void setAcresOwned(int acresOwned) {
        this.acresOwned = acresOwned;
    }

    public int getWheatInStorage() {
        return wheatInStorage;
    }

    public void setWheatInStorage(int wheatInStorage) {
        this.wheatInStorage = wheatInStorage;
    }

    public int getYear() {
        return yearPlaying;
    }

    public void setYear(int yearPlaying) {
        this.yearPlaying = yearPlaying;
    }

    // toString method
    @Override
    public String toString() {
        return "Game{\n"
                + "The Player: " + thePlayer
                + "\nThe Map: " + theMap
                + "\nThe Storehouse: " + theStorehouse
                + "\nCurrent Population: " + currentPopulation
                + "\nAcres Owned: " + acresOwned
                + "\nWheat in Storage: " + wheatInStorage
                + "\n}";
    }

}

/// AM: note using Lombok for building models:
// import lombok.*
//
//@Data
//@Builder
//public class Game {
//    private Player thePlayer;
//    private Storehouse theStorehouse;
//    private Map theMap;
//    
//    private int currentPopulation;
//    private int acresOwned;
//    private int wheatInStorage;
//}
