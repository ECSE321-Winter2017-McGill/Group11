package ca.mcgill.ecse321.tamasandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

import ca.mcgill.ecse321.tamas.controller.InstructorController;
import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.PositionType;
import ca.mcgill.ecse321.tamas.model.Review;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class ViewReviewsActivity extends AppCompatActivity {

    private Department d = null;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
        generateReview();
        refreshData();
    }

    private void refreshData(){
        // Initialize the data in the offer spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.reviewspinner);
        ArrayAdapter<CharSequence> reviewAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        reviewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Review i : d.getAllReviews()) {
            String review = i.getReviewer().getName();
            reviewAdapter.add(review);
        }
        spinner2.setAdapter(reviewAdapter);
    }

    /**
     * Method will generate a review for the student
     */
    public void generateReview(){
        //generate a view to be available to be viewed
        //Create Instructor
        String instructorName = "James";
        int instructorID = 12347;
        String instructorEmail = "james@mcgill.ca";

        Instructor instructor = new Instructor(instructorName,instructorID,instructorEmail);
        d.addAllInstructor(instructor);

        //Create Course
        String courseCode = "ECSE323";
        String courseName = "Software Engineering Adv";
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

        Job job = new Job(posType,postDeadLine, course);
        d.addAllJob(job);

        String content = "Student was very efficient and always on time would hire again and provide a recommendation";

        //i.createReview(instructor, MainActivity.login, content, job);
        Review review = new Review(content, MainActivity.login, job, instructor);

        d.addAllReview(review);
    }

    /**
     * Method will show the review to the student on the screen in a text view field
     * @param view
     */
    public void viewReview(View view){
        final Spinner reviewSpinner = (Spinner) findViewById(R.id.reviewspinner);

        String name = reviewSpinner.getSelectedItem().toString();
        Review review = null;
        for (Review r : d.getAllReviews()) {
            String nameinst = r.getReviewer().getName();
            if (nameinst.contentEquals(name)) {
                review = r;
                break;
            }
        }

        TextView tv = (TextView) findViewById(R.id.Review);
        tv.setText(review.getContent());
    }
}
