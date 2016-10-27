//Client1.java
import java.net.Socket;
import java.io.*;
public class Client1 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader sockIn;
		PrintWriter sockOut;
		try {
			//����һ�����ӷ�������socket��IP 127.0.0.1 Port 8890
			socket = new Socket("127.0.0.1",8891);
			if(socket == null) {
				System.out.println("Socket is null,connect Error!");
				System.exit(1);
			}
			//ʹ��getInputStream()��getOutputStream()�õ����������
			sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sockOut = new PrintWriter(socket.getOutputStream());
			//������������Ϣ
			sockOut.println("Hello,I'm Client!");
			sockOut.flush();
			//�ӷ�������ȡ��Ϣ
			String s = sockIn.readLine();
			System.out.println("Client received:" + s);
			//�ر�����
			sockOut.close();
			sockIn.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Client exit!");
	}
}
