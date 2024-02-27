/**
 *@author SeyedParsa Hejazi 
 *@author Han Lee
 */

import java.io.*;
import java.util.Scanner;

import Exceptions.BadIsbn10Exception;
import Exceptions.BadIsbn13Exception;
import Exceptions.BadPriceException;
import Exceptions.BadYearException;
public class classPart2 {

    /**
     * do_part2 method
     *
     * @throws Exception
     */
    public static void do_part2() throws Exception {
        PrintWriter fileWriter2 = null;
        BufferedReader reader3 = null;
        String[] createdFiles = {"Cartoons_Comics.csv.ser", "Hobbies_Collectibles.csv.ser", "Movies_TV_Books.csv.ser", "Music_Radio_Books.csv.ser",
                "Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio_Books.csv.ser", "Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser"};
        String[] initialFiles = {"Cartoons_Comics_Books.csv.txt", "Hobbies_Collectibles_Books.csv.txt","Movies_TV.csv.txt", "Music_Radio_Books.csv.txt",
                "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio.csv.txt", "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"};
        

        /**
        Open all files
         */

        try {
            /**
             * For loop to iterate through every file
             */
            for (int i = 0; i < createdFiles.length; i++) {
                /**
                 * Initialize book array for dynamic allocation and a counter to keep count of objects in array
                 */
                Book[] BooksArray = new Book[1];
                int bookCounter = 1;
                /**
                 * Initializing BufferedReader
                 */
                reader3 = new BufferedReader(new FileReader(initialFiles[i]));
                /**
                 * Initializing ObjectOutputStream
                 */
                ObjectOutputStream objectReader = new ObjectOutputStream(new FileOutputStream(createdFiles[i]));
                /**
                 * Initialzing PrintWriter
                 */
                fileWriter2 = new PrintWriter(new FileOutputStream("semantic_error_file.txt",true),true);
                String lineProcessor;
                /**
                 * Skips the first line of each file since it displays the number of files
                 */
                lineProcessor = reader3.readLine();
                /**
                 * While loop, stop at the end of the file, checks every line until null
                 */
                while ((lineProcessor = reader3.readLine()) != null) {
                    try {
                        String[] bookAttributes;
                        Book[] otherBookArray = BooksArray;
                        /**
                         *create new array with new size, replacing old one and copying that arrays objects into the copy array
                         */
                        BooksArray = new Book[bookCounter];
                        for (int y = 0; y < otherBookArray.length; y++) {
                            BooksArray[y] = otherBookArray[y];
                        }
                        int sumISBN10 = 0;
                        int sumISBN13 = 0;
                        /**
                         * Create Array to hold the individual parts of the ISBN number after it has been split using the split() method
                         */
                        String[] newISBNarray = new String[0];
                        /**
                         * This code block checks if a line from the input file has a book title enclosed within double quotes.
                         * If it does, it splits the line using the double quote as a delimiter to extract the book title, and then splits the remaining portion of the line using commas as delimiters to extract the other book details.
                         * It creates a copy of the split book details and assigns them to a fixed size array of 6, with the book title assigned to the first element.
                         *  If the line doesn't contain a double quote, it splits the line using commas as delimiters.
                         */
                        if (lineProcessor.contains("\"")) {
                            String[] title = lineProcessor.substring(1).split("\"");
                            bookAttributes = title[1].substring(1).split(",");
                            String[] bookAttributesCopy = bookAttributes.clone();
                            bookAttributes = new String[6];
                            for (int j = 0; j < bookAttributesCopy.length; j++) {
                                bookAttributes[j + 1] = bookAttributesCopy[j];
                            }
                            bookAttributes[0] = title[0] + "\"";
                            /**
                             * if no quotes, then split by commas
                             */
                        } else
                            bookAttributes = lineProcessor.split(",");
                        /**
                         * split isbn number seperately from the array
                         */
                        newISBNarray = bookAttributes[3].trim().split("");
                        int ISBNcounter = 10;
                        /**
                         * if isbn is equals to ten, validate
                         */
                        if (newISBNarray.length == 10) {
                            /**
                             * for loop the check if there is x at the end of isbn #
                             * and to validate isbn number
                             * fill the isbn sum number
                             */
                            for (int p = 0; p < newISBNarray.length; p++) {
                                if (newISBNarray[9].equalsIgnoreCase("x")) {
                                    newISBNarray[9] = "10";
                                }
                                sumISBN10 += (10-p)*Integer.parseInt(newISBNarray[p]);
                            }
                            /**
                             * if isbn is equals to 13
                             */
                        } else if (newISBNarray.length == 13) {
                            /**
                             * same purpose as the one above, except for 13 instead
                             */
                            for (int k = 0; k < newISBNarray.length; k++) {
                                if (newISBNarray[12].equalsIgnoreCase("x")) {
                                    newISBNarray[12] = "10";
                                }
                                sumISBN13 += ISBNcounter * Integer.parseInt(newISBNarray[k]);
                                if (k % 2 == 1) {
                                    sumISBN13 = sumISBN13 * 3;
                                }
                            }
                        }
                        /**
                         * Exceptions that we throw if we need to (Follow rules to know when to throw)
                         */
                        if ((Double.parseDouble(bookAttributes[2]) < 0)) {
                            throw new BadPriceException();
                        }

                        if (newISBNarray.length == 10 && ((sumISBN10 % 11) != 0)) {
                            throw new BadIsbn10Exception();
                        }
                        if (newISBNarray.length == 13 && (sumISBN13 % 10) != 0) {
                            throw new BadIsbn13Exception();
                        }
                        if ((1995 > Integer.parseInt(bookAttributes[5])) || (Integer.parseInt(bookAttributes[5]) > 2010)) {
                            throw new BadYearException();
                        }
                        /**
                         * counter goes up by 1 to make BooksArray bigger so that it can take object of next line
                         */
                        ++bookCounter;
                        /**
                         * create object with info of that line b/c no exceptions were caught
                         */
                        Book newBook = new Book(bookAttributes[0], bookAttributes[1], Double.parseDouble(bookAttributes[2]), bookAttributes[3], bookAttributes[4], Integer.parseInt(bookAttributes[5]));
                        /**
                         * place object in array, with -1 length b/c we did make it bigger with the counter
                         */
                        BooksArray[BooksArray.length - 1] = newBook;
                    }
                    /**
                     * catch exceptions thrown
                     */
                    catch (BadIsbn10Exception e) {
                        fileWriter2.println("Semantic error in file: " + createdFiles[i]);
                        fileWriter2.println("====================");
                        fileWriter2.println(e.getMessage());
                        fileWriter2.println("Record: " + lineProcessor);
                    } catch (BadIsbn13Exception e) {
                        fileWriter2.println("Semantic error in file: " + createdFiles[i]);
                        fileWriter2.println("====================");
                        fileWriter2.println(e.getMessage());
                        fileWriter2.println("Record: " + lineProcessor);
                    } catch (BadPriceException e) {
                        fileWriter2.println("Semantic error in file: " + createdFiles[i]);
                        fileWriter2.println("====================");
                        fileWriter2.println(e.getMessage());
                        fileWriter2.println("Record: " + lineProcessor);
                    } catch (BadYearException e) {
                        fileWriter2.println("Semantic error in file: " + createdFiles[i]);
                        fileWriter2.println("====================");
                        fileWriter2.println(e.getMessage());
                        fileWriter2.println("Record: " + lineProcessor);
                    }


                }

                /**
                 * Write the array of object into each file serializaed
                 */
                /**
                 * Close oos
                 */
                objectReader.writeObject(BooksArray);
                objectReader.close();

            }


        }
        /**
         * catch exception when initially opening the files, at the beginning
         */

        catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }

    /* if (fileWriter2 != null) {
            fileWriter2.close();
        }
        else{
            System.out.println("Error, fileWriter2 is null");
        }*/

        /**
         * close bufferedreader and printer
         */
        fileWriter2.close();
        reader3.close();


    }



}
