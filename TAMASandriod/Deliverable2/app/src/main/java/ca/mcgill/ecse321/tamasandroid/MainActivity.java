package ca.mcgill.ecse321.tamasandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

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

    private void refreshData() {
        TextView tv = (TextView) findViewById(R.id.newstudent_name);
        tv.setText("");
    }

    public void addStudent(View v) {
        //variables for create student from text view
        //Name
        TextView tvName = (TextView) findViewById(R.id.newstudent_name);
        //Id number
        TextView tvId = (TextView) findViewById(R.id.newstudent_id);
        String ID = tvId.getText().toString();
        int studentID = Integer.parseInt(ID);
        //email
        TextView tvEmail = (TextView) findViewById(R.id.newstudent_email);
        //isGrad?
        boolean checked = ((CheckBox) v).isChecked();
        boolean isGrad = false;
        switch(v.getId()){
            case R.id.newstudent_grad:
                if(checked){
                    isGrad = true;
                }
        }
        //year
        TextView tvYear = (TextView) findViewById(R.id.newstudent_year);
        String year = tvYear.getText().toString();
        int studentYear = Integer.parseInt(year);
        //Job preference
        TextView tvPreference = (TextView) findViewById(R.id.newstudent_preference);
        //Number of Hours
        TextView tvHours = (TextView) findViewById(R.id.newstudent_hours);
        String hours = tvHours.getText().toString();
        int studentHours = Integer.parseInt(hours);

        StudentController pc = new StudentController(d);

        pc.createStudent(studentID, tvName.getText().toString(), tvEmail.getText().toString(), isGrad, studentYear, tvPreference.getText().toString(), studentHours);
        refreshData();
    }
}
