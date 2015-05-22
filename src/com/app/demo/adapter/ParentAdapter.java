package com.app.demo.adapter;

import java.util.ArrayList;




import com.app.demo.R;
import com.app.demo.model.ChildEntity;
import com.app.demo.model.ParentEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
/**
 * @author :LiuJie 时间: 2015年5月22日 下午3:32:21
 * @注释:Tree
 */
public class ParentAdapter extends BaseExpandableListAdapter {
    
	private Context mContext;// 上下文
	private ArrayList<ParentEntity> mParents;// 数据源
	
	private OnChildTreeViewClickListener mTreeViewClickListener;
	
	public ParentAdapter(Context context, ArrayList<ParentEntity> parents) {
		this.mContext = context;
		this.mParents = parents;
	}
	
	@Override
	public int getGroupCount() {
		return mParents != null ? mParents.size() : 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mParents.get(groupPosition).getChilds() != null ? mParents
				.get(groupPosition).getChilds().size() : 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mParents.get(groupPosition);
	}
	
    /**@注释：Object  to  ChildEntity  */
	@Override
	public ChildEntity getChild(int groupPosition, int childPosition) {
		return mParents.get(groupPosition).getChilds().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
    /**@注释：核心方法  */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_parent_group, null);
			holder = new GroupHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (GroupHolder) convertView.getTag();
		}
		holder.update(mParents.get(groupPosition));
		return convertView;
	}
    
	/**
	 * @author LiuJie
	 * @功能:遍历ChildView 创建二级列表
	 */
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final ExpandableListView eListView=getExpandableListView();
		ArrayList<ChildEntity> childs=new ArrayList<ChildEntity>();
		final ChildEntity child=getChild(groupPosition, childPosition);
		childs.add(child);
		
		final ChildAdapter childAdapter = new ChildAdapter(this.mContext,
				childs);
		eListView.setAdapter(childAdapter);
		
		/**
		 * @author Apathy、恒
		 * 
		 *         点击子ExpandableListView子项时，调用回调接口
		 * */
		eListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1,
					int groupIndex, int childIndex, long arg4) {

				if (mTreeViewClickListener != null) {

					mTreeViewClickListener.onClickPosition(groupPosition,
							childPosition, childIndex);
				}
				return false;
			}
		});

		
		/**
		 * @author Apathy、恒
		 * 
		 *         子ExpandableListView展开时，因为group只有一项，所以子ExpandableListView的总高度=
		 *         （子ExpandableListView的child数量 + 1 ）* 每一项的高度
		 * */
		eListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				LayoutParams lp = new LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, (child
								.getChildNames().size() + 1)
								* (int) mContext.getResources().getDimension(
										R.dimen.parent_expandable_list_height));
				eListView.setLayoutParams(lp);
			}
		});
		
		eListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupIndex, long id) {
				Toast.makeText(mContext, "group="+childPosition, 2000).show();
				return false;
			}
		});

		/**
		 * @author Apathy、恒
		 *         子ExpandableListView关闭时，此时只剩下group这一项，
		 *         所以子ExpandableListView的总高度即为一项的高度
		 * */
		eListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				
				LayoutParams lp = new LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, (int) mContext
								.getResources().getDimension(
										R.dimen.parent_expandable_list_height));
				eListView.setLayoutParams(lp);
			}
		});
		
		return eListView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
	
	
	/**@注释：接口声明  */
	public interface OnChildTreeViewClickListener {

		void onClickPosition(int parentPosition, int groupPosition,
				int childPosition);
	}
	
	class GroupHolder{
		
		private TextView parentGroupTV;
		public GroupHolder(View v) {
			parentGroupTV = (TextView) v.findViewById(R.id.parentGroupTV);
		}

		public void update(ParentEntity model) {
			parentGroupTV.setText(model.getGroupName());
			parentGroupTV.setTextColor(model.getGroupColor());
		}
	}
	
	/**
	 * @author 
	 *         动态创建子ExpandableListView
	 * */
	public ExpandableListView getExpandableListView() {
		ExpandableListView mExpandableListView = new ExpandableListView(
				mContext);
		LayoutParams lp = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, (int) mContext
						.getResources().getDimension(
								R.dimen.parent_expandable_list_height));
		mExpandableListView.setLayoutParams(lp);
		mExpandableListView.setDividerHeight(0);// 取消group项的分割线
		mExpandableListView.setChildDivider(null);// 取消child项的分割线
		mExpandableListView.setGroupIndicator(null);// 取消展开折叠的指示图标
		return mExpandableListView;
	}
	
	/**
	 * @author 
	 * 
	 *         设置点击子ExpandableListView子项的监听
	 * */
	public void setOnChildTreeViewClickListener(
			OnChildTreeViewClickListener treeViewClickListener) {
		this.mTreeViewClickListener = treeViewClickListener;
	}

}
