package view;

import app.CityOfAaron;
import control.GameControl;
import exceptions.GameControlException;
import model.AnnualReport;
import model.Game;

/**
 *
 *
 * @author Amber Mitchell
 */
public class GameMenuView extends ViewBase {

    /**
     * Constructor
     *
     */
    public GameMenuView() {
        super();
    }

    @Override
    protected String getMessage() {
        if (GameControl.exit) {
            return "";
        }

        return "\n\n--------------------\n"
                + "Game Menu Options\n"
                + "--------------------\n"
                + "1 - Show annual report\n"
                + "2 - View map\n"
                + "3 - Move location\n"
                + "4 - Manage crops\n"
                + "5 - Live year\n"
                + "6 - View storehouse\n"
                + "7 - Save game\n"
                + "X - Exit to Main Menu\n";
    }

    /**
     * Get the set of inputs from the user.
     *
     * @return
     */
    @Override
    public String[] getInputs() {

        String[] inputs = new String[1];
        if (GameControl.exit) {
            inputs[0] = "";
        } else {
            inputs[0] = getUserInput("Which game menu option would you like?");
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
        try {
            if (GameControl.exit) {
                return false;
            }
            
            // Act on the user's input.
            switch (inputs[0].trim().toUpperCase()) {
                
                case "1":
                    displayAnnualReport();
                    break;
                case "2":
                    viewMap();
                    break;
                case "3":
                    moveLocation();
                    break;
                case "4":
                    manageCrops();
                    break;
                case "5":
                    liveYear();
                    break;
                case "6":
                    viewStorehouse();
                    break;
                case "7":
                    saveGame();
                    break;
                case "X":
                    this.console.println("Returning to Main Menu.");
                    return false;
            }
            
            return true;
        } catch (Exception ex) {
            ErrorView.display(this.getClass().getName(), "Returning to Manage Crops Menu.");
        }
        return false;
    }

    private void displayAnnualReport() {
        View report = new AnnualReportView();
        report.displayView();
    }

    private void viewMap() {
        View view = new MapView();
        view.displayView();
    }

    private void moveLocation() {
        View view = new MoveLocationView();
        view.displayView();
    }

    private void manageCrops() {
        View crops = new ManageCropsView();
        crops.displayView();
    }

    private void liveYear() {
        try {
            Game oldGame = CityOfAaron.getCurrentGame();

            // live the year
            GameControl.liveTheYear(oldGame);

            // get new report and game
            Game thisGame = CityOfAaron.getCurrentGame();
            AnnualReport thisReport = CityOfAaron.getCurrentReport();

            // this may be a good place to check GameControl.gameShouldEnd();
            if (GameControl.gameShouldEnd(thisGame)) {
                View end = new FinalView();
                end.displayView();
                return;
            }
            // now display the report
            this.console.println(
                    "\nAnnual Report for: " + thisGame.getThePlayer().getName() + "! \n"
                    + "Term: " + thisGame.getThePlayer().getRound() + "\n"
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

            // now head over to the final view if we've hit a new decade of play
            if (thisGame.getYear() == 11) {
                View end = new FinalView();
                end.displayView();
                return;
            }

            pause(3000);
        } catch (GameControlException e) {
            ErrorView.display(this.getClass().getName(), e.getMessage());
        }
    }

    private void viewStorehouse() {
        View storehouse = new StorehouseView();
        storehouse.displayView();
    }

    private void saveGame() {
        View save = new SaveGameView();
        save.displayView();
    }

}
