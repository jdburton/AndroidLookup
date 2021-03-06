/**
 * 
 */
package com.jburto2.androidlookup;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;




/**
 * @author jburton
 * @class DisplayWhoisActivity
 * 
 * @brief This class sets up the activity that displays the whois info.
 * 
 * How to install apache commons from http://stackoverflow.com/questions/2331803/how-to-use-org-apache-commons-lang-namespace-in-java
 * How to add to build path from http://stackoverflow.com/questions/1334802/how-can-i-use-external-jars-in-an-android-project
 *
 */
public class DisplayWhoisActivity extends Activity {
	
	@SuppressLint("NewApi")
	@Override
	/**
	 * @fn onCreate(Bundle savedInstanceState)
	 * @brief Called when the activity is created. 
	 * 
	 * When the DisplayWhois is created, this method enables the home/up button and sets the view to the activity_display_whois. 
	 * The activity_display_whois view contains an TextView that is the child of a HorizontalScrollView which is the child of a ScrollView to support scrolling.
	 * The output from the whois task is displayed on the TextView
	 * 
	 * @param savedInstanceState The instance state  
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_whois);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }


        
        // Get the message from the intent
        Intent intent = getIntent();
   
        String whoisInfo = intent.getStringExtra(MainActivity.WHOIS_INFO);
        
        // Get and set the text view.
        TextView textView = (TextView) findViewById(R.id.whoisTextView);
        textView.setTextSize(15);
        textView.setText(whoisInfo);



	}
	

	
	
	/**
	 * @fn private void setupActionBar()
	 * 
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 * This enables the up/home button to allow users to return to the main screen.
	 * 
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	/**
	 * @fn public boolean onCreateOptionsMenu(Menu menu) 
	 * @brief Inflate the menu; this adds items to the action bar if it is present.
	 * @param menu The menu
	 * @return true
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * @fn onOptionsItemSelected(MenuItem item) 
	 * This ID represents the Home or Up button. In the case of this
	 * activity, the Up button is shown. Use NavUtils to allow users
	 * to navigate up one level in the application structure. For
	 * more details, see the Navigation pattern on Android Design:
	 * 
	 * http://developer.android.com/design/patterns/navigation.html#up-vs-back	
	 * 
	 * @param item The MenuItem
	 * @return If Home or Up, navigate up and return true.
	 * @return Info, show the Info view. 
	 * @return Otherwise, parent class functionality.
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true;
	    case R.id.action_about:
	    	Intent intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}



}
