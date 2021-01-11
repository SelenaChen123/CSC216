package edu.ncsu.csc216.backlog.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;

/**
 * Tests Command.
 * 
 * @author Selena Chen
 */
public class CommandTest {

	/**
	 * Tests the Command constructor with values for all fields.
	 */
	@Test
	public void testCommand() {
		Command c = new Command(CommandValue.BACKLOG, "author", "text");
		assertEquals(CommandValue.BACKLOG, c.getCommand());
		assertEquals("author", c.getNoteAuthor());
		assertEquals("text", c.getNoteText());

		try {
			c = new Command(null, "author", "text");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(CommandValue.BACKLOG, c.getCommand());
			assertEquals("author", c.getNoteAuthor());
			assertEquals("text", c.getNoteText());
		}

		try {
			c = new Command(CommandValue.CLAIM, null, "text");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(CommandValue.BACKLOG, c.getCommand());
			assertEquals("author", c.getNoteAuthor());
			assertEquals("text", c.getNoteText());
		}
		
		try {
			c = new Command(CommandValue.PROCESS, "", "text");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(CommandValue.BACKLOG, c.getCommand());
			assertEquals("author", c.getNoteAuthor());
			assertEquals("text", c.getNoteText());
		}
		
		try {
			c = new Command(CommandValue.VERIFY, "author", null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(CommandValue.BACKLOG, c.getCommand());
			assertEquals("author", c.getNoteAuthor());
			assertEquals("text", c.getNoteText());
		}
		
		try {
			c = new Command(CommandValue.COMPLETE, "author", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(CommandValue.BACKLOG, c.getCommand());
			assertEquals("author", c.getNoteAuthor());
			assertEquals("text", c.getNoteText());
		}
	}

	/**
	 * Tests getCommand().
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(CommandValue.BACKLOG, "author", "text");
		assertEquals(CommandValue.BACKLOG, c.getCommand());
		
		c = new Command(CommandValue.CLAIM, "author", "text");
		assertEquals(CommandValue.CLAIM, c.getCommand());
		
		c = new Command(CommandValue.PROCESS, "author", "text");
		assertEquals(CommandValue.PROCESS, c.getCommand());
		
		c = new Command(CommandValue.VERIFY, "author", "text");
		assertEquals(CommandValue.VERIFY, c.getCommand());
		
		c = new Command(CommandValue.COMPLETE, "author", "text");
		assertEquals(CommandValue.COMPLETE, c.getCommand());
		
		c = new Command(CommandValue.REJECT, "author", "text");
		assertEquals(CommandValue.REJECT, c.getCommand());
	}

	/**
	 * Tests getNoteText().
	 */
	@Test
	public void testGetNoteText() {
		Command c = new Command(CommandValue.BACKLOG, "author", "text");
		assertEquals("text", c.getNoteText());
	}

	/**
	 * Tests getNoteAuthor().
	 */
	@Test
	public void testGetNoteAuthor() {
		Command c = new Command(CommandValue.BACKLOG, "author", "text");
		assertEquals("author", c.getNoteAuthor());
	}
}