package edu.ncsu.csc216.backlog.model.scrum_backlog;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Concrete class that maintains a current list of TaskItems in the Scrum
 * Backlog system. Has methods to add TaskItems, empty the list, add XML tasks,
 * get TaskItems (filtering by owner, creator, and id as well), execute a
 * Command, and delete tasks.
 * 
 * @author Selena Chen
 */
public class TaskItemList {

	/** Initial counter for the TaskItem's id */
	private static final int INITIAL_COUNTER = 1;

	/** List of TaskItems */
	private ArrayList<TaskItem> tasks;

	/**
	 * Constructs a TaskItemList object.
	 */
	public TaskItemList() {
		emptyList();
	}

	/**
	 * Empties the list of TaskItems.
	 */
	private void emptyList() {
		tasks = new ArrayList<TaskItem>();
		TaskItem.setCounter(INITIAL_COUNTER);
	}

	/**
	 * Adds a TaskItem to the list of TaskItems given a title, type, creator, and
	 * note.
	 * 
	 * @param title   TaskItem's title
	 * @param type    TaskItem's type
	 * @param creator TaskItem's creator
	 * @param note    TaskItem's note
	 * @return TaskItem's id
	 */
	public int addTaskItem(String title, Type type, String creator, String note) {
		TaskItem task = new TaskItem(title, type, creator, note);
		tasks.add(task);
		return task.getTaskItemId();
	}

	/**
	 * Adds a List of Tasks from the XML library to the list of TaskItems.
	 * 
	 * @param tasks List of Tasks from the XML library
	 */
	public void addXMLTasks(List<Task> tasks) {
		int maxId = 0;
		for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
			TaskItem task = new TaskItem(tasks.get(taskIndex));
			this.tasks.add(task);
			if (tasks.get(taskIndex).getId() > maxId) {
				maxId = tasks.get(taskIndex).getId();
			}
		}
		TaskItem.setCounter(maxId + 1);
	}

	/**
	 * Returns the list of TaskItems.
	 * 
	 * @return list of TaskItems
	 */
	public List<TaskItem> getTaskItems() {
		return tasks;
	}

	/**
	 * Returns a sublist of the list of TaskItems filtered by a given TaskItem's
	 * owner.
	 * 
	 * @param owner Given TaskItem's owner
	 * @return sublist of the list of TaskItems filtered by a given TaskItem's owner
	 */
	public List<TaskItem> getTaskItemsByOwner(String owner) {
		if (owner == null || owner.equals("")) {
			throw new IllegalArgumentException();
		}
		ArrayList<TaskItem> tasksByOwner = new ArrayList<TaskItem>();
		for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
			if (tasks.get(taskIndex).getOwner() != null && tasks.get(taskIndex).getOwner().equals(owner)) {
				TaskItem task = tasks.get(taskIndex);
				tasksByOwner.add(task);
			}
		}
		return tasksByOwner;
	}

	/**
	 * Returns a sublist of the list of TaskItems filtered by a given TaskItem's
	 * creator.
	 * 
	 * @param creator Given TaskItem's creator
	 * @return sublist of the list of TaskItems filtered by a given TaskItem's
	 *         creator
	 */
	public List<TaskItem> getTaskItemsByCreator(String creator) {
		if (creator == null || creator.equals("")) {
			throw new IllegalArgumentException();
		}
		ArrayList<TaskItem> tasksByCreator = new ArrayList<TaskItem>();
		for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
			if (tasks.get(taskIndex).getCreator() != null && tasks.get(taskIndex).getCreator().equals(creator)) {
				TaskItem task = tasks.get(taskIndex);
				tasksByCreator.add(task);
			}
		}
		return tasksByCreator;
	}

	/**
	 * Returns the TaskItem with the given id from the list of TaskItems.
	 * 
	 * @param id Given TaskItem's id
	 * @return TaskItem with the given id from the list of TaskItems
	 */
	public TaskItem getTaskItemById(int id) {
		TaskItem task = null;
		for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
			if (tasks.get(taskIndex) != null && tasks.get(taskIndex).getTaskItemId() == id) {
				task = tasks.get(taskIndex);
			}
		}
		return task;
	}

	/**
	 * Updates a TaskItem with the given id in the list of TaskItems through the
	 * execution of a Command.
	 * 
	 * @param id Id of the TaskItem to be updated
	 * @param c  User command
	 */
	public void executeCommand(int id, Command c) {
		for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
			if (tasks.get(taskIndex).getTaskItemId() == id) {
				tasks.get(taskIndex).update(c);
			}
		}
	}

	/**
	 * Deletes a TaskItem with the given id from the list of TaskItems.
	 * 
	 * @param id Id of the TaskItem to be deleted
	 */
	public void deleteTaskItemById(int id) {
		tasks.remove(getTaskItemById(id));
	}
}