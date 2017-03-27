package kr.inetaddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain01 {
	public static void main(String[] args) {
		BufferedReader br = null;
		String url = null;
		InetAddress address = null;
		
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("������Ʈ �����θ�");
			url = br.readLine();
			
			//�����θ� ����Ʈ�� ��ǥ ip�ּҸ� ��ȯ
			address = InetAddress.getByName(url);
			System.out.println("ȣ��Ʈ �̸� : "+address.getHostName());
			System.out.println("ȣ��Ʈ ip�ּ� : "+address.getHostAddress());
			
			System.out.println("======================");
			
			address = InetAddress.getLocalHost();
			
			System.out.println("���� ȣ��Ʈ �̸� "+address.getHostName()+"\n���� ȣ��Ʈ �ּ� "+address.getHostAddress());
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){try{br.close();}catch(IOException e){e.printStackTrace();}}
		}
	}

}
