/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 3, 2020
 * 
 * Description: This class will contain the blueprints for an object of type
 * Artist. The only information the Artist object contains is the artist name.
 * This class is needed to make a node that will contain the Artist object which
 * contains the artist name.
 */
package lab3;

public class Artist {
    
    private String artistName;
    
    public Artist (String artistName) {
        this.artistName = artistName;
    }
    
    public String getArtistName(){
        return artistName;
    }
    
    public void setArtistName(String artistName){
        this.artistName = artistName;
    }
    
    @Override
    public String toString() {
        return artistName;
    }
}
