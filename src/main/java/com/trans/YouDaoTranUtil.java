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
import java.util.Scanner;

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
import com.trans.bean.BasicBean;
import com.trans.bean.OutPutType;
import com.trans.bean.ResultBean;
import com.trans.type.ErrorCode;

/**
 * @Description: TODO
 * @author :lushaorong
 */
public class YouDaoTranUtil {
	

	
	public static  ResultBean autoTran(String src,String from , String to){
		//分割单词
		List<String> words = separatedWord(src);
		//查询单词
		HashMap<String,BasicBean> transResults = transWords(words,from,to);
		//构造查询结果
		ResultBean result = new ResultBean(src, words, transResults);				
		return result;
	}
	
	/**
	 * 
	 * @Description: 分割单词
	 * @date: 2018年1月4日
	 * @author :lushaorong
	 * @param src
	 * @return     
	 * @throws
	 */
	private static List<String> separatedWord(String src){		
	     List<String> words = new ArrayList<String>();
	     //匹配",", "?", "!", """, ":", "(", ")", "-"
         Scanner s = new Scanner(src).useDelimiter(" |,|\\?|\\.|!|:|\"|-|\\(|\\)");
         while(s.hasNext()){
        	 String word = s.next();
//             System.out.println(word);
             words.add(word);
         }
         
         return words;
	}
	
	/**
	 * 
	 * @Description: 循环查询所有单词
	 * @date: 2018年1月4日
	 * @author :lushaorong
	 * @param words
	 * @param from
	 * @param to
	 * @return     
	 * @throws
	 */
	private static HashMap<String,BasicBean> transWords(List<String> words,String from ,String to ){
		HashMap<String,BasicBean> transResults = new HashMap<String,BasicBean>();
		
		if(words != null){
			for(String word : words){
				if(!transResults.containsKey(word)){//减少查询次数
					JSONObject originalResult =  trans(word, from, to);
					BasicBean basic = parseResult(originalResult);
					transResults.put(word, basic);
				}
				
			}
		}
		
		return transResults;
		
	}
		
	
	/**
	 * 
	 * @Description: 底层查询api
	 * @date: 2018年1月4日
	 * @author :lushaorong
	 * @param src
	 * @param from
	 * @param to
	 * @return     
	 * @throws
	 */
	private static JSONObject trans(String src,String from , String to){
			        
	        JSONObject bean = null;
	        try {
	        	String result = trans1(src, from, to);
				bean = new JSONObject().parseObject(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		return bean;
	}
	
	public static String trans1(String src,String from , String to){
		String appKey =ReadConfig.getValue("appKey");
        String query = new String(src.getBytes(),Charset.forName("UTF-8"));
        String salt = String.valueOf(System.currentTimeMillis());
        String sign = md5(appKey + query + salt+ ReadConfig.getValue("key"));
        Map params = new HashMap();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKey);
        
        String result = "";
        try {
        	result = requestForHttp("http://openapi.youdao.com/api", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	
	}
	
	
	/**
	 * 
	 * @Description: 解析结果，提取单词音标
	 * @date: 2018年1月4日
	 * @author :lushaorong
	 * @param result
	 * @return     
	 * @throws
	 */
	private static BasicBean parseResult(JSONObject result){
		BasicBean bean = null;
		if(result != null){
			String errorCode = result.getString("errorCode");
			if(ErrorCode.SUCCESS.value.equals(errorCode)){//成功处理
				JSONObject basic = result.getJSONObject("basic");
				if(basic != null){
					bean = new BasicBean();
					bean.setWord(result.getString("query"));
					bean.setUkphonetic(basic.getString("uk-phonetic"));
					bean.setUsphonetic(basic.getString("us-phonetic"));
					bean.setPhonetic(basic.getString("phonetic"));
					bean.setExplains(basic.getJSONArray("explains").toArray(bean.getExplains()));
				}
			}
			
		}
				
		return bean;
	}
	
	
	private static  String requestForHttp(String url,Map requestParams) throws Exception{
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);
//        System.out.println(new JSONObject(requestParams).toString());
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
    private static  String md5(String string) {
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
    private static String getUrlWithQueryString(String url, Map<String,Object> params) {
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
    private static String encode(String input) {
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


