/*
 * Describe class. This view engages when...
//Ask the user "How many bushels of grain do you want to give to the people?"
//User enters a value.
//Check to make sure that the value is positive. If it is not, show a message and ask the user to enter the value again.
//Make sure that the city has this much wheat in storage. If not, show a message and ask the user to enter the value again.
// save the number to Crops.setBushelsToFeedPeople()
// DURING LIVE THE YEAR >>>
//Subtract this amount from the wheat in storage. Display the amount of wheat you have left.
//Update the game state to save how many bushels of wheat you have set aside to feed the people.

 */
package view;

import app.CityOfAaron;
import model.Crops;

/**
 *
 * @author Amber Mitchell
 */
public class FeedPeopleView extends ViewBase {

    /**
     * Constructor
     */
    public FeedPeopleView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\nFeed your People\n"
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

        inputs[0] = getUserInput("How many bushels of wheat would you like to \n"
                + "set aside to feed your people this year?");

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
            int bushelsToFeedPeople = Integer.parseInt(inputs[0].trim());
            int wheat = CityOfAaron.getCurrentGame().getWheatInStorage();
            
            if (bushelsToFeedPeople < 0) {
                ErrorView.display(this.getClass().getName(), "Not a valid number. Returning to Manage Crops Menu.");
            } else if (bushelsToFeedPeople > wheat) {
                ErrorView.display(this.getClass().getName(), "You don't have enough wheat to feed your people that much!\n"
                        + "You have " + wheat + " bushels in storage.\n"
                        + "Please enter a valid number of bushels."
                );
                return true;
            } else {
                feedPeople(bushelsToFeedPeople);
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

    private void feedPeople(int bushelsToFeedPeople) {
        Crops.setBushelsToFeedPeople(bushelsToFeedPeople);
        this.console.println("Success! You will feed your people " + bushelsToFeedPeople + " bushels when you live the year.");
    }
}
