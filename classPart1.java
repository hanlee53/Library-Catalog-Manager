/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */
/**
 */
import java.io.*;
import Exceptions.*;
import java.util.StringTokenizer;

public class classPart1 {

	public static void do_part1() throws TooManyFieldsException, IOException, TooFewFieldsException {

		int fileCount = 0;
		int mtvCount = 0;
		int ccbCount = 0;
		int ssmCount = 0;
		int nebCount = 0;
		int mrbCount = 0;
		int tpaCount = 0;
		int hcbCount = 0;
		int otrCount = 0;
		int syntCount = 0;
		BufferedReader reader = null;
		String[] segmentsArray = new String[6];
		String inputFile = "C:\\Users\\Parsa\\Downloads\\a3_SeyedParsa-Hejazi_40240798-AND-Han-Lee_40235611\\A3_249\\src\\textFiles\\part1_input_file_names.txt";
		String bookHolder = "";
		String path1 = "";
		
		

		/**
		*The line below reads the first line of the file part1_input_file_name
		* Since we know that the first line of this file is an integer that tells us how many files are inside I use the method Integer.parseInt() in combination to readLine() to store it to the integer fileCount
		 */

		reader = new BufferedReader(new FileReader(inputFile));
		fileCount = Integer.parseInt(reader.readLine());
/**
*This for loop uses the numFile integer that was read on the line above and runs through all the files inside the part1_input_file_name
 */
		for (int i = 0; i < fileCount; i++) {

			path1 = "C:\\Users\\Parsa\\Downloads\\a3_SeyedParsa-Hejazi_40240798-AND-Han-Lee_40235611\\A3_249\\src\\textFiles\\" + reader.readLine();
			PrintWriter fileWriter = null;

			try {

				BufferedReader reader2 = new BufferedReader(new FileReader(path1));
				String solidLine;
				/**
				*This while loop runs until the BufferedReader finds an empty line (Which would indicate the end of the file)
				* It also defines the String line by assigning it to the line read by the bufferedReader
				 */
				while ((solidLine = reader2.readLine()) != null) {


					bookHolder = solidLine;

					/**
					*This for loop counts the amount of commas inside the String bookHolder excluding thoses inside the readerackets
					* The counter is the integer commaNo and I declare a Boolean quotesWrapped to false
					* The loop runs through the length of the string and uses if statements and else if
					* I declare a character c and I use the method charAt onto the string bookHolder to go through the String

					 */
					boolean quotesWrapped = false;
					int commaNo = 0;
					for (int j = 0; j < bookHolder.length(); j++) {
						char index = bookHolder.charAt(j);
						/**
						*If the character is a comma and the quotesWrapped is equal to false, the counter commaNo is increased by one
						 */
						if (index == ',' && !quotesWrapped) {
							commaNo++;
						}
						/**
						*If the character is a quote and the boolean quotesWrapped is False, I redefine the Boolean inside Quotes to true to not trigger the counter
						 */
						else if (index == '"' && !quotesWrapped) {
							quotesWrapped = true;
						}
						/**
						*If the character is a quote again and that quotesWrapped is true (As it should be since we redefined it to true the last time it found a quote) , we redefine the boolean quotesWrapped to false to trigger our counter back again
						 */
						else if (index == '"' && quotesWrapped) {
							quotesWrapped = false;
						}

					}
					/**
					*This if statement checks for the amount of commas counted above(excluding those inside the quotes)
					* If the amount of commas is over 5 (5 being the good amount of commas required), it throws a TooManyFields Exception
					* It also writes in the Syntax Error file the amount of commas that are over and where the mistake got found
					 */
					if (commaNo > 5) {
						//throw new TooManyFieldsException("The CVS contains too many fields");

						try {
							fileWriter = new PrintWriter(new FileOutputStream("syntax_error_file.txt", true));

							fileWriter.println("\nSyntax error in file: " + path1 + "\n" + "===================================" + "\n" + "Error: There are " + (commaNo - 5) + " extra fields\n" + "Record: " + bookHolder);
							fileWriter.close();
							syntCount++;
							throw new TooManyFieldsException("Too many fields Exception!");

						} catch (FileNotFoundException e) {
							throw new RuntimeException(e);
						} catch (TooManyFieldsException e) {
						}
						/**
						*This else if statement checks for the amount of commas that were counted above (Excluding thoses inside the quotes).
						* If the amount of commas counted is below 5 (Which would mean that there is a field missing ) The error message and the exception TooFewFields is thrown
						* It also writes inside the syntax error file where it found the mistake and how many commas are missing
						 */
					} else if (commaNo < 5) {
						try {
							fileWriter = new PrintWriter(new FileOutputStream("syntax_error_file.txt", true));
							fileWriter.println("\nSyntax error in file: " + path1 + "\n" + "===================================" + "\n" + "Error: There are " + (5 - commaNo) + " missing fields\n" + "Record: " + bookHolder);
							fileWriter.close();
							syntCount++;
							throw new TooFewFieldsException("Too few fields in book record!");
						} catch (FileNotFoundException e) {
							throw new RuntimeException(e);
						} catch (TooFewFieldsException e) {
							System.out.println("Too few fields in book record!");
						} finally {
						}
					} else {
						/**
						*This statement below splits the String bookHolder that got read by the BufferedReader
						* It splits the String using the commas, but it doesn't consider the commas inside the quotes
						* The result is then store into an segmentsArrayay of Strings called segmentsArray
						* The method split is used and regular expressions are used to specify the conditions in which to split the String
						*
						 */
						segmentsArray = bookHolder.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

					}
					/**
					* The if statement below runs the block if it finds a part of the segmentsArrayay that is either null or empty
					* It prints the file in which the error got found
					* Then It uses If statements to print a message declaring what is missing
					*/


					if (segmentsArray[4] == null || segmentsArray[2] == null || segmentsArray[5] == null || segmentsArray[0] == null || segmentsArray[3] == null || segmentsArray[1] == null || segmentsArray[4] == "" || segmentsArray[2] == "" || segmentsArray[5] == "" || segmentsArray[0] == "" || segmentsArray[3] == "" || segmentsArray[1] == "") {
						try {
							fileWriter = new PrintWriter(new FileOutputStream("syntax_error_file.txt", true));
							fileWriter.println("\nSyntax error in file: " + path1 + "\n" + "===================================" + "\n");
							fileWriter.flush();
							syntCount++;
					
							if (segmentsArray[3] == null || segmentsArray[3] == "") {
								fileWriter.println("Error: Missing ISBN\n");
								fileWriter.flush();
							}
							if (segmentsArray[5] == null || segmentsArray[5] == "") {
								fileWriter.println("Error: Missing Year\n");
								fileWriter.flush();
							}
							if (segmentsArray[1] == null || segmentsArray[1] == "") {
								fileWriter.println("Error: Missing Author\n");
								fileWriter.flush();
							}
							if (segmentsArray[0] == null || segmentsArray[0] == "") {
								fileWriter.println("Error: Missing title\n");
								fileWriter.flush();
							}
							if (segmentsArray[2] == null || segmentsArray[2] == "") {
								fileWriter.println("Error: Missing Price\n");
								fileWriter.flush();
							}
							if (segmentsArray[4] == null || segmentsArray[4] == "") {
								fileWriter.println("Error: Missing Genre\n");
								fileWriter.flush();
							}
							fileWriter.println("Record: " + bookHolder);
							fileWriter.flush();
					
							throw new MissingFieldException("Missing a field!");
					
						} catch (FileNotFoundException e) {
							throw new RuntimeException(e);
						} catch (MissingFieldException e) {

						}
					}
					


					String genreCheck = segmentsArray[4];

					/**
					* This If statement checks for 2 conditions, one where a field in the segmentsArrayay is empty and one where the line contains two commas back to back
					*/
					if ((!bookHolder.matches(".*,,.*") && genreCheck != null) && (segmentsArray[0] != null && segmentsArray[1] != null && segmentsArray[2] != null && segmentsArray[3] != null && segmentsArray[5] != null) && (segmentsArray[0] != "" && segmentsArray[1] != "" && segmentsArray[2] != "" && segmentsArray[3] != "" && segmentsArray[5] != "")) {
						/**
						*This switch statement uses the String genre (equal to the String at index 4 of the segmentsArrayay where we split the bookHolder string
						* This Switch statements identifies the genre and write the String bookHolder to the appropriate file
						* The default statement would handle any unknown genre
						 */
						if (genreCheck.equals("MTV")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Movies_TV.csv.txt", true));
								fileWriter.println(bookHolder);
								mtvCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("CCB")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Cartoons_Comics_Books.csv.txt", true));
								fileWriter.println(bookHolder);
								ccbCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("SSM")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Sports_Sports_Memorabilia.csv.txt", true));
								fileWriter.println(bookHolder);
								ssmCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("NEB")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Nostalgia_Eclectic_Books.csv.txt", true));
								fileWriter.println(bookHolder);
								nebCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("MRB")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Music_Radio_Books.csv.txt", true));
								fileWriter.println(bookHolder);
								mrbCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("TPA")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Trains_Planes_Automobiles.csv.txt", true));
								fileWriter.println(bookHolder);
								tpaCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("HCB")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Hobbies_Collectibles_Books.csv.txt", true));
								fileWriter.println(bookHolder);
								hcbCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("OTR")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("Old_Time_Radio.csv.txt", true));
								fileWriter.println(bookHolder);
								otrCount++;
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							}
							fileWriter.close();
						} else if (genreCheck.equals("SYNT")) {
							try {
								fileWriter = new PrintWriter(new FileOutputStream("syntax_error_file.txt", true));
								fileWriter.println("\nSyntax error in file: " + path1 + "\n" + "===================================" + "\n" + "Error: Invalid Genre\n" + "Record: " + bookHolder);
								syntCount++;
								throw new UnknownGenreException("Unknown Genre!");
							} catch (FileNotFoundException e) {
								throw new RuntimeException(e);
							} catch (UnknownGenreException e) {
							}
							fileWriter.close();
						}
						
						


					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

		}

	}



}