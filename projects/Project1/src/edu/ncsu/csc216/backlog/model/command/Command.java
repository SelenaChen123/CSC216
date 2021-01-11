package edu.ncsu.csc216.backlog.model.command;

/**
 * Encapsulates the information about a user command that would lead to a
 * transition. Includes enumerations for for the possible commands (which
 * correspond to a button click by a user) that can cause transitions. Also has
 * getters for the note author and note text.
 * 
 * @author Selena Chen
 */
public class Command {

	/** Note's author */
	private String noteAuthor;
	/** Note's text */
	private String noteText;
	/** Command's value */
	private CommandValue c;

	/**
	 * Constructs a Command object with values for all fields.
	 * 
	 * @param c          Command's value
	 * @param noteAuthor Note's author
	 * @param noteText   Note's text
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) {
		if (c == null || noteAuthor == null || noteAuthor.equals("") || noteText == null || noteText.equals("")) {
			throw new IllegalArgumentException();
		} else {
			this.c = c;
			this.noteAuthor = noteAuthor;
			this.noteText = noteText;
		}
	}

	/**
	 * Return's Command's value.
	 * 
	 * @return Command's value
	 */
	public CommandValue getCommand() {
		return c;
	}

	/**
	 * Return's the Note's text.
	 * 
	 * @return Note's text
	 */
	public String getNoteText() {
		return noteText;
	}

	/**
	 * Return's the Note's author.
	 * 
	 * @return Note's author
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}

	/** Lists possible commands */
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }
}