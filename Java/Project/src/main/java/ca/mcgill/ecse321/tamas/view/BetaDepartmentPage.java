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
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextArea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

//model classes
//Controller class

public class BetaDepartmentPage extends JFrame {
    //Controller
    private DepartmentController departmentController;
    private StudentController studentController;
    private InstructorController instructorController;
    //End Controller

    //Publish Job Posting
    private JComboBox<String> instructorListForPublishJobPosting;
    private JComboBox<String> courseListForPublishJobPosting;
    private JComboBox<String> jobListForPublishJobPosting;
    private WebTextArea skillsRequiredTextArea;
    private JTextField experienceRequiredField;
    private JTextField jobDescriptionField;
    private WebDateField offerDeadlineDatePicker;

    private Job associatedJobForPublishJobPosting = null;
    private Course associatedCourseForPublishJobPosting = null;
    private Instructor associatedInstructorForPublishJobPosting = null;

    //End Publish Job Posting

    //Register a Student
    private JTextField studentNameField;
    private JTextField emailField;
    private JRadioButton undergraduateRadioForRegister;
    private JRadioButton graduateRadioForRegister;
    private JTextField studentYearField;
    private JTextField jobPreferenceField;
    private JTextField studentIDField;
    private JTextField studentHoursField;
    //End Register a Student

    //Apply for a Job
    private JComboBox<String> studentListForStudentApplyJob;
    private JComboBox<String> jobListForStudentApplyJob;
    private WebDateField publishDeadlineDatePicker;

    private WebTextArea skillsRequiredTextAreaForApply;
    private JTextField experienceRequiredFieldForApply;
    private JTextField jobDescriptionFieldForApply;

    //End Apply for a Job

    //Create a Job
    private JRadioButton TARadio;
    private JRadioButton graderRadio;
    private WebComboBox courseListForCreateAJob;
    //End Create a Job

    //Create a Course
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JComboBox<String> instructorListForCreateACourse;
    private JComboBox<String> semesterJComboBox;
    private JTextField creditsField;
    private JTextField numberOfTutorialsField;
    private JTextField numberOfLabsField;
    private JTextField numberStudentEnrolledField;
    private JTextField hoursField;
    private JTextField numberOfTAsNeededField;
    private JTextField numberOfGradersNeededField;
    private JTextField taHourlyRateField;
    private JTextField graderHourlyRateField;
    private JTextField budgetField;
    //End Create a Course

    //Create an Instructor
    private JTextField createAnInstructorNameField;
    private JTextField instructorIDField;
    private JTextField instructorEmailField;
    //End Create an Instructor

    //Create/Remove Allocation
    private JRadioButton createAllocationRadio;
    private JRadioButton removeAllocationRadio;
    private JComboBox<String> createAllocationStudentComboBox;
    private JComboBox<String> createAllocationJobComboBox;
    //End Create/Remove Allocation

    //Create Offer
    private JComboBox<String> createOfferStudentComboBox;
    private JComboBox<String> createOfferJobComboBox;
    //End Create Offer

    //View Student
    private JComboBox<String> studentListForViewStudent;
    private JTextField studentNameFieldForViewStudent;
    private JTextField emailFieldForViewStudent;
    private JRadioButton undergraduateRadioForRegisterForViewStudent;
    private JRadioButton graduateRadioForRegisterForViewStudent;
    private JTextField studentYearFieldForViewStudent;
    private WebTextArea jobPreferenceTextAreaForViewStudent;
    private JTextField studentIDFieldForViewStudent;
    private JTextField studentHoursFieldForViewStudent;
    private DefaultTableModel currentJobTableModel = new DefaultTableModel();
    private DefaultTableModel appliedToJobsTableModel = new DefaultTableModel();
    private DefaultTableModel previousJobsTableModel = new DefaultTableModel();
    //End View Student

    //View Course
    private JComboBox<String> courseListForViewCourse;
    private JTextField courseNameFieldForViewCourse;
    private JTextField courseCodeFieldForViewCourse;
    private JComboBox<String> instructorListForViewCourse;
    private JRadioButton fallRadio;
    private JRadioButton winterRadio;
    private JTextField creditsFieldForViewCourse;
    private JTextField numberOfTutorialsFieldForViewCourse;
    private JTextField numberOfLabsFieldForViewCourse;
    private JTextField numberStudentEnrolledFieldForViewCourse;
    private JTextField hoursFieldForViewCourse;
    private JTextField numberOfTAsNeededFieldForViewCourse;
    private JTextField numberOfGradersNeededFieldForViewCourse;
    private JTextField taHourlyRateFieldForViewCourse;
    private JTextField graderHourlyRateFieldForViewCourse;
    private JTextField budgetFieldForViewCourse;
    //End View Course

    //View Job
    private JComboBox<String> courseListForViewJob;
    private JComboBox<String> jobListForViewJob;
    private WebTextArea skillsRequiredTextAreaForViewJob;
    private WebTextArea experienceRequiredFieldForViewJob;
    private WebTextArea jobDescriptionFieldForViewJob;
    private WebDateField offerDeadlineDatePickerForViewJob;

    private Job associatedJobForViewJob = null;
    private Course associatedCourseForViewJob = null;

    private DefaultTableModel employeeTableModel = new DefaultTableModel();
    private DefaultTableModel applicantTableModel = new DefaultTableModel();
    private DefaultTableModel allocatedTableModel = new DefaultTableModel();
    //End View Job

    private Integer selectedInstructorForPublishJobPosting = -1;
    private Integer selectedJobForPublishJobPosting = -1;
    private Integer selectedCourseForPublishJobPosting = -1;
    private Integer selectedJobForStudentApply = -1;
    private Integer selectedStudentForStudentApply = -1;
    private Integer selectedCourseForCreateAJob = -1;
    private Integer selectedInstructorForCreateACourse;
    private Integer selectedSemesterForCreateACourse = -1;
    private Integer selectedStudentForCreateAllocation = -1;
    private Integer selectedJobForCreateAllocation = -1;
    private Integer selectedStudentForCreateOffer = -1;
    private Integer selectedJobForCreateOffer = -1;
    private Integer selectedStudentForViewStudent = -1;
    private Integer selectedSemesterForViewJob = -1;
    private Integer selectedInstructorForViewCourse = -1;
    private Integer selectedCourseListForViewCourse = -1;
    private Integer selectedCourseListForViewJob = -1;
    private Integer selectedJobListForViewJob = -1;


    private Integer widthOfApp = 800;
    private Integer heightOfApp = 700;

    private WebDesktopPane desktopPane = new WebDesktopPane();
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
        BorderLayout borderLayout = new BorderLayout(0, 0);
        borderLayout.preferredLayoutSize(getContentPane());
        getContentPane().setLayout(borderLayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);


        desktopPane.setOpaque(false);
        this.getContentPane().setBackground(new Color(250, 62, 51));

        WebPanel webPanel = new WebPanel();
        webPanel.setLayout(new GridLayout(0, 8, -3, -3));

        addNewFrame(desktopPane, PublishJobView(), "Publish Job Posting", "Publish Job Posting", webPanel, 469, 423);
        addNewFrame(desktopPane, RegisterAStudent(), "Register a Student", "Register a Student", webPanel, 324, 354);
        addNewFrame(desktopPane, ApplyForAJob(), "Apply for a Job", "Apply for a Job", webPanel, 350, 300);
        addNewFrame(desktopPane, CreateAJob(), "Create a Job", "Create a Job", webPanel, 350, 300);
        addNewFrame(desktopPane, CreateACourse(), "Create a Course", "Create a Course", webPanel, 440, 450);
        addNewFrame(desktopPane, CreateAnInstructor(), "Create an Instructor", "Create an Instructor", webPanel, 350, 300);
        addNewFrame(desktopPane, CreateOffer(), "Create Offer", "Create Offer", webPanel, 350, 300);
        addNewFrame(desktopPane, CreateRemoveAllocation(), "Create/Remove Allocation", "Create/Remove Allocation", webPanel, 350, 300);
        addNewFrame(desktopPane, ViewStudent(), "View Student Info", "View Student Info", webPanel, 500, 300);
        addNewFrame(desktopPane, ViewCourse(), "View Course Info", "View Course Info", webPanel, 500, 300);
        addNewFrame(desktopPane, ViewJob(), "View Job Info", "View Job Info", webPanel, 700, 400);


        webPanel.setBounds(0, 0, 900, 900);
        webPanel.setOpaque(false);

        getContentPane().add(desktopPane, BorderLayout.CENTER);
        getContentPane().add(webPanel, BorderLayout.NORTH);

        this.setMinimumSize(new Dimension(widthOfApp, heightOfApp));
        pack();
        updateDisplay();
    }


    private void updateDisplay() {
        updatePublishJobView(null, null, true);
        updateRegisterAStudent();
        updateApplyforAJob();
        updateCreateACourse();
        updateCreateAJob();
        updateCreateAnInstructor();
        updateCreateOffer();
        updateCreateRemoveAllocation();
        updateStudentView();
        updateCourseView();
        updateJobView();
    }

    private void updatePublishJobView(Instructor instructor, Course course, boolean clearData) {
        if(clearData) {
            //Clear job List combobox

            instructorListForPublishJobPosting.removeAllItems();
            for (Instructor i : department.getAllInstructors()) {
                String tmp = i.getInstructorID() + "(" + i.getName()+ ")";
                instructorListForPublishJobPosting.addItem(tmp);
            }

            selectedInstructorForPublishJobPosting = -1;
            instructorListForPublishJobPosting.setSelectedIndex(selectedInstructorForPublishJobPosting);

            associatedInstructorForPublishJobPosting = null;



            jobListForPublishJobPosting.removeAllItems();
            selectedJobForPublishJobPosting = -1;
            jobListForPublishJobPosting.setSelectedIndex(selectedJobForPublishJobPosting);

            associatedJobForPublishJobPosting = null;

            courseListForPublishJobPosting.removeAllItems();
            selectedCourseForPublishJobPosting = -1;
            courseListForPublishJobPosting.setSelectedIndex(selectedCourseForPublishJobPosting);

            associatedCourseForPublishJobPosting = null;

            //Clear Skills Required Text Area
            skillsRequiredTextArea.setText("");

            //Clear Experience required Text Area
            experienceRequiredField.setText("");

            //Clear Job Description Text Area
            jobDescriptionField.setText("");

            //Clear Offer Deadline Date
            offerDeadlineDatePicker.setDate(null);
        }else{
            if(course !=null){

                jobListForPublishJobPosting.removeAllItems();
                for (Job j : course.getJobs()) {
                    if(j.getState() == JobStatus.Ready) {
                        String tmp = j.getPosType().toString();
                        jobListForPublishJobPosting.addItem(tmp);
                    }
                }
                selectedJobForPublishJobPosting = -1;
                jobListForPublishJobPosting.setSelectedIndex(selectedJobForPublishJobPosting);

            }else if(instructor != null){

                courseListForPublishJobPosting.removeAllItems();
                for(Course c: instructor.getCourses()){
                    String tmp = c.getCode() + ": " + c.getName();
                    courseListForPublishJobPosting.addItem(tmp);
                }
                selectedCourseForPublishJobPosting = -1;
                courseListForPublishJobPosting.setSelectedIndex(selectedCourseForPublishJobPosting);

            }else if(instructor == null){
                courseListForPublishJobPosting.removeAllItems();
                selectedCourseForPublishJobPosting = -1;
                courseListForPublishJobPosting.setSelectedIndex(selectedCourseForPublishJobPosting);

                jobListForPublishJobPosting.removeAllItems();
                selectedJobForPublishJobPosting = -1;
                jobListForPublishJobPosting.setSelectedIndex(selectedJobForPublishJobPosting);
            }else if(course == null){
                jobListForPublishJobPosting.removeAllItems();
                selectedJobForPublishJobPosting = -1;
                jobListForPublishJobPosting.setSelectedIndex(selectedJobForPublishJobPosting);
            }
        }
    }

    private void updateRegisterAStudent() {
        //Clear Student Name
        studentNameField.setText("");

        //Clear studentEmail
        emailField.setText("");

        //Clear Radio
        undergraduateRadioForRegister.setSelected(false);
        graduateRadioForRegister.setSelected(false);

        //Clear Student Year
        studentYearField.setText("");

        //Clear Job Preference
        jobPreferenceField.setText("");

        //Clear Student ID
        studentIDField.setText("");

    }

    private void updateApplyforAJob() {

        studentListForStudentApplyJob.removeAllItems();
        for (Student j : department.getAllStudents()) {
                String tmp = j.getStudentID() + ": " + j.getName();
                studentListForStudentApplyJob.addItem(tmp);
        }
        selectedJobForStudentApply = -1;
        jobListForStudentApplyJob.setSelectedIndex(selectedJobForStudentApply);

        //Clear and Update job list for apply job
        jobListForStudentApplyJob.removeAllItems();
        for (Job j : department.getAllJobs()) {
            if (j.getState() == JobStatus.Posted) {
                String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                jobListForStudentApplyJob.addItem(tmp);
            }
        }
        selectedJobForStudentApply = -1;
        jobListForStudentApplyJob.setSelectedIndex(selectedJobForStudentApply);
    }

    private void updateCreateAJob() {
        //Clear Radio Selection
        TARadio.setSelected(false);
        graderRadio.setSelected(false);
        //Clear/Update Course List
        courseListForCreateAJob.removeAllItems();
        for (Course c : department.getAllCourses()) {
            String tmp = c.getCode() + ": " + c.getName();
            courseListForCreateAJob.addItem(tmp);
        }
        selectedCourseForCreateAJob = -1;
        courseListForCreateAJob.setSelectedIndex(selectedCourseForCreateAJob);

        publishDeadlineDatePicker.setDate(null);

    }

    private void updateCreateACourse() {
        courseNameField.setText("");
        courseCodeField.setText("");

        semesterJComboBox.removeAllItems();
        semesterJComboBox.addItem("Fall");
        semesterJComboBox.addItem("Winter");
        selectedSemesterForCreateACourse = -1;
        semesterJComboBox.setSelectedIndex(selectedSemesterForCreateACourse);

        creditsField.setText("");
        numberOfTutorialsField.setText("");
        numberOfLabsField.setText("");
        numberStudentEnrolledField.setText("");
        hoursField.setText("");
        numberOfTAsNeededField.setText("");
        numberOfGradersNeededField.setText("");
        taHourlyRateField.setText("");
        graderHourlyRateField.setText("");
        budgetField.setText("");


        instructorListForCreateACourse.removeAllItems();
        for (Instructor i : department.getAllInstructors()) {
            String tmp = i.getInstructorID() + "(" + i.getName()+ ")";
            instructorListForCreateACourse.addItem(tmp);
        }

        selectedInstructorForCreateACourse = -1;
        instructorListForCreateACourse.setSelectedIndex(selectedInstructorForCreateACourse);

    }

    private void updateCreateAnInstructor() {
        createAnInstructorNameField.setText("");
        instructorIDField.setText("");
        instructorEmailField.setText("");
    }

    private void updateCreateOffer() {
        createOfferStudentComboBox.removeAllItems();
        for (Student s : department.getAllStudents()) {
            createOfferStudentComboBox.addItem(s.getEmail());
        }
        selectedStudentForCreateOffer = -1;
        createOfferStudentComboBox.setSelectedIndex(selectedStudentForCreateOffer);


        createOfferJobComboBox.removeAllItems();
        for (Job j : department.getAllJobs()) {
            if (j.getState() == JobStatus.Posted) {
                String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                createOfferJobComboBox.addItem(tmp);
            }
        }
        selectedJobForCreateOffer = -1;
        createOfferJobComboBox.setSelectedIndex(selectedJobForCreateOffer);
    }

    private void updateCreateRemoveAllocation() {

        createAllocationRadio.setSelected(false);
        removeAllocationRadio.setSelected(false);
        createAllocationStudentComboBox.removeAllItems();
        for (Student s : department.getAllStudents()) {
            createAllocationStudentComboBox.addItem(s.getEmail());
        }
        selectedStudentForCreateAllocation = -1;
        createAllocationStudentComboBox.setSelectedIndex(selectedStudentForCreateAllocation);

        createAllocationJobComboBox.removeAllItems();
        for (Job j : department.getAllJobs()) {
            if (j.getState() == JobStatus.Posted) {
                String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                createAllocationJobComboBox.addItem(tmp);
            }
        }
        selectedJobForCreateAllocation = -1;
        createAllocationJobComboBox.setSelectedIndex(selectedJobForCreateAllocation);
    }

    private void updateStudentView(){
        studentListForViewStudent.removeAllItems();
        for (Student j : department.getAllStudents()) {
            String tmp = j.getStudentID() + ": " + j.getName();
            studentListForViewStudent.addItem(tmp);

        }
        selectedStudentForViewStudent = -1;
        studentListForViewStudent.setSelectedIndex(selectedStudentForViewStudent);


    }

    private void updateCourseView(){

        courseListForViewCourse.removeAllItems();
        for (Course c : department.getAllCourses()) {
            String tmp = c.getCode() + ": " + c.getName();
            courseListForViewCourse.addItem(tmp);

        }
        selectedCourseListForViewCourse = -1;
        courseListForViewCourse.setSelectedIndex(selectedCourseListForViewCourse);

        selectedInstructorForViewCourse = -1;
        instructorListForViewCourse.setSelectedIndex(selectedInstructorForViewCourse);

    }

    private void updateJobView(){
        courseListForViewJob.removeAllItems();
        for (Course c : department.getAllCourses()) {
            String tmp = c.getCode() + ": " + c.getName();
            courseListForViewJob.addItem(tmp);
        }

        selectedCourseListForViewJob = -1;
        courseListForViewJob.setSelectedIndex(selectedCourseListForViewJob);

        selectedJobListForViewJob = -1;
        jobListForViewJob.setSelectedIndex(selectedJobListForViewJob);
    }


    private void addNewFrame(final WebDesktopPane webDesktopPane, Component component, String frameName, String iconName, WebPanel webPanel, int width, int height) {
        final WebInternalFrame internalFrame = new WebInternalFrame(frameName, true, true, false, true);
        WebButton internalFrameIcon = new WebButton("<html><font color='white'>" + iconName + "</font></html>");
        internalFrame.add(component);
        internalFrame.getContentPane().setBackground(new Color(43, 43, 43, 255));
        internalFrameIcon.setRound(0);
        internalFrameIcon.setShineColor(new Color(75, 75, 75));
        internalFrameIcon.setRolloverShine(true);
        internalFrameIcon.setBorderPainted(false);


        // internalFrameIcon.setRolloverDecoratedOnly(true);
        internalFrameIcon.setShadeColor(new Color(43, 43, 43, 255));
        internalFrameIcon.setBottomBgColor(new Color(43, 43, 43, 255));
        internalFrameIcon.setTopBgColor(new Color(43, 43, 43, 255));
        internalFrameIcon.setHorizontalTextPosition(WebButton.CENTER);
        internalFrameIcon.setVerticalTextPosition(WebButton.BOTTOM);
        internalFrameIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (internalFrame.isClosed()) {
                    if (internalFrame.getParent() == null) {
                        webDesktopPane.add(internalFrame);
                    }
                    internalFrame.open();
                    internalFrame.setIcon(false);
                } else {
                    internalFrame.setIcon(!internalFrame.isIcon());
                }
            }
        });

        internalFrameIcon.setPreferredSize(100, 75);
        webPanel.add(internalFrameIcon);

        internalFrame.setBounds(50, 100, width, height);


        internalFrame.close();

    }

    private Component PublishJobView() {
        final WebPanel contentPane = new WebPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);

        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel publishJobLabel = new JLabel("Publish A Job Posting");
        publishJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        // publishJobLabel.setBounds(23, 6, 165, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        contentPane.add(publishJobLabel, gridBagConstraints);

        JLabel instructorLabel = new JLabel("Instructor");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorLabel,gridBagConstraints);

        instructorListForPublishJobPosting = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(instructorListForPublishJobPosting,gridBagConstraints);

        JLabel courseLabel = new JLabel("Course");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseLabel, gridBagConstraints);

        courseListForPublishJobPosting = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(courseListForPublishJobPosting, gridBagConstraints);

        JLabel jobIDLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(jobIDLabel, gridBagConstraints);

        jobListForPublishJobPosting = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(jobListForPublishJobPosting, gridBagConstraints);

        JLabel skillsRequiredLabel = new JLabel("Skills Required");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(skillsRequiredLabel, gridBagConstraints);

        skillsRequiredTextArea = new WebTextArea();
        gridBagConstraints.gridx++;
        skillsRequiredTextArea.setLineWrap(true);
        skillsRequiredTextArea.setWrapStyleWord(true);

        WebScrollPane areaScrollSkillsRequired = new WebScrollPane(skillsRequiredTextArea);
        areaScrollSkillsRequired.setPreferredSize(new Dimension(150, 150));
        contentPane.add(areaScrollSkillsRequired, gridBagConstraints);

        JLabel experienceRequiredLabel = new JLabel("Exp. Required");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.fill = gridBagConstraints.BOTH;
        contentPane.add(experienceRequiredLabel, gridBagConstraints);

        experienceRequiredField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(experienceRequiredField, gridBagConstraints);
        experienceRequiredField.setColumns(10);

        JLabel jobDescriptionLabel = new JLabel("Job Description");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(jobDescriptionLabel, gridBagConstraints);

        jobDescriptionField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(jobDescriptionField, gridBagConstraints);
        jobDescriptionField.setColumns(10);

        final JLabel offerDeadlineLabel = new JLabel("Offer Deadline");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(offerDeadlineLabel, gridBagConstraints);

        offerDeadlineDatePicker = new WebDateField();
        gridBagConstraints.gridx++;
        contentPane.add(offerDeadlineDatePicker, gridBagConstraints);

        final JLabel publishJobPostingErrorLabel = new JLabel("");
        publishJobPostingErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 2;
        contentPane.add(publishJobPostingErrorLabel,gridBagConstraints);

        WebButton publishJobPostingButton = new WebButton("Publish job posting");
        publishJobPostingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String skillsRequired, experienceRequired, jobDescription,additionalError ="";
                associatedJobForPublishJobPosting = null;

                skillsRequired = skillsRequiredTextArea.getText();
                experienceRequired = experienceRequiredField.getText();
                jobDescription = jobDescriptionField.getText();

                if(associatedInstructorForPublishJobPosting == null){
                    additionalError = "Please select an Instructor!<br>";
                }

                //if the job was found, add the new attributes. Otherwise, display an error.
                if(associatedCourseForPublishJobPosting != null) {
                    for (Job j : associatedCourseForPublishJobPosting.getJobs()) {
                        if (j.getPosType().toString().contentEquals(jobListForPublishJobPosting.getSelectedItem().toString())) {
                            associatedJobForPublishJobPosting = j;
                            break;
                        }
                    }

                }else{
                    additionalError += "Please select a Course!<br>";
                }



                InstructorController instructorController = new InstructorController(department);
                Date tmpDate = null;

                if(offerDeadlineDatePicker.getDate() != null){
                    tmpDate = new Date( offerDeadlineDatePicker.getDate().getTime());
                }

                try {
                    instructorController.createJobPosting(associatedJobForPublishJobPosting, jobDescription, skillsRequired, experienceRequired, tmpDate);
                    publishJobPostingErrorLabel.setText(""); //it worked so remove the error
                    additionalError = "";

                } catch (InvalidInputException e1) {
                    publishJobPostingErrorLabel.setText("<html><body width='"+250+"px'>" + additionalError +e1.getMessage() + "</body></html>");
                }

                updateDisplay();
            }
        });

        jobListForPublishJobPosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForPublishJobPosting = cb.getSelectedIndex();
            }
        });

        courseListForPublishJobPosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForPublishJobPosting = cb.getSelectedIndex();
                if(cb.getSelectedIndex() >= 0) {
                    associatedCourseForPublishJobPosting = associatedInstructorForPublishJobPosting.getCourse(cb.getSelectedIndex());
                    updatePublishJobView(associatedInstructorForPublishJobPosting, associatedCourseForPublishJobPosting, false);
                }

            }
        });

        instructorListForPublishJobPosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedInstructorForPublishJobPosting = cb.getSelectedIndex();
                if(cb.getSelectedIndex() >= 0) {
                    associatedInstructorForPublishJobPosting = department.getAllInstructor(cb.getSelectedIndex());
                    updatePublishJobView(associatedInstructorForPublishJobPosting, null, false);
                }
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(publishJobPostingButton, gridBagConstraints);

        return scrollPane;
    }

    private Component RegisterAStudent() {

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.fill =1;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel studentNameLabel = new JLabel("Student name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentNameLabel, gridBagConstraints);

        studentNameField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentNameField, gridBagConstraints);
        studentNameField.setColumns(10);

        JLabel emailLabel = new JLabel("Email");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        contentPane.add(emailLabel, gridBagConstraints);


        JLabel RegisterStudentLabel = new JLabel("Register a Student");
        RegisterStudentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(RegisterStudentLabel, gridBagConstraints);

        emailField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        contentPane.add(emailField, gridBagConstraints);
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
        contentPane.add(undergraduateRadioForRegister, gridBagConstraints);

        graduateRadioForRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        contentPane.add(graduateRadioForRegister, gridBagConstraints);

        JLabel studentYearLabel = new JLabel("Year");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        contentPane.add(studentYearLabel, gridBagConstraints);

        studentYearField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        contentPane.add(studentYearField, gridBagConstraints);
        studentYearField.setColumns(10);

        JLabel jobPreferenceLabel = new JLabel("Job preference");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        contentPane.add(jobPreferenceLabel, gridBagConstraints);

        jobPreferenceField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        contentPane.add(jobPreferenceField, gridBagConstraints);
        jobPreferenceField.setColumns(10);

        JLabel studentIDLabel = new JLabel("Student ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(studentIDLabel, gridBagConstraints);

        studentIDField = new JTextField();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(studentIDField, gridBagConstraints);
        studentIDField.setColumns(10);



        final JLabel registerAStudentErrorLabel = new JLabel("");
        registerAStudentErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        contentPane.add(registerAStudentErrorLabel, gridBagConstraints);

        WebButton registerStudentButton = new WebButton("Register student");
        registerStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String studentName, studentEmail, studentJobPreference, studentID, studentYear, numberOfHours;
                Boolean isGrad = false;

                //proceed with storing the field values since they are all non-empty
                studentName = studentNameField.getText();
                studentEmail = emailField.getText();
                studentJobPreference = jobPreferenceField.getText();
                studentID = studentIDField.getText();
                studentYear = studentYearField.getText();
                numberOfHours = "0";

                //if the user selected a position type, find which one
                if (graduateRadioForRegister.isSelected())
                    isGrad = true;

                try {
                    studentController.createStudent(studentID, studentName, studentEmail, isGrad, studentYear, studentJobPreference, numberOfHours);
                    registerAStudentErrorLabel.setText("");
                } catch (InvalidInputException e1) {
                    registerAStudentErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                }

                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(registerStudentButton, gridBagConstraints);


        return scrollPane;
    }

    private Component ApplyForAJob() {

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel applyForAJobLabel = new JLabel("Apply for a Job");
        applyForAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill =1;
        contentPane.add(applyForAJobLabel, gridBagConstraints);

        JLabel studentIDForApplyingLabel = new JLabel("Student");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentIDForApplyingLabel, gridBagConstraints);

        studentListForStudentApplyJob = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        contentPane.add(studentListForStudentApplyJob, gridBagConstraints);

        JLabel jobIDForApplyingLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(jobIDForApplyingLabel, gridBagConstraints);

        jobListForStudentApplyJob = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(jobListForStudentApplyJob, gridBagConstraints);

        //
        final JLabel skillsRequiredLabel = new JLabel("");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(skillsRequiredLabel, gridBagConstraints);

        skillsRequiredTextAreaForApply = new WebTextArea();
        gridBagConstraints.gridx++;
        skillsRequiredTextAreaForApply.setLineWrap(true);
        skillsRequiredTextAreaForApply.setWrapStyleWord(true);
        skillsRequiredTextAreaForApply.setEditable(false);

        final WebScrollPane areaScrollSkillsRequired = new WebScrollPane(skillsRequiredTextAreaForApply);
        areaScrollSkillsRequired.setPreferredSize(new Dimension(150, 150));
        areaScrollSkillsRequired.setVisible(false);
        contentPane.add(areaScrollSkillsRequired, gridBagConstraints);

        final JLabel experienceRequiredLabel = new JLabel("");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.fill = gridBagConstraints.BOTH;
        contentPane.add(experienceRequiredLabel, gridBagConstraints);

        experienceRequiredFieldForApply = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(experienceRequiredFieldForApply, gridBagConstraints);
        experienceRequiredFieldForApply.setColumns(10);
        experienceRequiredFieldForApply.setEditable(false);
        experienceRequiredFieldForApply.setVisible(false);

        final JLabel jobDescriptionLabel = new JLabel("");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(jobDescriptionLabel, gridBagConstraints);

        jobDescriptionFieldForApply = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(jobDescriptionFieldForApply, gridBagConstraints);
        jobDescriptionFieldForApply.setColumns(10);
        jobDescriptionFieldForApply.setEditable(false);
        jobDescriptionFieldForApply.setVisible(false);

        final JLabel applyForAJobErrorLabel = new JLabel("");
        applyForAJobErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth =2;
        gridBagConstraints.gridy++;
        contentPane.add(applyForAJobErrorLabel, gridBagConstraints);



        WebButton applyForAJobButton = new WebButton("Apply!");
        applyForAJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int studentID, jobID;
                Student student = null;
                Job associatedJob = null;

                //convert the student ID if possible.
                if(studentListForStudentApplyJob.getSelectedIndex() >= 0){
                    student = department.getAllStudent(studentListForStudentApplyJob.getSelectedIndex());
                }



                //search through all the jobs of the department to find the corresponding job object
                for (Job job : department.getAllJobs()) {
                    String tmp = job.getCorrespondingCourse().getName() + "-" + job.getPosType().toString();
                    if (jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply) != null && tmp.contentEquals(jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply))) {
                        associatedJob = job;
                        break;
                    }
                }

                try {
                    studentController.applyToJobPosting(associatedJob, student);
                    applyForAJobErrorLabel.setText(""); //it worked so remove the error

                } catch (InvalidInputException e1) {
                    applyForAJobErrorLabel.setText("<html><body width='200px'>" + e1.getMessage() + "</body></html>"); //this shouldn't happen since the user chooses the job from a spinner so the job must be existent

                }

                updateDisplay();

            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        jobListForStudentApplyJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobForStudentApply = cb.getSelectedIndex();
                if(cb.getSelectedIndex() >= 0){
                    Job associatedJob = null;

                    for (Job job : department.getAllJobs()) {
                        String tmp = job.getCorrespondingCourse().getName() + "-" + job.getPosType().toString();
                        if (jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply) != null && tmp.contentEquals(jobListForStudentApplyJob.getItemAt(selectedJobForStudentApply))) {
                            associatedJob = job;
                            break;
                        }
                    }

                    if(associatedJob != null) {
                        experienceRequiredFieldForApply.setText(associatedJob.getExperienceRequired());
                        jobDescriptionFieldForApply.setText(associatedJob.getJobDescription());
                        skillsRequiredTextAreaForApply.setText(associatedJob.getSkillsRequired());
                        experienceRequiredLabel.setText("Experience Required");
                        skillsRequiredLabel.setText("Skills Required");
                        jobDescriptionLabel.setText("Job Description");
                        areaScrollSkillsRequired.setVisible(true);
                        jobDescriptionFieldForApply.setVisible(true);
                        experienceRequiredFieldForApply.setVisible(true);

                    }

                }else{
                    experienceRequiredLabel.setText( "");
                    skillsRequiredLabel.setText("");
                    jobDescriptionLabel.setText("");
                    areaScrollSkillsRequired.setVisible(false);
                    jobDescriptionFieldForApply.setVisible(false);
                    experienceRequiredFieldForApply.setVisible(false);
                }
            }
        });

        contentPane.add(applyForAJobButton, gridBagConstraints);

        return scrollPane;
    }

    private Component CreateAJob() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createAJobLabel = new JLabel("Create a Job");
        createAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        contentPane.add(createAJobLabel, gridBagConstraints);

        JLabel posTypeLabel = new JLabel("Position");
        createAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contentPane.add(posTypeLabel, gridBagConstraints);

        JPanel radioPanel = new JPanel();
        FlowLayout radioLayout = new FlowLayout(FlowLayout.LEADING);
        radioPanel.setLayout(radioLayout);
        radioPanel.setOpaque(false);

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

        radioPanel.add(TARadio);

        graderRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        radioPanel.add(graderRadio);

        contentPane.add(radioPanel, gridBagConstraints);

        //This is a dummy instructor

        JLabel courseLabel = new JLabel("Course");
        createAJobLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        contentPane.add(courseLabel, gridBagConstraints);

        //spinners
        courseListForCreateAJob = new WebComboBox(new String[0]);
        courseListForCreateAJob.setEditable(false);
        //courseListForCreateAJob.setEditorColumns();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        contentPane.add(courseListForCreateAJob, gridBagConstraints);

        final JLabel publishDeadlineLabel = new JLabel("Publish Deadline");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(publishDeadlineLabel, gridBagConstraints);

        publishDeadlineDatePicker = new WebDateField();
        gridBagConstraints.gridx++;
        contentPane.add(publishDeadlineDatePicker, gridBagConstraints);

        final JLabel createAJobErrorLabel = new JLabel("");
        createAJobErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 2;
        contentPane.add(createAJobErrorLabel, gridBagConstraints);

        final WebButton createNewJobButton = new WebButton("Create New Job");
        createNewJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //This is code the allows the use to test publish job posting and apply to job

                PositionType positionType = null; //for now, this will be the default value (to avoid situation when user select neither of the choices)
                Course course = null;
                if(courseListForCreateAJob.getSelectedIndex()>=0) {
                    course = department.getAllCourse(courseListForCreateAJob.getSelectedIndex());
                }
                if (graderRadio.isSelected())
                    positionType = PositionType.Grader;
                else if(TARadio.isSelected())
                    positionType = PositionType.TA;

                Date dummyPostDeadLine = null;
                if(publishDeadlineDatePicker.getDate() != null) {
                    dummyPostDeadLine = new Date(publishDeadlineDatePicker.getDate().getTime());
                    createAJobErrorLabel.setText("");
                }
                try {
                    departmentController.createJob(positionType, dummyPostDeadLine, course);
                } catch (Exception error) {
                    createAJobErrorLabel.setText("<html><body width='"+250+"px'>"  +error.getMessage() + "</body></html>");

                }
                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 2;
        contentPane.add(createNewJobButton, gridBagConstraints);

        courseListForCreateAJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedCourseForCreateAJob = cb.getSelectedIndex();
            }
        });

        return scrollPane;
    }

    private Component CreateACourse() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createACourseLabel = new JLabel("Create a Course");
        createACourseLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        contentPane.add(createACourseLabel, gridBagConstraints);

        JLabel courseNameLabel = new JLabel("Course name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseNameLabel, gridBagConstraints);

        courseNameField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(courseNameField, gridBagConstraints);
        courseNameField.setColumns(10);

        JLabel courseCodeLabel = new JLabel("Course code");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseCodeLabel, gridBagConstraints);

        courseCodeField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(courseCodeField, gridBagConstraints);
        courseCodeField.setColumns(10);

        JLabel instructorLabel = new JLabel("Instructor");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorLabel,gridBagConstraints);

        instructorListForCreateACourse = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(instructorListForCreateACourse,gridBagConstraints);

        JLabel semesterLabel = new JLabel("Semester");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(semesterLabel, gridBagConstraints);

        semesterJComboBox = new JComboBox();
        gridBagConstraints.gridx++;

        contentPane.add(semesterJComboBox, gridBagConstraints);

        semesterJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedSemesterForCreateACourse = cb.getSelectedIndex();
            }
        });


        JLabel creditsLabel = new JLabel("Credits");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(creditsLabel, gridBagConstraints);

        creditsField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(creditsField, gridBagConstraints);
        creditsField.setColumns(10);



        JLabel numberOfTutorialsLabel = new JLabel("# of tutorials");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfTutorialsLabel, gridBagConstraints);

        numberOfTutorialsField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(numberOfTutorialsField, gridBagConstraints);
        numberOfTutorialsField.setColumns(10);

        JLabel numberOfLabsLabel = new JLabel("# of labs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfLabsLabel, gridBagConstraints);

        numberOfLabsField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(numberOfLabsField, gridBagConstraints);
        numberOfLabsField.setColumns(10);

        JLabel numberStudentEnrolledLabel = new JLabel("# student enrolled");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberStudentEnrolledLabel, gridBagConstraints);

        numberStudentEnrolledField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(numberStudentEnrolledField, gridBagConstraints);
        numberStudentEnrolledField.setColumns(10);

        JLabel hoursLabel = new JLabel("Hours");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(hoursLabel, gridBagConstraints);

        hoursField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(hoursField, gridBagConstraints);
        hoursField.setColumns(10);

        JLabel numberOfTAsNeededLabel = new JLabel("# of TAs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfTAsNeededLabel, gridBagConstraints);

        numberOfTAsNeededField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(numberOfTAsNeededField, gridBagConstraints);
        numberOfTAsNeededField.setColumns(10);

        JLabel numberOfGradersNeededLabel = new JLabel("# of Graders");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfGradersNeededLabel, gridBagConstraints);

        numberOfGradersNeededField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(numberOfGradersNeededField, gridBagConstraints);
        numberOfGradersNeededField.setColumns(10);

        JLabel taHourlyRateLabel = new JLabel("TA hourly rate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(taHourlyRateLabel, gridBagConstraints);

        taHourlyRateField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(taHourlyRateField, gridBagConstraints);
        taHourlyRateField.setColumns(10);

        JLabel graderHourlyRateLabel = new JLabel("Graders hourly rate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(graderHourlyRateLabel, gridBagConstraints);

        graderHourlyRateField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(graderHourlyRateField, gridBagConstraints);
        graderHourlyRateField.setColumns(10);

        JLabel budgetLabel = new JLabel("Budget");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(budgetLabel, gridBagConstraints);

        budgetField = new JTextField();
        gridBagConstraints.gridx++;

        contentPane.add(budgetField, gridBagConstraints);
        budgetField.setColumns(10);


        final JLabel createCourseErrorLabel = new JLabel("");
        createCourseErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createCourseErrorLabel, gridBagConstraints);

        WebButton createACourseButton = new WebButton("Create a Course");
        createACourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String courseName, courseCode, semester = "", numberOfLabs, numberOfTutorials, numberOfStudents, hours, numberOfTAsNeeded, numberOfGradersNeeded, hourlyRateTA, graderHourlyRate, numberOfCredits, budget;

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
                }

                Instructor dummyInstructor = null;
                    //TESTING PURPOSES
                if (selectedInstructorForCreateACourse >= 0) {

                    dummyInstructor = department.getAllInstructor(selectedInstructorForCreateACourse);
                }

                    try {
                        departmentController.createCourse(courseCode, courseName, semester, numberOfCredits, numberOfLabs, numberOfTutorials, hours, numberOfStudents, numberOfTAsNeeded, numberOfGradersNeeded, hourlyRateTA, graderHourlyRate, budget, dummyInstructor);
                        createCourseErrorLabel.setText("");
                    } catch (InvalidInputException e1) {
                        createCourseErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                    }




                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;


        contentPane.add(createACourseButton, gridBagConstraints);

        instructorListForCreateACourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedInstructorForCreateACourse = cb.getSelectedIndex();

            }
        });

        return scrollPane;
    }

    private Component CreateAnInstructor() {

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
        contentPane.add(lblCreateAnInstructor, gridBagConstraints);

        JLabel createAnInstructorNameLabel = new JLabel("Full name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAnInstructorNameLabel, gridBagConstraints);

        createAnInstructorNameField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(createAnInstructorNameField, gridBagConstraints);
        createAnInstructorNameField.setColumns(10);

        JLabel instructorIDLabel = new JLabel("Instructor ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorIDLabel, gridBagConstraints);

        instructorIDField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(instructorIDField, gridBagConstraints);
        instructorIDField.setColumns(10);

        JLabel instructorEmailLabel = new JLabel("Email");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorEmailLabel, gridBagConstraints);

        instructorEmailField = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(instructorEmailField, gridBagConstraints);
        instructorEmailField.setColumns(10);

        final JLabel createInstructorErrorLabel = new JLabel("");
        createInstructorErrorLabel.setForeground(Color.RED);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth=2;
        contentPane.add(createInstructorErrorLabel, gridBagConstraints);

        WebButton createInstructorButton = new WebButton("Create new instructor");
        createInstructorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String instructorName, instructorID, instructorEmail;

                instructorName = createAnInstructorNameField.getText();
                instructorID = instructorIDField.getText();
                instructorEmail = instructorEmailField.getText();

                try {
                    instructorController.createInstructor(instructorName, instructorID, instructorEmail);
                    createInstructorErrorLabel.setText("");

                } catch (InvalidInputException e1) {
                    createInstructorErrorLabel.setText("<html><body width='175px'>" + e1.getMessage() + "</body></html>");
                }

                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createInstructorButton, gridBagConstraints);

        return scrollPane;
    }

    private Component CreateRemoveAllocation() {

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
        contentPane.add(createAllocationLabel, gridBagConstraints);

        createAllocationRadio = new JRadioButton("Create");
        removeAllocationRadio = new JRadioButton("Remove");

        ButtonGroup group3 = new ButtonGroup();
        group3.add(createAllocationRadio);
        group3.add(removeAllocationRadio);

        createAllocationRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationRadio, gridBagConstraints);

        removeAllocationRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        gridBagConstraints.gridx++;
        contentPane.add(removeAllocationRadio, gridBagConstraints);

        JLabel createAllocationStudentLabel = new JLabel("Student");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationStudentLabel, gridBagConstraints);

        createAllocationStudentComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createAllocationStudentComboBox, gridBagConstraints);

        createAllocationStudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForCreateAllocation = cb.getSelectedIndex();
            }
        });

        JLabel createAllocationJobLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationJobLabel, gridBagConstraints);

        createAllocationJobComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createAllocationJobComboBox, gridBagConstraints);

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
        contentPane.add(createAllocationErrorLabel, gridBagConstraints);

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
                    for (Job j : department.getAllJobs()) {
                        String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                        if (createAllocationJobComboBox.getItemAt(selectedJobForCreateAllocation) != null && tmp.contentEquals(createAllocationJobComboBox.getItemAt(selectedJobForCreateAllocation))) {
                            job = j;
                            break;
                        }
                    }

                    //search through all students of the department to find corresponding student object
                    for (Student s : department.getAllStudents()) {
                        String tmp = s.getEmail();
                        if (createAllocationStudentComboBox.getItemAt(selectedStudentForCreateAllocation) != null && tmp.contentEquals(createAllocationStudentComboBox.getItemAt(selectedStudentForCreateAllocation))) {
                            student = s;
                            break;
                        }
                    }
                    try {
                        if (selectedCreateAllocation) {
                            departmentController.createAllocation(job, student);
                        } else {
                            departmentController.removeAllocation(job, student);
                        }
                        createAllocationErrorLabel.setText("");
                    } catch (Exception error) {
                        //TODO
                    }

                } else {
                    createAllocationErrorLabel.setText("Please selected all the fields.");
                }

                updateDisplay();
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createAllocationButton, gridBagConstraints);

        return scrollPane;
    }

    private Component CreateOffer() {

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
        contentPane.add(createOfferLabel, gridBagConstraints);

        JLabel createOfferStudentLabel = new JLabel("Student");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferStudentLabel, gridBagConstraints);

        createOfferStudentComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createOfferStudentComboBox, gridBagConstraints);

        createOfferStudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForCreateOffer = cb.getSelectedIndex();
            }
        });

        JLabel createOfferJobLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferJobLabel, gridBagConstraints);

        createOfferJobComboBox = new JComboBox();
        gridBagConstraints.gridx++;
        contentPane.add(createOfferJobComboBox, gridBagConstraints);

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
        contentPane.add(createOfferErrorLabel, gridBagConstraints);

        WebButton createOfferButton = new WebButton("Create offer");
        createOfferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentString, jobString;
                Student student = null;
                Job job = null;

                studentString = createOfferStudentComboBox.getSelectedItem().toString();
                jobString = createOfferJobComboBox.getSelectedItem().toString();

                //search through all students of the department to find corresponding student object
                for (Student s : department.getAllStudents()) {
                    String tmp = s.getEmail();
                    if (createOfferStudentComboBox.getItemAt(selectedStudentForCreateOffer) != null && tmp.contentEquals(createOfferStudentComboBox.getItemAt(selectedStudentForCreateOffer))) {
                        student = s;
                        break;
                    }
                }

                //search through all the jobs of the department to find the corresponding job object
                for (Job j : department.getAllJobs()) {
                    String tmp = j.getCorrespondingCourse().getName() + "-" + j.getPosType().toString();
                    if (createOfferJobComboBox.getItemAt(selectedJobForCreateOffer) != null && tmp.contentEquals(createOfferJobComboBox.getItemAt(selectedJobForCreateOffer))) {
                        job = j;
                        break;
                    }
                }
                try {
                    departmentController.createJobOffer(job, student);
                } catch (Exception error) {

                }

                updateDisplay();

            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(createOfferButton, gridBagConstraints);
        return scrollPane;
    }

    private Component ViewStudent(){
        final WebPanel contentPane = new WebPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);

        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        final JLabel viewStudentLabel = new JLabel("View Student Info");
        viewStudentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        // publishJobLabel.setBounds(23, 6, 165, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        contentPane.add(viewStudentLabel, gridBagConstraints);

        final JLabel studentLabel = new JLabel("Student");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(studentLabel,gridBagConstraints);

        studentListForViewStudent = new JComboBox<>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(studentListForViewStudent,gridBagConstraints);

        final JLabel studentNameLabel = new JLabel("Student name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(studentNameLabel,gridBagConstraints);
        studentNameLabel.setVisible(false);


        studentNameFieldForViewStudent = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(studentNameFieldForViewStudent, gridBagConstraints);
        studentNameFieldForViewStudent.setEditable(false);
        studentNameFieldForViewStudent.setVisible(false);

        final JLabel emailLabel = new JLabel("Email");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(emailLabel, gridBagConstraints);
        emailLabel.setVisible(false);



        emailFieldForViewStudent = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(emailFieldForViewStudent,gridBagConstraints);
        emailFieldForViewStudent.setEditable(false);
        emailFieldForViewStudent.setVisible(false);



        final JLabel studentIDLabel = new JLabel("Student ID");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(studentIDLabel,gridBagConstraints);
        studentIDLabel.setVisible(false);


        studentIDFieldForViewStudent = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(studentIDFieldForViewStudent, gridBagConstraints);
        studentIDFieldForViewStudent.setEditable(false);
        studentIDFieldForViewStudent.setVisible(false);

        undergraduateRadioForRegisterForViewStudent = new JRadioButton("Undergraduate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(undergraduateRadioForRegisterForViewStudent,gridBagConstraints);
        undergraduateRadioForRegisterForViewStudent.setSelected(false);
        undergraduateRadioForRegisterForViewStudent.setVisible(false);
        undergraduateRadioForRegisterForViewStudent.setEnabled(false);

        graduateRadioForRegisterForViewStudent = new JRadioButton("Graduate");
        gridBagConstraints.gridx++;
        contentPane.add(graduateRadioForRegisterForViewStudent,gridBagConstraints);
        graduateRadioForRegisterForViewStudent.setSelected(false);
        graduateRadioForRegisterForViewStudent.setVisible(false);
        graduateRadioForRegisterForViewStudent.setEnabled(false);

        final JLabel studentYearLabel = new JLabel("Year");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(studentYearLabel, gridBagConstraints);
        studentYearLabel.setVisible(false);

        studentYearFieldForViewStudent = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(studentYearFieldForViewStudent, gridBagConstraints);
        studentYearFieldForViewStudent.setEditable(false);
        studentYearFieldForViewStudent.setVisible(false);

        final JLabel jobPreferenceLabel = new JLabel("Job preference");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(jobPreferenceLabel, gridBagConstraints);
        jobPreferenceLabel.setVisible(false);

        jobPreferenceTextAreaForViewStudent = new WebTextArea();
        gridBagConstraints.gridx++;
        jobPreferenceTextAreaForViewStudent.setLineWrap(true);
        jobPreferenceTextAreaForViewStudent.setWrapStyleWord(true);
        jobPreferenceTextAreaForViewStudent.setEditable(false);


        final WebScrollPane areaScrollJobPreferenceRequired = new WebScrollPane(jobPreferenceTextAreaForViewStudent);
        areaScrollJobPreferenceRequired.setPreferredSize(new Dimension(250, 75));
        contentPane.add(areaScrollJobPreferenceRequired, gridBagConstraints);
        areaScrollJobPreferenceRequired.setVisible(false);

        final JLabel previousJobsLabel = new JLabel("Previous Jobs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(previousJobsLabel, gridBagConstraints);
        previousJobsLabel.setVisible(false);

        WebTable tablepreviousJobs = new WebTable();
        tablepreviousJobs.setModel(previousJobsTableModel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy+=3;
        gridBagConstraints.gridwidth = 3;
        tablepreviousJobs.setEditable(false);

        final WebScrollPane scrollPreviousJobsTable = new WebScrollPane(tablepreviousJobs);
        tablepreviousJobs.setFillsViewportHeight(true);

        scrollPreviousJobsTable.setMinimumWidth(600);
        contentPane.add(scrollPreviousJobsTable,gridBagConstraints);
        scrollPreviousJobsTable.setVisible(false);


        final JLabel currentJobsLabel = new JLabel("Current Jobs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(currentJobsLabel, gridBagConstraints);
        currentJobsLabel.setVisible(false);

        WebTable tableCurrentJobs = new WebTable();
        tableCurrentJobs.setModel(currentJobTableModel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy+=3;
        gridBagConstraints.gridwidth = 3;
        tableCurrentJobs.setEditable(false);

        final WebScrollPane scrollCurrentJobTable = new WebScrollPane(tableCurrentJobs);
        tableCurrentJobs.setFillsViewportHeight(true);

        scrollCurrentJobTable.setMinimumWidth(600);
        contentPane.add(scrollCurrentJobTable,gridBagConstraints);
        scrollCurrentJobTable.setVisible(false);



        final JLabel appliedToJobsLabel = new JLabel("Applied to Jobs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(appliedToJobsLabel, gridBagConstraints);
        appliedToJobsLabel.setVisible(false);

        WebTable tableAppliedToJobs = new WebTable();
        tableAppliedToJobs.setModel(appliedToJobsTableModel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy+=3;
        gridBagConstraints.gridwidth = 3;
        tableAppliedToJobs.setEditable(false);

        final WebScrollPane scrollAppliedToJobsTable = new WebScrollPane(tableAppliedToJobs);
        tableAppliedToJobs.setFillsViewportHeight(true);

        scrollAppliedToJobsTable.setMinimumWidth(600);
        contentPane.add(scrollAppliedToJobsTable,gridBagConstraints);
        scrollAppliedToJobsTable.setVisible(false);



        studentListForViewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForViewStudent = cb.getSelectedIndex();

                if(cb.getSelectedIndex() >= 0){
                    studentNameLabel.setVisible(true);
                    studentNameFieldForViewStudent.setVisible(true);

                    emailLabel.setVisible(true);
                    emailFieldForViewStudent.setVisible(true);

                    studentIDLabel.setVisible(true);
                    studentIDFieldForViewStudent.setVisible(true);

                    studentYearLabel.setVisible(true);
                    studentYearFieldForViewStudent.setVisible(true);

                    jobPreferenceLabel.setVisible(true);
                    areaScrollJobPreferenceRequired.setVisible(true);

                    currentJobsLabel.setVisible(true);
                    scrollCurrentJobTable.setVisible(true);

                    appliedToJobsLabel.setVisible(true);
                    scrollAppliedToJobsTable.setVisible(true);

                    previousJobsLabel.setVisible(true);
                    scrollPreviousJobsTable.setVisible(true);



                    String tmpIDString = cb.getSelectedItem().toString().split(":")[0];
                    Student tmpStudent = null;
                    for(Student s: department.getAllStudents()){
                        if(s.getStudentID() == Integer.parseInt(tmpIDString)){
                            tmpStudent = s;
                            break;
                        }
                    }

                    if(tmpStudent != null){
                        studentNameFieldForViewStudent.setText(tmpStudent.getName());
                        emailFieldForViewStudent.setText(tmpStudent.getEmail());
                        studentIDFieldForViewStudent.setText(tmpStudent.getStudentID()+"");
                        studentNameFieldForViewStudent.setText(tmpStudent.getName());
                        studentYearFieldForViewStudent.setText("U"+tmpStudent.getYear());
                        jobPreferenceTextAreaForViewStudent.setText(tmpStudent.getJobPreference());
                        currentJobTableModel.setRowCount(0);
                        appliedToJobsTableModel.setRowCount(0);
                        previousJobsTableModel.setRowCount(0);
                        Object[] columns = {"Course ID", "Course Name", "Position","Salary", "Hours"};
                        currentJobTableModel.setColumnIdentifiers(columns);
                        appliedToJobsTableModel.setColumnIdentifiers(columns);
                        previousJobsTableModel.setColumnIdentifiers(columns);

                        for(Job j: tmpStudent.getCurrentJobs()){
                            String courseID = j.getCorrespondingCourse().getCode();
                            String courseName = j.getCorrespondingCourse().getName();
                            String position = j.getPosType().toString();
                            String salary = "";
                            if(position == "TA") {
                                salary = j.getCorrespondingCourse().getTaHourlyRate()+"";
                            }else{
                                salary = j.getCorrespondingCourse().getGraderHourlyRate()+"";
                            }
                            String hours = j.getCorrespondingCourse().getNumberOfHours()+"";

                            Object[] tmpData = {courseID, courseName, position, salary, hours};
                            currentJobTableModel.addRow(tmpData);
                        }

                        for(Job j: tmpStudent.getJobsAppliedTo()){
                            String courseID = j.getCorrespondingCourse().getCode();
                            String courseName = j.getCorrespondingCourse().getName();
                            String position = j.getPosType().toString();
                            String salary = "";
                            if(position == "TA") {
                                salary = j.getCorrespondingCourse().getTaHourlyRate()+"";
                            }else{
                                salary = j.getCorrespondingCourse().getGraderHourlyRate()+"";
                            }
                            String hours = j.getCorrespondingCourse().getNumberOfHours()+"";

                            Object[] tmpData = {courseID, courseName, position, salary, hours};
                            appliedToJobsTableModel.addRow(tmpData);
                        }

                        for(Job j: tmpStudent.getPreviousJobExperiences()){
                            String courseID = j.getCorrespondingCourse().getCode();
                            String courseName = j.getCorrespondingCourse().getName();
                            String position = j.getPosType().toString();
                            String salary = "";
                            if(position == "TA") {
                                salary = j.getCorrespondingCourse().getTaHourlyRate()+"";
                            }else{
                                salary = j.getCorrespondingCourse().getGraderHourlyRate()+"";
                            }
                            String hours = j.getCorrespondingCourse().getNumberOfHours()+"";

                            Object[] tmpData = {courseID, courseName, position, salary, hours};
                            previousJobsTableModel.addRow(tmpData);
                        }




                        if(tmpStudent.isIsGrad()) {
                            undergraduateRadioForRegisterForViewStudent.setSelected(false);
                            undergraduateRadioForRegisterForViewStudent.setVisible(true);
                            graduateRadioForRegisterForViewStudent.setSelected(true);
                            graduateRadioForRegisterForViewStudent.setVisible(true);
                        }else{
                            undergraduateRadioForRegisterForViewStudent.setSelected(true);
                            undergraduateRadioForRegisterForViewStudent.setVisible(true);
                            graduateRadioForRegisterForViewStudent.setSelected(false);
                            graduateRadioForRegisterForViewStudent.setVisible(true);
                        }

                    }


                }else{
                    studentNameLabel.setVisible(false);
                    studentNameFieldForViewStudent.setVisible(false);

                    emailLabel.setVisible(false);
                    emailFieldForViewStudent.setVisible(false);

                    studentIDLabel.setVisible(false);
                    studentIDFieldForViewStudent.setVisible(false);

                    undergraduateRadioForRegisterForViewStudent.setSelected(false);
                    undergraduateRadioForRegisterForViewStudent.setVisible(false);
                    graduateRadioForRegisterForViewStudent.setSelected(false);
                    graduateRadioForRegisterForViewStudent.setVisible(false);

                    studentYearLabel.setVisible(false);
                    studentYearFieldForViewStudent.setVisible(false);

                    jobPreferenceLabel.setVisible(false);
                    areaScrollJobPreferenceRequired.setVisible(false);

                    currentJobsLabel.setVisible(false);
                    scrollCurrentJobTable.setVisible(false);

                    appliedToJobsLabel.setVisible(false);
                    scrollAppliedToJobsTable.setVisible(false);

                    previousJobsLabel.setVisible(false);
                    scrollPreviousJobsTable.setVisible(false);

                    currentJobTableModel.setRowCount(0);
                    appliedToJobsTableModel.setRowCount(0);
                    previousJobsTableModel.setRowCount(0);

                }
            }
        });

        return scrollPane;
    }

    private Component ViewCourse(){
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);


        JLabel createACourseLabel = new JLabel("View Course Info");
        createACourseLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        contentPane.add(createACourseLabel,gridBagConstraints);


        final JLabel courseLabel = new JLabel("Course");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseLabel,gridBagConstraints);


        courseListForViewCourse = new JComboBox<>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(courseListForViewCourse,gridBagConstraints);


        final JLabel courseNameLabel = new JLabel("Course name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseNameLabel, gridBagConstraints);
        courseNameLabel.setVisible(false);

        courseNameFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(courseNameFieldForViewCourse, gridBagConstraints);
        courseNameFieldForViewCourse.setVisible(false);
        courseNameFieldForViewCourse.setEditable(false);


        final JLabel courseCodeLabel = new JLabel("Course code");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseCodeLabel, gridBagConstraints);
        courseCodeLabel.setVisible(false);


        courseCodeFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(courseCodeFieldForViewCourse, gridBagConstraints);
        courseCodeFieldForViewCourse.setVisible(false);
        courseCodeFieldForViewCourse.setEditable(false);


        final JLabel instructorLabel = new JLabel("Instructor");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(instructorLabel,gridBagConstraints);
        instructorLabel.setVisible(false);

        instructorListForViewCourse = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(instructorListForViewCourse,gridBagConstraints);
        instructorListForViewCourse.setVisible(false);
        instructorListForViewCourse.setEditable(false);


        final JLabel semesterLabel = new JLabel("Semester");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(semesterLabel, gridBagConstraints);
        semesterLabel.setVisible(false);

        JPanel radioPanel = new JPanel();
        FlowLayout radioLayout = new FlowLayout(FlowLayout.LEADING);
        radioPanel.setLayout(radioLayout);
        radioPanel.setOpaque(false);

        fallRadio = new JRadioButton("Fall");
        winterRadio = new JRadioButton("Winter");

        //Created a group for the radio
        ButtonGroup group = new ButtonGroup();
        group.add(fallRadio);
        group.add(winterRadio);

        fallRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        fallRadio.setSelected(false);
        fallRadio.setVisible(false);
        fallRadio.setEnabled(false);

        radioPanel.add(fallRadio);

        winterRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


        winterRadio.setSelected(false);
        winterRadio.setVisible(false);
        winterRadio.setEnabled(false);
        radioPanel.add(winterRadio);

        gridBagConstraints.gridx++;
        contentPane.add(radioPanel, gridBagConstraints);




        final JLabel creditsLabel = new JLabel("Credits");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(creditsLabel, gridBagConstraints);
        creditsLabel.setVisible(false);

        creditsFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(creditsFieldForViewCourse, gridBagConstraints);
        creditsFieldForViewCourse.setVisible(false);
        creditsFieldForViewCourse.setEditable(false);




        final JLabel numberOfTutorialsLabel = new JLabel("# of tutorials");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfTutorialsLabel, gridBagConstraints);
        numberOfTutorialsLabel.setVisible(false);

        numberOfTutorialsFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(numberOfTutorialsFieldForViewCourse, gridBagConstraints);
        numberOfTutorialsFieldForViewCourse.setVisible(false);
        numberOfTutorialsFieldForViewCourse.setEditable(false);


        final JLabel numberOfLabsLabel = new JLabel("# of labs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfLabsLabel, gridBagConstraints);
        numberOfLabsLabel.setVisible(false);

        numberOfLabsFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(numberOfLabsFieldForViewCourse, gridBagConstraints);
        numberOfLabsFieldForViewCourse.setVisible(false);
        numberOfLabsFieldForViewCourse.setEditable(false);

        final JLabel numberStudentEnrolledLabel = new JLabel("# student enrolled");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberStudentEnrolledLabel, gridBagConstraints);
        numberStudentEnrolledLabel.setVisible(false);

        numberStudentEnrolledFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(numberStudentEnrolledFieldForViewCourse, gridBagConstraints);
        numberStudentEnrolledFieldForViewCourse.setVisible(false);
        numberStudentEnrolledFieldForViewCourse.setEditable(false);

        final JLabel hoursLabel = new JLabel("Hours");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(hoursLabel, gridBagConstraints);
        hoursLabel.setVisible(false);

        hoursFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(hoursFieldForViewCourse, gridBagConstraints);
        hoursFieldForViewCourse.setVisible(false);
        hoursFieldForViewCourse.setEditable(false);

        final JLabel numberOfTAsNeededLabel = new JLabel("# of TAs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfTAsNeededLabel, gridBagConstraints);
        numberOfTAsNeededLabel.setVisible(false);

        numberOfTAsNeededFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(numberOfTAsNeededFieldForViewCourse, gridBagConstraints);
        numberOfTAsNeededFieldForViewCourse.setVisible(false);
        numberOfTAsNeededFieldForViewCourse.setEditable(false);

        final JLabel numberOfGradersNeededLabel = new JLabel("# of Graders");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(numberOfGradersNeededLabel, gridBagConstraints);
        numberOfGradersNeededLabel.setVisible(false);

        numberOfGradersNeededFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(numberOfGradersNeededFieldForViewCourse, gridBagConstraints);
        numberOfGradersNeededFieldForViewCourse.setVisible(false);
        numberOfGradersNeededFieldForViewCourse.setEditable(false);

        final JLabel taHourlyRateLabel = new JLabel("TA hourly rate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(taHourlyRateLabel, gridBagConstraints);
        taHourlyRateLabel.setVisible(false);

        taHourlyRateFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(taHourlyRateFieldForViewCourse, gridBagConstraints);
        taHourlyRateFieldForViewCourse.setVisible(false);
        taHourlyRateFieldForViewCourse.setEditable(false);

        final JLabel graderHourlyRateLabel = new JLabel("Graders hourly rate");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(graderHourlyRateLabel, gridBagConstraints);
        graderHourlyRateLabel.setVisible(false);

        graderHourlyRateFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(graderHourlyRateFieldForViewCourse, gridBagConstraints);
        graderHourlyRateFieldForViewCourse.setVisible(false);
        graderHourlyRateFieldForViewCourse.setEditable(false);

        final JLabel budgetLabel = new JLabel("Budget");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(budgetLabel, gridBagConstraints);
        budgetLabel.setVisible(false);

        budgetFieldForViewCourse = new JTextField();
        gridBagConstraints.gridx++;
        contentPane.add(budgetFieldForViewCourse, gridBagConstraints);
        budgetFieldForViewCourse.setVisible(false);
        budgetFieldForViewCourse.setEditable(false);


        instructorListForViewCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedInstructorForViewCourse = cb.getSelectedIndex();

            }
        });

        courseListForViewCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedStudentForViewStudent = cb.getSelectedIndex();

                if(cb.getSelectedIndex() >= 0){
                    courseNameLabel.setVisible(true);
                    courseNameFieldForViewCourse.setVisible(true);

                    courseCodeLabel.setVisible(true);
                    courseCodeFieldForViewCourse.setVisible(true);

                    instructorLabel.setVisible(true);
                    instructorListForViewCourse.setVisible(true);

                    semesterLabel.setVisible(true);
                    winterRadio.setVisible(true);
                    fallRadio.setVisible(true);

                    creditsLabel.setVisible(true);
                    creditsFieldForViewCourse.setVisible(true);

                    numberOfTutorialsLabel.setVisible(true);
                    numberOfTutorialsFieldForViewCourse.setVisible(true);

                    numberOfLabsLabel.setVisible(true);
                    numberOfLabsFieldForViewCourse.setVisible(true);

                    numberStudentEnrolledLabel.setVisible(true);
                    numberStudentEnrolledFieldForViewCourse.setVisible(true);

                    hoursLabel.setVisible(true);
                    hoursFieldForViewCourse.setVisible(true);

                    numberOfTAsNeededLabel.setVisible(true);
                    numberOfTAsNeededFieldForViewCourse.setVisible(true);

                    numberOfGradersNeededLabel.setVisible(true);
                    numberOfGradersNeededFieldForViewCourse.setVisible(true);

                    taHourlyRateLabel.setVisible(true);
                    taHourlyRateFieldForViewCourse.setVisible(true);

                    graderHourlyRateLabel.setVisible(true);
                    graderHourlyRateFieldForViewCourse.setVisible(true);

                    budgetLabel.setVisible(true);
                    budgetFieldForViewCourse.setVisible(true);


                    String tmpCourseID = cb.getSelectedItem().toString().split(":")[0];
                    Course tmpCourse = null;
                    for(Course c: department.getAllCourses()){
                        if(c.getCode().contentEquals(tmpCourseID)){
                            tmpCourse = c;
                            break;
                        }
                    }

                    if(tmpCourse != null){

                        courseNameFieldForViewCourse.setText(tmpCourse.getName());
                        courseCodeFieldForViewCourse.setText(tmpCourse.getCode());

                        instructorListForViewCourse.removeAllItems();
                        for (Instructor i : tmpCourse.getInstructors()) {
                            String tmp = i.getInstructorID() + "(" + i.getName()+ ")";
                            instructorListForViewCourse.addItem(tmp);
                        }
                        selectedInstructorForViewCourse = 0;
                        instructorListForViewCourse.setSelectedIndex(selectedInstructorForViewCourse);



                        creditsFieldForViewCourse.setText(tmpCourse.getNumberOfCredits()+"");
                        numberOfTutorialsFieldForViewCourse.setText(tmpCourse.getNumberOfTutorials()+"");
                        numberOfLabsFieldForViewCourse.setText(tmpCourse.getNumberOfLabs()+"");
                        numberStudentEnrolledFieldForViewCourse.setText(tmpCourse.getStudentsEnrolled()+"");
                        hoursFieldForViewCourse.setText(tmpCourse.getNumberOfHours()+"");
                        numberOfTAsNeededFieldForViewCourse.setText(tmpCourse.getTasNeeded()+"");
                        numberOfGradersNeededFieldForViewCourse.setText(tmpCourse.getGradersNeeded()+"");
                        taHourlyRateFieldForViewCourse.setText("$"+tmpCourse.getTaHourlyRate());
                        graderHourlyRateFieldForViewCourse.setText("$" + tmpCourse.getGraderHourlyRate());
                        budgetFieldForViewCourse.setText("$"+ tmpCourse.getBudget());


                        if(tmpCourse.getSemester().contains("Fall")) {
                            winterRadio.setSelected(false);
                            fallRadio.setSelected(true);
                        }else{
                            winterRadio.setSelected(true);
                            fallRadio.setSelected(false);
                        }

                    }


                }else{
                    courseNameLabel.setVisible(false);
                    courseNameFieldForViewCourse.setVisible(false);

                    courseCodeLabel.setVisible(false);
                    courseCodeFieldForViewCourse.setVisible(false);

                    instructorLabel.setVisible(false);
                    instructorListForViewCourse.setVisible(false);

                    semesterLabel.setVisible(false);
                    winterRadio.setSelected(false);
                    winterRadio.setVisible(false);
                    fallRadio.setSelected(false);
                    fallRadio.setVisible(false);

                    creditsLabel.setVisible(false);
                    creditsFieldForViewCourse.setVisible(false);

                    numberOfTutorialsLabel.setVisible(false);
                    numberOfTutorialsFieldForViewCourse.setVisible(false);

                    numberOfLabsLabel.setVisible(false);
                    numberOfLabsFieldForViewCourse.setVisible(false);

                    numberStudentEnrolledLabel.setVisible(false);
                    numberStudentEnrolledFieldForViewCourse.setVisible(false);

                    hoursLabel.setVisible(false);
                    hoursFieldForViewCourse.setVisible(false);

                    numberOfTAsNeededLabel.setVisible(false);
                    numberOfTAsNeededFieldForViewCourse.setVisible(false);

                    numberOfGradersNeededLabel.setVisible(false);
                    numberOfGradersNeededFieldForViewCourse.setVisible(false);

                    taHourlyRateLabel.setVisible(false);
                    taHourlyRateFieldForViewCourse.setVisible(false);

                    graderHourlyRateLabel.setVisible(false);
                    graderHourlyRateFieldForViewCourse.setVisible(false);

                    budgetLabel.setVisible(false);
                    budgetFieldForViewCourse.setVisible(false);






                }
            }
        });

        return scrollPane;
    }

    private Component ViewJob(){
        final WebPanel contentPane = new WebPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        WebScrollPane scrollPane = new WebScrollPane(contentPane);

        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        final JLabel viewStudentLabel = new JLabel("View Job Info");
        viewStudentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        contentPane.add(viewStudentLabel, gridBagConstraints);

        JLabel courseLabel = new JLabel("Course");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(courseLabel, gridBagConstraints);

        courseListForViewJob = new JComboBox<String>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(courseListForViewJob, gridBagConstraints);

        final JLabel jobLabel = new JLabel("Job");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(jobLabel,gridBagConstraints);

        jobListForViewJob = new JComboBox<>(new String[0]);
        gridBagConstraints.gridx++;
        contentPane.add(jobListForViewJob,gridBagConstraints);

        final JLabel skillsRequiredLabel = new JLabel("Skills Required");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(skillsRequiredLabel,gridBagConstraints);
        skillsRequiredLabel.setVisible(false);


        skillsRequiredTextAreaForViewJob = new WebTextArea();
        gridBagConstraints.gridx++;
        skillsRequiredTextAreaForViewJob.setEditable(false);
        skillsRequiredTextAreaForViewJob.setLineWrap(true);
        skillsRequiredTextAreaForViewJob.setWrapStyleWord(true);

        final WebScrollPane areaScrollSkillsRequired = new WebScrollPane(skillsRequiredTextAreaForViewJob);
        contentPane.add(areaScrollSkillsRequired, gridBagConstraints);
        areaScrollSkillsRequired.setVisible(false);
        areaScrollSkillsRequired.setPreferredSize(new Dimension(250, 75));



        final JLabel experienceRequiredLabel = new JLabel("Exp. Required");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(experienceRequiredLabel, gridBagConstraints);
        experienceRequiredLabel.setVisible(false);



        experienceRequiredFieldForViewJob = new WebTextArea();
        gridBagConstraints.gridx++;
        experienceRequiredFieldForViewJob.setEditable(false);
        experienceRequiredFieldForViewJob.setLineWrap(true);
        experienceRequiredFieldForViewJob.setWrapStyleWord(true);

        final WebScrollPane areaScrollExperiencesRequired = new WebScrollPane(experienceRequiredFieldForViewJob);
        areaScrollExperiencesRequired.setPreferredSize(new Dimension(250, 75));
        contentPane.add(areaScrollExperiencesRequired, gridBagConstraints);
        areaScrollExperiencesRequired.setVisible(false);




        final JLabel jobDescriptionLabel = new JLabel("Job Description");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(jobDescriptionLabel,gridBagConstraints);
        jobDescriptionLabel.setVisible(false);


        jobDescriptionFieldForViewJob = new WebTextArea();
        gridBagConstraints.gridx++;
        jobDescriptionFieldForViewJob.setEditable(false);
        jobDescriptionFieldForViewJob.setLineWrap(true);
        jobDescriptionFieldForViewJob.setWrapStyleWord(true);

        final WebScrollPane areaScrollJobDescription = new WebScrollPane(jobDescriptionFieldForViewJob);
        areaScrollJobDescription.setPreferredSize(new Dimension(250, 75));
        contentPane.add(areaScrollJobDescription, gridBagConstraints);
        areaScrollJobDescription.setVisible(false);




        final JLabel employeeLabel = new JLabel("Employee");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        contentPane.add(employeeLabel, gridBagConstraints);
        employeeLabel.setVisible(false);

        WebTable tableCurrentEmployees = new WebTable();
        tableCurrentEmployees.setModel(employeeTableModel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy+=3;
        gridBagConstraints.gridwidth = 2;
        tableCurrentEmployees.setEditable(false);

        final WebScrollPane scrollCurrentEmployeesTable = new WebScrollPane(tableCurrentEmployees);
        tableCurrentEmployees.setFillsViewportHeight(true);

        scrollCurrentEmployeesTable.setMinimumWidth(600);
        contentPane.add(scrollCurrentEmployeesTable,gridBagConstraints);
        scrollCurrentEmployeesTable.setVisible(false);


        final JLabel allocatedLabel = new JLabel("Allocated Applicants");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 1;
        contentPane.add(allocatedLabel, gridBagConstraints);
        allocatedLabel.setVisible(false);

        WebTable tableAllocatedApplicant = new WebTable();
        tableAllocatedApplicant.setModel(allocatedTableModel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy+=3;
        gridBagConstraints.gridwidth = 2;
        tableAllocatedApplicant.setEditable(false);

        final WebScrollPane scrollAllocatedApplicantTable = new WebScrollPane(tableAllocatedApplicant);
        tableAllocatedApplicant.setFillsViewportHeight(true);

        scrollAllocatedApplicantTable.setMinimumWidth(600);
        contentPane.add(scrollAllocatedApplicantTable,gridBagConstraints);
        scrollAllocatedApplicantTable.setVisible(false);



        final JLabel applicantLabel = new JLabel("Applicants");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 1;
        contentPane.add(applicantLabel, gridBagConstraints);
        applicantLabel.setVisible(false);

        WebTable tableApplicants = new WebTable();
        tableApplicants.setModel(applicantTableModel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy+=3;
        gridBagConstraints.gridwidth = 2;
        tableApplicants.setEditable(false);

        final WebScrollPane scrollApplicantTable = new WebScrollPane(tableApplicants);
        tableApplicants.setFillsViewportHeight(true);

        scrollApplicantTable.setMinimumWidth(600);
        contentPane.add(scrollApplicantTable,gridBagConstraints);
        scrollApplicantTable.setVisible(false);

        courseListForViewJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedCourseListForViewJob = cb.getSelectedIndex();
                if(cb.getSelectedIndex() >= 0) {

                    Course tmpCourse = department.getAllCourse(cb.getSelectedIndex());

                    jobListForViewJob.removeAllItems();
                    for(Job j: tmpCourse.getJobs()){
                        String tmp = j.getPosType().toString();
                        jobListForViewJob.addItem(tmp);
                    }
                    selectedJobListForViewJob = -1;
                    jobListForViewJob.setSelectedIndex(selectedJobListForViewJob);


                }else{

                }

            }
        });

        jobListForViewJob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                selectedJobListForViewJob = cb.getSelectedIndex();

                if (cb.getSelectedIndex() >= 0) {
                    skillsRequiredLabel.setVisible(true);
                    areaScrollSkillsRequired.setVisible(true);

                    experienceRequiredLabel.setVisible(true);
                    areaScrollExperiencesRequired.setVisible(true);

                    jobDescriptionLabel.setVisible(true);
                    areaScrollJobDescription.setVisible(true);

                    employeeLabel.setVisible(true);
                    scrollCurrentEmployeesTable.setVisible(true);

                    allocatedLabel.setVisible(true);
                    scrollAllocatedApplicantTable.setVisible(true);

                    applicantLabel.setVisible(true);
                    scrollApplicantTable.setVisible(true);



                    String tmpPos = cb.getSelectedItem().toString();
                    Job tmpJob = null;
                    for (Job j : department.getAllCourse(selectedCourseListForViewJob).getJobs()) {

                        if (j.getPosType().toString().contains(tmpPos)) {
                            tmpJob = j;
                            break;
                        }
                    }

                    if (tmpJob != null) {
                        skillsRequiredTextAreaForViewJob.setText(tmpJob.getSkillsRequired());
                        experienceRequiredFieldForViewJob.setText(tmpJob.getExperienceRequired());
                        jobDescriptionFieldForViewJob.setText(tmpJob.getJobDescription() + "");

                        employeeTableModel.setRowCount(0);
                        allocatedTableModel.setRowCount(0);
                        applicantTableModel.setRowCount(0);
                        Object[] columns = {"Student ID", "Student Name", "Email", "Education"};
                        employeeTableModel.setColumnIdentifiers(columns);
                        allocatedTableModel.setColumnIdentifiers(columns);
                        applicantTableModel.setColumnIdentifiers(columns);

                        for (Student s : tmpJob.getEmployee()) {
                            String studentID = s.getStudentID()+"";
                            String studentName = s.getName();
                            String studentEmail = s.getEmail();
                            String education= "";
                            boolean isGrad = s.getIsGrad();
                            if (isGrad) {
                                education = "Graduate";
                            } else {
                                education = "Undergraduate";
                            }

                            Object[] tmpData = {studentID,studentName,studentEmail,education};
                            employeeTableModel.addRow(tmpData);
                        }

                        for (Student s : tmpJob.getAllocatedStudent()) {
                            String studentID = s.getStudentID()+"";
                            String studentName = s.getName();
                            String studentEmail = s.getEmail();
                            String education= "";
                            boolean isGrad = s.getIsGrad();
                            if (isGrad) {
                                education = "Graduate";
                            } else {
                                education = "Undergraduate";
                            }

                            Object[] tmpData = {studentID,studentName,studentEmail,education};
                            allocatedTableModel.addRow(tmpData);
                        }

                        for (Student s : tmpJob.getApplicant()) {
                            String studentID = s.getStudentID()+"";
                            String studentName = s.getName();
                            String studentEmail = s.getEmail();
                            String education= "";
                            boolean isGrad = s.getIsGrad();
                            if (isGrad) {
                                education = "Graduate";
                            } else {
                                education = "Undergraduate";
                            }

                            Object[] tmpData = {studentID,studentName,studentEmail,education};
                            applicantTableModel.addRow(tmpData);
                        }


                    }


                } else {
                    skillsRequiredLabel.setVisible(false);
                    areaScrollSkillsRequired.setVisible(false);

                    experienceRequiredLabel.setVisible(false);
                    areaScrollExperiencesRequired.setVisible(false);

                    jobDescriptionLabel.setVisible(false);
                    areaScrollJobDescription.setVisible(false);

                    employeeLabel.setVisible(false);
                    scrollCurrentEmployeesTable.setVisible(false);

                    allocatedLabel.setVisible(false);
                    scrollAllocatedApplicantTable.setVisible(false);

                    applicantLabel.setVisible(false);
                    scrollApplicantTable.setVisible(false);


                    currentJobTableModel.setRowCount(0);
                    appliedToJobsTableModel.setRowCount(0);
                    previousJobsTableModel.setRowCount(0);

                }



            }
        });





        return scrollPane;

    }
}
