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
    private JTextField studentIDForApplyingField;
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JTextField numberOfTutorialsField;
    private JTextField numberOfLabsField;
    private JTextField numberStudentEnrolledField;
    private JTextField hoursField;
    private JTextField taHourlyRateField;
    private JTextField creditsField;
    private JTextField numberOfTAsNeededField;
    private JTextField numberOfGradersNeededField;
    private JTextField graderHourlyRateField;
    private JTextField budgetField;
    private JTextField createAnInstructorNameField;
    private JTextField instructorIDField;
    private JTextField instructorEmailField;

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
    private JComboBox<String> semesterJComboBox;
    private JComboBox<String> createAllocationStudentComboBox;
    private JComboBox<String> createAllocationJobComboBox;
    private JComboBox<String> createOfferStudentComboBox;
    private JComboBox<String> createOfferJobComboBox;


    private Integer selectedJobForPublishJobPosting = -1;
    private Integer selectedJobForStudentApply = -1;
    private Integer selectedCourse = -1;
    private Integer selectedSemesterForCreateACourse = -1;
    private Integer selectedStudentForCreateAllocation = -1;
    private Integer selectedJobForCreateAllocation = -1;
    private Integer selectedStudentForCreateOffer = -1;
    private Integer selectedJobForCreateOffer = -1;

    Department department;


    /**
     * Create the frame.
     */

    public DepartmentPage(final Department department) {

        this.department = department;

        final DepartmentController departmentController = new DepartmentController(department);
        final StudentController studentController = new StudentController(department);
        final InstructorController instructorController = new InstructorController(department);

        setTitle("Department's Page");

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

                skillsRequired = skillsRequiredField.getText();
                experienceRequired = experienceRequiredField.getText();
                jobDescription = jobDescriptionField.getText();

                //if the job was found, add the new attributes. Otherwise, display an error.
                if (selectedJobForPublishJobPosting >= 0) {

                    associatedJob = department.getAllJob(selectedJobForPublishJobPosting);
                    InstructorController instructorController = new InstructorController(department);

                    try {
                        instructorController.createJobPosting(associatedJob, jobDescription, skillsRequired, experienceRequired, (Date) offerDeadlineDatePicker.getModel().getValue() );
                        associatedJob.setState(JobStatus.Posted); //post the job
                        publishJobPostingErrorLabel.setText(""); //it worked so remove the error

                    } catch (InvalidInputException e1) {
                        publishJobPostingErrorLabel.setText(e1.getMessage());
                        updateDisplay();
                    }

                } else
                    publishJobPostingErrorLabel.setText("Please select a job.");

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

                String studentName,studentEmail,studentJobPreference, studentID, studentYear, numberOfHours;
                Boolean isGrad = false;

                //proceed with storing the field values since they are all non-empty
                studentName = studentNameField.getText();
                studentEmail = emailField.getText();
                studentJobPreference = jobPreferenceField.getText();
                studentID = studentIDField.getText();
                studentYear = studentYearField.getText();
                numberOfHours = studentHoursField.getText();

                //if the user selected a position type, find which one
                if (graduateRadioForRegister.isSelected())
                    isGrad = true;

                try {
                    studentController.createStudent(studentID,studentName,studentEmail,isGrad,studentYear,studentJobPreference,numberOfHours);
                    registerAStudentErrorLabel.setText("");
                } catch (InvalidInputException e1) {
                    registerAStudentErrorLabel.setText(e1.getMessage());
                }

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
                student = Student.getWithStudentID(studentID);


                //search through all the jobs of the department to find the corresponding job object
                for (Job job: department.getAllJobs()) {
                    String tmp = job.getCorrespondingCourse().getName() + "-" + job.getPosType().toString();
                    if (jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply) != null && tmp.contentEquals(jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply))) {
                        associatedJob = job;
                        break;
                    }
                }

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


                try {
                    departmentController.createJob(positionType, dummyPostDeadLine, department.getAllCourse(selectedCourse));
                } catch (InvalidInputException e1) {

                }
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

        JLabel createACourseLabel = new JLabel("Create a Course");
        createACourseLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        createACourseLabel.setBounds(37, 240, 125, 16);
        contentPane.add(createACourseLabel);

        JLabel courseNameLabel = new JLabel("Course name");
        courseNameLabel.setBounds(10, 273, 90, 16);
        contentPane.add(courseNameLabel);

        JLabel courseCodeLabel = new JLabel("Course code");
        courseCodeLabel.setBounds(10, 301, 90, 16);
        contentPane.add(courseCodeLabel);

        JLabel numberOfTutorialsLabel = new JLabel("Number of tutorials");
        numberOfTutorialsLabel.setBounds(10, 357, 90, 16);
        contentPane.add(numberOfTutorialsLabel);

        JLabel numberOfLabsLabel = new JLabel("Number of labs");
        numberOfLabsLabel.setBounds(10, 385, 90, 16);
        contentPane.add(numberOfLabsLabel);

        JLabel numberStudentEnrolledLabel = new JLabel("Number student enrolled");
        numberStudentEnrolledLabel.setBounds(10, 413, 90, 16);
        contentPane.add(numberStudentEnrolledLabel);

        JLabel hoursLabel = new JLabel("Hours");
        hoursLabel.setBounds(10, 441, 90, 16);
        contentPane.add(hoursLabel);

        JLabel taHourlyRateLabel = new JLabel("TA hourly rate");
        taHourlyRateLabel.setBounds(10, 527, 90, 16);
        contentPane.add(taHourlyRateLabel);

        JLabel creditsLabel = new JLabel("Credits");
        creditsLabel.setBounds(10, 610, 90, 16);
        contentPane.add(creditsLabel);

        courseNameField = new JTextField();
        courseNameField.setBounds(112, 268, 100, 26);
        contentPane.add(courseNameField);
        courseNameField.setColumns(10);

        courseCodeField = new JTextField();
        courseCodeField.setBounds(112, 296, 100, 26);
        contentPane.add(courseCodeField);
        courseCodeField.setColumns(10);

        numberOfTutorialsField = new JTextField();
        numberOfTutorialsField.setBounds(112, 352, 100, 26);
        contentPane.add(numberOfTutorialsField);
        numberOfTutorialsField.setColumns(10);

        numberOfLabsField = new JTextField();
        numberOfLabsField.setBounds(112, 380, 100, 26);
        contentPane.add(numberOfLabsField);
        numberOfLabsField.setColumns(10);

        numberStudentEnrolledField = new JTextField();
        numberStudentEnrolledField.setBounds(112, 408, 100, 26);
        contentPane.add(numberStudentEnrolledField);
        numberStudentEnrolledField.setColumns(10);

        hoursField = new JTextField();
        hoursField.setBounds(112, 436, 100, 26);
        contentPane.add(hoursField);
        hoursField.setColumns(10);

        taHourlyRateField = new JTextField();
        taHourlyRateField.setBounds(112, 522, 100, 26);
        contentPane.add(taHourlyRateField);
        taHourlyRateField.setColumns(10);

        creditsField = new JTextField();
        creditsField.setBounds(112, 605, 100, 26);
        contentPane.add(creditsField);
        creditsField.setColumns(10);

        final JLabel createCourseErrorLabel = new JLabel("");
        createCourseErrorLabel.setForeground(Color.RED);
        createCourseErrorLabel.setBounds(10, 684, 202, 16);
        contentPane.add(createCourseErrorLabel);

        JLabel semesterLabel = new JLabel("Semester");
        semesterLabel.setBounds(10, 329, 94, 16);
        contentPane.add(semesterLabel);

        semesterJComboBox = new JComboBox();
        semesterJComboBox.setBounds(112, 325, 100, 27);
        contentPane.add(semesterJComboBox);

        semesterJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedSemesterForCreateACourse = cb.getSelectedIndex();
            }
        });

        JLabel numberOfTAsNeededLabel = new JLabel("TAs needed");
        numberOfTAsNeededLabel.setBounds(10, 469, 90, 16);
        contentPane.add(numberOfTAsNeededLabel);

        numberOfTAsNeededField = new JTextField();
        numberOfTAsNeededField.setBounds(112, 464, 100, 26);
        contentPane.add(numberOfTAsNeededField);
        numberOfTAsNeededField.setColumns(10);

        JLabel numberOfGradersNeededLabel = new JLabel("Graders needed");
        numberOfGradersNeededLabel.setBounds(10, 497, 90, 16);
        contentPane.add(numberOfGradersNeededLabel);

        numberOfGradersNeededField = new JTextField();
        numberOfGradersNeededField.setBounds(112, 492, 100, 26);
        contentPane.add(numberOfGradersNeededField);
        numberOfGradersNeededField.setColumns(10);

        JLabel graderHourlyRateLabel = new JLabel("Graders hourly rate");
        graderHourlyRateLabel.setBounds(10, 555, 90, 16);
        contentPane.add(graderHourlyRateLabel);

        graderHourlyRateField = new JTextField();
        graderHourlyRateField.setBounds(112, 550, 100, 26);
        contentPane.add(graderHourlyRateField);
        graderHourlyRateField.setColumns(10);

        JLabel budgetLabel = new JLabel("Budget");
        budgetLabel.setBounds(10, 583, 90, 16);
        contentPane.add(budgetLabel);

        budgetField = new JTextField();
        budgetField.setBounds(112, 578, 100, 26);
        contentPane.add(budgetField);
        budgetField.setColumns(10);


        JButton createACourseButton = new JButton("Create a Course");
        createACourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String courseName, courseCode, semester, numberOfLabs, numberOfTutorials, numberOfStudents,hours, numberOfTAsNeeded, numberOfGradersNeeded, hourlyRateTA, graderHourlyRate, numberOfCredits, budget;

                courseName = courseNameField.getText();
                courseCode = courseCodeField.getText();
                numberOfLabs = numberOfLabsField.getText();
                numberOfTutorials = numberOfTutorialsField.getText();
                numberOfStudents = numberStudentEnrolledField.getText();
                hours = hoursField.getText();
                numberOfTAsNeeded = numberOfTAsNeededField.getText();
                numberOfGradersNeeded = numberOfGradersNeededField.getText();
                hourlyRateTA = taHourlyRateField.getText();
                graderHourlyRate = graderHourlyRateField.getText();
                numberOfCredits = creditsField.getText();
                budget = budgetField.getText();

                if (selectedSemesterForCreateACourse >= 0) {

                    semester = semesterJComboBox.getSelectedItem().toString();

                    //TESTING PURPOSES
                    Instructor dummyInstructor = new Instructor("John Doe", 12346, "john.doe2@mcgill.ca");

                    try {
                        departmentController.createCourse(courseCode, courseName, semester,numberOfCredits, numberOfLabs,numberOfTutorials,hours,numberOfStudents,numberOfTAsNeeded,numberOfGradersNeeded,hourlyRateTA,graderHourlyRate,budget,dummyInstructor);
                        createCourseErrorLabel.setText("");
                    } catch (InvalidInputException e1) {
                        createCourseErrorLabel.setText(e1.getMessage());
                    }
                } else {
                    createCourseErrorLabel.setText("Please select a semester!");
                }

                updateDisplay();
            }
        });
        createACourseButton.setBounds(10, 643, 202, 29);
        contentPane.add(createACourseButton);

        JLabel lblCreateAnInstructor = new JLabel("Create an Instructor");
        lblCreateAnInstructor.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblCreateAnInstructor.setBounds(307, 310, 152, 16);
        contentPane.add(lblCreateAnInstructor);

        JLabel createAnInstructorNameLabel = new JLabel("Full name");
        createAnInstructorNameLabel.setBounds(260, 338, 90, 16);
        contentPane.add(createAnInstructorNameLabel);

        createAnInstructorNameField = new JTextField();
        createAnInstructorNameField.setBounds(362, 333, 130, 26);
        contentPane.add(createAnInstructorNameField);
        createAnInstructorNameField.setColumns(10);

        JLabel instructorIDLabel = new JLabel("Instructor ID");
        instructorIDLabel.setBounds(260, 367, 90, 16);
        contentPane.add(instructorIDLabel);

        instructorIDField = new JTextField();
        instructorIDField.setBounds(362, 362, 130, 26);
        contentPane.add(instructorIDField);
        instructorIDField.setColumns(10);

        JLabel instructorEmailLabel = new JLabel("Email");
        instructorEmailLabel.setBounds(260, 395, 90, 16);
        contentPane.add(instructorEmailLabel);

        instructorEmailField = new JTextField();
        instructorEmailField.setBounds(362, 390, 130, 26);
        contentPane.add(instructorEmailField);
        instructorEmailField.setColumns(10);

        final JLabel createInstructorErrorLabel = new JLabel("");
        createInstructorErrorLabel.setForeground(Color.RED);
        createInstructorErrorLabel.setBounds(260, 454, 232, 16);
        contentPane.add(createInstructorErrorLabel);

        JButton createInstructorButton = new JButton("Create new instructor");
        createInstructorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String instructorName, instructorID, instructorEmail;

                instructorName = createAnInstructorNameField.getText();
                instructorID = instructorIDField.getText();
                instructorEmail = instructorEmailField.getText();

                try {
                    instructorController.createInstructor(instructorName,instructorID,instructorEmail);
                    createInstructorErrorLabel.setText("");

                } catch (InvalidInputException e1) {
                    createInstructorErrorLabel.setText(e1.getMessage());
                }
            }
        });
        createInstructorButton.setBounds(260, 423, 232, 29);
        contentPane.add(createInstructorButton);

        JLabel createAllocationLabel = new JLabel("Create/Remove Allocation");
        createAllocationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createAllocationLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        createAllocationLabel.setBounds(260, 492, 229, 16);
        contentPane.add(createAllocationLabel);

        JLabel createAllocationStudentLabel = new JLabel("Student");
        createAllocationStudentLabel.setBounds(260, 555, 90, 16);
        contentPane.add(createAllocationStudentLabel);

        createAllocationStudentComboBox = new JComboBox();
        createAllocationStudentComboBox.setBounds(376, 551, 116, 27);
        contentPane.add(createAllocationStudentComboBox);

        createAllocationStudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForCreateAllocation = cb.getSelectedIndex();
            }
        });

        JLabel createAllocationJobLabel = new JLabel("Job");
        createAllocationJobLabel.setBounds(260, 583, 90, 16);
        contentPane.add(createAllocationJobLabel);

        createAllocationJobComboBox = new JComboBox();
        createAllocationJobComboBox.setBounds(376, 579, 116, 27);
        contentPane.add(createAllocationJobComboBox);

        createAllocationJobComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForCreateAllocation = cb.getSelectedIndex();
            }
        });


        final JRadioButton createAllocationRadio = new JRadioButton("Create");
        final JRadioButton removeAllocationRadio = new JRadioButton("Remove");

        ButtonGroup group3 = new ButtonGroup();
        group3.add(createAllocationRadio);
        group3.add(removeAllocationRadio);

        createAllocationRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        createAllocationRadio.setBounds(260, 523, 81, 23);
        contentPane.add(createAllocationRadio);

        removeAllocationRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        removeAllocationRadio.setBounds(392, 523, 100, 23);
        contentPane.add(removeAllocationRadio);

        final JLabel createAllocationErrorLabel = new JLabel("");
        createAllocationErrorLabel.setForeground(Color.RED);
        createAllocationErrorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        createAllocationErrorLabel.setBounds(260, 648, 232, 16);
        contentPane.add(createAllocationErrorLabel);

        JButton createAllocationButton = new JButton("Create/Remove");
        createAllocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean selectedCreateAllocation = false;
                boolean selectedRemoveAllocation = false;
                String jobString, studentString;
                Job job = null;
                Student student = null;

                if (createAllocationRadio.isSelected()) {
                    selectedCreateAllocation = true;
                }
                if (removeAllocationRadio.isSelected()) {
                    selectedRemoveAllocation = true;
                }

                if ((selectedCreateAllocation == true || selectedRemoveAllocation == true) && selectedJobForCreateAllocation >= 0 && selectedStudentForCreateAllocation >= 0) {

                    studentString = createAllocationStudentComboBox.getSelectedItem().toString();
                    jobString = createAllocationJobComboBox.getSelectedItem().toString();

                    //search through all the jobs of the department to find the corresponding job object
                    for (Job j: department.getAllJobs()) {
                        String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                        if (createAllocationJobComboBox.getItemAt(selectedJobForCreateAllocation) != null && tmp.contentEquals(createAllocationJobComboBox.getItemAt(selectedJobForCreateAllocation))) {
                            job = j;
                            break;
                        }
                    }

                    //search through all students of the department to find corresponding student object
                    for (Student s: department.getAllStudents()) {
                        String tmp = s.getEmail();
                        if (createAllocationStudentComboBox.getItemAt(selectedStudentForCreateAllocation) != null && tmp.contentEquals(createAllocationStudentComboBox.getItemAt(selectedStudentForCreateAllocation))) {
                            student = s;
                            break;
                        }
                    }

                        if (selectedCreateAllocation) {
                            try {
                                departmentController.createAllocation(job, student);
                            } catch (InvalidInputException e1) {
                                createAllocationErrorLabel.setText(e1.getMessage());
                            }
                        } else {
                            try {
                                departmentController.removeAllocation(job,student);
                            } catch (InvalidInputException e1) {
                                createAllocationErrorLabel.setText(e1.getMessage());
                            }
                        }

                } else {
                    createAllocationErrorLabel.setText("Please selected all the fields.");
                }

                updateDisplay();
            }
        });
        createAllocationButton.setBounds(260, 610, 232, 29);
        contentPane.add(createAllocationButton);

        JLabel createOfferLabel = new JLabel("Create Offer");
        createOfferLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        createOfferLabel.setBounds(606, 310, 110, 16);
        contentPane.add(createOfferLabel);

        JLabel createOfferStudentLabel = new JLabel("Student");
        createOfferStudentLabel.setBounds(543, 352, 61, 16);
        contentPane.add(createOfferStudentLabel);

        createOfferStudentComboBox = new JComboBox();
        createOfferStudentComboBox.setBounds(635, 346, 130, 27);
        contentPane.add(createOfferStudentComboBox);

        createOfferStudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForCreateOffer = cb.getSelectedIndex();
            }
        });

        JLabel createOfferJobLabel = new JLabel("Job");
        createOfferJobLabel.setBounds(543, 385, 61, 16);
        contentPane.add(createOfferJobLabel);

        createOfferJobComboBox = new JComboBox();
        createOfferJobComboBox.setBounds(635, 381, 130, 27);
        contentPane.add(createOfferJobComboBox);

        createOfferJobComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForCreateOffer = cb.getSelectedIndex();
            }
        });

        final JLabel createOfferErrorLabel = new JLabel("");
        createOfferErrorLabel.setForeground(Color.RED);
        createOfferErrorLabel.setBounds(533, 464, 232, 16);
        contentPane.add(createOfferErrorLabel);

        JButton createOfferButton = new JButton("Create offer");
        createOfferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentString, jobString;
                Student student = null;
                Job job = null;

                if (selectedJobForCreateOffer >= 0 && selectedStudentForCreateOffer >= 0) {

                    studentString = createOfferStudentComboBox.getSelectedItem().toString();
                    jobString = createOfferJobComboBox.getSelectedItem().toString();

                    //search through all students of the department to find corresponding student object
                    for (Student s: department.getAllStudents()) {
                        String tmp = s.getEmail();
                        if (createOfferStudentComboBox.getItemAt(selectedStudentForCreateOffer) != null && tmp.contentEquals(createOfferStudentComboBox.getItemAt(selectedStudentForCreateOffer))) {
                            student = s;
                            break;
                        }
                    }

                    //search through all the jobs of the department to find the corresponding job object
                    for (Job j: department.getAllJobs()) {
                        String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                        if (createOfferJobComboBox.getItemAt(selectedJobForCreateOffer) != null && tmp.contentEquals(createOfferJobComboBox.getItemAt(selectedJobForCreateOffer))) {
                            job = j;
                            break;
                        }
                    }

                    try {
                        departmentController.createJobOffer(job,student);
                    } catch (InvalidInputException e1) {
                        createOfferErrorLabel.setText(e1.getMessage());
                    }

                } else {
                    createOfferErrorLabel.setText("Please select all the fields.");
                }

            }
        });
        createOfferButton.setBounds(533, 423, 232, 29);
        contentPane.add(createOfferButton);

        this.setSize(800,800);

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

        //update the **register a student** component
        studentIDField.setText("");
        studentNameField.setText("");
        emailField.setText("");
        studentYearField.setText("");
        jobPreferenceField.setText("");
        studentHoursField.setText("");
        
        undergraduateRadioForRegister.setSelected(false);
        graduateRadioForRegister.setSelected(false);

        //update the **Apply for a Job** component
        studentIDForApplyingField.setText("");

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

        semesterJComboBox.removeAllItems();
        semesterJComboBox.addItem("Fall");
        semesterJComboBox.addItem("Winter");
        selectedSemesterForCreateACourse = -1;
        semesterJComboBox.setSelectedIndex(selectedSemesterForCreateACourse);

        createAllocationStudentComboBox.removeAllItems();
        for (Student s: department.getAllStudents()) {
            createAllocationStudentComboBox.addItem(s.getEmail());
        }
        selectedStudentForCreateAllocation = -1;
        createAllocationStudentComboBox.setSelectedIndex(selectedStudentForCreateAllocation);

        createAllocationJobComboBox.removeAllItems();
        for (Job j: department.getAllJobs()) {
            if (j.getState() == JobStatus.Posted) {
                String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                createAllocationJobComboBox.addItem(tmp);
            }
        }
        selectedJobForCreateAllocation = -1;
        createAllocationJobComboBox.setSelectedIndex(selectedJobForCreateAllocation);

        createOfferStudentComboBox.removeAllItems();
        for (Student s: department.getAllStudents()) {
            createOfferStudentComboBox.addItem(s.getEmail());
        }
        selectedStudentForCreateOffer = -1;
        createOfferStudentComboBox.setSelectedIndex(selectedStudentForCreateOffer);

        createOfferJobComboBox.removeAllItems();
        for (Job j: department.getAllJobs()) {
            if (j.getState() == JobStatus.Posted) {
                String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                createOfferJobComboBox.addItem(tmp);
            }
        }
        selectedJobForCreateOffer = -1;
        createOfferJobComboBox.setSelectedIndex(selectedJobForCreateOffer);

        offerDeadlineDatePicker.getModel().setValue(null);

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
