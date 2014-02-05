package com.jburto2.androidlookup;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



public class MainActivity extends Activity {
	
	public final static String IP_ADDRESSES = "com.jburto2.androidlookup.IP_ADDRESSES";
	public final static String CNAME = "com.jburto2.androidlookup.CNAME";
	public final static String LOOKUP_NAME = "com.jburto2.androidlookup.LOOKUP_NAME";
	public final static String PING_RESULTS = "com.jburto2.androidlookup.PING_RESULTS";
	public final static String WHOIS_INFO = "com.jburto2.androidlookup.WHOIS_INFO";

	
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
    	
    	// pointer to lookupString
    	String lookupString = null;
    	
    	//Results from tasks
    	String ipAddressesArray = null;
    	String cNameString = null;
    	String pingString = null;
    	
    	/// About progress bars: http://stackoverflow.com/questions/11099012/android-spin-refresh-button-while-reloading
    	ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
    	
     	
    	if (urlText.getText().toString().equals(""))
        	{
    			//both are empty, display message    		
    			displayMessageDialog("Please enter either a hostname or IP address to lookup.","No Value");
        		return;
        	}
    	else
    		{
    		// ip text is empty. Look up name.
    		lookupString = urlText.getText().toString();
    		//resultText = ipText;
    		}
    	pb.setVisibility(View.VISIBLE);
    	LookupAddressTask lookupAddressTask = new LookupAddressTask();
    	ipAddressesArray = runLookupTask(lookupAddressTask,lookupString);
    	// if we can't find any ipAddresses, return now.
    	if (ipAddressesArray == null)
    	{
    		pb.setVisibility(View.GONE);
    		return;
    	}
    	
    	
    	LookupCNAMETask lookupCNAMETask = new LookupCNAMETask();
    	cNameString = runLookupTask(lookupCNAMETask,lookupString);

    	// if we can't find any ipAddresses, return now.
    	if (cNameString == null)
    	{
    		pb.setVisibility(View.GONE);
    		return;
    	}

    	LookupPingTask lookupPingTask = new LookupPingTask();
    	pingString = runLookupTask(lookupPingTask,lookupString);
    	
    	Intent intent = new Intent(this, DisplayLookupActivity.class);
    	
    	
    	//EditText editText = (EditText) findViewById(R.id.edit_message);
    	intent.putExtra(IP_ADDRESSES, ipAddressesArray );
    	intent.putExtra(CNAME, cNameString );
    	intent.putExtra(LOOKUP_NAME, lookupString);
    	intent.putExtra(PING_RESULTS, pingString);
    	pb.setVisibility(View.GONE);
    	startActivity(intent);
    	
    }	
	/**
	 * @fn public void whoisMessage(View view)
	 * @brief Gets the whois information for the host. This should be the registered response to the "whois" button.
	 * Note: The actual lookup is done on a separate thread.
	 * @param view
	 */
	
    public void whoisMessage(View view) 
    {
        // EditText object for url
    	EditText urlText = (EditText) findViewById(R.id.editHostName);
    	
    	// pointer to lookupString
    	String lookupString = null;
    	
    	//Results from tasks
    	String whoisInfoString = null;

    	
    	/// About progress bars: http://stackoverflow.com/questions/11099012/android-spin-refresh-button-while-reloading
    	ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
    	
     	
    	if (urlText.getText().toString().equals(""))
        	{
    			//both are empty, display message    		
    			displayMessageDialog("Please enter either a hostname or IP address to lookup.","No Value");
        		return;
        	}
    	else
    		{
    		lookupString = urlText.getText().toString();    		
    		}
    	pb.setVisibility(View.VISIBLE);
    	LookupWhoisTask lookupWhoisTask = new LookupWhoisTask();
    	whoisInfoString = runLookupTask(lookupWhoisTask,lookupString);
    	
    	if (whoisInfoString == null)
    	{
    		pb.setVisibility(View.GONE);
    		return;
    	}
    	
    	Intent intent = new Intent(this, DisplayWhoisActivity.class);
    	
    	
    	
    	intent.putExtra(WHOIS_INFO, whoisInfoString );

    	pb.setVisibility(View.GONE);
    	startActivity(intent);
    	
    }	
    	// Must run network on separate thread.
    private String runLookupTask(LookupTask lookupTask, String lookupString) 
    {
    	
    
		
		AsyncTask<String, Void, String> task = lookupTask.execute(lookupString);
    	String result = null;
		try 
		{
			// Get the results of the task.
			result = task.get();
			if (result == null)
			{
				// if we didn't get a result, grab the exception and display it as a toast.
				//Exception E = lookupTask.exception;
	    		String message = lookupTask.getExceptionMsg();// E.toString();
	    		String title = lookupTask.getException().toString();
				displayMessageDialog(message,title);
	    	}
			//else 
			//{
				// display the result in the appropriate field.
				//resultText.setText(result);
				//return result;
				
			//}
		
		}
		catch (Exception taskE)
		{
			// display the exception as a toast.
    		String message = taskE.getMessage();// E.toString();
    		String title = taskE.toString();
			displayMessageDialog(message,title);
		}
		return result;
    	
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
    
    /**
     * @fn public void displayMessageDialog(String message, String Title)
     * @brief Displays a message dialog to the user.
     * Displaying message dialogs from http://www.mkyong.com/android/android-alert-dialog-example/
     * @param message Message to display
     * @param title Title of dialog
     * 
     */
    
    public void displayMessageDialog(String message, String title)
    {
    	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
 
		// set title
		if (title == null)
		{
			title = "Message";
		}
		alertDialogBuilder.setTitle(title);
 
		// set dialog message
		alertDialogBuilder
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				  })
				;
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
    }
    
    
    
}
