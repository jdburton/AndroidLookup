/**
 * 
 */
package com.jburto2.androidlookup;

import java.net.InetAddress;


import android.os.AsyncTask;
import android.text.TextUtils;


/**
 * @author jburton
 *
 */
public class LookupAddressTask extends AsyncTask<String, Void, String> 
{
	public Exception  exception;
	// Adapted from http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
    protected String doInBackground(String... urls) 
    {
    	String url = urls[0];
    	
    	try 
    	{
    		// Found NSLookup from http://www.coderanch.com/t/328875/java/java/nslookup-Java
	    	// More on inet addresses from http://download.java.net/jdk7/archive/b123/docs/api/java/net/InetAddress.html
    		
    		if (isIP(url))
    		{
    			InetAddress ipAddress = InetAddress.getByName(url);
				return ipAddress.getHostName();
    		}
    		else
    		{
			 	InetAddress ipAddress = InetAddress.getByName(url);
				return ipAddress.getHostAddress();
    		}
    		
    	}
    	catch(Exception e)
    	{
    		this.exception = e;
        	return null;
        } 
    }

    protected void onPostExecute(String str) 
    {
        // TODO: check this.exception 
        // TODO: do something with the feed
    }



    // Determine whether an address is an ip address. A bit naive, though.ja
    // http://stackoverflow.com/questions/11723184/checking-if-string-is-web-address-or-ip-on-android
	public static boolean isIP(String input) 
	{
	
	    if (input.contains(".") && input.length()>1) 
	    {
	        return TextUtils.isDigitsOnly( input.replace(".", "").trim() );
	    }
	    else 
	    {
	        return false;
	    }
	}
}