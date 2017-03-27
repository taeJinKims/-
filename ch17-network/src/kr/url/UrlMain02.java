package kr.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlMain02 {
	public static void main(String[] args) {
		URL url = null;
		BufferedReader input = null;
		String address = "http://www.naver.com";
		String line="";
		
		try{
			url = new URL(address);
			input = new BufferedReader(new InputStreamReader(url.openStream()));
			
			while((line = input.readLine())!=null){
				System.out.println(line);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input !=null){try{input.close();}catch(IOException e){}}
		}

	}

}
