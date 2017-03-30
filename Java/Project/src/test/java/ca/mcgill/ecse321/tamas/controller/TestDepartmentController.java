package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.controller.DepartmentController;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

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
        } catch(InvalidInputException e1) {
            System.out.println(e1.getMessage());
            fail();
        }
    }
}
