/**
 * 
 */
package com.jburto2.androidlookup;


import android.os.AsyncTask;


/**
 * @author James Burton
 * 
 * @class LookupTask 
 * @brief This class is an AsyncTask that looks up something. 
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
	 * @brief This function uses java.net.InetAddress to lookup either the hostname or the ipaddress.
	 * 
	 *  
	 * 
	 * @param urls Array of strings that are arguments to the function. url[0] is the address to lookup.
	 * @return String that represents either the IP address or the hostname if call succeeded.
	 * @return null If the call failed. 
	 * @exception Exception generated from InetAddress call is stored in the public instance variable exception.
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
    
    public Exception getException()
    {
    	return exception;
    }
    
    public String getExceptionMsg()
    {
    	return exception.getMessage();
    }



}
