package ca.mcgill.ecse321.tamasandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.mcgill.ecse321.tamas.controller.InvalidInputException;
import ca.mcgill.ecse321.tamas.controller.StudentController;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class UpdateInfoActivity extends AppCompatActivity {

    private Department d = null;
    private String fileName;
    String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        fileName = getFilesDir().getAbsolutePath() + "/tamasandroid.xml";
        d = PersistenceXStream.initializeModelManager(fileName);
        refreshData();
    }

    private void refreshData() {
        TextView tv = (TextView) findViewById(R.id.updatestudent_name);
        tv.setText(MainActivity.login.getName());
        TextView tv2 = (TextView) findViewById(R.id.updatestudent_email);
        tv2.setText(MainActivity.login.getEmail());
        TextView tv3 = (TextView) findViewById((R.id.updatestudent_jobPreference));
        tv3.setText(MainActivity.login.getJobPreference());
    }

    public void isGrad(View view){
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox);

        if(checkbox.isChecked()){
            // student as a graduate student
            MainActivity.login.setIsGrad(true);
        }
        else{
            //student will be set as not a graduate student
            MainActivity.login.setIsGrad(false);
        }
    }

    public void Update(View v){
        StudentController s = new StudentController(d);
        TextView tv = (TextView) findViewById(R.id.updatestudent_name);
        TextView tv2 = (TextView) findViewById(R.id.updatestudent_email);
        TextView tv3 = (TextView) findViewById(R.id.updatestudent_jobPreference);

        //functions with set name, email and preference of student
        MainActivity.login.setName(tv.getText().toString());
        MainActivity.login.setEmail(tv2.getText().toString());
        MainActivity.login.setJobPreference(tv3.getText().toString());
        refreshData();
    }
}
