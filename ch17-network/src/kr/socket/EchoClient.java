package kr.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class EchoClient {
	private String ip;
	private int port;
	private String str;
	BufferedReader file;
	
	public EchoClient(String ip, int port) throws IOException{
		this.ip = ip;
		this.port = port;
		
		Socket tcpSocket = getSocket();
		
		//출력스트림
		OutputStream os_socket = tcpSocket.getOutputStream();
		
		//입력스트림
		InputStream is_socket = tcpSocket.getInputStream();
		
		BufferedWriter bufferW = new BufferedWriter(new OutputStreamWriter(os_socket));
		BufferedReader bufferR = new BufferedReader(new InputStreamReader(is_socket));
		System.out.print("입력 : ");
		
		//콘솔에서 데이터 입력을 위한 입력문자 스트림 생성
		file = new BufferedReader(new InputStreamReader(System.in));
		
		str = file.readLine();
		str+=System.getProperty("line.separator");	//줄 구분자처리(os마다 행구분자가 다르기 때문에 사용함) 데이터가 끝남을 알림
		
		bufferW.write(str);
		bufferW.flush();	//버퍼를 비우고 데이터를 서버에 전송
		
		str = bufferR.readLine();
		
		System.out.println("서버에서 전송된 데이터 : "+str);
		
		file.close();
		bufferW.close();
		bufferR.close();
		tcpSocket.close();
		
	}
	
	public Socket getSocket(){
		Socket tcpSocket = null;
		
		try{
			tcpSocket = new Socket(ip, port);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return tcpSocket;
	}
	
	public static void main(String[] args) {
		try{
			new EchoClient("192.168.10.14",3000);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
