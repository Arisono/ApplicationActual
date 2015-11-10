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

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class NotesDetailActivity extends ActionBarActivity {
	public static final String EMPTY_STRING = "";
	private File note;
	private File newNote;
    private Context context;
    private EditText noteTitle;
    private HighlightingEditor content;
    @SuppressWarnings({ "unused"})
	private ScrollView scrollView;
    private ViewGroup keyboardBarView;
    
    private String targetDirectory;//目标路径
    private boolean isPreviewIncoming = false;
	
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
	   setupKeyboardBar();
	   setupAppearancePreferences();
	        
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		saveNote();
	    if (isPreviewIncoming) {
	            finish();
	    }
		super.onPause();
	}
	
	private String readNote() {
        java.net.URI oldUri = note.toURI();
        return WriteilySingleton.getInstance().readFileUri(Uri.parse(oldUri.toString()), this);
    }
	
	private void saveNote(){
		   try {
	            String content = this.content.getText().toString();
	            String filename = normalizeFilename(content, noteTitle.getText().toString());
	            if (filename == null) return;

	            String parent = targetDirectory != null ? targetDirectory : note.getParent();
	            newNote = new File(parent, filename + Constants.MD_EXT);
	            FileOutputStream fos = new FileOutputStream(newNote);
	            OutputStreamWriter writer = new OutputStreamWriter(fos);

	            writer.write(content);
	            writer.flush();

	            writer.close();
	            fos.close();
	            // If we have created a new note due to renaming, delete the old copy
	            if (note != null && !newNote.getName().equals(note.getName()) && newNote.exists()) {
	                //重命名文件，会覆盖原笔记，特别危险
	            	note.delete();
	            }
	            updateWidgets();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	 private void shareNote() {
	        saveNote();

	        String shareContent = content.getText().toString();

	        Intent shareIntent = new Intent();
	        shareIntent.setAction(Intent.ACTION_SEND);
	        shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
	        shareIntent.setType("text/plain");
	        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_string)));
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
	                shareNote();
	                return true;
	            case R.id.action_preview:
	                previewNote();
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);

	        }
	    }
	    
	    private void previewNote() {
	        saveNote();
	        Intent intent = new Intent(this, PreviewActivity.class);

	        // .replace is a workaround for Markdown lists requiring two \n characters
	        if (note != null) {
	            Uri uriBase = WriteilySingleton.getInstance().getUriFromFile(note.getParentFile());
	            intent.putExtra(Constants.MD_PREVIEW_BASE, uriBase.toString());
	        }

	        intent.putExtra(Constants.NOTE_KEY,newNote);
	        intent.putExtra(Constants.MD_PREVIEW_KEY, content.getText().toString().replace("\n-", "\n\n-"));

	        isPreviewIncoming = true;
	        startActivity(intent);
	    }
	    
	    private void setupAppearancePreferences() {
	        String theme = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_theme_key), EMPTY_STRING);
	        String fontType = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_font_choice_key), EMPTY_STRING);
	        String fontSize = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_font_size_key), EMPTY_STRING);

	        if (!fontSize.equals(EMPTY_STRING)) {
	            content.setTextSize(TypedValue.COMPLEX_UNIT_SP, Float.parseFloat(fontSize));
	        }

	        if (!fontType.equals(EMPTY_STRING)) {
	            content.setTypeface(Typeface.create(fontType, Typeface.NORMAL));
	        }

	        if (theme.equals(getString(R.string.theme_dark))) {
	            content.setBackgroundColor(getResources().getColor(R.color.dark_grey));
	            content.setTextColor(getResources().getColor(android.R.color.white));
	            keyboardBarView.setBackgroundColor(getResources().getColor(R.color.grey));
	        } else {
	            content.setBackgroundColor(getResources().getColor(android.R.color.white));
	            content.setTextColor(getResources().getColor(R.color.dark_grey));
	            keyboardBarView.setBackgroundColor(getResources().getColor(R.color.lighter_grey));
	        }
	    }
	    
	    
	    
	    private void setupKeyboardBar() {
	        boolean showShortcuts = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.pref_show_markdown_shortcuts_key), true);

	        if (showShortcuts && keyboardBarView.getChildCount() == 0) {
	            appendRegularShortcuts();
	            if(isSmartShortcutsActivated()) {
	                appendSmartBracketShortcuts();
	            } else {
	                appendRegularBracketShortcuts();
	            }
	        } else if (!showShortcuts) {
	            findViewById(R.id.keyboard_bar_scroll).setVisibility(View.GONE);
	        }
	    }
	    
	    private boolean isSmartShortcutsActivated() {
	        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.pref_smart_shortcuts_key),false);
	    }
	    
	    private void appendRegularShortcuts() {
	        for (String shortcut : Constants.KEYBOARD_SHORTCUTS) {
	            appendButton(shortcut, new KeyboardBarListener());
	        }
	    }

	    private void appendRegularBracketShortcuts() {
	        for (String shortcut : Constants.KEYBOARD_SHORTCUTS_BRACKETS) {
	            appendButton(shortcut, new KeyboardBarListener());
	        }

	    }

	    private void appendSmartBracketShortcuts() {
	        for (String shortcut : Constants.KEYBOARD_SMART_SHORTCUTS) {
	            appendButton(shortcut, new KeyboardBarSmartShortCutListener());
	        }
	    }
	    //添加底部快捷字符
	    private void appendButton(String shortcut, View.OnClickListener l) {
	        TextView shortcutButton = (TextView) getLayoutInflater().inflate(R.layout.keyboard_shortcut, null);
	        shortcutButton.setText(shortcut);
	        shortcutButton.setOnClickListener(l);

	        String theme = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_theme_key), EMPTY_STRING);

	        if (theme.equals(getString(R.string.theme_dark))) {
	            shortcutButton.setTextColor(getResources().getColor(android.R.color.white));
	        } else {
	            shortcutButton.setTextColor(getResources().getColor(R.color.grey));
	        }

	        keyboardBarView.addView(shortcutButton);
	    }
	    
	    private class KeyboardBarListener implements View.OnClickListener {
	        @Override
	        public void onClick(View v) {
	            CharSequence shortcut = ((TextView) v).getText();
	            content.getText().insert(content.getSelectionStart(), shortcut);
	        }
	    }
	    
	    private class KeyboardBarSmartShortCutListener implements View.OnClickListener {
	        @Override
	        public void onClick(View v) {
	            CharSequence shortcut = ((TextView) v).getText();
	            if(content.hasSelection()) {
	                CharSequence selected = content.getText().subSequence(content.getSelectionStart(),
	                        content.getSelectionEnd());
	                content.getText().replace(content.getSelectionStart(), content.getSelectionEnd(),
	                        Character.toString(shortcut.charAt(0)) + selected + shortcut.charAt(1));
	            } else {
	                content.getText().insert(content.getSelectionStart(), shortcut);
	                content.setSelection(content.getSelectionStart() - 1);
	            }
	        }
	    }

}
