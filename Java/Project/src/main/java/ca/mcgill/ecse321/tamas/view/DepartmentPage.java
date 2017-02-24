package ca.mcgill.ecse321.tamas.view;

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

public class DepartmentPage extends JFrame {

    private JPanel contentPane;
    private JTextField positionTypeField;
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
    private JTextField jobForApplyingField;


    /**
     * Create the frame.
     */

    public DepartmentPage() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel positionTypeLabel = new JLabel("Position type");
        positionTypeLabel.setBounds(10, 35, 90, 16);
        contentPane.add(positionTypeLabel);

        positionTypeField = new JTextField();
        positionTypeField.setBounds(105, 30, 100, 26);
        contentPane.add(positionTypeField);
        positionTypeField.setColumns(10);

        JLabel skillsRequiredLabel = new JLabel("Skills required");
        skillsRequiredLabel.setBounds(10, 63, 90, 16);
        contentPane.add(skillsRequiredLabel);

        skillsRequiredField = new JTextField();
        skillsRequiredField.setBounds(105, 58, 100, 26);
        contentPane.add(skillsRequiredField);
        skillsRequiredField.setColumns(10);

        JLabel experienceRequiredLabel = new JLabel("Exp. required");
        experienceRequiredLabel.setBounds(10, 91, 90, 16);
        contentPane.add(experienceRequiredLabel);

        experienceRequiredField = new JTextField();
        experienceRequiredField.setBounds(105, 86, 100, 26);
        contentPane.add(experienceRequiredField);
        experienceRequiredField.setColumns(10);

        JLabel jobDescriptionLabel = new JLabel("Job description");
        jobDescriptionLabel.setBounds(10, 119, 90, 16);
        contentPane.add(jobDescriptionLabel);

        jobDescriptionField = new JTextField();
        jobDescriptionField.setBounds(105, 114, 100, 26);
        contentPane.add(jobDescriptionField);
        jobDescriptionField.setColumns(10);

        JButton publishJobPostingButton = new JButton("Publish job posting");
        publishJobPostingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        publishJobPostingButton.setBounds(10, 175, 190, 29);
        contentPane.add(publishJobPostingButton);

        JLabel studentNameLabel = new JLabel("Student name");
        studentNameLabel.setBounds(215, 63, 90, 16);
        contentPane.add(studentNameLabel);

        studentNameField = new JTextField();
        studentNameField.setBounds(310, 58, 130, 26);
        contentPane.add(studentNameField);
        studentNameField.setColumns(10);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(215, 91, 61, 16);
        contentPane.add(emailLabel);

        JLabel publishJobLabel = new JLabel("Publish A Job Posting");
        publishJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        publishJobLabel.setBounds(23, 6, 165, 20);
        contentPane.add(publishJobLabel);

        JLabel RegisterStudentLabel = new JLabel("Register a Student");
        RegisterStudentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        RegisterStudentLabel.setBounds(260, 7, 140, 20);
        contentPane.add(RegisterStudentLabel);

        emailField = new JTextField();
        emailField.setBounds(310, 86, 130, 26);
        contentPane.add(emailField);
        emailField.setColumns(10);

        JRadioButton undergraduateRadio = new JRadioButton("Undergraduate");
        undergraduateRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        undergraduateRadio.setBounds(217, 115, 124, 23);
        contentPane.add(undergraduateRadio);

        JRadioButton graduateRadio = new JRadioButton("Graduate");
        graduateRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        graduateRadio.setBounds(339, 115, 101, 23);
        contentPane.add(graduateRadio);

        JLabel studentYearLabel = new JLabel("Year");
        studentYearLabel.setBounds(215, 147, 61, 16);
        contentPane.add(studentYearLabel);

        studentYearField = new JTextField();
        studentYearField.setBounds(310, 142, 130, 26);
        contentPane.add(studentYearField);
        studentYearField.setColumns(10);

        JLabel jobPreferenceLabel = new JLabel("Job preference");
        jobPreferenceLabel.setBounds(215, 175, 90, 16);
        contentPane.add(jobPreferenceLabel);

        jobPreferenceField = new JTextField();
        jobPreferenceField.setBounds(310, 170, 130, 26);
        contentPane.add(jobPreferenceField);
        jobPreferenceField.setColumns(10);

        JLabel studentIDLabel = new JLabel("Student ID");
        studentIDLabel.setBounds(215, 35, 90, 16);
        contentPane.add(studentIDLabel);

        studentIDField = new JTextField();
        studentIDField.setBounds(310, 30, 130, 26);
        contentPane.add(studentIDField);
        studentIDField.setColumns(10);

        JLabel courseNumberLabel = new JLabel("Course number");
        courseNumberLabel.setBounds(10, 147, 90, 16);
        contentPane.add(courseNumberLabel);

        courseNumberField = new JTextField();
        courseNumberField.setBounds(105, 142, 100, 26);
        contentPane.add(courseNumberField);
        courseNumberField.setColumns(10);

        JButton registerStudentButton = new JButton("Register");
        registerStudentButton.setBounds(323, 204, 117, 29);
        contentPane.add(registerStudentButton);

        JLabel applyForAJobLabel = new JLabel("Apply for a Job");
        applyForAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        applyForAJobLabel.setBounds(60, 208, 110, 16);
        contentPane.add(applyForAJobLabel);

        JLabel studentNameForApplyingLabel = new JLabel("Student Name");
        studentNameForApplyingLabel.setBounds(10, 233, 61, 16);
        contentPane.add(studentNameForApplyingLabel);

        studentNameForApplyingField = new JTextField();
        studentNameForApplyingField.setBounds(87, 228, 130, 26);
        contentPane.add(studentNameForApplyingField);
        studentNameForApplyingField.setColumns(10);

        JLabel JobForApplyingLabel = new JLabel("Job");
        JobForApplyingLabel.setBounds(10, 256, 61, 16);
        contentPane.add(JobForApplyingLabel);

        jobForApplyingField = new JTextField();
        jobForApplyingField.setBounds(87, 251, 130, 26);
        contentPane.add(jobForApplyingField);
        jobForApplyingField.setColumns(10);

        JButton applyForAJobButton = new JButton("Apply!");
        applyForAJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        applyForAJobButton.setBounds(225, 233, 100, 43);
        contentPane.add(applyForAJobButton);
    }



    //*****SETTERS AND GETTERS******

    public void setPositionTypeField(String text) {

        positionTypeField.setText(text);
    }

    public String getPositionTypeField() {

        return positionTypeField.getText();
    }

    public void setSkillsRequiredField(String text) {

        skillsRequiredField.setText(text);
    }

    public String getSkillsRequiredField() {

        return positionTypeField.getText();
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

    public void setJobForApplyingField(String text) {

        jobForApplyingField.setText(text);
    }

    public String getJobForApplyingField() {

        return jobForApplyingField.getText();
    }
}
