/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 3, 2020
 * 
 * Description: This class behaves like a LinkedList which has nodes that point
 * to the node previous and to the node after it. This class in particular 
 * stores the information of the head and tail (size is used to help test the 
 * program). This class also contains all of the methods in which the nodes are
 * added into the LinkedList and how the LinkedList is printed to a file. It
 * also contains a printList() method that will print to the terminal if that
 * is required. This method was not used in the main method so that is why you
 * don't see anything in the terminal besides build successful.
 */

package lab3;

import java.io.PrintStream;

public class ArtistLinkedList {
    
    private ArtistNode head;
    private ArtistNode tail;
    // Size is used to test the number of nodes in the list when we copy the 
    // the info from an array into the linked list. Should be 200 for this lab.
    private int size;
    
    // Dont need a contructor setting head/tail to null as they are already set 
    // to null. But it can be implemented for clarity sake.
    
    public void addToEnd(Artist artist) {
        ArtistNode node = new ArtistNode(artist);
        if(tail == null){
            head = node;
        }else{
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;
        size++;
    }
    
    public void printList(){
        ArtistNode current = head;
        System.out.println("HEAD -> Tail");
        while(current != null){
            System.out.println(current);
            current = current.getNext();
        }
    }
    
    public void printListIntoFile(PrintStream ps) {
        ArtistNode current = head;
        ps.println("HEAD -> Tail");
        while(current != null) {
            ps.println(current.getArtist().getArtistName());
            current = current.getNext();
        }       
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }  
}
