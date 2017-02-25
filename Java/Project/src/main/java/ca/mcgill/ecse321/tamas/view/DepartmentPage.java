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
import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Instructor;

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
    private JTextField courseNumberField;
    private JTextField studentNameForApplyingField;

    //My own private fields
    private final JRadioButton undergraduateRadioForCreatingJob;
    private final JRadioButton graduateRadioForCreatingJob;
    private final JRadioButton undergraduateRadioForRegister;
    private final JRadioButton graduateRadioForRegister;
    private JSpinner createNewJobSpinner;

    /**
     * Create the frame.
     */
    public DepartmentPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel jobTitleLabel = new JLabel("Job title");
        jobTitleLabel.setBounds(10, 40, 90, 16);
        contentPane.add(jobTitleLabel);

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

        JButton publishJobPostingButton = new JButton("Publish job posting");
        publishJobPostingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        undergraduateRadioForRegister.setBounds(255, 175, 124, 23);
        contentPane.add(undergraduateRadioForRegister);

        graduateRadioForRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        graduateRadioForRegister.setBounds(391, 175, 101, 23);
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

        JLabel courseNumberLabel = new JLabel("Course number");
        courseNumberLabel.setBounds(10, 152, 90, 16);
        contentPane.add(courseNumberLabel);

        courseNumberField = new JTextField();
        courseNumberField.setBounds(112, 147, 100, 26);
        contentPane.add(courseNumberField);
        courseNumberField.setColumns(10);

        JButton registerStudentButton = new JButton("Register student");
        registerStudentButton.setBounds(260, 210, 232, 29);
        contentPane.add(registerStudentButton);

        JLabel applyForAJobLabel = new JLabel("Apply for a Job");
        applyForAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        applyForAJobLabel.setBounds(591, 162, 110, 16);
        contentPane.add(applyForAJobLabel);

        JLabel studentNameForApplyingLabel = new JLabel("Student Name");
        studentNameForApplyingLabel.setBounds(533, 190, 61, 16);
        contentPane.add(studentNameForApplyingLabel);

        studentNameForApplyingField = new JTextField();
        studentNameForApplyingField.setBounds(635, 185, 130, 26);
        contentPane.add(studentNameForApplyingField);
        studentNameForApplyingField.setColumns(10);

        JLabel jobTitleForApplyingLabel = new JLabel("Job title");
        jobTitleForApplyingLabel.setBounds(533, 215, 61, 16);
        contentPane.add(jobTitleForApplyingLabel);

        JButton applyForAJobButton = new JButton("Apply!");
        applyForAJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        applyForAJobButton.setBounds(533, 242, 232, 26);
        contentPane.add(applyForAJobButton);

        JLabel createAJobLabel = new JLabel("Create a Job");
        createAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        createAJobLabel.setBounds(591, 6, 140, 20);
        contentPane.add(createAJobLabel);


        undergraduateRadioForCreatingJob = new JRadioButton("Undergraduate");
        graduateRadioForCreatingJob = new JRadioButton("Graduate");

        //Created a group for the radio
        ButtonGroup group = new ButtonGroup();
        group.add(undergraduateRadioForCreatingJob);
        group.add(graduateRadioForCreatingJob);

        undergraduateRadioForCreatingJob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        undergraduateRadioForCreatingJob.setBounds(533, 31, 130, 23);
        contentPane.add(undergraduateRadioForCreatingJob);

        graduateRadioForCreatingJob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        graduateRadioForCreatingJob.setBounds(675, 31, 90, 23);
        contentPane.add(graduateRadioForCreatingJob);





        //temporary course object to test the spinner
        Instructor instructor = new Instructor("Prakash",26057929, "prakash@mail.mcgill.ca");
        Course courseA = new Course("COMP 302", "Prog Lang and Par", "Winter", 3, 0, 0, 9, 150, 4, 6, 15, 15, 4000, instructor);

        String[] profStrings = {"Prakash", "David", "Mathieu"};

        List<String> profStringsList = new ArrayList<String>();

        profStringsList.add("Prakash");
        profStringsList.add("David");
        profStringsList.add("Jerome");

        SpinnerListModel profModel = new SpinnerListModel(profStringsList);


        //spinners
        createNewJobSpinner = new JSpinner(profModel);
        ((JSpinner.DefaultEditor) createNewJobSpinner.getEditor()).getTextField().setEditable(false);
        createNewJobSpinner.setBounds(533, 63, 221, 26);
        contentPane.add(createNewJobSpinner);

        JSpinner jobTitleSpinner = new JSpinner();
        jobTitleSpinner.setBounds(112, 35, 100, 26);
        contentPane.add(jobTitleSpinner);

        JButton createNewJobButton = new JButton("Create new job");
        createNewJobButton.setBounds(533, 96, 221, 29);
        contentPane.add(createNewJobButton);

        JSpinner applyForJobSpinner = new JSpinner();
        applyForJobSpinner.setBounds(636, 212, 126, 26);
        contentPane.add(applyForJobSpinner);

        this.setSize(800,400);
    }

    public void setCreateNewJobSpinnerModel(SpinnerModel model) {
        createNewJobSpinner.setModel(model);
    }

    private void addCourseToSpinner(Course course) {

        createNewJobSpinner.setValue(100);
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

        studentNameForApplyingField.setText(text);
    }

    public String getStudentForApplyingField() {

        return studentNameForApplyingField.getText();
    }
}
