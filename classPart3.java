/**
 *@author SeyedParsa Hejazi
 *@author Han Lee
 */

import java.io.*;
import java.util.Scanner;
public class classPart3 {

    public static void do_part3() throws Exception{

        /**
         * Scanners initialization
         */
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = null;

        String[] createdFiles = {"Cartoons_Comics.csv.ser","Hobbies_Collectibles.csv.ser","Movies_TV_Books.csv.ser","Music_Radio_Books.csv.ser",
        "Nostalgia_Eclectic_Books.csv.ser","Old_Time_Radio_Books.csv.ser", "Sports_Sports_Memorabilia.csv.ser","Trains_Planes_Automobiles.csv.ser"};

        String[] createdFilesLength = new String[createdFiles.length];
        String tempfile = createdFiles[0];
        Book[][] openedFiles = new Book[createdFilesLength.length] [];
        int option = 0;

        try{

            for(int i = 0; i < createdFiles.length; i++){
                FileInputStream reader = new FileInputStream(createdFiles[i]);
                ObjectInputStream opener = new ObjectInputStream(reader);
                openedFiles[i] = (Book[]) opener.readObject();
                sc2 = new Scanner(opener);
                /**
                 * check length of book array
                 */
                int lengthOf = (openedFiles[i]).length;
                String numRecords = "(" + lengthOf + " record)";
                createdFilesLength[i] = createdFiles[i] + "\t" + numRecords;
            }

            String option2;
            String openingSet = createdFilesLength[0];

            do{
                System.out.println("-----------------------------");
                System.out.println("\t" +"Main Menu");
                System.out.println("-----------------------------");
                System.out.println("v View the selected file: " + openingSet);
                System.out.println("s Select a file to view");
                System.out.println("x Exit");
                System.out.println("-----------------------------");
                System.out.print("Enter Your Choice: ");
                option2 = sc.next().toLowerCase();

                if ("v".equals(option2)) {
                    int recordNo1 = 0;
                    while (true) {
                        System.out.println("Viewing: " + openingSet);
                        System.out.print("Please enter a command: ");
                        int n = sc.nextInt();
                
                        if (n == 0) {
                            System.out.println("Viewing session ended!\n Going back to the main menu!");
                            break;
                        } else if (n > 0) {
                            int i = 0;
                            try {
                                for (i = recordNo1; i <= (recordNo1 + (n - 1)); i++) {
                                    System.out.println(openedFiles[option][i]);
                                    System.out.println("=============================================");
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("EOF has been reached");
                            }
                            recordNo1 = i - 1;
                            System.out.println();
                        } else {
                            int k;
                            int counter = 0;
                            int firstBook = (recordNo1 - (Math.abs(n) - 1));
                
                            for (k = firstBook; k <= recordNo1; k++) {
                                if (k < 0) {
                                    if (counter == 1) {
                                        System.out.println("BOF has been reached");
                                    }
                                    counter++;
                                } else {
                                    System.out.println(openedFiles[option][k]);
                                }
                            }
                            if (!(firstBook < 0)) {
                                recordNo1 = firstBook;
                            }
                            System.out.println();
                        }
                    }
                } else if ("s".equals(option2)) {
                    System.out.println("-----------------------------");
                    System.out.println("File Sub-Menu");
                    System.out.println("-----------------------------");
                    for (int m = 0; m < createdFilesLength.length; m++) {
                        System.out.println((m + 1) + "\t" + createdFilesLength[m]);
                    }
                    System.out.println((createdFilesLength.length + 1) + "\t Exit");
                    System.out.println("-----------------------------\n");
                    System.out.print("Enter Your Choice: ");
                
                    option = sc.nextInt();
                    if (option == createdFilesLength.length + 2) {
                        // Continue to main menu
                    } else if (0 < option && option <= createdFilesLength.length) {
                        tempfile = createdFiles[option - 1];
                        openingSet = createdFilesLength[option - 1];
                    } else {
                        System.out.println("Exit sub-Menu, Back to Main Menu!");
                        // Continue to main menu
                    }
                } else if ("x".equals(option2)) {
                    // Exit the loop and return to the main menu
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
                


                /**
                 * loop stops when x entered
                 */
            }while(!option2.equals("x"));

            System.out.println("You have exit the Menu!");


            /**
             * Catch blocks
             */

        }
        catch(FileNotFoundException e){
            System.out.println("File not Found!");
        }
        catch (IOException e) {
            System.out.println("IOException in main");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class Not Found!");
        }


    }

   


}
