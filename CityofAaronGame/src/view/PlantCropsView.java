/*
 * Describe class. This view engages when...
//Ask the user "How many acres of land do you want to plant?"
//User enters a value.
//Check to make sure that the value is positive. If it is not, show a message and ask the user to enter the value again.
//Check to make sure the city has this much land. If not, show a message and ask the user to enter a new value.
//Make sure that the city has enough wheat in storage to plant this many acres (You can plant 2 acres with one bushel of wheat). If not, show a message and ask the user to enter the value again.
//Make sure that the city has enough people to tend the land. One person can take care of 10 acres. If there are not enough people, show a message and ask the user to enter a different value. ~~Note: this requirement has been moved from the "Buy Land" menu.
//Calculate the number of bushels required to plant the crops.
// save acres number to Crops.setAcresToPlant()
// save wheat bushels as seed number to Crops.setWheatToPlant()
// DURING LIVE THE YEAR >>>
//Subtract this amount from the wheat in storage. Display the amount of wheat you have left.
//Update game state to save how many acres have been planted.

 */
package view;

import app.CityOfAaron;
import model.Crops;

/**
 *
 * @author Amber Mitchell
 */
public class PlantCropsView extends ViewBase {

    /**
     * Constructor
     */
    public PlantCropsView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\nPlant Crops\n"
                + "\n";
    }

    /**
     * Get the set of inputs from the user.
     *
     * @return
     */
    @Override
    public String[] getInputs() {

        // Declare the array to have the number of elements we'll get 
        // from the user.
        String[] inputs = new String[1];

        inputs[0] = getUserInput("How many acres of your land would you like to \n"
                + "plant this year?");

        // Repeat for each input we need, putting it into its proper slot in the array.
        return inputs;
    }

    /**
     * Perform the action indicated by the user's input.
     *
     * @param inputs
     * @return true if the view should repeat itself, and false if the view should exit and return to the previous view.
     */
    @Override
    public boolean doAction(String[] inputs) {
        // Act on the user's input.
        // Validate input, use Try/Catch
        // AM: remember that we have to have that many acres, and we have to have the people to work it
        try {
            int acresToPlant = Integer.parseInt(inputs[0].trim());
            int land = CityOfAaron.getCurrentGame().getAcresOwned();
            int wheat = CityOfAaron.getCurrentGame().getWheatInStorage();
            int people = CityOfAaron.getCurrentGame().getCurrentPopulation();
            // AM: trying to decide if people can plant the acres they just bought (same year) or not...
            // .... and if so, must also NOT let them plant acres they just sold (same year)
            //int acresToBuyNext = Crops.getLandToBuy();
            //int totalLand = land + acresToBuyNext;

            if (acresToPlant < 0) {
                System.out.println("Not a valid number. Returning to Manage Crops Menu.");
            } else if (acresToPlant > land) {
                ErrorView.display(this.getClass().getName(), "You don't own that much land!\n"
                        + "You have " + land + " acres of wheat fields.\n"
                        + "Please enter a valid number of acres to plant."
                );
                return true;
            } else if ((acresToPlant/2) > wheat) {
                ErrorView.display(this.getClass().getName(), "You don't have enough wheat in storage to plant that much acreage!\n"
                        + "It takes a bushel of wheat to plant two acres.\n"
                        + "Please enter a valid number of acres to plant."
                );
                return true;
            } else if ((acresToPlant/10) > people) {
                ErrorView.display(this.getClass().getName(), "You don't have enough people to tend that much land!\n"
                        + "One person can tend 10 acres of wheat and you have " + people + " people.\n"
                        + "Please enter a valid number of acres to plant."
                );
                return true;
            } else {
                plantCrops(acresToPlant);
            }
        } catch (NumberFormatException | NullPointerException e) {
            ErrorView.display(this.getClass().getName(), "Not a valid number. Returning to Manage Crops Menu.");
        }

        // return false if we want this view to exit and return
        // to the view that called it.
        pause(3000);
        return false;
    }

    private void plantCrops(int acresToPlant) {
        Crops.setAcresToPlant(acresToPlant);
        Crops.setWheatToPlant(acresToPlant/2);
        this.console.println("Success! You will plant " + acresToPlant + " acres when you live the year.");
    }
}
