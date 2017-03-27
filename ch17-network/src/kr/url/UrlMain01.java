package kr.url;

import java.io.IOException;
import java.net.MalformedURLException;	//형식에 맞지 않는 주소형식예외처리
import java.net.URL;

public class UrlMain01 {
	public static void main(String[] args) {
		String address = "http://java.sun.com/index.jsp?name=kim#content";
		try{
			URL url = new URL(address);
			System.out.println("프로토콜 : "+url.getProtocol());
			System.out.println("호스트 : "+url.getHost());
			System.out.println("기본포트 : "+url.getDefaultPort());
			System.out.println("포트 : "+url.getPort());
			System.out.println("패스 : "+url.getPath());
			System.out.println("쿼리 : "+url.getQuery());
			System.out.println("ref : "+url.getRef());
			
		}catch(MalformedURLException e){
			e.printStackTrace();                                                                                                 
		}catch(IOException e){
			e.printStackTrace();
		}finally{

		}

	}

}
