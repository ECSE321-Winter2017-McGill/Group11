package ca.mcgill.ecse321.tamasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Date;
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

import static ca.mcgill.ecse321.tamasandroid.R.id.offerspinner;
import static ca.mcgill.ecse321.tamasandroid.R.id.studentspinner;

public class ViewOfferActivity extends AppCompatActivity {
    private Department d = null;
    private String fileName;
    String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offer);

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
        addJobPosting();
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

    /**
     * Method to generate a dummy job posting
     */
    public void addJobPosting(){
        //create a dummy posting
        Instructor dummyInstructor = new Instructor("John", 123456799, "john@mail.uni.ca");
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
     * Method in order accept an offer
     *
     * @param view
     */
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

        d.removeAllJob(jobOffer);
        refreshData();
    }


    /**
     * Method to reject an offer
     *
     * @param view
     */
    public void rejectOffer(View view){
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
        studentController.respondToJobOffer(MainActivity.login, jobOffer, false);

        d.removeAllJob(jobOffer);
        refreshData();
    }
}
