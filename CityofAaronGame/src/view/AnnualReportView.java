/*
 * Describe class. This view engages when the player selects Display Annual Report from the Game Menu
 */
package view;
import app.CityOfAaron;
import model.AnnualReport;
import model.Game;

/**
 *
 * @author Amber Mitchell
 */
public class AnnualReportView extends ViewBase {
    
    /**
     * Constructor
     */
    public AnnualReportView(){
        super();
    }

    @Override
    protected String getMessage() {
        String report = getReport();
        return report;
    }
    
    
    /**
     * Get the set of inputs from the user.
     * @return 
     */
    @Override
    public String[] getInputs() {
        
        // Declare the array to have the number of elements we'll get 
        // from the user.
        String[] inputs = new String[1];
        
        inputs[0] = getUserInput("Press Enter to return to the Game Menu.", true);
        
        // Repeat for each input we need, putting it into its proper slot in the array.
        
        return inputs;
    }
    
    
    /**
     * Perform the action indicated by the user's input.
     * @param inputs
     * @return true if the view should repeat itself, and false if the view
     * should exit and return to the previous view.
     */
    @Override
    public boolean doAction(String[] inputs){
        // Act on the user's input.
        // This is a "dispatch" function that decides what
        // other functions to call. You can use an if-, if-else,
        // or switch statement.
        
        // return false if we want this view to exit and return
        // to the view that called it.
        return false;
    }
    
    
    private String getReport(){

        Game thisGame = CityOfAaron.getCurrentGame();
        AnnualReport thisReport = CityOfAaron.getCurrentReport();

        String thisString = 
                "\nAnnual Report for " + thisGame.getThePlayer().getName() + "! \n"
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
        ;
        
        return thisString;
    }
}
