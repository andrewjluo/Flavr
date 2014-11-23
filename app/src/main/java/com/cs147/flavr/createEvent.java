package com.cs147.flavr;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import java.lang.String;
import java.util.Calendar;
import android.widget.Toast;


public class createEvent extends Activity {
    //Temporary array for event string information.
    public final static String EVENT_STRINGS = "temp";
    //Temporary array for event time information.
    public final static String EVENT_TIMES = "tempTimes";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
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

    /*Extracts string from an edit text field where the user inputs data.
     */
    public String extractStringFromID(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
    }

    private void giveToastError(String error) {
        Toast errorMessage;
        if(error == "foodType")  Toast.makeText(getApplicationContext(), "Please enter a valid food type.", Toast.LENGTH_LONG).show();
        else if(error == "eventTitle")  Toast.makeText(getApplicationContext(), "Please enter a valid event title.", Toast.LENGTH_LONG).show();
        else if(error == "location")  Toast.makeText(getApplicationContext(), "Please enter a valid location.", Toast.LENGTH_LONG).show();
    }
    /*Upon the clicking of the submit button, reads all the strings and times from the event listing and puts them into an respective arrays to be passed into
     *the next activity of confirming the event. Bundles these into an extra for the intent
     * and goes to event confirmation.
     */
    public void submitEvent(View view) {

        String[] eventInformation = new String[6];
        String foodType = extractStringFromID(R.id.food_type);
        String eventTitle = extractStringFromID(R.id.event_title);
        String description = extractStringFromID(R.id.description);
        String location = extractStringFromID(R.id.location);
        String tags = extractStringFromID(R.id.tags);
        String capacity = extractStringFromID(R.id.capacity);
        if (foodType.length() == 0) giveToastError("foodType");
        else if (eventTitle.length() == 0) giveToastError("eventTitle");
        else if (location.length() == 0) giveToastError("location");
        else {
            eventInformation[0] = foodType;
            eventInformation[1] = eventTitle;
            eventInformation[2] = description;
            eventInformation[3] = location;
            eventInformation[4] = tags;

        eventInformation[5] = capacity;
        int[] times = new int[4];
        TimePicker startTimer = (TimePicker) findViewById(R.id.start_time);
        int startHour = startTimer.getCurrentHour();
        int startMinute = startTimer.getCurrentMinute();
        times[0] = startHour;
        times[1] = startMinute;
        TimePicker endTimer = (TimePicker) findViewById(R.id.end_time);
        int endHour = endTimer.getCurrentHour();
        int endMinute = endTimer.getCurrentMinute();
        times[2] = endHour;
        times[3] = endMinute;
        Intent submit = new Intent(this, EventConfirmation.class);
        Intent addEvent = new Intent(this, GetFoodList.class);
        Bundle event = new Bundle();
        event.putIntArray(EVENT_TIMES, times);
        event.putStringArray(EVENT_STRINGS, eventInformation);
        submit.putExtras(event);
        addEvent.putExtras(event);
        startActivity(submit);
    }
    }

}
