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
    public void testCreateJobPosting() {

        assertEquals(0, department.getAllJobs().size());
        assertEquals(0, department.getAllCourses().size());
        assertEquals(0, department.getAllInstructors().size());

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
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job job = new Job(posType,postDeadLine, course);
        department.addAllJob(job);
        assertEquals(1, department.getAllJobs().size());

        //Create Job Posting

        String jobDescription = "Learn Software Engineering";
        String skillsRequired = "COMP202";
        String experienceRequired = "None";
        c.set(2017, Calendar.MARCH, 30, 9, 0, 0);
        Date offerDeadLine = new Date(c.getTimeInMillis());



        try{
            instructorController.createJobPosting(job, jobDescription,skillsRequired,experienceRequired,postDeadLine);
        }catch (InvalidInputException e){
            fail();
        }

        checkJobPosting(job,jobDescription,skillsRequired,experienceRequired,postDeadLine,department);

        Department department2 = (Department)PersistenceXStream.loadFromXMLwithXStream();
        checkJobPosting(job,jobDescription,skillsRequired,experienceRequired,postDeadLine,department2);

        department2.delete();
        instructor.delete();

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



}
