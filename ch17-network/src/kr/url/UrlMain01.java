package kr.url;

import java.io.IOException;
import java.net.MalformedURLException;	//���Ŀ� ���� �ʴ� �ּ����Ŀ���ó��
import java.net.URL;

public class UrlMain01 {
	public static void main(String[] args) {
		String address = "http://java.sun.com/index.jsp?name=kim#content";
		try{
			URL url = new URL(address);
			System.out.println("�������� : "+url.getProtocol());
			System.out.println("ȣ��Ʈ : "+url.getHost());
			System.out.println("�⺻��Ʈ : "+url.getDefaultPort());
			System.out.println("��Ʈ : "+url.getPort());
			System.out.println("�н� : "+url.getPath());
			System.out.println("���� : "+url.getQuery());
			System.out.println("ref : "+url.getRef());
			
		}catch(MalformedURLException e){
			e.printStackTrace();                                                                                                 
		}catch(IOException e){
			e.printStackTrace();
		}finally{

		}

	}

}
