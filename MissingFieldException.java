/**
 *@author SeyedParsa Hejazi
 *@author Han Lee
 */
package Exceptions;
public class MissingFieldException extends Exception {
    public MissingFieldException(){
        super ("Missing field detected!");
    }
    public MissingFieldException(String s){
        super(s);

    }
    public String getMessage(){
        return super.getMessage();
    }
}
