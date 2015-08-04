package com.app.demo.activity;



import java.io.File;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;



import com.app.demo.R;
import com.app.demo.model.WriteilySingleton;
import com.app.demo.util.Constants;
import com.app.demo.view.HighlightingEditor;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

@SuppressWarnings("deprecation")
public class NotesDetailActivity extends ActionBarActivity {
	
	private File note;
    private Context context;
    private EditText noteTitle;
    private HighlightingEditor content;
    private ScrollView scrollView;
    private ViewGroup keyboardBarView;
    
    private String targetDirectory;//目标路径
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_detail);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        context=getApplicationContext();
        /**@注释：init id view  */
        content = (HighlightingEditor)findViewById(R.id.note_content);
        noteTitle = (EditText) findViewById(R.id.edit_note_title);
        scrollView = (ScrollView) findViewById(R.id.note_scrollview);
        keyboardBarView = (ViewGroup) findViewById(R.id.keyboard_bar);
        /**@注释：end id view  */
        Intent receivingIntent = getIntent();
        targetDirectory = receivingIntent.getStringExtra(Constants.TARGET_DIR);

        String intentAction = receivingIntent.getAction();
        String type = receivingIntent.getType();
        /**@注释：init intent */
        if (Intent.ACTION_SEND.equals(intentAction) && type != null) {
            openFromSendAction(receivingIntent);
        } else if (Intent.ACTION_EDIT.equals(intentAction) && type != null) {
            openFromEditAction(receivingIntent);
        } else {
            note = (File) getIntent().getSerializableExtra(Constants.NOTE_KEY);
        }

        if (note != null) {
            content.setText(readNote());
            noteTitle.setText(note.getName().replaceAll("((?i)\\.md$)", ""));
        }
	}
	
	
	@Override
	protected void onResume() {
//	   setupKeyboardBar();
//	   setupAppearancePreferences();
	        
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	private String readNote() {
        java.net.URI oldUri = note.toURI();
        return WriteilySingleton.getInstance().readFileUri(Uri.parse(oldUri.toString()), this);
    }
	
	private void save(){
		   try {
	            String content = this.content.getText().toString();
	            String filename = normalizeFilename(content, noteTitle.getText().toString());
	            if (filename == null) return;

	            String parent = targetDirectory != null ? targetDirectory : note.getParent();
	            File newNote = new File(parent, filename + Constants.MD_EXT);
	            FileOutputStream fos = new FileOutputStream(newNote);
	            OutputStreamWriter writer = new OutputStreamWriter(fos);

	            writer.write(content);
	            writer.flush();

	            writer.close();
	            fos.close();
	            // If we have created a new note due to renaming, delete the old copy
	            if (note != null && !newNote.getName().equals(note.getName()) && newNote.exists()) {
	                note.delete();
	            }
	            updateWidgets();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	  private void updateWidgets() {
//	        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//	        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(
//	                new ComponentName(context, WriteilyWidgetProvider.class));
//	        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_notes_list);
	    }

	private String normalizeFilename(String content, String title) {
        String filename = title;
        if (filename.length() == 0) {
            if (content.length() == 0) {
                return null;
            } else {
                if (content.length() < Constants.MAX_TITLE_LENGTH) {
                    filename = content.substring(0, content.length());
                } else {
                    filename = content.substring(0, Constants.MAX_TITLE_LENGTH);
                }
            }
        }
        filename = filename.replaceAll("[\\\\/:\"*?<>|]+", "").trim();

        if(filename.isEmpty()) {
            filename = "Writeily - " + String.valueOf(UUID.randomUUID().getMostSignificantBits()).substring(0,6);
        }
        return filename;
    }
	
	 private void openFromSendAction(Intent receivingIntent) {
	        Uri fileUri = receivingIntent.getParcelableExtra(Intent.EXTRA_STREAM);
	        readFileUriFromIntent(fileUri);
	    }

	    private void openFromEditAction(Intent receivingIntent) {
	        Uri fileUri = receivingIntent.getData();
	        readFileUriFromIntent(fileUri);
	    }

	    private void readFileUriFromIntent(Uri fileUri) {
	        if (fileUri != null) {
	            note = WriteilySingleton.getInstance().getFileFromUri(fileUri);
	            content.setText(WriteilySingleton.getInstance().readFileUri(fileUri, this));
	        }
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.note_menu, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	                super.onBackPressed();
	                overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
	                return true;
	            case R.id.action_share:
	                //shareNote();
	                return true;
	            case R.id.action_preview:
	               // previewNote();
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
}
