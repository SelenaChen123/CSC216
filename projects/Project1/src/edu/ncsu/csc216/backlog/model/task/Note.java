package edu.ncsu.csc216.backlog.model.task;

/**
 * Concrete class representing a note’s contents and the author. Note
 * encapsulates the author and note text of a user’s interaction with a
 * TaskItem. Has public getters and private setters for the note author and note
 * text. Also has a method to get the note array.
 * 
 * @author Selena Chen
 */
public class Note {

	/** Note's author */
	private String noteAuthor;
	/** Note's text */
	private String noteText;

	/**
	 * Constructs a Note object with values for all fields.
	 * 
	 * @param noteAuthor Note's author
	 * @param noteText   Note's text
	 */
	public Note(String noteAuthor, String noteText) {
		setNoteAuthor(noteAuthor);
		setNoteText(noteText);
	}

	/**
	 * Returns the Note's author.
	 * 
	 * @return Note's author
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}

	/**
	 * Sets the Note's author.
	 * 
	 * @param noteAuthor Note's author
	 */
	private void setNoteAuthor(String noteAuthor) {
		if (noteAuthor == null || noteAuthor.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteAuthor = noteAuthor;
	}

	/**
	 * Returns the Note's text.
	 * 
	 * @return Note's text
	 */
	public String getNoteText() {
		return noteText;
	}

	/**
	 * Sets the Note's text.
	 * 
	 * @param noteText Note's text
	 */
	private void setNoteText(String noteText) {
		if (noteText == null || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteText = noteText;
	}

	/**
	 * Returns an array of length 2 with the author at index 0 and the text at index
	 * 1. This is used when creating a list of notes to display in the GUI.
	 * 
	 * @return String array of length 2 with the author at index 0 and the text at
	 *         index 1
	 */
	public String[] getNoteArray() {
		String[] arr = new String[2];
		arr[0] = noteAuthor;
		arr[1] = noteText;
		return arr;
	}
}