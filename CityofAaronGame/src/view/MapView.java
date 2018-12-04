/*
 * MapView class. This view engages when the user selects "View Map" from the Game Menu.
 */
package view;

import app.CityOfAaron;
import control.MapControl;
import exceptions.MapControlException;
import model.Location;

/**
 *
 * @authors Amber Mitchell, Teresa Moser, Amy Zollinger
 */
public class MapView extends ViewBase {

    // get the map from the model;
    Location[][] mapArray = CityOfAaron.getCurrentGame().getTheMap().getLocations();

    /**
     * Constructor
     */
    public MapView() {
        super();
    }

    @Override
    protected String getMessage() {
        String messageMap = "\nThe City of Aaron is made up of villages, grasslands,"
                + "\nwheat fields, a river, and other beautiful and functional areas."
                + "\n";
        try {
            messageMap += MapControl.viewMap(mapArray);
        } catch (MapControlException mce) {
            ErrorView.display(this.getClass().getName(), "Error: " + mce.getMessage());
        }
        return messageMap;
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

        inputs[0] = getUserInput("Would you like to view a particular location? \nChoose row and column -- format: row/col \nor type 'X' to return to the Game Menu.");

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
        // This is a "dispatch" function that decides what
        // other functions to call. You can use an if-, if-else,
        // or switch statement.
        String go = inputs[0].trim().toUpperCase();
        String first = go.substring(0, 1);

        switch (first) {
            case "X":
                System.out.println("Returning to Game Menu.");
                return false;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                try {
                    int row = Integer.parseInt(first);
                    int col = Integer.parseInt(go.substring(go.length() - 1));
                    viewLocation(row, col);
                } catch (NumberFormatException | NullPointerException e) {
                    ErrorView.display(this.getClass().getName(), "Please enter a row and column number such as: '2/4'.");
                }
                break;
            default:
                this.console.println("\nPlease enter a row and column number such as: '2/4'\n or 'X' to exit to Game Menu.");
        }

        // return false if we want this view to exit and return
        // to the view that called it.
        pause(3000);
        return true;
    }

    private void viewLocation(int row, int col) {
        // look up the location with above args
        Location thisLocation = mapArray[row - 1][col - 1];
        // print to user
        this.console.println(thisLocation.getMapSymbol() + " " + thisLocation.getName() + "\n"
                + thisLocation.getDescription() + "\n" + thisLocation.getGameTip());
    }
}
