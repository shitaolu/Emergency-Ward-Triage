package A2Q2S;

import java.util.*;

/**
 * Grading program for PatientTriage
 *
 * @author elder
 */
public class gradePatientTriage {

    public static void main(String[] args) throws BoundaryViolationException, EmptyQueueException {
        //String patient;
        Comparator<Patient> priorityComparator = new PatientPriorityComparator();
        Locator<Patient> priorityLocator = new PatientPriorityLocator();
        Patient patient;
        int[] ans = {0, 11, 6, 17, 4, 3, 7, 2, 5, 0, 9, 0};     
        int[] marks = new int[ans.length];
        int testNum = 0;
        int nCorrect = 0;

        long startTime = System.nanoTime();  //Start measurement of execution time when we start adding patients

        PatientTriage patientTriage = new PatientTriage(new Time(2, 30)); //Time limit of 2.5 hours
       
        patientTriage.add(new Patient(17, 6, new Time(1, 00)));
        patientTriage.add(new Patient(4, 5, new Time(1, 15)));
        patientTriage.add(new Patient(11, 1, new Time(1, 30)));

        //Test Case 1
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(1, 45));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        patientTriage.add(new Patient(3, 5, new Time(2, 00)));
        patientTriage.add(new Patient(6, 2, new Time(2, 10)));

        //Test Case 2
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(2, 20));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception");
        }

        patientTriage.add(new Patient(2, 11, new Time(2, 30)));
        patientTriage.add(new Patient(5, 8, new Time(2, 40)));

        //Test Case 3
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(3, 35));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        //Test Case 4
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(3, 40));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        patientTriage.add(new Patient(7, 1, new Time(4, 15)));

        //Test Case 5
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(4, 45));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
         } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        //Test Case 6
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(4, 55));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        //Test Case 7
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(5, 15));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        //Test Case 8
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(5, 30));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        //Test Case 9
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(5, 35));
            System.out.println("Incorrect: patientTriage.remove did not throw Empty Queue Exception given empty queue");
        } catch (EmptyQueueException ex) {
            System.out.println("Correct: patientTriage.remove correctly threw Empty Queue Exception given empty queue");
            marks[testNum] = 1;
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception but not the Empty Queue Exception that was expected");
        }

        patientTriage.add(new Patient(9, 7, new Time(5, 40)));

        //Test Case 10
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = patientTriage.remove(new Time(5, 50));
            if (patient.getID() == ans[testNum]) {
                System.out.println("Correct");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception.");
        }

        long stopTime = System.nanoTime();

        //Test Case 11
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patientTriage.remove(null);
            System.out.println("Incorrect: patientTriage.remove did not throw Null Pointer Exception given null element");
        } catch (NullPointerException ex) {
            System.out.println("Correct: patientTriage.remove correctly threw Null Pointer Exception given empty queue");
            marks[testNum] = 1;
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception but not the Null Pointer Exception that was expected");
        }

        System.out.print("Test Case Summary: ");
        for (int i = 1; i < marks.length; i++) {
            System.out.print(marks[i] + " ");
            nCorrect += marks[i];
        }
        System.out.println();
        System.out.println("Test Case Grade: " + (double) nCorrect/(marks.length-1));
        double elapsedTime = (double) (stopTime - startTime)/1000000; //in msec
        System.out.println("Execution Time (msec): " + elapsedTime);

    }
}
