package com.app.demo.model;

import java.util.List;

/**
 * @author :LiuJie 2015年11月2日 上午11:10:37
 * @注释:天气预报实体类
 */
public class Weather {
	//空气质量，仅限国内部分城市，国际城市无此字段
	private aqi aqi;
	 //基本信息
	private basic basic;
	//7天天气预报
	private List<daily_forecast> daily_forecasts ;
	//每三小时天气预报，全能版为每小时预报
	private List<hourly_forecast> hourly_forecasts ;
	//实况天气
	private now now;
	//接口状态，参考http://www.heweather.com/documents/api 
	private String status;
	//生活指数，仅限国内城市，国际城市无此字段
	private suggestion suggestion;

	public class aqi {
		private City city;

		public void setCity(City city) {
			this.city = city;
		}

		public City getCity() {
			return this.city;
		}

	}

	public class City {
		private String aqi;

		private String co;

		private String no2;

		private String o3;

		private String pm10;

		private String pm25;

		private String qlty;

		private String so2;

		public void setAqi(String aqi) {
			this.aqi = aqi;
		}

		public String getAqi() {
			return this.aqi;
		}

		public void setCo(String co) {
			this.co = co;
		}

		public String getCo() {
			return this.co;
		}

		public void setNo2(String no2) {
			this.no2 = no2;
		}

		public String getNo2() {
			return this.no2;
		}

		public void setO3(String o3) {
			this.o3 = o3;
		}

		public String getO3() {
			return this.o3;
		}

		public void setPm10(String pm10) {
			this.pm10 = pm10;
		}

		public String getPm10() {
			return this.pm10;
		}

		public void setPm25(String pm25) {
			this.pm25 = pm25;
		}

		public String getPm25() {
			return this.pm25;
		}

		public void setQlty(String qlty) {
			this.qlty = qlty;
		}

		public String getQlty() {
			return this.qlty;
		}

		public void setSo2(String so2) {
			this.so2 = so2;
		}

		public String getSo2() {
			return this.so2;
		}

	}
	
	
	/**
	 * @author :LiuJie 2015年11月2日 上午11:29:10
	 * @注释:更新時間
	 */
	public class update {
		private String loc;

		private String utc;

		public void setLoc(String loc){
		this.loc = loc;
		}
		public String getLoc(){
		return this.loc;
		}
		public void setUtc(String utc){
		this.utc = utc;
		}
		public String getUtc(){
		return this.utc;
		}

		}
	/**
	 * @author LiuJie
	 * @功能:基本信息
	 */
	public class basic {
		private String city;

		private String cnty;

		private String id;

		private String lat;

		private String lon;

		private update update;

		public void setCity(String city){
		this.city = city;
		}
		public String getCity(){
		return this.city;
		}
		public void setCnty(String cnty){
		this.cnty = cnty;
		}
		public String getCnty(){
		return this.cnty;
		}
		public void setId(String id){
		this.id = id;
		}
		public String getId(){
		return this.id;
		}
		public void setLat(String lat){
		this.lat = lat;
		}
		public String getLat(){
		return this.lat;
		}
		public void setLon(String lon){
		this.lon = lon;
		}
		public String getLon(){
		return this.lon;
		}
		public void setUpdate(update update){
		this.update = update;
		}
		public update getUpdate(){
		return this.update;
		}

		}
	
	/**
	 * @author :LiuJie 2015年11月2日 上午11:30:49
	 * @注释:天下状况
	 */
	public class cond {
		private String code;

		private String txt;

		public void setCode(String code){
		this.code = code;
		}
		public String getCode(){
		return this.code;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	/**
	 * @author :LiuJie 2015年11月2日 上午11:31:49
	 * @注释:风向风速
	 */
	public class wind {
		private String deg;

		private String dir;

		private String sc;

		private String spd;

		public void setDeg(String deg){
		this.deg = deg;
		}
		public String getDeg(){
		return this.deg;
		}
		public void setDir(String dir){
		this.dir = dir;
		}
		public String getDir(){
		return this.dir;
		}
		public void setSc(String sc){
		this.sc = sc;
		}
		public String getSc(){
		return this.sc;
		}
		public void setSpd(String spd){
		this.spd = spd;
		}
		public String getSpd(){
		return this.spd;
		}

		}
	
	/**
	 * @author :LiuJie 2015年11月2日 上午11:32:44
	 * @注释:现在天气状况
	 */
	public class now {
		private cond cond;

		private String fl;

		private String hum;

		private String pcpn;

		private String pres;

		private String tmp;

		private String vis;

		private wind wind;

		public void setCond(cond cond){
		this.cond = cond;
		}
		public cond getCond(){
		return this.cond;
		}
		public void setFl(String fl){
		this.fl = fl;
		}
		public String getFl(){
		return this.fl;
		}
		public void setHum(String hum){
		this.hum = hum;
		}
		public String getHum(){
		return this.hum;
		}
		public void setPcpn(String pcpn){
		this.pcpn = pcpn;
		}
		public String getPcpn(){
		return this.pcpn;
		}
		public void setPres(String pres){
		this.pres = pres;
		}
		public String getPres(){
		return this.pres;
		}
		public void setTmp(String tmp){
		this.tmp = tmp;
		}
		public String getTmp(){
		return this.tmp;
		}
		public void setVis(String vis){
		this.vis = vis;
		}
		public String getVis(){
		return this.vis;
		}
		public void setWind(wind wind){
		this.wind = wind;
		}
		public wind getWind(){
		return this.wind;
		}

		}
	
	
	public class comf {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	
	public class cw {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	
	public class drsg {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	
	public class flu {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	/**@注释：运动指数  */
	public class sport {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	
	/**@注释：旅游指数  */
	public class trav {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	/**@注释：紫外线指数  */
	public class uv {
		private String brf;

		private String txt;

		public void setBrf(String brf){
		this.brf = brf;
		}
		public String getBrf(){
		return this.brf;
		}
		public void setTxt(String txt){
		this.txt = txt;
		}
		public String getTxt(){
		return this.txt;
		}

		}
	
	
	public class suggestion {
		/**@注释：舒适度指数  */
		private comf comf;
		 //洗车指数
		private cw cw;
		//穿衣指数
		private drsg drsg;
		 //感冒指数
		private flu flu;
		 //运动指数
		private sport sport;
		 //旅游指数
		private trav trav;
		//紫外线指数
		private uv uv;

		public comf getComf() {
			return comf;
		}

		public void setComf(comf comf) {
			this.comf = comf;
		}

		public cw getCw() {
			return cw;
		}

		public void setCw(cw cw) {
			this.cw = cw;
		}

		public drsg getDrsg() {
			return drsg;
		}

		public void setDrsg(drsg drsg) {
			this.drsg = drsg;
		}

		public flu getFlu() {
			return flu;
		}

		public void setFlu(flu flu) {
			this.flu = flu;
		}

		public sport getSport() {
			return sport;
		}

		public void setSport(sport sport) {
			this.sport = sport;
		}

		public trav getTrav() {
			return trav;
		}

		public void setTrav(trav trav) {
			this.trav = trav;
		}

		public uv getUv() {
			return uv;
		}

		public void setUv(uv uv) {
			this.uv = uv;
		}
	}
	
	/**
	 * @author :LiuJie 2015年11月2日 上午11:49:31
	 * @注释:预报
	 */
	public class daily_forecast {
		private astro astro;

		private cond cond;

		private String date;

		private String hum;

		private String pcpn;

		private String pop;

		private String pres;

		private tmp tmp;

		private String vis;

		private wind wind;
		
		public class astro {
			private String sr;

			private String ss;

			public void setSr(String sr){
			this.sr = sr;
			}
			public String getSr(){
			return this.sr;
			}
			public void setSs(String ss){
			this.ss = ss;
			}
			public String getSs(){
			return this.ss;
			}

			}
		
		public class cond {
			private String code_d;

			private String code_n;

			private String txt_d;

			private String txt_n;

			public void setCode_d(String code_d){
			this.code_d = code_d;
			}
			public String getCode_d(){
			return this.code_d;
			}
			public void setCode_n(String code_n){
			this.code_n = code_n;
			}
			public String getCode_n(){
			return this.code_n;
			}
			public void setTxt_d(String txt_d){
			this.txt_d = txt_d;
			}
			public String getTxt_d(){
			return this.txt_d;
			}
			public void setTxt_n(String txt_n){
			this.txt_n = txt_n;
			}
			public String getTxt_n(){
			return this.txt_n;
			}

			}
		
		public class tmp {
			private String max;

			private String min;

			public void setMax(String max){
			this.max = max;
			}
			public String getMax(){
			return this.max;
			}
			public void setMin(String min){
			this.min = min;
			}
			public String getMin(){
			return this.min;
			}

			}
		
		public class wind {
			private String deg;

			private String dir;

			private String sc;

			private String spd;

			public void setDeg(String deg){
			this.deg = deg;
			}
			public String getDeg(){
			return this.deg;
			}
			public void setDir(String dir){
			this.dir = dir;
			}
			public String getDir(){
			return this.dir;
			}
			public void setSc(String sc){
			this.sc = sc;
			}
			public String getSc(){
			return this.sc;
			}
			public void setSpd(String spd){
			this.spd = spd;
			}
			public String getSpd(){
			return this.spd;
			}

			}

		public astro getAstro() {
			return astro;
		}

		public void setAstro(astro astro) {
			this.astro = astro;
		}

		public cond getCond() {
			return cond;
		}

		public void setCond(cond cond) {
			this.cond = cond;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getHum() {
			return hum;
		}

		public void setHum(String hum) {
			this.hum = hum;
		}

		public String getPcpn() {
			return pcpn;
		}

		public void setPcpn(String pcpn) {
			this.pcpn = pcpn;
		}

		public String getPop() {
			return pop;
		}

		public void setPop(String pop) {
			this.pop = pop;
		}

		public String getPres() {
			return pres;
		}

		public void setPres(String pres) {
			this.pres = pres;
		}

		public tmp getTmp() {
			return tmp;
		}

		public void setTmp(tmp tmp) {
			this.tmp = tmp;
		}

		public String getVis() {
			return vis;
		}

		public void setVis(String vis) {
			this.vis = vis;
		}

		public wind getWind() {
			return wind;
		}

		public void setWind(wind wind) {
			this.wind = wind;
		}
		
		
	}
	
	/**
	 * @author :LiuJie 2015年11月2日 下午1:51:58
	 * @注释:每三小时预报
	 */
	public class hourly_forecast {
		private String date;

		private String hum;

		private String pop;

		private String pres;

		private String tmp;

		private wind wind;
		
		public class wind {
			private String deg;

			private String dir;

			private String sc;

			private String spd;

			public void setDeg(String deg){
			this.deg = deg;
			}
			public String getDeg(){
			return this.deg;
			}
			public void setDir(String dir){
			this.dir = dir;
			}
			public String getDir(){
			return this.dir;
			}
			public void setSc(String sc){
			this.sc = sc;
			}
			public String getSc(){
			return this.sc;
			}
			public void setSpd(String spd){
			this.spd = spd;
			}
			public String getSpd(){
			return this.spd;
			}

			}

		public void setDate(String date){
		this.date = date;
		}
		public String getDate(){
		return this.date;
		}
		public void setHum(String hum){
		this.hum = hum;
		}
		public String getHum(){
		return this.hum;
		}
		public void setPop(String pop){
		this.pop = pop;
		}
		public String getPop(){
		return this.pop;
		}
		public void setPres(String pres){
		this.pres = pres;
		}
		public String getPres(){
		return this.pres;
		}
		public void setTmp(String tmp){
		this.tmp = tmp;
		}
		public String getTmp(){
		return this.tmp;
		}
		public void setWind(wind wind){
		this.wind = wind;
		}
		public wind getWind(){
		return this.wind;
		}

		}

	public aqi getAqi() {
		return aqi;
	}

	public void setAqi(aqi aqi) {
		this.aqi = aqi;
	}

	public basic getBasic() {
		return basic;
	}

	public void setBasic(basic basic) {
		this.basic = basic;
	}

	public List<daily_forecast> getDaily_forecasts() {
		return daily_forecasts;
	}

	public void setDaily_forecasts(List<daily_forecast> daily_forecasts) {
		this.daily_forecasts = daily_forecasts;
	}

	public List<hourly_forecast> getHourly_forecasts() {
		return hourly_forecasts;
	}

	public void setHourly_forecasts(List<hourly_forecast> hourly_forecasts) {
		this.hourly_forecasts = hourly_forecasts;
	}

	public now getNow() {
		return now;
	}

	public void setNow(now now) {
		this.now = now;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(suggestion suggestion) {
		this.suggestion = suggestion;
	}
	
	
	
	
	
	
}
