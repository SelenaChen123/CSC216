package edu.ncsu.csc216.backlog.model.task;

import static org.junit.Assert.*;

import org.junit.*;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.*;

/**
 * Tests TaskItem.
 * 
 * @author Selena Chen
 */
public class TaskItemTest {

	/**
	 * Tests the TaskItem constructor with values for title, type, creator, and
	 * note.
	 */
	@Test
	public void testTaskItemStringTypeStringString() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(1, taskItem.getTaskItemId());
		assertEquals("title", taskItem.getTitle());
		assertEquals(Type.BUG, taskItem.getType());
		assertEquals("creator", taskItem.getCreator());
		assertEquals("note", taskItem.getNotes().get(0).getNoteText());

		try {
			taskItem = new TaskItem(null, Type.FEATURE, "creator", "note");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}

		try {
			taskItem = new TaskItem("", Type.TECHNICAL_WORK, "creator", "note");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}

		try {
			taskItem = new TaskItem("title", null, "creator", "note");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}

		try {
			taskItem = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, null, "note");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}

		try {
			taskItem = new TaskItem("title", Type.BUG, "", "note");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}

		try {
			taskItem = new TaskItem("title", Type.FEATURE, "creator", null);
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}

		try {
			taskItem = new TaskItem("title", Type.TECHNICAL_WORK, "creator", "");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
			assertEquals("title", taskItem.getTitle());
			assertEquals(Type.BUG, taskItem.getType());
			assertEquals("creator", taskItem.getCreator());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		}
	}

	/**
	 * Tests the TaskItem constructor with a Task object.
	 */
	@Test
	public void testTaskItemTask() {
		TaskItem.setCounter(1);

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
		NoteItem note2 = new NoteItem();
		note2.setNoteAuthor("owner");
		note2.setNoteText("text2");
		notes.getNotes().add(note2);
		task.setNoteList(notes);

		TaskItem taskItem = new TaskItem(task);
		assertEquals(1, taskItem.getTaskItemId());
		assertEquals("title", taskItem.getTitle());
		assertEquals(Type.BUG, taskItem.getType());
		assertEquals("creator", taskItem.getCreator());
		assertEquals("creator", taskItem.getNotesArray()[0][0]);
		assertEquals("text", taskItem.getNotesArray()[0][1]);
		assertEquals("owner", taskItem.getNotesArray()[1][0]);
		assertEquals("text2", taskItem.getNotesArray()[1][1]);
	}

	/**
	 * Tests incrementCounter().
	 */
	@Test
	public void testIncrementCounter() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(1, taskItem.getTaskItemId());

		taskItem = new TaskItem("title", Type.FEATURE, "creator", "note");
		assertEquals(2, taskItem.getTaskItemId());

		TaskItem.incrementCounter();

		taskItem = new TaskItem("title", Type.TECHNICAL_WORK, "creator", "note");
		assertEquals(4, taskItem.getTaskItemId());

		TaskItem taskItem2 = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "creator", "note");
		assertEquals(5, taskItem2.getTaskItemId());
	}

	/**
	 * Tests getTaskItemId().
	 */
	@Test
	public void testGetTaskItemId() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(1, taskItem.getTaskItemId());

		try {
			taskItem = new TaskItem(null, Type.FEATURE, "creator", "note");
		} catch (IllegalArgumentException iae) {
			assertEquals(1, taskItem.getTaskItemId());
		}

		taskItem = new TaskItem("title", Type.FEATURE, "creator", "note");
		assertEquals(2, taskItem.getTaskItemId());
	}

	/**
	 * Tests getStateName().
	 */
	@Test
	public void testGetStateName() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("Backlog", taskItem.getStateName());

		Command c = new Command(CommandValue.CLAIM, "owner", "text");
		taskItem.update(c);
		assertEquals("Owned", taskItem.getStateName());

		c = new Command(CommandValue.PROCESS, "owner2", "text2");
		taskItem.update(c);
		assertEquals("Processing", taskItem.getStateName());

		c = new Command(CommandValue.VERIFY, "owner3", "text3");
		taskItem.update(c);
		assertEquals("Verifying", taskItem.getStateName());

		c = new Command(CommandValue.COMPLETE, "owner4", "text4");
		taskItem.update(c);
		assertEquals("Done", taskItem.getStateName());

		c = new Command(CommandValue.BACKLOG, "owner5", "text5");
		taskItem.update(c);
		assertEquals("Backlog", taskItem.getStateName());

		c = new Command(CommandValue.REJECT, "owner6", "text6");
		taskItem.update(c);
		assertEquals("Rejected", taskItem.getStateName());

		c = new Command(CommandValue.COMPLETE, "owner7", "text7");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem.getStateName());
		}
	}

	/**
	 * Tests getType().
	 */
	@Test
	public void testGetType() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(Type.BUG, taskItem.getType());

		taskItem = new TaskItem("title", Type.FEATURE, "creator", "note");
		assertEquals(Type.FEATURE, taskItem.getType());

		taskItem = new TaskItem("title", Type.TECHNICAL_WORK, "creator", "note");
		assertEquals(Type.TECHNICAL_WORK, taskItem.getType());

		taskItem = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "creator", "note");
		assertEquals(Type.KNOWLEDGE_ACQUISITION, taskItem.getType());
	}

	/**
	 * Tests getTypeString().
	 */
	@Test
	public void testGetTypeString() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("B", taskItem.getTypeString());

		taskItem = new TaskItem("title", Type.FEATURE, "creator", "note");
		assertEquals("F", taskItem.getTypeString());

		taskItem = new TaskItem("title", Type.TECHNICAL_WORK, "creator", "note");
		assertEquals("TW", taskItem.getTypeString());

		taskItem = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "creator", "note");
		assertEquals("KA", taskItem.getTypeString());
	}

	/**
	 * Tests getTypeFullString().
	 */
	@Test
	public void testGetTypeFullString() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("Bug", taskItem.getTypeFullString());

		taskItem = new TaskItem("title", Type.FEATURE, "creator", "note");
		assertEquals("Feature", taskItem.getTypeFullString());

		taskItem = new TaskItem("title", Type.TECHNICAL_WORK, "creator", "note");
		assertEquals("Technical Work", taskItem.getTypeFullString());

		taskItem = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "creator", "note");
		assertEquals("Knowledge Acquisition", taskItem.getTypeFullString());
	}

	/**
	 * Tests getOwner().
	 */
	@Test
	public void testGetOwner() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertNull(taskItem.getOwner());

		Command c = new Command(CommandValue.CLAIM, "owner", "text");
		taskItem.update(c);
		assertEquals("owner", taskItem.getOwner());

		c = new Command(CommandValue.PROCESS, "owner", "text2");
		taskItem.update(c);
		assertEquals("owner", taskItem.getOwner());

		c = new Command(CommandValue.REJECT, "owner2", "text3");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("owner", taskItem.getOwner());
		}
	}

	/**
	 * Tests getTitle().
	 */
	@Test
	public void testGetTitle() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("title", taskItem.getTitle());

		taskItem = new TaskItem("title2", Type.BUG, "creator", "note");
		assertEquals("title2", taskItem.getTitle());
	}

	/**
	 * Tests getCreator().
	 */
	@Test
	public void testGetCreator() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("creator", taskItem.getCreator());

		Command c = new Command(CommandValue.CLAIM, "owner", "text");
		taskItem.update(c);
		assertEquals("creator", taskItem.getCreator());

		TaskItem taskItem2 = new TaskItem("title2", Type.BUG, "creator2", "note");
		assertEquals("creator2", taskItem2.getCreator());
	}

	/**
	 * Tests getNotes().
	 */
	@Test
	public void testGetNotes() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("creator", taskItem.getNotes().get(0).getNoteAuthor());
		assertEquals("note", taskItem.getNotes().get(0).getNoteText());

		Command c = new Command(CommandValue.CLAIM, "owner", "text");
		taskItem.update(c);
		assertEquals("creator", taskItem.getNotes().get(0).getNoteAuthor());
		assertEquals("note", taskItem.getNotes().get(0).getNoteText());
		assertEquals("owner", taskItem.getNotes().get(1).getNoteAuthor());
		assertEquals("text", taskItem.getNotes().get(1).getNoteText());

		c = new Command(CommandValue.COMPLETE, "owner2", "text2");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("creator", taskItem.getNotes().get(0).getNoteAuthor());
			assertEquals("note", taskItem.getNotes().get(0).getNoteText());
			assertEquals("owner", taskItem.getNotes().get(1).getNoteAuthor());
			assertEquals("text", taskItem.getNotes().get(1).getNoteText());
		}
	}

	/**
	 * Tests update().
	 */
	@Test
	public void testUpdate() {
		TaskItem.setCounter(1);

		// Full valid path

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("Backlog", taskItem.getStateName());
		assertNull(taskItem.getOwner());
		assertEquals("creator", taskItem.getNotes().get(0).getNoteAuthor());
		assertEquals("note", taskItem.getNotes().get(0).getNoteText());

		Command c = new Command(CommandValue.CLAIM, "owner", "text");
		taskItem.update(c);
		assertEquals("Owned", taskItem.getStateName());
		assertEquals("owner", taskItem.getOwner());
		assertEquals("owner", taskItem.getNotes().get(1).getNoteAuthor());
		assertEquals("text", taskItem.getNotes().get(1).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner", "text2");
		taskItem.update(c);
		assertEquals("Processing", taskItem.getStateName());
		assertEquals("owner", taskItem.getOwner());
		assertEquals("owner", taskItem.getNotes().get(2).getNoteAuthor());
		assertEquals("text2", taskItem.getNotes().get(2).getNoteText());

		c = new Command(CommandValue.VERIFY, "owner", "text3");
		taskItem.update(c);
		assertEquals("Verifying", taskItem.getStateName());
		assertEquals("owner", taskItem.getOwner());
		assertEquals("owner", taskItem.getNotes().get(3).getNoteAuthor());
		assertEquals("text3", taskItem.getNotes().get(3).getNoteText());

		c = new Command(CommandValue.COMPLETE, "owner", "text4");
		taskItem.update(c);
		assertEquals("Done", taskItem.getStateName());
		assertEquals("owner", taskItem.getOwner());
		assertEquals("owner", taskItem.getNotes().get(4).getNoteAuthor());
		assertEquals("text4", taskItem.getNotes().get(4).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner", "text5");
		taskItem.update(c);
		assertEquals("Backlog", taskItem.getStateName());
		assertNull(taskItem.getOwner());
		assertEquals("owner", taskItem.getNotes().get(5).getNoteAuthor());
		assertEquals("text5", taskItem.getNotes().get(5).getNoteText());

		c = new Command(CommandValue.REJECT, "owner", "text6");
		taskItem.update(c);
		assertEquals("Rejected", taskItem.getStateName());
		assertNull(taskItem.getOwner());
		assertEquals("owner", taskItem.getNotes().get(6).getNoteAuthor());
		assertEquals("text6", taskItem.getNotes().get(6).getNoteText());

		// Possible valid paths from Backlog

		TaskItem taskItem2 = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals("Backlog", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("creator", taskItem2.getNotes().get(0).getNoteAuthor());
		assertEquals("note", taskItem2.getNotes().get(0).getNoteText());

		c = new Command(CommandValue.REJECT, "owner", "text");
		taskItem2.update(c);
		assertEquals("Rejected", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner", taskItem2.getNotes().get(1).getNoteAuthor());
		assertEquals("text", taskItem2.getNotes().get(1).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner", "text2");
		taskItem2.update(c);
		assertEquals("Backlog", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner", taskItem2.getNotes().get(2).getNoteAuthor());
		assertEquals("text2", taskItem2.getNotes().get(2).getNoteText());
		taskItem.update(c);

		// Possible invalid paths from Backlog

		c = new Command(CommandValue.BACKLOG, "owner2", "text2.5");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner", taskItem2.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem2.getNotes().get(2).getNoteText());
		}

		c = new Command(CommandValue.PROCESS, "owner2", "text3");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner", taskItem2.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem2.getNotes().get(2).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner2", "text4");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner", taskItem2.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem2.getNotes().get(2).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner2", "text5");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner", taskItem2.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem2.getNotes().get(2).getNoteText());
		}

		// Possible valid paths from Owned

		c = new Command(CommandValue.CLAIM, "owner2", "text6");
		taskItem2.update(c);
		assertEquals("Owned", taskItem2.getStateName());
		assertEquals("owner2", taskItem2.getOwner());
		assertEquals("owner2", taskItem2.getNotes().get(3).getNoteAuthor());
		assertEquals("text6", taskItem2.getNotes().get(3).getNoteText());

		c = new Command(CommandValue.REJECT, "owner2", "text7");
		taskItem2.update(c);
		assertEquals("Rejected", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner2", taskItem2.getNotes().get(4).getNoteAuthor());
		assertEquals("text7", taskItem2.getNotes().get(4).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner2", "text8");
		taskItem2.update(c);
		assertEquals("Backlog", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner2", taskItem2.getNotes().get(5).getNoteAuthor());
		assertEquals("text8", taskItem2.getNotes().get(5).getNoteText());

		c = new Command(CommandValue.CLAIM, "owner3", "text9");
		taskItem2.update(c);
		assertEquals("Owned", taskItem2.getStateName());
		assertEquals("owner3", taskItem2.getOwner());
		assertEquals("owner3", taskItem2.getNotes().get(6).getNoteAuthor());
		assertEquals("text9", taskItem2.getNotes().get(6).getNoteText());

		// Possible invalid paths from Owned

		c = new Command(CommandValue.CLAIM, "owner3", "text9.5");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Owned", taskItem2.getStateName());
			assertEquals("owner3", taskItem2.getOwner());
			assertEquals("owner3", taskItem2.getNotes().get(6).getNoteAuthor());
			assertEquals("text9", taskItem2.getNotes().get(6).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner3", "text10");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Owned", taskItem2.getStateName());
			assertEquals("owner3", taskItem2.getOwner());
			assertEquals("owner3", taskItem2.getNotes().get(6).getNoteAuthor());
			assertEquals("text9", taskItem2.getNotes().get(6).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner3", "text11");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Owned", taskItem2.getStateName());
			assertEquals("owner3", taskItem2.getOwner());
			assertEquals("owner3", taskItem2.getNotes().get(6).getNoteAuthor());
			assertEquals("text9", taskItem2.getNotes().get(6).getNoteText());
		}

		// Possible valid paths from Processing

		c = new Command(CommandValue.PROCESS, "owner3", "text12");
		taskItem2.update(c);
		assertEquals("Processing", taskItem2.getStateName());
		assertEquals("owner3", taskItem2.getOwner());
		assertEquals("owner3", taskItem2.getNotes().get(7).getNoteAuthor());
		assertEquals("text12", taskItem2.getNotes().get(7).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner3", "text13");
		taskItem2.update(c);
		assertEquals("Processing", taskItem2.getStateName());
		assertEquals("owner3", taskItem2.getOwner());
		assertEquals("owner3", taskItem2.getNotes().get(8).getNoteAuthor());
		assertEquals("text13", taskItem2.getNotes().get(8).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner3", "text14");
		taskItem2.update(c);
		assertEquals("Backlog", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner3", taskItem2.getNotes().get(9).getNoteAuthor());
		assertEquals("text14", taskItem2.getNotes().get(9).getNoteText());

		c = new Command(CommandValue.CLAIM, "owner4", "text15");
		taskItem2.update(c);
		assertEquals("Owned", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(10).getNoteAuthor());
		assertEquals("text15", taskItem2.getNotes().get(10).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner4", "text16");
		taskItem2.update(c);
		assertEquals("Processing", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(11).getNoteAuthor());
		assertEquals("text16", taskItem2.getNotes().get(11).getNoteText());

		// Possible invalid paths from Processing

		c = new Command(CommandValue.CLAIM, "owner4", "text17");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Processing", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(11).getNoteAuthor());
			assertEquals("text16", taskItem2.getNotes().get(11).getNoteText());
		}

		c = new Command(CommandValue.REJECT, "owner4", "text18");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Processing", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(11).getNoteAuthor());
			assertEquals("text16", taskItem2.getNotes().get(11).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner4", "text19");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Processing", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(11).getNoteAuthor());
			assertEquals("text16", taskItem2.getNotes().get(11).getNoteText());
		}

		// Possible valid paths from Verifying

		c = new Command(CommandValue.VERIFY, "owner4", "text20");
		taskItem2.update(c);
		assertEquals("Verifying", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(12).getNoteAuthor());
		assertEquals("text20", taskItem2.getNotes().get(12).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner4", "text21");
		taskItem2.update(c);
		assertEquals("Processing", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(13).getNoteAuthor());
		assertEquals("text21", taskItem2.getNotes().get(13).getNoteText());

		c = new Command(CommandValue.VERIFY, "owner4", "text22");
		taskItem2.update(c);
		assertEquals("Verifying", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(14).getNoteAuthor());
		assertEquals("text22", taskItem2.getNotes().get(14).getNoteText());

		// Possible invalid paths from Verifying

		c = new Command(CommandValue.VERIFY, "owner4", "text23");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Verifying", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(14).getNoteAuthor());
			assertEquals("text22", taskItem2.getNotes().get(14).getNoteText());
		}

		c = new Command(CommandValue.BACKLOG, "owner4", "text24");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Verifying", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(14).getNoteAuthor());
			assertEquals("text22", taskItem2.getNotes().get(14).getNoteText());
		}

		c = new Command(CommandValue.CLAIM, "owner4", "text25");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Verifying", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(14).getNoteAuthor());
			assertEquals("text22", taskItem2.getNotes().get(14).getNoteText());
		}

		c = new Command(CommandValue.REJECT, "owner4", "text26");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Verifying", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(14).getNoteAuthor());
			assertEquals("text22", taskItem2.getNotes().get(14).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner4", "text27");
		taskItem2.update(c);
		assertEquals("Done", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(15).getNoteAuthor());
		assertEquals("text27", taskItem2.getNotes().get(15).getNoteText());

		// Possible valid paths from Done

		c = new Command(CommandValue.PROCESS, "owner4", "text28");
		taskItem2.update(c);
		assertEquals("Processing", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(16).getNoteAuthor());
		assertEquals("text28", taskItem2.getNotes().get(16).getNoteText());

		c = new Command(CommandValue.VERIFY, "owner4", "text29");
		taskItem2.update(c);
		assertEquals("Verifying", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(17).getNoteAuthor());
		assertEquals("text29", taskItem2.getNotes().get(17).getNoteText());

		c = new Command(CommandValue.COMPLETE, "owner4", "text30");
		taskItem2.update(c);
		assertEquals("Done", taskItem2.getStateName());
		assertEquals("owner4", taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(18).getNoteAuthor());
		assertEquals("text30", taskItem2.getNotes().get(18).getNoteText());

		// Possible invalid paths from Done

		c = new Command(CommandValue.COMPLETE, "owner4", "text31");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(18).getNoteAuthor());
			assertEquals("text30", taskItem2.getNotes().get(18).getNoteText());
		}

		c = new Command(CommandValue.CLAIM, "owner4", "text32");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(18).getNoteAuthor());
			assertEquals("text30", taskItem2.getNotes().get(18).getNoteText());
		}

		c = new Command(CommandValue.REJECT, "owner4", "text33");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(18).getNoteAuthor());
			assertEquals("text30", taskItem2.getNotes().get(18).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner4", "text34");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem2.getStateName());
			assertEquals("owner4", taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(18).getNoteAuthor());
			assertEquals("text30", taskItem2.getNotes().get(18).getNoteText());
		}

		// Possible invalid paths from Rejected, since there are no more valid paths

		c = new Command(CommandValue.BACKLOG, "owner4", "text35");
		taskItem2.update(c);
		assertEquals("Backlog", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(19).getNoteAuthor());
		assertEquals("text35", taskItem2.getNotes().get(19).getNoteText());

		c = new Command(CommandValue.REJECT, "owner4", "text36");
		taskItem2.update(c);
		assertEquals("Rejected", taskItem2.getStateName());
		assertNull(taskItem2.getOwner());
		assertEquals("owner4", taskItem2.getNotes().get(20).getNoteAuthor());
		assertEquals("text36", taskItem2.getNotes().get(20).getNoteText());

		c = new Command(CommandValue.REJECT, "owner4", "text37");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(20).getNoteAuthor());
			assertEquals("text36", taskItem2.getNotes().get(20).getNoteText());
		}

		c = new Command(CommandValue.CLAIM, "owner4", "text38");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(20).getNoteAuthor());
			assertEquals("text36", taskItem2.getNotes().get(20).getNoteText());
		}

		c = new Command(CommandValue.PROCESS, "owner4", "text39");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(20).getNoteAuthor());
			assertEquals("text36", taskItem2.getNotes().get(20).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner4", "text40");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(20).getNoteAuthor());
			assertEquals("text36", taskItem2.getNotes().get(20).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner4", "text41");
		try {
			taskItem2.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem2.getStateName());
			assertNull(taskItem2.getOwner());
			assertEquals("owner4", taskItem2.getNotes().get(20).getNoteAuthor());
			assertEquals("text36", taskItem2.getNotes().get(20).getNoteText());
		}

		// Full valid path

		TaskItem taskItem3 = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "creator", "note");
		assertEquals("Backlog", taskItem3.getStateName());
		assertNull(taskItem3.getOwner());
		assertEquals("creator", taskItem3.getNotes().get(0).getNoteAuthor());
		assertEquals("note", taskItem3.getNotes().get(0).getNoteText());

		c = new Command(CommandValue.CLAIM, "owner", "text");
		taskItem3.update(c);
		assertEquals("Owned", taskItem3.getStateName());
		assertEquals("owner", taskItem3.getOwner());
		assertEquals("owner", taskItem3.getNotes().get(1).getNoteAuthor());
		assertEquals("text", taskItem3.getNotes().get(1).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner", "text2");
		taskItem3.update(c);
		assertEquals("Processing", taskItem3.getStateName());
		assertEquals("owner", taskItem3.getOwner());
		assertEquals("owner", taskItem3.getNotes().get(2).getNoteAuthor());
		assertEquals("text2", taskItem3.getNotes().get(2).getNoteText());

		c = new Command(CommandValue.COMPLETE, "owner", "text3");
		taskItem3.update(c);
		assertEquals("Done", taskItem3.getStateName());
		assertEquals("owner", taskItem3.getOwner());
		assertEquals("owner", taskItem3.getNotes().get(3).getNoteAuthor());
		assertEquals("text3", taskItem3.getNotes().get(3).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner", "text4");
		taskItem3.update(c);
		assertEquals("Backlog", taskItem3.getStateName());
		assertNull(taskItem3.getOwner());
		assertEquals("owner", taskItem3.getNotes().get(4).getNoteAuthor());
		assertEquals("text4", taskItem3.getNotes().get(4).getNoteText());

		c = new Command(CommandValue.REJECT, "owner", "text5");
		taskItem3.update(c);
		assertEquals("Rejected", taskItem3.getStateName());
		assertNull(taskItem3.getOwner());
		assertEquals("owner", taskItem3.getNotes().get(5).getNoteAuthor());
		assertEquals("text5", taskItem3.getNotes().get(5).getNoteText());

		// Possible valid paths from Backlog

		TaskItem taskItem4 = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "creator", "note");
		assertEquals("Backlog", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("creator", taskItem4.getNotes().get(0).getNoteAuthor());
		assertEquals("note", taskItem4.getNotes().get(0).getNoteText());

		c = new Command(CommandValue.REJECT, "owner", "text");
		taskItem4.update(c);
		assertEquals("Rejected", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner", taskItem4.getNotes().get(1).getNoteAuthor());
		assertEquals("text", taskItem4.getNotes().get(1).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner", "text2");
		taskItem4.update(c);
		assertEquals("Backlog", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner", taskItem4.getNotes().get(2).getNoteAuthor());
		assertEquals("text2", taskItem4.getNotes().get(2).getNoteText());

		// Possible invalid paths from Backlog

		c = new Command(CommandValue.BACKLOG, "owner2", "text2.5");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner", taskItem4.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem4.getNotes().get(2).getNoteText());
		}

		c = new Command(CommandValue.PROCESS, "owner2", "text3");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner", taskItem4.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem4.getNotes().get(2).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner2", "text4");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner", taskItem4.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem4.getNotes().get(2).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner2", "text5");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Backlog", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner", taskItem4.getNotes().get(2).getNoteAuthor());
			assertEquals("text2", taskItem4.getNotes().get(2).getNoteText());
		}

		// Possible valid paths from Owned

		c = new Command(CommandValue.CLAIM, "owner2", "text6");
		taskItem4.update(c);
		assertEquals("Owned", taskItem4.getStateName());
		assertEquals("owner2", taskItem4.getOwner());
		assertEquals("owner2", taskItem4.getNotes().get(3).getNoteAuthor());
		assertEquals("text6", taskItem4.getNotes().get(3).getNoteText());

		c = new Command(CommandValue.REJECT, "owner2", "text7");
		taskItem4.update(c);
		assertEquals("Rejected", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner2", taskItem4.getNotes().get(4).getNoteAuthor());
		assertEquals("text7", taskItem4.getNotes().get(4).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner2", "text8");
		taskItem4.update(c);
		assertEquals("Backlog", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner2", taskItem4.getNotes().get(5).getNoteAuthor());
		assertEquals("text8", taskItem4.getNotes().get(5).getNoteText());

		c = new Command(CommandValue.CLAIM, "owner3", "text9");
		taskItem4.update(c);
		assertEquals("Owned", taskItem4.getStateName());
		assertEquals("owner3", taskItem4.getOwner());
		assertEquals("owner3", taskItem4.getNotes().get(6).getNoteAuthor());
		assertEquals("text9", taskItem4.getNotes().get(6).getNoteText());

		// Possible invalid paths from Owned

		c = new Command(CommandValue.CLAIM, "owner3", "text9.5");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Owned", taskItem4.getStateName());
			assertEquals("owner3", taskItem4.getOwner());
			assertEquals("owner3", taskItem4.getNotes().get(6).getNoteAuthor());
			assertEquals("text9", taskItem4.getNotes().get(6).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner3", "text10");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Owned", taskItem4.getStateName());
			assertEquals("owner3", taskItem4.getOwner());
			assertEquals("owner3", taskItem4.getNotes().get(6).getNoteAuthor());
			assertEquals("text9", taskItem4.getNotes().get(6).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner3", "text11");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Owned", taskItem4.getStateName());
			assertEquals("owner3", taskItem4.getOwner());
			assertEquals("owner3", taskItem4.getNotes().get(6).getNoteAuthor());
			assertEquals("text9", taskItem4.getNotes().get(6).getNoteText());
		}

		// Possible valid paths from Processing

		c = new Command(CommandValue.PROCESS, "owner3", "text12");
		taskItem4.update(c);
		assertEquals("Processing", taskItem4.getStateName());
		assertEquals("owner3", taskItem4.getOwner());
		assertEquals("owner3", taskItem4.getNotes().get(7).getNoteAuthor());
		assertEquals("text12", taskItem4.getNotes().get(7).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner3", "text13");
		taskItem4.update(c);
		assertEquals("Processing", taskItem4.getStateName());
		assertEquals("owner3", taskItem4.getOwner());
		assertEquals("owner3", taskItem4.getNotes().get(8).getNoteAuthor());
		assertEquals("text13", taskItem4.getNotes().get(8).getNoteText());

		c = new Command(CommandValue.BACKLOG, "owner3", "text14");
		taskItem4.update(c);
		assertEquals("Backlog", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner3", taskItem4.getNotes().get(9).getNoteAuthor());
		assertEquals("text14", taskItem4.getNotes().get(9).getNoteText());

		c = new Command(CommandValue.CLAIM, "owner4", "text15");
		taskItem4.update(c);
		assertEquals("Owned", taskItem4.getStateName());
		assertEquals("owner4", taskItem4.getOwner());
		assertEquals("owner4", taskItem4.getNotes().get(10).getNoteAuthor());
		assertEquals("text15", taskItem4.getNotes().get(10).getNoteText());

		c = new Command(CommandValue.PROCESS, "owner4", "text16");
		taskItem4.update(c);
		assertEquals("Processing", taskItem4.getStateName());
		assertEquals("owner4", taskItem4.getOwner());
		assertEquals("owner4", taskItem4.getNotes().get(11).getNoteAuthor());
		assertEquals("text16", taskItem4.getNotes().get(11).getNoteText());

		// Possible invalid paths from Processing

		c = new Command(CommandValue.CLAIM, "owner4", "text17");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Processing", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(11).getNoteAuthor());
			assertEquals("text16", taskItem4.getNotes().get(11).getNoteText());
		}

		c = new Command(CommandValue.REJECT, "owner4", "text18");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Processing", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(11).getNoteAuthor());
			assertEquals("text16", taskItem4.getNotes().get(11).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner4", "text18.5");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Processing", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(11).getNoteAuthor());
			assertEquals("text16", taskItem4.getNotes().get(11).getNoteText());
		}

		// Possible valid paths from Done

		c = new Command(CommandValue.PROCESS, "owner4", "text19");
		taskItem4.update(c);
		assertEquals("Processing", taskItem4.getStateName());
		assertEquals("owner4", taskItem4.getOwner());
		assertEquals("owner4", taskItem4.getNotes().get(12).getNoteAuthor());
		assertEquals("text19", taskItem4.getNotes().get(12).getNoteText());

		c = new Command(CommandValue.COMPLETE, "owner4", "text20");
		taskItem4.update(c);
		assertEquals("Done", taskItem4.getStateName());
		assertEquals("owner4", taskItem4.getOwner());
		assertEquals("owner4", taskItem4.getNotes().get(13).getNoteAuthor());
		assertEquals("text20", taskItem4.getNotes().get(13).getNoteText());

		// Possible invalid paths from Done

		c = new Command(CommandValue.COMPLETE, "owner4", "text21");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(13).getNoteAuthor());
			assertEquals("text20", taskItem4.getNotes().get(13).getNoteText());
		}

		c = new Command(CommandValue.CLAIM, "owner4", "text22");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(13).getNoteAuthor());
			assertEquals("text20", taskItem4.getNotes().get(13).getNoteText());
		}

		c = new Command(CommandValue.REJECT, "owner4", "text23");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(13).getNoteAuthor());
			assertEquals("text20", taskItem4.getNotes().get(13).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner4", "text24");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Done", taskItem4.getStateName());
			assertEquals("owner4", taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(13).getNoteAuthor());
			assertEquals("text20", taskItem4.getNotes().get(13).getNoteText());
		}

		// Possible invalid paths from Rejected, since there are no more valid paths

		c = new Command(CommandValue.BACKLOG, "owner4", "text25");
		taskItem4.update(c);
		assertEquals("Backlog", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner4", taskItem4.getNotes().get(14).getNoteAuthor());
		assertEquals("text25", taskItem4.getNotes().get(14).getNoteText());

		c = new Command(CommandValue.REJECT, "owner4", "text26");
		taskItem4.update(c);
		assertEquals("Rejected", taskItem4.getStateName());
		assertNull(taskItem4.getOwner());
		assertEquals("owner4", taskItem4.getNotes().get(15).getNoteAuthor());
		assertEquals("text26", taskItem4.getNotes().get(15).getNoteText());

		c = new Command(CommandValue.REJECT, "owner4", "text27");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(15).getNoteAuthor());
			assertEquals("text26", taskItem4.getNotes().get(15).getNoteText());
		}

		c = new Command(CommandValue.CLAIM, "owner4", "text28");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(15).getNoteAuthor());
			assertEquals("text26", taskItem4.getNotes().get(15).getNoteText());
		}

		c = new Command(CommandValue.PROCESS, "owner4", "text29");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(15).getNoteAuthor());
			assertEquals("text26", taskItem4.getNotes().get(15).getNoteText());
		}

		c = new Command(CommandValue.VERIFY, "owner4", "text30");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(15).getNoteAuthor());
			assertEquals("text26", taskItem4.getNotes().get(15).getNoteText());
		}

		c = new Command(CommandValue.COMPLETE, "owner4", "text31");
		try {
			taskItem4.update(c);
			fail();
		} catch (UnsupportedOperationException uoe) {
			assertEquals("Rejected", taskItem4.getStateName());
			assertNull(taskItem4.getOwner());
			assertEquals("owner4", taskItem4.getNotes().get(15).getNoteAuthor());
			assertEquals("text26", taskItem4.getNotes().get(15).getNoteText());
		}
	}

	/**
	 * Tests getXMLTask().
	 */
	@Test
	public void testGetXMLTask() {
		TaskItem.setCounter(1);

		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");

		Task task = new Task();
		task.setId(1);
		task.setTitle("title");
		task.setType("B");
		task.setCreator("creator");
		NoteList notes = new NoteList();
		NoteItem note = new NoteItem();
		note.setNoteAuthor("creator");
		note.setNoteText("note");
		notes.getNotes().add(note);
		task.setNoteList(notes);

		assertEquals(task.getId(), taskItem.getXMLTask().getId());
		assertEquals(task.getTitle(), taskItem.getXMLTask().getTitle());
		assertEquals(task.getType(), taskItem.getXMLTask().getType());
		assertEquals(task.getCreator(), taskItem.getXMLTask().getCreator());
		assertEquals(task.getNoteList().getNotes().get(0).getNoteAuthor(),
				taskItem.getXMLTask().getNoteList().getNotes().get(0).getNoteAuthor());
		assertEquals(task.getNoteList().getNotes().get(0).getNoteText(),
				taskItem.getXMLTask().getNoteList().getNotes().get(0).getNoteText());
		
		TaskItem taskItem2 = new TaskItem("title2", Type.KNOWLEDGE_ACQUISITION, "creator2", "note2");
		
		Task task2 = new Task();
		task2.setId(2);
		task2.setTitle("title2");
		task2.setType("KA");
		task2.setCreator("creator2");
		NoteList notes2 = new NoteList();
		NoteItem note2 = new NoteItem();
		note2.setNoteAuthor("creator2");
		note2.setNoteText("note2");
		notes2.getNotes().add(note2);
		task2.setNoteList(notes2);
		
		assertEquals(task2.getId(), taskItem2.getXMLTask().getId());
		assertEquals(task2.getTitle(), taskItem2.getXMLTask().getTitle());
		assertEquals(task2.getType(), taskItem2.getXMLTask().getType());
		assertEquals(task2.getCreator(), taskItem2.getXMLTask().getCreator());
		assertEquals(task2.getNoteList().getNotes().get(0).getNoteAuthor(),
				taskItem2.getXMLTask().getNoteList().getNotes().get(0).getNoteAuthor());
		assertEquals(task2.getNoteList().getNotes().get(0).getNoteText(),
				taskItem2.getXMLTask().getNoteList().getNotes().get(0).getNoteText());
	}

	/**
	 * Tests setCounter().
	 */
	@Test
	public void testSetCounter() {
		TaskItem.setCounter(1);
		
		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(1, taskItem.getTaskItemId());
		
		TaskItem taskItem2 = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(2, taskItem2.getTaskItemId());
		
		TaskItem.setCounter(7);
		
		TaskItem taskItem3 = new TaskItem("title", Type.BUG, "creator", "note");
		assertEquals(7, taskItem3.getTaskItemId());
	}

	/**
	 * Tests getNotesArray().
	 */
	@Test
	public void testGetNotesArray() {
		TaskItem.setCounter(1);
		
		TaskItem taskItem = new TaskItem("title", Type.BUG, "creator", "note1");
		Note note1 = new Note("creator", "note1");
		assertEquals(note1.getNoteArray()[0], taskItem.getNotesArray()[0][0]);
		assertEquals(note1.getNoteArray()[1], taskItem.getNotesArray()[0][1]);
		
		
		Command c = new Command(CommandValue.CLAIM, "owner", "note2");
		taskItem.update(c);
		Note note2 = new Note("owner", "note2");
		assertEquals(note1.getNoteArray()[0], taskItem.getNotesArray()[0][0]);
		assertEquals(note1.getNoteArray()[1], taskItem.getNotesArray()[0][1]);
		assertEquals(note2.getNoteArray()[0], taskItem.getNotesArray()[1][0]);
		assertEquals(note2.getNoteArray()[1], taskItem.getNotesArray()[1][1]);
	}
}