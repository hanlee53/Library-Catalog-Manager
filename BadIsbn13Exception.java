/**
 *@author SeyedParsa Hejazi
 *@author Han Lee
 */
package Exceptions;

public class BadIsbn13Exception extends Exception{
    public BadIsbn13Exception(){
        super ("The 13 digit ISBN is not valid.");
    }
    public BadIsbn13Exception(String s){
        super(s);

    }
    public String getMessage(){
        return super.getMessage();
    }
}
