package com.app.demo.model;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;



/**@ע�ͣ�
 *  ʵ��Parcelable����
	1��implements Parcelable
	2����дwriteToParcel����������Ķ������л�Ϊһ��Parcel���󣬼������������д���ⲿ�ṩ��Parcel�У������Ҫ���ݵ����ݵ�Parcel�������棬�Ա�� Parcel������ȡ����
	3����дdescribeContents���������ݽӿ�������Ĭ�Ϸ���0�Ϳ���
	4��ʵ������̬�ڲ�����CREATORʵ�ֽӿ�Parcelable.Creator
	public static final Parcelable.Creator<T> CREATOR
 *  2015��5��8�� */
public class DemoItem implements AsymmetricItem {

  private int columnSpan;
  private int rowSpan;
  private int position;
  
  private String title;

  public DemoItem() {
    this(1, 1, 0);
  }

  public DemoItem(int columnSpan, int rowSpan, int position) {
    this.columnSpan = columnSpan;
    this.rowSpan = rowSpan;
    this.position = position;
  }
  
  public DemoItem(int columnSpan, int rowSpan, String title) {
	    this.columnSpan = columnSpan;
	    this.rowSpan = rowSpan;
	    this.title = title;
   }

  public DemoItem(Parcel in) {
    readFromParcel(in);
  }

  @Override public int getColumnSpan() {
    return columnSpan;
  }

  @Override public int getRowSpan() {
    return rowSpan;
  }

  public int getPosition() {
    return position;
  }
  
  public String getTitle(){
	 return title;
  }

  @Override public String toString() {
    return String.format("%s: %sx%s", position, rowSpan, columnSpan);
  }

  @Override public int describeContents() {
    return 0;
  }

  private void readFromParcel(Parcel in) {
    columnSpan = in.readInt();
    rowSpan = in.readInt();
    position = in.readInt();
    title=in.readString();
  }

  @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeInt(columnSpan);
    dest.writeInt(rowSpan);
    dest.writeInt(position);
    dest.writeString(title);
  }

  /* Parcelable interface implementation */
  public static final Parcelable.Creator<DemoItem> CREATOR = new Parcelable.Creator<DemoItem>() {

    @Override public DemoItem createFromParcel(@NonNull Parcel in) {
      return new DemoItem(in);
    }

    @Override @NonNull public DemoItem[] newArray(int size) {
      return new DemoItem[size];
    }
  };
}
