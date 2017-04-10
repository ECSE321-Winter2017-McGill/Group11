package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.*;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by trp41 on 2/23/2017.
 */
public class TestInstructorController {

    private Department department;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
    }

    @Before
    public void setUp() throws Exception {
        department = new Department();
    }

    @After
    public void tearDown() throws Exception {
        department.delete();
    }

    @Test
    public void testCreateInstructor() {

        String error = "";

        assertEquals(0,department.numberOfAllInstructors());

        InstructorController instructorController = new InstructorController(department);

        String instructorName = "bobby john";
        String instructorID = "123456789";
        String instructorEmail = "bobbyjohn@mail.mcgill.ca";
        String instructorEmail2 = "bobbyjohn@mcgill.ca";

        String invalidInstructorID = "1";
        String invalidInstructorID2 = "3333333333"; // > 9 digits
        String invalidInstructorEmail = "bobby_john@gmail.com";
        String invalidInstructorEmail2 = "bobby$john33";

        try {
            instructorController.createInstructor(instructorName,instructorID,instructorEmail);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals(1,department.numberOfAllInstructors());

        try {
            instructorController.createInstructor(null,null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a valid 9 digit ID number!<br> Instructor name cannot be empty!<br> Instructor email cannot be empty!<br>", error);
        assertEquals(1,department.numberOfAllInstructors());

        try {
            instructorController.createInstructor("","","");
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a valid 9 digit ID number!<br> Instructor name cannot be empty!<br> Instructor email cannot be empty!<br>", error);
        assertEquals(1,department.numberOfAllInstructors());

        try {
            instructorController.createInstructor(instructorName,invalidInstructorID, instructorEmail2);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a valid 9 digit ID number!<br>", error);
        assertEquals(1,department.numberOfAllInstructors());

        try {
            instructorController.createInstructor(instructorName, invalidInstructorID2, invalidInstructorEmail);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a valid 9 digit ID number!<br>", error);
        assertEquals(1,department.numberOfAllInstructors());

        try {
            instructorController.createInstructor(instructorName,instructorID,invalidInstructorEmail2);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Instructor ID already registered!<br> Please input a valid email address!<br>", error);
        assertEquals(1,department.numberOfAllInstructors());

    }

    @Test
    public void testCreateJobPosting() {

        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        String error = "";

        InstructorController instructorController = new InstructorController(department);

        //Create Instructor
        String instructorName = "James";
        int instructorID = 12345;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructor = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructor);
        assertEquals(1, department.getAllInstructors().size());

        //Create Course

        String courseCode = "ECSE321";
        String courseName = "Software Engineering";
        String courseSemester = "W2017";
        int courseNumOfCredits = 3;
        int courseNumOfLabs = 0;
        int courseNumOfTutorials = 2;
        int courseNumOfHours = 1;
        int courseNumOfStudentsEnrolled = 100;
        int courseTasRequired = 1;
        int courseGradersRequired = 1;
        int courseTaHourlyRates = 12;
        int courseGraderHourlyRates = 12;
        int courseBudget = 10000;

        Course course = new Course(courseCode,courseName,courseSemester,courseNumOfCredits,courseNumOfLabs,courseNumOfTutorials,courseNumOfHours,courseNumOfStudentsEnrolled,courseTasRequired,courseGradersRequired,courseTaHourlyRates,courseGraderHourlyRates,courseBudget,instructor);
        department.addAllCourse(course);
        assertEquals(1, department.getAllCourses().size());


        //Create Job
        PositionType posType = PositionType.Grader;
        Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job job = new Job(posType,postDeadLine, course);
        department.addAllJob(job);
        assertEquals(1, department.getAllJobs().size());

        //Create Job Posting

        String jobDescription = "Learn Software Engineering";
        String skillsRequired = "COMP202";
        String experienceRequired = "None";
        c.set(2013, Calendar.MARCH, 30, 9, 0, 0);
        Date offerDeadLine = new Date(c.getTimeInMillis());



        try{
            instructorController.createJobPosting(job, jobDescription,skillsRequired,experienceRequired,postDeadLine);
        }catch (InvalidInputException e){
            fail();
        }
        assertEquals(JobStatus.Posted, job.getState());

        try {
            instructorController.createJobPosting(null,null,null, null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(error," Must select Job!<br> Job description cannot be empty!<br> Skills required cannot be empty!<br> Experience required cannot be empty!<br> Job offer deadline date cannot be empty!<br>");
        assertEquals(JobStatus.Posted, job.getState());

        try {
            instructorController.createJobPosting(null, "", "", "", null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(error," Must select Job!<br> Job description cannot be empty!<br> Skills required cannot be empty!<br> Experience required cannot be empty!<br> Job offer deadline date cannot be empty!<br>");
        assertEquals(JobStatus.Posted, job.getState());

        try{
            instructorController.createJobPosting(job, jobDescription,skillsRequired,experienceRequired,offerDeadLine);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        assertEquals(error," Offer deadline cannot be before today!<br>");
        assertEquals(JobStatus.Posted, job.getState());

        checkJobPosting(job,jobDescription,skillsRequired,experienceRequired,postDeadLine,department);
        Department department2 = (Department)PersistenceXStream.loadFromXMLwithXStream();
        checkJobPosting(job,jobDescription,skillsRequired,experienceRequired,postDeadLine,department2);

        department2.delete();

        clean(null,instructor,course,null);

    }

    public void checkJobPosting(Job job, String jobDescription, String skillsRequired, String experienceRequired, Date postDeadLine, Department department) {

        assertEquals(1, department.getAllJobs().size());
        assertEquals(1, department.getAllCourses().size());
        assertEquals(1, department.getAllInstructors().size());

        assertEquals(jobDescription, department.getAllJob(0).getJobDescription());
        assertEquals(skillsRequired, department.getAllJob(0).getSkillsRequired());
        assertEquals(experienceRequired, department.getAllJob(0).getExperienceRequired());
        assertEquals(postDeadLine.toString(), department.getAllJob(0).getPostingDeadlineDate().toString());
    }

    @Test
    public void testCreateReview() {

        String error = "";

        InstructorController instructorController = new InstructorController(department);

        //Create instructor
        String instructorName = "James";
        int instructorID = 123456782;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructorA = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructorA);
        assertEquals(1, department.getAllInstructors().size());

        //Create student
        int studentID = 222222222;
        String studentName = "Bobby Jones";
        String studentEmail = "bobbyjones@mail.mcgill.ca";
        Boolean isGrad = false;
        int studentYear = 2;
        String studentJobPreference = "TA, but I don't mind.";
        int studentNumberOfHours = 45;

        Student studentA = new Student(studentID,studentName,studentEmail,isGrad,studentYear,studentJobPreference,studentNumberOfHours);
        department.addAllStudent(studentA);
        assertEquals(1,department.numberOfAllStudents());

        //Create a course
        String courseCode = "ECSE321";
        String courseName = "Software Engineering";
        String courseSemester = "W2017";
        int courseNumOfCredits = 3;
        int courseNumOfLabs = 0;
        int courseNumOfTutorials = 2;
        int courseNumOfHours = 1;
        int courseNumOfStudentsEnrolled = 100;
        int courseTasRequired = 1;
        int courseGradersRequired = 1;
        int courseTaHourlyRates = 12;
        int courseGraderHourlyRates = 12;
        int courseBudget = 10000;

        Course courseA = new Course(courseCode,courseName,courseSemester,courseNumOfCredits,courseNumOfLabs,courseNumOfTutorials,courseNumOfHours,courseNumOfStudentsEnrolled,courseTasRequired,courseGradersRequired,courseTaHourlyRates,courseGraderHourlyRates,courseBudget,instructorA);
        department.addAllCourse(courseA);
        assertEquals(1,department.numberOfAllCourses());

        //Create a job
        PositionType posType = PositionType.Grader;

        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        java.sql.Date postDeadLine = new java.sql.Date(c.getTimeInMillis());

        Job jobA = new Job(posType,postDeadLine,courseA);
        department.addAllJob(jobA);
        assertEquals(1,department.getAllJobs().size());

        assertEquals(studentA.hasReviewText(),false);
        assertEquals(instructorA.hasReviewText(), false);

        try {
            instructorController.createReview(instructorA,studentA,"Really good student", jobA);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals(studentA.hasReviewText(),true);
        assertEquals(instructorA.hasReviewText(), true);

        try {
            instructorController.createReview(null,null,"",null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(error, " Please select an instructor!<br> Please select a student!<br> Content cannot be empty!<br> Please select a job!<br>");
        assertEquals(studentA.hasReviewText(),true);
        assertEquals(instructorA.hasReviewText(), true);

        clean(studentA,instructorA,courseA,null);
    }

    private void clean(Student student, Instructor instructor, Course course, Job job) {

        if (student != null) {
            student.delete();
        }
        if (instructor != null) {
            instructor.delete();
        }
        if (course != null) {
            course.delete();
        }
        if (job != null) {
            job.delete();
        }
    }

}
