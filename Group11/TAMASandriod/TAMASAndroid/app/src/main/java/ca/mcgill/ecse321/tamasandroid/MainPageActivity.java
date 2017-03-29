package ca.mcgill.ecse321.tamasandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class MainPageActivity extends AppCompatActivity {

    private Department d = null;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //the description of the student

        TextView description = (TextView) findViewById(R.id.studentDescription);
        description.setText("Will be used to display the information of the student");

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
    }

    //Will access the view offers tab for job offers
    public void viewOffers(View view) {
        Intent intent = new Intent(this, ViewOfferActivity.class);
        startActivity(intent);
    }

    //Enter the menu to update info if something has changed
    public void updateInfo(View view){
        Intent intent = new Intent(this, UpdateInfoActivity.class);
        startActivity(intent);
    }

    //View the job posting
    public void viewPost(View view){
        Intent intent = new Intent(this, ViewJobPostingActivity.class);
        startActivity(intent);
    }

    //View the reviews from instructors
    public void viewReviews(View view){
        Intent intent = new Intent(this, ViewReviewsActivity.class);
        startActivity(intent);
    }
}
