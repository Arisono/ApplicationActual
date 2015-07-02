package com.app.demo.activity;

import com.app.demo.R;
import com.app.demo.base.BaseComplexActivity;
import com.app.demo.view.CustomProgressDialog;
import com.app.demo.view.StretchScrollView;
import com.app.demo.view.StretchScrollView.OnHeaderRefreshListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ScrollViewRefreshActivity extends BaseComplexActivity  implements OnHeaderRefreshListener{

	private CustomProgressDialog progressDialog;
	@ViewInject(R.id.pb_refresh_top)
	private ProgressBar refresh;
	@ViewInject(R.id.personal_background_image)
	private ImageView mBackgroundImageView = null;
	@ViewInject(R.id.personal_scrollView)
	private StretchScrollView mScrollView = null;
	
	private Handler mhandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				refresh.setVisibility(View.GONE);
				progressDialog.dismiss();
				break;
			default:
				break;
			}
		};
	};

	@Override
	public void onHeaderRefresh(StretchScrollView view) {
		refresh.setVisibility(View.VISIBLE);
		progressDialog.show();
		startTestThread(mhandler, 1, 5000);
	}
	
	
	@Override
	public void initView() {
		setContentView(R.layout.scroll_demo_stretch);
		ViewUtils.inject(this);
		TAG="ScrollViewRefresh";
		progressDialog=CustomProgressDialog.createDialog(this);;
		progressDialog.setTitile("正在拼命加载！");
		mScrollView.setImageView(mBackgroundImageView);
		mScrollView.setOnHeaderRefreshListener(this);		
	}
	
	@Override
	public void initData() {
		System.out.println(TAG+":initData()");
	}
	

}
