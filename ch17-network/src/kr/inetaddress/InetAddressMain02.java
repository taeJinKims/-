package kr.inetaddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetAddressMain02 {

	public static void main(String[] args) {
		BufferedReader br = null;
		String url = null;
		InetAddress[] address;
		
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("������Ʈ ������ �ּ� : ");
			url = br.readLine();
			
			//�ش� �����ΰ� ���εǾ��ִ� ��� �ּ� ��ȯ
			
			address = InetAddress.getAllByName(url);
			
			for(int i = 0; i<address.length;i++){
				System.out.println("ȣ��Ʈ �̸� : "+address[i].getHostName());
				System.out.println("ip�ּ� : "+address[i].getHostAddress());
			
			}
			
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){try{br.close();}catch(IOException e){e.printStackTrace();}}
		}

	}

}
