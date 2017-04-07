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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import ca.mcgill.ecse321.tamas.controller.InvalidInputException;
import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

import static ca.mcgill.ecse321.tamasandroid.R.id.studentspinner;

public class MainActivity extends AppCompatActivity {

    private Department d = null;
    private String fileName;
    String error = null;
    public static Student login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
        refreshData();
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

    private void refreshData(){

        // Initialize the data in the student spinner
        Spinner spinner = (Spinner) findViewById(studentspinner);
        ArrayAdapter<Integer> participantAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item);
        participantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Student s : d.getAllStudents()) {
            participantAdapter.add(s.getStudentID());
        }
        spinner.setAdapter(participantAdapter);
    }

    public void loginActivity(View view){
        //select student in spinner for login
        final Spinner studentSpinner = (Spinner) findViewById(R.id.studentspinner);
        String studentID = studentSpinner.getSelectedItem().toString();

        Intent intent = new Intent(this, MainPageActivity.class);


        for (Student a : d.getAllStudents()) {
            if (a.getStudentID() == Integer.parseInt(studentID)) {
                login = a;
                break;
            }
        }
        startActivity(intent);
    }

    public void registerActivity(View view){
        Intent intent = new Intent(this, RegisterStudentActivity.class);
        startActivity(intent);
    }
}
