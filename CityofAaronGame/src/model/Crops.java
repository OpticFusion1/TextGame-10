/*
 * Let's store all the intra-year bits of data here.
 */
package model;

import control.GameControl;
import exceptions.GameControlException;
import java.io.Serializable;

/**
 *
 * @author metauser
 */
public class Crops implements Serializable {

    private static int upcomingLandPrice = 0;
    private static int landToBuy = 0;
    private static int landToSell = 0;
    private static int bushelsToFeedPeople = 0;
    private static int wheatToPlant = 0; // this is how many bushels of wheat we'll use as seed
    private static int acresToPlant = 0; // this is how many acres we actually want to plant
    private static int tithingPercentToPay = 0;

    public Crops() {
        // Empty constructor for bean
    }

    public static int getUpcomingLandPrice() {
        return upcomingLandPrice;
    }

    public static void setUpcomingLandPrice() throws GameControlException {
        int price = GameControl.getRandomNumber(17, 27);
        upcomingLandPrice = price;
    }

    public static int getLandToBuy() {
        return landToBuy;
    }

    public static void setLandToBuy(int landToBuy) {
        Crops.landToBuy = landToBuy;
    }

    public static int getLandToSell() {
        return landToSell;
    }

    public static void setLandToSell(int landToSell) {
        Crops.landToSell = landToSell;
    }

    public static int getBushelsToFeedPeople() {
        return bushelsToFeedPeople;
    }

    public static void setBushelsToFeedPeople(int bushelsToFeedPeople) {
        Crops.bushelsToFeedPeople = bushelsToFeedPeople;
    }

    public static int getWheatToPlant() {
        return wheatToPlant;
    }

    public static void setWheatToPlant(int wheatToPlant) {
        Crops.wheatToPlant = wheatToPlant;
    }

    public static int getAcresToPlant() {
        return acresToPlant;
    }

    public static void setAcresToPlant(int acresToPlant) {
        Crops.acresToPlant = acresToPlant;
    }

    public static int getTithingPercentToPay() {
        return tithingPercentToPay;
    }

    public static void setTithingPercentToPay(int tithingPercentToPay) {
        Crops.tithingPercentToPay = tithingPercentToPay;
    }
    
}
