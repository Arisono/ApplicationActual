package com.app.demo.broadcast;


import java.io.File;



import com.app.demo.R;
import com.app.demo.activity.NotesActivity;
import com.app.demo.util.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
/**
 * @author :LiuJie 2015年8月6日 下午3:56:22
 * @注释:Notes 文件夹改变广播
 */
public class CurrentFolderChangedReceiver extends BroadcastReceiver {
	//需要在哪里注册
	private NotesActivity context;
	
	public CurrentFolderChangedReceiver(NotesActivity activity){
		this.context=activity;
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Constants.CURRENT_FOLDER_CHANGED)) {
			File directory = (File) intent.getSerializableExtra(Constants.CURRENT_FOLDER);
            File rootDir = (File) intent.getSerializableExtra(Constants.ROOT_DIR);
            toggleBreadcrumbsVisibility(directory, rootDir);
		}
	}
	
	
	 private void toggleBreadcrumbsVisibility(File currentDir, File rootDir) {
	        TextView breadcrumbs = (TextView) context.findViewById(R.id.breadcrumbs);
	        if (currentDir.equals(rootDir)) {
	            breadcrumbs.setVisibility(View.GONE);
	        } else {
	            breadcrumbs.setText(backButtonText(currentDir, rootDir));
	            breadcrumbs.setVisibility(View.VISIBLE);
	        }
	    }

	    private String backButtonText(File currentDir, File rootDir) {
	        if (currentDir.getParentFile().equals(rootDir)) {
	            return "Writeily > " + currentDir.getName();
	        } else {
	            return "... > " + currentDir.getParentFile().getName() + " > " + currentDir.getName();
	        }
	    }

}
