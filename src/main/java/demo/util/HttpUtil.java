package demo.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String post(String url, Map<String, String> params) {
		// 创建Http Post请求
		HttpPost httpPost = new HttpPost(url);
		// 创建参数列表
		if (params != null) {
			// 模拟表单
			// List<NameValuePair> paramList =
			// params.entrySet().parallelStream().map(entry -> new
			// BasicNameValuePair(entry.getKey(),
			// entry.getValue())).collect(Collectors.toList());
			// UrlEncodedFormEntity entity = new
			// UrlEncodedFormEntity(paramList);
			// json
			String jsonString = JSON.toJSONString(params);
			StringEntity stringEntity = new StringEntity(jsonString, "utf-8");
			stringEntity.setContentType(ContentType.APPLICATION_JSON.toString());

			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(stringEntity);
		}
		return response(httpPost);
	}

	public static String post(String url) {
		return post(url, null);
	}

	public static String get(String url) {
		// 创建Http Get请求
		HttpGet httpGet = new HttpGet(url);
		return response(httpGet);
	}

	private static String response(HttpRequestBase request) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 执行http请求
			response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() != 200) {
				resultString = "error";
			} else {
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
}
