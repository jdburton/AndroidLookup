/**
 * 
 */
package com.jburto2.androidlookup;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author James Burton
 * 
 * @class LookupCNAMETask 
 * @brief This class is an LookupTask that uses java.net.InetAddress to looks up the Canonical Name of a host.
 * 
 */
public class LookupCNAMETask extends LookupTask
{

	
	/**
	 * @fn protected String doInBackground(String... urls)
	 * 
	 * @brief This function uses java.net.InetAddress to lookup the Canonical Name (CNAME) of a server
	 * @brief This takes either a hostname an ipaddress.
	 * 
	 * @param urls Array of strings that are arguments to the function. url[0] is the address to lookup.
	 * @return String that represents the CNAME if call succeeded.
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
    		return ipAddress.getCanonicalHostName();
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

}
