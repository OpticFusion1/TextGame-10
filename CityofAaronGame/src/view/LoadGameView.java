package view;

import app.CityOfAaron;
import control.GameControl;
import model.Game;

/**
 *
 * @author Amber Mitchell, Teresa Moser, Amy Zollinger
 */
public class LoadGameView extends ViewBase {

    /**
     * Constructor
     */
    public LoadGameView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "Load a saved game.\n";
    }

    /**
     * Get the set of inputs from the user.
     *
     * @return
     */
    @Override
    public String[] getInputs() {

        // Declare the array to have the number of elements you intend to get 
        // from the user.
        String[] inputs = new String[1];

        inputs[0] = getUserInput("Please enter the name of your saved game (no extension, just the name).");

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

        //if no input return to main menu
        if (inputs[0] == null || inputs[0].equals("")) {
            System.out.println("No valid game name entered. Returning to the Main Menu...");
            return false;
        }
        loadGame(inputs[0]);
        pause(3000);
        return false;
    }

    private void loadGame(String filename) {
        // AM: calling function to load saved object and cast it into a Game type object.
        boolean success = GameControl.loadGameFromFile(filename);

        if (success) {
            System.out.print("Loaded game: \n" + filename + "\n");
            // now head over to the Game View!
            View gameMenu = new GameMenuView();
            gameMenu.displayView();
        } else {
            System.out.println("No valid game name entered. Returning to the Main Menu...");

        }

    }
}
