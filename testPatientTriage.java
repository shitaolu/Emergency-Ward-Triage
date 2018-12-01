package A2Q2;

/**
 * Example test program for PatientTriage
 * @author elder
 */
public class testPatientTriage {
	public static void main(String[] args) throws BoundaryViolationException, EmptyQueueException {
		String patient;
		long startTime = System.nanoTime();
		PatientTriage patientTriage;
		
		patientTriage = new PatientTriage(new Time(3, 0));
		patientTriage.add(new Patient(1, 3, new Time(0, 30)));
		patientTriage.add(new Patient(7, 6, new Time(0, 50)));
		patientTriage.add(new Patient(2, 2, new Time(1, 30)));
		patientTriage.add(new Patient(6, 5, new Time(1, 45)));
		patientTriage.add(new Patient(3, 4, new Time(2, 15)));
		patientTriage.add(new Patient(4, 1, new Time(2, 50)));
		patientTriage.add(new Patient(5, 7, new Time(3, 20)));
		patientTriage.add(new Patient(8, 8, new Time(3, 40)));
		
		//case 1
		patient = patientTriage.remove(new Time (2, 0)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 4");
		
		//case 2
		patient = patientTriage.remove(new Time (3, 0)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 2");

		//case 3
		patientTriage.add(new Patient(10, 1, new Time(4, 10)));
		System.out.println();
		patient = patientTriage.remove(new Time (4,10)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 1");
		
		//case 4
		patient = patientTriage.remove(new Time (3, 45)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 10");
		
		//case 5
		patient = patientTriage.remove(new Time (4,20)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 7");

		//case 6
		patient = patientTriage.remove(new Time (5,00)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 6");
		
		//case 7
		patient = patientTriage.remove(new Time (5,5)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 3");
		
		patientTriage.add(new Patient(20, 5, new Time(3, 1)));
		
		//case 8
		patient = patientTriage.remove(new Time (5,55)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 20");
		
		//case 9
		PatientTriage patientTriage2 = new PatientTriage(new Time(3, 0));
		patientTriage2.add(new Patient(1, 3, new Time(1,20)));
		patientTriage2.add(new Patient(2, 4, new Time(0,20)));
		
		patient = patientTriage2.remove(new Time (3,30)).toString(); 
        System.out.print("Now seeing: ");
        System.out.println(patient);
		System.out.println("correct: patient 2");
		
		long stopTime = System.nanoTime();
        double elapsedTime = (double) (stopTime - startTime) / 1000000; //in msec
        System.out.println("Execution time: " + elapsedTime);
	}
}
