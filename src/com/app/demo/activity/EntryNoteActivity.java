package com.app.demo.activity;


import com.app.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

@SuppressWarnings("deprecation")
public class EntryNoteActivity extends ActionBarActivity {
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_nonelevated);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
        //取数组
        String [] stringArray=getResources().getStringArray(R.array.possibleLocksValues);
        String lockType =PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_lock_type_key), "");
        
        		   if (lockType == null || lockType.equals("") || stringArray[0].equals(lockType)) {
        	            Intent mainIntent = new Intent(this, NotesActivity.class);
        	            startActivity(mainIntent);
        	            this.finish();
        	        } else if (stringArray[1].equals(lockType)) {
//        	            Intent pinIntent = new Intent(this, PinActivity.class);
//        	            startActivity(pinIntent);
//        	            this.finish();
        	        } else if (stringArray[2].equals(lockType)) {
        	            Intent pinIntent = new Intent(this, AlphanumericPinActivity.class);
        	            startActivity(pinIntent);
        	            this.finish();
        	        }
        
    }
}
