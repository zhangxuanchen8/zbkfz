package com.ehinfo.hr.controller.declare;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;    
import java.io.IOException;  
import java.io.InputStream;    
import java.io.InputStreamReader;    
import java.io.OutputStreamWriter;  
import java.io.UnsupportedEncodingException;    
import java.net.HttpURLConnection;    
import java.net.InetSocketAddress;  
import java.net.Proxy;  
import java.net.URL;   
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;  
import java.util.Map;  

import com.ehinfo.hr.entity.declare.Declare;

public class DeclareThread extends Thread {
private  static final String path="";
static boolean proxySet = false;  
static String proxyHost = "127.0.0.1";  
static int proxyPort = 8087; 

	private Declare declare;

	public DeclareThread(Declare declare) {
		this.declare = declare;
	}

	@Override
	public void run() {

		try {
			
			sendPost(path, "json", proxySet);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	  /**  
     * 向指定 URL 发送POST方法的请求  
     *   
     * @param url  
     *            发送请求的 URL  
     * @param param  
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。  
     * @param isproxy  
     *               是否使用代理模式  
     * @return 所代表远程资源的响应结果  
     */  
    public static String sendPost(String path, String param,boolean isproxy) {  
        OutputStreamWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(path);  
            HttpURLConnection conn = null;  
            if(isproxy){//使用代理模式  
                @SuppressWarnings("static-access")  
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));  
                conn = (HttpURLConnection) realUrl.openConnection(proxy);  
            }else{  
                conn = (HttpURLConnection) realUrl.openConnection();  
            }  
            // 打开和URL之间的连接  
  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setRequestMethod("POST");    // POST方法  
  
  
            // 设置通用的请求属性  
  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            conn.setRequestProperty("content-Type", "application/json");  
            conn.connect();  
  
            // 获取URLConnection对象对应的输出流  
            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");  
            // 发送请求参数  
            out.write(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(  
                    new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！"+e);  
            e.printStackTrace();  
        }  
        //使用finally块来关闭输出流、输入流  
        finally{  
            try{  
                if(out!=null){  
                    out.close();  
                }  
                if(in!=null){  
                    in.close();  
                }  
            }  
            catch(IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }
    
    public static void main(String[] args) {
    	String sendPost = sendPost("http://m.weather.com.cn/data/101010100.html", "", false);
    	String str;
		try {
			str = new String(sendPost.getBytes("Gb2312"), "UTF-8"); 
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	System.out.println(sendPost);
	}
}