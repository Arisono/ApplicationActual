/**
 * 
 */
package com.app.demo.activity.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.demo.R;
import com.app.demo.adapter.CalendarAdapter;
import com.app.demo.view.BorderText;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.AbsListView.LayoutParams;

/**
 * @author LiuJie
 * 日历控件
 */
public class CalendarActivity extends Activity implements OnGestureListener {
	
	//每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private static int jumpMonth = 0;   
	//滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private static int jumpYear = 0; 
	
	private ViewFlipper flipper = null;
	private GestureDetector gestureDetector = null;
	private CalendarAdapter calAdapter = null;
	private GridView gridView = null;
	private BorderText topText = null;
	private Drawable draw = null;
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	
	/**@annotation：构造方法 */
	public CalendarActivity() {
		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    	currentDate = sdf.format(date);  //当期日期
    	year_c = Integer.parseInt(currentDate.split("-")[0]);
    	month_c = Integer.parseInt(currentDate.split("-")[1]);
    	day_c = Integer.parseInt(currentDate.split("-")[2]);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gestureDetector = new GestureDetector(this,this);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        flipper.removeAllViews();
		
	}
	
	
	//添加 gridview
	public void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		//取得屏幕的宽度和高度
		WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        @SuppressWarnings("deprecation")
		int Width = display.getWidth(); 
        @SuppressWarnings("deprecation")
		int Height = display.getHeight();
        
        gridView = new GridView(this);
		/**@annotation：7列 */
		gridView.setNumColumns(7);
		gridView.setColumnWidth(46);
		
		if(Width == 480 && Height == 800){
			gridView.setColumnWidth(69);
		}
		
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		// 去除gridView边框
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
        gridView.setBackgroundResource(R.drawable.gridview_bk);
        /**@annotation：监听事件 */
        
        
        gridView.setLayoutParams(params);
	}

	
	
	//添加头部的年份 闰哪月等信息
	@SuppressWarnings("deprecation")
	public void addTextToTopTextView(TextView view){
		StringBuffer textDate = new StringBuffer();
		//背景颜色
		draw = getResources().getDrawable(R.drawable.top_day);
		view.setBackgroundDrawable(draw);
		//日期显示
		/*textDate.append(calAdapter.getShowYear()).append("年").append(
				calAdapter.getShowMonth()).append("月").append("\t");
		
		if (!calAdapter.getLeapMonth().equals("") && calAdapter.getLeapMonth() != null) {
			textDate.append("闰").append(calAdapter.getLeapMonth()).append("月")
					.append("\t");
		}
		
		textDate.append(calAdapter.getAnimalsYear()).append("年").append("(").append(
				calAdapter.getCyclical()).append("年)");*/

        view.setText(textDate);
		view.setTextColor(Color.BLACK);
		view.setTypeface(Typeface.DEFAULT_BOLD);
		
	}


	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onShowPress(android.view.MotionEvent)
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}
}
