package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GeneralKey {

	// 编码
	public static final String UTF8 = "UTF-8";

	public static String convertParams(Map<String, Object> params)
			throws Exception {
		StringBuilder sb = new StringBuilder();

		if (params != null && params.size() > 0) {
			List<String> paramNames = new ArrayList<String>(params.size());
			paramNames.addAll(params.keySet());
			Collections.sort(paramNames);
			for (String paramName : paramNames) {
				String value = String.valueOf(params.get(paramName));
				value = URLEncoder.encode(value == null ? "null" : value,
						"UTF-8");
				sb.append(paramName).append("=").append(value).append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String sign(String url, Map<String, Object> params) {

		String sign = "";
		try {
			String paramString = convertParams(params);
			String path = "";
			if (null != url) {
				URI uri = new URI(url);
				path = uri.getHost() + uri.getPath();
			}
			sign = getMd5(getMd5(path, UTF8) + paramString, UTF8);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sign;
	}
	
	public static String getMd5(String value, String charset) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(value.getBytes(charset));
			return toHexString(md5.digest());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}

	private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static void main(String[] s) throws UnsupportedEncodingException {
		
	}
}
