package com.app.demo.view;

import com.app.demo.interfaces.IOnSlidingHandleViewClickListener;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SlidingDrawer;

@SuppressWarnings("deprecation")
public class MySlidingDrawer extends SlidingDrawer {
	/** @注释：抽屉行为控件ID */
	private int mHandleId = 0;
	/** @注释：Handle 部分其他控件ID */
	private int[] mTouchableIds = null;

	private IOnSlidingHandleViewClickListener mTouchViewClickListener;

	public MySlidingDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MySlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setOnSliderHandleViewClickListener(
			IOnSlidingHandleViewClickListener listener) {
		mTouchViewClickListener = listener;
	}
	
	/**
	 * @author LiuJie
	 * @功能:核心方法
	 */
	public boolean onInterceptTouchEvent(MotionEvent event) {
		// 触摸位置转换为屏幕坐标
		int[] location = new int[2];
		int x = (int) event.getX();
		int y = (int) event.getY();
		this.getLocationOnScreen(location);
		x += location[0];
		y += location[1];
		if (mTouchableIds != null) {
			for (int id : mTouchableIds) {
				View view = findViewById(id);
				if (view.isShown()) {
					Rect rect = getRectOnScreen(view);
					if (rect.contains(x, y)) {
                        //关键代码，接口回调
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							if (mTouchViewClickListener != null) {
								mTouchViewClickListener.onViewClick(view);
							}
						}
						return true;
					}
				}
			}
		}

		// 抽屉行为控件
		if (event.getAction() == MotionEvent.ACTION_DOWN && mHandleId != 0) {
			View view = findViewById(mHandleId);
			Rect rect = getRectOnScreen(view);
			if (rect.contains(x, y)) {// 点击抽屉控件时交由系统处理
				{
					return super.onInterceptTouchEvent(event);
				}
			} else {
				return false;
			}
		}

		return super.onInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	/*
	 * 获取控件的屏幕区域
	 */
	public Rect getRectOnScreen(View view) {
		Rect rect = new Rect();
		int[] location = new int[2];
		View parent = view;
		if (view.getParent() instanceof View) {
			parent = (View) view.getParent();
		}
		parent.getLocationOnScreen(location);
		view.getHitRect(rect);
		rect.offset(location[0], location[1]);
		return rect;
	}

	public int getmHandleId() {
		return mHandleId;
	}

	public void setmHandleId(int mHandleId) {
		this.mHandleId = mHandleId;
	}

	public int[] getmTouchableIds() {
		return mTouchableIds;
	}

	public void setmTouchableIds(int[] mTouchableIds) {
		this.mTouchableIds = mTouchableIds;
	}

}
