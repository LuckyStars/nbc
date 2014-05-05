package org.luckystars.weixin.transfer.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.utils.json.GsonUtil;


/**
 * 获取应用的access_token
 * @author xuechong
 */
public class AppAccessToken {
	
	private static final Logger logger = Logger.getLogger(AppAccessToken.class);
	
	private static volatile String accessToken = null;
	
	private static final long EXPIRE_TIME = 1000L*7100L;
	
	private static final String ACCESS_TOKEN_JSON_KEY = "access_token";
	
	private AppAccessToken(){}
	
	public static String getAccessToken(){
		if(accessToken==null){
			refreshToken();
		}
		return accessToken;
	}

	private static void refreshToken() {
		fetchAccessToken();
		Timer timer = new Timer();
		timer.schedule(new RefreshTimer(), EXPIRE_TIME);//7200s 过期
	}
	

	private static void fetchAccessToken() {
		String url = getFetchUrl();
		String fetchResult = fetchJson(url);
		String token = getResultFromJson(fetchResult);
		accessToken = token;
	}


	private static String getFetchUrl() {
		StringBuilder fetchUrl = new StringBuilder();
		fetchUrl.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
		fetchUrl.append("&appid=");
		fetchUrl.append(AppContext.getContext().get(Constants.APPID_CONTEXT_KEY));
		fetchUrl.append("&secret=");
		fetchUrl.append(AppContext.getContext().get(Constants.APPSECRET_CONTEXT_KEY));
		return fetchUrl.toString();
	}
	
	private static String fetchJson(String url) {
		StringBuilder result = new StringBuilder("");
		try {
			HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
			conn.setDoOutput(Boolean.TRUE);
			conn.setRequestMethod("GET");
			conn.connect();
			
			if(conn.getResponseCode()==200){
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				try {
					for (String line = reader.readLine();line!=null;line = reader.readLine()) {
						result.append(line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{if (reader!=null) reader.close();}
			}
			
		} catch (MalformedURLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return result.toString();
	}
	
	private static String getResultFromJson(String fetchResult) {
		return GsonUtil.fromJson(fetchResult).get(ACCESS_TOKEN_JSON_KEY);
	}


	private static class RefreshTimer extends TimerTask{
		@Override
		public void run() {
			accessToken = null;
		}
	}
}
