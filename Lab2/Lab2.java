/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since September 7th, 2020
 * 
 * Description: The purpose of this program is to take data from Spotify Top 
 * 200 charts and process it into something that can be used by other 
 * departments in the record label including the executive.
 * 
 * This program looks at the text file downloaded from Excel that has the
 * content of the Spotify Top 200 chart. It makes a 2-dimensional array
 * storing the text file of the Top 200 chart that is used by other methods
 * in the program. Using this 2D array I made a 1-dimensional array that stored
 * just the names of the artist, all 200 that appear on the text file. I then
 * made another 2D array that will contain the artist of the Top 200 chart and
 * the number of times they appear on it. I then made a method which will
 * alphabetize the second 2D array, and print methods for the original 2D array
 * and the second 2D array that contains the artists and the number of times 
 * they appear on the Top 200 chart. All of which is printed onto a file.
 * 
 * I had trouble with removing duplicates from the array that contained the
 * artist name and the number of times they appeared on the Chart. I was able
 * to fix this problem by introducing a boolean variable that would determine
 * if a artist and the number of times they appear on the chart would be placed
 * into the array that contained that information. I essentially took the name
 * and searched the 2D array for that name and if it was there, the boolean
 * variable would be true and would skip the next block of code that would of
 * added it in.
 * 
 * I also had trouble with the bubblesort used in alphabetizing the 2D array
 * but I was able to fix my mistakes by tracing through the code and realizing
 * that the values I was putting for the string variables relating to the arrays
 * were making it go out of bounds.
 * 
 * Additional Info about the code:
 * The line holdFirstLine will contain the heading of the file. Meaning that it 
 * will hold Position,Track Name,Artist,Stream,URL.
 */
package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintStream;

public class Lab2 {

    public static void main(String[] args) throws FileNotFoundException {
        
        //[] <-Rows [] <- Columns 
        String [][] spotifyList = new String [200][5];
        
        // Location of the file that the Scanner will be scanning.
        File file = new File("C:\\Users\\Nick\\Desktop\\"
                + "regional-global-daily-latest-copy-filetest.txt");
        Scanner sc = new Scanner(file);
        // The name of the text file that all data will be printed too.
        PrintStream ps = new PrintStream("Artists-WeekOf-09-05-2020.txt");
        // holdFirstLine will hold the header of the file so its not included
        // in the array but is somewhere that we can access later on if needed.
        String holdFirstLine = sc.nextLine();
        
        // This method uses the scanner "sc" to scan the text file from the CSV
        // file and populates the 2D array with its content.
        populate2DArrayFromCSVFile(spotifyList, sc);
             
        // This array will be used to create a 2D array which will have the
        // artist name in column zero and the the number of times they appear
        // in the top 200 chart in column 1.
        String artist [] = new String[200];     
        
        // This method takes artist name from the 2D array and copies it into 
        // this single dimension array. This method also contains a print
        // option to see the content of it but it is commented out by default.
        populateArtistArray(artist, spotifyList, ps);
        
        // The creation of the second 2D array that will contain the artist in
        // the Top 200 Chart and the amount of times they appear on there.
        String [][] artistCount = new String [200][2];
        
        int index;
        // This method populates the second 2D array with the artist and number
        // of times they appear on the list. This will return the logical length
        // of this array as this array was created with 200 rows if in case,
        // 200 different artist made it on this Chart
        index = artistAndTheNumberOfApperancesArray(artist, artistCount);
        
        //Alphabetizes the Array with the artist and the number of times they
        //appear.
        alphabetizeApperanceArray(artistCount, index);
        
        ps.println("The artist that appear on the Top 200 Chart and the amount"
                + " of times they appear on the chart:\n");
        
        // Prints the artistCount array which contains the artist and the
        // amount of times they showed up in the list.
        printAlphabetizedApperanceArray(artistCount, index, ps);
        
        ps.println("\nThe Top 200 chart with postion, track name, Artist, Streams"
                + " and URL:\n");
        // Prints the original 2D array that contains all the info from the Top
        // 200 chart into a text file.
        print_spotifyList(spotifyList, ps);    
    } // END main
    
    // Populates the 2D array with the content from the csv file which is read 
    // by the scanner. This method uses a string method .split so each line can
    // be cut up into indivdiual strings which can be stored into the array.
    public static void populate2DArrayFromCSVFile(String[][]spotifyList, 
            Scanner sc){
        int row = 0;
        String line = "";
        
        while(sc.hasNextLine()){
            line = sc.nextLine();
            //.split(","); ",(?=([^\"]|\"[^\"]*\")*$)"
            String [] seperate = line.split(",(?=([^\"]|\"[^\"]*\")*$)");
            
            for(int i = 0; i < 5; i++) {
                spotifyList[row][i] = seperate[i];               
            }
            row = row + 1;
        }
    }
    
    // Will print the 2D array with the content from the csv file into a text
    // file.
    public static void print_spotifyList(String[][]spotifyList, PrintStream ps){
        for(int j = 0; j < 200; j++) {
            for(int k = 0; k < 5; k++) {
                ps.print(spotifyList[j][k]);
                ps.print(" ");
            }
            ps.println();
        }
    }
    
    // Will fill the artist array with all 200 artist from the 2D array which
    // was populated from the CSV file. The artist array will contain dupes.
    // Also contains a built in print method when populating the array. This
    // print method is just to verify if the artist array is getting all 200
    // artist, it is by default commented out.
    public static void populateArtistArray (String[] artist, 
            String[][]spotifyList, PrintStream ps){
        //ps.println("Names of Artist in the top 200 for this week.\n");
        for(int m = 0; m < 200; m++){
            artist[m] = spotifyList[m][2];
            // Below is just to test if the artist array has the correct 
            // content
            // ps.println(artist[m]);
        }
    }
    // This method uses the artist array to populate the artistCount array with
    // the artist name and the number of times they appear on the chart. The
    // method takes the first artist name and counts how many times they appear,
    // then it looks into the artistCount array to see if that person is already
    // there. If they are then they are not added in so that artistCount won't
    // contain any duplicates.
    public static int artistAndTheNumberOfApperancesArray (String[] artist,
            String[][] artistCount){
        
        boolean isItACopy = false;
        int count = 0;
        int artCountIndex = 0;
        
        for (int a = 0; a < 200; a++) {
            String artistName = artist[a];
            for(int b = 0; b < 200; b++) {
                if(artistName.equals(artist[b])) {
                    count++;
                }
            }
            for(int c = 0; c < 200; c++) {
                if(artistName.equals(artistCount[c][0])) {
                    isItACopy = true;
                }
            }
            // At this point the method will know if the artist is in the
            // artistCount array. If it is then the if block is skipped and if
            // the artist is not in the artistCount array then their information
            // is added in.
            if(isItACopy == false) {
                artistCount[artCountIndex][0] = artistName;
                artistCount[artCountIndex][1] = Integer.toString(count);
                artCountIndex++;      
            }
            isItACopy = false;
            count = 0;       
        }
        
        return artCountIndex;
    }
    
    // This method alphabetizes the artistCount array via bubblesort.
    public static void alphabetizeApperanceArray(String [][] artistCount,
            int artCountIndex) {
        
        int pass, j;
        String [][] temp = new String [1][2];
        boolean switched = true;
        
        for(pass = 0; pass < artCountIndex - 1 && switched; pass++) {
            switched = false;
            for(j = 0; j < artCountIndex - pass - 1; j++) {
                if(artistCount[j][0].compareToIgnoreCase(artistCount[j + 1][0]) 
                        > 0) {
                    switched = true;
                    temp[0][0] = artistCount[j][0];
                    temp[0][1] = artistCount[j][1];
                    
                    artistCount[j][0] = artistCount[j+1][0];
                    artistCount[j][1] = artistCount[j+1][1];
                         
                    artistCount[j+1][0] = temp[0][0];
                    artistCount[j+1][1] = temp[0][1];
                }
            }
        }
    }
    
    // This method will print the alphabetized artistCount array into the text
    // file.
    public static void printAlphabetizedApperanceArray(String[][] artistCount,
            int artCountIndex, PrintStream ps) {
        for (int p = 0; p < artCountIndex; p++) {
            for (int q = 0; q < 2; q++) {
                ps.print(artistCount[p][q]);
                ps.print("  ");  
            }
            ps.println();
        }
    }    
} // END lab2 class
