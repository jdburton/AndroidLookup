/**
 * 
 */
package com.jburto2.androidlookup;

import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * @author jburton
 *
 */
public class DisplayLookupActivity extends Activity {
	
	@SuppressLint("NewApi")
	@Override
	/**
	 * @fn onCreate(Bundle savedInstanceState)
	 * @brief Called when the activity is created. 
	 * 
	 * @param savedInstanceState The instance state  
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_lookup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get the IP address
        // Get the cName
        // IP address = IP field
        // cname = cname field
        // aliases = dynamic table
        
        // Get the message from the intent
        Intent intent = getIntent();
        String lookupName = intent.getStringExtra(MainActivity.LOOKUP_NAME);
        String ipAddresses = intent.getStringExtra(MainActivity.IP_ADDRESSES);
        String cname = intent.getStringExtra(MainActivity.CNAME);
        String ping = intent.getStringExtra(MainActivity.PING_RESULTS);

        /// Create the table layout
        /// From: http://developer.android.com/reference/android/widget/TableLayout.html
        /// More information at: http://stackoverflow.com/questions/18207470/adding-table-rows-dynamically-in-android
        /// How to separate lines in TableLayout http://stackoverflow.com/questions/5092116/how-can-i-add-separating-lines-between-my-tablerows-that-are-created-programmati
   
        // Table layout
        //TableLayout tableLayout = new TableLayout(this);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tlGridTable);
        tableLayout.setBaselineAligned(false);
        tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_BEGINNING | TableLayout.SHOW_DIVIDER_END | TableLayout.SHOW_DIVIDER_MIDDLE);
        tableLayout.setDividerPadding(0);
        //int index = 0;
        
        //Horizontal Line
        View h_line = TableLayoutUtils.createHorizontalLine(this,Color.rgb(51, 51, 51));
        tableLayout.addView(h_line);
        
        // First row: Entered data
        TableRow tableRow = TableLayoutUtils.createTableRow(this);
        
        
        // Create label       
        TextView textView = TableLayoutUtils.createTextView(this, "Hostname", 15, Color.rgb(255, 255, 255), Color.rgb(196, 0, 0));
        tableRow.addView(textView);

        // Data
        textView = TableLayoutUtils.createTextView(this, lookupName, 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(textView);
        
        // Vertical line
        View v_line = TableLayoutUtils.createVerticalLine(this, Color.rgb(51,51,51));
        tableRow.addView(v_line);
 
        tableLayout.addView(tableRow);
        
        //Horizontal Line
        h_line = TableLayoutUtils.createHorizontalLine(this,Color.rgb(51, 51, 51));
        tableLayout.addView(h_line);
        
        //Second row: IP Addresses
        tableRow = TableLayoutUtils.createTableRow(this);
        
        // Create label       
        textView = TableLayoutUtils.createTextView(this, "IP Addresses", 15, Color.rgb(255, 255, 255), Color.rgb(0, 196, 0));
        tableRow.addView(textView);

        
        // Data        
        StringTokenizer strtok = new StringTokenizer(ipAddresses,";"); 
        
        while (strtok.hasMoreTokens())
        {

        	textView = TableLayoutUtils.createTextView(this, strtok.nextToken(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(textView);
            v_line = TableLayoutUtils.createVerticalLine(this, Color.rgb(51,51,51));
            tableRow.addView(v_line);
          
        }
        tableLayout.addView(tableRow);
        
        //Horizontal Line
        h_line = TableLayoutUtils.createHorizontalLine(this,Color.rgb(51, 51, 51));
        tableLayout.addView(h_line);
              
        // Third Row: Canonical Name
        tableRow = TableLayoutUtils.createTableRow(this);
        
        // Create label       
        textView = TableLayoutUtils.createTextView(this, "CNAME", 15, Color.rgb(255, 255, 255), Color.rgb(0, 0, 196));
        tableRow.addView(textView);

        // Data
        textView = TableLayoutUtils.createTextView(this, cname, 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(textView);
        
        // Vertical line
        v_line = TableLayoutUtils.createVerticalLine(this, Color.rgb(51,51,51));
        tableRow.addView(v_line);
        
        tableLayout.addView(tableRow);
        
        //Horizontal Line
        h_line = TableLayoutUtils.createHorizontalLine(this,Color.rgb(51, 51, 51));
        tableLayout.addView(h_line);
        
        // Fourth Row: Alive?
        tableRow = TableLayoutUtils.createTableRow(this);
        
        // Create label       
        textView = TableLayoutUtils.createTextView(this, "Accessible?", 15, Color.rgb(255, 255, 255), Color.rgb(51,51,51));
        tableRow.addView(textView);

        // Data
        int background = 0;
        
        // Green if up, red if down.
        if (ping.equalsIgnoreCase("Yes"))
        {
        	background = Color.rgb(0, 196, 0);
        }
        else
        {
        	background = Color.rgb(196, 0, 0);
        }
        		
        textView = TableLayoutUtils.createTextView(this, ping, 15, Color.rgb(255,255,255),background);
        tableRow.addView(textView);
        
        // Vertical line
        v_line = TableLayoutUtils.createVerticalLine(this, Color.rgb(51,51,51));
        tableRow.addView(v_line);
        
        tableLayout.addView(tableRow);
        
        //Horizontal Line
        h_line = TableLayoutUtils.createHorizontalLine(this,Color.rgb(51, 51, 51));
        tableLayout.addView(h_line);
        
        //* Can't get this to work. Maybe later.
        // Fifth Row: Link
        tableRow = TableLayoutUtils.createTableRow(this);
        
        // Create label
        textView = TableLayoutUtils.createTextView(this, "Web:", 15, Color.rgb(51, 51, 51), Color.rgb(255,255,255));
        tableRow.addView(textView);
        

        
        /// Webviews from http://developer.android.com/reference/android/webkit/WebView.html
        // Create webview with link.
        String link = "<html><body><a href=\"http://"+lookupName+"\">"+lookupName+"</a> </body></html>";
        WebView webview = TableLayoutUtils.createHtmlView(this, link);
        
        tableRow.addView(webview);
        
        // Vertical line
        v_line = TableLayoutUtils.createVerticalLine(this, Color.rgb(51,51,51));
        tableRow.addView(v_line);
               
        
        tableLayout.addView(tableRow);
        
        //Horizontal Line
        h_line = TableLayoutUtils.createHorizontalLine(this,Color.rgb(51, 51, 51));
        tableLayout.addView(h_line);

        
        
              
	}
	

	
	

	/**
	 * @fn private void setupActionBar()
	 * 
	 * Set up the {@link android.app.ActionBar}, if the API is available.
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

	@Override
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
