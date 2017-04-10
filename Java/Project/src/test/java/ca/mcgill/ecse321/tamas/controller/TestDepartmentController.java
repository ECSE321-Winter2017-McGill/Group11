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
        int instructorID = 888888888;
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
        String courseNumOfHours = "100";
        String courseNumOfStudentsEnrolled = "100";
        String courseTasRequired = "1";
        String courseGradersRequired = "1";
        String courseTaHourlyRates = "12";
        String courseGraderHourlyRates = "12";
        String courseBudget = "100";

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
        assertEquals(" Invalid instructor!<br>", error);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse("","","","","","","","","","","","","",instructorA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a numeric number of credits!<br> Input a numeric number of labs!<br> Input a numeric number of tutorials!<br> Input a numeric number of hours!<br> Input a numeric number of student enrolled!<br> Input a numeric number of TAs needed!<br> Input a numeric number of graders needed!<br> Input a numeric TA hourly rate!<br> Input a numeric Grader hourly rate!<br> Input a numeric budget!<br> Course code cannot be empty!<br> Course name cannot be empty!<br>",error);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse(null,null,null,null,null,null,null,null,null,null,null,null,null,instructorA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a numeric number of credits!<br> Input a numeric number of labs!<br> Input a numeric number of tutorials!<br> Input a numeric number of hours!<br> Input a numeric number of student enrolled!<br> Input a numeric number of TAs needed!<br> Input a numeric number of graders needed!<br> Input a numeric TA hourly rate!<br> Input a numeric Grader hourly rate!<br> Input a numeric budget!<br> Course code cannot be empty!<br> Course name cannot be empty!<br>",error);
        assertEquals(1,department.numberOfAllCourses());

        try {
            departmentController.createCourse("-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1",instructorA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Input a non-negative number of credits!<br> Input a non-negative number of labs!<br> Input a non-negative number of tutorials!<br> Input a non-negative number of hours!<br> Input a non-negative number of student enrolled!<br> Input a non-negative number of TAs needed!<br> Input a non-negative number of graders needed!<br> Input a non-negative TA hourly rate!<br> Input a non-negative grader hourly rate!<br> Input a non-negative budget!<br>Please input valid number of hourse ranging between 45 hours to 180 hours!<br>", error);
        assertEquals(1,department.numberOfAllCourses());

        //remove the instructor for next test suits
        instructorA.delete();

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
        int instructorID = 123456789;
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
        assertEquals(" Must select a Position!<br> Posting deadline cannot be empty!<br> Selected course cannot be empty!<br>", error);
        assertEquals(1,department.numberOfAllJobs());


        //remove instructor and course for next test suite
        instructorA.delete();
        courseA.delete();
    }

    @Test
    public void testCreateAllocation() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

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

        //Create instructor for creating a course
        String instructorName = "James";
        int instructorID = 333333333;
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

        //first make sure that there is no allocated job
        assertEquals(false, jobA.hasAllocatedStudent());

        //now try to create an allocation
        try {
            departmentController.createAllocation(jobA,studentA);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals(1, department.getAllJobs().size());
        assertEquals(true, jobA.hasAllocatedStudent());

        try {
            departmentController.createAllocation(null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Job cannot be empty!<br> Student cannot be empty!<br>", error);
        assertEquals(1,department.getAllJobs().size());
        assertEquals(true,jobA.hasAllocatedStudent());

        instructorA.delete();
        studentA.delete();
        courseA.delete();

    }

    @Test
    public void testRemoveAllocation() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        //Create student
        int studentID = 444444444;
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
        int instructorID = 555555555;
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

        //first we need to allocate the student before removing him
        try {
            departmentController.createAllocation(jobA,studentA);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals(1,department.getAllJobs().size());
        assertEquals(true,jobA.hasAllocatedStudent());

        //now we test if remove works
        try {
            departmentController.removeAllocation(jobA,studentA);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals(1,department.getAllJobs().size());
        assertEquals(false,jobA.hasAllocatedStudent());


        //test that removing a student already removed is correctly handled
        try {
            departmentController.removeAllocation(jobA,studentA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Student is not allocated for this job!<br>", error);
        assertEquals(1,department.getAllJobs().size());
        assertEquals(false,jobA.hasAllocatedStudent());

        try {
            departmentController.removeAllocation(null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Job cannot be empty!<br> Student cannot be empty!<br> Student is not allocated for this job!<br>", error);
        assertEquals(1,department.getAllJobs().size());
        assertEquals(false,jobA.hasAllocatedStudent());

        instructorA.delete();
        studentA.delete();
        courseA.delete();
    }

    @Test
    public void testCreateJobOffer() {

        String error = "";

        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

        DepartmentController departmentController = new DepartmentController(department);

        //Create student
        int studentID = 666666666;
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
        int instructorID = 777777777;
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

        //try adding the job offer a second time
        try {
            departmentController.createJobOffer(jobA,studentA);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Offer already made to this student!<br>", error);

        try {
            departmentController.createJobOffer(null,null);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        assertEquals(" Job cannot be empty!<br> Student cannot be empty!<br>", error);
        assertEquals(1,department.getAllJobs().size());

        instructorA.delete();
        studentA.delete();
        courseA.delete();

    }

}
