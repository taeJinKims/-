package kr.socket;


public class MultiClientThread extends Thread{
	private MultiClient mc;
	
	public MultiClientThread(MultiClient mc){
		this.mc = mc;
	}
	
	@Override
	public void run(){
		String message = null;
		String[] receivedMsg = null;
		boolean isStop = false;
		while(!isStop){
			try{
				message = (String)mc.getOis().readObject();
				receivedMsg = message.split("#");
			}catch(Exception e){
				e.printStackTrace();
				isStop = true;
			}
			System.out.println(receivedMsg[0]+","+receivedMsg[1]);
			
			if(receivedMsg[1].equals("exit")){	//종료메세지
				if(receivedMsg[0].equals(mc.getId())){
					mc.exit();
				}else{	//타인의 프로그램 종료
					mc.getJta().append(receivedMsg[0]+"님이 종료하셨습니다."+System.getProperty("line.separator"));
					mc.getJta().setCaretPosition(mc.getJta().getDocument().getLength());
				}
			}else{
				mc.getJta().append(
						receivedMsg[0]+" : "+receivedMsg[1]+System.getProperty("line.separator"));
				mc.getJta().setCaretPosition(mc.getJta().getDocument().getLength());
			}
		}
	}
}
