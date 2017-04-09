package ca.mcgill.ecse321.tamasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Date;
import java.util.Calendar;

import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.PositionType;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

import static ca.mcgill.ecse321.tamasandroid.R.id.offerspinner;
import static ca.mcgill.ecse321.tamasandroid.R.id.studentspinner;

public class ViewOfferActivity extends AppCompatActivity {
    private Department d = null;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offer);

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
        refreshData();
    }

    private void refreshData(){

        // Initialize the data in the offer spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.offerspinner);
        ArrayAdapter<CharSequence> jobOfferAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        jobOfferAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Job i : d.getAllJobs()) {
            // jobPostingAdapter.add(i.getPosType());
            String posting = i.getPosType().toString() + i.getCorrespondingCourse().getName();
            jobOfferAdapter.add(posting);
        }
        spinner2.setAdapter(jobOfferAdapter);
    }

    public void generateOffer(View view){
        //Create Instructor
        String instructorName = "James";
        int instructorID = 12346;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructor = new Instructor(instructorName,instructorID,instructorEmail);
        d.addAllInstructor(instructor);


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
        d.addAllCourse(course);

        //Create Job
        PositionType posType = PositionType.Grader;
        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date postDeadLine = new Date(c.getTimeInMillis());

        Job jobOffer = new Job(posType,postDeadLine, course);
        d.addAllJob(jobOffer);
    }

    public void acceptOffer(View view){
        StudentController studentController = new StudentController(d);

        final Spinner offerSpinner = (Spinner) findViewById(R.id.offerspinner);

        String jobDesc = offerSpinner.getSelectedItem().toString();
        Job jobOffer = null;
        for (Job j : d.getAllJobs()) {
            String offerDesc = j.getPosType().toString() + j.getCorrespondingCourse().getName();
            if (offerDesc.contentEquals(jobDesc)) {
                jobOffer = j;
                break;
            }
        }
        studentController.respondToJobOffer(MainActivity.login, jobOffer, true);
    }

    public void rejectOffer(View view){
        StudentController studentController = new StudentController(d);

        final Spinner offerSpinner = (Spinner) findViewById(R.id.offerspinner);

        String jobDesc = offerSpinner.getSelectedItem().toString();
        Job jobOffer = null;
        for (Job j : d.getAllJobs()) {
            String offerDesc = j.getJobDescription();
            if (offerDesc.contentEquals(jobDesc)) {
                jobOffer = j;
                break;
            }
        }
        studentController.respondToJobOffer(MainActivity.login, jobOffer, false);
    }
}
