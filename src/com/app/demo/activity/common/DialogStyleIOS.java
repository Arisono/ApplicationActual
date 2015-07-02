package com.app.demo.activity.common;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.app.demo.R;
import com.app.demo.base.BaseSimpleActivity;
import com.app.demo.dialog.ActionSheetDialog;
import com.app.demo.dialog.ActionSheetDialog.OnSheetItemClickListener;
import com.app.demo.dialog.ActionSheetDialog.SheetItemColor;
import com.app.demo.dialog.AlertDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * @author :LiuJie 2015年6月23日 上午9:58:52
 * @注释:ios style dialog
 */
public class DialogStyleIOS extends BaseSimpleActivity {
    
	@ViewInject(R.id.btn1)
	private Button btn1;
	@ViewInject(R.id.btn2)
	private Button btn2;
	@ViewInject(R.id.btn3)
	private Button btn3;
	@ViewInject(R.id.btn4)
	private Button btn4;
	@ViewInject(R.id.btn5)
	private Button btn5;
	
	@Override
	public void onClick(View v) {
       switch (v.getId()) {
	   case R.id.btn1:
		/**@注释：取消对话框  */
		   new ActionSheetDialog(ct)
			.builder()
			.setTitle("清空消息列表后，聊天记录依然保留，确定要清空消息列表？")
			.setCancelable(false)
			.setCanceledOnTouchOutside(false)
			.addSheetItem("清空消息列表", SheetItemColor.Red,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					}).show();
		break;
	   case R.id.btn2:
		   new ActionSheetDialog(ct)
			.builder()
			.setCancelable(false)
			.setCanceledOnTouchOutside(false)
			.addSheetItem("发送给好友", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("转载到空间相册", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("上传到群相册", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("保存到手机", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("收藏", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("查看聊天图片", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					}).show();
		   break;
	   case R.id.btn3:
		   new ActionSheetDialog(ct)
			.builder()
			.setTitle("请选择操作")
			.setCancelable(false)
			.setCanceledOnTouchOutside(false)
			.addSheetItem("条目一", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目二", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目三", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目四", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目五", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目六", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目七", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目八", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目九", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("条目十", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					}).show();
			break;
	   case R.id.btn4:
		   new AlertDialog(ct).builder().setTitle("退出当前账号")
			.setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
			.setPositiveButton("确认退出", new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			}).setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			}).show();
			break;
	   case R.id.btn5:
		   new AlertDialog(ct).builder()
			.setMsg("你现在无法接收到新消息提醒。请到系统-设置-通知中开启消息提醒")
			.setNegativeButton("确定", new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			}).show();
			
			break;
	default:
		break;
	}		
	}

	@Override
	public void setView() {
     setContentView(R.layout.act_dialog_style_ios);		
	}

	@Override
	public void initView() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
