package com.app.demo.activity.tool;

import java.text.DecimalFormat;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.demo.R;
import com.app.demo.base.BaseComplexActivity;
import com.app.demo.util.StringUtils;
import com.app.demo.view.AutofitTextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * @author :LiuJie 2018年10月16日 上午10:09:18
 * @注释:计算年利率
 */
public class InterestActivity extends BaseComplexActivity implements OnClickListener{
	/**
	 * @author LiuJie 
	 * @功能:年利率公式      利息=总额×(年利率÷365)×天数
	 */
    //textview
	@ViewInject(R.id.tv_interest)
	private AutofitTextView tv_interest;
	@ViewInject(R.id.tv_total)
	private AutofitTextView tv_tv_total;
	//edit
	@ViewInject(R.id.et_total_money)
	private EditText et_total_money;
	@ViewInject(R.id.et_days)
	private EditText et_days;
	@ViewInject(R.id.et_rate)
	private EditText et_rate;
	//delete 
	@ViewInject(R.id.iv_delete_total)
	private ImageView iv_delete_total;
	@ViewInject(R.id.iv_delete_days)
	private ImageView iv_delete_days;
	@ViewInject(R.id.iv_delete_rate)
	private ImageView iv_delete_rate;
	
	@ViewInject(R.id.rl_money)
	private RelativeLayout rl_money;
	@ViewInject(R.id.rl_day)
	private RelativeLayout rl_day;
	@ViewInject(R.id.rl_rate)
	private RelativeLayout rl_rate;
	@Override
	public void initView() {
		setContentView(R.layout.act_tools_calculator);
		ViewUtils.inject(this);
		iv_delete_days.setOnClickListener(this);
		iv_delete_rate.setOnClickListener(this);
		iv_delete_total.setOnClickListener(this);
		
		rl_day.setOnClickListener(this);
		rl_money.setOnClickListener(this);
		rl_rate.setOnClickListener(this);
		
		et_total_money.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!StringUtils.isEmpty(et_total_money.getText().toString())
						&&et_total_money.isFocused()) {
					iv_delete_total.setVisibility(View.VISIBLE);
					et_total_money.setTextSize(18);
				}else{
					iv_delete_total.setVisibility(View.INVISIBLE);
					et_total_money.setTextSize(18);
				}
			}
		});
		et_days.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!StringUtils.isEmpty(et_days.getText().toString())
						&&et_days.isFocused()) {
					et_days.setTextSize(18);
					iv_delete_days.setVisibility(View.VISIBLE);
				}else{
					et_days.setTextSize(18);
					iv_delete_days.setVisibility(View.INVISIBLE);
				}				
			}
		});
		
		et_rate.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!StringUtils.isEmpty(et_rate.getText().toString())
						&&et_rate.isFocused()) {
					et_rate.setTextSize(18);
					iv_delete_rate.setVisibility(View.VISIBLE);
				}else{
					et_rate.setTextSize(18);
					iv_delete_rate.setVisibility(View.INVISIBLE);
				}				
			}
		});
		
		et_total_money.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				calculate(et_total_money);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		
		et_days.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				calculate(et_days);				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		et_rate.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				calculate(et_rate);				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	/**
	 * 计算年化利率
	 */
	private void calculate(EditText v) {
		Editable etext = v.getEditableText();
		Selection.setSelection(etext, etext.length());
		if (!StringUtils.isEmpty(et_total_money.getText().toString())
				&&et_total_money.isFocused()) {
			iv_delete_total.setVisibility(View.VISIBLE);
			et_total_money.setTextSize(18);
		}else{
			iv_delete_total.setVisibility(View.INVISIBLE);
			et_total_money.setTextSize(18);
		}
		if (!StringUtils.isEmpty(et_days.getText().toString())
				&&et_days.isFocused()) {
			et_days.setTextSize(18);
			iv_delete_days.setVisibility(View.VISIBLE);
		}else{
			et_days.setTextSize(18);
			iv_delete_days.setVisibility(View.INVISIBLE);
		}	
		if (!StringUtils.isEmpty(et_rate.getText().toString())
				&&et_rate.isFocused()) {
			et_rate.setTextSize(18);
			iv_delete_rate.setVisibility(View.VISIBLE);
		}else{
			et_rate.setTextSize(18);
			iv_delete_rate.setVisibility(View.INVISIBLE);
		}	
		
		if (!StringUtils.isEmpty(et_total_money.getText().toString())
				&&!StringUtils.isEmpty(et_days.getText().toString())
				&&!StringUtils.isEmpty(et_rate.getText().toString())) {
			Double total=Double.valueOf(et_total_money.getText().toString());
			Double days=Double.valueOf(et_days.getText().toString());
			Double rate=Double.valueOf(et_rate.getText().toString());
			Double accrual=total*(rate/365/100)*days;
			DecimalFormat df = new DecimalFormat("#,##0.00;(#)");  
		    System.out.println(df.format(accrual));  
//		    if (!"0".equals(df.format(accrual))) {
		    	tv_interest.setText(df.format(accrual));
			    tv_tv_total.setText(df.format(total+accrual));
//			}else{
//				 tv_tv_total.setText(df.format(total));
//			}
		    
		}
	}

	@Override
	public void onClick(View v) {
      switch (v.getId()) {
	   case R.id.iv_delete_total:
		    et_total_money.setText("");
		    tv_interest.setText("0.00");
		    tv_tv_total.setText("0.00");
		break;
	   case R.id.iv_delete_days:
			et_days.setText("");
			tv_interest.setText("0.00");
			tv_tv_total.setText("0.00");
			break;
	   case R.id.iv_delete_rate:
            et_rate.setText("");	
            tv_interest.setText("0.00");
		    tv_tv_total.setText("0.00");
			break;
	   case R.id.rl_day:
		   et_days.setFocusable(true);
		   et_days.requestFocus();
		   break;
	   case R.id.rl_money:
		   et_total_money.setFocusable(true);
		   et_total_money.requestFocus();
		   break;
	   case R.id.rl_rate:
		   et_rate.setFocusable(true);
		   et_rate.requestFocus();
		   break;

	default:
		break;
	}		
	}
	
	

}
