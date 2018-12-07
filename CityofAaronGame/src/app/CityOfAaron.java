/*
 * Main class. This is the first code that will be executed when 
 * the program starts.
 */
package app;

/**
 * @author Amber Mitchell
 */
//import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import model.AnnualReport;
import model.Game;
import view.StartProgramView;
import view.View;

public class CityOfAaron {

    /**
     * The starting point for our application.
     */
    public static Game currentGame = null;
    public static AnnualReport currentReport = null;
    private static PrintWriter outFile = null;
    private static BufferedReader inFile = null;
    private static PrintWriter logFile = null;

    public static PrintWriter getLogFile() {
        return logFile;
    }

    public static void setLogFile(PrintWriter logFile) {
        CityOfAaron.logFile = logFile;
    }

    public static PrintWriter getOutFile() {
        return outFile;
    }

    public static void setOutFile(PrintWriter outFile) {
        CityOfAaron.outFile = outFile;
    }

    public static BufferedReader getInFile() {
        return inFile;
    }

    public static void setInFile(BufferedReader inFile) {
        CityOfAaron.inFile = inFile;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game thisCurrentGame) {
        currentGame = thisCurrentGame;
    }

    public static AnnualReport getCurrentReport() {
        return currentReport;
    }

    public static void setCurrentReport(AnnualReport currentReport) {
        CityOfAaron.currentReport = currentReport;
    }

    public static void main(String[] args) {
        try {

            inFile = new BufferedReader(new InputStreamReader(System.in)); //input
            outFile = new PrintWriter(System.out, true);  //output
            logFile = new PrintWriter("logFile.txt");

            View startProgramView = new StartProgramView();
            startProgramView.displayView();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (CityOfAaron.inFile != null) {
                    CityOfAaron.inFile.close();
                }
                if (CityOfAaron.outFile != null) {
                    CityOfAaron.outFile.close();
                }
                if (logFile != null){
                    logFile.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing files");
                return;
            }
        }
    }

}
