/*
 * New Game class. This view engages when the player chooses "N" from the Main Menu.
 */
package view;

import java.util.Scanner;
import app.CityOfAaron;
import control.GameControl;
import control.LandControl;
import model.AnnualReport;
import model.Player;
import model.Game;

/**
 *
 * @authors Amber Mitchell, Teresa Moser, Amy Zollinger
 */
public class NewGameView {

    /**
     * The message that will be displayed by this view.
     */
    protected String message;

    /**
     * Constructor
     */
    public NewGameView() {

        message = "\n\nLet's get to playing!\n\n";

    }

    /**
     * Get the user's input. Keep prompting them until they enter a value.
     *
     * @param prompt
     * @param allowEmpty - determine whether the user can enter no value (just a return key)
     * @return
     */
    protected String getUserInput(String prompt, boolean allowEmpty) {

        Scanner keyboard = new Scanner(System.in);
        String input = "";
        boolean inputReceived = false;

        while (inputReceived == false) {

            System.out.println(prompt);
            input = keyboard.nextLine();

            // Make sure we avoid a null-pointer error.
            if (input == null) {
                input = "";
            }

            // Trim any trailing whitespace, including the carriage return.
            input = input.trim();

            if (input.equals("") == false || allowEmpty == true) {
                inputReceived = true;
            }
        }

        return input;
    }

    /**
     * An overloaded version of getUserInput that sets allowEmpty to false so we don't have to type it ourselves.
     *
     * @param prompt
     * @return
     */
    protected String getUserInput(String prompt) {
        return getUserInput(prompt, false);
    }

    /**
     * Get the set of inputs from the user.
     *
     * @return
     */
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
     * @return true if the view should repeat itself, and false if the view should exit and return to the previous view.
     */
    public boolean doAction(String[] inputs) {
        // Act on the user's input.
        if (inputs[0] == null || inputs[0].equals("")) {
            System.out.println("No player name entered. Returning to the Main Menu...");
            return false;
        }

        String playerName = inputs[0];
        createAndStartGame(playerName);

        //return false so we don't loop.
        return false;
    }

    /**
     * Control this view's display/prompt/action loop until the user chooses an action that causes this view to close.
     */
    public void displayView() {

        boolean keepGoing = true;

        while (keepGoing == true) {

            System.out.println(message);
            String[] inputs = getInputs();
            keepGoing = doAction(inputs);
        }
    }

    // Define action handlers here. These are the methods that doAction()
    // method will call based on the user's input. We don't want to do a lot of 
    // complex game stuff in our doAction() method. It will get messy very quickly.
    private void createAndStartGame(String playerName) {

        GameControl.createNewGame(playerName);

        Game thisGame = CityOfAaron.getCurrentGame();
        AnnualReport thisReport = CityOfAaron.getCurrentReport();

        System.out.println("\nWelcome to the game, " + thisGame.getThePlayer().getName() + "! \n");
        System.out.println("Year: " + thisGame.getYear() + "\n");
        System.out.println("Acres of wheat fields: " + thisReport.getEndingAcresOwned() + "\n");
        System.out.println("Bushels per acre harvested: " + thisReport.getBushelsPerAcre() + "\n");
        System.out.println("Total bushels harvested: " + thisReport.getBushelsHarvested() + "\n");
        System.out.println("Bushels paid in tithes and offerings: " + (thisReport.getTithingAmount() * thisReport.getBushelsHarvested())/100 + "\n");
        System.out.println("Bushels stolen by robbers: " + thisReport.getLostToRobbers() + "\n");
        System.out.println("Bushels of wheat in store: " + thisReport.getEndingWheatInStorage() + "\n");
        System.out.println("People starved: " + thisReport.getPeopleStarved() + "\n");
        System.out.println("People arrived in city: " + thisReport.getPeopleMovedIn() + "\n");
        System.out.println("Current population: " + thisReport.getEndingPopulation() + "\n");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        // now head over to the Game View!
        GameMenuView gameMenu = new GameMenuView();
        gameMenu.displayView();
    }
}