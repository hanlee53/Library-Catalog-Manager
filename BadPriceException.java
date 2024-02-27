/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */
package Exceptions;

public class BadPriceException extends Exception {
    /**
     * default constructor
     */
    public BadPriceException() {
        super("Negative price is not valid!");
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
    public BadPriceException(String s) {

        super(s);

    }

    public String getMessage() {
        return super.getMessage();
    }
}
