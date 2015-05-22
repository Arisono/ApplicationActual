package com.app.demo.model;

import java.util.ArrayList;

/**
 * 
 * @author Apathy、恒
 * 
 *         父类分组的实体
 * 
 * */

public class ChildEntity {

	private int groupColor;

	private String groupName;
	private boolean isGroupExpand;

	private ArrayList<String> childNames=new ArrayList<String>();


	/* ==========================================================
	 * ======================= get method =======================
	 * ========================================================== */
	
	public int getGroupColor() {
		return groupColor;
	}

	public String getGroupName() {
		return groupName;
	}

	public ArrayList<String> getChildNames() {
		return childNames;
	}

	/* ==========================================================
	 * ======================= set method =======================
	 * ========================================================== */
	
	public void setGroupColor(int groupColor) {
		this.groupColor = groupColor;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setChildNames(ArrayList<String> childNames) {
		this.childNames = childNames;
	}

	public boolean isGroupExpand() {
		return isGroupExpand;
	}

	public void setGroupExpand(boolean isGroupExpand) {
		this.isGroupExpand = isGroupExpand;
	}

	
}
