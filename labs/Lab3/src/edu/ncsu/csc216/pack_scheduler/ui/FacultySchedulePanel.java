package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Creates a user interface for Faculty to view their schedule and course rolls.
 * 
 * @author Selena Chen
 * @author Atharva Mahajan
 * @author Sarah Morton
 */
public class FacultySchedulePanel extends JPanel {

	/** ID number used for object serialization */
	private static final long serialVersionUID = 1L;
	/** Course Roll table */
	private JTable tableRoll;
	/** Faculty Schedule table */
	private JTable tableSchedule;
	/** Model of the Faculty Schedule */
	private CourseTableModel scheduleTableModel;
	/** Model of the Course Roll */
	private StudentTableModel rollTableModel;
	/** Border type */
	private Border lowerEtched;
	/** Scroll pane for the Faculty Schedule */
	private JScrollPane scrollSchedule;
	/** Scroll pane for the Course Roll */
	private JScrollPane scrollRoll;
	/** Panel for the Course Details */
	private JPanel pnlCourseDetails;
	/** Label for the title of the Course name */
	private JLabel lblNameTitle;
	/** Title label for the Course section */
	private JLabel lblSectionTitle;
	/** Title label for the Course instructor */
	private JLabel lblInstructorTitle;
	/** Title label for the Course title */
	private JLabel lblTitleTitle;
	/** Title label for the Course credits */
	private JLabel lblCreditsTitle;
	/** Title label for the Course meeting days */
	private JLabel lblMeetingTitle;
	/** Title label for the Course enrollment cap */
	private JLabel lblEnrollmentCapTitle;
	/** Title label for the Course open seats */
	private JLabel lblOpenSeatsTitle;
	/** Title label for the Course waitlist */
	private JLabel lblWaitlistTitle;
	/** Label for the Course name */
	private JLabel lblName;
	/** Label for the Course section */
	private JLabel lblSection;
	/** Label for the Course instructor */
	private JLabel lblInstructor;
	/** Label for the Course title */
	private JLabel lblTitle;
	/** Label for the Course credits */
	private JLabel lblCredits;
	/** Label for the Course meeting days */
	private JLabel lblMeeting;
	/** Label for the Course enrollment cap */
	private JLabel lblEnrollmentCap;
	/** Label for the Course open seats */
	private JLabel lblOpenSeats;
	/** Label for the Course waitlist */
	private JLabel lblWaitlist;
	/** Faculty's schedule */
	private FacultySchedule schedule;

	/**
	 * Constructs a FacultySchedulePanel object.
	 */
	public FacultySchedulePanel() {
		super(new GridBagLayout());
		initFacultySchedule();
		initCourseDetails();
		initCourseRoll();
//		schedule = RegistrationManager.getInstance().getFacultyDirectory()
//				.getFacultyById(RegistrationManager.getInstance().getCurrentUser().getId()).getSchedule();

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollSchedule, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollRoll, c);
	}

	/**
	 * Initializes the Faculty Schedule panel.
	 */
	private void initFacultySchedule() {
		scheduleTableModel = new CourseTableModel();
		
		tableSchedule = new JTable(scheduleTableModel) {
			private static final long serialVersionUID = 1L;

			/**
			 * Set custom tool tips for cells
			 * 
			 * @param e MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);

				return (String) scheduleTableModel.getValueAt(rowIndex, realColumnIndex);
			}
		};
		
		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);
		tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				Course c = RegistrationManager.getInstance().getCourseCatalog().getCourseFromCatalog(name, section);
				updateCourseDetails(c);
			}
		});
		
		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);

		scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Faculty Schedule");

		scrollSchedule.setBorder(border);
		scrollSchedule.setToolTipText("Student Directory");
	}

	/**
	 * Initializes the Course Roll panel.
	 */
	private void initCourseRoll() {
		rollTableModel = new StudentTableModel();
		tableRoll = new JTable(rollTableModel);
		tableRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableRoll.setFillsViewportHeight(true);

		scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");

		scrollRoll.setBorder(border);
		scrollRoll.setToolTipText("Course Roll");
	}

	/**
	 * Initializes the Course Details panel.
	 */
	private void initCourseDetails() {
		pnlCourseDetails = new JPanel();

		lblNameTitle = new JLabel("Name: ");
		lblSectionTitle = new JLabel("Section: ");
		lblTitleTitle = new JLabel("Title: ");
		lblInstructorTitle = new JLabel("Instructor: ");
		lblCreditsTitle = new JLabel("Credits: ");
		lblMeetingTitle = new JLabel("Meeting: ");
		lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
		lblOpenSeatsTitle = new JLabel("Open Seats: ");
		lblWaitlistTitle = new JLabel("Waitlist: ");

		lblName = new JLabel("");
		lblSection = new JLabel("");
		lblTitle = new JLabel("");
		lblInstructor = new JLabel("");
		lblCredits = new JLabel("");
		lblMeeting = new JLabel("");
		lblEnrollmentCap = new JLabel("");
		lblOpenSeats = new JLabel("");
		lblWaitlist = new JLabel("");

		pnlCourseDetails.setLayout(new GridLayout(6, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);

		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);

		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);

		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);

		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 6));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);
		pnlEnrollment.add(lblWaitlistTitle);
		pnlEnrollment.add(lblWaitlist);

		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);

		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
	}

	/**
	 * Updates tables.
	 */
	public void updateTables() {
		scheduleTableModel.updateData();
		rollTableModel.updateData();
	}
	
	/**
	 * Updates the pnlCourseDetails with full information about the most recently
	 * selected course.
	 */
	private void updateCourseDetails(Course c) {
		if (c != null) {
			lblName.setText(c.getName());
			lblSection.setText(c.getSection());
			lblTitle.setText(c.getTitle());
			lblInstructor.setText(c.getInstructorId());
			lblCredits.setText("" + c.getCredits());
			lblMeeting.setText(c.getMeetingString());
			lblEnrollmentCap.setText("" + c.getCourseRoll().getEnrollmentCap());
			lblOpenSeats.setText("" + c.getCourseRoll().getOpenSeats());
			lblWaitlist.setText("" + c.getCourseRoll().getNumberOnWaitlist());
		}
	}

	/**
	 * {@link CourseTableModel} is the object underlying the {@link JTable} object
	 * that displays the list of {@link Course}s to the user.
	 * 
	 * @author Sarah Heckman
	 */
	private class CourseTableModel extends AbstractTableModel {

		/** ID number used for object serialization */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "Name", "Section", "Title", "Meeting Days", "Open Seats" };
		/** Data stored in the table */
		private Object[][] data;

		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * 
		 * @return the number of columns in the table
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * 
		 * @return the number of rows in the table
		 */
		public int getRowCount() {
			if (data == null)
				return 0;
			return data.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @return the column name at the given column
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * 
		 * @return the data at the given location
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value  Object to modify in the data
		 * @param row    location to modify the data
		 * @param column location to modify the data
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * Updates the given model with {@link Course} information from the
		 * {@link WolfScheduler}.
		 */
		private void updateData() {
			if (RegistrationManager.getInstance().getCurrentUser() != null) {
				Faculty currentUser = (Faculty) RegistrationManager.getInstance().getCurrentUser();
				schedule = currentUser.getSchedule();
				data = schedule.getScheduledCourses();

//				FacultySchedulePanel.this.repaint();
//				FacultySchedulePanel.this.validate();
			}
			FacultySchedulePanel.this.repaint();
			FacultySchedulePanel.this.validate();
		}
	}

	/**
	 * StudentTableModel is the object underlying the {@link JTable} object that
	 * displays the list of Students to the user.
	 * 
	 * @author Sarah Heckman
	 */
	private class StudentTableModel extends AbstractTableModel {

		/** ID number used for object serialization */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "First Name", "Last Name", "Student ID" };
		/** Data stored in the table */
		private Object[][] data;

		/**
		 * Constructs the StudentTableModel by requesting the latest information from
		 * the {@link RequirementTrackerModel}.
		 */
		public StudentTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * 
		 * @return the number of columns in the table
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * 
		 * @return the number of rows in the table
		 */
		public int getRowCount() {
			if (data == null)
				return 0;
			return data.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @return the column name at the given column
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * 
		 * @return the data at the given location
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value  Object to modify in the data
		 * @param row    location to modify the data
		 * @param column location to modify the data
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * Updates the given model with {@link Student} information from the
		 * {@link StudentDirectory}.
		 */
		public void updateData() {
			boolean found = false;
			LinkedList<Student> roll = new LinkedList<Student>();
			for (int i = 0; i < RegistrationManager.getInstance().getStudentDirectory()
					.getStudentDirectory().length; i++) {
				Student studentFromDirectory = RegistrationManager.getInstance().getStudentDirectory().getStudentById(
						RegistrationManager.getInstance().getStudentDirectory().getStudentDirectory()[i][2]);
				for (int j = 0; j < studentFromDirectory.getSchedule().getScheduledCourses().length; j++) {
					for (int k = 0; k < schedule.getNumScheduledCourses(); k++) {
						if (schedule.getScheduledCourses()[k][0]
								.equals(studentFromDirectory.getSchedule().getScheduledCourses()[j][0])
								&& schedule.getScheduledCourses()[k][1]
										.equals(studentFromDirectory.getSchedule().getScheduledCourses()[j][1])) {
							roll.add(studentFromDirectory);
							found = true;
							break;
						}
						if (found) {
							break;
						}
					}
					if (found) {
						break;
					}

				}
			}

			data = new Object[roll.size()][3];
			for (int i = 0; i < roll.size(); i++) {
				data[i][0] = roll.get(i).getFirstName();
				data[i][1] = roll.get(i).getLastName();
				data[i][2] = roll.get(i).getId();
			}
			
			FacultySchedulePanel.this.validate();
			FacultySchedulePanel.this.repaint();
		}
	}
}