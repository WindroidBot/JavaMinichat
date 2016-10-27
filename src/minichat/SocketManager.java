package minichat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SocketManager extends ArrayList{
	//�����׽���
	synchronized void add(Socket socket) {
		super.add(socket);
	}
	//ɾ���׽���
	synchronized void remove(Socket socket) {
		super.remove(socket);
	}
	//�����Ϣ
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
	//������������
	synchronized int sendClientInfo() {
		String info = "��ǰ��������"+size();
		System.out.println(info);
		sendToAll(info);
		return size();
	}
}
