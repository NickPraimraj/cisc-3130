/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 3, 2020
 * 
 * Description: This class is ArtistNode class which mimics a node class for a
 * doubly LinkedList. In this class the ArtistNode contains only one thing, the
 * Artist object, which contains the artist name. Each ArtistNode also contains
 * a next and previous field which points to the nodes that come before and
 * after it. We need this class because the ArtistLinkedList class will be 
 * storing the nodes in it.
 */
package lab3;

public class ArtistNode {
    
    private Artist artist;
    private ArtistNode next;
    private ArtistNode previous;
    
    public ArtistNode(Artist artist){
        this.artist = artist;
    }
    
    public Artist getArtist(){
        return artist;
    }
       
    public void setArtist(Artist artist){
        this.artist = artist;
    }
    
    public ArtistNode getNext(){
        return next;
    }
    
    public void setNext(ArtistNode next) {
        this.next = next;
    }
    
    //Uses the Artist class toString in this methods implementation
    @Override
    public String toString() {
        return artist.toString();
    }
    
    public ArtistNode getPrevious(){
        return previous;
    }
    
    public void setPrevious(ArtistNode previous) {
        this.previous = previous;
    }
}
