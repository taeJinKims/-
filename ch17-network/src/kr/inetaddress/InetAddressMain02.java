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
			
			System.out.print("웹사이트 도메인 주소 : ");
			url = br.readLine();
			
			//해당 도메인과 매핑되어있는 모든 주소 반환
			
			address = InetAddress.getAllByName(url);
			
			for(int i = 0; i<address.length;i++){
				System.out.println("호스트 이름 : "+address[i].getHostName());
				System.out.println("ip주소 : "+address[i].getHostAddress());
			
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
