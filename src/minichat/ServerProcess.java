//ServerProcess.java
//服务器端程序
package minichat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProcess {
	private int userNum = 0;
	private SocketManager socketMan = new SocketManager();
	
	//返回连接数
	public int userinfo() {
		return userNum;
	}
	
	//判断客户端是否连接
	
	
	
	
	
	void getServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(8892);
			System.out.println("服务器套接字已创建");
			while(true) {
				Socket socket = serverSocket.accept();
				new write_Thread(socket).start();
				socketMan.add(socket);
				userNum = socketMan.sendClientInfo();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	class write_Thread extends Thread{
		Socket socket = null;
		private BufferedReader reader;
		private PrintWriter writer;
		public write_Thread(Socket socket) {
			this.socket = socket;
		}
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				String msg;				
				while((msg = reader.readLine()) != null) {
					System.out.println(msg);
					socketMan.sendToAll(msg);
					//socketMan.sendClientInfo();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//主方法
	public static void main(String[] args) {
		ServerProcess server = new ServerProcess();
		server.getServer();
	}
}
