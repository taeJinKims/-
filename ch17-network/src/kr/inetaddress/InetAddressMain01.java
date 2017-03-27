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
			System.out.print("웹사이트 도메인명");
			url = br.readLine();
			
			//도메인명 사이트의 대표 ip주소를 반환
			address = InetAddress.getByName(url);
			System.out.println("호스트 이름 : "+address.getHostName());
			System.out.println("호스트 ip주소 : "+address.getHostAddress());
			
			System.out.println("======================");
			
			address = InetAddress.getLocalHost();
			
			System.out.println("로컬 호스트 이름 "+address.getHostName()+"\n로컬 호스트 주소 "+address.getHostAddress());
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){try{br.close();}catch(IOException e){e.printStackTrace();}}
		}
	}

}
