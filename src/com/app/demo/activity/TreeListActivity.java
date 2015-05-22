package com.app.demo.activity;

import java.util.ArrayList;

import android.graphics.Color;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.app.demo.R;
import com.app.demo.adapter.ParentAdapter;
import com.app.demo.adapter.ParentAdapter.OnChildTreeViewClickListener;
import com.app.demo.adapter.ParentAdapter.OnGruopPositionViewClickListener;
import com.app.demo.base.BaseAcivity;
import com.app.demo.model.ChildEntity;
import com.app.demo.model.ParentEntity;
 
 /**@注释：多级树演示案例  */
public class TreeListActivity extends BaseAcivity implements OnGroupExpandListener,
OnChildTreeViewClickListener, OnGroupClickListener
{
    
	private ExpandableListView eList;
	
	private ArrayList<ParentEntity> parents;
	private ParentAdapter adapter;
	
	private ArrayList<String> childNames;
	@Override
	public void setView() {
     setContentView(R.layout.expand_tree_view);
     TAG="TreeListActivity";
	}

	@Override
	public void initView() {
      
		initData();
	}

	@Override
	public void setListener() {
		eList = (ExpandableListView) findViewById(R.id.eList);
		eList.setOnGroupExpandListener(this);
		eList.setOnGroupClickListener(this);
		adapter = new ParentAdapter(ct, parents);
		eList.setAdapter(adapter);
		adapter.setOnChildTreeViewClickListener(this);
	}
	
	public void initData(){

		parents = new ArrayList<ParentEntity>();

		for (int i = 0; i < 3; i++) {

			ParentEntity parent = new ParentEntity();

			parent.setGroupName("父类父分组第" + i + "项");

			parent.setGroupColor(getResources().getColor(
					android.R.color.black));

			ArrayList<ChildEntity> childs = new ArrayList<ChildEntity>();

			for (int j = 0; j < 3; j++) {

				ChildEntity child = new ChildEntity();

				child.setGroupName("子类父分组第" + j + "项");

				child.setGroupColor(getResources().getColor(
						android.R.color.black));

				childNames = new ArrayList<String>();

				ArrayList<Integer> childColors = new ArrayList<Integer>();
				if (i!=0||i==5) {

					for (int k = 0; k < 3; k++) {

						childNames.add("子类第" + k + "项");

						childColors.add(Color.parseColor("#003333"));

					}
				}


				child.setChildNames(childNames);

				childs.add(child);

			}

			parent.setChilds(childs);

			parents.add(parent);

		}
	}


	@Override
	public void onClickPosition(ExpandableListView view,int parentPosition, int groupPosition,
			int childPosition) {
		String childName = parents.get(parentPosition).getChilds()
				.get(groupPosition).getChildNames().get(childPosition)
				.toString();
//		Toast.makeText(
//				ct,
//				"点击的下标为： parentPosition=" + parentPosition
//						+ "   groupPosition=" + groupPosition
//						+ "   childPosition=" + childPosition + "\n点击的是："
//						+ childName, Toast.LENGTH_SHORT).show();
		parents.get(parentPosition).setSeletedTV(childName);
	    this.adapter.notifyDataSetChanged();
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		 parents.get(groupPosition).setHide(true);
	}
	
	
	public void tString(){
		for (int i = 0; i < parents.size(); i++) {
			System.out.println("\n"+parents.get(i).getSeletedTV());
		}
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		parents.get(groupPosition).setHide(false);
		return false;
	}


}
