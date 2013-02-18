package javapadawan.android;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ITAMCO.MTConnect.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ITAMCO.MTConnect.R;



public class AccountDetail extends Activity implements OnClickListener {
	public static final String PREFS_NAME = "MyPrefsFile";
	
	//String Value = "http://www.androidster.com/android_news.rss";
	//String Value;
	private MyDatabaseAdapter myDatabaseAdapter;
	private long selectedRow;
	private TextView tvId;
	private EditText etServerName;
	EditText etUserName;
	private EditText etPassword;
	private Button btnSave, btnCancel, btnCurrent, btnCamera;
	private Account account;
	
	
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_detail);
		myDatabaseAdapter = MyDatabaseAdapter.getInstance(this);
		initComponents();
	}

	private void initComponents() {
		selectedRow = getIntent().getLongExtra(
				AccountList.INTENT_EXTRA_SELECTED_ROW, 0);
		tvId = (TextView) findViewById(R.id.tvId);
		etServerName = (EditText) findViewById(R.id.etServerName);
		etUserName = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		
				
		account = new Account();
		account.setSQLiteDatabase(myDatabaseAdapter.getDatabase());
		Log.v(getClass().getSimpleName(), "selectedRow=" + selectedRow);
		account.setId((int) selectedRow);
		if (selectedRow > 0) {
			account.load(this);
		}
		Log.v(getClass().getSimpleName(), "account.getId()=" + account.getId());
		if (account.getId() > 0) {
			tvId.setText(account.getId() + "");
			etServerName.setText(account.getServerName());
			etUserName.setText(account.getUsername());
			etPassword.setText(account.getPassword());
		} else {
			tvId.setText("new");
		}
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);
		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(this);
		btnCurrent = (Button) findViewById(R.id.btnCurrent);
		btnCurrent.setOnClickListener(this);
		btnCamera = (Button) findViewById(R.id.btnCamera);
		btnCamera.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view == btnSave) {
			if (account == null) {
				account = new Account();
			}
			account.setPassword(etPassword.getText().toString());
			account.setServerName(etServerName.getText().toString());
			account.setUsername(etUserName.getText().toString());
			account.save();
			finish();
		}
		else if (view == btnCurrent) {
			//Bundle bundle = new Bundle();
			//bundle.putString("value", "http://www.androidster.com/android_news.rss");
			Intent i = new Intent(AccountDetail.this, MessageList.class);
			//value = etUserName.getText().toString();
			SharedPreferences myPreference = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor e = myPreference.edit();
			String Value = etUserName.getText().toString();
			e.putString("key", Value);
			e.commit();
			//String result = myPreference.getString("key","");
			//((Article) this.getApplication()).setSomeVariable(result);
			//AlertDialog alertDialog;
	        //alertDialog = new AlertDialog.Builder(this).create();
	        //alertDialog.setTitle("Connection Value");
	        //alertDialog.setMessage(result);
	        //alertDialog.show();
			//i.putExtras(bundle);
			startActivity(i);
		}
		else if (view == btnCamera) {
			String Camera = etPassword.getText().toString();
			Uri uri = Uri.parse(Camera);
			startActivity( new Intent( Intent.ACTION_VIEW, uri));
			
		}
		else if (view == btnCancel) {
			finish();
		}
	}
}