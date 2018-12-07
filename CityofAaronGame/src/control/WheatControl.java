package control;

import model.Storehouse;
import exceptions.GameControlException;

/**
 * @author Amber Mitchell
 */
public class WheatControl {
    public static void sortStorehouse(Storehouse storehouse) {
        // work on this
//        for (int i=0; i < items.length-1; i++) {
//            
//            for (int j=i+1; j < items.length; j++) {
//                
//                // If items[i] > items[j] then swap them
//                if (items[i].getDescription().compareTo( items[j].getDescription() ) >0) {
//                    Item temp = items[i];
//                    items[i] = items[j];
//                    items[j] = temp;
//                }
//            }
//        }
    }

    public static int calcLossToRobbers(int tithesPercent, int wheatInStorage) throws GameControlException {
        // Calculate the amount of wheat in storage lost to robbers, based on
        // the percentage of tithing paid. Assume that GameControl.getRandomNumber(low, high) is available to be called.
        if (wheatInStorage < 0) {
            throw new GameControlException("Wheat in Storage cannot be less than 0");
        }
        if (tithesPercent < 0 || tithesPercent > 100) {
            throw new GameControlException("Tithes percent cannot be less than 0 or more than 100");
        }

        int low = 1;
        int high = 100;
        int chanceOfRobbers = GameControl.getRandomNumber(low, high);

        if (chanceOfRobbers >= 30) {
            return 0;
        }

        if (tithesPercent < 8) {
            low = 6;
            high = 10;
        }

        if (tithesPercent >= 8 && tithesPercent <= 12) {
            low = 3;
            high = 7;
        }
        if (tithesPercent > 12) {
            low = 3;
            high = 5;
        } 
            
        
        int percentLost = GameControl.getRandomNumber(low, high);
        int loss = wheatInStorage * percentLost / 100;
        
        return loss;

    }
    
    public static int calcHarvest (int perAcre, int acresPlanted) throws GameControlException{
        // Calculate the amount of wheat harvested, based on the percentage
        // of tithing paid. This is meant to be returned to the Annual Report harvest number
     
        if (acresPlanted < 0 || perAcre < 1) {
            throw new GameControlException("Acres planted cannot be less than 0 or per Acre cannot be less than 1");
        }
     
        int yield = (perAcre * acresPlanted);
        return yield;
    }

    public static int calcBushelsPerAcre (int tithesPercent) throws GameControlException{
        // Calculate the amount of bushels per acre to be harvested, based on the percentage
        // of tithing paid. Assume that GameControl.getRandomNumber(low, high)
        // is available to be called. This is meant to be returned to the Annual Report bushels per acre number.
     
        if (tithesPercent < 0 || tithesPercent > 100) {
            throw new GameControlException("Tithes Percent cannot be less than 0 or more than 100");
        }
        
        int low = 1;
        int high = 5;

        if (tithesPercent < 8) {
            low = 1;
            high = 3;   
        }
     
        if (tithesPercent >= 8 && tithesPercent <= 12) {
             low = 2;
             high = 4;       
        }
     
        if (tithesPercent > 12) {
            low = 3;
            high = 5;
        }
        
        int perAcre = GameControl.getRandomNumber(low, high);
        // store this number in the annual report
        return perAcre;
    }
    
}

