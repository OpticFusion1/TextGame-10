/*
 * Help Menu class. This view engages when the player chooses "H" from the Main Menu.
 */
package view;

/**
 *
 * @author Amber Mitchell
 */
public class HelpMenuView extends ViewBase {

    /**
     * Constructor
     */
    public HelpMenuView() {
        super();
    }

    @Override
    protected String getMessage() {
        return "\n\n--------------------\n"
                + "Help Menu\n"
                + "--------------------\n"
                + "1 - Game Goals\n"
                + "2 - Where is the city of Aaron?\n"
                + "3 - How to view map\n"
                + "4 - How to move to another location\n"
                + "5 - How to display items in storehouse\n"
                + "X - Exit to the Main Menu\n";
        
    }
    /**
     * Get the set of inputs from the user.
     * @return
     */
    @Override
    public String[] getInputs() {

        String[] inputs = new String[1];
        inputs[0] = getUserInput("Which help menu option would you like?");

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
        String which;
        which = inputs[0].trim().toUpperCase();
        switch (which) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                helpItemMessage(which);
                pause(3000);

                break;
            case "X":
                return false;
        }
        return true;
    }

    private void helpItemMessage(String which) {
        switch (which) {
            case "1":
                which = "\n\n--------------------\n"
                        + "Your goal, as steward of the City of Aaron, is\n"
                        + "to care for your people in such a way that they\n"
                        + "thrive (or at least don't die!). This is done by\n"
                        + "careful management of your wheat fields over the\n"
                        + "course of your term as leader."
                        + "\n--------------------\n";
                break;

            case "2":
                which = "\n\n--------------------\n"
                        + "The City of Aaron is a lovely Nephite land with fields\n"
                        + "and villages, rivers and wild lands, stretching from\n"
                        + "the center of rule to the border of Lamanite land."
                        + "\n--------------------\n";
                break;

            case "3":
                which = "\n\n--------------------\n"
                        + "View the Map at the start of any new or saved game\n"
                        + "or at any time from Game Menu."
                        + "\n--------------------\n";
                break;

            case "4":
                which = "\n\n--------------------\n"
                        + "Move to a new location at the start of any new or saved game\n"
                        + "or at any time from Game Menu."
                        + "\n--------------------\n";
                break;

            case "5":
                which = "\n\n--------------------\n"
                        + "You may view the Storehouse at the start of any new or saved game\n"
                        + "or at any time from Game Menu, by choosing the 'Reports Menu'."
                        + "\n--------------------\n";
                break;

        }
        this.console.println(which);
    }

}
