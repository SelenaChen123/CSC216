package edu.ncsu.csc216.backlog.model.scrum_backlog;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;
import edu.ncsu.csc216.task.xml.TaskWriter;

/**
 * Concrete class that maintains the TaskItemList and handles Commands from the
 * GUI. ScrumBacklogModel implements the Singleton Design Pattern. Works with
 * the XML files that contain the TaskItems in a file when the application is
 * not in use. Also provides information to the GUI. Has methods to get an
 * instance of itself, save Tasks to a file, load Tasks from a file, create a
 * new TaskItemList, get the TaskItemList as an array (filtered by creator and
 * owner as well), get a TaskItem by id, execute a Command, delete a TaskItem by
 * id, and add a TaskItem.
 * 
 * @author Selena Chen
 */
public class ScrumBacklogModel {

	/** List of TaskItems */
	private TaskItemList taskItemList;
	/** Instance of the ScrumBacklogModel */
	private static ScrumBacklogModel singleton;

	/**
	 * Constructs a ScrumBacklogModel object.
	 */
	private ScrumBacklogModel() {
		taskItemList = new TaskItemList();
	}

	/**
	 * Returns an instance of the ScrumBacklogModel.
	 * 
	 * @return Instance of the ScrumBacklogModel
	 */
	public static ScrumBacklogModel getInstance() {
		if (singleton == null) {
			singleton = new ScrumBacklogModel();
		}
		return singleton;
	}

	/**
	 * Saves the TaskItemList to a file.
	 * 
	 * @param fileName File to be saved to
	 */
	public void saveTasksToFile(String fileName) {
		try {
			TaskWriter taskWriter = new TaskWriter(fileName);
			for (int taskIndex = 0; taskIndex < taskItemList.getTaskItems().size(); taskIndex++) {
				taskWriter.addItem(taskItemList.getTaskItemById(taskIndex + 1).getXMLTask());
			}
			taskWriter.marshal();
		} catch (TaskIOException ioe) {
			throw new IllegalArgumentException();
		} catch (NullPointerException npe) {
			// Caught a null pointer exception
		}
	}

	/**
	 * Loads a list of TaskItems from a file.
	 * 
	 * @param fileName File to be saved to
	 */
	public void loadTasksFromFile(String fileName) {
		try {
			TaskReader taskReader = new TaskReader(fileName);
			TaskItemList list = new TaskItemList();
			list.addXMLTasks(taskReader.getTasks());
			taskItemList = list;
		} catch (TaskIOException ioe) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates a new TaskItemList.
	 */
	public void createNewTaskItemList() {
		taskItemList = new TaskItemList();
	}

	/**
	 * Returns the TaskItemList as a 2D array of Objects.
	 * 
	 * @return TaskItemList as a 2D array of Objects
	 */
	public Object[][] getTaskItemListAsArray() {
		Object[][] arr = new Object[taskItemList.getTaskItems().size()][3];
		for (int arrIndex = 0; arrIndex < arr.length; arrIndex++) {
			arr[arrIndex][0] = taskItemList.getTaskItems().get(arrIndex).getTaskItemId();
			arr[arrIndex][1] = taskItemList.getTaskItems().get(arrIndex).getStateName();
			arr[arrIndex][2] = taskItemList.getTaskItems().get(arrIndex).getTitle();
		}
		return arr;
	}

	/**
	 * Returns the TaskItemList as a 2D array of Objects filtered by a given
	 * TaskItem's owner.
	 * 
	 * @param owner Given TaskItem's owner
	 * @return TaskItemList as a 2D array of Objects filtered by a given TaskItem's
	 *         owner
	 */
	public Object[][] getTaskItemListByOwnerAsArray(String owner) {
		if (owner == null) {
			throw new IllegalArgumentException();
		}
		Object[][] arr = new Object[taskItemList.getTaskItemsByOwner(owner).size()][3];
		for (int arrIndex = 0; arrIndex < arr.length; arrIndex++) {
			arr[arrIndex][0] = taskItemList.getTaskItemsByOwner(owner).get(arrIndex).getTaskItemId();
			arr[arrIndex][1] = taskItemList.getTaskItemsByOwner(owner).get(arrIndex).getStateName();
			arr[arrIndex][2] = taskItemList.getTaskItemsByOwner(owner).get(arrIndex).getTitle();
		}
		return arr;
	}

	/**
	 * Returns the TaskItemList as a 2D array of Objects filtered by a given
	 * TaskItem's creator.
	 * 
	 * @param creator Given TaskItem's creator
	 * @return TaskItemList as a 2D array of Objects filtered by a given TaskItem's
	 *         creator
	 */
	public Object[][] getTaskItemListByCreatorAsArray(String creator) {
		if (creator == null) {
			throw new IllegalArgumentException();
		}
		Object[][] arr = new Object[taskItemList.getTaskItemsByCreator(creator).size()][3];
		for (int arrIndex = 0; arrIndex < arr.length; arrIndex++) {
			arr[arrIndex][0] = taskItemList.getTaskItemsByCreator(creator).get(arrIndex).getTaskItemId();
			arr[arrIndex][1] = taskItemList.getTaskItemsByCreator(creator).get(arrIndex).getStateName();
			arr[arrIndex][2] = taskItemList.getTaskItemsByCreator(creator).get(arrIndex).getTitle();
		}
		return arr;
	}

	/**
	 * Returns a TaskItem with the given id from the TaskItemList.
	 * 
	 * @param id Given TaskItem's id
	 * @return TaskItem with the given id from the TaskItemList
	 */
	public TaskItem getTaskItemById(int id) {
		return taskItemList.getTaskItemById(id);
	}

	/**
	 * Updates a TaskItem with the given id in the TaskItemList through the
	 * execution of a Command.
	 * 
	 * @param id Id of the TaskItem to be updated
	 * @param c  User command
	 */
	public void executeCommand(int id, Command c) {
		for (int taskIndex = 0; taskIndex < taskItemList.getTaskItems().size(); taskIndex++) {
			if (taskItemList.getTaskItems().get(taskIndex).getTaskItemId() == id) {
				taskItemList.getTaskItems().get(taskIndex).update(c);
			}
		}
	}

	/**
	 * Deletes a TaskItem with the given id from the TaskItemList.
	 * 
	 * @param id Id of the TaskItem to be deleted
	 */
	public void deleteTaskItemById(int id) {
		taskItemList.deleteTaskItemById(id);
	}

	/**
	 * Adds a TaskItem to the TaskItemList given a title, type, creator, and note.
	 * 
	 * @param title   TaskItem's title
	 * @param type    TaskItem's type
	 * @param creator TaskItem's creator
	 * @param note    TaskItem's note
	 */
	public void addTaskItemToList(String title, Type type, String creator, String note) {
		taskItemList.addTaskItem(title, type, creator, note);
	}
}