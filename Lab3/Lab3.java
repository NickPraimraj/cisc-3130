/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 3, 2020
 * 
 * Description: This program is similar to Lab 2 in which we had to produce a
 * file for the executive containing all the artist that made it on the Top 200
 * Chart on Spotify. The only difference with this Lab is that instead of using
 * arrays we have to use linked list to do it.
 * 
 * I chose to use a doubly linked list for the ease of traversing the nodes. In
 * addition I had to make an Artist class which just contains the artist name
 * and stores it as an Artist Object. The program contains a ArtistNode class
 * which takes the Artist object and turns it into a node object with next and
 * previous fields. It also contains a ArtistLinkedList class which is the
 * LinkedList I implemented for this lab. It contains most of the methods 
 * important to make all the data go to a file. 
 * 
 * I did not have that much trouble in making this project. However it did take
 * me some time to figure out how to print the LinkedList into the file but it
 * only took a few minutes to realize that I had to make such a method in the
 * ArtistLinkedList class. While doing this project I learned how to implement 
 * and use my own LinkedList in the process.
 * 
 * It is worth stating that one can use the built in JDK LinkedList as well but
 * making your own LinkedList allows for more control over how you can handle 
 * the specific data that you have.
 * 
 * Additional Info about the code:
 * The String holdFirstLine will contain the heading of the file. Meaning that 
 * it will hold Position,Track Name,Artist,Stream,URL.
 */
package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Lab3 {

    public static void main(String[] args) throws FileNotFoundException {
        //[] <-Rows [] <- Columns
        // This creates a 2D array that will store the content of the CSV file.
        String [][] spotifyList = new String [200][5];
        
        // Location of the file that the Scanner will be scanning.
        File file = new File("C:\\Users\\Nick\\Desktop\\"
                + "regional-global-daily-latest-copy-filetest.txt");
        Scanner sc = new Scanner(file);
        
        // The name of the text file that the artist names will be printed too.
        PrintStream ps = new PrintStream("ArtistsSorted-WeekOf-09-05-2020.txt");
        
        // holdFirstLine will hold the header of the file so its not included
        // in the array but is somewhere that we can access later on if needed.
        String holdFirstLine = sc.nextLine();
        
        // This method uses the scanner "sc" to scan the text file from the CSV
        // file and populates the 2D array with its content.
        populate2DArrayFromCSVFile(spotifyList, sc);
        
        // This array will contain just the artist from the 2D array. It will
        // also contain duplicate names.
        String artist [] = new String[200];
        
        // This method takes artist name from the 2D array and copies it into 
        // this single dimension array. This method also contains a print
        // option to see the content of it but it is commented out by default.
        populateArtistArray(artist, spotifyList, ps);
        
        // This array will contain all the unique artist that appear on the
        // Top 200 chart of soptify. It has 200 elements just in case the Top
        // 200 Chart has 200 unique artist in it.
        String uniqueArtist [] = new String[200];
        int index;
        
        // This index contains the logical count of the uniqueArtist array. We
        // take note of this number so we don't add in null values when sending
        // the artist names into the LinkedList and for printing the names.
        index = removeDupes(artist, uniqueArtist);
        
        // The executive requested an alphabetized file containing the artist
        // name and this method alphabetizes the uniqueArtist array up to the
        // index point. Otherwise we would be sorting null's which is not 
        // benefical for anyone.
        alphabetizeUniqueArray(uniqueArtist, index);
        
        // This creates the LinkedList of which all the unique artist will be
        // stored in.
        ArtistLinkedList list = new ArtistLinkedList();
        
        // This will populate the LinkedList with the unique names from the Top
        // 200 List.
        populateDoublyLinkedList(uniqueArtist, index , list);
        
        // The following four lines will print out the content of the LinkedList
        // into the file.
        ps.println("There are " + list.getSize() + " unique Artist in the Top "
                + "200 Chart.");
        ps.println();
        list.printListIntoFile(ps);     
    }// END main
    
    // Populates the 2D array with the content from the csv file which is stored
    // in a .txt file. The .txt file is then read by the scanner. This method 
    // uses a string method .split so each line can be cut up into indivdiual 
    // strings which can be stored into the array.
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
    }// END populate2DArrayFromCSVFile
    
    // This method takes in the uniqueArtist array, its index and the LinkedList
    // so that the LinkedList can be popluated with the sorted names. We need
    // the index parameter because without it we would be sending null values
    // into the LinkedList
    public static void populateDoublyLinkedList(String[] uniqueArtist,int index, 
            ArtistLinkedList list) {
        
        String name = "";
        for (int i = 0; i < index; i++) {
            name = uniqueArtist[i];
            // Makes the artist object which is then used by the 
            // ArtistLinkedList class to add a node into the list. In this lab
            // we add the node to the end of the list so it would be
            // alphabetized.
            Artist inTheTop200 = new Artist(name);
            // Now we send the Artist object into the ArtistLinkedList via the
            // method .addToEnd
            list.addToEnd(inTheTop200);
        }     
    }// END populateDoublyLinkedList
   
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
    }// END populateArtistArray
    
    // This method uses the artist array to populate the uniqueArtist array with
    // the artist names that appeared on the Top 200 chart. The method takes the
    // first artist name and looks into the uniqueArtist array to see if that 
    // person is already there. If they are in the uniqueArtist array then they 
    // will not be added in, so that the uniqueArtist array won't contain any 
    // duplicates.
    public static int removeDupes (String[] artist, String[] uniqueArtist){
        
        boolean isItACopy = false;
        int uniqueArtistIndex = 0;
        
        for (int a = 0; a < 200; a++) {
            String artistName = artist[a];
            for(int b = 0; b < 200; b++) {
                if(artistName.equals(uniqueArtist[b])) {
                    isItACopy = true;
                }
            }
            // At this point the method will know if the artist is in the
            // uniqueArtist array. If the name is in the uniqueArtist array then
            // the if block is skipped. If the artist is not in the uniqueArtist
            // array, then their name is added in.
            if(isItACopy == false) {
                uniqueArtist[uniqueArtistIndex] = artistName;
                uniqueArtistIndex++;      
            }
            isItACopy = false;       
        }
        return uniqueArtistIndex;
    }// END removeDupes
    
    // This method alphabetizes the uniqueArtist array via bubblesort up to the
    // index value. Past that point the array only contains null values, if the
    // uniqueArtist array does not have 200 elements in it.
    public static void alphabetizeUniqueArray(String [] uniqueArtist,
            int uniqueArtistIndex) {
        
        int pass, j;
        String [] temp = new String [1];
        boolean switched = true;
        
        for(pass = 0; pass < uniqueArtistIndex - 1 && switched; pass++) {
            switched = false;
            for(j = 0; j < uniqueArtistIndex - pass - 1; j++) {
                if(uniqueArtist[j].compareToIgnoreCase(uniqueArtist[j + 1]) 
                        > 0) {
                    switched = true;
                    temp[0] = uniqueArtist[j];
                    
                    uniqueArtist[j] = uniqueArtist[j+1];
                         
                    uniqueArtist[j+1] = temp[0];
                }
            }
        }
    }// END alphabetizeUniqueArray
}// End Lab3
