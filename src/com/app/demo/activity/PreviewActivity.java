package com.app.demo.activity;

import java.io.File;
import java.io.FileOutputStream;




import com.app.demo.R;
import com.app.demo.util.Constants;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

@SuppressWarnings("deprecation")
public class PreviewActivity extends ActionBarActivity {
	private WebView previewWebView;
    private String markdownRaw;
    private String markdownHtml;
    private String currentDir;
    private File note;
    private boolean isEditIncoming = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_note_preview);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	        if (toolbar != null) {
	            setSupportActionBar(toolbar);
	            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    }
	        
	    previewWebView = (WebView) findViewById(R.id.preview_webview);
	    previewWebView = (WebView) findViewById(R.id.preview_webview);
        markdownRaw = getIntent().getStringExtra(Constants.MD_PREVIEW_KEY);
        String baseFolder = getIntent().getStringExtra(Constants.MD_PREVIEW_BASE);
        note = (File) getIntent().getSerializableExtra(Constants.NOTE_KEY);

        setTitleFromNote(note);
        previewWebView.loadDataWithBaseURL(baseFolder, markdownRaw, "text/html", Constants.UTF_CHARSET, null);
	}
	
	
	  private void setTitleFromNote(File note) {
	        if (note != null) {
	            setTitle(this.note.getName());
	        } else {
	            setTitle(getResources().getString(R.string.preview));
	        }
	    }
	  

	    @Override
	    protected void onPause() {
	        if (isEditIncoming) {
	            finish();
	        }

	        super.onPause();
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.preview_menu, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	                super.onBackPressed();
	                return true;
	            case R.id.action_share_text:
	                shareText(markdownRaw, "text/plain");
	                return true;
	            case R.id.action_share_html:
	                shareText(markdownHtml, "text/html");
	                return true;
	            case R.id.action_share_html_source:
	                shareText(markdownHtml, "text/plain");
	                return true;
	            case R.id.action_share_image:
	                shareImage();
	                return true;
	            case R.id.action_edit:
	                editNote();
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    
	    
	    private void shareText(String text, String type) {
	        Intent shareIntent = new Intent();
	        shareIntent.setAction(Intent.ACTION_SEND);
	        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
	        shareIntent.setType(type);
	        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_string)));
	    }
	    
	    
	    private Bitmap getBitmapFromWebView(WebView webView) {
	        try {
	            float scale = 1.0f / getResources().getDisplayMetrics().density;
	            Picture picture = webView.capturePicture();
	            Bitmap bitmap = Bitmap.createBitmap((int) (picture.getWidth() * scale), (int) (picture.getHeight() * scale), Bitmap.Config.ARGB_8888);
	            Canvas canvas = new Canvas(bitmap);
	            canvas.scale(scale, scale);
	            picture.draw(canvas);
	            return bitmap;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    
	    private void shareImage() {
	        Bitmap bitmap = getBitmapFromWebView(previewWebView);
	        if (bitmap != null) {
	            File image = new File(getExternalCacheDir(), note.getName() + ".png");
	            if (saveBitmap(bitmap, image)) {
	                shareStream(Uri.fromFile(image), "image/png");
	            }
	        }
	    }
	    
	    private void shareStream(Uri uri, String type) {
	        Intent shareIntent = new Intent();
	        shareIntent.setAction(Intent.ACTION_SEND);
	        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
	        shareIntent.setType(type);
	        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_string)));
	    }
	    private boolean saveBitmap(Bitmap bitmap, File file) {
	        FileOutputStream stream = null;
	        try {
	            stream = new FileOutputStream(file);
	            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	        finally {
	            try {
	                if (stream != null) {
	                    stream.close();
	                }
	            } catch (Exception e) {
	            }
	        }
	    }
	    
	    private void editNote() {
	        isEditIncoming = true;
	        Intent intent = new Intent(this, NotesDetailActivity.class);
	        intent.putExtra(Constants.NOTE_KEY, note);
	        startActivity(intent);
	        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
	    }

}
