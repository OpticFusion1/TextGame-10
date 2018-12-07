package view;

import app.CityOfAaron;
import control.GameControl;
import java.io.IOException;
import model.AnnualReport;
import model.Game;



/**
 *
 * @author Amber Mitchell
 */
public class SaveGameView extends ViewBase {

    /**
     * Constructor
     */
    public SaveGameView() {
        super();
    }
        @Override
    protected String getMessage() {
        return "Save your current game.\n";
    }

       /**
     * Get the set of inputs from the user.
     * @return
     */
    @Override
    public String[] getInputs() {

        // Declare the array to have the number of elements you intend to get 
        // from the user.
        String[] inputs = new String[1];

        inputs[0] = getUserInput("Please enter a name for your game.");

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
            ErrorView.display(this.getClass().getName(), "No game name entered. Returning to the Main Menu...");
            return false;
        }
        saveGame(inputs[0]);
        pause(3000);
        return false;
        
    }

    private void saveGame(String filename) {
        try {
            // AM: need to actually call function to save game data to file.
            Game game = CityOfAaron.getCurrentGame();
            AnnualReport report = CityOfAaron.getCurrentReport();
            GameControl.saveGameToFile(filename, game, report);
            
            // AM: print success to user.
            this.console.print("Game saved: " + filename + "\n");
        } catch (IOException e) {
            ErrorView.display(this.getClass().getName(), "No valid game name entered. Returning to the Main Menu...");
        }
        
    }
}
