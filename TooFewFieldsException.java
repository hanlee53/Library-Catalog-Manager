package Exceptions;
/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */
public class TooFewFieldsException extends Exception {
    public TooFewFieldsException(){
        super ("Not enough fields!");
    }
    public TooFewFieldsException(String s){
        super(s);

    }
    public String getMessage(){
        return super.getMessage();
    }
}