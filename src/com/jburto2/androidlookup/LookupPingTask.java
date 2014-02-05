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
 * @class LookupAddressTask 
 * @brief This class is an LookupTask that looks up a hostname given an IP address and an IP address if given a hostname.
 * Adapted from http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
 * 
 */
public class LookupPingTask extends LookupTask
{
	ProgressDialog progressDialog;
	
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
