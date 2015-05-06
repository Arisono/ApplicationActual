package com.app.demo.adapter;

import android.widget.ListAdapter;

import java.util.List;

import com.app.demo.model.DemoItem;

public interface DemoAdapter extends ListAdapter {

  void appendItems(List<DemoItem> newItems);

  void setItems(List<DemoItem> moreItems);
}
