package javapadawan.android;
import com.ITAMCO.MTConnect.*;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.ITAMCO.MTConnect.R;

public class AccountList extends ListActivity {
	

	/** Called when the activity is first created. */
	private MyDatabaseAdapter myDatabaseAdapter;

	private static final int INTENT_NEXT_SCREEN = 0;
	public static final String INTENT_EXTRA_SELECTED_ROW = "SELECTED_ROW";

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int EXIT_ID = DELETE_ID + 1;
	private Account account = new Account();
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_list);
		myDatabaseAdapter = MyDatabaseAdapter.getInstance(this);
		intent = new Intent(this, AccountDetail.class);
		initComponents();
	}

	private void initComponents() {
		account.setSQLiteDatabase(myDatabaseAdapter.getDatabase());
		Cursor recordsCursor = account.retrieveAll();
		startManagingCursor(recordsCursor);
		String[] from = new String[] { Account.COL_SERVER_NAME };
		int[] to = new int[] { R.id.tfServerName };
		SimpleCursorAdapter records = new SimpleCursorAdapter(this,
				R.layout.account_row, recordsCursor, from, to);
		setListAdapter(records);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createRecord();
			return true;
		case DELETE_ID:
			account.setId((int) getListView().getSelectedItemId());
			account.delete();
			initComponents();
			return true;
		case EXIT_ID:
			finish();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void createRecord() {
		intent.putExtra(INTENT_EXTRA_SELECTED_ROW, 0);
		startActivityForResult(intent, INTENT_NEXT_SCREEN);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.v(getClass().getSimpleName(), "id=" + id);
		intent.putExtra(INTENT_EXTRA_SELECTED_ROW, id);
		startActivityForResult(intent, INTENT_NEXT_SCREEN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.btnAdd);
		menu.add(0, DELETE_ID, 0, R.string.btnDelete);
		menu.add(0, EXIT_ID, 0, R.string.btnExit);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		initComponents();
	}
}