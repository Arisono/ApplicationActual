package com.app.demo.activity.music;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.demo.R;
import com.app.demo.util.Constants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * @author :LiuJie 2015年8月25日 下午7:51:35
 * @注释:music 显示主界面
 */
public class MainFragment extends Fragment implements Constants {
	
	@ViewInject(R.id.gridview)
	private GridView mGridView;
	@ViewInject(R.id.bottomLayout)
	private RelativeLayout mBottomLayout;
	@ViewInject(R.id.main_layout)
	private RelativeLayout mMainLayout;
	
	private MyGridViewAdapter mAdapter;
	public UIManager mUIManager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view=inflater.inflate(R.layout.music_frame_main_gridlayout, container, false);
    	ViewUtils.inject(view);
    	
    	
    	return view;
    }
      
      
   private class MyGridViewAdapter extends BaseAdapter {
	   //固定的展示图片  
	   private int[] drawable = new int[] { R.drawable.icon_local_music,
				R.drawable.icon_favorites, R.drawable.icon_folder_plus,
				R.drawable.icon_artist_plus, R.drawable.icon_album_plus };
		private String[] name = new String[] { "我的音乐", "我的最爱", "文件夹", "歌手",
		"专辑" };
	    private int musicNum = 0, artistNum = 0, albumNum = 0, folderNum = 0, favoriteNum = 0;
	
	   
		@Override
		public int getCount() {
			return 5;
		}
	
		@Override
		public Object getItem(int position) {
			return null;
		}
	
		@Override
		public long getItemId(int position) {
			return position;
		}
	    
		public void setNum(int music_num, int artist_num, int album_num,
				int folder_num, int favorite_num) {
			musicNum = music_num;
			artistNum = artist_num;
			albumNum = album_num;
			folderNum = folder_num;
			favoriteNum = favorite_num;
			notifyDataSetChanged();
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView==null) {
				holder = new ViewHolder();
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.music_main_gridview_item, null);
				holder.iv = (ImageView) convertView
						.findViewById(R.id.gridview_item_iv);
				holder.nameTv = (TextView) convertView
						.findViewById(R.id.gridview_item_name);
				holder.numTv = (TextView) convertView
						.findViewById(R.id.gridview_item_num);
				convertView.setTag(holder);
				
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			
			convertView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					int from = -1;
					switch (position) {
					case 0:// 我的音乐
						from = START_FROM_LOCAL;
						break;
					case 1:// 我的最爱
						from = START_FROM_FAVORITE;
						break;
					case 2:// 文件夹
						from = START_FROM_FOLDER;
						break;
					case 3:// 歌手
						from = START_FROM_ARTIST;
						break;
					case 4:// 专辑
						from = START_FROM_ALBUM;
						break;
					}
					mUIManager.setContentType(from);
				}
			});

			holder.iv.setImageResource(drawable[position]);
			holder.nameTv.setText(name[position]);
			return convertView;
		}
		
		private class ViewHolder {
			ImageView iv;
			TextView nameTv, numTv;
		}
	  
   }    
}
