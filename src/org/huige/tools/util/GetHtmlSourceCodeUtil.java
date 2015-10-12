package org.huige.tools.util;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 通过模拟IE浏览器方式获取HTML源码
 * @author huige
 */
public class GetHtmlSourceCodeUtil {
	private static String cookies=";";
	/**
	 * 通过模拟IE浏览器方式获取HTML源码
	 * 例子GetHtmlSourceCodeUtil.getHttpSourceCode("GET",monsterUrl,null,null,"GB2312")
	 * @param mode GET 或者 POST
	 * @param url 访问的URL全路径
	 * @param post 以POST方式访问时，是否提交参数
	 * @param cookie 是否有cookie
	 * @param coder 访问时的编码方式
	 * @return HTML源代码
	 */
	public static StringBuffer getHttpSourceCode(String mode,String url,String post,String cookie,String coder){
	       InputStreamReader fin;
	       StringBuffer sbuf = new StringBuffer();
	       int l;
	       char c;
	        try{
	            URL urls=new URL(url);
	            HttpURLConnection http= (HttpURLConnection)urls.openConnection();
	            http.setConnectTimeout(60000);
	            http.setReadTimeout(60000);
	            http.setRequestProperty("Host",urls.getHost());
	            //ie8
	            http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322)");
	            //火狐
//	            http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.5) Gecko/20041108 Firefox/1.5");
	            http.setRequestProperty("Referer",url);
	            http.setRequestProperty("Accept", "text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
	            http.setRequestProperty("Accept-Language","zh-cn,zh;q=0.5");
	            http.setRequestProperty("Accept-Charset","gbk,gb2312,utf-8;q=0.7,*;q=0.7");
	            http.setRequestProperty("Keep-Alive","300");
	            http.setRequestProperty("Connection","keep-alive");
	            http.setRequestProperty("Content-type","application/x-www-form-urlencoded");
	            http.setRequestProperty("Referer",url);
	            if(cookie!=null) http.setRequestProperty("Cookie",cookie);
	            http.setRequestMethod(mode);
	            if(mode.compareTo("POST")==0){
	            http.setRequestProperty("Content-Length",Integer.toString(post.length()));
	            http.setDoOutput(true);
	            OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream(),coder);
	            out.write(post);
	            out.close();
	            }
	            fin = new InputStreamReader(http.getInputStream(),coder);
	            //boolean w=true;
	            for(int i=0;i<5000000;i++){
	            l = fin.read();
	            if(l==-1) break;
	            c = (char)l;
	            sbuf.append(c);
	            }
	           cookies=getCookies(http);
	            http.disconnect();
	           }catch(Exception ex){
	        	   Logger.getLogger(GetHtmlSourceCodeUtil.class.getName()).log(Level.SEVERE, null, ex);
	        	   return null;
	           }
	        if(sbuf.length()>0) return sbuf; else return null;
	    }
	/**
	 * getCookies
	 *@param http
	 *@return
	 */
	 private static String getCookies(HttpURLConnection http){
	        String key,cookie;
	        StringBuffer buf=new StringBuffer();
	        for(int i=1;i<100;i++){
	            key=http.getHeaderFieldKey(i);
	            if(key==null) break;
	            if(key.compareToIgnoreCase("set-cookie")==0){
	                cookie=http.getHeaderField(i);
	                cookie=cookie.substring(0,cookie.indexOf(';'));
	                buf.append(cookie+';');
	            }
	        }
	        if(buf.length()>0) return buf.toString(); else return null;
	    }
}

