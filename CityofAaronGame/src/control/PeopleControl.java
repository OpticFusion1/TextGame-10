package control;
/**
 * @authors Amber Mitchell, Teresa Moser, Amy Zollinger
 */
public class PeopleControl {
    
    public static int calculateMortality(int bushelsSetAside, int currentPopulation){

// Calculate the number of people who are not fed the amount required to
// sustain life (20 bushels per person). They die. It is very sad.

    if (bushelsSetAside < 0 ){
        return -1;
    }

    if (currentPopulation < 0){ 
        return -1;
    }
    
    int numberOfPeopleFed = bushelsSetAside / 20;

    if (numberOfPeopleFed > currentPopulation){ 
            return 0; // everyone got fed
    }
    return (currentPopulation - numberOfPeopleFed);
    
}
    public static int calculateNewMoveIns(int currentPopulation) {
        int peopleMovedIn = 0;
        
        // AM: need calcuations here!!!
        
        return peopleMovedIn;
    }
}