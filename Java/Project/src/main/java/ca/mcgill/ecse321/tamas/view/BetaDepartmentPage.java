package ca.mcgill.ecse321.tamas.view;


//GUI classes

import ca.mcgill.ecse321.tamas.controller.DepartmentController;
import ca.mcgill.ecse321.tamas.controller.InstructorController;
import ca.mcgill.ecse321.tamas.controller.InvalidInputException;
import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.*;
import com.alee.extended.date.WebDateField;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.text.WebTextArea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

//model classes
//Controller class

public class BetaDepartmentPage extends JFrame {

    private DepartmentController departmentController;
    private StudentController studentController;
    private InstructorController instructorController;

    private WebTextArea skillsRequiredTextArea;
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
    private JRadioButton undergraduateRadioForRegister;
    private JRadioButton graduateRadioForRegister;
    private JRadioButton TARadio;
    private JRadioButton graderRadio;
    private JSpinner createNewJobSpinner;
    private WebDateField offerDeadlineDatePicker;


    private JComboBox<String> jobListForPublishJobPosting;
    private JComboBox<String> jobListForStudentApplyJob;
    private WebComboBox courseList;
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

    private Integer widthOfApp = 800;
    private Integer heightOfApp = 800;

    final private WebDesktopPane desktopPane = new WebDesktopPane();
    Department department;


    /**
     * Create the frame.
     */

    public BetaDepartmentPage(final Department department) {

        WebLookAndFeel.install();
        this.department = department;

        departmentController = new DepartmentController(department);
        studentController = new StudentController(department);
        instructorController = new InstructorController(department);

        setTitle("Department's Page");
        GroupPanel groupPanel;
        WebTabbedPane tabbedPane = new WebTabbedPane();
        BorderLayout borderLayout = new BorderLayout(0,0);
        borderLayout.preferredLayoutSize(getContentPane());
        getContentPane().setLayout(borderLayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);


        desktopPane.setOpaque(false);
        this.getContentPane().setBackground(new Color(239,62,51));

        WebPanel webPanel = new WebPanel();
        webPanel.setLayout(new GridLayout(0,8,-3,-3));

        addNewFrame(desktopPane,PublishJobView(),"Publish Job Posting", "Publish Job Posting", webPanel, 393, 388);
        addNewFrame(desktopPane,RegisterAStudent(),"Register a Student", "Register a Student", webPanel, 324, 354);
        addNewFrame(desktopPane,ApplyForAJob(),"Apply for a Job", "Apply for a Job", webPanel, 350, 300);
        addNewFrame(desktopPane,CreateAJob(),"Create a Job", "Create a Job", webPanel, 350, 300);
        addNewFrame(desktopPane,CreateACourse(),"Create a Course", "Create a Course", webPanel, 350, 300);
        addNewFrame(desktopPane, CreateAnInstructor(),"Create an Instructor", "Create an Instructor", webPanel, 350, 300);
        addNewFrame(desktopPane,CreateOffer(),"Create Offer", "Create Offer", webPanel, 350, 300);
        addNewFrame(desktopPane,CreateRemoveAllocation(),"Create/Remove Allocation", "Create/Remove Allocation", webPanel, 350, 300);

        webPanel.setBounds(0,0,800,800);
        webPanel.setOpaque(false);

        getContentPane().add(desktopPane,BorderLayout.CENTER);
        getContentPane().add(webPanel, BorderLayout.NORTH);

        this.setMinimumSize(new Dimension(widthOfApp,heightOfApp));
        pack();
        updateDisplay();
    }

    //useful for updating the createNewJobSpinner using the spinner model
    private void setCreateNewJobSpinnerModel(SpinnerModel model) {
        createNewJobSpinner.setModel(model);
    }

    private void updateDisplay() {

        //update the **publish a job posting** component
        skillsRequiredTextArea.setText("");
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

        offerDeadlineDatePicker.setDate(null);




    }

    private void addNewFrame(final WebDesktopPane webDesktopPane, Component component, String frameName, String iconName, WebPanel webPanel, int width, int height){
        final WebInternalFrame internalFrame = new WebInternalFrame(frameName, true, true, false,true);
        WebButton internalFrameIcon = new WebButton("<html><font color='white'>"+ iconName + "</font></html>");
        internalFrame.add(component);
        internalFrame.getContentPane().setBackground(new Color(43, 43, 43, 255));
        internalFrameIcon.setRound(0);
        internalFrameIcon.setShineColor(new Color(75,75,75));
        internalFrameIcon.setRolloverShine(true);
        internalFrameIcon.setBorderPainted(false);



       // internalFrameIcon.setRolloverDecoratedOnly(true);
        internalFrameIcon.setShadeColor(new Color(43, 43, 43, 255));
        internalFrameIcon.setBottomBgColor(new Color(43, 43, 43, 255));
        internalFrameIcon.setTopBgColor(new Color(43, 43, 43, 255));
        internalFrameIcon.setHorizontalTextPosition(WebButton.CENTER);
        internalFrameIcon.setVerticalTextPosition(WebButton.BOTTOM);
        internalFrameIcon.addActionListener ( new ActionListener ()
        {
            @Override
            public void actionPerformed ( final ActionEvent e )
            {
                if ( internalFrame.isClosed () )
                {
                    if ( internalFrame.getParent () == null )
                    {
                        updateDisplay();
                        webDesktopPane.add ( internalFrame );
                    }
                    internalFrame.open ();
                    internalFrame.setIcon ( false );
                }
                else
                {
                    internalFrame.setIcon ( !internalFrame.isIcon () );
                }
            }
        } );

        internalFrameIcon.setPreferredSize(100,75);
        webPanel.add(internalFrameIcon);

        internalFrame.setBounds ((int)(Math.random()*(widthOfApp/2)), (int)(Math.random()*(heightOfApp/2)), width, height );


        internalFrame.close ();

    }

    private Component PublishJobView(){
        WebPanel contentPane = new WebPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);

        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel jobIDLabel = new JLabel("Job ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty =1;
        gridBagConstraints.weightx = 1;

        //jobIDLabel.setBounds(10, 40, 90, 16);

        contentPane.add(jobIDLabel,gridBagConstraints);

        JLabel publishJobLabel = new JLabel("Publish A Job Posting");
        publishJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        // publishJobLabel.setBounds(23, 6, 165, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        contentPane.add(publishJobLabel);

        JLabel skillsRequiredLabel = new JLabel("Skills required");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(skillsRequiredLabel,gridBagConstraints);




        skillsRequiredTextArea = new WebTextArea();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        skillsRequiredTextArea.setLineWrap(true);
        skillsRequiredTextArea.setWrapStyleWord(true);

        WebScrollPane areaScrollSkillsRequired = new WebScrollPane(skillsRequiredTextArea);
        areaScrollSkillsRequired.setPreferredSize(new Dimension(150,150));
        contentPane.add(areaScrollSkillsRequired,gridBagConstraints);

        JLabel experienceRequiredLabel = new JLabel("Exp. required");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = gridBagConstraints.BOTH;
        contentPane.add(experienceRequiredLabel,gridBagConstraints);

        experienceRequiredField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        contentPane.add(experienceRequiredField,gridBagConstraints);
        experienceRequiredField.setColumns(10);

        JLabel jobDescriptionLabel = new JLabel("Job description");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        contentPane.add(jobDescriptionLabel,gridBagConstraints);

        jobDescriptionField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        contentPane.add(jobDescriptionField,gridBagConstraints);
        jobDescriptionField.setColumns(10);

        final JLabel offerDeadlineLabel = new JLabel("Offer Deadline");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        contentPane.add(offerDeadlineLabel,gridBagConstraints);

        offerDeadlineDatePicker= new WebDateField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        contentPane.add(offerDeadlineDatePicker,gridBagConstraints);

        final JLabel publishJobPostingErrorLabel = new JLabel("");
        publishJobPostingErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        contentPane.add(publishJobPostingErrorLabel);

        //declare this spinner final since accessed from a inner class
        jobListForPublishJobPosting = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(jobListForPublishJobPosting,gridBagConstraints);

        WebButton publishJobPostingButton = new WebButton("Publish job posting");
        publishJobPostingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String skillsRequired, experienceRequired, jobDescription;
                int jobID;
                Job associatedJob = null;

                skillsRequired = skillsRequiredTextArea.getText();
                experienceRequired = experienceRequiredField.getText();
                jobDescription = jobDescriptionField.getText();

                //if the job was found, add the new attributes. Otherwise, display an error.
                if (selectedJobForPublishJobPosting >= 0) {

                    associatedJob = department.getAllJob(selectedJobForPublishJobPosting);
                    InstructorController instructorController = new InstructorController(department);

                    try {
                        instructorController.createJobPosting(associatedJob, jobDescription, skillsRequired, experienceRequired, (Date) offerDeadlineDatePicker.getDate() );
                        associatedJob.setState(JobStatus.Posted); //post the job
                        publishJobPostingErrorLabel.setText(""); //it worked so remove the error

                    } catch (InvalidInputException e1) {
                        publishJobPostingErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                        updateDisplay();
                    }

                } else
                    publishJobPostingErrorLabel.setText("Please select a job.");

                updateDisplay();
            }
        });

        jobListForPublishJobPosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForPublishJobPosting = cb.getSelectedIndex();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        contentPane.add(publishJobPostingButton,gridBagConstraints);

        return scrollPane;
    }

    private Component RegisterAStudent(){

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel studentNameLabel = new JLabel("Student name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentNameLabel,gridBagConstraints);

        studentNameField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentNameField,gridBagConstraints);
        studentNameField.setColumns(10);

        JLabel emailLabel = new JLabel("Email");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        contentPane.add(emailLabel,gridBagConstraints);


        JLabel RegisterStudentLabel = new JLabel("Register a Student");
        RegisterStudentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(RegisterStudentLabel,gridBagConstraints);

        emailField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        contentPane.add(emailField,gridBagConstraints);
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        contentPane.add(undergraduateRadioForRegister,gridBagConstraints);

        graduateRadioForRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        contentPane.add(graduateRadioForRegister,gridBagConstraints);

        JLabel studentYearLabel = new JLabel("Year");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        contentPane.add(studentYearLabel,gridBagConstraints);

        studentYearField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        contentPane.add(studentYearField,gridBagConstraints);
        studentYearField.setColumns(10);

        JLabel jobPreferenceLabel = new JLabel("Job preference");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        contentPane.add(jobPreferenceLabel,gridBagConstraints);

        jobPreferenceField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        contentPane.add(jobPreferenceField,gridBagConstraints);
        jobPreferenceField.setColumns(10);

        JLabel studentIDLabel = new JLabel("Student ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(studentIDLabel,gridBagConstraints);

        studentIDField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(studentIDField,gridBagConstraints);
        studentIDField.setColumns(10);

        JLabel studentHoursLabel = new JLabel("Number of hours");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        contentPane.add(studentHoursLabel,gridBagConstraints);

        studentHoursField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        contentPane.add(studentHoursField,gridBagConstraints);
        studentHoursField.setColumns(10);

        final JLabel registerAStudentErrorLabel = new JLabel("");
        registerAStudentErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth =2;
        contentPane.add(registerAStudentErrorLabel,gridBagConstraints);

        WebButton registerStudentButton = new WebButton("Register student");
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
                    registerAStudentErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                }

                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth =2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(registerStudentButton,gridBagConstraints);


        return scrollPane;
    }

    private Component ApplyForAJob(){

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel applyForAJobLabel = new JLabel("Apply for a Job");
        applyForAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(applyForAJobLabel,gridBagConstraints);

        JLabel studentIDForApplyingLabel = new JLabel("Student ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentIDForApplyingLabel,gridBagConstraints);

        studentIDForApplyingField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentIDForApplyingField,gridBagConstraints);
        studentIDForApplyingField.setColumns(10);

        JLabel jobIDForApplyingLabel = new JLabel("Job title");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(jobIDForApplyingLabel,gridBagConstraints);

        jobListForStudentApplyJob = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(jobListForStudentApplyJob,gridBagConstraints);

        final JLabel applyForAJobErrorLabel = new JLabel("");
        applyForAJobErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        contentPane.add(applyForAJobErrorLabel,gridBagConstraints);

        WebButton applyForAJobButton = new WebButton("Apply!");
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
                    applyForAJobErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>"); //this shouldn't happen since the user chooses the job from a spinner so the job must be existent

                }

                updateDisplay();

            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jobListForStudentApplyJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForStudentApply = cb.getSelectedIndex();
            }
        });

        contentPane.add(applyForAJobButton,gridBagConstraints);

        return scrollPane;
    }

    private Component CreateAJob(){
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createAJobLabel = new JLabel("Create a Job");
        createAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(createAJobLabel,gridBagConstraints);


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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(TARadio,gridBagConstraints);

        graderRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(graderRadio,gridBagConstraints);

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
        courseList = new WebComboBox(new String[0]);
        courseList.setEditable(true);
        //courseList.setEditorColumns();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(courseList,gridBagConstraints);

        final WebButton createNewJobButton = new WebButton("Create new job");
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


                departmentController.createJob(positionType, dummyPostDeadLine, department.getAllCourse(selectedCourse));
                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        contentPane.add(createNewJobButton,gridBagConstraints);

        courseList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedCourse = cb.getSelectedIndex();
            }
        });

        return scrollPane;
    }

    private Component CreateACourse(){
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createACourseLabel = new JLabel("Create a Course");
        createACourseLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(createACourseLabel,gridBagConstraints);

        JLabel courseNameLabel = new JLabel("Course name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(courseNameLabel,gridBagConstraints);

        courseNameField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(courseNameField,gridBagConstraints);
        courseNameField.setColumns(10);

        JLabel courseCodeLabel = new JLabel("Course code");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(courseCodeLabel,gridBagConstraints);

        courseCodeField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(courseCodeField,gridBagConstraints);
        courseCodeField.setColumns(10);

        JLabel semesterLabel = new JLabel("Semester");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        contentPane.add(semesterLabel,gridBagConstraints);

        semesterJComboBox = new JComboBox();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        contentPane.add(semesterJComboBox,gridBagConstraints);

        semesterJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedSemesterForCreateACourse = cb.getSelectedIndex();
            }
        });


        JLabel creditsLabel = new JLabel("Credits");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        contentPane.add(creditsLabel,gridBagConstraints);

        creditsField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        contentPane.add(creditsField,gridBagConstraints);
        creditsField.setColumns(10);

        JLabel numberOfTutorialsLabel = new JLabel("Number of tutorials");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        contentPane.add(numberOfTutorialsLabel,gridBagConstraints);

        numberOfTutorialsField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        contentPane.add(numberOfTutorialsField,gridBagConstraints);
        numberOfTutorialsField.setColumns(10);

        JLabel numberOfLabsLabel = new JLabel("Number of labs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        contentPane.add(numberOfLabsLabel,gridBagConstraints);

        numberOfLabsField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        contentPane.add(numberOfLabsField,gridBagConstraints);
        numberOfLabsField.setColumns(10);

        JLabel numberStudentEnrolledLabel = new JLabel("Number student enrolled");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        contentPane.add(numberStudentEnrolledLabel,gridBagConstraints);

        numberStudentEnrolledField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        contentPane.add(numberStudentEnrolledField,gridBagConstraints);
        numberStudentEnrolledField.setColumns(10);

        JLabel hoursLabel = new JLabel("Hours");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        contentPane.add(hoursLabel,gridBagConstraints);

        hoursField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        contentPane.add(hoursField,gridBagConstraints);
        hoursField.setColumns(10);

        JLabel numberOfTAsNeededLabel = new JLabel("TAs needed");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        contentPane.add(numberOfTAsNeededLabel,gridBagConstraints);

        numberOfTAsNeededField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        contentPane.add(numberOfTAsNeededField,gridBagConstraints);
        numberOfTAsNeededField.setColumns(10);

        JLabel numberOfGradersNeededLabel = new JLabel("Graders needed");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        contentPane.add(numberOfGradersNeededLabel,gridBagConstraints);

        numberOfGradersNeededField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        contentPane.add(numberOfGradersNeededField,gridBagConstraints);
        numberOfGradersNeededField.setColumns(10);

        JLabel taHourlyRateLabel = new JLabel("TA hourly rate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        contentPane.add(taHourlyRateLabel,gridBagConstraints);

        taHourlyRateField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        contentPane.add(taHourlyRateField,gridBagConstraints);
        taHourlyRateField.setColumns(10);

        JLabel graderHourlyRateLabel = new JLabel("Graders hourly rate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        contentPane.add(graderHourlyRateLabel,gridBagConstraints);

        graderHourlyRateField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        contentPane.add(graderHourlyRateField,gridBagConstraints);
        graderHourlyRateField.setColumns(10);

        JLabel budgetLabel = new JLabel("Budget");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        contentPane.add(budgetLabel,gridBagConstraints);

        budgetField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        contentPane.add(budgetField,gridBagConstraints);
        budgetField.setColumns(10);


        final JLabel createCourseErrorLabel = new JLabel("");
        createCourseErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        contentPane.add(createCourseErrorLabel,gridBagConstraints);

        WebButton createACourseButton = new WebButton("Create a Course");
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
                        createCourseErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                    }
                } else {
                    createCourseErrorLabel.setText("Please select a semester!");
                }

                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        contentPane.add(createACourseButton,gridBagConstraints);

        return scrollPane;
    }

    private Component CreateAnInstructor(){

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel lblCreateAnInstructor = new JLabel("Create an Instructor");
        lblCreateAnInstructor.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(lblCreateAnInstructor,gridBagConstraints);

        JLabel createAnInstructorNameLabel = new JLabel("Full name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAnInstructorNameLabel,gridBagConstraints);

        createAnInstructorNameField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(createAnInstructorNameField,gridBagConstraints);
        createAnInstructorNameField.setColumns(10);

        JLabel instructorIDLabel = new JLabel("Instructor ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorIDLabel,gridBagConstraints);

        instructorIDField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(instructorIDField,gridBagConstraints);
        instructorIDField.setColumns(10);

        JLabel instructorEmailLabel = new JLabel("Email");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorEmailLabel,gridBagConstraints);

        instructorEmailField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(instructorEmailField,gridBagConstraints);
        instructorEmailField.setColumns(10);

        final JLabel createInstructorErrorLabel = new JLabel("");
        createInstructorErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createInstructorErrorLabel,gridBagConstraints);

        WebButton createInstructorButton = new WebButton("Create new instructor");
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
                    createInstructorErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                }
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createInstructorButton,gridBagConstraints);

        return scrollPane;
    }

    private Component CreateRemoveAllocation(){
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createAllocationLabel = new JLabel("Create/Remove Allocation");
        createAllocationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createAllocationLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(createAllocationLabel,gridBagConstraints);

        final JRadioButton createAllocationRadio = new JRadioButton("Create");
        final JRadioButton removeAllocationRadio = new JRadioButton("Remove");

        ButtonGroup group3 = new ButtonGroup();
        group3.add(createAllocationRadio);
        group3.add(removeAllocationRadio);

        createAllocationRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationRadio,gridBagConstraints);

        removeAllocationRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        gridBagConstraints.gridx++;
        contentPane.add(removeAllocationRadio,gridBagConstraints);

        JLabel createAllocationStudentLabel = new JLabel("Student");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationStudentLabel,gridBagConstraints);

        createAllocationStudentComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createAllocationStudentComboBox,gridBagConstraints);

        createAllocationStudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForCreateAllocation = cb.getSelectedIndex();
            }
        });

        JLabel createAllocationJobLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationJobLabel,gridBagConstraints);

        createAllocationJobComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createAllocationJobComboBox,gridBagConstraints);

        createAllocationJobComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForCreateAllocation = cb.getSelectedIndex();
            }
        });


        final JLabel createAllocationErrorLabel = new JLabel("");
        createAllocationErrorLabel.setForeground(Color.RED);
        createAllocationErrorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationErrorLabel,gridBagConstraints);

        WebButton createAllocationButton = new WebButton("Create/Remove");
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
                        departmentController.createAllocation(job,student);
                    } else {
                        departmentController.removeAllocation(job,student);
                    }

                } else {
                    createAllocationErrorLabel.setText("Please selected all the fields.");
                }

                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationButton,gridBagConstraints);

        return scrollPane;
    }

    private Component CreateOffer(){

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createOfferLabel = new JLabel("Create Offer");
        createOfferLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(createOfferLabel,gridBagConstraints);

        JLabel createOfferStudentLabel = new JLabel("Student");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferStudentLabel,gridBagConstraints);

        createOfferStudentComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createOfferStudentComboBox,gridBagConstraints);

        createOfferStudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForCreateOffer = cb.getSelectedIndex();
            }
        });

        JLabel createOfferJobLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferJobLabel,gridBagConstraints);

        createOfferJobComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createOfferJobComboBox,gridBagConstraints);

        createOfferJobComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForCreateOffer = cb.getSelectedIndex();
            }
        });

        final JLabel createOfferErrorLabel = new JLabel("");
        createOfferErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferErrorLabel,gridBagConstraints);

        WebButton createOfferButton = new WebButton("Create offer");
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

                    departmentController.createJobOffer(job,student);

                } else {
                    createOfferErrorLabel.setText("Please select all the fields.");
                }

            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferButton,gridBagConstraints);
        return scrollPane;
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
