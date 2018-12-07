/*
 * New Game class. This view engages when the player chooses "N" from the Main Menu.
 */
package view;

import app.CityOfAaron;
import control.GameControl;
import exceptions.GameControlException;
import model.AnnualReport;
import model.Game;

/**
 *
 * @author Amber Mitchell
 */
public class NewGameView extends ViewBase {

    /**
     * Constructor
     */
    public NewGameView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\nLet's get to playing!\n\n";

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

        inputs[0] = getUserInput("Please enter your name, or press 'Enter' to return to the Main Menu", true);

        return inputs;
    }

    /**
     * Perform the action indicated by the user's input.
     *
     * @param inputs
     * @return true if the view should repeat itself, and false if the view
     * should exit and return to the previous view.
     */
    @Override
    public boolean doAction(String[] inputs) {
        // Act on the user's input.
        if (inputs[0] == null || inputs[0].equals("")) {
            ErrorView.display(this.getClass().getName(), "No player name entered. Returning to the Main Menu...");
            return false;
        }

        String playerName = inputs[0];
        createAndStartGame(playerName);

        //return false so we don't loop.
        return false;
    }

    private void createAndStartGame(String playerName) {

        try {
            GameControl.createNewGame(playerName);
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), "Error: Game not created.");
        }

        Game thisGame = CityOfAaron.getCurrentGame();
        AnnualReport thisReport = CityOfAaron.getCurrentReport();

        this.console.println(
                "\nWelcome to the game, " + thisGame.getThePlayer().getName() + "! \n"
                + "Year: " + thisGame.getYear() + "\n"
                + "Acres of wheat fields owned: " + thisGame.getAcresOwned() + "\n"
                + "Acres of wheat fields planted: " + thisReport.getAcresPlanted() + "\n"
                + "Bushels per acre harvested: " + thisReport.getBushelsPerAcre() + "\n"
                + "Total bushels harvested: " + thisReport.getBushelsHarvested() + "\n"
                + "Bushels paid in tithes and offerings: " + thisReport.getTithingAmount() + "\n"
                + "Bushels stolen by robbers: " + thisReport.getLostToRobbers() + "\n"
                + "Bushels of wheat in store: " + thisGame.getWheatInStorage() + "\n"
                + "People starved: " + thisReport.getPeopleStarved() + "\n"
                + "People arrived in city: " + thisReport.getPeopleMovedIn() + "\n"
                + "Current population: " + thisGame.getCurrentPopulation() + "\n"
        );

        pause(3000);
        // now head over to the Game View!
        View gameMenu = new GameMenuView();
        gameMenu.displayView();
    }
}
