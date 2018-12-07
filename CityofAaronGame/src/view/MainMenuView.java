/*
 * Main Menu class. This view engages on game startup right after the welcome message, and is 
 * bounced back to when deeper views exit.
 */
package view;

/**
 *
 * @author Amber Mitchell
 */
public class MainMenuView extends ViewBase {

    /**
     * Constructor
     */
    public MainMenuView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\n--------------------\n"
                + "Main Menu\n"
                + "--------------------\n"
                + "N - Start new game\n"
                + "L - Load saved game\n"
                + "H - Help menu\n"
                + "Q - Quit game\n"
                + "\n";
    }

    /**
     * Get the set of inputs from the user.
     *
     * @return
     */
    @Override
    public String[] getInputs() {

        String[] inputs = new String[1];
        inputs[0] = getUserInput("Please select a choice from the Main Menu.");

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
        try {
            switch (inputs[0].trim().toUpperCase()) {
                case "N":
                    startNewGame();
                    break;
                case "L":
                    loadSavedGame();
                    break;
                case "H":
                    helpMenu();
                    break;
                case "Q":
                    this.console.println("Thank you for playing. Good-bye.");
                    return false;
            }
        } catch (NullPointerException e) {
            ErrorView.display(this.getClass().getName(), "A choice must be made.");
        }
        return true;
    }

    private void startNewGame() {
        View view = new NewGameView();
        view.displayView();
    }

    private void helpMenu() {
        View help = new HelpMenuView();
        help.displayView();
    }

    private void loadSavedGame() {
        View view = new LoadGameView();
        view.displayView();
    }
}
