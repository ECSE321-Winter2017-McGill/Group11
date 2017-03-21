package ca.mcgill.ecse321.tamasandroid;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a dummy student

        //the description of the student

        TextView description = (TextView) findViewById(R.id.studentDescription);
        description.setText("Will be used to display the information of the student");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
