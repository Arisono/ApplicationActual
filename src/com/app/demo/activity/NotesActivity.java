package com.app.demo.activity;



import java.io.File;












import com.app.demo.R;
import com.app.demo.dialog.CreateFolderDialog;
import com.app.demo.util.Constants;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class NotesActivity extends ActionBarActivity {
	
	private Toolbar toolbar;
	private View frameLayout;
	private NotesFragment notesFragment;
	private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabCreateNote;
    private FloatingActionButton fabCreateFolder;
    /**@注释：广播  */
    private BroadcastReceiver createFolderBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			/**@注释：接收广播  */
			 if (intent.getAction().equals(Constants.CREATE_FOLDER_DIALOG_TAG)) {
				 createFolder(new File(intent.getStringExtra(Constants.FOLDER_NAME)));
	              notesFragment.listFilesInDirectory(notesFragment.getCurrentDir());
			 }
		}
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_note_main_list);
		initView();
	
	}
	
	public void initView(){
		toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        frameLayout = findViewById(R.id.frame);
        /**@注释：悬浮按钮  */
        fabMenu = (FloatingActionsMenu) findViewById(R.id.fab);
        fabCreateNote = (FloatingActionButton) findViewById(R.id.create_note);
        fabCreateFolder = (FloatingActionButton) findViewById(R.id.create_folder);
        fabCreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFolder();
            }
        });
        
        
        // Set up the fragments
        notesFragment = new NotesFragment();
     // Load initial fragment
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.frame, notesFragment)
                .commit();
	    initFolders();
	}
    
	@Override
	protected void onResume() {
		
		IntentFilter ifilterCreateFolderDialog = new IntentFilter();
        ifilterCreateFolderDialog.addAction(Constants.CREATE_FOLDER_DIALOG_TAG);
        registerReceiver(createFolderBroadcastReceiver, ifilterCreateFolderDialog);
        
		super.onResume();
	}
	
	  /**
     * Create folders, if they don't already exist.
     */
    private void initFolders() {
        File defaultWriteilyFolder = new File(Constants.DEFAULT_WRITEILY_STORAGE_FOLDER);
        createFolder(defaultWriteilyFolder);
    }

    /**
     * Creates the specified folder if it doesn't already exist.
     *
     * @param folder
     * @return
     */
    private boolean createFolder(File folder) {
        boolean success = false;

        if (!folder.exists()) {
            success = folder.mkdir();
        }

        return success;
    }
	
    private void createFolder() {
        showFolderNameDialog();
        fabMenu.collapse();
    }
     /**
	 * @author LiuJie
	 * @功能:对话框
	 */
    private void showFolderNameDialog() {
        FragmentManager fragManager = getFragmentManager();

        Bundle args = new Bundle();
        args.putString(Constants.CURRENT_DIRECTORY_DIALOG_KEY, notesFragment.getCurrentDir().getAbsolutePath());

        CreateFolderDialog createFolderDialog = new CreateFolderDialog();
        createFolderDialog.setArguments(args);
        /**@注释：后面参数是tag  */
        createFolderDialog.show(fragManager, Constants.CREATE_FOLDER_DIALOG_TAG);
    }
	
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
       getMenuInflater().inflate(R.menu.main, menu);
       final MenuItem searchItem = menu.findItem(R.id.action_search);
       final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

       SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
       searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
       if (searchView != null) {
           searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String query) {
                   if (query != null) {
                       if (notesFragment.isVisible())
                           notesFragment.search(query);
                   }
                   return false;
               }

               @Override
               public boolean onQueryTextChange(String newText) {
                   if (newText != null) {
                       if (notesFragment.isVisible()) {
                           if (newText.equalsIgnoreCase("")) {
                               notesFragment.clearSearchFilter();
                           } else {
                               notesFragment.search(newText);
                           }
                       }
                   }
                   return false;
               }
           });

           searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View v, boolean hasFocus) {

                   menu.findItem(R.id.action_import).setVisible(false);
                   menu.findItem(R.id.action_settings).setVisible(false);

                   if (!hasFocus) {
                       menu.findItem(R.id.action_import).setVisible(true);
                       menu.findItem(R.id.action_settings).setVisible(true);
                       searchItem.collapseActionView();
                       searchView.setQuery("", false);
                   }
               }
           });

           searchView.setQueryHint(getString(R.string.search_hint));
       }

	   return true;
	}
	
	 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_settings) {

            return true;
        } else if (item.getItemId() == R.id.action_import) {
            return true;
        }
        return false;

	 }

}
