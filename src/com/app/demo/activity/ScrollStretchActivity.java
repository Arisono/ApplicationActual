package com.app.demo.activity;

import com.app.demo.R;
import com.app.demo.view.StretchScrollView;
import com.app.demo.view.StretchScrollView.OnHeaderRefreshListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class ScrollStretchActivity extends Activity  implements OnHeaderRefreshListener{

	private ImageView mBackgroundImageView = null;
	private StretchScrollView mScrollView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_demo_stretch);
		mBackgroundImageView = (ImageView) findViewById(R.id.personal_background_image);
		mScrollView = (StretchScrollView) findViewById(R.id.personal_scrollView);
		mScrollView.setImageView(mBackgroundImageView);
		mScrollView.setOnHeaderRefreshListener(this);
	}
	@Override
	public void onHeaderRefresh(StretchScrollView view) {
		Toast.makeText(getApplicationContext(), "Ë¢ÐÂ", 1).show();
	}

}
