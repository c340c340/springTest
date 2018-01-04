package com.trans.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: TODO
 * @author :lushaorong
 */
public class ResultBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6000491148332554906L;
	
	private String src;	//源文
	private String ukphonetic;	//英式音标
	private String usphonetic;	//美式音标
	private List<String> words;
	private HashMap<String,BasicBean> transResults;
	
	public ResultBean(String src,List<String> words,HashMap<String,BasicBean> transResults){
		this.src =  src;
		this.words = words;
		this.transResults =transResults;
		
		mergeUKphonetic();
		mergeUSphonetic();
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
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
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}
	public HashMap<String, BasicBean> getTransResults() {
		return transResults;
	}
	public void setTransResults(HashMap<String, BasicBean> transResults) {
		this.transResults = transResults;
	}
	
	private void mergeUKphonetic(){
		StringBuilder temp = new StringBuilder();
		for(String word : words){
			BasicBean basic = transResults.get(word);
			if(basic != null && !"".equals(basic.toUKphonetic())){
				temp.append(basic.toUKphonetic()).append(" ");
			}else{
				temp.append(word).append(" ");
			}			
		}		
		
		this.ukphonetic = temp.toString();
	};
	
	private void mergeUSphonetic(){
		StringBuilder temp = new StringBuilder();
		for(String word : words){
			BasicBean basic = transResults.get(word);
			if(basic != null && !"".equals(basic.toUSphonetic())){
				temp.append(basic.toUSphonetic()).append(" ");
			}else{
				temp.append(word).append(" ");
			}			
		}	
		this.usphonetic = temp.toString();
	}
	
	public String toWordAndUK(){
		StringBuilder temp = new StringBuilder();
		for(String word : words){
			BasicBean basic = transResults.get(word);
			if(basic != null && !"".equals(basic.toUKphonetic()) ){
				temp.append(word);
				temp.append(basic.toUKphonetic()).append("  ");
			}else{
				temp.append(word).append("  ");
			}			
		}		
		
		
		return temp.toString();
	}
	
	public String toWordAndUS(){
		StringBuilder temp = new StringBuilder();
		for(String word : words){
			BasicBean basic = transResults.get(word);
			if(basic != null && !"".equals(basic.toUSphonetic())){
				temp.append(word);
				temp.append(basic.toUSphonetic()).append("  ");
			}else{
				temp.append(word).append("  ");
			}			
		}		
		
		
		return temp.toString();
	}
	
	
}


