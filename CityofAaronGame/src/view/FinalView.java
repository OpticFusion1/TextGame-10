/*
 * New Game class. This view engages when the player chooses "N" from the Main Menu.
 */
package view;

import app.CityOfAaron;
import control.GameControl;
import control.PeopleControl;
import exceptions.GameControlException;

/**
 *
 * @author Amber Mitchell
 */
public class FinalView extends ViewBase {

    int rating;
    String userMessage;

    /**
     * Constructor
     */
    public FinalView() {
        super();
        try {
            rating = PeopleControl.calculateUserRating(CityOfAaron.getCurrentGame());
            userMessage = PeopleControl.getUserMessage();
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }
    }

    @Override
    protected String getMessage() {
        String message = "\n\n";

        switch (rating) {
            case 0:
                message += "Too many of your people have starved!\n";
                break;
            case 1:
                message += "Your term is complete! You have been ranked\n"
                        + "a poor steward of the City of Aaron.\n";
                break;
            default:
                message += "Your term is complete! You have been ranked\n"
                        + "a good steward of the City of Aaron.\n";
                break;
        }

        // get the hero or villain random message and add it.
        message += userMessage;
        return message;
    }

    /**
     * Get the set of inputs from the user.
     *
     * @return
     */
    @Override
    public String[] getInputs() {

        // Declare the array to have the number of elements we get 
        // from the user.
        String[] inputs = new String[1];
        if (rating == 0) {
        }
        switch (rating) {
            case 0:
            case 1:
                inputs[0] = getUserInput("Try again and see if you can do better! Press <Enter> to return to the Main Menu.", true);
                break;
            default:
                inputs[0] = getUserInput("Would you like to continue your current game for another 10 years? Y/N", true);
                break;
        }

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
        switch (inputs[0].trim().toUpperCase()) {
            case "Y":
                continueGame();
                break;
            default:
                this.console.println("Thank you for playing. Returning to Main Menu.");
                GameControl.exit = true;
                break;
        }

        //return false so we don't loop.
        return false;
    }

    private void continueGame() {
        // advance the player's round, reset the year
        int round = CityOfAaron.getCurrentGame().getThePlayer().getRound();
        CityOfAaron.getCurrentGame().getThePlayer().setRound(round + 1);
        CityOfAaron.getCurrentGame().setYear(1);
    }
}
