/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import app.CityOfAaron;
import java.io.PrintWriter;

/**
 *
 * @author Amber Mitchell
 */
public class ErrorView {
    private static PrintWriter errorFile = CityOfAaron.getOutFile();
    private static PrintWriter logFile = CityOfAaron.getLogFile();
    
    public ErrorView(){
        super();
    }
    public static void display (String className, String errorMessage){
        
        errorFile.println(
        "\n--- ERROR ----------------------------------------"
        + "\n" + errorMessage
        + "\n------------------------------------------------");
        
        logFile.printf("%n%n%s", className + " -- " + errorMessage);
    }
    
}
