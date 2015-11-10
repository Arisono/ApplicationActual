package com.app.demo.activity;

import java.io.File;

import com.app.demo.R;
import com.app.demo.broadcast.CurrentFolderChangedReceiver;
import com.app.demo.broadcast.RenameBroadcastReceiver;
import com.app.demo.dialog.CreateFolderDialog;
import com.app.demo.model.WriteilySingleton;
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
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {
	
	private Toolbar toolbar;
	private View frameLayout;
	//falg  back
	private boolean doubleBackToExitPressedOnce;
	
	private NotesFragment notesFragment;
	private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabCreateNote;
    private FloatingActionButton fabCreateFolder;
    
    private BroadcastReceiver browseToFolderBroadcastReceiver;
    private RenameBroadcastReceiver renameBroadcastReceiver;
    private BroadcastReceiver createFolderBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			 if (intent.getAction().equals(Constants.CREATE_FOLDER_DIALOG_TAG)) {
				 createFolder(new File(intent.getStringExtra(Constants.FOLDER_NAME)));
	              notesFragment.listFilesInDirectory(notesFragment.getCurrentDir());
			 }
		}
    };
    
    private BroadcastReceiver confirmBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.CONFIRM_DELETE_DIALOG_TAG)) {
                WriteilySingleton.getInstance().deleteSelectedItems(notesFragment.getSelectedItems());
                notesFragment.listFilesInDirectory(notesFragment.getCurrentDir());
                notesFragment.finishActionMode();
            }
            if (intent.getAction().equals(Constants.CONFIRM_OVERWRITE_DIALOG_TAG)) {
               // importFileToStorageDir(context, (File) intent.getSerializableExtra(Constants.SOURCE_FILE));
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
           // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        frameLayout = findViewById(R.id.frame);
        fabMenu = (FloatingActionsMenu) findViewById(R.id.fab);
        fabCreateNote = (FloatingActionButton) findViewById(R.id.create_note);
        fabCreateFolder = (FloatingActionButton) findViewById(R.id.create_folder);
        
        fabCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNote();
            }
        });
        
        fabCreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFolder();
            }
        });
        
        
        // Set up the fragments
        notesFragment = new NotesFragment();
        //注册文件夹改变广播
        browseToFolderBroadcastReceiver = new CurrentFolderChangedReceiver(this);
        renameBroadcastReceiver = new RenameBroadcastReceiver(notesFragment);
        // Load initial fragment
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.frame, notesFragment)
                .commit();
	    initFolders();
	}
    
	@Override
	protected void onResume() {
		//update theme
		setupAppearancePreferences();
		//注册广播
		//创建文件夹对话框后     广播刷新页面
		IntentFilter ifilterCreateFolderDialog = new IntentFilter();
        ifilterCreateFolderDialog.addAction(Constants.CREATE_FOLDER_DIALOG_TAG);
        registerReceiver(createFolderBroadcastReceiver, ifilterCreateFolderDialog);
        
        //创建文件夹路径改变    广播刷新界面
        IntentFilter ifilterSwitchedFolderFilder = new IntentFilter();
        ifilterSwitchedFolderFilder.addAction(Constants.CURRENT_FOLDER_CHANGED);
        registerReceiver(browseToFolderBroadcastReceiver, ifilterSwitchedFolderFilder);
        
        //删除
        IntentFilter ifilterConfirmDialog = new IntentFilter();
        ifilterConfirmDialog.addAction(Constants.CONFIRM_DELETE_DIALOG_TAG);
        ifilterConfirmDialog.addAction(Constants.CONFIRM_OVERWRITE_DIALOG_TAG);
        registerReceiver(confirmBroadcastReceiver, ifilterConfirmDialog);
        
        //，重命名广播
        IntentFilter ifilterRenameDialog = new IntentFilter();
        ifilterRenameDialog.addAction(Constants.RENAME_DIALOG_TAG);
        registerReceiver(renameBroadcastReceiver, ifilterRenameDialog);
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
   
    

    private void createNote() {
        Intent intent = new Intent(NotesActivity.this, NotesDetailActivity.class);
        intent.putExtra(Constants.TARGET_DIR, notesFragment.getCurrentDir().getAbsolutePath());
        startActivity(intent);
        /**@注释：设置动画  */
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        fabMenu.collapse();
    }

    
    
    private void showFolderNameDialog() {
        FragmentManager fragManager = getFragmentManager();

        Bundle args = new Bundle();
        args.putString(Constants.CURRENT_DIRECTORY_DIALOG_KEY, notesFragment.getCurrentDir().getAbsolutePath());

        CreateFolderDialog createFolderDialog = new CreateFolderDialog();
        createFolderDialog.setArguments(args);
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
        	showSettings();
            return true;
        } else if (item.getItemId() == R.id.action_import) {
            return true;
        }
        return false;

	 }
	 
	 //编辑器字体风格偏好设置
	 private void setupAppearancePreferences() {
	        String theme = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_theme_key), "");

	        if (theme.equals(getString(R.string.theme_dark))) {
	            frameLayout.setBackgroundColor(getResources().getColor(R.color.dark_grey));
	        } else {
	            frameLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
	        }
	    }

	    /**
	     * Show the SettingsFragment
	     */
	    private void showSettings() {
	        Intent settingsIntent = new Intent(this, SettingsActivity.class);
	        startActivity(settingsIntent);
	    }


	    
	    @Override
	    public void onBackPressed() {
	        if (doubleBackToExitPressedOnce) {
	            super.onBackPressed();
	            return;
	        }

	        if (!notesFragment.onRooDir()) {
	            notesFragment.goToPreviousDir();
	        } else {
	            this.doubleBackToExitPressedOnce = true;
	            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

	            new Handler().postDelayed(new Runnable() {

	                @Override
	                public void run() {
	                    doubleBackToExitPressedOnce = false;
	                }
	            }, 2000);
	        }
	    }

}
