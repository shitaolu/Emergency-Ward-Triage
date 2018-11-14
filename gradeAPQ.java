package A2Q2S;

import java.util.*;

/**
 * Grading program for APQ
 *
 * @author elder
 */
public class gradeAPQ {

    public static void main(String[] args) throws BoundaryViolationException, EmptyQueueException {
        //String patient;
        Comparator<Patient> priorityComparator = new PatientPriorityComparator();
        Locator<Patient> priorityLocator = new PatientPriorityLocator();
        APQ<Patient> testAPQ = new APQ<>(priorityComparator, priorityLocator); //adaptable priority queue for testing
        Patient patient;
        int[] marks = new int[9];
        int testNum = 0;
        int nCorrect = 0;

        //Test Case 1
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            testAPQ.offer(null);
            System.out.println("Incorrect: APQ.offer did not throw Null Pointer Exception if given null element");
        } catch (NullPointerException npx1) {
            System.out.println("Correct: APQ.offer correctly throws Null Pointer Exception if given null element");
            marks[testNum] = 1;
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception but not the Null Pointer Exception that was expected");
        }

        //Test Case 2
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            testAPQ.remove(0);
            System.out.println("Incorrect: APQ.remove did not throw Boundary Violation Exception given non-positive position");
        } catch (BoundaryViolationException bvx1) {
            System.out.println("Correct: APQ.remove correctly throws Boundary Violation Exception if given non-positive position");
            marks[testNum] = 1;
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception but not the Boundary Violation Exception that was expected");
        }

        //Test Case 3
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            testAPQ.remove(1);
            System.out.println("Incorrect: APQ.offer did not throw Boundary Violation Exception given position exceeding size of queue");
        } catch (BoundaryViolationException bvx2) {
            System.out.println("Correct: APQ.remove correctly throws Boundary Violation Exception if given position exceeding size of queue");
            marks[testNum] = 1;
        } catch (Exception ex) {
            System.out.println("Incorrect: patientTriage.remove threw an exception but not the Boundary Violation Exception that was expected");
        }

        //Test Case 4
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            if (testAPQ.poll() == null) {
                System.out.println("Correct: APQ.poll returned null for empty queue.");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect: APQ.poll did not return null for empty queue.");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: APQ.poll threw an unexpected exception.");
        }

        //Test Case 5
        Patient patient1 = new Patient(3, 2, new Time(12, 33));
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            testAPQ.offer(patient1);
            if (testAPQ.size() == 1) {
                System.out.println("Correct: Queue has correct size.");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect: Queue has incorrect size.");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: APQ.offer threw unexpected exception.");
            marks[testNum] = 0;
        }

        //Test Case 6
        Patient patient2 = new Patient(11, 5, new Time(1, 31));
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            testAPQ.offer(patient2);
            if (testAPQ.size() == 2) {
                System.out.println("Correct: Queue has correct size.");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect: Queue has incorrect size.");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: APQ.offer threw unexpected exception.");
            marks[testNum] = 0;
        }

        //Test Case 7
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            patient = testAPQ.poll();
            if (patient == patient1 && testAPQ.size() == 1) {
                System.out.println("Correct: APQ.poll returned correct entry and queue has correct size.");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect: APQ.poll returned incorrect entry or queue has wrong size.");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: APQ.poll threw unexpected exception.");
            marks[testNum] = 0;
        }

        //Test Case 8
        System.out.print("Test Case " + ++testNum + ": ");
        try {
            testAPQ.remove(patient2.getPriorityPos());
            if (testAPQ.isEmpty()) {
                System.out.println("Correct: APQ is empty.");
                marks[testNum] = 1;
            } else {
                System.out.println("Incorrect: APQ should be empty and is not.");
            }
        } catch (Exception ex) {
            System.out.println("Incorrect: APQ.remove threw unexpected exception.");
            marks[testNum] = 0;
        }

        System.out.print("Test Case Summary: ");
        for (int i = 1; i < marks.length; i++) {
            System.out.print(marks[i] + " ");
            nCorrect += marks[i];
        }
        System.out.println();
        System.out.println("Test Case Grade: " + (double) nCorrect/(marks.length-1));

    }
}
