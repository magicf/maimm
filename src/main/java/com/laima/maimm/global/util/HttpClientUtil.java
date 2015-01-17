package com.laima.maimm.global.util;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jiayi.zhang
 * 
 */
@Component("httpClientUtil")
public class HttpClientUtil {

	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	private HttpClient httpClient = new HttpClient();

	public HttpClientUtil() {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		httpClient.setHttpConnectionManager(connectionManager);
		httpClient.getParams().setContentCharset("UTF-8");
	}

	public String sendPost(String url, Map<String, String> params) {
		String result = null;
		PostMethod post = new PostMethod(url);
		for (String key : params.keySet()) {
			post.addParameter(new NameValuePair(key, params.get(key)));
		}
		try {
			int status = httpClient.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString().trim();
			}
		} catch (Exception e) {
			log.error("获取远程数据失败：" + url, e);
		} finally {
			post.releaseConnection();
		}
		return result;
	}

	public String sendPost(String url) {
		String result = null;
		PostMethod post = new PostMethod(url);
		try {
			int status = httpClient.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString().trim();
			}
		} catch (Exception e) {
			log.error("获取远程数据失败：" + url, e);
		} finally {
			post.releaseConnection();
		}
		return result;
	}
}
