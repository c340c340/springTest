package com.trans.bean;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author :lushaorong
 */
public class BasicBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8686950877294499979L;
	
	private String word;
	private String phonetic;	
	private String ukphonetic; //英式发音
	private String usphonetic;	//美式发音
	private String[] explains;
	
	private String openToken;
	private String endToken;
	
	public BasicBean(){
		this.openToken = "[";
		this.endToken = "]";
		this.explains = new String[1];
	}
	
	public BasicBean(String openToken,String endToken){
		this.openToken = openToken;
		this.endToken = endToken;
		this.explains = new String[1];
	}
	
	public String getPhonetic() {
		return phonetic;
	}
	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}
	public String getUkphonetic() {
		return ukphonetic;
	}
	public void setUkphonetic(String ukphonetic) {
		this.ukphonetic = ukphonetic;
	}
	public String getUsphonetic() {
		return usphonetic;
	}
	public void setUsphonetic(String usphonetic) {
		this.usphonetic = usphonetic;
	}
	public String[] getExplains() {
		return explains;
	}
	public void setExplains(String[] explains) {
		this.explains = explains;
	}
	public String getOpenToken() {
		return openToken;
	}
	public void setOpenToken(String openToken) {
		this.openToken = openToken;
	}
	public String getEndToken() {
		return endToken;
	}
	public void setEndToken(String endToken) {
		this.endToken = endToken;
	}
	
	
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * 
	 * @Description: 输出英式音标
	 * @date: 2018年1月4日
	 * @author :lushaorong
	 * @return     
	 * @throws
	 */
	public String toUKphonetic(){
		return this.ukphonetic == null?"":this.openToken+this.ukphonetic+this.endToken;
	}
	
	/**
	 * 
	 * @Description: 输出美式音标
	 * @date: 2018年1月4日
	 * @author :lushaorong
	 * @return     
	 * @throws
	 */
	public String toUSphonetic(){
		return this.usphonetic == null?"":this.openToken+this.usphonetic+this.endToken;
	}
	
	
}


