package com.app.demo.activity;

import java.io.File;
import java.util.ArrayList;




import java.util.List;






import com.app.demo.R;
import com.app.demo.adapter.NotesAdapter;
import com.app.demo.dialog.ConfirmDialog;
import com.app.demo.dialog.RenameDialog;
import com.app.demo.model.WriteilySingleton;
import com.app.demo.util.Constants;
import com.mobsandgeeks.adapters.Sectionizer;
import com.mobsandgeeks.adapters.SimpleSectionAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
	
	
	 public static final int RENAME_CONTEXT_BUTTON_ID = 103;
	 private Context context;
	 private View layoutView;
	 
	 private File rootDir;
	 private File currentDir;
	 private List<File> selectedItems = new ArrayList<File>();
	 
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
         
         //init Listener
         filesListView.setOnItemClickListener(new NotesItemClickListener());
         filesListView.setMultiChoiceModeListener(new ActionModeCallback());
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
	 
	
	 //核心功能，列表点击事件
	 private class NotesItemClickListener implements android.widget.AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int i,
				long id) {
              File file=(File) simpleSectionAdapter.getItem(i);
              if (file.isDirectory()) {
            	  currentDir = file;
                  listFilesInDirectory(file);
			  }else{
				  //文件
				  File note = (File) simpleSectionAdapter.getItem(i);
	              //是否先预览的偏好设置
				  boolean previewFirst = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.pref_preview_first_key), false);
	              Intent intent;
	              //偏好设置
	              if (previewFirst) {
					//预览
	            	  intent = new Intent(context, PreviewActivity.class);
	            	  // .replace is a workaround for Markdown lists requiring two \n characters
	                    if (note != null) {
	                        Uri uriBase = null;
	                        if (note.getParentFile() != null) {
	                            uriBase = Uri.parse(note.getParentFile().toURI().toString());
	                        }

	                        intent.putExtra(Constants.MD_PREVIEW_BASE, uriBase.toString());
	                    }

	                    Uri noteUri = Uri.parse(note.toURI().toString());
	                    String content = WriteilySingleton.getInstance().readFileUri(noteUri, context);
	                    intent.putExtra(Constants.MD_PREVIEW_KEY, content.replace("\n-", "\n\n-"));
				  }else{
					  //进入编辑区域
					  intent = new Intent(context, NotesDetailActivity.class);
				  }
	              
	              intent.putExtra(Constants.NOTE_KEY, note);
	              startActivity(intent);
	              getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
			  }
		}
		 
	 }
	 
	 private ActionMode actionMode;
	 
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
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			  actionMode = mode;
	            switch (item.getItemId()) {
	                case R.id.context_menu_delete:
	                    confirmDelete();
	                    return true;
	                case R.id.context_menu_move:
	                   // promptForDirectory();
	                    return true;
	                case RENAME_CONTEXT_BUTTON_ID:
	                	System.out.println("file name:"+selectedItems.get(0).getName());
	                	System.out.println("file path:"+selectedItems.get(0).getAbsolutePath());
	                    promptForNewName(selectedItems.get(0));
	                    return true;
	                default:
	                    return false;
	            }
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			
		}
        //多选模式   颜色值为item设置的背景图颜色为准，不设置，会没有颜色
		@Override
		public void onItemCheckedStateChanged(ActionMode  actionMode, int i,
				long id, boolean checked) {
			switch (filesListView.getCheckedItemCount()) {
            case 0:
                actionMode.setSubtitle(null);
                //
                manageClickedVIew(i, checked);
                hideRenameButton(actionMode);
                break;
            case 1:
                actionMode.setSubtitle(getResources().getString(R.string.one_item_selected));
                manageClickedVIew(i, checked);
                showRenameButton(actionMode);
                break;
            default:
                manageClickedVIew(i, checked);
                actionMode.setSubtitle(String.format(getResources().getString(R.string.more_items_selected), filesListView.getCheckedItemCount()));
                hideRenameButton(actionMode);
                break;
        }		
		}
	 }
	 
	 private void promptForNewName(File renameable) {
         FragmentManager fragManager = getFragmentManager();
         Bundle args = new Bundle();
         args.putString(Constants.SOURCE_FILE, renameable.getAbsolutePath());
         RenameDialog renameDialog = new RenameDialog();
         renameDialog.setArguments(args);
         renameDialog.show(fragManager, Constants.RENAME_DIALOG_TAG);
     }
	 
	 private void confirmDelete() {
	        FragmentManager fragManager = getFragmentManager();
	        ConfirmDialog confirmDialog = new ConfirmDialog();
	        confirmDialog.show(fragManager, Constants.CONFIRM_DELETE_DIALOG_TAG);
	    }
	 
	 public void finishActionMode() {
	        actionMode.finish();
	        //不加这句话，重命名文件夹会有严重的bug
	        selectedItems.clear();
	    }
	 
	 public List<File> getSelectedItems(){
	        return selectedItems;
	    }
	 
	  private void manageClickedVIew(int i, boolean checked) {
          if(checked) { selectedItems.add((File) simpleSectionAdapter.getItem(i));}
          else { selectedItems.remove((File) simpleSectionAdapter.getItem(i));}
      }

     private void hideRenameButton(ActionMode actionMode) {
         showRenameContextButton(actionMode.getMenu(), false);
     }

     private void showRenameButton(ActionMode actionMode) {
         showRenameContextButton(actionMode.getMenu(), true);
     }
	 
	 private void showRenameContextButton(Menu menu, boolean show) {
         if (show) {
             menu.add(Menu.FIRST+1, RENAME_CONTEXT_BUTTON_ID,Menu.FIRST,R.string.rename)
                     .setIcon(R.drawable.ic_edit_light);

         } else {
             menu.setGroupVisible(1, false);
             menu.removeItem(RENAME_CONTEXT_BUTTON_ID);
         };
     }
	 
	 public File getCurrentDir() {
	        return (currentDir == null) ? getRootDir() : currentDir.getAbsoluteFile();
	    }
	 
	 public File getRootDir() {
	        return rootDir.getAbsoluteFile();
	    }
	 
	 public void goToPreviousDir() {
	        if (currentDir != null) {
	            currentDir = currentDir.getParentFile();
	        }

	        listFilesInDirectory(getCurrentDir());
	    }
	 //是否是根目录
	 public boolean onRooDir() {
	        return writeilySingleton.isRootDir(currentDir, rootDir);
	    }
	 
	 /**@注释：查询  */
	 /** Search **/
	 public void search(CharSequence query) {
	        if (query.length() > 0) {
	            filesAdapter.getFilter().filter(query);
	            simpleSectionAdapter.notifyDataSetChanged();
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
	 
	 
}
