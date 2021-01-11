package edu.ncsu.csc216.backlog.model.scrum_backlog;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests TaskItemList.
 * 
 * @author Selena Chen
 */
public class TaskItemListTest {

	/**
	 * Tests the TaskItemList constructor.
	 */
	@Test
	public void testTaskItemList() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
	}

	/**
	 * Tests addTaskItem().
	 */
	@Test
	public void testAddTaskItem() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title", Type.BUG, "creator", "note");
		assertEquals(1, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title2", Type.BUG, "creator2", "note2");
		assertEquals(2, tasks.getTaskItems().size());
	}

	/**
	 * Tests addXMLTasks().
	 */
	@Test
	public void testAddXMLTasks() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		Task task = new Task();
		task.setId(1);
		task.setTitle("title");
		task.setType("B");
		task.setState(TaskItem.BACKLOG_NAME);
		task.setCreator("creator");
		NoteItem note = new NoteItem();
		note.setNoteAuthor("creator");
		note.setNoteText("text");
		NoteList notes = new NoteList();
		notes.getNotes().add(note);
		task.setNoteList(notes);
		
		ArrayList<Task> xmlTasks = new ArrayList<Task>();
		xmlTasks.add(task);
		
		tasks.addXMLTasks(xmlTasks);
		assertEquals("title", tasks.getTaskItems().get(0).getTitle());
	}

	/**
	 * Tests getTaskItems().
	 */
	@Test
	public void testGetTaskItems() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title", Type.BUG, "creator", "note");
		assertEquals(1, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title2", Type.BUG, "creator2", "note2");
		assertEquals(2, tasks.getTaskItems().size());
	}

	/**
	 * Tests getTaskItemsByOwner().
	 */
	@Test
	public void testGetTaskItemsByOwner() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title", Type.BUG, "creator", "note1");
		assertEquals(1, tasks.getTaskItems().size());
		
		assertEquals(0, tasks.getTaskItemsByOwner("owner").size());
		
		tasks.getTaskItems().get(0).update(new Command(CommandValue.CLAIM, "owner", "note2"));
		assertEquals(1, tasks.getTaskItemsByOwner("owner").size());
	}

	/**
	 * Tests getTaskItemsByCreator().
	 */
	@Test
	public void testGetTaskItemsByCreator() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title", Type.BUG, "creator", "note1");
		assertEquals(1, tasks.getTaskItems().size());
		
		assertEquals(1, tasks.getTaskItemsByCreator("creator").size());
		
		assertEquals(0, tasks.getTaskItemsByCreator("Selena").size());
	}

	/**
	 * Tests getTaskItemsById().
	 */
	@Test
	public void testGetTaskItemById() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title1", Type.BUG, "creator", "note1");
		assertEquals("title1", tasks.getTaskItemById(1).getTitle());
		
		tasks.addTaskItem("title2", Type.FEATURE, "creator2", "note2");
		assertEquals("title2", tasks.getTaskItemById(2).getTitle());
	}

	/**
	 * Tests executeCommand().
	 */
	@Test
	public void testExecuteCommand() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title", Type.BUG, "creator", "note1");
		assertEquals("Backlog", tasks.getTaskItemById(1).getStateName());
		
		tasks.executeCommand(1, new Command(CommandValue.CLAIM, "owner", "note2"));
		assertEquals("Owned", tasks.getTaskItemById(1).getStateName());
	}

	/**
	 * Tests deleteTaskItemById().
	 */
	@Test
	public void testDeleteTaskItemById() {
		TaskItemList tasks = new TaskItemList();
		assertEquals(0, tasks.getTaskItems().size());
		
		tasks.addTaskItem("title", Type.BUG, "creator", "note1");
		assertEquals(1, tasks.getTaskItems().size());
		
		tasks.deleteTaskItemById(1);
		assertEquals(0, tasks.getTaskItems().size());
	}
}