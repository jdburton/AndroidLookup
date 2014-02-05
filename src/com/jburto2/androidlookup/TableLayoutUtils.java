/**
 * 
 */
package com.jburto2.androidlookup;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author jburton
 *
 */
public class TableLayoutUtils 
{
	protected static TableRow createTableRow(Context context)
	{
		TableRow tableRow = new TableRow(context);
		tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
		return tableRow;
		
	}
	protected static View createHorizontalLine(Context context,int color)
	{
		View h_line = new View(context);
        h_line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        h_line.setBackgroundColor(color);
        return h_line;
	}
	
	protected static View createVerticalLine(Context context,int color)
	{
		View v_line = new View(context);
        v_line.setLayoutParams(new TableRow.LayoutParams(1,TableRow.LayoutParams.MATCH_PARENT));
        v_line.setBackgroundColor(color);
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
        return textView;
	}

}
