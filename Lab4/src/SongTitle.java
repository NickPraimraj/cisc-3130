/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 25, 2020
 * 
 * Description: This class contains methods such as a constructor, 
 * getters/setters for the object and a toString method. The object made by
 * this class only holds a string which is the song name. Then that same object
 * is pushed into a stack that is implemented via LinkedList using the built in
 * class for LinkedList.
 */
package lab4;

public class SongTitle {
    
    private String songName;
    
    public SongTitle(String songName) {
        this.songName = songName;
    }
    
    public String getSongName(){
        return songName;
    }
    
    public void setSongName(String songName) {
        this.songName = songName;
    }
    
    @Override
    public String toString(){
        return "Song title: " + songName;
    }
}
