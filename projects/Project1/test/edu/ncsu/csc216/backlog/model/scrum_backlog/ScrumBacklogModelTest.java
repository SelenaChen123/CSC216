package edu.ncsu.csc216.backlog.model.scrum_backlog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;

/**
 * Tests ScrumBacklogModel.
 * 
 * @author Selena Chen
 */
public class ScrumBacklogModelTest {

	/**
	 * Tests getInstance().
	 */
	@Test
	public void testGetInstance() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title", Type.BUG, "creator", "note");
		assertEquals(1, instance.getTaskItemListAsArray().length);
	}

	/**
	 * Tests saveTasksToFile().
	 */
	@Test
	public void testSaveTasksToFile() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		
		instance.loadTasksFromFile("test-files/exp_task_empty.xml");
		assertEquals(0, instance.getTaskItemListAsArray().length);
		instance.saveTasksToFile("test-files/act_task_empty.xml");
		checkFiles("test-files/exp_task_empty.xml", "test-files/act_task_empty.xml");
		
		instance.loadTasksFromFile("test-files/exp_task_backlog.xml");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		instance.saveTasksToFile("test-files/act_task_backlog.xml");
		checkFiles("test-files/exp_task_backlog.xml", "test-files/act_task_backlog.xml");
		
		instance.loadTasksFromFile("test-files/exp_task_owned.xml");
		instance.saveTasksToFile("test-files/act_task_owned.xml");
		checkFiles("test-files/exp_task_owned.xml", "test-files/act_task_owned.xml");
		
		instance.loadTasksFromFile("test-files/exp_task_processing.xml");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		instance.saveTasksToFile("test-files/act_task_processing.xml");
		checkFiles("test-files/exp_task_processing.xml", "test-files/act_task_processing.xml");
		
		instance.loadTasksFromFile("test-files/exp_task_verifying.xml");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		instance.saveTasksToFile("test-files/act_task_verifying.xml");
		checkFiles("test-files/exp_task_verifying.xml", "test-files/act_task_verifying.xml");
		
		instance.loadTasksFromFile("test-files/exp_task_done.xml");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		instance.saveTasksToFile("test-files/act_task_done.xml");
		checkFiles("test-files/exp_task_done.xml", "test-files/act_task_done.xml");
	}

	/**
	 * Tests loadTasksFromFile().
	 */
	@Test
	public void testLoadTasksFromFile() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.loadTasksFromFile("test-files/tasks_valid.xml");
		assertEquals(6, instance.getTaskItemListAsArray().length);
		
		try {
			instance.loadTasksFromFile("test-files/tasks1.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks2.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks3.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks4.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks5.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks6.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks7.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks8.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks9.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks10.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks11.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks12.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks13.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks14.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks15.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks16.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks17.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks18.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks19.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
		
		try {
			instance.loadTasksFromFile("test-files/tasks20.xml");
			fail();
		} catch (IllegalArgumentException ioe) {
			assertEquals(6, instance.getTaskItemListAsArray().length);
		}
	}

	/**
	 * Tests createNewTaskItemList().
	 */
	@Test
	public void testCreateNewTaskItemList() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title", Type.BUG, "creator", "note");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
	}

	/**
	 * Tests getTaskItemListAsArray().
	 */
	@Test
	public void testGetTaskItemListAsArray() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator1", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		assertEquals(1, instance.getTaskItemListAsArray()[0][0]);
		assertEquals("Backlog", instance.getTaskItemListAsArray()[0][1]);
		assertEquals("title1", instance.getTaskItemListAsArray()[0][2]);
		
		instance.addTaskItemToList("title2", Type.FEATURE, "creator2", "note2");
		assertEquals(2, instance.getTaskItemListAsArray().length);
		assertEquals(2, instance.getTaskItemListAsArray()[1][0]);
		assertEquals("Backlog", instance.getTaskItemListAsArray()[1][1]);
		assertEquals("title2", instance.getTaskItemListAsArray()[1][2]);
	}

	/**
	 * Tests getTaskItemListByOwnerAsArray().
	 */
	@Test
	public void testGetTaskItemListByOwnerAsArray() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		
		assertEquals(0, instance.getTaskItemListByOwnerAsArray("Selena").length);
		
		instance.getTaskItemById(1).update(new Command(CommandValue.CLAIM, "owner", "note2"));
		assertEquals(1, instance.getTaskItemListByOwnerAsArray("owner").length);
	}

	/**
	 * Tests getTaskItemListByCreatorAsArray().
	 */
	@Test
	public void testGetTaskItemListByCreatorAsArray() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		
		assertEquals(1, instance.getTaskItemListByCreatorAsArray("creator").length);
		
		assertEquals(0, instance.getTaskItemListByCreatorAsArray("Selena").length);
	}

	/**
	 * Tests getTaskItemById().
	 */
	@Test
	public void testGetTaskItemById() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		assertEquals("title1", instance.getTaskItemById(1).getTitle());
		
		instance.addTaskItemToList("title2", Type.FEATURE, "creator2", "note2");
		assertEquals(2, instance.getTaskItemListAsArray().length);
		assertEquals("title2", instance.getTaskItemById(2).getTitle());
	}

	/**
	 * Tests executeCommand().
	 */
	@Test
	public void testExecuteCommand() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		
		instance.executeCommand(1, new Command(CommandValue.CLAIM, "owner", "note2"));
		assertEquals(1, instance.getTaskItemListByOwnerAsArray("owner").length);
		assertEquals("Owned", instance.getTaskItemById(1).getStateName());
	}

	/**
	 * Tests deleteTaskItemById().
	 */
	@Test
	public void testDeleteTaskItemById() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		
		instance.deleteTaskItemById(1);
		assertEquals(0, instance.getTaskItemListAsArray().length);
	}

	/**
	 * Tests addTaskItemToList().
	 */
	@Test
	public void testAddTaskItemToList() {
		ScrumBacklogModel instance = ScrumBacklogModel.getInstance();
		instance.createNewTaskItemList();
		assertEquals(0, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title1", Type.BUG, "creator", "note1");
		assertEquals(1, instance.getTaskItemListAsArray().length);
		
		instance.addTaskItemToList("title2", Type.FEATURE, "creator2", "note2");
		assertEquals(2, instance.getTaskItemListAsArray().length);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File(expFile));
			Scanner actScanner = new Scanner(new File(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}