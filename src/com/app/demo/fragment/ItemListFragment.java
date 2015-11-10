package com.app.demo.fragment;

import com.app.demo.model.Event.ItemListEvent;
import com.app.demo.model.TestItemOne;

import de.greenrobot.event.EventBus;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author :LiuJie 2015年11月6日 下午3:30:23
 * @注释:左侧布局
 */
public class ItemListFragment extends ListFragment {
	

	private  onClicItemEvent clicItemEvent;
	
	public onClicItemEvent getClicItemEvent() {
		return clicItemEvent;
	}

	public void setClicItemEvent(onClicItemEvent clicItemEvent) {
		this.clicItemEvent = clicItemEvent;
	}

	public interface onClicItemEvent{
		public void updateTextView(TestItemOne item);
	}
	private Handler myHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				ItemListEvent event=new ItemListEvent(TestItemOne.TestItemOneS);
				onEventMainThread(event);
				break;
			default:
				break;
			}
		};
	};
     @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	EventBus.getDefault().register(this);
    }
     
     @Override
    public void onDestroy() {
    	super.onDestroy();
    	EventBus.getDefault().unregister(this);
    }
     
     @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				  try  
	                {  
	                    Thread.sleep(2000); // 模拟延时  
	                    // 发布事件，在后台线程发的事件  
	                    //  EventBus.getDefault().post(new ItemListEvent(TestItemOne.TestItemOneS));  
	                   //handler的方式
	                    myHandler.sendEmptyMessage(0);
	                
	                } catch (Exception e)  
	                {  
	                    e.printStackTrace();  
	                }  
			}
		}).start();
    }
     
    /**@注释：level 11  */
     public void onEventMainThread(ItemListEvent event) {
    	  setListAdapter(new ArrayAdapter<TestItemOne>(getActivity(),  
                  android.R.layout.simple_list_item_activated_2,  
                  android.R.id.text1, event.getItems()));  
	 }
     
     @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
    	super.onListItemClick(l, v, position, id);
      //EventBus.getDefault().post(getListView().getItemAtPosition(position));
    	clicItemEvent.updateTextView((TestItemOne)getListView().getItemAtPosition(position));
    }
     
     
}
