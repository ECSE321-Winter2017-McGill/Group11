package ca.mcgill.ecse321.tamasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import ca.mcgill.ecse321.tamas.controller.InstructorController;
import ca.mcgill.ecse321.tamas.controller.InvalidInputException;
import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.PositionType;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class ViewJobPostingActivity extends AppCompatActivity {

    private Department d = null;
    private String fileName;
    String error = null;
    Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_posting);


        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);

        addJobPosting();
        student = MainActivity.login;
        refreshData();
    }

    private void refreshData(){
        // Initialize the data in the jobposting spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.jobpostingspinner);
        //ArrayAdapter<PositionType> jobPostingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> jobPostingAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        //jobPostingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobPostingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        for (Job i : d.getAllJobs()) {
            // jobPostingAdapter.add(i.getPosType());
            String posting = i.getPosType().toString() + i.getCorrespondingCourse().getName();
            jobPostingAdapter.add(posting);
        }
        spinner2.setAdapter(jobPostingAdapter);

        //Set error in error TextView
        TextView errorText = (TextView) findViewById(R.id.errorlabel);
        errorText.setText(error);
    }

    /**
     * Method to generate a job posting
     */
    public void addJobPosting(){
        //create a dummy posting
        Instructor dummyInstructor = new Instructor("Henry Smith", 123456789, "henry.smith@mail.uni.ca");
        Course dummyCourse = new Course("EECC 111", "Introduction", "W2017", 15, 0, 3, 120, 200, 5, 4, 20, 15, 500, dummyInstructor);
        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        java.sql.Date dummyPostDeadLine = new java.sql.Date(c.getTimeInMillis());
        Job dummyJob = new Job(PositionType.TA, dummyPostDeadLine, dummyCourse);

        InstructorController instructor = new InstructorController(d);
        d.addAllJob(dummyJob);
        try {
            instructor.createJobPosting(dummyJob, "Tutorial for first years", "Should have knowledge of EECC 112", "Should have been TA at least once before", dummyPostDeadLine);
            error = "";
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
    }

    /**
     * Method to apply to job posting
     * @param view
     */
    public void applyToPosting(View view){
        //implement apply methods

        final Spinner postingSpinner = (Spinner) findViewById(R.id.jobpostingspinner);
        StudentController s = new StudentController(d);

        String jobName = postingSpinner.getSelectedItem().toString();
        Job jobposting = null;
        for (Job j : d.getAllJobs()) {
            String postingName = j.getPosType().toString() + j.getCorrespondingCourse().getName();
            if (postingName.contentEquals(jobName)) {
                jobposting = j;
                break;
            }
        }

        try {
            s.applyToJobPosting(jobposting, student);
            error = "";
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
    }
}
