package com.app.demo.model;

import java.util.List;

public class Event {
	/** 列表加载事件 */  
    public static class ItemListEvent  
    {  
        private List<TestItemOne> items;  
  
        public ItemListEvent(List<TestItemOne> items)  
        {  
            this.items = items;  
        }  
  
        public List<TestItemOne> getItems()  
        {  
            return items;  
        }  
    }  
}
