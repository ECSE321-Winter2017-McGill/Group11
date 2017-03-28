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
        TextView tvName = (TextView) findViewById(R.id.newstudent_name);
        tvName.setText("");
        TextView tvId = (TextView) findViewById(R.id.newstudent_id);
        tvId.setText("");
        TextView tvEmail = (TextView) findViewById(R.id.newstudent_email);
        tvEmail.setText("");
        TextView tvYear = (TextView) findViewById(R.id.newstudent_year);
        tvYear.setText("");
        TextView tvPreference = (TextView) findViewById(R.id.newstudent_preference);
        tvPreference.setText("");
        TextView tvHours = (TextView) findViewById(R.id.newstudent_hours);
        tvHours.setText("");

        // Initialize the data in the student spinner
        Spinner spinner = (Spinner) findViewById(studentspinner);
        ArrayAdapter<CharSequence> participantAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        participantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Student s : d.getAllStudents()) {
            participantAdapter.add(s.getName());
        }
        spinner.setAdapter(participantAdapter);
    }

    public void addStudent(View v) {
        //variables for create student from text view
        //Name
        TextView tvName = (TextView) findViewById(R.id.newstudent_name);
        //Id number
        TextView tvId = (TextView) findViewById(R.id.newstudent_id);
        String ID = tvId.getText().toString();

        //email
        TextView tvEmail = (TextView) findViewById(R.id.newstudent_email);
        //isGrad?
        CheckBox gradCheckBox = (CheckBox) findViewById(R.id.isGrad_checkBox);
        boolean isGrad = false;
        isGrad = gradCheckBox.isChecked();

        //year
        TextView tvYear = (TextView) findViewById(R.id.newstudent_year);
        String year = tvYear.getText().toString();

        //Job preference
        TextView tvPreference = (TextView) findViewById(R.id.newstudent_preference);
        //Number of Hours
        TextView tvHours = (TextView) findViewById(R.id.newstudent_hours);
        String hours = tvHours.getText().toString();

        StudentController pc = new StudentController(d);
        try {
            pc.createStudent(ID, tvName.getText().toString(), tvEmail.getText().toString(), isGrad, year, tvPreference.getText().toString(), hours);
            error = "";
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }

        refreshData();
    }

    public void loginActivity(View view){
        final Spinner studentSpinner = (Spinner) findViewById(R.id.studentspinner);

        //have to use the data gottenand login as the student



        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }
}
