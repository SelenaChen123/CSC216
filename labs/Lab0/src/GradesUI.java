import java.util.Scanner;

public class GradesUI {
	
	/**
	 * Prompts the user for grades and calculates the student's grade.
	 */
	public void requestGrades() {
		Grades grades = null;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter numerical grades for the following assignments:");
		System.out.println("Guided Project 1: ");
		grades.setGp1(in.nextDouble());
		
		System.out.println("Guided Project 2: ");
		grades.setGp2(in.nextDouble());
		
		System.out.println("Guided Project 3: ");
		grades.setGp3(in.nextDouble());
		
		System.out.println("Project 1 Part 1: ");
		grades.setP1p1(in.nextDouble());
		
		System.out.println("Project 1 Part 2: ");
		grades.setP1p2(in.nextDouble());
		
		System.out.println("Project 2 Part 1: ");
		grades.setP2p1(in.nextDouble());
		
		System.out.println("Project 2 Part 2: ");
		grades.setP2p2(in.nextDouble());
		
		System.out.println("Lab Average: ");
		grades.setLabs(in.nextDouble());
		
		System.out.println("Exam 1: ");
		grades.setExam1(in.nextDouble());
		
		System.out.println("Exam 2: ");
		grades.setExam2(in.nextDouble());
		
		System.out.println("Exam 3: ");
		grades.setExam3(in.nextDouble());
		
		in.close(); //Always close things that you open.
		
		System.out.println(grades.toString());
	}
	
	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		GradesUI ui = new GradesUI();
		ui.requestGrades();
	}

}
