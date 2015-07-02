package com.app.demo.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * @description 阻尼效果的ScrollView
 * 
 */
public class StretchScrollView extends ScrollView {

	private View inner;// 子View

	private float y;// 点击时y坐标
	private float y1;// 点击时y坐标
	private float y2;// 抬起时y坐标

	private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否�?��动画.)

	private boolean isCount = false;// 是否�?��计算

	private boolean isMoveing = false;// 是否�?��移动.

	private ImageView imageView;

	private int initTop, initbottom;// 初始高度
	private int top, bottom;// 拖动时时高度�?

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public StretchScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private OnHeaderRefreshListener mOnHeaderRefreshListener;

	/***
	 * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之�? 即使子类覆盖�?onFinishInflate
	 * 方法，也应该调用父类的方法，使该方法得以执行.
	 */
	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);
		}
	}

	/** touch 事件处理 **/
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (inner != null) {
			commOnTouchEvent(ev);
		}
		return super.onTouchEvent(ev);
	}

	/***
	 * 触摸事件
	 * 
	 * @param ev
	 */
	public void commOnTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			y1 = ev.getY();
			if (imageView != null) {
				top = initTop = imageView.getTop();
				bottom = initbottom = imageView.getBottom();
			}
			break;
		case MotionEvent.ACTION_UP:
			y2 = ev.getY();
			if (isMoveing && (y2 - y1 > 0)) {
				Log.e("jj", "下拉结束");
				mOnHeaderRefreshListener.onHeaderRefresh(this);
			}

			if (isMoveing && (y2 - y1 < 0)) {
				Log.e("jj", "上拉结束");
				mOnHeaderRefreshListener.onHeaderRefresh(this);
			}

			isMoveing = false;
			// 手指松开.
			if (isNeedAnimation()) {
				/***
				 * 回缩动画
				 */
				animation();
			}
			break;
		/***
		 * 排除出第�?��移动计算，因为第�?��无法得知y坐标�?在MotionEvent.ACTION_DOWN中获取不到，
		 * 因为此时是MyScrollView的touch事件传�?到到了LIstView的孩子item上面.�?��从第二次计算�?��.
		 * 然�?我们也要进行初始化，就是第一次移动的时�?让滑动距离归0. 之后记录准确了就正常执行.
		 */
		case MotionEvent.ACTION_MOVE:
			final float preY = y;// 按下时的y坐标
			float nowY = ev.getY();// 时时y坐标
			int deltaY = (int) (nowY - preY);// 滑动距离
			// System.out.println("MOVE deltaY="+ deltaY);
			if (!isCount) {
				deltaY = 0; // 在这里要�?.
			}
			// System.out.println("MOVE  top="+ top);
			// System.out.println("MOVE  initTop="+ initTop);
			// if (deltaY < 0 && top <= initTop)
			// return;

			// 当滚动到�?��或�?�?��时就不会再滚动，这时移动布局
			isNeedMove();

			if (isMoveing) {
				// 初始化头部矩�?
				if (normal.isEmpty()) {
					// 保存正常的布�?���?
//					System.out.println("normal.isEmpty()=" + normal.isEmpty());
					normal.set(inner.getLeft(), inner.getTop(),
							inner.getRight(), inner.getBottom());
				}

				// 移动布局
				top += (deltaY / 7 * 3);
				bottom += (deltaY / 7 * 3);
//				System.out.println("top=" + top);
//				System.out.println("bottom=" + bottom);
				inner.layout(inner.getLeft(), inner.getTop() + deltaY / 3 * 2,
						inner.getRight(), inner.getBottom() + deltaY / 3 * 2);
				if (imageView != null) {
					imageView.layout(imageView.getLeft(), top,
							imageView.getRight(), bottom);
				}
			}
			isCount = true;
			y = nowY;
			break;
		default:
			break;
		}
	}

	/***
	 * 回缩动画
	 */
	public void animation() {

		TranslateAnimation taa = new TranslateAnimation(0, 0, top + 350,
				initTop + 350);
		taa.setDuration(200);
		if (imageView != null) {
			imageView.startAnimation(taa);
			imageView.layout(imageView.getLeft(), initTop,
					imageView.getRight(), initbottom);
		}

		// �?��移动动画
		TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
				normal.top);
		ta.setDuration(200);
		inner.startAnimation(ta);
		// 设置回到正常的布�?���?
		inner.layout(normal.left, normal.top, normal.right, normal.bottom);
		normal.setEmpty();

		isCount = false;
		y = 0;// 手指松开要归0.

	}

	// 是否�?���?��动画
	public boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	/***
	 * 是否�?��移动布局 inner.getMeasuredHeight():获取的是控件的�?高度
	 * 
	 * getHeight()：获取的是屏幕的高度
	 * 
	 * @return
	 */
	public void isNeedMove() {
		int offset = inner.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
		// 0是顶部，后面那个是底�?
		if (scrollY == 0 || scrollY == offset) {
			isMoveing = true;
		}
		// if (scrollY == 0) {
		// isMoveing = true;
		// }
	}

	
	/**
	 * @author :LiuJie 2015年7月2日 上午9:52:20
	 * @注释:接口
	 */
	public interface OnHeaderRefreshListener {
		public void onHeaderRefresh(StretchScrollView view);
	}

	public void setOnHeaderRefreshListener(
			OnHeaderRefreshListener headerRefreshListener) {
		mOnHeaderRefreshListener = headerRefreshListener;
	}

}
