/**
 * 
 */
package com.app.demo.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;














import com.app.demo.util.LunarCalendar;
import com.app.demo.util.SpecialCalendar;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author LiuJie
 * 日历控件的适配器
 */
public class CalendarAdapter extends BaseAdapter {
	

	private Resources res = null;
	private Context context;
	private SpecialCalendar sc = null;
	private LunarCalendar lc = null; 
	
	//是否为闰年
	private boolean isLeapyear = false; 
	//某月的天数
	private int daysOfMonth = 0;      
	//具体某一天是星期几
	private int dayOfWeek = 0;        
	//上一个月的总天数
	private int lastDaysOfMonth = 0; 
	
	//一个gridview中的日期存入此数组中
	public String[] dayNumber = new String[49];  
	private static String week[] = {"周日","周一","周二","周三","周四","周五","周六"};
	
	//用于在头部显示的年份
	private String showYear = "";  
	//用于在头部显示的月份
	private String showMonth = "";
	 //闰哪一个月
	private String animalsYear = ""; 
	private String leapMonth = "";  
	//天干地支
	private String cyclical = "";   
	
	//用于标记当天
	private int currentFlag = -1; 
	//存储当月所有的日程日期
	private int[] schDateTagFlag = null;  
	
	private SimpleDateFormat sdf = 
			new SimpleDateFormat("yyyy-M-d");
	
	private String currentYear = "";
	private String currentMonth = "";
	private String currentDay = "";
	//日程时间(需要标记的日程日期)
	private String sch_year = "";
	private String sch_month = "";
	private String sch_day = "";
	//系统当前时间
	private String sysDate = "";
	private String sys_year = "";
	private String sys_month = "";
	private String sys_day = "";
	/**
	 * 无参数构造方法
	 */
	public CalendarAdapter() {
		Date date = new Date();
		sysDate = sdf.format(date);  //当期日期
		sys_year = sysDate.split("-")[0];
		sys_month = sysDate.split("-")[1];
		sys_day = sysDate.split("-")[2];
	}
	
	/**
	 * @annotation:有参数构造方法
	 * @param:jumMonth
	 * @param:jumpYear 
	 * @param:year_c  当前年
	 * @param:month_c 当前月
	 * @param:day_c   当前日
	 */
	public CalendarAdapter(Context context,Resources rs,
	int jumpMonth,int jumpYear,int year_c,int month_c,int day_c){
		this();
		this.context=context;
		this.res=rs;
		sc=new SpecialCalendar();
		lc=new LunarCalendar();
		//局部变量
		int stepYear = year_c+jumpYear;
		int stepMonth = month_c+jumpMonth ;
		
		if(stepMonth > 0){
			//往下一个月滑动
			if(stepMonth%12 == 0){
				stepYear = year_c + stepMonth/12 -1;
				stepMonth = 12;
			}else{
				stepYear = year_c + stepMonth/12;
				stepMonth = stepMonth%12;
			}
		}else{
			//往上一个月滑动
			stepYear = year_c - 1 + stepMonth/12;
			stepMonth = stepMonth%12 + 12;
			if(stepMonth%12 == 0){
				
			}
		}
		//得到当前的年份
		currentYear = String.valueOf(stepYear); 
		//得到本月 （jumpMonth为滑动的次数，每滑动一次就增加一月或减一月）
		currentMonth = String.valueOf(stepMonth);
		//得到当前日期是哪天
		currentDay = String.valueOf(day_c);  
		getCalendar(Integer.parseInt(currentYear),Integer.parseInt(currentMonth));
		
	}
	public CalendarAdapter(Context context,Resources rs,
			int year, int month, int day){
		this();
		
		
	}

	//得到某年的某月的天数且这月的第一天是星期几
	private void getCalendar(int year, int month){
	   //是否为闰年
		isLeapyear=sc.isLeapYear(year);
	   //某月的总天数
		daysOfMonth=sc.getDaysOfMonth(isLeapyear, month);
	   //某月第一天为星期几
		dayOfWeek=sc.getWeekdayOfMonth(year, month);
	   //上一个月的总天数	
		lastDaysOfMonth=sc.getDaysOfMonth(isLeapyear, month-1);
	    getweek(year, month);
		
	}
	
	//将一个月中的每一天的值添加入数组dayNuber中
	private void getweek(int year, int month) {
		int j = 1;
		int flag = 0;
		String lunarDay = "";
		
		for (int i = 0; i < dayNumber.length; i++) {
			//周日到周六
			if(i<7){
				dayNumber[i]=week[i]+"."+" ";
			}else if(i<dayOfWeek+7){
				//上一个月
				//上一个月的总天数—第一天星期几
				int temp = lastDaysOfMonth - dayOfWeek+1-7;
				// isday: 这个参数为false---日期为节假日时，阴历日期就返回节假日 ，
				//true---不管日期是否为节假日依然返回这天对应的阴历日期
				lunarDay = lc.getLunarDate(year, month-1, temp+i,false);
				dayNumber[i] = (temp + i)+"."+lunarDay;
		    }else if (i<daysOfMonth + dayOfWeek+7) {
				//本月
		    	String day = String.valueOf(i-dayOfWeek+1-7);   //得到的日期
		    	// isday: 这个参数为false---日期为节假日时，阴历日期就返回节假日 ，
				//true---不管日期是否为节假日依然返回这天对应的阴历日期
				lunarDay = lc.getLunarDate(year, month, i-dayOfWeek+1-7,false);
				dayNumber[i] = i-dayOfWeek+1-7+"."+lunarDay;
				//对于当前月才去标记当前日期
				/*if(sys_year.equals(String.valueOf(year)) && sys_month.equals(String.valueOf(month)) && sys_day.equals(day)){
					//笔记当前日期
					currentFlag = i;
				}*/
				
				//标记日程日期
//				if(dateTagList != null && dateTagList.size() > 0){
//					for(int m = 0; m < dateTagList.size(); m++){
//						ScheduleDateTag dateTag = dateTagList.get(m);
//						int matchYear = dateTag.getYear();
//						int matchMonth = dateTag.getMonth();
//						int matchDay = dateTag.getDay();
//						if(matchYear == year && matchMonth == month && matchDay == Integer.parseInt(day)){
//							schDateTagFlag[flag] = i;
//							flag++;
//						}
//					}
//				}
				
				setShowYear(String.valueOf(year));
				setShowMonth(String.valueOf(month));
				setAnimalsYear(lc.animalsYear(year));
				setLeapMonth(lc.leapMonth == 0?"":String.valueOf(lc.leapMonth));
				setCyclical(lc.cyclical(year));
		    }else{
			     //下一个月   	
				lunarDay = lc.getLunarDate(year, month+1, j,false);
				dayNumber[i] = j+"."+lunarDay;
				j++;
			}
			
		}//for
		
		//打印
		 String abc = "";
	        for(int i = 0; i < dayNumber.length; i++){
	        	 abc = abc+dayNumber[i]+":";
	     }
	        Log.d("DAYNUMBER",abc);
	}
	
    public void matchScheduleDate(int year, int month, int day){
		
	}
	
	/**
	 * 点击每一个item时返回item中的日期
	 * @param position
	 * @return
	 */
	public String getDateByClickItem(int position){
		return dayNumber[position];
	}
	
	/**
	 * 在点击gridView时，得到这个月中第一天的位置
	 * @return
	 */
	public int getStartPositon(){
		return dayOfWeek+7;
	}
	
	/**
	 * 在点击gridView时，得到这个月中最后一天的位置
	 * @return
	 */
	public int getEndPosition(){
		return  (dayOfWeek+daysOfMonth+7)-1;
	}
	/** 注释：**************************************************************/
    @Override
	public int getCount() {
		/**@annotation：7*7 表格一共有四十九和空格 */
		return dayNumber.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	//get  set
	public String getShowYear() {
		return showYear;
	}

	public void setShowYear(String showYear) {
		this.showYear = showYear;
	}

	public String getShowMonth() {
		return showMonth;
	}

	public void setShowMonth(String showMonth) {
		this.showMonth = showMonth;
	}

	public String getAnimalsYear() {
		return animalsYear;
	}

	public void setAnimalsYear(String animalsYear) {
		this.animalsYear = animalsYear;
	}

	public String getCyclical() {
		return cyclical;
	}

	public void setCyclical(String cyclical) {
		this.cyclical = cyclical;
	}

	public String getLeapMonth() {
		return leapMonth;
	}

	public void setLeapMonth(String leapMonth) {
		this.leapMonth = leapMonth;
	}
	
    
	
	

}
