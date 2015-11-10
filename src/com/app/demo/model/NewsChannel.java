package com.app.demo.model;

import java.util.List;

/**
 * @author :LiuJie 2015年11月9日 上午9:47:20
 * @注释:新闻频道
 */
public class NewsChannel{
	private int showapi_res_code;
	private String showapi_res_error;
	private ChannelEntity showapi_res_body;

	public class ChannelEntity{
		private int ret_code;
		private int totalNum;
		private List<ChannelList> channelList;

		public int getRet_code() {
			return ret_code;
		}

		public void setRet_code(int ret_code) {
			this.ret_code = ret_code;
		}

		public int getTotalNum() {
			return totalNum;
		}

		public void setTotalNum(int totalNum) {
			this.totalNum = totalNum;
		}

		public List<ChannelList> getChannelList() {
			return channelList;
		}

		public void setChannelList(List<ChannelList> channelList) {
			this.channelList = channelList;
		}

		public class ChannelList{
			private String channelId;

			private String name;

			public void setChannelId(String channelId) {
				this.channelId = channelId;
			}

			public String getChannelId() {
				return this.channelId;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getName() {
				return this.name;
			}

		}

	}

	
	public int getShowapi_res_code() {
		return showapi_res_code;
	}

	public void setShowapi_res_code(int showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}

	public String getShowapi_res_error() {
		return showapi_res_error;
	}

	public void setShowapi_res_error(String showapi_res_error) {
		this.showapi_res_error = showapi_res_error;
	}

	public ChannelEntity getShowapi_res_body() {
		return showapi_res_body;
	}

	public void setShowapi_res_body(ChannelEntity showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}

}
