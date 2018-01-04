package com.trans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trans.bean.ResultBean;

/**
 * @Description: TODO
 * @author :lushaorong
 */
public class TransTest {
	 public static void main(String[] args) throws Exception {
	        String appKey =ReadConfig.getValue("appKey");
	        String src = "smartphones";
	        String query = new String(src.getBytes(),Charset.forName("UTF-8"));
	        String salt = String.valueOf(System.currentTimeMillis());
	        String from = "dd";
	        String to = "rr";
	        String sign = md5(appKey + query + salt+ ReadConfig.getValue("key"));
	        Map params = new HashMap();
	        params.put("q", query);
	        params.put("from", from);
	        params.put("to", to);
	        params.put("sign", sign);
	        params.put("salt", salt);
	        params.put("appKey", appKey);
	        System.out.println(requestForHttp("http://openapi.youdao.com/api", params));
//		 String src = "People are using their smartphones for shopping and riding shared bikes. And searches for wallets on e-commerce platforms are declining for the first time, statistics show. Last year, a total of 10.46 million Shanghai Alipay users adopted Ant Forest, an environmental initiative meant to encourage Alipay users to keep track of their low-carbon activities.";
//		
//		 long startTime = System.currentTimeMillis(); 
//		 
//		 ResultBean result =  YouDaoTranUtil.autoTran(src, "EN", "EN");
//		 
//		 long endTime = System.currentTimeMillis();  
//		 
//		 System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); 
//		 
//		 System.out.println(src);
//		 System.out.println("英式音标");
////		 System.out.println(result.getUkphonetic());
//		 System.out.println(result.toWordAndUK());
//		 System.out.println("美式音标");
////		 System.out.println(result.getUsphonetic());
//		 System.out.println(result.toWordAndUS());
	    }

	    public static String requestForHttp(String url,Map requestParams) throws Exception{
	        String result = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        /**HttpPost*/
	        HttpPost httpPost = new HttpPost(url);
	        System.out.println(new JSONObject(requestParams).toString());
	        List params = new ArrayList();
	        Iterator it = requestParams.entrySet().iterator();
	        while (it.hasNext()) {
	            Entry en = (Entry) it.next();
	            String key = (String) en.getKey();
	            String value = (String) en.getValue();
	            if (value != null) {
	                params.add(new BasicNameValuePair(key, value));
	            }
	        }
	        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
	        /**HttpResponse*/
	        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
	        try{
	            HttpEntity httpEntity = httpResponse.getEntity();
	            result = EntityUtils.toString(httpEntity, "utf-8");
	            EntityUtils.consume(httpEntity);
	        }finally{
	            try{
	                if(httpResponse!=null){
	                    httpResponse.close();
	                }
	            }catch(IOException e){
	                e.printStackTrace();
	            }
	        }
	        return result;
	    }

	    /**
	     * 生成32位MD5摘要
	     * @param string
	     * @return
	     */
	    public static String md5(String string) {
	        if(string == null){
	            return null;
	        }
	        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                'A', 'B', 'C', 'D', 'E', 'F'};

	        try{
	            byte[] btInput = string.getBytes("utf-8");
	            /** 获得MD5摘要算法的 MessageDigest 对象 */
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            /** 使用指定的字节更新摘要 */
	            mdInst.update(btInput);
	            /** 获得密文 */
	            byte[] md = mdInst.digest();
	            /** 把密文转换成十六进制的字符串形式 */
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (byte byte0 : md) {
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
	            return null;
	        }
	    }

	    /**
	     * 根据api地址和参数生成请求URL
	     * @param url
	     * @param params
	     * @return
	     */
	    public static String getUrlWithQueryString(String url, Map<String,Object> params) {
	        if (params == null) {
	            return url;
	        }

	        StringBuilder builder = new StringBuilder(url);
	        if (url.contains("?")) {
	            builder.append("&");
	        } else {
	            builder.append("?");
	        }

	        int i = 0;
	        for (String key : params.keySet()) {
	            String value = (String) params.get(key);
	            if (value == null) { // 过滤空的key
	                continue;
	            }

	            if (i != 0) {
	                builder.append('&');
	            }

	            builder.append(key);
	            builder.append('=');
	            builder.append(encode(value));

	            i++;
	        }

	        return builder.toString();
	    }
	    /**
	     * 进行URL编码
	     * @param input
	     * @return
	     */
	    public static String encode(String input) {
	        if (input == null) {
	            return "";
	        }

	        try {
	            return URLEncoder.encode(input, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }

	        return input;
	    }
}


