/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 25, 2020
 * 
 * Description: In this lab our VIP requested that he/she be given the song 
 * names in ascending order so they could be listened too. To do this we had
 * the choice of using Stacks or Queues or a combination of both to achieve
 * this. I opted for using stacks in my code.
 * 
 * There are some steps before we can use the stack so I made a class SongTitle,
 * which is an object that contained the song title. This object is then pushed
 * into the stack which has its own class. The 12 weeks were processed using
 * arrays which lead to an array with unique unsorted song names. These song
 * names would be turned into objects then pushed into the stack.
 * 
 * The only problem I encountered during this lab was trying to figure out
 * how to sort a Stack. I was eventually able to do this by looking online for
 * help (sources will be in the README file on git). The way I chose to sort the
 * stack was with another stack. This process involved checking the orginal stack
 * for values and comparing it with the tempStack then sorting. If the stack
 * value was bigger than the top value of the tempStack it was popped from the
 * Stack and pushed onto the tempStack. If the value was smaller, we would pop
 * from the tempStack until we found a value that was smaller than the popped
 * value. In which we would then push it into the tempStack and continue the
 * process all over again.
 */
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab4 {

    public static void main(String[] args) throws FileNotFoundException {
        
        // [Rows] [Column]
        // This creates a 2D array that will store the contents from the CSV files.
        String [][] spotifyList = new String [2400][5];
        // This array contains the names of the text files that I will be using
        // for this lab. These names are used later on in the code so I could
        // loop a menthod for populating the 2D array.
        String [] fileNames = {"Week1Text.txt","Week2Text.txt", "Week3Text.txt", "Week4Text.txt",
            "Week5Text.txt", "Week6Text.txt", "Week7Text.txt", "Week8Text.txt", "Week9Text.txt", 
            "Week10Text.txt", "Week11Text.txt", "Week12Text.txt"};
        Scanner sc;
        int row = 0;
        String myFileName = fileNames[0];
        
        // This for loop is looping a method which will read in from the text
        // files into the 2D array using the fileNames Array above.
        for(int i = 0; i < fileNames.length; i ++) {
            row = populate2DArray(spotifyList, row, fileNames[i]);
        }
        
        // This array will contain the songs from the 2D array without dupes/
        String [] songs = new String[2400];
        
        // This method returns the logical count which represents the number
        // of unique songs in the array. This value is assigned to
        // songsLogicalCount variable.
        int songsLogicalCount = populateSongsWithNoDupes(spotifyList, songs);
        
        // Making the stack that will contain the unique, unsorted song names.
        LinkStack stack = new LinkStack();
        
        // Fills in the Stack with unique song names. Sorting comes after.
        populateLinkedListStack(songs, songsLogicalCount, stack);
        
        // This method will sort the stack (see method below)
        sortStack(stack);
        
        // Prints the stack
        stack.printStack();
    }// END main
    
    // This method populated the 2D array by looking at each CSV file that was
    // converted into a .txt file. Then we use a String method .split to get
    // all the data from the .txt file into the 2D array. We used a loop in
    // our usage of this method so we do not have to use this method 12 times.
    // The name of the file is looped and they come from the fileNames array.
    public static int populate2DArray(String[][] spotifyList, int row, 
            String myFileName) throws FileNotFoundException{
        File myFile = new File("C:\\Users\\Nick\\Desktop\\"
                + "Fiscal Quarter Song Name txt\\" + myFileName);
        
        Scanner sc = new Scanner(myFile);
        String line = "";
        
        while(sc.hasNextLine()){
            line = sc.nextLine();
            String [] seperate = line.split(",(?=([^\"]|\"[^\"]*\")*$)");    
            for(int i = 0; i < 5; i++) {
                spotifyList[row][i] = seperate[i];               
            }
            row = row + 1;
        }
        return row;
    }// END populate2DArray
    
    // This method takes in the 2D array and prints it. This method is not used
    // in this code. The only use for this method is to see if the 2D array has
    // all 2400 rows and 5 columns which the respective data.
    public static void print2DArray(String [][] spotifyList){
        for(int i = 0; i < spotifyList.length;i++){
            for(int j = 0; j < 5; j++) {
                System.out.print(spotifyList[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }// END print2DArray
    
    // This method looks at the 2D array for the song names and places them
    // into the song array if they are not already in the song array. This is
    // done primarily on the backbone of the boolean variable. If 
    // isItACopy = false was not at the end of the loop then unique songs would
    // not be added into the song array.
    public static int populateSongsWithNoDupes(String[][] spotifyList, 
            String [] songs){
        //Array index 1 for the song name in the 2D array
        int column = 1;
        int songIndex = 0;
        boolean isItACopy = false;
        for(int i = 0; i < spotifyList.length; i++) {
            String songName = spotifyList[i][column];
            for(int j = 0; j < songs.length; j++) {
                if(songName.equals(songs[j])) {
                    isItACopy = true;
                }
            }
            if (isItACopy == false) {
                songs[songIndex] = songName;
                songIndex++;
            }
            isItACopy = false;
        }
        return songIndex;
    }// END populateSongsWithNoDupes
    
    // This method looks in the songs array for its elements which is then used
    // to construct a SongTitle object which is then pushed into the stack.
    // These objects go into the stack unsorted.
    public static void populateLinkedListStack(String [] songs, 
            int songsLogicalCount, LinkStack stack) {
        
        for(int i = 0; i < songsLogicalCount;i ++) {
            SongTitle songFromArray = new SongTitle(songs[i]);
            stack.push(songFromArray);
        }
    }// END populateLinkedListStack
    
    // This method sorts the stack by using a tempStack to move back and forth
    // objects in the stack. This implementation works by putting the smallest
    // value at the bottom of the tempStack and the largest value at the top.
    // When the orginal stack is empty we pop from the tempStack and push into
    // the orginal stack and thus we have a sorted stack.
    public static void sortStack (LinkStack stack){
        LinkStack tempStack = new LinkStack();
        
        while(!stack.isEmpty()){
            String stackPeek = stack.pop().getSongName();
            while(!tempStack.isEmpty() && stackPeek.
                    compareToIgnoreCase(tempStack.peek().getSongName()) < 0) {
                stack.push(tempStack.pop());
            }
            tempStack.push(new SongTitle(stackPeek));
        }
        while(!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    } // END sortStack
}// END Lab4
