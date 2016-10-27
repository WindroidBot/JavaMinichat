package minichat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SocketManager extends ArrayList{
	//连接套接字
	synchronized void add(Socket socket) {
		super.add(socket);
	}
	//删除套接字
	synchronized void remove(Socket socket) {
		super.remove(socket);
	}
	//输出消息
	synchronized void sendToAll(String str) {
		PrintWriter writer = null;
		Socket socket;
		for(int i = 0;i < size();i ++) {
			socket = (Socket)get(i);
			try {
				writer = new PrintWriter(socket.getOutputStream(),true);
				if(writer != null)
					writer.println(str);
			}catch(Exception e) {
				e.printStackTrace();		
			}
		}
	}
	//返回在线人数
	synchronized int sendClientInfo() {
		String info = "当前在线人数"+size();
		System.out.println(info);
		sendToAll(info);
		return size();
	}
}
