/**
 * 
 */
package com.jburto2.androidlookup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;


/**
 * @author James Burton
 * 
 * @class LookupAddressTask 
 * @brief This class is an LookupTask that looks up all IP addresses for a if given a hostname.
 * 
 */
public class LookupAddressTask extends LookupTask
{

	
	
	/**
	 * @fn protected String doInBackground(String... urls)
	 * 
	 * @brief This function uses java.net.InetAddress to lookup all the ipAddresses for a given host.
	 * 
	 * 
	 * 
	 * @param urls Array of strings that are arguments to the function. url[0] is the address to lookup.
	 * @return String that represents all the IP addresses for the host with ';' delimiter.
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

    		InetAddress ipAddresses[] = InetAddress.getAllByName(url);
    		StringBuffer strbuf = new StringBuffer("");
    		
    		for(int i = 0; i < ipAddresses.length; i++ )
    		{
    			
    			strbuf.append(ipAddresses[i].getHostAddress()+";");
    		}
			return strbuf.toString();
    		
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
	protected static boolean isIP(String input) throws UnknownHostException
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
