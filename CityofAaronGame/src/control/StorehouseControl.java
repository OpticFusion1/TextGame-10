/*
 * This class helps us print reports from the Storehouse
 */
package control;

import app.CityOfAaron;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Animal;
import model.InventoryItem;
import model.Provision;

/**
 *
 * @author Amber Mitchell
 */
public class StorehouseControl {
    private final String[] args = new String[3];
    /**
     * Constructor
     * @param animalFile
     * @param provisionFile
     * @param toolFile
     * @throws java.io.IOException
     */
    public StorehouseControl(String animalFile, String provisionFile, String toolFile) throws IOException {
        args[0] = animalFile;
        args[1] = provisionFile;
        args[2] = toolFile;
        printProvisions(args[1]);
    }

    public static void printAnimals(String arg) throws IOException {
        String filename = "animals.txt";
        if (arg != null) {
            filename = arg;
        }

        // set up data
        ArrayList<Animal> animals = CityOfAaron.getCurrentGame().getTheStorehouse().getAnimals();

        try (PrintWriter report = new PrintWriter(new FileWriter(filename))) {

            report.println("Animals");
            report.println();

            String formatString = "%-40s %-15s %-15s %-15s %10s";
            report.println(String.format(formatString, "NAME", "TYPE", "QUANTITY", "CONDITION", "AGES"));
            report.println("------------------------------------------------------------------------------------------------------");

            for (Animal animal : animals) {
                report.println(String.format(formatString, animal.getName(), animal.getType(), animal.getQuantity(), animal.getCondition(), animal.getAge()));
            }

            report.println();
            report.println("End");

            report.flush();
        }

    }
    public static void printProvisions(String arg) throws IOException {
        String filename = "provisions.txt";
        if (arg != null) {
            filename = arg;
        }

        // set up data
        ArrayList<Provision> provisions = CityOfAaron.getCurrentGame().getTheStorehouse().getProvisions();

        try (PrintWriter report = new PrintWriter(new FileWriter(filename))) {

            report.println("Provisions");
            report.println();

            String formatString = "%-40s %-15s %-15s %-15s %10s";
            report.println(String.format(formatString, "NAME", "TYPE", "QUANTITY", "CONDITION", "PERISHABLE"));
            report.println("------------------------------------------------------------------------------------------------------");

            for (Provision provision : provisions) {
                report.println(String.format(formatString, provision.getName(), provision.getType(), provision.getQuantity(), provision.getCondition(), provision.isPerishable()));
            }

            report.println();
            report.println("End");

            report.flush();
        }

    }
    public static void printTools(String arg) throws IOException {
        String filename = "tools.txt";
        if (arg != null) {
            filename = arg;
        }

        // set up data
        ArrayList<InventoryItem> tools = CityOfAaron.getCurrentGame().getTheStorehouse().getTools();

        try (PrintWriter report = new PrintWriter(new FileWriter(filename))) {

            report.println("Tools");
            report.println();

            String formatString = "%-40s %-15s %-15s %15s";
            report.println(String.format(formatString, "NAME", "TYPE", "QUANTITY", "CONDITION"));
            report.println("------------------------------------------------------------------------------------------------------");

            for (InventoryItem tool : tools) {
                report.println(String.format(formatString, tool.getName(), tool.getType(), tool.getQuantity(), tool.getCondition()));
            }

            report.println();
            report.println("End");

            report.flush();
        }

    }
}
