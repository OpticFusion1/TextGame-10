/*
 * Manage Crops View 
 */
package view;

import model.Crops;

/**
 *
 * @author Amber Mitchell
 */
public class ManageCropsView extends ViewBase {
    int buy = 0;
    int sell = 0;
    int crops = 0;
    int feed = 0;
    int tithes = 0;
    /**
     * Constructor
     */
    public ManageCropsView(){
        super();
    }
    @Override
    protected String getMessage() {
        buy = Crops.getLandToBuy();
        sell = Crops.getLandToSell();
        feed = Crops.getBushelsToFeedPeople();
        crops = Crops.getAcresToPlant();
        tithes = Crops.getTithingPercentToPay();
        return "\n\n--------------------\n"
                + "Manage Crops\n"
                + "--------------------\n"
                + "1 - Buy Land: " + buy + " acres\n"
                + "2 - Sell Land: " + sell + " acres \n"
                + "3 - Feed your People: " + feed + " bushels of wheat\n"
                + "4 - Plant Crops: " + crops + " acres\n"
                + "5 - Pay Tithes and Offerings: " + tithes + "%\n"
                + "X - Return to the Game Menu \n";               
        
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

        inputs[0] = getUserInput("This is where you plan all your actions for the year.\n"
                + "You may keep adjusting these numbers as you see fit until you are ready to\n"
                + "return to the Game Menu and 'Live the Year'. Be sure your calculations are correct!\n\n"
                + "Which option do you choose?");

        // Repeat for each input you need, putting it into its proper slot in the array.
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

        // return false if you want this view to exit and return
        // to the view that called it.
        switch (inputs[0].trim().toUpperCase()) {
            case "1":
                buyLand();
                break;
            case "2":
                sellLand();
                break;
            case "3":
                feedPeople();
                break;
            case "4":
                plantCrops();
                break;
            case "5":
                payTithes();
                break;
            case "X":
                return false;
        }
        return true;
    }
  
    private void buyLand(){
       View view = new BuyLandView();
       view.displayView();
    }
    
    private void sellLand(){
       View view = new SellLandView();
       view.displayView();
    }
    
    private void feedPeople(){
       View view = new FeedPeopleView();
       view.displayView();
    }
    
    private void plantCrops(){
       View view= new PlantCropsView();
       view.displayView();
    }
    
    private void payTithes(){
       View view= new PayTithingView();
       view.displayView();
    }   
    
}
