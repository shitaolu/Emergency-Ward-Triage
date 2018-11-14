package A2Q2S;

import java.util.*;

/**
 * Triages patients in Emergency Ward according to medical priority and wait time.
 * Priorities are positive integers;  the highest priority is 1. 
 * Normally patients are seen in priority order, however, if there are patients 
 * who have waited longer than a specified time (maxWait), they are seen first, 
 * in order of their arrival.  
 * @author elder
 */
public class PatientTriage {

    private APQ<Patient> priorityHeap; //maintain patients in priority order
    private APQ<Patient> timeHeap;  //maintain patients in order of arrival
    private Time maxWait; //maximum waiting time

    /**
     * Constructor
     *
     * @param time Maximum wait time.  Patients waiting longer than this are seen first.
     */
    public PatientTriage(Time time) {
        Comparator<Patient> priorityComparator = new PatientPriorityComparator();
        Comparator<Patient> timeComparator = new PatientTimeComparator();
        Locator<Patient> priorityLocator = new PatientPriorityLocator();
        Locator<Patient> timeLocator = new PatientTimeLocator();
        priorityHeap = new APQ<>(priorityComparator, priorityLocator);
        timeHeap = new APQ<>(timeComparator, timeLocator);
        setMaxWait(time);
    }

   /**
     * Adds patient to queues.  
     * @param patient to add.
     * @throws NullPointerException if given null patient
     */
     public void add(Patient patient) throws NullPointerException {
        if (patient == null) {
            throw new NullPointerException();
        }
        priorityHeap.offer(patient); //add to priority queue
        timeHeap.offer(patient); //add to arrival time queue
    }

   /**
     * Removes next patient in queue.  
     * @param currentTime used to determine whether to use priority or arrival time
     * @return Next patient to attend to
     * @throws NullPointerException if given null time
     * @throws EmptyQueueException if queue is empty
     * @throws BoundaryViolationException under some internal error conditions
     */
    public Patient remove(Time currentTime) throws NullPointerException, EmptyQueueException, BoundaryViolationException {
        Patient patient;
        Time wait;
        Comparator<Time> comparator = new TimeComparator();
        
        if (currentTime == null) {
            throw new NullPointerException();
        }
        if (timeHeap.isEmpty() || priorityHeap.isEmpty()) {
            throw new EmptyQueueException();
        }
        
        patient = timeHeap.peek(); //patient who has waited longest
        wait = patient.getArrivalTime().elapsed(currentTime); //calculate wait time
        if (comparator.compare(wait,maxWait)> 0) { //limit has been exceeded      
            priorityHeap.remove(patient.getPriorityPos()); //remove this patient from the priority queue
            return timeHeap.poll(); //remove and return this patient from the arrival time queue
        }
        else { //no one has waited past the limit - select the highest priority patient.
            patient = priorityHeap.poll(); //remove highest prioritity patient from priority queue.
            timeHeap.remove(patient.getTimePos()); //remove this patient from the time queue
            return patient;
        }
    }

   /**
     * @return maximum wait time
     */
    public Time getMaxWait() {
        return maxWait;
    }

    /**
     * Set the maximum wait time
     *
     * @param time - the maximum wait time
     * @throws NullPointerException if given null time
     */
    public void setMaxWait(Time time) throws NullPointerException {
        if (time == null) {
            throw new NullPointerException();
        }
        maxWait = time;
    }

}
