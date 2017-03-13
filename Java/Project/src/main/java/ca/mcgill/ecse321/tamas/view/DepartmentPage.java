package ca.mcgill.ecse321.tamas.view;


//GUI classes
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

//model classes
import ca.mcgill.ecse321.tamas.controller.InstructorController;
import ca.mcgill.ecse321.tamas.controller.InvalidInputException;
import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.*;

//Controller class
import ca.mcgill.ecse321.tamas.controller.DepartmentController;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class DepartmentPage extends JFrame {

    private JPanel contentPane;
    private JTextField skillsRequiredField;
    private JTextField experienceRequiredField;
    private JTextField jobDescriptionField;
    private JTextField studentNameField;
    private JTextField emailField;
    private JTextField studentYearField;
    private JTextField jobPreferenceField;
    private JTextField studentIDField;
    private JTextField studentHoursField;
    private JTextField courseNumberField;
    private JTextField studentIDForApplyingField;

    //My own private fields
    private final JRadioButton TARadio;
    private final JRadioButton graderRadio;
    private final JRadioButton undergraduateRadioForRegister;
    private final JRadioButton graduateRadioForRegister;
    private JSpinner createNewJobSpinner;
    private JDatePickerImpl offerDeadlineDatePicker;


    private JComboBox<String> jobListForPublishJobPosting;
    private JComboBox<String> jobListForStudentApplyJob;
    private JComboBox<String> courseList;


    private Integer selectedJobForPublishJobPosting = -1;
    private Integer selectedJobForStudentApply = -1;
    private Integer selectedCourse = -1;

    Department department;


    /**
     * Create the frame.
     */

    //TODO: READ ME
    /* in DepartmentPage() below, you will find the construction of the Swing UI as well as
     * the event handlers. Event handlers are used when a button is click so this where most of
     * the interesting code lives. There are 4 major event handlers, one for each button. Note that
     *  the event handler for "Creating a job" is not complete at all, I will write it for deliverable 3*/

    public DepartmentPage(final Department department) {

        this.department = department;
        //create a department in order to get a department controller

        final DepartmentController controller = new DepartmentController(department);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel jobIDLabel = new JLabel("Job ID");
        jobIDLabel.setBounds(10, 40, 90, 16);
        contentPane.add(jobIDLabel);

        JLabel skillsRequiredLabel = new JLabel("Skills required");
        skillsRequiredLabel.setBounds(10, 68, 90, 16);
        contentPane.add(skillsRequiredLabel);

        skillsRequiredField = new JTextField();
        skillsRequiredField.setBounds(112, 63, 100, 26);
        contentPane.add(skillsRequiredField);
        skillsRequiredField.setColumns(10);

        JLabel experienceRequiredLabel = new JLabel("Exp. required");
        experienceRequiredLabel.setBounds(10, 96, 90, 16);
        contentPane.add(experienceRequiredLabel);

        experienceRequiredField = new JTextField();
        experienceRequiredField.setBounds(112, 91, 100, 26);
        contentPane.add(experienceRequiredField);
        experienceRequiredField.setColumns(10);

        JLabel jobDescriptionLabel = new JLabel("Job description");
        jobDescriptionLabel.setBounds(10, 124, 90, 16);
        contentPane.add(jobDescriptionLabel);

        jobDescriptionField = new JTextField();
        jobDescriptionField.setBounds(112, 119, 100, 26);
        contentPane.add(jobDescriptionField);
        jobDescriptionField.setColumns(10);

        final JLabel offerDeadlineLabel = new JLabel("Offer Deadline");
        offerDeadlineLabel.setBounds(10, 152, 90, 16);
        contentPane.add(offerDeadlineLabel);

        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        offerDeadlineDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter(){});
        offerDeadlineDatePicker.setBounds(112,147,100,26);
        contentPane.add(offerDeadlineDatePicker);

        final JLabel publishJobPostingErrorLabel = new JLabel("");
        publishJobPostingErrorLabel.setForeground(Color.RED);
        publishJobPostingErrorLabel.setBounds(10, 215, 202, 16);
        contentPane.add(publishJobPostingErrorLabel);

        //declare this spinner final since accessed from a inner class
        /*
        final JSpinner publishJobSpinner = new JSpinner();
        publishJobSpinner.setBounds(112, 35, 100, 26);
        contentPane.add(publishJobSpinner);

        */
        jobListForPublishJobPosting = new JComboBox<String>(new String[0]);
        jobListForPublishJobPosting.setBounds(112,35,100,26);
        contentPane.add(jobListForPublishJobPosting);

        JButton publishJobPostingButton = new JButton("Publish job posting");
        publishJobPostingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String skillsRequired, experienceRequired, jobDescription;
                int jobID;
                Job associatedJob = null;

                //if one of the fields is empty, display an error and reset the display
                if (skillsRequiredField.getText().equals("") || experienceRequiredField.getText().equals("") || jobDescriptionField.getText().equals("") || offerDeadlineDatePicker.getModel().getValue() == null) {
                    publishJobPostingErrorLabel.setText("Please fill all the fields.");
                    updateDisplay();
                    return;
                }

                //the fields are non-empty, so proceed to storing there value
                skillsRequired = skillsRequiredField.getText();
                experienceRequired = experienceRequiredField.getText();
                jobDescription = jobDescriptionField.getText();

                //this get the value displayed by the spinner
                //jobID = (int) publishJobSpinner.getValue(); //TODO: NOT user-friendly, should use a meaningful name instead of an ID

                //if the job was found, add the new attributes. Otherwise, display an error.
                if (selectedJobForPublishJobPosting >= 0) {
                    associatedJob = department.getAllJob(selectedJobForPublishJobPosting);
                    InstructorController instructorController = new InstructorController(department);
                    try {
                        instructorController.createJobPosting(associatedJob, jobDescription, skillsRequired, experienceRequired, (Date) offerDeadlineDatePicker.getModel().getValue() );
                    } catch (InvalidInputException e1) {
                    }
                    associatedJob.setState(JobStatus.Posted); //post the job
                    publishJobPostingErrorLabel.setText(""); //it worked so remove the error
                } else
                    publishJobPostingErrorLabel.setText("An error occurred, please try again."); //this shouldn't happen since the user chooses the job from a spinner so the job must be existent

                updateDisplay();
            }
        });
        publishJobPostingButton.setBounds(10, 185, 190, 29);
        contentPane.add(publishJobPostingButton);

        JLabel studentNameLabel = new JLabel("Student name");
        studentNameLabel.setBounds(260, 63, 90, 16);
        contentPane.add(studentNameLabel);

        studentNameField = new JTextField();
        studentNameField.setBounds(362, 58, 130, 26);
        contentPane.add(studentNameField);
        studentNameField.setColumns(10);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(260, 91, 61, 16);
        contentPane.add(emailLabel);

        JLabel publishJobLabel = new JLabel("Publish A Job Posting");
        publishJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        publishJobLabel.setBounds(23, 6, 165, 20);
        contentPane.add(publishJobLabel);

        JLabel RegisterStudentLabel = new JLabel("Register a Student");
        RegisterStudentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        RegisterStudentLabel.setBounds(307, 6, 140, 20);
        contentPane.add(RegisterStudentLabel);

        emailField = new JTextField();
        emailField.setBounds(362, 86, 130, 26);
        contentPane.add(emailField);
        emailField.setColumns(10);

        undergraduateRadioForRegister = new JRadioButton("Undergraduate");
        graduateRadioForRegister = new JRadioButton("Graduate");

        ButtonGroup group1 = new ButtonGroup();
        group1.add(undergraduateRadioForRegister);
        group1.add(graduateRadioForRegister);

        undergraduateRadioForRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        undergraduateRadioForRegister.setBounds(260, 211, 124, 23);
        contentPane.add(undergraduateRadioForRegister);

        graduateRadioForRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        graduateRadioForRegister.setBounds(391, 211, 101, 23);
        contentPane.add(graduateRadioForRegister);

        JLabel studentYearLabel = new JLabel("Year");
        studentYearLabel.setBounds(260, 119, 61, 16);
        contentPane.add(studentYearLabel);

        studentYearField = new JTextField();
        studentYearField.setBounds(362, 114, 130, 26);
        contentPane.add(studentYearField);
        studentYearField.setColumns(10);

        JLabel jobPreferenceLabel = new JLabel("Job preference");
        jobPreferenceLabel.setBounds(260, 147, 90, 16);
        contentPane.add(jobPreferenceLabel);

        jobPreferenceField = new JTextField();
        jobPreferenceField.setBounds(362, 142, 130, 26);
        contentPane.add(jobPreferenceField);
        jobPreferenceField.setColumns(10);

        JLabel studentIDLabel = new JLabel("Student ID");
        studentIDLabel.setBounds(260, 35, 90, 16);
        contentPane.add(studentIDLabel);

        studentIDField = new JTextField();
        studentIDField.setBounds(362, 30, 130, 26);
        contentPane.add(studentIDField);
        studentIDField.setColumns(10);

        JLabel studentHoursLabel = new JLabel("Number of hours");
        studentHoursLabel.setBounds(260, 175, 90, 16);
        contentPane.add(studentHoursLabel);

        studentHoursField = new JTextField();
        studentHoursField.setBounds(362, 173, 130, 26);
        contentPane.add(studentHoursField);
        studentHoursField.setColumns(10);

        final JLabel registerAStudentErrorLabel = new JLabel("");
        registerAStudentErrorLabel.setForeground(Color.RED);
        registerAStudentErrorLabel.setBounds(270, 282, 222, 16);
        contentPane.add(registerAStudentErrorLabel);

        JButton registerStudentButton = new JButton("Register student");
        registerStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String studentName,studentEmail,studentJobPreference;
                int studentID, studentYear, numberOfHours;
                Boolean isGrad = false;

                //if one of the field is empty, display an error and update the display
                if (studentNameField.getText().equals("") || emailField.getText().equals("") || jobPreferenceField.getText().equals("")) {
                    registerAStudentErrorLabel.setText("Please fill all the required forms.");
                    updateDisplay();
                    return;
                }
                //proceed with storing the field values since they are all non-empty
                studentName = studentNameField.getText();
                studentEmail = emailField.getText();
                studentJobPreference = jobPreferenceField.getText();

                //convert the student ID (if possible) as well as student year and number of hours
                try {
                    studentID = Integer.valueOf(studentIDField.getText());
                } catch (NumberFormatException exception) {
                    registerAStudentErrorLabel.setText("Invalid student ID. Please try again.");
                    updateDisplay();
                    return;
                }

                try {
                    studentYear = Integer.valueOf(studentYearField.getText());
                } catch (NumberFormatException exception) {
                    registerAStudentErrorLabel.setText("Invalid student year. Please try again.");
                    updateDisplay();
                    return;
                }

                try {
                    numberOfHours = Integer.valueOf(studentHoursField.getText());
                } catch (NumberFormatException exception) {
                    registerAStudentErrorLabel.setText("Invalid student hours. Please try again.");
                    updateDisplay();
                    return;
                }

                //check if the user selected a position type
                if (!graduateRadioForRegister.isSelected() && !undergraduateRadioForRegister.isSelected()) {
                    registerAStudentErrorLabel.setText("Please select a position type.");
                    updateDisplay();
                    return;
                }

                //if the user selected a position type, find which one
                if (graduateRadioForRegister.isSelected())
                    isGrad = true;

                //register the student

                for (Student student : department.getAllStudents()) {
                    if (Integer.toString(student.getStudentID()).equals(studentID) || student.getEmail().equals(studentEmail)) {
                        registerAStudentErrorLabel.setText("This student is already existent.");
                        updateDisplay();
                        return;
                    }
                }
                controller.registerAStudent(studentID,studentName,studentEmail,isGrad,studentYear,studentJobPreference,numberOfHours);
                updateDisplay();

            }
        });
        registerStudentButton.setBounds(260, 241, 232, 29);
        contentPane.add(registerStudentButton);

        JLabel applyForAJobLabel = new JLabel("Apply for a Job");
        applyForAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        applyForAJobLabel.setBounds(591, 162, 110, 16);
        contentPane.add(applyForAJobLabel);

        JLabel studentIDForApplyingLabel = new JLabel("Student ID");
        studentIDForApplyingLabel.setBounds(533, 190, 61, 16);
        contentPane.add(studentIDForApplyingLabel);

        studentIDForApplyingField = new JTextField();
        studentIDForApplyingField.setBounds(635, 185, 130, 26);
        contentPane.add(studentIDForApplyingField);
        studentIDForApplyingField.setColumns(10);

        JLabel jobIDForApplyingLabel = new JLabel("Job title");
        jobIDForApplyingLabel.setBounds(533, 215, 61, 16);
        contentPane.add(jobIDForApplyingLabel);

        jobListForStudentApplyJob = new JComboBox<String>(new String[0]);
        jobListForStudentApplyJob.setBounds(636,212,126,26);
        contentPane.add(jobListForStudentApplyJob);

        final JLabel applyForAJobErrorLabel = new JLabel("");
        applyForAJobErrorLabel.setForeground(Color.RED);
        applyForAJobErrorLabel.setBounds(543, 282, 222, 16);
        contentPane.add(applyForAJobErrorLabel);

        JButton applyForAJobButton = new JButton("Apply!");
        applyForAJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int studentID, jobID;
                Student student;
                Job associatedJob = null;

                //convert the student ID if possible.
                try {
                    studentID = Integer.valueOf(studentIDForApplyingField.getText());
                } catch (NumberFormatException exception) {
                    applyForAJobErrorLabel.setText("Invalid student ID. Please try again.");
                    updateDisplay();
                    return;
                }

                //find the associated student
                student = Student.getWithStudentID(studentID); //TODO: the student might not exist

                //store the job ID from the spinner

                //search through all the jobs of the department to find the corresponding job object
                for (Job job: department.getAllJobs()) {
                    String tmp = job.getCorrespondingCourse().getName() + "-" + job.getPosType().toString();
                    if (jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply) != null && tmp.contentEquals(jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply))) {
                        associatedJob = job;
                        break;
                    }

                }

                //if the job was found (which should be the case, since the choices come from a spinner)

                StudentController studentController = new StudentController(department);
                try {
                    studentController.applyToJobPosting(associatedJob,student);
                    applyForAJobErrorLabel.setText(""); //it worked so remove the error

                } catch (InvalidInputException e1) {
                    applyForAJobErrorLabel.setText(e1.getMessage()); //this shouldn't happen since the user chooses the job from a spinner so the job must be existent

                }

                updateDisplay();

            }
        });
        applyForAJobButton.setBounds(533, 242, 232, 26);
        contentPane.add(applyForAJobButton);

        JLabel createAJobLabel = new JLabel("Create a Job");
        createAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        createAJobLabel.setBounds(591, 6, 140, 20);
        contentPane.add(createAJobLabel);


        TARadio = new JRadioButton("TA");
        graderRadio = new JRadioButton("Grader");

        //Created a group for the radio
        ButtonGroup group = new ButtonGroup();
        group.add(TARadio);
        group.add(graderRadio);

        TARadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        TARadio.setBounds(533, 31, 130, 23);
        contentPane.add(TARadio);

        graderRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        graderRadio.setBounds(675, 31, 90, 23);
        contentPane.add(graderRadio);

        //This is a dummy instructor
        Instructor dummyInstructor = new Instructor("John Doe", 12345, "john.doe@mcgill.ca");

        //These are some dummy course for testing purposes of the prototype application
        if(department.getAllCourses().size()<2) {
            final Course dummyCourse1 = new Course("ECSE100", "Computer Engineering", "2016", 3, 0, 1, 6, 140, 2, 2, 20, 20, 3000, dummyInstructor);
            Course dummyCourse2 = new Course("ECSE200", "Software Engineering", "2016", 3, 0, 1, 6, 140, 2, 2, 20, 20, 3000, dummyInstructor);
            Course dummyCourse3 = new Course("ECSE300", "Electrical Engineering", "2016", 3, 0, 1, 6, 140, 2, 2, 20, 20, 3000, dummyInstructor);

            department.addAllCourse(dummyCourse1);
            department.addAllCourse(dummyCourse2);
            department.addAllCourse(dummyCourse3);
        }

        //spinners
        courseList = new JComboBox<String>(new String[0]);
        courseList.setBounds(533, 63, 221, 26);
        contentPane.add(courseList);

        final JButton createNewJobButton = new JButton("Create new job");
        createNewJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //This is code the allows the use to test publish job posting and apply to job

                PositionType positionType = PositionType.TA; //for now, this will be the default value (to avoid situation when user select neither of the choices)

                if (graderRadio.isSelected())
                    positionType = PositionType.Grader;
                //Create dummy post Deadline to create job (TESTING PURPOSES)
                Calendar c = Calendar.getInstance();
                c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
                Date dummyPostDeadLine = new Date(c.getTimeInMillis());


                controller.createJob(positionType, dummyPostDeadLine, department.getAllCourse(selectedCourse));
                updateDisplay();
            }
        });
        createNewJobButton.setBounds(533, 96, 221, 29);
        contentPane.add(createNewJobButton);

        courseList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedCourse = cb.getSelectedIndex();
            }
        });

        jobListForPublishJobPosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForPublishJobPosting = cb.getSelectedIndex();
            }
        });

        jobListForStudentApplyJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForStudentApply = cb.getSelectedIndex();
            }
        });
        this.setSize(800,400);

        updateDisplay();
    }

    //useful for updating the createNewJobSpinner using the spinner model
    private void setCreateNewJobSpinnerModel(SpinnerModel model) {
        createNewJobSpinner.setModel(model);
    }

    private void updateDisplay() {

        //update the **publish a job posting** component
        skillsRequiredField.setText("");
        experienceRequiredField.setText("");
        jobDescriptionField.setText("");
        //TODO: update the spinner

        //update the **register a student** component
        studentIDField.setText("");
        studentNameField.setText("");
        emailField.setText("");
        studentYearField.setText("");
        jobPreferenceField.setText("");
        studentHoursField.setText("");

        //TODO: there seems to be an issue here, the radio buttons do not get unselected
        undergraduateRadioForRegister.setSelected(false);
        graduateRadioForRegister.setSelected(false);

        //update the **Apply for a Job** component
        studentIDForApplyingField.setText("");
        //TODO: update the spinner

        courseList.removeAllItems();
        for(Course c: department.getAllCourses()){
            courseList.addItem(c.getName());
        }
        selectedCourse = -1;
        courseList.setSelectedIndex(selectedCourse);

        jobListForPublishJobPosting.removeAllItems();
        for(Job j: department.getAllJobs()){
            String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
            jobListForPublishJobPosting.addItem(tmp);
        }
        selectedJobForPublishJobPosting = -1;
        jobListForPublishJobPosting.setSelectedIndex(selectedJobForPublishJobPosting);

        jobListForStudentApplyJob.removeAllItems();
        for(Job j: department.getAllJobs()){
            if(j.getState() == JobStatus.Posted){
                String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                jobListForStudentApplyJob.addItem(tmp);
            }
        }
        selectedJobForStudentApply = -1;
        jobListForStudentApplyJob.setSelectedIndex(selectedJobForStudentApply);

        offerDeadlineDatePicker.getModel().setValue(null);


    }

    //*****SETTERS AND GETTERS******

    public void setSkillsRequiredField(String text) {

        skillsRequiredField.setText(text);
    }

    public String getSkillsRequiredField() {

        return skillsRequiredField.getText();
    }

    public void setExperienceRequiredField(String text) {

        experienceRequiredField.setText(text);
    }

    public String getExperienceRequiredField() {

        return experienceRequiredField.getText();
    }

    public void setJobDescriptionField(String text) {

        jobDescriptionField.setText(text);
    }

    public String getJobDescription() {

        return jobDescriptionField.getText();
    }

    public void setCourseNumberField(String text) {

        courseNumberField.setText(text);
    }

    public String getCourseNumber() {

        return courseNumberField.getText();
    }

    public void setStudentIDField(String text) {

        studentIDField.setText(text);
    }

    public String getStudentIDField() {

        return studentIDField.getText();
    }

    public void setStudentNameField(String text) {

        studentNameField.setText(text);
    }

    public String getStudentNameField() {

        return studentNameField.getText();
    }

    public void setEmailField(String text) {

        emailField.setText(text);
    }

    public String getEmailField() {

        return emailField.getText();
    }

    public void setStudentYearField(String text) {

        studentYearField.setText(text);
    }

    public String getStudentYearField() {

        return studentYearField.getText();
    }

    public void setJobPreferenceField(String text) {

        jobDescriptionField.setText(text);
    }

    public String getJobPreferenceField() {

        return jobPreferenceField.getText();
    }

    public void setStudentNameForApplyingField(String text) {

        studentIDForApplyingField.setText(text);
    }

    public String getStudentForApplyingField() {

        return studentIDForApplyingField.getText();
    }

    private class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private static final long serialVersionUID = -2169252224419341678L;

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
