package com.app.demo.activity;

import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

@SuppressWarnings("deprecation")
public class SettingsActivity extends ActionBarActivity implements SettingsFragment.WriteilySettingsListener  {

	
	SettingsFragment settingsFragment;
	
	
	
	
	
	@Override
	public void onThemeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAboutClicked() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
