package Exceptions;
/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */

public class TooManyFieldsException extends Exception{
	public TooManyFieldsException(){
		super ("Too many fields detected!");
	}
	public TooManyFieldsException(String s){
		super(s);

	}
	public String getMessage(){
		return super.getMessage();
	}
}
