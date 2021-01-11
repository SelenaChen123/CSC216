package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Reads Activity records from text files. Writes a set of Activity records to a
 * file.
 * 
 * @author Selena Chen
 */
public class ActivityRecordIO {

	/**
	 * Writes the given list of Activities to an output file
	 * 
	 * @param fileName file to save to
	 * @param courses  list of course to save
	 * @throws IOException if the file cannot be written
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (Activity c : courses) {
			fileWriter.println(c.toString());
		}
		fileWriter.close();
	}
}