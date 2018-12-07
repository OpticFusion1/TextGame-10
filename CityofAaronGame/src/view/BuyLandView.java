/*
 * Describe class. This view engages when...
//Generate a random number between 17 and 27 for the price of an acre of land. Display the price to the user.
//Ask the user "How many acres of new land do you want buy?"
//The user enters a value
//Check to make sure the value is positive. If not, show a message and ask the user to enter the value again.
//Make sure that the player has enough wheat to make the purchase. If not, show a message and ask the user to enter the value again.
// save buy land number to Crops.setLandToBuy()
// DURING LIVE THE YEAR >>>
//Add the number of acres purchased to the acres owned
//Subtract the wheat used to purchase the land from the wheat in storage
 */
package view;

import app.CityOfAaron;
import model.Crops;

/**
 *
 * @author Amber Mitchell
 */
public class BuyLandView extends ViewBase {

    int currentPrice = Crops.getUpcomingLandPrice();

    /**
     * Constructor
     */
    public BuyLandView() {
        super();
    }

    @Override
    protected String getMessage() {

        return "\n\nBuy Land\n"
                + "Current Price per Acre: $" + currentPrice + "\n";
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

        inputs[0] = getUserInput("How many acres of land would you like to buy?");

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
        try {
            int acresToBuy = Integer.parseInt(inputs[0].trim());
            int cost = acresToBuy * currentPrice;
            int wheat = CityOfAaron.getCurrentGame().getWheatInStorage();
            if (acresToBuy < 0) {
                ErrorView.display(this.getClass().getName(),"Not a valid number. Returning to Manage Crops Menu.");
                //System.out.println("Not a valid number. Returning to Manage Crops Menu.");
            } else if (cost > wheat) {
                ErrorView.display(this.getClass().getName(), "You don't have enough wheat to buy that much land!\n"
                        + "You have " + wheat + " in storage.\n"
                        + "Please enter a valid number of acres to buy at the current price of $" + currentPrice + " per acre."
                );
                return true;
            } else {
                buyLand(acresToBuy);
            }
        } catch (NumberFormatException | NullPointerException e) {
            ErrorView.display(this.getClass().getName(), "Not a valid number. Returning to Manage Crops Menu.");
        }
        // 

        // return false if we want this view to exit and return
        // to the view that called it.
        pause(3000);
        return false;
    }

    private void buyLand(int acresToBuy) {
        Crops.setLandToBuy(acresToBuy);
        this.console.println("Success! You will add " + acresToBuy + " to your acreage when you live the year.");
    }
}
