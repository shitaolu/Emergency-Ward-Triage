package A2Q2S;

import java.util.*;

/**
 * Adaptable priority queue using location-aware entries in a min-heap, based on
 * an extendable array.  The order in which equal entries were added is preserved.
 *
 * @author jameselder
 * @param <E> The entry type.
 */
public class APQ<E> {

    private final ArrayList<E> apq; //will store the min heap
    private final Comparator<E> comparator; //to compare the entries
    private final Locator<E> locator;  //to locate the entries within the queue

    /**
     * Constructor
     * @param comparator used to compare the entries
     * @param locator used to locate the entries in the queue
     * @throws NullPointerException if comparator or locator parameters are null
    */
    public APQ(Comparator<E> comparator, Locator<E> locator) throws NullPointerException {
        if (comparator == null || locator == null) {
            throw new NullPointerException();
        }
        apq = new ArrayList<>();
        apq.add(null); //dummy value at index = 0
        this.comparator = comparator;
        this.locator = locator;
    }

    /**
     * Inserts the specified entry into this priority queue.
     *
     * @param e the entry to insert
     * @throws NullPointerException if parameter e is null
     */
    public void offer(E e) throws NullPointerException {
        if (e == null) {
            throw new NullPointerException();
        }
        apq.add(e); // append to array
        locator.set(e, size()); //update location
        upheap(size()); //move to correct location in heap
    }

   /**
     * Removes the entry at the specified location.
     *
     * @param pos the location of the entry to remove
     * @throws BoundaryViolationException if pos is out of range
     */
    public void remove(int pos) throws BoundaryViolationException {
        E e;
        if (pos < 1 || pos > size()) {
            throw new BoundaryViolationException();
        }

        locator.set(apq.get(pos), 0); //null location of removed entry
        e = apq.remove(size()); //remove last entry
        if (pos != size()+1) { //if element to be removed was not last
            apq.set(pos, e); //store last entry at pos
            locator.set(e, pos); //update location
            downheap(pos); //move to correct location
        }
    }


   /**
     * Removes the entry at the specified location.
     *
     * @param e1 the entry to remove
     */
    public void remove(E e1) {
        E e2;
        int pos;
        pos = locator.get(e1); //get location of entry to be removed
        locator.set(e1, 0); //null location of removed entry
        e2 = apq.remove(size()); //remove last entry
        if (pos != size() + 1) { //if element to be removed was not last
            apq.set(pos, e2); //store last entry at pos
            locator.set(e2, pos); //update location
            downheap(pos); //move to correct location
        }
    }

   /**
     * Removes the first entry in the priority queue.
     */
    public E poll() {
        E e1, e2;
        if (isEmpty()) {
            return null;
        }
        e1 = apq.get(1); //Get first entry
        e2 = apq.remove(size()); //Remove last entry
        if (size() > 0) { //Otherwise last entry was first entry
            apq.set(1, e2); //Store removed last entry at first location
            locator.set(e2, 1); //Update location
            downheap(1); //Move to correct location
        }
        locator.set(e1, 0); //Zero location of removed entry
        return e1;
    }

  /**
     * Returns but does not remove the first entry in the priority queue.
     */
     public E peek() {
        if (isEmpty()) {
            return null;
        }
        return apq.get(1);
    }

   public boolean isEmpty() {
        return (size() == 0); 
    }

    public int size() {
        return apq.size() - 1; //dummy node at location 0
    }


    /**
     * Shift the entry at pos upward in the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
    private void upheap(int pos) {
        int parentPos;
        if (pos > 1) { //Otherwise already at top
            parentPos = pos / 2;
            if (comparator.compare(apq.get(pos), apq.get(parentPos)) < 0) { //do not move if tie.
                swap(pos, parentPos);
                upheap(parentPos);
            }
        }
    }

    /**
     * Shift the entry at pos downward in the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
    private void downheap(int pos) {
        int childPos, rightChildPos;
        childPos = 2 * pos; //left child
        if (childPos <= size()) { //Otherwise already at bottom.
            rightChildPos = 2 * pos + 1;
            if (rightChildPos <= size()) { //right child exists
                if (comparator.compare(apq.get(childPos), apq.get(rightChildPos)) > 0) { //choose left on tie
                    childPos = rightChildPos; //right child is smaller than left
                }
            }
            if (comparator.compare(apq.get(pos), apq.get(childPos)) >= 0) { //move down on tie
                swap(pos, childPos);
            }
            downheap(childPos);
        }
    }

    /**
     * Swaps the entries at the specified locations.
     *
     * @param pos1 the location of the first entry 
     * @param pos2 the location of the second entry 
     */
    private void swap(int pos1, int pos2) {
        E e1, e2;
        
        e1 = apq.get(pos1);
        e2 = apq.get(pos2);
        apq.set(pos1, e2);
        locator.set(e2, pos1); //update location
        apq.set(pos2, e1);
        locator.set(e1, pos2); //update location
    }
}