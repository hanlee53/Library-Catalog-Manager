/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */
package Exceptions;
public class UnknownGenreException extends Exception{
    public UnknownGenreException(){
        super ("Uknown genre detected!");
    }
    public UnknownGenreException(String s){
        super(s);

    }
    public String getMessage(){
        return super.getMessage();
    }
}
