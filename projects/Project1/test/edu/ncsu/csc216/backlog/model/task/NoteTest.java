package edu.ncsu.csc216.backlog.model.task;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Note.
 * 
 * @author Selena Chen
 */
public class NoteTest {

	/**
	 * Tests the Note constructor with values for all fields.
	 */
	@Test
	public void testNote() {
		Note note = new Note("author", "text");
		
		assertEquals("author", note.getNoteAuthor());
		assertEquals("text", note.getNoteText());
	}

	/**
	 * Tests getNoteAuthor().
	 */
	@Test
	public void testGetNoteAuthor() {
		Note note = new Note("author", "text");
		
		assertEquals("author", note.getNoteAuthor());
	}

	/**
	 * Tests getNoteText().
	 */
	@Test
	public void testGetNoteText() {
		Note note = new Note("author", "text");
		
		assertEquals("text", note.getNoteText());
	}

	/**
	 * Tests getNoteArray().
	 */
	@Test
	public void testGetNoteArray() {
		Note note = new Note("author", "text");
		
		assertEquals("author", note.getNoteArray()[0]);
		assertEquals("text", note.getNoteArray()[1]);
	}
}