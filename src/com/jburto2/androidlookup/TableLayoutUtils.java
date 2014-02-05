/**
 * 
 */
package com.jburto2.androidlookup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author jburton
 *
 */
@SuppressLint("NewApi")
public class TableLayoutUtils 
{
	protected static TableRow createTableRow(Context context)
	{
		TableRow tableRow = new TableRow(context);
		///http://stackoverflow.com/questions/2481455/set-margins-in-a-linearlayout-programmatically
		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 0, 0, 0);
		tableRow.setLayoutParams(layoutParams);
		tableRow.setPadding(0, 0, 0, 0);
		
		return tableRow;
		
	}
	protected static View createHorizontalLine(Context context,int color)
	{
		View h_line = new View(context);
        h_line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        h_line.setBackgroundColor(color);
        h_line.setPadding(0, 0, 0, 0);
        return h_line;
	}
	
	protected static View createVerticalLine(Context context,int color)
	{
		View v_line = new View(context);
        v_line.setLayoutParams(new TableRow.LayoutParams(1,TableRow.LayoutParams.MATCH_PARENT));
        v_line.setBackgroundColor(color);
        v_line.setPadding(0, 0, 0, 0);
        return v_line;
	}
	
	protected static TextView createTextView(Context context,String message,int size,int textColor,int backgroundColor)
	{
		TextView textView = new TextView(context);
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setText(message);
        textView.setTextSize(size);
        textView.setBackgroundColor(backgroundColor);
        textView.setTextColor(textColor);
        textView.setPadding(5, 0, 5, 0);
        return textView;
	}

	protected static WebView createHtmlView(Context context,String link)
	{
	    WebView webview = new WebView(context);
	    webview.loadData(link, "text/html", null);
	    webview.setPadding(5, 0, 5, 0);
	    return webview;
	}
}
