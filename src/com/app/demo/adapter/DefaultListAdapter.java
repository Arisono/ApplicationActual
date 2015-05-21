package com.app.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.app.demo.R;
import com.app.demo.model.DemoItem;

import java.util.List;

/**AsymmetricGridViewAdapter
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
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
	  LinearLayout v;
    TextView tv=null;
    DemoItem item = getItem(position);

    if (convertView == null) {
      v = (LinearLayout) layoutInflater.inflate(R.layout.act_adapter_item_grid_asymm, parent, false);
      tv=(TextView) v.findViewById(R.id.text);
    } else {
      v = (LinearLayout) convertView;
      tv=(TextView) v.findViewById(R.id.text);
    }
    if (item.getPosition()==0) {
    	tv.setText(String.valueOf(item.getTitle()));
	}else{
    tv.setText(String.valueOf(item.getPosition()));
	}

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