package ioc;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class BeanFactory {
//	public static String classpath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();  
	
	public static void main(String[] args) throws DocumentException {
		System.out.println(BeanFactory.class.getClassLoader().getResource("").getPath());
		
		
		
		
//		  SAXReader sax = new SAXReader();  
//          // 获得dom4j的文档对象  
//          Document root = sax.read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));  
//          // 得到database节点  
//          Element database = (Element) root  
//                  .selectSingleNode("//employee[@id='2']");  
//          List list = database.elements(); // 得到database元素下的子元素集合  
//          /* 
//           * 循环遍历集合中的每一个元素 将每一个元素的元素名和值在控制台中打印出来 
//           */  
//          for (Object obj : list) {  
//              Element element = (Element) obj;  
//              // getName()是元素名,getText()是元素值  
//              System.out  
//                      .println(element.getName() + ": " + element.getText());  
//          }  
	}

}
