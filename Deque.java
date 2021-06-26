import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// linked list implementation
// Ammaar Iftikhar

public class Deque<Item> implements Iterable<Item> {
   
   // Field Declaration
   private Node first;
   private Node  last;
   private int   size;
  
   // construct an empty deque
   public Deque() {
      // initialisation
      first = null;
      last  = null;
      size  =    0;
   }
   
   // is the deque empty?
   public boolean isEmpty() {
      return size == 0;
   }
      
   // return the number of items on the deque
   public int size() {
      return size;
   }
      
   // add the item to the front
   public void addFirst(Item item) {
      // check the client argument 
      if (item == null) throw new IllegalArgumentException();
      
      Node temp = new Node();
      temp.node = item;
      size++;
      
      // add to list 
      if (size == 1) {
         first = temp;
         last  = temp;
         return;
      }
      
      temp.next = first;
      first.prev = temp;
      
      first = temp;
}
      
   // add the item to the back
   public void addLast(Item item) {
      // check the client argument 
      if (item == null) throw new IllegalArgumentException();
      
      Node temp = new Node();
      temp.node = item;
      size++;
      
      // add the end
      if (size == 1) {
         first = temp;
         last  = temp;
         return;
      }
      
      temp.prev = last;
      last.next = temp;
      
      last = temp;
   }
      
   // remove and return the item from the front
   public Item removeFirst() {
      // check the dequeue for emptiness
      if (first == null)   throw new NoSuchElementException();
      
      // Save the first item
      Item temp  = first.node;
      size--;
      
      // clear the queue if empty
      if (size == 0) {
         last  = null;
         first = null;
         return temp;
      }
      
      // delete and change the first pointer
      first  = first.next;
      first.prev = null;
      
      return temp;
   }
      
   // remove and return the item from the back
   public Item removeLast() {
      if (first == null)   throw new NoSuchElementException();
      
      // copy the last item 
      Item temp = last.node;
      size--;
      
      // clear the queue if empty
      if (size == 0) {
         last  = null;
         first = null;
         return temp;
      }
         
      // delete and change the first pointer
      last      = last.prev;
      last.next = null;
      
      return temp;
   }
      
   // return an iterator over items in order from front to back
   public Iterator<Item> iterator() {
      return new DequeIterator(first);
   }
   
   private class DequeIterator implements Iterator<Item> {
      public Node current;
      
      DequeIterator(Node fir) {
         current = fir;
      }
      
      public boolean hasNext() {
         return current != null;
      }
      
      public void remove() {
         throw new UnsupportedOperationException("The method is not in use.");
      }
      
      public Item next() {
         if (current == null)   throw new NoSuchElementException("The list is over!");
         
         Item temp = current.node;
         current = current.next; 
         return temp;
      }
   }
   
   // This is the private inner node class
   private class Node {
      public Item node;              // the value of the current node
      public Node next;              // the element on the right side
      public Node prev;              // the element on the left side    
   }
   
   // this is the main method to test the deque data structure
   public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(deque.removeLast());
            else if (s.equals("--"))
               StdOut.print(deque.removeFirst());
            else
                deque.addFirst(s);
      }
   }
}