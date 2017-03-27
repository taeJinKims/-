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
		
		//��½�Ʈ��
		OutputStream os_socket = tcpSocket.getOutputStream();
		
		//�Է½�Ʈ��
		InputStream is_socket = tcpSocket.getInputStream();
		
		BufferedWriter bufferW = new BufferedWriter(new OutputStreamWriter(os_socket));
		BufferedReader bufferR = new BufferedReader(new InputStreamReader(is_socket));
		System.out.print("�Է� : ");
		
		//�ֿܼ��� ������ �Է��� ���� �Է¹��� ��Ʈ�� ����
		file = new BufferedReader(new InputStreamReader(System.in));
		
		str = file.readLine();
		str+=System.getProperty("line.separator");	//�� ������ó��(os���� �౸���ڰ� �ٸ��� ������ �����) �����Ͱ� ������ �˸�
		
		bufferW.write(str);
		bufferW.flush();	//���۸� ���� �����͸� ������ ����
		
		str = bufferR.readLine();
		
		System.out.println("�������� ���۵� ������ : "+str);
		
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
