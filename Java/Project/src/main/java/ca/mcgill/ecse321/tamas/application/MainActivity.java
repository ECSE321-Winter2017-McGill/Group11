//package ca.mcgill.ecse321.eventregistration;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import ca.mcgill.ecse321.eventregistration.controller.EventRegistrationController;
//import ca.mcgill.ecse321.eventregistration.controller.InvalidInputException;
//import ca.mcgill.ecse321.eventregistration.model.Event;
//import ca.mcgill.ecse321.eventregistration.model.Participant;
//import ca.mcgill.ecse321.eventregistration.model.Registration;
//import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
//import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RegistrationManager rm = null;
//    private String fileName;
//    String error = null;
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    private GoogleApiClient client;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        // Initialize file name and XStream
//        fileName = getFilesDir().getAbsolutePath() + "/eventregistration.xml";
//        rm = PersistenceXStream.initializeModelManager(fileName);
//        refreshData();
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void refreshData() {
//        TextView tv = (TextView) findViewById(R.id.newparticipant_name);
//        tv.setText("");
//
//        // Initialize the data in the participant spinner
//        Spinner spinner = (Spinner) findViewById(R.id.participantspinner);
//        ArrayAdapter<CharSequence> participantAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
//        participantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        for (Participant p : rm.getParticipants()) {
//            participantAdapter.add(p.getName());
//        }
//        spinner.setAdapter(participantAdapter);
//
//        // Initialize the data in the event spinner
//        Spinner spinner2 = (Spinner) findViewById(R.id.eventspinner);
//        ArrayAdapter<CharSequence> eventAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
//        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        for (Event p : rm.getEvents()) {
//            eventAdapter.add(p.getName());
//        }
//        spinner2.setAdapter(eventAdapter);
//
//
//        TextView registrationsTextField = (TextView) findViewById(R.id.registrationsText);
//        String text = "";
//        for ( Registration r : rm.getRegistrations()){
//            text += r.getParticipant().getName() + " -> " + r.getEvent().getName() + "\n";
//        }
//        registrationsTextField.setText(text);
//    }
//
//    public void addParticipant(View v) {
//
//        //error messages
//        //for participant name
//        final EditText edit = (EditText) findViewById(R.id.newparticipant_name);
//        Button addParticipant = (Button) findViewById(R.id.addParticipant);
//        addParticipant.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (edit.getText().length() == 0) {
//                    edit.setError("Please provide a participant name");
//                } else {
//                    TextView tv = (TextView) findViewById(R.id.newparticipant_name);
//                    EventRegistrationController pc = new EventRegistrationController(rm);
//                    try {
//                        pc.createParticipant(tv.getText().toString());
//                    } catch (InvalidInputException e) {
//                        error = e.getMessage();
//                    }
//                    refreshData();
//                }
//
//            }
//        });
//    }
//
//    public void registerParticipant(View v) {
//        Button registerParticipantButton = (Button) findViewById(R.id.registerParticipant);
//        final Spinner participantSpinner = (Spinner) findViewById(R.id.participantspinner);
//        final Spinner eventSpinner = (Spinner) findViewById(R.id.eventspinner);
//        registerParticipantButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(participantSpinner.getSelectedItem() != null && eventSpinner.getSelectedItem() != null) {
//                    EventRegistrationController pc = new EventRegistrationController(rm);
//
//                    String participantName = participantSpinner.getSelectedItem().toString();
//
//                    Participant participant = null;
//                    for(Participant p : rm.getParticipants()) {
//                        if (p.getName() == participantName){
//                            participant = p;
//                            break;
//                        }
//                    }
//
//                    String eventName = eventSpinner.getSelectedItem().toString();
//                    Event event = null;
//                    for(Event e : rm.getEvents()){
//                        if(e.getName() == eventName){
//                            event = e;
//                            break;
//                        }
//                    }
//
//                    if(participant != null && event != null){
//                        try {
//                            pc.register(participant, event);
//                        } catch (InvalidInputException e) {
//                            error = e.getMessage();
//                        }
//                    }else{
//                            Snackbar.make(v, "You must choose a participant and an event!", Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
//                    }
//                    refreshData();
//                }
//            }
//        });
//    }
//
//    public void addEvent(View v) {
//
//        //error message for event
//        final EditText edit = (EditText) findViewById(R.id.newevent_name);
//      //  final TextView start = (TextView) findViewById(R.id.newevent_starttime);
//      //  final TextView end = (TextView) findViewById(R.id.newevent_endtime);
//
//      //  final Integer time1 = Integer.parseInt(start.getText().toString());
//      //  final Integer time2 = Integer.parseInt(end.getText().toString());
//
//        Button addEvent = (Button) findViewById(R.id.addEvent);
//        addEvent.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //check if no input for name
//
//                if (edit.getText().length() == 0) {
//                    edit.setError("Please provide a event name");
//                }
//                //check for if end time is earlier than start time
//                /*else if (time1 > time2) {
//                    edit.setError("Please make sure end time is after start time");
//                }*/ else {
//                    TextView tv = (TextView) findViewById(R.id.newevent_name);
//                    TextView tvDate = (TextView) findViewById(R.id.newevent_date);
//                    TextView tvStartTime = (TextView) findViewById(R.id.newevent_starttime);
//                    TextView tvEndTime = (TextView) findViewById(R.id.newevent_endtime);
//
//                    // conversion to sql
//                    //for the date
//                    SimpleDateFormat temp = new SimpleDateFormat("dd-MM-yyyy");
//                    Date date = null;
//                    try {
//                        date = temp.parse(tvDate.getText().toString());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//
//                    //for start time
//                    SimpleDateFormat temp2 = new SimpleDateFormat("HH:mm");
//                    Date start = null;
//                    try {
//                        start = temp2.parse(tvStartTime.getText().toString());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    java.sql.Time sqlStartTime = new java.sql.Time(start.getTime());
//
//                    //for end time
//                    SimpleDateFormat temp3 = new SimpleDateFormat("HH:mm");
//                    Date end = null;
//                    try {
//                        end = temp3.parse(tvEndTime.getText().toString());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    java.sql.Time sqlEndTime = new java.sql.Time(end.getTime());
//
//                    if(sqlStartTime.compareTo(sqlEndTime) > 0){
//                        Snackbar.make(v, "Start time has to precede end time!", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//
//
//                    EventRegistrationController pc = new EventRegistrationController(rm);
//                    try {
//                        pc.createEvent(tv.getText().toString(), sqlStartDate, sqlStartTime, sqlEndTime);
//                    } catch (InvalidInputException e) {
//                        error = e.getMessage();
//                    }
//                    refreshData();
//                }
//
//            }
//        });
//    }
//
//    public void showDatePickerDialog(View v) {
//        TextView tf = (TextView) v;
//        Bundle args = getDateFromLabel(tf.getText());
//        args.putInt("id", v.getId());
//
//        DatePickerFragment newFragment = new DatePickerFragment();
//        newFragment.setArguments(args);
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }
//
//    public void showTimePickerDialog(View v) {
//        TextView tf = (TextView) v;
//        Bundle args = getTimeFromLabel(tf.getText());
//        args.putInt("id", v.getId());
//
//        TimePickerFragment newFragment = new TimePickerFragment();
//        newFragment.setArguments(args);
//        newFragment.show(getSupportFragmentManager(), "timePicker");
//    }
//
//    private Bundle getTimeFromLabel(CharSequence text) {
//        Bundle rtn = new Bundle();
//        String comps[] = text.toString().split(":");
//        int hour = 12;
//        int minute = 0;
//
//        if (comps.length == 2) {
//            hour = Integer.parseInt(comps[0]);
//            minute = Integer.parseInt(comps[1]);
//        }
//
//        rtn.putInt("hour", hour);
//        rtn.putInt("minute", minute);
//
//        return rtn;
//    }
//
//    private Bundle getDateFromLabel(CharSequence text) {
//        Bundle rtn = new Bundle();
//        String comps[] = text.toString().split("-");
//        int day = 1;
//        int month = 1;
//        int year = 1;
//
//        if (comps.length == 3) {
//            day = Integer.parseInt(comps[0]);
//            month = Integer.parseInt(comps[1]);
//            year = Integer.parseInt(comps[2]);
//        }
//
//        rtn.putInt("day", day);
//        rtn.putInt("month", month - 1);
//        rtn.putInt("year", year);
//
//        return rtn;
//    }
//
//    public void setTime(int id, int h, int m) {
//        TextView tv = (TextView) findViewById(id);
//        tv.setText(String.format("%02d:%02d", h, m));
//    }
//
//    public void setDate(int id, int d, int m, int y) {
//        TextView tv = (TextView) findViewById(id);
//        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
//    }
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Main Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
//    }
//}
