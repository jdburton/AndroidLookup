/**
 * 
 */
package com.jburto2.androidlookup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.ProgressDialog;


/**
 * @author James Burton
 * 
 * @class LookupPingTask 
 * @brief This class is an LookupTask that uses java.net.InetAddress to see if a host is rechable or not.
 * 
 * 
 */
public class LookupPingTask extends LookupTask
{
	ProgressDialog progressDialog;
	
	/**
	 * @fn protected String doInBackground(String... urls)
	 * 
	 * @brief This function uses java.net.InetAddress to determine if the host is reachable.
	 * 
	 * @param urls Array of strings that are arguments to the function. url[0] is the address to lookup.
	 * @return "Yes" if host is reachable.
	 * @return "No" if host is not reachable or if an exception is generated. 
	 * @exception Exception generated from InetAddress call is stored in the public instance variable exception.
	 * 
	 */
	
    protected String doInBackground(String... urls) 
    {
    	String url = urls[0];
    	
    	try 
    	{
    		///Found NSLookup from http://www.coderanch.com/t/328875/java/java/nslookup-Java
    		///More on inet addresses from http://download.java.net/jdk7/archive/b123/docs/api/java/net/InetAddress.html 
    		InetAddress ipAddress = InetAddress.getByName(url);
			//return ipAddress.getHostName();
    		if (ipAddress.isReachable(3000))
    		{
    			return "Yes";
    		}
    		else
    		{
    			return "No";
    		}
    	}
    	catch(UnknownHostException e)
    	{
    		this.exception = e;
        	return "No";
        } 
    	catch(IOException io)
    	{
    		this.exception = io;
    		return "No";
    	}
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

}
