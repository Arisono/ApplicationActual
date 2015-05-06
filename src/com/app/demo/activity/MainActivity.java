package com.app.demo.activity;


import java.util.ArrayList;

import com.app.demo.R;
import com.app.demo.base.BaseAcivity;
import com.app.demo.base.BasePage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * @author xzjmyk
 * RadioGroup+ViewPager+View[] 
 * 
 */
public class MainActivity extends BaseAcivity {
	
	@ViewInject(R.id.rg_main_radio)
	private RadioGroup m_radiogroup;
	@ViewInject(R.id.vp_viewpager)
	private ViewPager m_viewpager;
	private HomePagerAdapter adapter;
	private ArrayList<BasePage> pages = new ArrayList<BasePage>();
	
//	private String TAG="MainActivity";
	private int curCheckId = R.id.rb_function;

	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#setView()
	 */
	@Override
	public void setView() {
		setContentView(R.layout.activity_mian_tab_radio);
		TAG="MainActivity";
		ViewUtils.inject(this);
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#initView()
	 */
	@Override
	public void initView() {
       /** init data */
		if (pages.isEmpty()) {
			pages.add(new HomePage(this));
			pages.add(new MusicPage(this));
			pages.add(new NewPage(this));
			pages.add(new NotePage(this));
			pages.add(new UserPage(this));
		}
		if (adapter==null) {
			adapter=new HomePagerAdapter(this, pages);
			m_viewpager.setAdapter(adapter);
		} else {  
           
		} 
		
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#setListener()
	 */
	@Override
	public void setListener() {
		
		m_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				BasePage page=pages.get(position);
				 switch (position) {
				 case 0:
					m_radiogroup.check(R.id.rb_function);
					break;
				 case 1:
					 m_radiogroup.check(R.id.rb_news_center);
					break;
				 case 2:
					 m_radiogroup.check(R.id.rb_smart_service);
					break;
				 case 3:
					 m_radiogroup.check(R.id.rb_gov_affairs);
					break;
				 case 4:
					 m_radiogroup.check(R.id.rb_setting);
					break;

				default:
					break;
				}
			
				if (!page.isInitDataSuccess) {
					 page.onResume();
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0){
				// TODO Auto-generated method stub 
			}
		});
		
	    m_radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_function:
					m_viewpager.setCurrentItem(0,false);
					break;
				case R.id.rb_gov_affairs:
					m_viewpager.setCurrentItem(3,false);
					break;
				case R.id.rb_news_center:
					m_viewpager.setCurrentItem(1,false);
					break;
				case R.id.rb_setting:
					m_viewpager.setCurrentItem(4,false);
					break;
				case R.id.rb_smart_service:
					m_viewpager.setCurrentItem(2,false);
					break;

				default:
					break;
				}
				curCheckId=checkedId;
			}
		});	
	       
	       pages.get(0).initData();
	       m_radiogroup.check(curCheckId);
   
	}
	
	
	class HomePagerAdapter extends PagerAdapter {
//		private Context mContext;
		private ArrayList<BasePage> pages;
		public HomePagerAdapter(Context ct, ArrayList<BasePage> pages) {
//			this.mContext = ct;
			this.pages = pages;
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg1 == arg0;
		}
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(pages.get(position)
					.getContentView());
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(pages.get(arg1).getContentView(), 0);
			return pages.get(arg1).getContentView();
		}

	}


}
