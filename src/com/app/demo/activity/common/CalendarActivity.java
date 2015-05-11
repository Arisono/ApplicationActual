package com.app.demo.activity.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.demo.R;
import com.app.demo.adapter.CalendarAdapter;
import com.app.demo.view.BorderText;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
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
	public GestureDetector gestureDetector = null;
	private CalendarAdapter calAdapter = null;
	private GridView gridView = null;
	private BorderText topText = null;
	private Drawable draw = null;
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	
	private int select_cell;
	//当前选中的日期
	private String selectDay;
	private String selectYear;
	private String selectMonth;
	private String week;
	
	/**@annotation：构造方法 */
	public CalendarActivity() {
	   initDate();
	}
	
	public void initDate(){
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
		setContentView(R.layout.act_calendar_grid_display);
		
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        gestureDetector = new GestureDetector(this,this);
        flipper.removeAllViews();
        /**@annotation：传参数构造对象 */
        calAdapter = new CalendarAdapter(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
        
        /**@annotation：关键代码 */
        addGridView();
        gridView.setAdapter(calAdapter);
        //flipper.addView(gridView);
        flipper.addView(gridView,0);
        
		topText = (BorderText) findViewById(R.id.toptext);
		addTextToTopTextView(topText);
		
	}
	
	
	//添加 gridview
	public void addGridView() {
		//取得屏幕的宽度和高度
		WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        @SuppressWarnings("deprecation")
		int Width = display.getWidth(); 
        @SuppressWarnings("deprecation")
		int Height = display.getHeight();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		
        params.height=Height;
        gridView = new GridView(this);
		/**@annotation：7列 */
		gridView.setNumColumns(7);
		gridView.setColumnWidth(46);
		
//		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)gridView.getLayoutParams(); // 取控件mGrid当前的布局参数
//		linearParams.height = CommonUtil.dip2px(this, 83);// 当控件的高强制设成75象素
//		gridView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2
		
		if(Width == 480 && Height == 800){
			gridView.setColumnWidth(69);
		}
		
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		// 去除gridView边框
		//gridView.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
        //gridView.setBackgroundResource(R.drawable.gridview_bk);
		gridView.setBackgroundColor(getResources().getColor(R.color.grid_view_cell));
        
		/**@注释：触摸事件监听 2015年5月7日 */
    	gridView.setOnTouchListener(new OnTouchListener() {
            //将gridview中的触摸事件回传给gestureDetector
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return CalendarActivity.this.gestureDetector.onTouchEvent(event);
			}
		});
    	
    	
        gridView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
              System.out.println("gridview item 点击="+position);
              /**@注释：逻辑处理
               *  2015年5月12日 
               *  得到一个月的有效范围 点击有效，其它的点击无效
               */
              int startPosition=calAdapter.getStartPositon();
              int endPosition=calAdapter.getEndPosition();
              if (startPosition <= position  && position <= endPosition) {
            	  select_cell=position;
                  calAdapter.setSeclection(position);
                  calAdapter.notifyDataSetChanged();
                   week = "";
                   selectDay = calAdapter.getDateByClickItem(position).split("\\.")[0];  //这一天的阳历
				  //String scheduleLunarDay = calV.getDateByClickItem(position).split("\\.")[1];  //这一天的阴历
                   selectYear = calAdapter.getShowYear();
                   selectMonth = calAdapter.getShowMonth();
                
                  //得到这一天是星期几
                  switch(position%7){
                  case 0:
                	  week = "星期日";
                	  break;
                  case 1:
                	  week = "星期一";
                	  break;
                  case 2:
                	  week = "星期二";
                	  break;
                  case 3:
                	  week = "星期三";
                	  break;
                  case 4:
                	  week = "星期四";
                	  break;
                  case 5:
                	  week = "星期五";
                	  break;
                  case 6:
                	  week = "星期六";
                	  break;
                  }
              }
              
			}
		});
        
        gridView.setSelector(getResources().getDrawable(R.drawable.selector_calendar_normal));
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
		textDate.append(calAdapter.getShowYear()).append("年").append(
				calAdapter.getShowMonth()).append("月").append("\t");
		
		if (!calAdapter.getLeapMonth().equals("") && calAdapter.getLeapMonth() != null) {
			textDate.append("闰").append(calAdapter.getLeapMonth()).append("月")
					.append("\t");
		}
		
		textDate.append(calAdapter.getAnimalsYear()).append("年").append("(").append(
				calAdapter.getCyclical()).append("年)");

        view.setText(textDate);
		view.setTextColor(Color.BLACK);
		view.setTypeface(Typeface.DEFAULT_BOLD);
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/**@注释：把监听交给手势监听类  2015年5月7日 */
		return this.gestureDetector.onTouchEvent(event);
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
		System.out.println("e1="+e1+"  e2="+e2);
		if (e1==null||e2==null) {
			return true;
		}
		int gvFlag = 0;         //每次添加gridview到viewflipper中时给的标记
		if (e1.getX() - e2.getX() > 120) {
            //像左滑动
			addGridView();   //添加一个gridView
			jumpMonth++;     //下一个月
			
			calAdapter = new CalendarAdapter(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calAdapter);
	        addTextToTopTextView(topText);
//	        if (select_cell!=0) {
//	        calAdapter.setSeclection(select_cell);
//	        calAdapter.notifyDataSetChanged();
//			}
	       
	        gvFlag++;
	        flipper.addView(gridView, gvFlag);
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));
			this.flipper.showNext();
			flipper.removeViewAt(0);
			return true;
		} else if (e1.getX() - e2.getX() < -120) {
            //向右滑动
			addGridView();   //添加一个gridView
			jumpMonth--;     //上一个月
			calAdapter = new CalendarAdapter(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calAdapter);
//	        if (select_cell!=0) {
//		        calAdapter.setSeclection(select_cell);
//		        calAdapter.notifyDataSetChanged();
//			}
	        gvFlag++;
	        addTextToTopTextView(topText);
	        flipper.addView(gridView,gvFlag);
	        
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));
			this.flipper.showPrevious();
			flipper.removeViewAt(0);
			return true;
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.calendar_menu_actionbar, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.cd_mi_today:
			//跳转到今天
        	int xMonth = jumpMonth;
        	int xYear = jumpYear;
	        if(xMonth == 0 && xYear == 0){
	        	System.out.println("跳转到今天！");
	        	initDate();
	         	calAdapter = new CalendarAdapter(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        	gridView.setAdapter(calAdapter);
	        	addTextToTopTextView(topText);
	        }else if((xYear == 0 && xMonth >0) || xYear >0){
	        	int gvFlag =0;
	        	jumpMonth = 0;
	        	jumpYear = 0;
	        	addGridView();   //添加一个gridView
	        	year_c = Integer.parseInt(currentDate.split("-")[0]);
	        	month_c = Integer.parseInt(currentDate.split("-")[1]);
	        	day_c = Integer.parseInt(currentDate.split("-")[2]);
	        	calAdapter = new CalendarAdapter(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
		        gridView.setAdapter(calAdapter);
		        addTextToTopTextView(topText);
		        gvFlag++;
		        flipper.addView(gridView,gvFlag);
		        this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));
				this.flipper.showNext();
				flipper.removeViewAt(0);
	        }else{
	        	int gvFlag =0;
	        	jumpMonth = 0;
	        	jumpYear = 0;
	        	addGridView();   //添加一个gridView
	        	year_c = Integer.parseInt(currentDate.split("-")[0]);
	        	month_c = Integer.parseInt(currentDate.split("-")[1]);
	        	day_c = Integer.parseInt(currentDate.split("-")[2]);
	        	calAdapter = new CalendarAdapter(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
		        gridView.setAdapter(calAdapter);
		        addTextToTopTextView(topText);
		        gvFlag++;
		        flipper.addView(gridView,gvFlag);
	        	this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));
				this.flipper.showPrevious();
				flipper.removeViewAt(0);
	        }
			return true;
		case R.id.cd_mi_change:
				new DatePickerDialog(this, new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					System.out.println("on event");
					if(year < 1901 || year > 2100){
						//不在查询范围内
						new AlertDialog.Builder(CalendarActivity.this).setTitle("错误日期").setMessage("跳转日期范围(1901/1/1-2049/12/31)").setPositiveButton("确认", null).show();
					}else{
				        if(year == year_c && monthOfYear+1 == month_c){
				        	System.out.println("跳转1");
				        	calAdapter = new CalendarAdapter(CalendarActivity.this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
				        	gridView.setAdapter(calAdapter);
				        }else
				        if((year == year_c && monthOfYear+1 > month_c) || year > year_c ){
				        	int gvFlag = 0;
							addGridView();   //添加一个gridView
				        	calAdapter = new CalendarAdapter(CalendarActivity.this, CalendarActivity.this.getResources(),year,monthOfYear+1,dayOfMonth);
					        gridView.setAdapter(calAdapter);
					        addTextToTopTextView(topText);
					        gvFlag++;
					        flipper.addView(gridView,gvFlag);
				        	System.out.println("跳转2");
				        	flipper.setInAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_left_in));
				        	flipper.setOutAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_left_out));
				        	flipper.showNext();
				        	flipper.clearAnimation();
				        	flipper.removeViewAt(0);
				        }else{
				        	int gvFlag = 0;
							addGridView();   //添加一个gridView
				        	calAdapter = new CalendarAdapter(CalendarActivity.this, CalendarActivity.this.getResources(),year,monthOfYear+1,dayOfMonth);
					        gridView.setAdapter(calAdapter);
					        addTextToTopTextView(topText);
					        gvFlag++;
					        flipper.addView(gridView,gvFlag);
				        	System.out.println("跳转3");
				        	flipper.setInAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_right_in));
				        	flipper.setOutAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_right_out));
				        	
				        	flipper.showPrevious();
				        	flipper.clearAnimation();
				        	flipper.removeViewAt(0);
				        }
				        //跳转之后将跳转之后的日期设置为当期日期
				        year_c = year;
						month_c = monthOfYear+1;
						day_c = dayOfMonth;
						jumpMonth = 0;
						jumpYear = 0;
					}
				}
			},year_c, month_c-1, day_c).show();
		    return true;
		case R.id.cd_mi_exit:
		     /**@注释：启动第三方app 2015年5月8日 */
			 Intent intent = getPackageManager().getLaunchIntentForPackage(  
		                "com.felipecsl.asymmetricgridview.app");  
		        if (intent != null) {  
		            startActivity(intent);  
		        }  
			return true;
		case R.id.cd_mi_newTask:
			/**@注释：跳转  新建日程 2015年5月12日 */
			System.out.println("您点击了"+selectYear+"年"+selectMonth+"月"+selectDay+"日"+week);
			Intent it_schedule=new Intent(this, NewSchedule.class);
			startActivity(it_schedule);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
