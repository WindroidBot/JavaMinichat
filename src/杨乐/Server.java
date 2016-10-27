package 杨乐; 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
	private List<Socket> sockets=new ArrayList<Socket>();
	
	
	synchronized void sendToAll(String string){
		PrintWriter writer;
		
		for(Socket socket:sockets){
			try {
				writer=new PrintWriter(socket.getOutputStream());
				writer.println(string);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}
	class write_Thread extends Thread{
		private BufferedReader reader;
//		private DataInputStream read;
		private PrintWriter writer;
		private Socket socket;
		public write_Thread(Socket socket) {
			this.socket=socket;
		}
		@Override
		public void run() {
			try {
				reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer=new PrintWriter(socket.getOutputStream(),true);
				String msString;
				while((msString=reader.readLine())!=null){
					sendToAll(msString);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	void getServer(){
		try {
			ServerSocket serverSocket=new ServerSocket(7235);
			System.out.println("服务器已经启动");
			while(true){
				Socket socket=serverSocket.accept();
				new write_Thread(socket).start();
				sockets.add(socket);
				sendToAll("当前用户在线人数："+sockets.size());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
