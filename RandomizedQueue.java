import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
   // Field Declaration
   private int size;       
   private Item[] queue;    
   
   // construct an empty randomized queue
   public RandomizedQueue() {
      // create a item array with length 1
      queue = (Item[]) new Object[1];
      size = 0;
   }

   // is the randomized queue empty?
   public boolean isEmpty() {
      return size == 0;
   }

   // return the number of items on the randomized queue
   public int size() {
      return size;
   }

   // add the item
   public void enqueue(Item item) {
      if (item == null) throw new IllegalArgumentException();
      if (size == queue.length)   resize(2 * queue.length);       // the new array will be twice the size;
      queue[size++] = item;
   }

   // remove and return a random item
   public Item dequeue() {
      // if empty: no such element exc.
      if (isEmpty())   throw new NoSuchElementException();
      
      // get a random number ranging from 0 upto size, then remove that element
      int index = StdRandom.uniform(0, size);
      Item item = queue[index];
      shift(index);                             // this method will swap the last element with the removed element
      if (size == (queue.length / 4))   resize(queue.length / 4);     // the new size will be a quarter of the previous
      return item;
   }

   // return a random item (but do not remove it)
   public Item sample() {
      // if empty: no such element exc.
      if (isEmpty())   throw new NoSuchElementException();
      
      int index = StdRandom.uniform(0, size);
      Item item = queue[index];
      return item;
   }

   // this method resizes the array
   private void resize(int newSize) {
      Item[] temp = (Item[]) new Object[newSize];
      for (int i = 0; i < size; i++)   temp[i] = queue[i];
      queue = temp;
   }
   
   // this method shifts elements one place left
   private void shift(int start) {
      queue[start] = queue[size - 1];
      queue[--size] = null;
   }
   
   // return an independent iterator over items in random order
   public Iterator<Item> iterator() { 
      return new RandomQueueIterator();
   }

   // This is the inner RandomQueueIterator
   private class RandomQueueIterator implements Iterator<Item> {
      private int left = 0;
      private final int[] indices;
      
      public RandomQueueIterator() {
         indices = new int[size];
         for (int i = 0; i < size; i++)
            indices[i] = i;
         StdRandom.shuffle(indices);
      }
      
      // this is the has next method
      public boolean hasNext() {
         return (size - left) != 0;
      }
      
      // this is the remove method, not in use currently
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      // this is the next method, returns the next element 
      public Item next() {
         if (!hasNext())   throw new NoSuchElementException();
         return queue[indices[left++]];
      }
   }
   
      public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(rq.dequeue());
            else
                rq.enqueue(s);
        }
    } // unit testing (optional)
}