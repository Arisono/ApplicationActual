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
 * @author :LiuJie 2015��6��23�� ����9:58:52
 * @ע��:ios style dialog
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
		/**@ע�ͣ�ȡ���Ի���  */
		   new ActionSheetDialog(ct)
			.builder()
			.setTitle("�����Ϣ�б�������¼��Ȼ������ȷ��Ҫ�����Ϣ�б�")
			.setCancelable(false)
			.setCanceledOnTouchOutside(false)
			.addSheetItem("�����Ϣ�б�", SheetItemColor.Red,
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
			.addSheetItem("���͸�����", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("ת�ص��ռ����", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("�ϴ���Ⱥ���", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("���浽�ֻ�", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("�ղ�", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					})
			.addSheetItem("�鿴����ͼƬ", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {

						}
					}).show();
		   break;
	   case R.id.btn3:
		   new ActionSheetDialog(ct)
			.builder()
			.setTitle("��ѡ�����")
			.setCancelable(false)
			.setCanceledOnTouchOutside(false)
			.addSheetItem("��Ŀһ", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀ��", SheetItemColor.Blue,
					new OnSheetItemClickListener() {
						@Override
						public void onClick(int which) {
							Toast.makeText(ct,
									"item" + which, Toast.LENGTH_SHORT)
									.show();
						}
					})
			.addSheetItem("��Ŀʮ", SheetItemColor.Blue,
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
		   new AlertDialog(ct).builder().setTitle("�˳���ǰ�˺�")
			.setMsg("��������½15�죬�Ϳɱ���ΪQQ���ˡ��˳�QQ���ܻ�ʹ�����м�¼���㣬ȷ���˳���")
			.setPositiveButton("ȷ���˳�", new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			}).setNegativeButton("ȡ��", new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			}).show();
			break;
	   case R.id.btn5:
		   new AlertDialog(ct).builder()
			.setMsg("�������޷����յ�����Ϣ���ѡ��뵽ϵͳ-����-֪ͨ�п�����Ϣ����")
			.setNegativeButton("ȷ��", new OnClickListener() {
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
