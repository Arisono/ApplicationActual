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
 * �����ؼ���������
 */
public class CalendarAdapter extends BaseAdapter {
	

	private Resources res = null;
	private Context context;
	private SpecialCalendar sc = null;
	private LunarCalendar lc = null; 
	
	//�Ƿ�Ϊ����
	private boolean isLeapyear = false; 
	//ĳ�µ�����
	private int daysOfMonth = 0;      
	//����ĳһ�������ڼ�
	private int dayOfWeek = 0;        
	//��һ���µ�������
	private int lastDaysOfMonth = 0; 
	
	//һ��gridview�е����ڴ����������
	public String[] dayNumber = new String[49];  
	private static String week[] = {"����","��һ","�ܶ�","����","����","����","����"};
	
	//������ͷ����ʾ�����
	private String showYear = "";  
	//������ͷ����ʾ���·�
	private String showMonth = "";
	 //����һ����
	private String animalsYear = ""; 
	private String leapMonth = "";  
	//��ɵ�֧
	private String cyclical = "";   
	
	//���ڱ�ǵ���
	private int currentFlag = -1; 
	//�洢�������е��ճ�����
	private int[] schDateTagFlag = null;  
	
	private SimpleDateFormat sdf = 
			new SimpleDateFormat("yyyy-M-d");
	
	private String currentYear = "";
	private String currentMonth = "";
	private String currentDay = "";
	//�ճ�ʱ��(��Ҫ��ǵ��ճ�����)
	private String sch_year = "";
	private String sch_month = "";
	private String sch_day = "";
	//ϵͳ��ǰʱ��
	private String sysDate = "";
	private String sys_year = "";
	private String sys_month = "";
	private String sys_day = "";
	/**
	 * �޲������췽��
	 */
	public CalendarAdapter() {
		Date date = new Date();
		sysDate = sdf.format(date);  //��������
		sys_year = sysDate.split("-")[0];
		sys_month = sysDate.split("-")[1];
		sys_day = sysDate.split("-")[2];
	}
	
	/**
	 * @annotation:�в������췽��
	 * @param:jumMonth
	 * @param:jumpYear 
	 * @param:year_c  ��ǰ��
	 * @param:month_c ��ǰ��
	 * @param:day_c   ��ǰ��
	 */
	public CalendarAdapter(Context context,Resources rs,
	int jumpMonth,int jumpYear,int year_c,int month_c,int day_c){
		this();
		this.context=context;
		this.res=rs;
		sc=new SpecialCalendar();
		lc=new LunarCalendar();
		//�ֲ�����
		int stepYear = year_c+jumpYear;
		int stepMonth = month_c+jumpMonth ;
		
		if(stepMonth > 0){
			//����һ���»���
			if(stepMonth%12 == 0){
				stepYear = year_c + stepMonth/12 -1;
				stepMonth = 12;
			}else{
				stepYear = year_c + stepMonth/12;
				stepMonth = stepMonth%12;
			}
		}else{
			//����һ���»���
			stepYear = year_c - 1 + stepMonth/12;
			stepMonth = stepMonth%12 + 12;
			if(stepMonth%12 == 0){
				
			}
		}
		//�õ���ǰ�����
		currentYear = String.valueOf(stepYear); 
		//�õ����� ��jumpMonthΪ�����Ĵ�����ÿ����һ�ξ�����һ�»��һ�£�
		currentMonth = String.valueOf(stepMonth);
		//�õ���ǰ����������
		currentDay = String.valueOf(day_c);  
		getCalendar(Integer.parseInt(currentYear),Integer.parseInt(currentMonth));
		
	}
	public CalendarAdapter(Context context,Resources rs,
			int year, int month, int day){
		this();
		
		
	}

	//�õ�ĳ���ĳ�µ����������µĵ�һ�������ڼ�
	private void getCalendar(int year, int month){
	   //�Ƿ�Ϊ����
		isLeapyear=sc.isLeapYear(year);
	   //ĳ�µ�������
		daysOfMonth=sc.getDaysOfMonth(isLeapyear, month);
	   //ĳ�µ�һ��Ϊ���ڼ�
		dayOfWeek=sc.getWeekdayOfMonth(year, month);
	   //��һ���µ�������	
		lastDaysOfMonth=sc.getDaysOfMonth(isLeapyear, month-1);
	    getweek(year, month);
		
	}
	
	//��һ�����е�ÿһ���ֵ���������dayNuber��
	private void getweek(int year, int month) {
		int j = 1;
		int flag = 0;
		String lunarDay = "";
		
		for (int i = 0; i < dayNumber.length; i++) {
			//���յ�����
			if(i<7){
				dayNumber[i]=week[i]+"."+" ";
			}else if(i<dayOfWeek+7){
				//��һ����
				//��һ���µ�����������һ�����ڼ�
				int temp = lastDaysOfMonth - dayOfWeek+1-7;
				// isday: �������Ϊfalse---����Ϊ�ڼ���ʱ���������ھͷ��ؽڼ��� ��
				//true---���������Ƿ�Ϊ�ڼ�����Ȼ���������Ӧ����������
				lunarDay = lc.getLunarDate(year, month-1, temp+i,false);
				dayNumber[i] = (temp + i)+"."+lunarDay;
		    }else if (i<daysOfMonth + dayOfWeek+7) {
				//����
		    	String day = String.valueOf(i-dayOfWeek+1-7);   //�õ�������
		    	// isday: �������Ϊfalse---����Ϊ�ڼ���ʱ���������ھͷ��ؽڼ��� ��
				//true---���������Ƿ�Ϊ�ڼ�����Ȼ���������Ӧ����������
				lunarDay = lc.getLunarDate(year, month, i-dayOfWeek+1-7,false);
				dayNumber[i] = i-dayOfWeek+1-7+"."+lunarDay;
				//���ڵ�ǰ�²�ȥ��ǵ�ǰ����
				/*if(sys_year.equals(String.valueOf(year)) && sys_month.equals(String.valueOf(month)) && sys_day.equals(day)){
					//�ʼǵ�ǰ����
					currentFlag = i;
				}*/
				
				//����ճ�����
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
			     //��һ����   	
				lunarDay = lc.getLunarDate(year, month+1, j,false);
				dayNumber[i] = j+"."+lunarDay;
				j++;
			}
			
		}//for
		
		//��ӡ
		 String abc = "";
	        for(int i = 0; i < dayNumber.length; i++){
	        	 abc = abc+dayNumber[i]+":";
	     }
	        Log.d("DAYNUMBER",abc);
	}
	
    public void matchScheduleDate(int year, int month, int day){
		
	}
	
	/**
	 * ���ÿһ��itemʱ����item�е�����
	 * @param position
	 * @return
	 */
	public String getDateByClickItem(int position){
		return dayNumber[position];
	}
	
	/**
	 * �ڵ��gridViewʱ���õ�������е�һ���λ��
	 * @return
	 */
	public int getStartPositon(){
		return dayOfWeek+7;
	}
	
	/**
	 * �ڵ��gridViewʱ���õ�����������һ���λ��
	 * @return
	 */
	public int getEndPosition(){
		return  (dayOfWeek+daysOfMonth+7)-1;
	}
	/** ע�ͣ�**************************************************************/
    @Override
	public int getCount() {
		/**@annotation��7*7 ���һ������ʮ�źͿո� */
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
