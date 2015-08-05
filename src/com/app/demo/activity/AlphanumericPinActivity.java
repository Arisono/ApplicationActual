package com.app.demo.activity;


import com.app.demo.R;
import com.app.demo.util.Constants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class AlphanumericPinActivity extends ActionBarActivity {
    
	@ViewInject(R.id.toolbar_nonelevated)
	private Toolbar toolbar;
	private Context context;
	private String pin;
	@ViewInject(R.id.passcode)
    private EditText pinView;
	
	private boolean isSettingUp = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alphanumeric_pin);
		ViewUtils.inject(this);
		context=getApplicationContext();
		
		if (toolbar!=null) {
			setSupportActionBar(toolbar);
		}
		
		String action=getIntent().getAction();
		if (Constants.SET_PIN_ACTION.equalsIgnoreCase(action)) {
	            isSettingUp = true;
	    }
		
		pin=PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.USER_PIN_KEY, "");
				
		pinView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkPin();
                return true;
            }
        });
		
		
	}
	
	@Override
    public void onBackPressed() {
        if (isSettingUp) {
            setResult(RESULT_CANCELED);
        }

        super.onBackPressed();
    }
	
	 private void checkPin() {
		 String enteredPin = pinView.getText().toString();
	        if (isSettingUp) {
	            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
	            editor.putString(getString(R.string.pref_lock_type_key), getString(R.string.pref_alpha_pin_lock_value)).apply();
	            editor.putString(Constants.USER_PIN_KEY, enteredPin).apply();

	            setResult(RESULT_OK);
	            finish();
	        } else {
	            // Check if we can unlock the app
	            if (enteredPin.equalsIgnoreCase(pin)) {
	                startMain();
	            } else {
	                Toast.makeText(context, getString(R.string.incorrect_pin_text), Toast.LENGTH_SHORT).show();
	                resetPin();
	            }
	        }
		 
	 }
	
	private void resetPin() {
        pinView.setText("");
        pinView.requestFocus();
    }
	private void startMain(){
	   Intent intent=new Intent(this, NotesActivity.class);
	   this.finish();
	   startActivity(intent);
	}
}
