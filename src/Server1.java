//Server1.java
import java.net.*;
import java.io.*;
public class Server1{
	public static void main(String argv[]) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader sockIn;
		PrintWriter socketOut;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		try {
			//����ServerSocket
			serverSocket = new ServerSocket(8891);
			System.out.println("�����������˿ڣ�8891");
			socket = serverSocket.accept();//����������ֱ�����ӽ�����������socket����
			
			if(socket == null) {
				System.out.println("SocketΪ��");
				System.exit(1);
			}
			System.out.println("���յ�����"+socket.getInetAddress().getHostAddress()+"��������");
			//�õ�������
			sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//��ͻ��������Ϣ
			socketOut = new PrintWriter(socket.getOutputStream());
			socketOut.println("�����������ӣ�");
			socketOut.flush();
			//���տͻ��˵���Ϣ�����
			String s = sockIn.readLine();
			System.out.println("���յ����Կͻ��˵���Ϣ��"+s);
			//�ر����������
			socketOut.close();
			sockIn.close();
			socket.close();
			serverSocket.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("�������ر�");
	}
}
