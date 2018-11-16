package A2Q2;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
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
    	if (e == null){
    		throw new NullPointerException();
    	}
    	apq.add(e);
    	locator.set(e, apq.size()-1);
    	if(apq.size()>2){
    		upheap(locator.get(e)); 
    	}
    	 
    }

   /**
     * Removes the entry at the specified location.
     *
     * @param pos the location of the entry to remove
     * @throws BoundaryViolationException if pos is out of range
     */
	public void remove(int pos) throws BoundaryViolationException {
		if (pos > apq.size()-1 || pos<=0) {
			throw new BoundaryViolationException();
		}
		else if(pos == apq.size()-1){   
			apq.remove(apq.size() - 1);
		}
		else{
			apq.set(pos, apq.get(apq.size()-1));
			locator.set(apq.get(apq.size()-1), pos);
			apq.remove(apq.size()-1);
			if (2 * pos <(apq.size()-1)) {     //���Ҷ���child
				if (comparator.compare(apq.get(pos / 2), apq.get(pos)) > 0) {
					upheap(pos);
				} 
				else if (comparator.compare(apq.get(pos), apq.get(pos * 2)) > 0
						|| comparator.compare(apq.get(pos), apq.get(pos * 2 + 1)) > 0) {
					downheap(pos);}
			}
			
			else if(2*pos == ( apq.size()-1)){  //ֻ��һ��left child
				downheap(pos);
			}

			else if(2*pos > (apq.size()-1)){
				upheap(pos);
			}
		}
		
	}

   /**
     * Removes the first entry in the priority queue.
     */
    public E poll() {
    	if (isEmpty()){
    		return null;
    	}
    	E temp = apq.get(1);
    	apq.set(1, apq.get(apq.size()-1));
    	locator.set(apq.get(apq.size()-1), 1);
    	apq.remove(apq.size()-1);
    	downheap(1);
    	return temp;

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
    		while(pos >= 2 && comparator.compare(apq.get(pos/2), apq.get(pos)) > 0){
        		swap(pos,pos/2);
        		pos = pos/2;
        	}
    	}

   

    /**
     * Shift the entry at pos downward in the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
	private void downheap(int pos) {
		// �����������һ�֣����û�У��ұ�Ҳû�У��Ǿ�ʲô����Ҫ��,��Ϊ������Ѿ��������
		// �ڶ��֣�����У��ұ�û��
		// �����֣�����У��ұ���

		while (2 * pos < apq.size()-1) { //������child
			if (comparator.compare(apq.get(pos), apq.get(pos * 2)) > 0) { //parent ���� left child
				if (comparator.compare(apq.get(2 * pos), apq.get(2 * pos + 1)) >= 0) { //�Ƚ���ߺ��ұߣ������ߴ󣬺��ұ߻�
					swap(pos, pos * 2 + 1);
					pos = pos * 2 + 1;
				} 
				else if (comparator.compare(apq.get(2 * pos), apq.get(2 * pos + 1)) < 0) {//����ұ߱Ƚϴ󣬺���߻�
					swap(pos, pos * 2);
					pos = pos * 2;
				}
			} 
			
			else if (comparator.compare(apq.get(pos), apq.get(pos * 2)) <= 0) { // parent С�� left child
				if (comparator.compare(apq.get(pos), apq.get(pos * 2 + 1)) > 0) { //parent ���� right child,�����ұߵ�
					swap(pos, pos * 2 + 1);
					pos = pos * 2 + 1;
				}
			}
		}
		
		if (2 * pos == apq.size()-1) { //ֻ��left child
			if (comparator.compare(apq.get(pos), apq.get(pos * 2)) >= 0) {
				swap(pos, pos * 2);
				pos = pos * 2;
			}
		}
	}
    	

    /**
     * Swaps the entries at the specified locations.
     *
     * @param pos1 the location of the first entry 
     * @param pos2 the location of the second entry 
     */
    private void swap(int pos1, int pos2) {
    	E temp1 = apq.get(pos1);
    	E temp2 = apq.get(pos2);
    	locator.set(temp1, pos2);
    	locator.set(temp2, pos1);
    	apq.set(pos1, temp2);
    	apq.set(pos2, temp1);
    	
        //implement this method
    }
    
}