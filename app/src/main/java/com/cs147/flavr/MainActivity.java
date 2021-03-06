package com.cs147.flavr;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import net.java.jddac.common.type.ArgMap;
import net.java.jddac.util.StringUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {
    // Hashmaps to associate events with categories and dietary preferences.
    public static HashMap<String, List<ArgMap>> categories = new HashMap<String, List<ArgMap>>(30);
    public static HashMap<String, List<ArgMap>> dietChoices = new HashMap<String, List<ArgMap>>(10);
    // List of all events
    public static List<ArgMap> events;
    //List of events user has created.
    public static List<ArgMap> userEvents = new ArrayList<ArgMap>(30);
    //Lists of dietary preferences and food category options
    public static List<String> dietPrefs = Arrays.asList("Kosher", "Vegetarian", "Vegan", "Gluten-free", "Peanut Allergy", "Lactose Intolerant");
    public static List<String> allCategories = Arrays.asList("Asian Cuisine", "Baked Foods", "BBQ Food", "Beverages", "Coffee or Tea", "Dessert", "Doughnuts", "Ethnic Food", "Fast Food",
            "Fish or Seafood", "Frozen Yogurt", "Ice Cream", "Meat", "Mexican Food", "Pancakes or Waffles", "Pizza", "Various Protein", "Sandwiches", "Snacks", "Soup or Salad");

    /* Set action bar font to system standard.
     */
    private void createCustomActionBar() {
        int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title","id","android");
        TextView actionBarTitleView = (TextView) getWindow().findViewById(actionBarTitle);
        Typeface alegreya = Typeface.createFromAsset(getAssets(),"fonts/alegreyasanssc_bold.ttf");
        actionBarTitleView.setTypeface(alegreya);
        getActionBar().setTitle("Flavr");
    }

    /* Start activity to allow user to see previously created events.
     */
    private void userEvents() {
        Intent events = new Intent(this, UserEvents.class);
        startActivity(events);
    }
    /*Reads in the hard-coded information through a CSV files using JDDAC library.
    */
    public List<ArgMap> readAndroidEmbeddedCSVFile() {
        InputStream is = getResources().openRawResource(R.raw.event_data);
        try {
            return StringUtil.readCSVStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ArgMap>();
        }
    }

    /* Go to form to give food
    */
    public void giveFood(View view) {
        Intent goToForm = new Intent(this, createEvent.class);
        startActivity(goToForm);
    }
    /* Go to list of food events.
    */
    public void getFood(View view) {
        Intent goToEventList = new Intent(this, GetFoodList.class);
        startActivity(goToEventList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCustomActionBar();
        setContentView(R.layout.activity_main);
        Typeface alegreya = Typeface.createFromAsset(getAssets(), "fonts/alegreyasanssc_bold.ttf");
        Button get = (Button) findViewById(R.id.get_food);
        get.setTypeface(alegreya);
        get.setTextColor(Color.WHITE);
        Button give = (Button) findViewById(R.id.give_food);
        give.setTypeface(alegreya);
        give.setTextColor(Color.WHITE);
        MainActivity.events = readAndroidEmbeddedCSVFile();
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

        switch (item.getItemId()) {
            case R.id.myevents1:
                userEvents();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
