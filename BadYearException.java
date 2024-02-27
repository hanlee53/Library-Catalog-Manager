/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */
package Exceptions;

public class BadYearException extends Exception{
    /**
     * default constructor
     */
    public BadYearException(){
        super ("Impossible year! Does not exist.");
    }

    /**
     *
     * @param s takes in a message that will be displayed as the error message
     */
    // takes in a message that'll be displaced per user's request

    /**
     *
     * @param s returns the message above
     */
    public BadYearException(String s){
        super(s);

    }
    public String getMessage(){
        return super.getMessage();
    }
}
