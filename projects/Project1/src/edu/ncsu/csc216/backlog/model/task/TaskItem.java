package edu.ncsu.csc216.backlog.model.task;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Concrete class that represents a task item tracked in the Scrum Backlog
 * system. A TaskItem keeps track of all task information including the current
 * state. The state is updated when a Command encapsulating a transition is
 * given to the TaskItem. TaskItem encapsulates the the TaskItemState interface,
 * six concrete TaskItemState classes and one enumeration.
 * 
 * @author Selena Chen
 */
public class TaskItem {

	/** TaskItem's id */
	private int taskId;
	/** TaskItem's state */
	private TaskItemState state;
	/** TaskItem's title */
	private String title;
	/** TaskItem's creator */
	private String creator;
	/** TaskItem's owner */
	private String owner;
	/** Whether or not TaskItem is verified */
	private boolean isVerified;
	/** TaskItem's tye */
	private Type type;
	/** TaskItem's notes */
	private ArrayList<Note> notes;

	/** Backlog state */
	private final TaskItemState backlogState = new BacklogState();
	/** Owned state */
	private final TaskItemState ownedState = new OwnedState();
	/** Processing state */
	private final TaskItemState processingState = new ProcessingState();
	/** Verifying state */
	private final TaskItemState verifyingState = new VerifyingState();
	/** Done state */
	private final TaskItemState doneState = new DoneState();
	/** Rejected state */
	private final TaskItemState rejectedState = new RejectedState();

	/** String representation of the backlog state */
	public static final String BACKLOG_NAME = "Backlog";
	/** String representation of the owned state */
	public static final String OWNED_NAME = "Owned";
	/** String representation of the processing state */
	public static final String PROCESSING_NAME = "Processing";
	/** String representation of the verifying state */
	public static final String VERIFYING_NAME = "Verifying";
	/** String representation of the done state */
	public static final String DONE_NAME = "Done";
	/** String representation of the rejected state */
	public static final String REJECTED_NAME = "Rejected";
	/** String representation of the feature type */
	public static final String T_FEATURE = "F";
	/** String representation of the bug type */
	public static final String T_BUG = "B";
	/** String representation of the technical work type */
	public static final String T_TECHNICAL_WORK = "TW";
	/** String representation of the knowledge type */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";

	/** Counter for the TaskItem's id */
	private static int counter = 1;

	/**
	 * Constructs a TaskItem object with values for title, type, creator, and note.
	 * 
	 * @param title   TaskItem's title
	 * @param type    TaskItem's type
	 * @param creator TaskItem's creator
	 * @param note    TaskItem's note
	 */
	public TaskItem(String title, Type type, String creator, String note) {
		if (title == null || title.equals("") || type == null || type.toString().equals("") || creator == null
				|| creator.equals("") || note == null || note.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.type = type;
		this.creator = creator;
		notes = new ArrayList<Note>();
		notes.add(new Note(creator, note));
		setState(BACKLOG_NAME);
		taskId = counter;
		isVerified = false;
		incrementCounter();
	}

	/**
	 * Constructs a TaskItem object with a Task object.
	 * 
	 * @param task Task object
	 */
	public TaskItem(Task task) {
		if (task.getTitle() == null || task.getTitle().equals("") || task.getType() == null || task.getType().equals("")
				|| task.getCreator() == null || task.getCreator().equals("")
				|| task.getNoteList().getNotes().get(0).getNoteText() == null
				|| task.getNoteList().getNotes().get(0).getNoteText().equals("")) {
			throw new IllegalArgumentException();
		}
		taskId = task.getId();
		title = task.getTitle();
		setType(task.getType());
		creator = task.getCreator();
		owner = task.getOwner();
		setState(task.getState());
		isVerified = task.isVerified();
		notes = new ArrayList<Note>();
		for (int noteIndex = 0; noteIndex < task.getNoteList().getNotes().size(); noteIndex++) {
			notes.add(new Note(task.getNoteList().getNotes().get(noteIndex).getNoteAuthor(),
					task.getNoteList().getNotes().get(noteIndex).getNoteText()));
		}
	}

	/**
	 * Increases the counter for the next TaskItem’s id.
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * Returns the TaskItem's id.
	 * 
	 * @return TaskItem's id
	 */
	public int getTaskItemId() {
		return taskId;
	}

	/**
	 * Returns a String representation of the TaskItem's state name.
	 * 
	 * @return String representation of the TaskItem's state name
	 */
	public String getStateName() {
		return state.getStateName();
	}

	/**
	 * Sets the TaskItem's state.
	 * 
	 * @param stateValue String representation of the TaskItem's state
	 */
	private void setState(String stateValue) {
		if (stateValue.equals(BACKLOG_NAME)) {
			state = backlogState;
		} else if (stateValue.equals(OWNED_NAME)) {
			state = ownedState;
		} else if (stateValue.equals(PROCESSING_NAME)) {
			state = processingState;
		} else if (stateValue.equals(VERIFYING_NAME)) {
			state = verifyingState;
		} else if (stateValue.equals(DONE_NAME)) {
			state = doneState;
		} else if (stateValue.equals(REJECTED_NAME)) {
			state = rejectedState;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Sets the TaskItem's type.
	 * 
	 * @param typeValue String representation of the TaskItem's type
	 */
	private void setType(String typeValue) {
		if (typeValue.equals(T_FEATURE)) {
			type = Type.FEATURE;
		} else if (typeValue.equals(T_BUG)) {
			type = Type.BUG;
		} else if (typeValue.equals(T_TECHNICAL_WORK)) {
			type = Type.TECHNICAL_WORK;
		} else if (typeValue.equals(T_KNOWLEDGE_ACQUISITION)) {
			type = Type.KNOWLEDGE_ACQUISITION;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the TaskItem's type.
	 * 
	 * @return TaskItem's type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Returns a String representation of the TaskItem's type.
	 * 
	 * @return String representation of the TaskItem's type
	 */
	public String getTypeString() {
		String typeString = null;
		if (type.equals(Type.FEATURE)) {
			typeString = "F";
		} else if (type.equals(Type.BUG)) {
			typeString = "B";
		} else if (type.equals(Type.TECHNICAL_WORK)) {
			typeString = "TW";
		} else {
			typeString = "KA";
		}
		return typeString;
	}

	/**
	 * Returns a full String representation of the TaskItem's type.
	 * 
	 * @return Full String representation of the TaskItem's type
	 */
	public String getTypeFullString() {
		String typeString = null;
		if (type.equals(Type.BUG)) {
			typeString = "Bug";
		} else if (type.equals(Type.FEATURE)) {
			typeString = "Feature";
		} else if (type.equals(Type.TECHNICAL_WORK)) {
			typeString = "Technical Work";
		} else if (type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			typeString = "Knowledge Acquisition";
		}
		return typeString;
	}

	/**
	 * Returns the TaskItem's owner.
	 * 
	 * @return TaskItem's owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Returns the TaskItem's title.
	 * 
	 * @return TaskItem's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the TaskItem's creator.
	 * 
	 * @return TaskItem's creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Returns the TaskItem's notes.
	 * 
	 * @return TaskItem's notes
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**
	 * Updates the TaskItem based on the given Command.
	 * 
	 * @param c User command
	 */
	public void update(Command c) {
		state.updateState(c);
	}

	/**
	 * Generates a Task object for writing to a file.
	 * 
	 * @return Task object generated for writing to a file
	 */
	public Task getXMLTask() {
		Task task = new Task();
		task.setId(taskId);
		task.setState(getStateName());
		task.setTitle(title);
		String typeValue = null;
		if (type == Type.BUG) {
			typeValue = "B";
		} else if (type == Type.FEATURE) {
			typeValue = "F";
		} else if (type == Type.TECHNICAL_WORK) {
			typeValue = "TW";
		} else if (type == Type.KNOWLEDGE_ACQUISITION) {
			typeValue = "KA";
		}
		task.setType(typeValue);
		task.setCreator(creator);
		task.setOwner(owner);
		task.setVerified(isVerified);
		NoteList noteList = new NoteList();
		for (int noteIndex = 0; noteIndex < notes.size(); noteIndex++) {
			NoteItem note = new NoteItem();
			note.setNoteAuthor(notes.get(noteIndex).getNoteAuthor());
			note.setNoteText(notes.get(noteIndex).getNoteText());
			noteList.getNotes().add(noteIndex, note);
		}
		task.setNoteList(noteList);
		return task;
	}

	/**
	 * Sets the counter for the TaskItem's id.
	 * 
	 * @param counter Counter for the TaskItem's id
	 * @throws IllegalArgumentException if the new counter is zero or less
	 */
	public static void setCounter(int counter) {
		if (counter <= 0) {
			throw new IllegalArgumentException();
		}
		TaskItem.counter = counter;
	}

	/**
	 * Returns a 2D String array representation of the TaskItem's notes.
	 * 
	 * @return 2D String array representation of the TaskItem's notes
	 */
	public String[][] getNotesArray() {
		String[][] arr = new String[notes.size()][2];
		for (int noteIndex = 0; noteIndex < notes.size(); noteIndex++) {
			arr[noteIndex] = notes.get(noteIndex).getNoteArray();
		}
		return arr;
	}

	/**
	 * Interface for states in the Task State Pattern. All concrete task states must
	 * implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface TaskItemState {

		/**
		 * Update the {@link TaskItem} based on the given {@link Command}. An
		 * {@link UnsupportedOperationException} is throw if the {@link CommandValue} is
		 * not a valid action for the given state.
		 * 
		 * @param c {@link Command} describing the action that will update the
		 *          {@link TaskItem}'s state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a
		 *                                       valid action for the given state.
		 */
		void updateState(Command c);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	}

	/**
	 * Concrete class that represents the backlog state of the Scrum Backlog FSM.
	 * Has methods to update the TaskItem based on a given Command and get the
	 * String representation of the name of the state.
	 * 
	 * @author Selena Chen
	 */
	private class BacklogState implements TaskItemState {

		/**
		 * Constructs a BacklogState object.
		 */
		private BacklogState() {
			state = backlogState;
			isVerified = false;
			owner = null;
		}

		/**
		 * Updates the TaskItem based on the given Command.
		 * 
		 * @param c User command
		 */
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.CLAIM) {
				setState(OWNED_NAME);
				owner = c.getNoteAuthor();
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.REJECT) {
				setState(REJECTED_NAME);
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns a String representation of the TaskItem's state name.
		 * 
		 * @return String representation of the TaskItem's state name
		 */
		public String getStateName() {
			return BACKLOG_NAME;
		}
	}

	/**
	 * Concrete class that represents the owned state of the Scrum Backlog FSM. Has
	 * methods to update the TaskItem based on a given Command and get the String
	 * representation of the name of the state.
	 * 
	 * @author Selena Chen
	 */
	private class OwnedState implements TaskItemState {

		/**
		 * Constructs an OwnedState object.
		 */
		private OwnedState() {
			state = ownedState;
			isVerified = false;
		}

		/**
		 * Updates the TaskItem based on the given Command.
		 * 
		 * @param c User command
		 */
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.PROCESS) {
				setState(PROCESSING_NAME);
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.BACKLOG) {
				setState(BACKLOG_NAME);
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.REJECT) {
				setState(REJECTED_NAME);
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns a String representation of the TaskItem's state name.
		 * 
		 * @return String representation of the TaskItem's state name
		 */
		public String getStateName() {
			return OWNED_NAME;
		}
	}

	/**
	 * Concrete class that represents the processing state of the Scrum Backlog FSM.
	 * Has methods to update the TaskItem based on a given Command and get the
	 * String representation of the name of the state.
	 * 
	 * @author Selena Chen
	 */
	private class ProcessingState implements TaskItemState {

		/**
		 * Constructs a ProcessingState object.
		 */
		private ProcessingState() {
			state = processingState;
			isVerified = false;
		}

		/**
		 * Updates the TaskItem based on the given Command.
		 * 
		 * @param c User command
		 */
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.VERIFY && type != Type.KNOWLEDGE_ACQUISITION) {
				setState(VERIFYING_NAME);
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.BACKLOG) {
				setState(BACKLOG_NAME);
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.PROCESS) {
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.COMPLETE && type == Type.KNOWLEDGE_ACQUISITION) {
				setState(DONE_NAME);
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns a String representation of the TaskItem's state name.
		 * 
		 * @return String representation of the TaskItem's state name
		 */
		public String getStateName() {
			return PROCESSING_NAME;
		}
	}

	/**
	 * Concrete class that represents the verifying state of the Scrum Backlog FSM.
	 * Has methods to update the TaskItem based on a given Command and get the
	 * String representation of the name of the state.
	 * 
	 * @author Selena Chen
	 */
	private class VerifyingState implements TaskItemState {

		/**
		 * Constructs a VerifyingState object.
		 */
		private VerifyingState() {
			state = verifyingState;
		}

		/**
		 * Updates the TaskItem based on the given Command.
		 * 
		 * @param c User command
		 */
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.COMPLETE) {
				setState(DONE_NAME);
				isVerified = true;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.PROCESS) {
				setState(PROCESSING_NAME);
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns a String representation of the TaskItem's state name.
		 * 
		 * @return String representation of the TaskItem's state name
		 */
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}

	/**
	 * Concrete class that represents the done state of the Scrum Backlog FSM. Has
	 * methods to update the TaskItem based on a given Command and get the String
	 * representation of the name of the state.
	 * 
	 * @author Selena Chen
	 */
	private class DoneState implements TaskItemState {

		/**
		 * Constructs a DoneState object.
		 */
		private DoneState() {
			state = doneState;
		}

		/**
		 * Updates the TaskItem based on the given Command.
		 * 
		 * @param c User command
		 */
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BACKLOG) {
				setState(BACKLOG_NAME);
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.PROCESS) {
				setState(PROCESSING_NAME);
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns a String representation of the TaskItem's state name.
		 * 
		 * @return String representation of the TaskItem's state name
		 */
		public String getStateName() {
			return DONE_NAME;
		}
	}

	/**
	 * Concrete class that represents the rejected state of the Scrum Backlog FSM.
	 * Has methods to update the TaskItem based on a given Command and get the
	 * String representation of the name of the state.
	 * 
	 * @author Selena Chen
	 */
	private class RejectedState implements TaskItemState {

		/**
		 * Constructs a RejectedState object.
		 */
		private RejectedState() {
			state = rejectedState;
			owner = null;
			isVerified = false;
		}

		/**
		 * Updates the TaskItem based on the given Command.
		 * 
		 * @param c User command
		 */
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BACKLOG) {
				setState(BACKLOG_NAME);
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns a String representation of the TaskItem's state name.
		 * 
		 * @return String representation of the TaskItem's state name
		 */
		public String getStateName() {
			return REJECTED_NAME;
		}
	}

	/** Lists possible task types */
	public enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }
}