package model;

import java.io.Serializable;
/**
 * @author Amber Mitchell
 */
public class Point implements Serializable {
    private int row;
    private int column;
    
    public Point(){
        //Empty constructor for bean
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getColumn(){
        return column;
    }
    public void setColumn(int column){
        this.column = column;
    }
   
    // toString method
    @Override
    public String toString(){ 
        return "Point  " + "(" + row + "," + column + ")";    
    }
    
}
