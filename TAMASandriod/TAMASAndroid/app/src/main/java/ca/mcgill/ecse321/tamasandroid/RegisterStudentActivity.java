package ca.mcgill.ecse321.tamasandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class RegisterStudentActivity extends AppCompatActivity {

    private Department d = null;
    private String fileName;
    String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
        refreshData();
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

        //Set error in error TextView
        TextView errorText = (TextView) findViewById(R.id.errorlabel);
        errorText.setText(error);
    }


    /**
     * Method to add a student
     * @param v
     */
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
}
