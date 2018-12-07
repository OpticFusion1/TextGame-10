/*
 * Describe class. This view engages when...
//Generate a random number between 17 and 27 for the price of an acre of land. Display the price to the user.
//Ask the user "How many acres of land do you want to sell?"
//The user enters a value.
//Check to make sure the value is positive. If not, show a message and ask the user to enter the value again.
//Make sure that the player has enough acres of land to sell. If not, show a message and ask the user to enter the value again.
// save sell land number to Crops.setLandToSell()
// DURING LIVE THE YEAR >>>
//Subtract the number of acres sold from the acres owned.
//Add the bushels of wheat that was increased by the selling of land to the bushels of wheat in storage.

 */
package view;

import app.CityOfAaron;
import model.Crops;

/**
 *
 * @author Amber Mitchell
 */
public class SellLandView extends ViewBase {

    int currentPrice = Crops.getUpcomingLandPrice();

    /**
     * Constructor
     */
    public SellLandView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\nSell Land\n"
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

        inputs[0] = getUserInput("How many acres of land would you like to sell?");

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
            int acresToSell = Integer.parseInt(inputs[0].trim());
            int land = CityOfAaron.getCurrentGame().getAcresOwned();
            if (acresToSell < 0) {
                ErrorView.display(this.getClass().getName(), "Not a valid number. Returning to Manage Crops Menu.");
            } else if (acresToSell > land) {
                ErrorView.display(this.getClass().getName(), "You don't have enough land to sell that much!\n"
                        + "You have " + land + " acres of land.\n"
                        + "Please enter a valid number of acres to sell."
                );
                return true;
            } else {
                sellLand(acresToSell);
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

    private void sellLand(int acresToSell) {
        Crops.setLandToSell(acresToSell);
        int income = acresToSell * currentPrice;
        this.console.println("Success! You will sell " + acresToSell + " from your acreage when you live the year.\n"
                + "This will profit you " + income + " bushels of wheat."
        );
    }
}
