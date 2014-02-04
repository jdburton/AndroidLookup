package com.jburto2.androidlookup;

import com.jburto2.androidlookup.DisplayInfoActivity;
import com.jburto2.androidlookup.LookupAddressTask;
import com.jburto2.androidlookup.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;



public class MainActivity extends Activity {

	
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		// Menu from http://developer.android.com/guide/topics/ui/menus.html#options-menu
	    switch (item.getItemId()) {
	   
	    case R.id.action_about:
	    	Intent intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);

	    }
	    return true;
	}

	/**
	 *  @fn public void clearIPAddressMessage(View view)
	 *  @brief Clear the fields. This should be the registered response to the "clear" button for the IP Address.
	 *  Learned how to clear a field from http://stackoverflow.com/questions/8758635/how-to-clear-the-edittext-when-onclick-on-button
	 * @param view
	 */

	public void clearIPAddressMessage(View view) {
    	EditText ipText = (EditText) findViewById(R.id.editIPAddress);
    	ipText.setText("");

    
    }
	
	/**
	 * @fn void clearHostNameMessage(View view)
	 * @brief Clear the fields. This should be the registered response to the "clear" button for the Hostname button.
	 * Learned how to clear a field from http://stackoverflow.com/questions/8758635/how-to-clear-the-edittext-when-onclick-on-button
	 * @param view
	 */
	public void clearHostNameMessage(View view) {
		EditText urlText = (EditText) findViewById(R.id.editHostName);
		urlText.setText("");
		
    
    }

	/**
	 * @fn public void lookupMessage(View view)
	 * @brief Looks up the url or the IP address. This should be the registered response to the "lookup" button.
	 * Note: The actual lookup is done on a separate thread.
	 * @param view
	 */
	
    public void lookupMessage(View view) 
    {
        // EditText object for url
    	EditText urlText = (EditText) findViewById(R.id.editHostName);
    	
    	// EditText object for ip address
    	EditText ipText = (EditText) findViewById(R.id.editIPAddress);
    	
    	// pointer to lookupString
    	String lookupString = null;
    	
    	// pointer to output field for results.
    	EditText resultText = null;
    	
    	// See which field is empty to figure out what we're looking for.
    	if (ipText.getText().toString().equals(""))
    	{
    		if (urlText.getText().toString().equals(""))
        	{
    			//both are empty, display message
    			displayToast("Please enter either a hostname or IP address to lookup.");
        		return;
        	}
    		else
    		{
    		// ip text is empty. Look up name.
    		lookupString = urlText.getText().toString();
    		resultText = ipText;
    		}
    	}
    	else if (urlText.getText().toString().equals(""))
    	{
    		// urlText is empty, look up ip address
    		lookupString = ipText.getText().toString();
    		resultText = urlText;
    		
    	}
    	else 
    	{
    		// both fields full. Display error and return.
    		displayToast("Leave field empty to lookup value.");
    		return;
    	}
    	
    	// Must run network on separate thread.
		LookupAddressTask lookupTask = new LookupAddressTask();
		AsyncTask<String, Void, String> task = lookupTask.execute(lookupString);
    	
		try 
		{
			// Get the results of the task.
			String result = task.get();
			if (result == null)
			{
				// if we didn't get a result, grab the exception and display it as a toast.
				Exception E = lookupTask.exception;
	    		String error = E.toString();
				displayToast(error);
	    	}
			else 
			{
				// display the result in the appropriate field.
				resultText.setText(result);
			}
		
		}
		catch (Exception taskE)
		{
			// display the exception as a toast.
			displayToast(taskE.toString());
		}
    	
    }
    /**
     * @fn public void displayToast(String message)
     * @brief Displays a popup "Toast" message to the user.
     * Displaying toasts from http://developer.android.com/guide/topics/ui/notifiers/toasts.html
     * @param message Message to display
     */
    public void displayToast(String message)
    {
    	Context context = this.getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
    }
}
