/**
 *@author SeyedParsa Hejazi
 *@author Han Lee
 */
package Exceptions;

public class BadIsbn10Exception extends Exception {
    
    public BadIsbn10Exception(){
        super ("The 10 digits ISBN is not valid.");
    }

    /**
     *
     * @param s takes in a message that will be displayed as the error message
     */
    // takes in a message that'll be displaced per user's request
    public BadIsbn10Exception(String s){
        super(s);

        /**
         * @return String return the message above
         */
    }
    public String getMessage(){
        return super.getMessage();
    }
}
