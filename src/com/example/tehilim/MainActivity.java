package com.example.tehilim;

import java.io.InputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
//import android.text.method.ScrollingMovementMethod;

public class MainActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		//Date d = new Date();
		//String dayOfTheWeek = sdf.format(d);

		
		int dayOfTheWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
	    //TextView textView = new TextView(this);
	    TextView textView = (TextView) findViewById(R.id.text_teh);
	    textView.setTextSize(16);
	    //textView.setMovementMethod(new ScrollingMovementMethod());
	    
	    Resources res = getResources();
	    String textToDisplay;
	    if (dayOfTheWeek==7)
	    {
	    	textToDisplay = res.getString(R.string.no_tehilim);
	    	textView.setText(textToDisplay);
	    }
	    
	    try
	    {
		    
		    InputStream in_s;
		    byte[] a;
		    byte[] b;
		    byte[] c;
		    if (dayOfTheWeek==1) in_s = res.openRawResource(R.raw.day1);
		    else if (dayOfTheWeek==2) in_s = res.openRawResource(R.raw.day2);
		    else if (dayOfTheWeek==3) in_s = res.openRawResource(R.raw.day3);
		    else if (dayOfTheWeek==4) in_s = res.openRawResource(R.raw.day4);
		    else if (dayOfTheWeek==5) in_s = res.openRawResource(R.raw.day5);
		    else if (dayOfTheWeek==6) in_s = res.openRawResource(R.raw.day6);
		    else 
		    {
		    	throw new Exception();
		    }
	        a = new byte[in_s.available()];
	        in_s.read(a);
	        
	        in_s = res.openRawResource(R.raw.all_days);
	        b = new byte[in_s.available()];
	        in_s.read(b);
	        
	        if (dayOfTheWeek==6) 
	        {
	        	in_s = res.openRawResource(R.raw.day6b);
		        c = new byte[in_s.available()];
		        in_s.read(c);
	        }
	        else
	        {
	        	c = new byte[1];
	        	c[0] = 0;
	        }
	        textToDisplay = new String(a).concat(new String(b)).concat(new String(c));
	        //textView.setText(dayOfTheWeek);
	    } catch(Exception e) {
	        // e.printStackTrace();
	    	textToDisplay = "Error: No day " + dayOfTheWeek;
	    }
	    textView.setText(textToDisplay);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
