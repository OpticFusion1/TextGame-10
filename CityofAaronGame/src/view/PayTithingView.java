/*
 * Describe class. This view engages when...
//Ask the user what percentage of their harvest they want to pay in tithes and offerings.
//Get the user’s input.
//Check to make sure that the value entered by the user is positive. If it is not, show a message and ask the user to enter a new value.
//Check to make sure that the value entered by the user is not greater than 100. If it is, display a message and ask the user to enter a new value.
//Save the value entered by the user in Crops.setTithingPercentToPay()

 */
package view;

import model.Crops;

/**
 *
 * @author Amber Mitchell
 */
public class PayTithingView extends ViewBase {

    /**
     * Constructor
     */
    public PayTithingView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\nPay Tithes and Offerings\n"
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

        inputs[0] = getUserInput("What percentage would you like to \n"
                + "pay for tithes and offerings this year?");

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
            int tithingPercentToPay = Integer.parseInt(inputs[0].trim());
            if (tithingPercentToPay < 0 || tithingPercentToPay > 100) {
                ErrorView.display(this.getClass().getName(), "Not a valid number. Returning to Manage Crops Menu.");
            } else {
                payTithing(tithingPercentToPay);
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

    private void payTithing(int tithingPercentToPay) {
        Crops.setTithingPercentToPay(tithingPercentToPay);
        this.console.println("Success! You will pay " + tithingPercentToPay + "% in tithes when you live the year.");
    }
}
