package cn.mvtech.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 
 * @author 徐童
 *
 */
public class HttpClientUtil {
	private static final Logger logger=LogManager.getLogger(HttpClientUtil.class);
	private static final String APPLICATION_JSON = "application/json";	
	/**
	 * post请求
	 * @param url
	 * @param jsonObject
	 * @return
	 */
	public static String httpPost(String url, String jsonObject) {
		logger.info("httpPost => ***---------------发送接口的URL------------***"+url);
		logger.info("httpPost => ***---------------发送接口的参数------------***"+jsonObject);
		CloseableHttpClient httpClient = null;
		String result = "";
		//配置超时时间
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(40000)
				.setConnectTimeout(40000)
				.setConnectionRequestTimeout(40000)
				.build();
		RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		try {
			HttpPost httpPost=new HttpPost(url);
			//设置hearder信息
	        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	        StringEntity se = new StringEntity(jsonObject, ContentType.APPLICATION_JSON);
	        httpPost.setConfig(requestConfig);
	        httpPost.setEntity(se);
	        HttpResponse response;
			response = httpClient.execute(httpPost);
			//判定返回状态是否成功
	        int flag = response.getStatusLine().getStatusCode();
	        if(flag == 200){
	        	logger.info("httpPost => ***---------------接口机连接成功------------***");
	        	result=EntityUtils.toString(response.getEntity(),Charset.defaultCharset());
	        }else {
	        	logger.info("httpPost => ***---------------接口机连接失败------------***");
			}
		} catch (ClientProtocolException e) {
			logger.error("httpPost => ***-----接口机连接异常----------ClientProtocolException***"+e);
		} catch (IOException e) {
			logger.error("httpPost => ***-----接口机连接异常----------IOException***"+e);
		}finally {
			if(httpClient != null){
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("httpPost => 关闭失败"+ e);
				}
			}
		}
		return result;		
	}
	
	
	
	
}
