package ca.mcgill.ecse321.tamas.view;


//GUI classes
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerListModel;

//model classes
import ca.mcgill.ecse321.tamas.model.*;
import com.sun.media.sound.InvalidFormatException;
import com.sun.org.apache.xpath.internal.operations.Bool;

//Controller class
import ca.mcgill.ecse321.tamas.controller.DepartmentController;

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

    /**
     * Create the frame.
     */

    //TODO: READ ME
    /* in DepartmentPage() below, you will find the construction of the Swing UI as well as
     * the event handlers. Event handlers are used when a button is click so this where most of
     * the interesting code lives. There are 4 major event handlers, one for each button. Note that
     *  the event handler for "Creating a job" is not complete at all, I will write it for deliverable 3*/

    public DepartmentPage() {

        //create a department in order to get a department controller
        final Department department = new Department();
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

        final JLabel publishJobPostingErrorLabel = new JLabel("");
        publishJobPostingErrorLabel.setForeground(Color.RED);
        publishJobPostingErrorLabel.setBounds(10, 215, 202, 16);
        contentPane.add(publishJobPostingErrorLabel);

        //declare this spinner final since accessed from a inner class
        final JSpinner publishJobSpinner = new JSpinner();
        publishJobSpinner.setBounds(112, 35, 100, 26);
        contentPane.add(publishJobSpinner);

        JButton publishJobPostingButton = new JButton("Publish job posting");
        publishJobPostingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String skillsRequired, experienceRequired, jobDescription;
                int jobID;
                Job associatedJob = null;

                //if one of the fields is empty, display an error and reset the display
                if (skillsRequiredField.getText().equals("") || experienceRequiredField.getText().equals("") || jobDescriptionField.getText().equals("")) {
                    publishJobPostingErrorLabel.setText("Please fill all the fields.");
                    updateDisplay();
                    return;
                }

                //the fields are non-empty, so proceed to storing there value
                skillsRequired = skillsRequiredField.getText();
                experienceRequired = experienceRequiredField.getText();
                jobDescription = jobDescriptionField.getText();

                //this get the value displayed by the spinner
                jobID = (int) publishJobSpinner.getValue(); //TODO: NOT user-friendly, should use a meaningful name instead of an ID

                //find a corresponding job using jobID
                for (Job job: department.getAllJobs()) {
                    if (job.getJobID() == jobID)
                        associatedJob = job;
                }

                //if the job was found, add the new attributes. Otherwise, display an error.
                if (associatedJob != null) {
                    associatedJob.setSkillsRequired(skillsRequired);
                    associatedJob.setExperienceRequired(experienceRequired);
                    associatedJob.setJobDescription(jobDescription);
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
                controller.registerAStudent(studentID,studentName,studentEmail,isGrad,studentYear,studentJobPreference,numberOfHours);

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

        final JSpinner applyForJobSpinner = new JSpinner();
        applyForJobSpinner.setBounds(636, 212, 126, 26);
        contentPane.add(applyForJobSpinner);

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
                jobID = (int) applyForJobSpinner.getValue(); //TODO: A more user-friendly way would be to use a job name

                //search through all the jobs of the department to find the corresponding job object
                for (Job job: department.getAllJobs()) {
                    if (job.getJobID() == jobID)
                        associatedJob = job;
                }

                //if the job was found (which should be the case, since the choices come from a spinner)
                if (associatedJob != null) {
                    associatedJob.setState(JobStatus.AppliedTo); //post the job
                    applyForAJobErrorLabel.setText(""); //it worked so remove the error

                } else
                    applyForAJobErrorLabel.setText("An error occurred, please try again."); //this shouldn't happen since the user chooses the job from a spinner so the job must be existent

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


        //TODO: This commented code is for later, it's how to add elements to a spinner
//        String[] profStrings = {"Prakash", "Daniel", "David"};
//
//        List<String> profStringsList = new ArrayList<String>();
//
//        profStringsList.add("COMP302");
//        profStringsList.add("ECSE321");
//        profStringsList.add("COMP206");
//
//        SpinnerListModel profModel = new SpinnerListModel(profStringsList);
//        createNewJobSpinner = new JSpinner(profModel);


        //spinners
        createNewJobSpinner = new JSpinner();
        ((JSpinner.DefaultEditor) createNewJobSpinner.getEditor()).getTextField().setEditable(false);
        createNewJobSpinner.setBounds(533, 63, 221, 26);
        contentPane.add(createNewJobSpinner);

        final JButton createNewJobButton = new JButton("Create new job");
        createNewJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PositionType positionType = PositionType.TA; //for now, this will be the default value (to avoid situation when user select neither of the choices)

                if (graderRadio.isSelected())
                    positionType = PositionType.Grader;

                int courseNumber = (int) createNewJobSpinner.getValue();

                //TODO: Create an empty job and (empty?) instructor in order to create the job. (Will be done in future deliverable when we will implement all the remaining functionalities)
            }
        });
        createNewJobButton.setBounds(533, 96, 221, 29);
        contentPane.add(createNewJobButton);

        this.setSize(800,400);
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
}
