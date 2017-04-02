package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.*;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by matlapo on 2017-03-30.
 */
public class TestDepartmentController {

    private Department department;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        PersistenceXStream.initializeModelManager("output"+ File.separator+"data.xml");
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
    public void testCreateCourse() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        //Create Instructor
        String instructorName = "James";
        int instructorID = 12345;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructorA = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructorA);
        assertEquals(1, department.getAllInstructors().size());


        String courseCode = "ECSE321";
        String courseName = "Software Engineering";
        String courseSemester = "W2017";
        String courseNumOfCredits = "3";
        String courseNumOfLabs = "0";
        String courseNumOfTutorials = "2";
        String courseNumOfHours = "1";
        String courseNumOfStudentsEnrolled = "100";
        String courseTasRequired = "1";
        String courseGradersRequired = "1";
        String courseTaHourlyRates = "12";
        String courseGraderHourlyRates = "12";
        String courseBudget = "10000";

        try {
            departmentController.createCourse(courseCode,courseName,courseSemester,courseNumOfCredits,courseNumOfLabs,courseNumOfTutorials,courseNumOfHours,courseNumOfStudentsEnrolled,courseTasRequired,courseGradersRequired,courseTaHourlyRates,courseGraderHourlyRates,courseBudget,instructorA);
        } catch(InvalidInputException e) {
            fail();
        }
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse(courseCode,courseName,courseSemester,courseNumOfCredits,courseNumOfLabs,courseNumOfTutorials,courseNumOfHours,courseNumOfStudentsEnrolled,courseTasRequired,courseGradersRequired,courseTaHourlyRates,courseGraderHourlyRates,courseBudget,null);
        } catch(InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals("Invalid instructor!", error);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse("","","","","","","","","","","","","",instructorA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a numeric number of credits! Input a numeric number of labs! Input a numeric number of tutorials! Input a numeric number of hours! Input a numeric number of student enrolled! Input a numeric number of TAs needed! Input a numeric number of graders needed! Input a numeric TA hourly rate! Input a numeric Grader hourly rate! Input a numeric budget! Course code cannot be empty! Course name cannot be empty!",error);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse(null,null,null,null,null,null,null,null,null,null,null,null,null,instructorA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a numeric number of credits! Input a numeric number of labs! Input a numeric number of tutorials! Input a numeric number of hours! Input a numeric number of student enrolled! Input a numeric number of TAs needed! Input a numeric number of graders needed! Input a numeric TA hourly rate! Input a numeric Grader hourly rate! Input a numeric budget! Course code cannot be empty! Course name cannot be empty!",error);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse("-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1",instructorA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a non-negative number of credits! Input a non-negative number of labs! Input a non-negative number of tutorials! Input a non-negative number of hours! Input a non-negative number of student enrolled! Input a non-negative number of TAs needed! Input a non-negative number of graders needed! Input a non-negative TA hourly rate! Input a non-negative grader hourly rate! Input a non-negative budget!", error);
        assertEquals(1,department.numberOfAllCourses());

    }


    @Test
    public void testCreateJob() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());
        assertEquals(0,department.getAllJobs().size());

        DepartmentController departmentController = new DepartmentController(department);

        PositionType posType = PositionType.Grader;

        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        java.sql.Date postDeadLine = new java.sql.Date(c.getTimeInMillis());


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

        //Create instructor for creating a course
        String instructorName = "James";
        int instructorID = 12345;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructorA = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructorA);
        assertEquals(1, department.getAllInstructors().size());

        Course courseA = new Course(courseCode,courseName,courseSemester,courseNumOfCredits,courseNumOfLabs,courseNumOfTutorials,courseNumOfHours,courseNumOfStudentsEnrolled,courseTasRequired,courseGradersRequired,courseTaHourlyRates,courseGraderHourlyRates,courseBudget,instructorA);
        department.addAllCourse(courseA);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createJob(posType,postDeadLine,courseA);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            fail();
        }

        try {
            departmentController.createJob(null,null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Position type cannot be empty! Posting deadline cannot be empty! Selected course cannot be empty!", error);
        assertEquals(1,department.numberOfAllJobs());
    }

    @Test
    public void testRegisterAStudent() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        String studentID = "260689345";
        String studentName = "Bobby Jones";
        String studentEmail = "bobbyjones@mail.mcgill.ca";
        Boolean isGrad = false;
        String studentYear = "2";
        String studentJobPreference = "TA";
        String studentNumberOfHours = "45";
    }

    @Test
    public void testCreateAllocation() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        //Create student
        int studentID = 123456;
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

        //Create instructor for creating a course
        String instructorName = "James";
        int instructorID = 12345;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructorA = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructorA);
        assertEquals(1, department.getAllInstructors().size());

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

        try {
            departmentController.createAllocation(jobA,studentA);
        } catch (InvalidInputException e) {
            fail();
        }
        //TODO

        try {
            departmentController.createAllocation(null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Job cannot be empty! Student cannot be empty!", error);
        assertEquals(1,department.getAllJobs().size());
        //TODO

    }

    @Test
    public void testRemoveAllocation() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        //Create student
        int studentID = 123456;
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

        //Create instructor for creating a course
        String instructorName = "James";
        int instructorID = 12345;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructorA = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructorA);
        assertEquals(1, department.getAllInstructors().size());

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

        try {
            departmentController.removeAllocation(jobA,studentA);
        } catch (InvalidInputException e) {
            fail();
        }
        //TODO

        try {
            departmentController.removeAllocation(null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Job cannot be empty! Student cannot be empty!", error);
        assertEquals(1,department.getAllJobs().size());
        //TODO
    }

    @Test
    public void testCreateJobOffer() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        //Create student
        int studentID = 123456;
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

        //Create instructor for creating a course
        String instructorName = "James";
        int instructorID = 12345;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructorA = new Instructor(instructorName,instructorID,instructorEmail);
        department.addAllInstructor(instructorA);
        assertEquals(1, department.getAllInstructors().size());

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

        try {
            departmentController.createJobOffer(jobA,studentA);
        } catch (InvalidInputException e) {
            fail();
        }
        //TODO

        try {
            departmentController.createJobOffer(null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Job cannot be empty! Student cannot be empty!", error);
        assertEquals(1,department.getAllJobs().size());

    }

}
