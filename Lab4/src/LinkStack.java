/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since October 25, 2020
 * 
 * Description: This class contains methods for the Stack which uses the 
 * implementation from the built in LinkedList class in the JDK with some 
 * methods that I wrote so the stack would be pushing/popping/peeking for the
 * right object types. That being of object type SongTitle.
 * 
 * Why use a LinkedList implementation over the stack class from the JDK?
 * The Stack class in the JDK is not consistent(from oracle). It was
 * recommended to use the Deque interface but LinkedList use the Deque
 * interface in its implementation so I went with that.
 */
package lab4;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkStack {
    
    private LinkedList<SongTitle> stack;
    
    public LinkStack(){
        stack = new LinkedList<>();
    }
    
    public void push (SongTitle songTitle) {
        stack.push(songTitle);
    }
    
    public SongTitle pop() {
        return stack.pop();
    }
    
    public SongTitle peek() {
        return stack.peek();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public void printStack() {
        ListIterator<SongTitle> iterator = stack.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
