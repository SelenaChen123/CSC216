/**
 * Represents the grades of a CSC216 student.
 * @author Dr. Sarah Heckman
 *
 */
public class Grades {
	
	/** GP1 grade */
	private double gp1;
	/** GP2 grade */
	private double gp2;
	/** GP3 grade */
	private double gp3;
	/** P1P1 grade */
	private double p1p1;
	/** P1P2 grade */
	private double p1p2;
	/** P2P1 grade */
	private double p2p1;
	/** P2P2 grade */
	private double p2p2;
	/** Lab grades */
	private double labs;
	/** Exam 1 grade */
	private double exam1;
	/** Exam 2 grade */
	private double exam2;
	/** Exam 3 grade */
	private double exam3;
	
	private static final int GP_PERCENT = 4;
	private static final int PART1_PERCENT = 3;
	private static final int PART2_PERCENT = 14;
	private static final int LABS_PERCENT = 15;
	private static final int IN_CLASS_EXAMS_PERCENT = 12;
	private static final int FINAL_EXAM_PERCENT = 15;
	private static final int FIRST_MGR_THRESHOLD = 60;
	private static final int SECOND_MGR_THRESHOLD = 65;
	private static final int HIGHEST_MGR_GRADE = 72;
	private static final int D_MINUS = 60;
	private static final int D = 63;
	private static final int D_PLUS = 67;
	private static final int C_MINUS = 70;
	private static final int C = 73;
	private static final int C_PLUS = 77;
	private static final int B_MINUS = 80;
	private static final int B = 83;
	private static final int B_PLUS = 87;
	private static final int A_MINUS = 90;
	private static final int A = 93;
	private static final int A_PLUS = 97;
	private static final int FULL_SCORE = 100;

	/**
	 * Returns the final numerical grade for the course.
	 */
	public double getFinalGrade() {
		double grade = ((gp1 * GP_PERCENT) +
				(gp2 * GP_PERCENT) +
				(gp3 * GP_PERCENT) + 
				(p1p1 * PART1_PERCENT) +
				(p1p2 * PART2_PERCENT) +
				(p2p1 * PART1_PERCENT) +
				(p2p2 * PART2_PERCENT) +
				(labs * LABS_PERCENT) +
				(exam1 * IN_CLASS_EXAMS_PERCENT) +
				(exam2 * IN_CLASS_EXAMS_PERCENT) +
				(exam3 * FINAL_EXAM_PERCENT)) / FULL_SCORE;
				
		return grade;
	}
	
	/**
	 * Returns the letter grade for CSC216 by considering the minimum grade requirements (MGR)
	 * and grade calculation.
	 * @return letter grade for CSC216
	 */
	public String getFinalLetterGrade() {
		double grade = getFinalGrade();
		double projectMGR = getProjectMGR();
		double projectGPMGR = getProjectGPMGR();
		double examMGR = getExamMGR();
		
		if (projectMGR < FIRST_MGR_THRESHOLD || examMGR < FIRST_MGR_THRESHOLD) {
			return "F";
		}
		
		if (projectGPMGR < SECOND_MGR_THRESHOLD || examMGR < SECOND_MGR_THRESHOLD) {
			grade =  Math.min(HIGHEST_MGR_GRADE, grade);
		}
		
		if (grade >= A_PLUS) return "A+";
		if (grade >= A) return "A";
		if (grade >= A_MINUS) return "A-";
		if (grade >= B_PLUS) return "B+";
		if (grade >= B) return "B+";
		if (grade >= B_MINUS) return "B-";
		if (grade >= C_PLUS) return "C+";
		if (grade >= C) return "C";
		if (grade >= C_MINUS) return "C-";
		if (grade >= D_PLUS) return "D+";
		if (grade >= D) return "D";
		if (grade >= D_MINUS) return "D-";
		if (grade >= 0) return "F";
		return null;
		
	}
	
	/**
	 * Returns the project MGR
	 * @return the project MGR
	 */
	public double getProjectMGR() {
		double projectMGR = 
				((p1p1 * PART1_PERCENT) +
				(p1p2 * PART2_PERCENT) +
				(p2p1 * PART1_PERCENT) +
				(p2p2 * PART2_PERCENT)) / 
				((PART1_PERCENT * 2)  + (PART2_PERCENT * 2));
		return projectMGR;
	}
	
	/**
	 * Returns the project and guided project MGR
	 * @return the project and GP MGR
	 */
	public double getProjectGPMGR() {
		double projectGPMGR = 
				((gp1 * GP_PERCENT) +
				(gp2 * GP_PERCENT) +
				(gp3 * GP_PERCENT) + 
				(p1p1 * PART1_PERCENT) +
				(p1p2 * PART2_PERCENT) +
				(p2p1 * PART1_PERCENT) +
				(p2p2 * PART2_PERCENT)) / 
				((GP_PERCENT * 3) + (PART1_PERCENT * 2)  + (PART2_PERCENT * 2));
		return projectGPMGR;
	}
	
	/**
	 * Returns the exam MGR 
	 * @return exam MGR
	 */
	public double getExamMGR() {
		double examMGR = 
				((exam1 * IN_CLASS_EXAMS_PERCENT) +
				(exam2 * IN_CLASS_EXAMS_PERCENT) +
				(exam3 * FINAL_EXAM_PERCENT)) / 
				((IN_CLASS_EXAMS_PERCENT * 2) + FINAL_EXAM_PERCENT);
		return examMGR;
	}

	/**
	 * Returns GP1 grade
	 * @return GP1 grade
	 */
	public double getGp1() {
		return gp1;
	}

	/**
	 * Sets GP1 grade
	 * @param gp1 grade
	 */
	public void setGp1(double gp1) {
		this.gp1 = gp1;
	}

	/**
	 * Returns GP2 grade
	 * @return GP2 grade
	 */
	public double getGp2() {
		return gp2;
	}

	/**
	 * Sets GP2 grade
	 * @param gp2 grade
	 */
	public void setGp2(double gp2) {
		this.gp2 = gp2;
	}

	/**
	 * Returns GP3 grade
	 * @return GP3 grade
	 */
	public double getGp3() {
		return gp3;
	}

	/**
	 * Sets GP3 grade
	 * @param gp3 grade
	 */
	public void setGp3(double gp3) {
		this.gp3 = gp3;
	}

	/**
	 * Returns P1P1 grade
	 * @return P1P1 grade
	 */
	public double getP1p1() {
		return p1p1;
	}

	/**
	 * Sets P1P1 grade
	 * @param p1p1 grade
	 */
	public void setP1p1(double p1p1) {
		this.p1p1 = p1p1;
	}

	/**
	 * Returns P1P2 grade
	 * @return P1P2 grade
	 */
	public double getP1p2() {
		return p1p2;
	}

	/**
	 * Sets P1P2 grade
	 * @param p1p2 grade
	 */
	public void setP1p2(double p1p2) {
		this.p1p2 = p1p2;
	}

	/**
	 * Returns P2P1 grade
	 * @return P2P1 grade
	 */
	public double getP2p1() {
		return p2p1;
	}
	/**
	 * Sets P2P1 grade
	 * @param p2p1 grade
	 */
	public void setP2p1(double p2p1) {
		this.p2p1 = p2p1;
	}

	/**
	 * Returns P2P2 grade
	 * @return P2P2 grade
	 */
	public double getP2p2() {
		return p2p2;
	}
	/**
	 * Sets P2P2 grade
	 * @param p2p2 grade
	 */
	public void setP2p2(double p2p2) {
		this.p2p2 = p2p2;
	}

	/**
	 * Returns lab grades
	 * @return lab grades
	 */
	public double getLabs() {
		return labs;
	}
	/**
	 * Sets Lab grades
	 * @param labs grade
	 */
	public void setLabs(double labs) {
		this.labs = labs;
	}

	/**
	 * Returns Exam1 grade
	 * @return Exam1 grade
	 */
	public double getExam1() {
		return exam1;
	}
	/**
	 * Sets Exam1 grade
	 * @param exam1 grade
	 */
	public void setExam1(double exam1) {
		this.exam1 = exam1;
	}

	/**
	 * Returns Exam2 grade
	 * @return Exam2 grade
	 */
	public double getExam2() {
		return exam2;
	}
	/**
	 * Sets Exam2 grade
	 * @param exam2 grade
	 */
	public void setExam2(double exam2) {
		this.exam2 = exam2;
	}

	/**
	 * Returns Exam3 grade
	 * @return Exam3 grade
	 */
	public double getExam3() {
		return exam3;
	}
	/**
	 * Sets Exam3 grade
	 * @param exam3 grade
	 */
	public void setExam3(double exam3) {
		this.exam3 = exam3;
	}

	/**
	 * Compares all of the grade information.
	 * @param obj object to compare to this one
	 * @return true if the grades are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grades other = (Grades) obj;
		if (Double.doubleToLongBits(exam1) != Double.doubleToLongBits(other.exam1))
			return false;
		if (Double.doubleToLongBits(exam2) != Double.doubleToLongBits(other.exam2))
			return false;
		if (Double.doubleToLongBits(exam3) != Double.doubleToLongBits(other.exam3))
			return false;
		if (Double.doubleToLongBits(gp1) != Double.doubleToLongBits(other.gp1))
			return false;
		if (Double.doubleToLongBits(gp2) != Double.doubleToLongBits(other.gp2))
			return false;
		if (Double.doubleToLongBits(gp3) != Double.doubleToLongBits(other.gp3))
			return false;
		if (Double.doubleToLongBits(labs) != Double.doubleToLongBits(other.labs))
			return false;
		if (Double.doubleToLongBits(p1p1) != Double.doubleToLongBits(other.p1p1))
			return false;
		if (Double.doubleToLongBits(p1p2) != Double.doubleToLongBits(other.p1p2))
			return false;
		if (Double.doubleToLongBits(p2p1) != Double.doubleToLongBits(other.p2p1))
			return false;
		if (Double.doubleToLongBits(p2p2) != Double.doubleToLongBits(other.p2p2))
			return false;
		return true;
	}
	
	/**
	 * Returns grade info as a String.
	 * @return grade info
	 */
	@Override
	public String toString() {
		String gradeInfo = "";
		gradeInfo += "Project MGR: " + getProjectMGR() + "\n";
		gradeInfo += "Project + GP MGR: " + getProjectGPMGR() + "\n";
		gradeInfo += "Exam MGR: " + getExamMGR() + "\n";
		gradeInfo += "Final Grade: " + getFinalGrade() + "\n";
		gradeInfo += "Final Letter Grade: " + getFinalLetterGrade();
		return gradeInfo;
	}

}
