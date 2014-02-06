/**
 * 
 */
package com.jburto2.androidlookup;


import android.os.AsyncTask;


/**
 * @author James Burton
 * 
 * @class LookupTask 
 * @brief This class is an AsyncTask that looks up something. The lookup functionality classes inherit from this.
 * Adapted from http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
 * 
 */
public abstract class LookupTask extends AsyncTask<String, Void, String> 
{
	/**
	 * @var Exception exception
	 * @brief This holds any exception generated from the lookup call.
	 */
	
	protected Exception  exception;
	
	
	/**
	 * @fn protected String doInBackground(String... urls)
	 * 
	 * @brief This function makes the function call in the background.
	 * 
	 *  
	 * 
	 * @param urls Array of strings that are arguments to the function. 
	 * @return String With the answer to the function call
	 * @return null If the call failed. 
	 * @exception Exception generated from InetAddress call is stored in the instance variable exception.
	 * 
	 */
	
    protected String doInBackground(String... urls) 
    {
    	return null;
    }
    
    /**
     * @fn protected void onPostExecute(String str)
     * 
     * @brief Method to be done after the task has executed.
     * @param str Some string 
     */

    protected void onPostExecute(String str) 
    {
        // TODO: check this.exception 
        // TODO: do something with the feed
    }
    
    /**
     * @fn public Exception getException()
     * @return exception
     */
    public Exception getException()
    {
    	return exception;
    }
    
    /**
     * @fn public String getExceptionMsg()
     * @return String of the exception message.
     */
    public String getExceptionMsg()
    {
    	return exception.getMessage();
    	
    }

    


}
