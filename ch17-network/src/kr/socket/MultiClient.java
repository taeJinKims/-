package kr.socket;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MultiClient extends JFrame implements ActionListener{
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextField jtf;
	private JTextArea jta;
	private JLabel jlb1, jlb2;
	private JPanel jp1, jp2;
	private String ip;
	private String id;
	private JButton jbtn;
	
	public MultiClient(String ip, String id){
		super("Multi Chatting");
		this.ip = ip;
		this.id = id;
		
		jtf = new JTextField(30);
		jta = new JTextArea(10,50);
		jlb1 = new JLabel("사용자 ID["+id+"]");
		jlb2 = new JLabel("IP : "+ip);
		jbtn = new JButton("종료");
		jp1 = new JPanel();
		jp2= new JPanel();
		
		jta.setBackground(Color.PINK);
		jp1.setLayout(new BorderLayout());
		jp2.setLayout(new BorderLayout());
		
		jp1.add(jbtn, BorderLayout.EAST);
		jp1.add(jtf, BorderLayout.CENTER);
		jp2.add(jlb1, BorderLayout.CENTER);
		jp2.add(jlb2, BorderLayout.EAST);
		
		add(jp1,  BorderLayout.SOUTH);
		add(jp2,  BorderLayout.NORTH);
		
		JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(jsp, BorderLayout.CENTER);
		
		jta.setEditable(false);
		
		jtf.addActionListener(this);
		jbtn.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			//이벤트 핸들러
			@Override
			public void windowClosing(WindowEvent e){
				try{
					oos.writeObject(id+"#exit");
				}catch(IOException ie){
					ie.printStackTrace();
				}
				System.exit(0);
			}
			@Override
			public void windowOpened(WindowEvent e){
				jtf.requestFocus();
			}
		});
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		int screenWidth = d.width;
		int screenHight = d.height;
		
		//JFrame사이즈를 지정하지 않으며 컴포넌트 크기를 계산해서 자동으로 넓이와 높이를 지정함
		
		pack();
		
		setLocation((screenWidth-getWidth())/2, (screenHight - getHeight())/2);
		
		setResizable(false);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj ==jtf){	//JTextField에서 이벤트 발생
			//JTextField에서 입력한 데이터 읽기
			String msg = jtf.getText();
			if(msg == null || msg.length()==0){
				//데이터를 입력하지 않았을 경우
				JOptionPane.showMessageDialog(this,"글을 쓰세요","경고",JOptionPane.WARNING_MESSAGE);
			}else{
				try{
					oos.writeObject(id+"#"+msg);
				}catch(IOException ie){
					ie.printStackTrace();
				}
				jtf.setText("");
			}
			
		}else if(obj == jbtn){
			try{
				oos.writeObject(id+"#exit");
			}catch(IOException ie){
				ie.printStackTrace();
			}
			System.exit(0);
		}
	}
	
	//종료처리
	public void exit(){
		System.exit(0);
	}
	
	//입력 스트림 반환
	public ObjectInputStream getOis(){	
		return ois;
	}
	
	public JTextArea getJta(){
		return jta;
		
	}
	
	public String getId(){
		return id; 
	}
	
	public void init() throws IOException{
		socket = new Socket(ip,5000);
		System.out.println("conneted...");
		//서버에 데이터를 전송할 객체 생성
		oos = new ObjectOutputStream(socket.getOutputStream());
		//서버로부터 전달받은 데이터를 처리(입력)할 객체 생성
		ois = new ObjectInputStream(socket.getInputStream());
		
		//데이터 처리를 위한 스레드 생성
		MultiClientThread ct = new MultiClientThread(this);
		
		ct.start();
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		MultiClient cc = new MultiClient("192.168.10.14","dslfkjdls");
		
		try{
			cc.init();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
