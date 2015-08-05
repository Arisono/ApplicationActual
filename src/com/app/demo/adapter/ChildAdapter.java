package com.app.demo.adapter;

import java.util.ArrayList;








import com.app.demo.R;
import com.app.demo.model.ChildEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ChildAdapter extends BaseExpandableListAdapter {
    
	private Context mContext;// ������
	private ArrayList<ChildEntity> mChilds;// ���Դ

	public ChildAdapter(Context context, ArrayList<ChildEntity> childs) {
		this.mContext = context;
		this.mChilds = childs;
	}

	
	@Override
	public int getGroupCount() {
		return mChilds != null ? mChilds.size() : 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mChilds.get(groupPosition).getChildNames() != null ? mChilds
				.get(groupPosition).getChildNames().size() : 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		if (mChilds != null && mChilds.size() > 0)
			return mChilds.get(groupPosition);
		return null;
	}
    
	/**@ע�ͣ�Object to String  */
	@Override
	public String getChild(int groupPosition, int childPosition) {
		if (mChilds.get(groupPosition).getChildNames() != null
				&& mChilds.get(groupPosition).getChildNames().size() > 0)
			return mChilds.get(groupPosition).getChildNames()
					.get(childPosition).toString();
		return null;
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

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_child_group, null);
			holder = new GroupHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (GroupHolder) convertView.getTag();
		}
		holder.update(mChilds.get(groupPosition));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_child_child, null);
			holder = new ChildHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ChildHolder) convertView.getTag();
		}
		holder.update(getChild(groupPosition, childPosition));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		/**
		 * ==============================================
		 * �˴����뷵��true�������޷���Ӧ����ĵ���¼�===============
		 * ==============================================
		 **/
		return true;
	}
	
	
	class GroupHolder {

		private TextView childGroupTV;

		public GroupHolder(View v) {
			childGroupTV = (TextView) v.findViewById(R.id.childGroupTV);
		}

		public void update(ChildEntity model) {
			childGroupTV.setText(model.getGroupName());
			childGroupTV.setTextColor(model.getGroupColor());
		}
	}
	
	class ChildHolder {

		private TextView childChildTV;

		public ChildHolder(View v) {
			childChildTV = (TextView) v.findViewById(R.id.childChildTV);
		}

		public void update(String str) {
			childChildTV.setText(str);

		}
	}

}
