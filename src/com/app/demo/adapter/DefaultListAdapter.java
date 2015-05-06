package com.app.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import org.jetbrains.annotations.NotNull;



import com.app.demo.R;
import com.app.demo.model.DemoItem;

import java.util.List;

/**
 * Sample adapter implementation extending from AsymmetricGridViewAdapter<DemoItem>
 * This is the easiest way to get started.
 */
public class DefaultListAdapter extends ArrayAdapter<DemoItem> implements DemoAdapter {

  private final LayoutInflater layoutInflater;

  public DefaultListAdapter(Context context, List<DemoItem> items) {
    super(context, 0, items);
    layoutInflater = LayoutInflater.from(context);
  }

  public DefaultListAdapter(Context context) {
    super(context, 0);
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  @SuppressWarnings("deprecation")
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
    TextView v;

    DemoItem item = getItem(position);

    if (convertView == null) {
      v = (TextView) layoutInflater.inflate(R.layout.act_adapter_item_grid_asymm, parent, false);
    } else {
      v = (TextView) convertView;
    }

    v.setText(String.valueOf(item.getPosition()));

    return v;
  }

  public void appendItems(List<DemoItem> newItems) {
    addAll(newItems);
    notifyDataSetChanged();
  }

  public void setItems(List<DemoItem> moreItems) {
    clear();
    appendItems(moreItems);
  }
}