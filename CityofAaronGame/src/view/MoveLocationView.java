/*
 * MoveLocationView class. This view engages when the user selects "Move" from the Game Menu.
 */
package view;

import app.CityOfAaron;
import control.MapControl;
import exceptions.GameControlException;
import model.Location;
import model.Map;
import model.Point;

/**
 *
 * @author Amber Mitchell
 */
public class MoveLocationView extends ViewBase {

    // get the map from the model;
    Map map = CityOfAaron.getCurrentGame().getTheMap();
    Location[][] mapArray = map.getLocations();

    /**
     * Constructor
     */
    public MoveLocationView() {
        super();
    }

    @Override
    protected String getMessage() {
        // look up the current location
        Point point = map.getCurrentLocation();
        int row = point.getRow();
        int col = point.getColumn();
        Location thisLocation = mapArray[row - 1][col - 1];

        String messageMap = "You are currently at " + thisLocation.getName() + "\n\n"
                + "\nMove to a new location in the City of Aaron."
                + "\n";
        try {
            messageMap += MapControl.viewMap(mapArray);
        } catch (GameControlException e) {
            ErrorView.display(this.getClass().getName(), "Error: " + e.getMessage());
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
        inputs[0] = getUserInput("Type a row and column to move location -- format: row/col \nor type 'X' to return to the Game Menu.");

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
                this.console.println("Returning to Game Menu.");
                return false;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                try {
                    int row = Integer.parseInt(first);
                    int col = Integer.parseInt(go.substring(go.length() - 1));
                    moveLocation(row, col);
                } catch (NumberFormatException | NullPointerException e) {
                    ErrorView.display(this.getClass().getName(), "Please enter a row and column number such as: '2/4'.");
                }
                pause(3000);
                this.console.println("\nMove successful. Returning to Game Menu.");
                return false;
            default:
                this.console.println("\nPlease enter a row and column number such as: '2/4'\n or 'X' to exit to Game Menu.");
                pause(3000);
        }

        // return false if we want this view to exit and return
        // to the view that called it.
        return true;
    }

    private void moveLocation(int row, int col) {
        try {
            MapControl.moveLocation(row, col);
            // look up the location with above args
            Location newLocation = mapArray[row - 1][col - 1];
            // print to user
            this.console.println("\nYou have moved to:\n" + newLocation.getMapSymbol() + " " + newLocation.getName() + "\n"
                    + newLocation.getDescription() + "\n" + newLocation.getGameTip());
        } catch (GameControlException e) {
            ErrorView.display(this.getClass().getName(), "Error: " + e.getMessage());
        }
    }
}
