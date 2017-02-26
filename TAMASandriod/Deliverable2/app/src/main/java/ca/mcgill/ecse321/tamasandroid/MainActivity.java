package ca.mcgill.ecse321.tamasandroid;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import ca.mcgill.ecse321.tamas.controller.DepartmentController;
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

import static ca.mcgill.ecse321.tamasandroid.R.id.jobpostingspinner;
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

    private void refreshData() {
        TextView tvName = (TextView) findViewById(R.id.newstudent_name);
        tvName.setText("");
        TextView tvId = (TextView) findViewById(R.id.newstudent_id);
        tvId.setText("");
        TextView tvEmail = (TextView) findViewById(R.id.newstudent_email);
        tvEmail.setText("");
        TextView tvGrad = (TextView) findViewById(R.id.newstudent_grad);
        tvGrad.setText("");
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

        for (Student s: d.getAllStudents() ) {
            participantAdapter.add(s.getName());
        }
        spinner.setAdapter(participantAdapter);


        // Initialize the data in the jobposting spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.jobpostingspinner);
        //ArrayAdapter<PositionType> jobPostingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> jobPostingAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        //jobPostingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobPostingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        for (Job i: d.getAllJobs() ) {
           // jobPostingAdapter.add(i.getPosType());
            String posting = i.getPosType().toString() + i.getCorrespondingCourse().getName();
            jobPostingAdapter.add(posting);
        }
        spinner2.setAdapter(jobPostingAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        TextView tvGrad = (TextView) findViewById(R.id.newstudent_grad);
        String grad = tvGrad.getText().toString();
        boolean isGrad = false;
        if(Objects.equals(grad, "Yes")){
            isGrad = true;
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

    public void addJobPosting(View v) throws InvalidInputException {
        //variables to create job posting
        //the job (dummy job)
        Instructor dummyInstructor = new Instructor("Henry Smith", 123456789, "henry.smith@mail.uni.ca");
        Course dummyCourse = new Course("EECC 111", "Introduction", "W2017", 15, 0, 3, 120, 200, 5, 4, 20, 15, 500, dummyInstructor );
        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        java.sql.Date dummyPostDeadLine = new java.sql.Date(c.getTimeInMillis());
        Job dummyJob = new Job(PositionType.TA, dummyPostDeadLine,dummyCourse);
        //Description
        TextView tvDescription = (TextView) findViewById(R.id.newjobposting_description);
        //Required Skills
        TextView tvSkills = (TextView) findViewById(R.id.newjobposting_skills);
        //Required Experience
        TextView tvExperience = (TextView) findViewById(R.id.newjobposting_experience);
        //Date
        TextView tvDate = (TextView) findViewById(R.id.newjobposting_date);
        SimpleDateFormat temp = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = (Date) temp.parse(tvDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDeadline = new java.sql.Date(date.getTime());

        InstructorController pc = new InstructorController(d);

        d.addAllJob(dummyJob);
        pc.createJobPosting(dummyJob, tvDescription.getText().toString(), tvSkills.getText().toString(), tvExperience.getText().toString(), sqlDeadline);

        refreshData();
    }

    public void applyToJob(View v){
        Button applyToJobButton = (Button) findViewById(R.id.register);
        final Spinner studentSpinner = (Spinner) findViewById(R.id.studentspinner);
        final Spinner postingSpinner = (Spinner) findViewById(R.id.jobpostingspinner);
        applyToJobButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(studentSpinner.getSelectedItem() != null && postingSpinner.getSelectedItem() != null){
                    StudentController s = new StudentController(d);

                    String studentName = studentSpinner.getSelectedItem().toString();

                    Student applicant = null;
                    for(Student a : d.getAllStudents()){
                        if(a.getName() == studentName){
                            applicant = a;
                            break;
                        }
                    }

                    String jobName = postingSpinner.getSelectedItem().toString();
                    Job jobposting = null;
                    for(Job j : d.getAllJobs()){
                        String postingName = j.getPosType().toString() + j.getCorrespondingCourse().getName();
                        if(postingName.contentEquals(jobName)){
                            jobposting = j;
                            break;
                        }
                    }

                    try {
                        s.applyToJobPosting(jobposting, applicant);
                    } catch (InvalidInputException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }
    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private Bundle getDateFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);

        return rtn;
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }
}
