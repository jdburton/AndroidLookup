/**
 * 
 */
package com.jburto2.androidlookup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import android.os.AsyncTask;


/**
 * @author James Burton
 * 
 * @class LookupAddressTask 
 * @brief This class is an AsyncTask that looks up a hostname given an IP address and an IP address if given a hostname.
 * Adapted from http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
 * 
 */
public class LookupCNAMETask extends AsyncTask<String, Void, String> 
{
	/**
	 * @var Exception exception
	 * @brief This holds any exception generated from the lookup call.
	 */
	
	public Exception  exception;
	
	
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
			return ipAddress.getHostName();
    	}
    	catch(UnknownHostException e)
    	{
    		this.exception = e;
        	return null;
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


    /**
     * @fn public static boolean isIP(String input) 
     * @brief Determine whether an address is an IPv4 address. 
     * 
     * @param input Address to check
     * @return true if an IP address.
     * @return false if not an IP address.
     */
	public static boolean isIP(String input) throws UnknownHostException
	{
		//break up the address into tokens
		StringTokenizer strtok = new StringTokenizer(input,".");
		while (strtok.hasMoreTokens())
		{
			// try to convert the value to a number.
			int value = 0;
			try
			{
				value = Integer.parseInt(strtok.nextToken());
				if (value < 0 && value > 255)
				{
					// if the number is < 0 or > 255, not an IP address.		
					return false;
					
				}
			}
			catch (NumberFormatException nfe) 
			{
				// not a number. Return false.
				return false;
			}

		}
		// if all tokens are valid numbers, then this is an IP address.
		return true;

	}
}
