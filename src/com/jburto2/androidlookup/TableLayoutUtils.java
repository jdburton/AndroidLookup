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
 * @class TableLayoutUtils
 * 
 * @brief A series of static methods that create widgets for a table layout.
 *
 */
@SuppressLint("NewApi")
public class TableLayoutUtils 
{
	/**
	 * @fn protected static TableRow createTableRow(Context context)
	 * Creates a table row with no margins and no padding that will expand to the entire length of the parent TableLayout.
	 * @param context
	 * @return Created TableRow.
	 */
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
	/**
	 * @fn protected static View createHorizontalLine(Context context,int color)
	 * @brief Creates a horizontal line of height 1 that goes across the parent.
	 * @param context 
	 * @param color Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created horizontal line View Object.
	 */
	protected static View createHorizontalLine(Context context,int color)
	{
		/// Creating lines http://stackoverflow.com/questions/5092116/how-can-i-add-separating-lines-between-my-tablerows-that-are-created-programmati
		View h_line = new View(context);
        h_line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        h_line.setBackgroundColor(color);
        h_line.setPadding(0, 0, 0, 0);
        return h_line;
	}
	/**
	 * @fn protected static View createVerticalLine(Context context,int color)
	 * @brief Creates a vertical line of width 1 that goes across the parent.
	 * @param context 
	 * @param color Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created vertical line View Object.
	 */
	
	protected static View createVerticalLine(Context context,int color)
	{
		/// Creating lines http://stackoverflow.com/questions/5092116/how-can-i-add-separating-lines-between-my-tablerows-that-are-created-programmati
		View v_line = new View(context);
        v_line.setLayoutParams(new TableRow.LayoutParams(1,TableRow.LayoutParams.MATCH_PARENT));
        v_line.setBackgroundColor(color);
        v_line.setPadding(0, 0, 0, 0);
        return v_line;
	}
	/**
	 * @fn protected static TextView createTextView(Context context,String message,int size,int textColor,int backgroundColor)
	 * @brief Creates a textview object.
	 * @param context 
	 * @param message Message to be displayed in the text view
	 * @param size Text size
	 * @param textColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @param backgroundColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created TextView Object.
	 */
	
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
	/**
	 * @fn protected static WebView createHtmlView(Context context,String html)
	 * @brief This creates a new WebView object to display the HTML code in string.
	 * @param context
	 * @param html HTML code to be displayed. This is a string of the code itself, not a link to a file.
	 * @return Created WebView object.
	 */

	protected static WebView createHtmlView(Context context,String html)
	{
	    WebView webview = new WebView(context);
	    webview.loadData(html, "text/html", null);
	    webview.setPadding(5, 0, 5, 0);
	    return webview;
	}
}
