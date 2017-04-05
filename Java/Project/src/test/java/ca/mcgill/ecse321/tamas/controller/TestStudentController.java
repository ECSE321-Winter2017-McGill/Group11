package ca.mcgill.ecse321.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ca.mcgill.ecse321.tamas.model.*;

import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

/**
 *
 * Created by edwardtanzhao on 30/3/2017
 *
 */
public class TestStudentController {

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
    public void testCreateStudent() {
        assertEquals(0, department.getAllStudents().size());

        StudentController studentController = new StudentController(department);
        Student student = null;

        //create student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        studentController.createStudent(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        assertEquals(1, department.getAllStudents().size());

        student = Student.getWithEmail(email);

        student.delete();
    }

    @Test
    public void testCreateStudent2(){
        assertEquals(0, department.getAllStudents().size());

        StudentController studentController = new StudentController(department);
        Student student = null;

        //create student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        try{
            studentController.createStudent(Integer.toString(studentID), name, email, isGrad, Integer.toString(year), jobPreference, Integer.toString(numberOfHours));
        } catch (InvalidInputException e){
            fail();
        }
        assertEquals(1, department.getAllStudents().size());

        student = Student.getWithEmail(email);

        student.delete();
    }

    @Test
    public void testAddPrevJobExp() {
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);

        //Create Student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(student);
        assertEquals(1, department.getAllStudents().size());
        assertEquals(0, student.getPreviousJobExperiences().size());

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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job job = new Job(posType,postDeadLine, course);
        department.addAllJob(job);
        assertEquals(1, department.getAllJobs().size());

        //add experience to student
        studentController.addPreviousExperienceToStudent(student, job);
        assertEquals(1, student.getPreviousJobExperiences().size());

        student.delete();
        instructor.delete();
    }

    @Test
    public void applyToJobPosting(){
        //normal function and if applicant tries a second time
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);


        //Create Student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        Student applicant = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(applicant);
        assertEquals(1, department.getAllStudents().size());

        //Create Instructor
        String instructorName = "James";
        int instructorID = 23456;
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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job jobPosting = new Job(posType,postDeadLine, course);
        department.addAllJob(jobPosting);
        assertEquals(1, department.getAllJobs().size());
        assertEquals(0, jobPosting.getApplicant().size());

        try{
            studentController.applyToJobPosting(jobPosting, applicant);
        } catch (InvalidInputException e){
            fail();
        }

        assertEquals(1, jobPosting.getApplicant().size());

        try{
            studentController.applyToJobPosting(jobPosting, applicant);
        } catch (InvalidInputException e){
            assertEquals(1, jobPosting.getApplicant().size());
            applicant.delete();
            instructor.delete();
        }
    }

    @Test
    public void applyToJobPosting2(){
        //Null applicant
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);


        //Create Student
        Student student = null;

        //Create Instructor
        String instructorName = "James";
        int instructorID = 23456;
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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job jobPosting = new Job(posType,postDeadLine, course);
        department.addAllJob(jobPosting);
        assertEquals(1, department.getAllJobs().size());
        assertEquals(0, jobPosting.getApplicant().size());

        try{
            studentController.applyToJobPosting(jobPosting, student);
        } catch (InvalidInputException e){

            assertEquals(0, jobPosting.getApplicant().size());
            instructor.delete();
        }
    }

    @Test
    public void applyToJobPosting3(){
        //Student doesn't exist
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);


        //Create Student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        Student applicant = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        assertEquals(0, department.getAllStudents().size());

        //Create Instructor
        String instructorName = "James";
        int instructorID = 23456;
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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job jobPosting = new Job(posType,postDeadLine, course);
        department.addAllJob(jobPosting);
        assertEquals(1, department.getAllJobs().size());
        assertEquals(0, jobPosting.getApplicant().size());

        try{
            studentController.applyToJobPosting(jobPosting, applicant);
        } catch (InvalidInputException e){
            assertEquals(0, jobPosting.getApplicant().size());

            applicant.delete();
            instructor.delete();
        }
    }

    @Test
    public void applyToJobPosting4(){
        //No Job chosen
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);


        //Create Student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        Student applicant = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(applicant);
        assertEquals(1, department.getAllStudents().size());

        Job jobPosting = null;

        try{
            studentController.applyToJobPosting(jobPosting, applicant);
        } catch (InvalidInputException e){
            assertEquals(0, applicant.getJobsAppliedTo().size());
            applicant.delete();
        }
    }

    @Test
    public void testRespondToOfferAccept(){
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);


        //Create Student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(student);
        assertEquals(1, department.getAllStudents().size());

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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job jobOffer = new Job(posType,postDeadLine, course);
        department.addAllJob(jobOffer);
        assertEquals(1, department.getAllJobs().size());

        //accept or decline
        boolean accept = true;

        assertEquals(0, jobOffer.getEmployee().size());

        studentController.respondToJobOffer(student, jobOffer, accept);

        assertEquals(1, jobOffer.getEmployee().size());

        student.delete();
        instructor.delete();
    }

    @Test
    public void testRespondToOfferReject(){
        assertEquals(0, department.getAllStudents().size());
        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        StudentController studentController = new StudentController(department);


        //Create Student
        int studentID = 123456789;
        String name = "Jonathan";
        String email = "jonathan@mcgill.ca";
        boolean isGrad = true;
        int year = 2016;
        String jobPreference = "Preference to be a TA";
        int numberOfHours = 16;

        Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(student);
        assertEquals(1, department.getAllStudents().size());

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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job jobOffer = new Job(posType,postDeadLine, course);
        department.addAllJob(jobOffer);
        assertEquals(1, department.getAllJobs().size());

        //accept or decline
        boolean reject = false;

        assertEquals(0, jobOffer.getEmployee().size());

        studentController.respondToJobOffer(student, jobOffer, reject);

        assertEquals(0, jobOffer.getEmployee().size());

        student.delete();
        instructor.delete();
    }

}
