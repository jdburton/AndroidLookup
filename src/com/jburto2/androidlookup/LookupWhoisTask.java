/**
 * 
 */
package com.jburto2.androidlookup;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.whois.WhoisClient;

/**
 * @author James Burton
 * 
 * @class LookupAddressTask 
 * @brief This class is an LookupTask that looks up a hostname given an IP address and an IP address if given a hostname.
 * Adapted from http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
 * 
 */
public class LookupWhoisTask extends LookupTask
{

	
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
    	
    	/// from http://www.mkyong.com/java/java-whois-example/
    	
    	StringBuilder result = new StringBuilder("");
    	 
		WhoisClient whois = new WhoisClient();
		try 
		{
 
			//default is internic.net
			whois.connect(WhoisClient.DEFAULT_HOST);
			String whoisData1 = whois.query("=" + url);
			result.append(whoisData1);
			whois.disconnect();
 
		} 
		catch (SocketException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
 
		return result.toString();
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
