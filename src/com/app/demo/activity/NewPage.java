/**
 * 
 */
package com.app.demo.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.app.demo.R;
import com.app.demo.base.BasePage;
import com.app.demo.fragment.ViewPagerTabListViewFragment;
import com.app.demo.model.NewsChannel;
import com.app.demo.net.ResquestManager;
import com.app.demo.util.ApplicationUtil;
import com.app.demo.view.SlidingTabLayout;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.github.ksoichiro.android.observablescrollview.Scrollable;
import com.lidroid.xutils.ViewUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;


/**
 * @author LiuJie
 *
 */
public class NewPage extends BasePage  implements ObservableScrollViewCallbacks{
	
	private String TAG="NewPage";
	private View mHeaderView;
	private View mToolbarView;
	private int mBaseTranslationY;
	private ViewPager mPager;
	private NavigationAdapter mPagerAdapter;
	public NewPage(Context context) {
		super(context);
	}

	
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.view_radio_new_list, null);
		ViewUtils.inject(this, view);
		//init Toolbar
		appCompatActivity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
		mHeaderView = view.findViewById(R.id.header);
	    ViewCompat.setElevation(mHeaderView, ct.getResources().getDimension(R.dimen.toolbar_elevation));
		mToolbarView=view.findViewById(R.id.toolbar);
		mPagerAdapter=new NavigationAdapter(appCompatActivity.getSupportFragmentManager());
		mPager = (ViewPager) view.findViewById(R.id.pager);
	    mPager.setAdapter(mPagerAdapter);
		
	     
	    SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(ct.getResources().getColor(R.color.accent));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);
        
        slidingTabLayout.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				propagateToolbarState(toolbarIsShown());
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        propagateToolbarState(toolbarIsShown());
		return view;
	}

	
	
	
	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initData()
	 */
	@Override
	public void initData() {
		Log.i(TAG, "数据初始化成功！");
		isInitDataSuccess=true;
		ResquestManager.addRequest(
				new StringRequest(Method.POST, "http://route.showapi.com/109-34", 
						responseListener(), errorListener()){
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						Map<String, String> params=new HashMap<String, String>();
						params.put("showapi_appid", "12041");
						params.put("showapi_sign", "67f7892db890407f95cdf39f870b1234");
						params.put("showapi_timestamp", ApplicationUtil.getCurrentTime("yyyyMMddHHmmss"));
						return params;
					}
				}, this);
	}

	
	
	 private boolean toolbarIsShown() {
	        return ViewHelper.getTranslationY(mHeaderView) == 0;
	    }
	
	
	
	
	/**
	 * @author :LiuJie 2015年11月10日 上午9:13:51
	 * @注释:结构监听
	 */
	private Response.Listener<String> responseListener(){
		return new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i(TAG, response);
				NewsChannel cEntity=JSON.parseObject(response, NewsChannel.class);
				Log.i(TAG, "chanel list:"+cEntity.getShowapi_res_body().getChannelList().size());
			}
		};
	}
	
	protected Response.ErrorListener errorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(ct, error.getMessage(), Toast.LENGTH_LONG).show();
			}
		};
	}


	@Override
	public void onScrollChanged(int scrollY, boolean firstScroll,
			boolean dragging) {
		Log.i(TAG, "OnScrollChanged()");
		Toast.makeText(ct, "onScrollChanged", 2000).show();
		 if (dragging) {
	            int toolbarHeight = mToolbarView.getHeight();
	            float currentHeaderTranslationY = ViewHelper.getTranslationY(mHeaderView);
	            if (firstScroll) {
	                if (-toolbarHeight < currentHeaderTranslationY) {
	                    mBaseTranslationY = scrollY;
	                }
	            }
	            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
	            ViewPropertyAnimator.animate(mHeaderView).cancel();
	            ViewHelper.setTranslationY(mHeaderView, headerTranslationY);
	        }		
	}


	@Override
	public void onDownMotionEvent() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onUpOrCancelMotionEvent(ScrollState scrollState) {
		 mBaseTranslationY = 0;

	        Fragment fragment = getCurrentFragment();
	        if (fragment == null) {
	            return;
	        }
	        View view = fragment.getView();
	        if (view == null) {
	            return;
	        }

	        // ObservableXxxViews have same API
	        // but currently they don't have any common interfaces.
	        adjustToolbar(scrollState, view);		
	}
	
	/**@注释：CacheFragmentStatePagerAdapter  */
	private static class NavigationAdapter extends CacheFragmentStatePagerAdapter{

	    private static final String[] TITLES = new String[]{"国内聚焦", "Butter Cookie", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop"};

        private int mScrollY;

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setScrollY(int scrollY) {
            mScrollY = scrollY;
        }
		@Override
		protected Fragment createItem(int position) {
			 Fragment f ;
			 f=new ViewPagerTabListViewFragment();
			 if (0 < mScrollY) {
                 Bundle args = new Bundle();
                 args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
                 f.setArguments(args);
             }
			/* final int pattern=position%4;
			 switch (pattern) {
			case 0:
				 f=new ViewPagerTabListViewFragment();
				 if (0 < mScrollY) {
	                 Bundle args = new Bundle();
	                 args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
	                 f.setArguments(args);
	             }
				break;
			case 1:
				 f=new ViewPagerTabListViewFragment();
				 if (0 < mScrollY) {
	                 Bundle args = new Bundle();
	                 args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
	                 f.setArguments(args);
	             }
             break;
			case 2:
				 f=new ViewPagerTabListViewFragment();
				 if (0 < mScrollY) {
	                 Bundle args = new Bundle();
	                 args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
	                 f.setArguments(args);
	             }
				break;
			case 3:
				 f=new ViewPagerTabListViewFragment();
				 if (0 < mScrollY) {
	                 Bundle args = new Bundle();
	                 args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
	                 f.setArguments(args);
	             }
				break;
			default:
				 f=new ViewPagerTabListViewFragment();
				 if (0 < mScrollY) {
	                 Bundle args = new Bundle();
	                 args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
	                 f.setArguments(args);
	             }
				break;
			}*/
		
			return f;
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}
		
		 @Override
	    public CharSequence getPageTitle(int position) {
	            return TITLES[position];
	    }
	}
	
	
	/**@注释：ViewPager 改变页面滑动状态  */
   private void propagateToolbarState(boolean isShown) {
	        int toolbarHeight = mToolbarView.getHeight();

	        // Set scrollY for the fragments that are not created yet
	        mPagerAdapter.setScrollY(isShown ? 0 : toolbarHeight);

	        // Set scrollY for the active fragments
	        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
	            // Skip current item
	            if (i == mPager.getCurrentItem()) {
	                continue;
	            }

	            // Skip destroyed or not created item
	            Fragment f = mPagerAdapter.getItemAt(i);
	            if (f == null) {
	                continue;
	            }

	            View view = f.getView();
	            if (view == null) {
	                continue;
	            }
	            propagateToolbarState(isShown, view, toolbarHeight);
	        }
	    }

	    private void propagateToolbarState(boolean isShown, View view, int toolbarHeight) {
	        Scrollable scrollView = (Scrollable) view.findViewById(R.id.scroll);
	        if (scrollView == null) {
	            return;
	        }
	        if (isShown) {
	            // Scroll up
	            if (0 < scrollView.getCurrentScrollY()) {
	                scrollView.scrollVerticallyTo(0);
	            }
	        } else {
	            // Scroll down (to hide padding)
	            if (scrollView.getCurrentScrollY() < toolbarHeight) {
	                scrollView.scrollVerticallyTo(toolbarHeight);
	            }
	        }
	    }
	    
	    
	    private void adjustToolbar(ScrollState scrollState, View view) {
	        int toolbarHeight = mToolbarView.getHeight();
	        final Scrollable scrollView = (Scrollable) view.findViewById(R.id.scroll);
	        if (scrollView == null) {
	            return;
	        }
	        int scrollY = scrollView.getCurrentScrollY();
	        if (scrollState == ScrollState.DOWN) {
	            showToolbar();
	        } else if (scrollState == ScrollState.UP) {
	            if (toolbarHeight <= scrollY) {
	                hideToolbar();
	            } else {
	                showToolbar();
	            }
	        } else {
	            // Even if onScrollChanged occurs without scrollY changing, toolbar should be adjusted
	            if (toolbarIsShown() || toolbarIsHidden()) {
	                // Toolbar is completely moved, so just keep its state
	                // and propagate it to other pages
	                propagateToolbarState(toolbarIsShown());
	            } else {
	                // Toolbar is moving but doesn't know which to move:
	                // you can change this to hideToolbar()
	                showToolbar();
	            }
	        }
	    }
	    
	    private boolean toolbarIsHidden() {
	        return ViewHelper.getTranslationY(mHeaderView) == -mToolbarView.getHeight();
	    }
	    
	    private void showToolbar() {
	        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
	        if (headerTranslationY != 0) {
	            ViewPropertyAnimator.animate(mHeaderView).cancel();
	            ViewPropertyAnimator.animate(mHeaderView).translationY(0).setDuration(200).start();
	        }
	        propagateToolbarState(true);
	    }

	    private void hideToolbar() {
	        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
	        int toolbarHeight = mToolbarView.getHeight();
	        if (headerTranslationY != -toolbarHeight) {
	            ViewPropertyAnimator.animate(mHeaderView).cancel();
	            ViewPropertyAnimator.animate(mHeaderView).translationY(-toolbarHeight).setDuration(200).start();
	        }
	        propagateToolbarState(false);
	    }
	    
	    
	    private Fragment getCurrentFragment() {
	        return mPagerAdapter.getItemAt(mPager.getCurrentItem());
	    }


}
