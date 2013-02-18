package javapadawan.android;
import com.ITAMCO.MTConnect.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.app.Application;

import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MessageList extends ListActivity {
	public static final String PREFS_NAME = "MyPrefsFile";
	
	public String Value;
    String id;	
	//String Value = "http://www.androidster.com/android_news.rss";
		
	private List<Message> messages;
	private TextView tvSpindle;
	private TextView tvEmergencyStop;
	private TextView tvAlarm;
	private TextView tvPower;
	private TextView tvPathf;
	private TextView tvPathp;
	private TextView tvBlock;
	private TextView tvControl;
	private TextView tvLine;
	private TextView tvProgram;
	private TextView tvExecution;
	private TextView tvXpos;
	private TextView tvYpos;
	private TextView tvZpos;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        //Bundle bundle = this.getIntent().getExtras();
        //value = bundle.getString("value");
        //AlertDialog alertDialog;
        //alertDialog = new AlertDialog.Builder(this).create();
        //alertDialog.setTitle("Connection Value");
        //alertDialog.setMessage(value);
        //alertDialog.show();
        //SharedPreferences myPreference = getSharedPreferences(PREFS_NAME,0);
        //String result = myPreference.getString("key",""); 
        //SharedPreferences.Editor editor = myPreference.edit();
        //editor.putString("key", result);
        //editor.commit();
        //Value = ((Article) this.getApplication()).getSomeVariable();
        //AlertDialog alertDialog;
        //alertDialog = new AlertDialog.Builder(this).create();
        //alertDialog.setTitle("Connection Value");
        //alertDialog.setMessage(Value);
        //alertDialog.show();        
        //Article.createYourSpecialIntent(getIntent()).putExtra("key", Value);        
        loadFeed();
    }
  
       
	private void loadFeed(){
    	try{
    		BaseFeedParser parser = new BaseFeedParser(this);    		
    		messages = parser.parse();	    	
	    	List<String> titles = new ArrayList<String>(messages.size());
	    	for (Message msg : messages){
	    		//titles.add(msg.getTitle());	    			
	    		tvSpindle = (TextView) findViewById(R.id.tvSpindle);
	    		tvSpindle.setText(msg.getTitle());
	    		tvEmergencyStop = (TextView) findViewById(R.id.tvEmergencyStop);
	    		tvEmergencyStop.setText(msg.getEstop());
	    		tvAlarm = (TextView) findViewById(R.id.tvAlarm);
	    		tvAlarm.setText(msg.getAlarm());
	    		tvPower = (TextView) findViewById(R.id.tvPower);
	    		tvPower.setText(msg.getPower());
	    		tvPathf = (TextView) findViewById(R.id.tvPathf);
	    		tvPathf.setText(msg.getPathf());
	    		tvPathp = (TextView) findViewById(R.id.tvPathp);
	    		tvPathp.setText(msg.getPathp());
	    		tvBlock = (TextView) findViewById(R.id.tvBlock);
	    		tvBlock.setText(msg.getBlock());
	    		tvControl = (TextView) findViewById(R.id.tvControl);
	    		tvControl.setText(msg.getControl());
	    		tvLine = (TextView) findViewById(R.id.tvLine);
	    		tvLine.setText(msg.getLine());
	    		tvProgram = (TextView) findViewById(R.id.tvProgram);
	    		tvProgram.setText(msg.getProgram());
	    		tvExecution = (TextView) findViewById(R.id.tvExecution);
	    		tvExecution.setText(msg.getExecution());
	    		tvXpos = (TextView) findViewById(R.id.tvXpos);
	    		tvXpos.setText(msg.getXpos());
	    		tvYpos = (TextView) findViewById(R.id.tvYpos);
	    		tvYpos.setText(msg.getYpos());
	    		tvZpos = (TextView) findViewById(R.id.tvZpos);
	    		tvZpos.setText(msg.getZpos());
	    	}
	    	ArrayAdapter<String> adapter = 
	    		new ArrayAdapter<String>(this, R.layout.row,titles);
	    	this.setListAdapter(adapter);
    	} catch (Throwable t){
    		Log.e("AndroidNews",t.getMessage(),t);
    	}
    }
    
}