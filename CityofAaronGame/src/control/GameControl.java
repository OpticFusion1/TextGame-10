package control;

import app.CityOfAaron;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import exceptions.GameControlException;
import model.Animal;
import model.AnnualReport;
import model.Condition;
import model.Game;
import model.InventoryItem;
import model.ItemType;
import model.Map;
import model.Player;
import model.Provision;
import model.Storehouse;
import model.Author;
import model.Crops;

/**
 * @author Amber Mitchell
 */
public class GameControl {

    private static Random randomGenerator = new Random();
    private static String filepath = "";
    public static boolean exit = false;

    public static Game createNewGame(String thePlayer) throws GameControlException {
        // reset our exit boolean;
        exit = false;
        // set our player
        Player player = new Player(thePlayer, 1);

        // add our authors
        String[] authors = new String[]{Author.Amber.getValue(), Author.Teresa.getValue(), Author.Amy.getValue()};

        // set up animals
        // let's have 4 ages of cows/oxen, as follows (5 of each age)
        // I added 3 more types of animals: horse, pig, and chicken--Teresa Mo
        ArrayList<Integer> ages = new ArrayList<>();
        ages.add(1);
        ages.add(2);
        ages.add(3);
        ages.add(4);
        ArrayList<Animal> animals = new ArrayList<>();
        Animal cows = new Animal("Dairy Cows", ItemType.Animal, 20, Condition.Good, ages);
        Animal oxen = new Animal("Oxen", ItemType.Animal, 20, Condition.Good, ages);
        Animal horse = new Animal("Horse", ItemType.Animal, 20, Condition.Good, ages);
        Animal pig = new Animal("Pig", ItemType.Animal, 20, Condition.Good, ages);
        Animal chicken = new Animal("Chicken", ItemType.Animal, 20, Condition.Good, ages);
        animals.add(cows);
        animals.add(oxen);
        animals.add(horse);
        animals.add(pig);
        animals.add(chicken);

        // set up provisions
        ArrayList<Provision> provisions = new ArrayList<>();
        Provision bread = new Provision("Bread", ItemType.Provision, 200, Condition.Good, true);
        Provision corn = new Provision("Corn Ears (dried)", ItemType.Provision, 5000, Condition.Good, false);
        Provision oil = new Provision("Corn oil casks", ItemType.Provision, 1000, Condition.Good, true);
        provisions.add(bread);
        provisions.add(corn);
        provisions.add(oil);

        // set up tools
        ArrayList<InventoryItem> tools = new ArrayList<>();
        InventoryItem tool = new InventoryItem("Shovel", ItemType.Tool, 26, Condition.Good);
        InventoryItem tool2 = new InventoryItem("Plow", ItemType.Tool, 10, Condition.Good);
        InventoryItem tool3 = new InventoryItem("Shovel", ItemType.Tool, 5, Condition.Poor);
        InventoryItem tool4 = new InventoryItem("Spade", ItemType.Tool, 7, Condition.Good);
        InventoryItem tool5 = new InventoryItem("Rake", ItemType.Tool, 10, Condition.Fair);
        InventoryItem tool6 = new InventoryItem("Hoe", ItemType.Tool, 8, Condition.Poor);
        InventoryItem tool7 = new InventoryItem("Plow", ItemType.Tool, 3, Condition.Poor);
        InventoryItem tool8 = new InventoryItem("Hoe", ItemType.Tool, 1, Condition.Fair);
        InventoryItem tool9 = new InventoryItem("Rake", ItemType.Tool, 5, Condition.Poor);
        InventoryItem tool10 = new InventoryItem("Spade", ItemType.Tool, 8, Condition.Poor);
        tools.add(tool);
        tools.add(tool2);
        tools.add(tool3);
        tools.add(tool4);
        tools.add(tool5);
        tools.add(tool6);
        tools.add(tool7);
        tools.add(tool8);
        tools.add(tool9);
        tools.add(tool10);

        // initialize Storehouse elements
        Storehouse storehouse = new Storehouse(authors, animals, tools, provisions);

        // create the map (call MapControl for this job)
        Map map = new Map();
        map = MapControl.createMap(map);

        // set up our shiny game object
        Game theGame = new Game(player, map, storehouse, 100, 1000, 3000, 1);

        // now fill up the Annual Report for use in the Game Menu
        AnnualReport report = new AnnualReport(23, 1000, 3000, 3, 300, 0, 0, 5);

        // now save all this to the app so it's easy to get in other places!
        CityOfAaron.setCurrentGame(theGame);

        CityOfAaron.setCurrentReport(report);

        // set a year-2 land price
        Crops.setUpcomingLandPrice();

        return theGame;
    }

    public static void loadGameFromFile(String filename) throws GameControlException, FileNotFoundException, IOException, ClassNotFoundException {
        // make the game a regular object
        String filepathA = filepath + filename + ".txt";
        Object obj;
        Object obj2;
        try (FileInputStream fi = new FileInputStream(filepathA); ObjectInputStream oi = new ObjectInputStream(fi)) {
            obj = oi.readObject();
            obj2 = oi.readObject();
            Game game = (Game) obj;
            AnnualReport report = (AnnualReport) obj2;
            CityOfAaron.setCurrentGame(game);
            CityOfAaron.setCurrentReport(report);
        }

    }

    public static void saveGameToFile(String filename, Game game, AnnualReport report) throws FileNotFoundException, IOException {
        // make the game a regular object
        String filepathB = filepath + filename + ".txt";
        Object obj = (Object) game;
        Object obj2 = (Object) report;
        try (FileOutputStream f = new FileOutputStream(filepathB); ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(obj);
            o.writeObject(obj2);
        }
    }

    public static boolean gameShouldEnd(Game game) throws GameControlException {
        boolean ended = false;
        int rating = PeopleControl.calculateUserRating(game);
        if (rating == 0) {
            ended = true;
        }
        return ended;
    }

    /**
     * Process the current year's results and update the Game Object.
     *
     * @param game The current Game object (pass by reference)
     * @throws exceptions.GameControlException
     */
    public static void liveTheYear(Game game) throws GameControlException {

        // get all our upcoming numbers
        int tithesPercent = Crops.getTithingPercentToPay();
        int bushelsForFood = Crops.getBushelsToFeedPeople();
        int acresToPlant = Crops.getAcresToPlant();
        int wheatToPlant = Crops.getWheatToPlant();
        int landPrice = Crops.getUpcomingLandPrice();
        int landToBuy = Crops.getLandToBuy();
        int landToSell = Crops.getLandToSell();

        // ERROR here
        if (tithesPercent < 0 || tithesPercent > 100
                || bushelsForFood < 0 || acresToPlant < 0) {
            throw new GameControlException("Your numbers are impossible! Try again.");
        }
        // ERROR now note if the most important ones are zero and kick back
        if (acresToPlant == 0 || bushelsForFood == 0) {
            throw new GameControlException("You are going to starve everyone! \nPlease update your acres to plant and bushels for food.");
        }

        // next get the current total of wheat bushels in storage and acres owned
        int totalWheat = game.getWheatInStorage();
        int acres = game.getAcresOwned();

        //Add the number of acres purchased to the acres owned
        acres = landToBuy + acres;
        //Subtract the wheat used to purchase the land from the wheat in storage
        totalWheat = totalWheat - (landToBuy * landPrice);
        //Subtract the number of acres sold from the acres owned.
        acres = acres - landToSell;
        //Add the bushels of wheat that was increased by the selling of land to the bushels of wheat in storage.
        totalWheat = totalWheat + (landToSell * landPrice);

        // Subtract the bushels to plant crops from total.
        totalWheat = totalWheat - (wheatToPlant);
        // Subtract the bushels to feed people from total.
        totalWheat = totalWheat - bushelsForFood;

        // ERROR if totalWheat < 0 at this point, need an error!! Means player didn't plan enough.
        if (totalWheat < 0) {
            throw new GameControlException("You used more wheat than was in your storehouse!\n"
                    + "Please adjust your numbers in the 'Manage Crops' area and then live the year again.");
        }
        // now figure out our harvest
        int perAcre = WheatControl.calcBushelsPerAcre(tithesPercent);
        int harvested = WheatControl.calcHarvest(perAcre, acresToPlant);
        int tithingAmount = (int) (double) ((tithesPercent / 100.0) * harvested);
        int lostToRobbers = WheatControl.calcLossToRobbers(tithesPercent, harvested);

        // now figure out what our population should be
        int peopleStarved = PeopleControl.calculateMortality(bushelsForFood, game.getCurrentPopulation());
        int peopleMovedIn = PeopleControl.calculateNewMoveIns(game.getCurrentPopulation());

        /// Store the new number of wheat bushels in storage, acres owned, and new population as we end the year
        totalWheat = totalWheat + harvested - tithingAmount - lostToRobbers;
        game.setWheatInStorage(totalWheat);
        game.setAcresOwned(acres);
        game.setCurrentPopulation(game.getCurrentPopulation() - peopleStarved + peopleMovedIn);

        // advance the year by one
        game.setYear(game.getYear() + 1);

        // update our game
        CityOfAaron.setCurrentGame(game);
        // update the report
        AnnualReport report = new AnnualReport(landPrice, acresToPlant, harvested, perAcre, tithingAmount, lostToRobbers, peopleStarved, peopleMovedIn);
        CityOfAaron.setCurrentReport(report);

        // let's update the land price for next year, needs no argument
        Crops.setUpcomingLandPrice();
        // and reset the manage crops variables for next year
        Crops.setTithingPercentToPay(0);
        Crops.setBushelsToFeedPeople(0);
        Crops.setAcresToPlant(0);
        Crops.setWheatToPlant(0);
        Crops.setLandToBuy(0);
        Crops.setLandToSell(0);
    }

    /**
     * Generates a random integer between lowValue and highValue, inclusive.
     * <ul>Requirements:
     * <li>lowValue and highValue must be positive int (&gt;= 0) (return -1)</li>
     * <li>highValue must be greater than lowValue (return -2) </li>
     * <li>highValue cannot be equal to the max value for int (return -3)</li>
     * </ul>
     *
     * @param lowValue
     * @param highValue
     * @return The random number
     */
    public static int getRandomNumber(int lowValue, int highValue) throws GameControlException {
        // if low < 0 or high < 0 then return -1
        if (lowValue < 0 || highValue < 0) {
            throw new GameControlException("The value cannot be less than 0.");
        }
        // if high <= low then return -2
        if (highValue <= lowValue) {
            throw new GameControlException("The high value cannot be less than or equal to the low value");
        }

        // if high is the max val for int, then return -3
        if (highValue == Integer.MAX_VALUE) {
            throw new GameControlException("The high equals the max value for the integer.");
        }

        // calc the size of the range; add one so Random() includes high val
        int range = (highValue - lowValue) + 1;

        // return low + random(range size)
        return lowValue + randomGenerator.nextInt(range);

    }

    /**
     * Protected setter for tests to inject a mock random object.
     *
     * @param random
     */
    protected static void setRandomGenerator(Random random) {
        randomGenerator = random;
    }

}
