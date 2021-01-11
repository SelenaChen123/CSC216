import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** 
 * Tests for the Grades class
 * @author Dr. Sarah Heckman
 */
public class GradesTest {
	
	/**
	 * Test A student
	 */
	@Test
	public void testAStudent() {
		Grades studentA = new Grades();
		studentA.setGp1(97);
		studentA.setGp2(94);
		studentA.setGp3(96);
		studentA.setP1p1(95);
		studentA.setP1p2(98);
		studentA.setP2p1(97);
		studentA.setP2p2(94);
		studentA.setLabs(93);
		studentA.setExam1(89);
		studentA.setExam2(92);
		studentA.setExam3(94);
		assertEquals(93.89, studentA.getFinalGrade(), 0.01);
		assertEquals("A", studentA.getFinalLetterGrade());
		assertEquals(96.0, studentA.getProjectMGR(), 0.01);
		assertEquals(95.91, studentA.getProjectGPMGR(), 0.01);
		assertEquals(91.85, studentA.getExamMGR(), 0.01);
	}
	
	/**
	 * Test B student
	 */
	@Test
	public void testBStudent() {
		Grades studentB = new Grades();
		studentB.setGp1(87);
		studentB.setGp2(84);
		studentB.setGp3(86);
		studentB.setP1p1(85);
		studentB.setP1p2(88);
		studentB.setP2p1(87);
		studentB.setP2p2(84);
		studentB.setLabs(83);
		studentB.setExam1(79);
		studentB.setExam2(82);
		studentB.setExam3(84);
		assertEquals(83.89, studentB.getFinalGrade(), 0.01);
		assertEquals("B", studentB.getFinalLetterGrade());
		assertEquals(86.0, studentB.getProjectMGR(), 0.01);
		assertEquals(85.91, studentB.getProjectGPMGR(), 0.01);
		assertEquals(81.85, studentB.getExamMGR(), 0.01);
	}
	
	/**
	 * Test C student
	 */
	@Test
	public void testCStudent() {
		Grades studentC = new Grades();
		studentC.setGp1(77);
		studentC.setGp2(74);
		studentC.setGp3(76);
		studentC.setP1p1(75);
		studentC.setP1p2(78);
		studentC.setP2p1(77);
		studentC.setP2p2(74);
		studentC.setLabs(73);
		studentC.setExam1(69);
		studentC.setExam2(72);
		studentC.setExam3(74);
	}
	
}
