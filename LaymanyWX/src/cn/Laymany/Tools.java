package cn.Laymany;

import com.alibaba.fastjson.JSONObject;

public class Tools {
	public static String getToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

		url = url.replace("APPID", "wx49b4b211b15ff2f5").replace("APPSECRET", "4a20b5005c540da4fc788e16f57e9b9e");
		
		String r = MyRequest.get(url);
		System.out.println(r);
		
		JSONObject json = JSONObject.parseObject(r);
		return json.getString("access_token");
	}
	public static void main(String[] args) {
		System.out.println("I am Superman !");

		System.out.println(getToken());
		
		
	}
}
