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
 * �����ؼ�
 */
public class CalendarActivity extends Activity implements OnGestureListener {
	
	//ÿ�λ��������ӻ��ȥһ����,Ĭ��Ϊ0������ʾ��ǰ�£�
	private static int jumpMonth = 0;   
	//������Խһ�꣬�����ӻ��߼�ȥһ��,Ĭ��Ϊ0(����ǰ��)
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
	
	/**@annotation�����췽�� */
	public CalendarActivity() {
		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    	currentDate = sdf.format(date);  //��������
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
	
	
	//��� gridview
	public void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		//ȡ����Ļ�Ŀ�Ⱥ͸߶�
		WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        @SuppressWarnings("deprecation")
		int Width = display.getWidth(); 
        @SuppressWarnings("deprecation")
		int Height = display.getHeight();
        
        gridView = new GridView(this);
		/**@annotation��7�� */
		gridView.setNumColumns(7);
		gridView.setColumnWidth(46);
		
		if(Width == 480 && Height == 800){
			gridView.setColumnWidth(69);
		}
		
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		// ȥ��gridView�߿�
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
        gridView.setBackgroundResource(R.drawable.gridview_bk);
        /**@annotation�������¼� */
        
        
        gridView.setLayoutParams(params);
	}

	
	
	//���ͷ������� �����µ���Ϣ
	@SuppressWarnings("deprecation")
	public void addTextToTopTextView(TextView view){
		StringBuffer textDate = new StringBuffer();
		//������ɫ
		draw = getResources().getDrawable(R.drawable.top_day);
		view.setBackgroundDrawable(draw);
		//������ʾ
		/*textDate.append(calAdapter.getShowYear()).append("��").append(
				calAdapter.getShowMonth()).append("��").append("\t");
		
		if (!calAdapter.getLeapMonth().equals("") && calAdapter.getLeapMonth() != null) {
			textDate.append("��").append(calAdapter.getLeapMonth()).append("��")
					.append("\t");
		}
		
		textDate.append(calAdapter.getAnimalsYear()).append("��").append("(").append(
				calAdapter.getCyclical()).append("��)");*/

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
