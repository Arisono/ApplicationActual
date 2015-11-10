package com.app.demo.model;

import java.util.List;

/**
 * @author :LiuJie 2015年11月9日 上午10:05:37
 * @注释:
 */
public class NewsEntity {
	private int showapi_res_code;
	private String showapi_res_error;
	private BodyEntity showapi_res_body;

	public class BodyEntity {
		private Pagebean pagebean;

		private int ret_code;

		public void setPagebean(Pagebean pagebean) {
			this.pagebean = pagebean;
		}

		public Pagebean getPagebean() {
			return this.pagebean;
		}

		public void setRet_code(int ret_code) {
			this.ret_code = ret_code;
		}

		public int getRet_code() {
			return this.ret_code;
		}
		
		public class Pagebean {
			private int allNum;

			private int allPages;

			private List<Contentlist> contentlist;

			private int currentPage;

			private int maxResult;

			public void setAllNum(int allNum) {
				this.allNum = allNum;
			}

			public int getAllNum() {
				return this.allNum;
			}

			public void setAllPages(int allPages) {
				this.allPages = allPages;
			}

			public int getAllPages() {
				return this.allPages;
			}

			public void setContentlist(List<Contentlist> contentlist) {
				this.contentlist = contentlist;
			}

			public List<Contentlist> getContentlist() {
				return this.contentlist;
			}

			public void setCurrentPage(int currentPage) {
				this.currentPage = currentPage;
			}

			public int getCurrentPage() {
				return this.currentPage;
			}

			public void setMaxResult(int maxResult) {
				this.maxResult = maxResult;
			}

			public int getMaxResult() {
				return this.maxResult;
			}
			
			
			public class Contentlist {
				private String channelId;

				private String channelName;

				private int chinajoy;

				private String desc;

				private List<Imageurls> imageurls;

				private String link;

				private String nid;

				private String pubDate;

				private String source;

				private String title;

				public void setChannelId(String channelId) {
					this.channelId = channelId;
				}

				public String getChannelId() {
					return this.channelId;
				}

				public void setChannelName(String channelName) {
					this.channelName = channelName;
				}

				public String getChannelName() {
					return this.channelName;
				}

				public void setChinajoy(int chinajoy) {
					this.chinajoy = chinajoy;
				}

				public int getChinajoy() {
					return this.chinajoy;
				}

				public void setDesc(String desc) {
					this.desc = desc;
				}

				public String getDesc() {
					return this.desc;
				}

				public void setImageurls(List<Imageurls> imageurls) {
					this.imageurls = imageurls;
				}

				public List<Imageurls> getImageurls() {
					return this.imageurls;
				}

				public void setLink(String link) {
					this.link = link;
				}

				public String getLink() {
					return this.link;
				}

				public void setNid(String nid) {
					this.nid = nid;
				}

				public String getNid() {
					return this.nid;
				}

				public void setPubDate(String pubDate) {
					this.pubDate = pubDate;
				}

				public String getPubDate() {
					return this.pubDate;
				}

				public void setSource(String source) {
					this.source = source;
				}

				public String getSource() {
					return this.source;
				}

				public void setTitle(String title) {
					this.title = title;
				}

				public String getTitle() {
					return this.title;
				}
				
				public class Imageurls {
					private int height;
					private String url;
					private int width;

					public void setHeight(int height) {
						this.height = height;
					}

					public int getHeight() {
						return this.height;
					}

					public void setUrl(String url) {
						this.url = url;
					}

					public String getUrl() {
						return this.url;
					}

					public void setWidth(int width) {
						this.width = width;
					}

					public int getWidth() {
						return this.width;
					}
				}

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

	public BodyEntity getShowapi_res_body() {
		return showapi_res_body;
	}

	public void setShowapi_res_body(BodyEntity showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}

}
