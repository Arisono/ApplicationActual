package com.app.demo.activity;

import java.io.File;
import java.util.ArrayList;


















import com.app.demo.R;
import com.app.demo.adapter.NotesAdapter;
import com.app.demo.model.WriteilySingleton;
import com.app.demo.util.Constants;
import com.mobsandgeeks.adapters.Sectionizer;
import com.mobsandgeeks.adapters.SimpleSectionAdapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * @author :LiuJie 2015年6月8日 上午9:46:03
 * @注释:notes
 */
public class NotesFragment extends Fragment {
	
	 private Context context;
	 private View layoutView;
	 
	 private File rootDir;
	 private File currentDir;
	 /**@注释：单例文件操作类  */
	 private WriteilySingleton writeilySingleton;
	 private ListView filesListView;
	 private TextView hintTextView;
	 private NotesAdapter filesAdapter;
	 
	 private ArrayList<File> filesCurrentlyShown = new ArrayList<File>();
	 /**@注释： 第三方类库 */
	 private SimpleSectionAdapter<File> simpleSectionAdapter;
	 private Sectionizer<File> sectionizer = new Sectionizer<File>() {
	        @Override
	        public String getSectionTitleForItem(File instance) {
	            return instance.isDirectory() ? getString(R.string.folders) : getString(R.string.files);
	        }
	    };
    public NotesFragment() {
        super();
    }
	
	 
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 context = getActivity().getApplicationContext();
	     layoutView = inflater.inflate(R.layout.notes_fragment, container, false);
         hintTextView = (TextView) layoutView.findViewById(R.id.empty_hint);
         filesListView = (ListView) layoutView.findViewById(R.id.notes_listview);
         
         filesAdapter = new NotesAdapter(context, 0, filesCurrentlyShown);
         simpleSectionAdapter =
          new SimpleSectionAdapter<File> (context, filesAdapter, R.layout.notes_fragment_section_header, R.id.notes_fragment_section_text, sectionizer);
         
         filesListView.setAdapter(simpleSectionAdapter);
         /**@注释：获取根目录  */
         rootDir = getRootFolderFromPrefsOrDefault();
         return layoutView;
	}
	 
	 @Override
	public void onResume() {
		/**@注释：实例化单例类  */
	    writeilySingleton = WriteilySingleton.getInstance();
	    File possiblyNewRootDir = getRootFolderFromPrefsOrDefault();
	    if (possiblyNewRootDir != rootDir) {
            rootDir = possiblyNewRootDir;
            currentDir = possiblyNewRootDir;
        }
	    retrieveCurrentFolder();
        listFilesInDirectory(getCurrentDir());
		super.onResume();
	}
	 
	 @Override
	public void onPause() {
		saveCurrentFolder();
		super.onPause();
	}
	 
	 private void saveCurrentFolder() {
	        SharedPreferences pm = PreferenceManager.getDefaultSharedPreferences(context);
	        boolean isLastDirStored = pm.getBoolean(getString(R.string.pref_remember_directory_key), false);

	        if (isLastDirStored) {
	            String saveDir = (currentDir == null) ? rootDir.getAbsolutePath() : currentDir.getAbsolutePath();
	            pm.edit().putString(getString(R.string.pref_last_open_directory), saveDir).apply();
	        }

	        writeilySingleton.setNotesLastDirectory(currentDir);
	    }
	 
	 private File getRootFolderFromPrefsOrDefault() {
	        return new File(PreferenceManager.getDefaultSharedPreferences(this.getActivity()).getString(getString(R.string.pref_root_directory),Constants.DEFAULT_WRITEILY_STORAGE_FOLDER));
	    }
	 
	 private void retrieveCurrentFolder() {
	        SharedPreferences pm = PreferenceManager.getDefaultSharedPreferences(context);
	        boolean isLastDirStored = pm.getBoolean(getString(R.string.pref_remember_directory_key), false);
	        if (isLastDirStored) {
	            String rememberedDir = pm.getString(getString(R.string.pref_last_open_directory), null);
	            currentDir = (rememberedDir != null) ? new File(rememberedDir) : null;
	        }

	        // Two-fold check, in case user doesn't have the preference to remember directories enabled
	        // This code remembers last directory WITHIN the app (not leaving it)
	        if (currentDir == null) {
	            currentDir = (writeilySingleton.getNotesLastDirectory() != null) ? writeilySingleton.getNotesLastDirectory() : rootDir;
	        }
	    }
	 
	 /**
	 * @author LiuJie
	 * @功能:遍历
	 */
	 public void listFilesInDirectory(File directory) {
		    reloadFiles(directory);
	        broadcastDirectoryChange(directory, rootDir);
	        showEmptyDirHintIfEmpty();
	        reloadAdapter();
	 }
	 
	 private void reloadFiles(File directory) {
	        try {
	            // Load from SD card
	            filesCurrentlyShown = WriteilySingleton.getInstance().addFilesFromDirectory(directory, new ArrayList<File>());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 /**
	 * @author LiuJie
	 * @功能:广播
	 */
	 private void broadcastDirectoryChange(File directory, File rootDir) {
	        Intent broadcast = new Intent();
	        broadcast.setAction(Constants.CURRENT_FOLDER_CHANGED);
	        broadcast.putExtra(Constants.CURRENT_FOLDER, directory);
	        broadcast.putExtra(Constants.ROOT_DIR, rootDir);
	        getActivity().sendBroadcast(broadcast);
	        clearSearchFilter();
	    }
	 
	 private void showEmptyDirHintIfEmpty() {
	        if (writeilySingleton.isDirectoryEmpty(filesCurrentlyShown)) {
	            hintTextView.setVisibility(View.VISIBLE);
	            hintTextView.setText(getString(R.string.empty_directory));
	        } else {
	            hintTextView.setVisibility(View.INVISIBLE);
	        }
	    }
	 
	 public void clearSearchFilter() {
	        filesAdapter.getFilter().filter("");
	        simpleSectionAdapter.notifyDataSetChanged();
	        reloadAdapter();
	    }
	 
	 private void reloadAdapter() {
	        if (filesAdapter != null) {
	            filesAdapter = new NotesAdapter(context, 0, filesCurrentlyShown);
	            simpleSectionAdapter =
	                    new SimpleSectionAdapter<> (context, filesAdapter, R.layout.notes_fragment_section_header, R.id.notes_fragment_section_text, sectionizer);
	            filesListView.setAdapter(simpleSectionAdapter);
	            simpleSectionAdapter.notifyDataSetChanged();
	        }
	    }
	 
	 private class NotesItemClickListener implements android.widget.AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	 
	 /**
	 * @author LiuJie
	 * @功能:选择事件
	 */
	 private class ActionModeCallback implements ListView.MultiChoiceModeListener {

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.notes_context_menu, menu);
            mode.setTitle(getResources().getString(R.string.select_elements));
            return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			// TODO Auto-generated method stub
			
		}
	 }
	 
	 public File getCurrentDir() {
	        return (currentDir == null) ? getRootDir() : currentDir.getAbsoluteFile();
	    }
	 
	 public File getRootDir() {
	        return rootDir.getAbsoluteFile();
	    }
	 
	 /**@注释：查询  */
	 /** Search **/
	 public void search(CharSequence query) {
	        if (query.length() > 0) {
	            filesAdapter.getFilter().filter(query);
	            simpleSectionAdapter.notifyDataSetChanged();
	        }
	  }
}
