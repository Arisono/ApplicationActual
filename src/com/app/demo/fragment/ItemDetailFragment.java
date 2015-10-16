package com.app.demo.fragment;

import com.app.demo.R;
import com.app.demo.fragment.ItemListFragment.onClicItemEvent;
import com.app.demo.model.TestItemOne;

import de.greenrobot.event.EventBus;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemDetailFragment extends Fragment implements onClicItemEvent {
	  private TextView tvDetail;  
	  
	    @Override  
	    public void onCreate(Bundle savedInstanceState)  
	    {  
	        super.onCreate(savedInstanceState);  
	        // register  
	        EventBus.getDefault().register(this);  
	        //接口
	        ItemListFragment listFragment=new ItemListFragment();
	        listFragment.setClicItemEvent(this);
	    }  
	  
	    @Override  
	    public void onDestroy()  
	    {  
	        super.onDestroy();  
	        // Unregister  
	        EventBus.getDefault().unregister(this);  
	    }  
	  
	    /** List点击时会发送些事件，接收到事件后更新详情 */  
	    public void onEventMainThread(TestItemOne item)  
	    {  
	        if (item != null)  
	            tvDetail.setText(item.content);  
	    }  
	  
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState)  
	    {  
	        View rootView = inflater.inflate(R.layout.fragment_item_detail,  
	                container, false);  
	        tvDetail = (TextView) rootView.findViewById(R.id.tv_item_detail);  
	        return rootView;  
	    }

		@Override
		public void updateTextView(TestItemOne item) {
			onEventMainThread(item)  ;			
		}  
}
